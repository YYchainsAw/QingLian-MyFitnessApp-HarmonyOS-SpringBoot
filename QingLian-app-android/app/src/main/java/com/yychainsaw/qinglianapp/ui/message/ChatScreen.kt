package com.yychainsaw.qinglianapp.ui.message

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.dto.MessageSendDTO
import com.yychainsaw.qinglianapp.data.model.vo.GroupMemberVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.network.WebSocketManager
import com.yychainsaw.qinglianapp.ui.community.resolveImageUrl
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// ==========================================
// 本地 UI 模型定义
// ==========================================

data class UiMessage(
    val id: Long,
    val senderId: String,
    val content: String,
    val sentAt: String,
    val senderAvatar: String? = null,
    val senderName: String? = null
)

sealed interface ChatUiItem {
    data class Message(val uiMessage: UiMessage) : ChatUiItem
    data class Time(val timeDisplay: String) : ChatUiItem
}

// ==========================================
// 聊天页面
// ==========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    friendId: String,
    friendName: String,
    friendAvatar: String?
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val isGroupChat = friendId.startsWith("GROUP_")
    val realChatId = if (isGroupChat) friendId.removePrefix("GROUP_") else friendId

    // 原始消息列表（按时间正序：旧 -> 新）
    var rawMessages by remember { mutableStateOf<List<UiMessage>>(emptyList()) }
    // 群成员缓存 Map <UserId, GroupMemberVO>
    var groupMembers by remember { mutableStateOf<Map<String, GroupMemberVO>>(emptyMap()) }

    var inputText by remember { mutableStateOf("") }
    var currentUserId by remember { mutableStateOf("") }
    var currentUserAvatar by remember { mutableStateOf<String?>(null) }
    var isFriend by remember { mutableStateOf(isGroupChat) }

    // 定义添加好友的函数
    fun addFriend() {
        scope.launch {
            try {
                val response = RetrofitClient.apiService.sendFriendRequest(realChatId)
                if (response.isSuccess()) {
                    Toast.makeText(context, "好友请求已发送", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "请求失败: ${response.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 处理消息列表：添加时间分隔符并反转以适应 reverseLayout
    val displayMessages by remember(rawMessages) {
        derivedStateOf {
            val result = mutableListOf<ChatUiItem>()
            var lastTime: Long = 0
            val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

            rawMessages.forEach { msg ->
                val msgTime = try {
                    timeFormat.parse(msg.sentAt)?.time ?: 0L
                } catch (e: Exception) { 0L }

                // 如果距离上一条消息超过 5 分钟 (300,000 毫秒)，显示时间
                if (msgTime - lastTime > 300000) {
                    val displayTime = formatChatTime(msgTime)
                    result.add(ChatUiItem.Time(displayTime))
                    lastTime = msgTime
                }
                result.add(ChatUiItem.Message(msg))
            }
            // LazyColumn reverseLayout=true，所以列表需要反转（新消息在 index 0）
            result.reversed()
        }
    }

    LaunchedEffect(Unit) {
        try {
            // 1. 获取当前用户信息
            val userRes = RetrofitClient.apiService.getUserInfo()
            if (userRes.isSuccess()) {
                currentUserId = userRes.data?.userId ?: userRes.data?.username ?: ""
                currentUserAvatar = userRes.data?.avatarUrl
            }

            // 2. 如果是群聊，获取群成员信息（用于显示头像和昵称）
            if (isGroupChat) {
                launch {
                    try {
                        val membersRes = RetrofitClient.apiService.getGroupMembers(realChatId.toLong())
                        if (membersRes.isSuccess()) {
                            // 将 List 转为 Map，方便后续根据 userId 快速查找
                            groupMembers = membersRes.data?.associateBy { it.userId } ?: emptyMap()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            // 3. 加载历史记录
            loadHistory(realChatId, isGroupChat) { msgs ->
                rawMessages = msgs
            }

            // 4. 私聊逻辑：标记已读、检查好友关系
            if (!isGroupChat) {
                RetrofitClient.apiService.markAsRead(realChatId)
                val friendsRes = RetrofitClient.apiService.getFriends()
                if (friendsRes.isSuccess()) {
                    val friendList = friendsRes.data ?: emptyList()
                    isFriend = friendList.any { it.userId == realChatId }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // --- WebSocket 监听逻辑 ---
    LaunchedEffect(Unit) {
        if (isGroupChat) {
            try { WebSocketManager.joinGroup(realChatId.toLong()) } catch (e: Exception) { e.printStackTrace() }
        }

        WebSocketManager.messageFlow.collect { msgVO ->
            // WebSocket 返回的是 MessageVO (id, senderId, senderName)
            val isCurrentSessionMsg = if (isGroupChat) {
                msgVO.groupId?.toString() == realChatId
            } else {
                msgVO.senderId == realChatId
            }

            if (isCurrentSessionMsg) {
                // 关键修复：防止重复添加（WebSocket 推送的和本地发送成功的可能是同一条）
                if (rawMessages.any { it.id == msgVO.id }) return@collect

                val newMsg = UiMessage(
                    id = msgVO.id,
                    senderId = msgVO.senderId,
                    content = msgVO.content,
                    sentAt = msgVO.sentAt,
                    senderAvatar = null, // 此时为 null，UI 渲染时会动态查找
                    senderName = msgVO.senderName
                )
                rawMessages = rawMessages + newMsg

                scope.launch {
                    if (displayMessages.isNotEmpty()) listState.scrollToItem(0)
                }

                if (!isGroupChat) {
                    try { RetrofitClient.apiService.markAsRead(realChatId) } catch (_: Exception) {}
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Text(text = friendName, fontWeight = FontWeight.Bold)
                            if (isGroupChat) {
                                Text(text = "群成员: ${groupMembers.size}人", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
                if (!isFriend && !isGroupChat) {
                    Surface(
                        color = Color(0xFFFFF3E0),
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("你们还不是好友", color = Color(0xFFE65100))
                            Button(
                                onClick = { addFriend() },
                                colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow)
                            ) {
                                Text("添加好友", color = Color.Black)
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            if (isFriend || isGroupChat) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color.White).padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("发送消息...") },
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = QingLianYellow
                        )
                    )
                    IconButton(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                val contentToSend = inputText
                                inputText = "" // 立即清空输入框，提升体验
                                scope.launch {
                                    try {
                                        val dto = MessageSendDTO(
                                            receiverId = if (isGroupChat) null else realChatId,
                                            groupId = if (isGroupChat) realChatId.toLong() else null,
                                            content = contentToSend
                                        )
                                        val res = RetrofitClient.apiService.sendMessage(dto)
                                        if (res.isSuccess()) {
                                            val sentMsg = res.data!!
                                            // 关键修复：检查是否已存在（防止 WebSocket 比 API 回调更快导致重复）
                                            if (rawMessages.none { it.id == sentMsg.id }) {
                                                val newUiMsg = UiMessage(
                                                    id = sentMsg.id,
                                                    senderId = sentMsg.senderId,
                                                    content = sentMsg.content,
                                                    sentAt = sentMsg.sentAt,
                                                    senderAvatar = null,
                                                    senderName = sentMsg.senderName
                                                )
                                                rawMessages = rawMessages + newUiMsg
                                                listState.scrollToItem(0)
                                            }
                                        } else {
                                            Toast.makeText(context, "发送失败: ${res.message}", Toast.LENGTH_SHORT).show()
                                            inputText = contentToSend // 恢复文本
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        Toast.makeText(context, "发送错误", Toast.LENGTH_SHORT).show()
                                        inputText = contentToSend // 恢复文本
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send", tint = QingLianYellow)
                    }
                }
            }
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        LazyColumn(
            state = listState,
            reverseLayout = true,
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(displayMessages) { item ->
                when (item) {
                    is ChatUiItem.Time -> {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = item.timeDisplay,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp)).padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    is ChatUiItem.Message -> {
                        val msg = item.uiMessage
                        val isMe = msg.senderId == currentUserId

                        // ============================================================
                        // 关键修复：在此处动态解析头像和昵称
                        // ============================================================
                        val specificAvatar = when {
                            isMe -> currentUserAvatar // 如果是我，用我的头像
                            isGroupChat -> groupMembers[msg.senderId]?.avatarUrl // 群聊：查表
                            else -> friendAvatar // 私聊：用传进来的好友头像
                        }

                        val specificName = when {
                            isGroupChat && !isMe -> groupMembers[msg.senderId]?.nickname ?: groupMembers[msg.senderId]?.username ?: msg.senderName
                            else -> null // 私聊或自己通常不显示名字
                        }

                        MessageItemRow(
                            msg = msg.copy(senderName = specificName), // 传入解析后的名字
                            isMe = isMe,
                            avatarUrl = specificAvatar, // 传入解析后的头像
                            showNickname = isGroupChat && !isMe
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItemRow(msg: UiMessage, isMe: Boolean, avatarUrl: String?, showNickname: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!isMe) {
            ChatAvatar(avatarUrl)
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(horizontalAlignment = if (isMe) Alignment.End else Alignment.Start, modifier = Modifier.weight(1f, fill = false)) {
            if (showNickname && !msg.senderName.isNullOrBlank()) {
                Text(
                    text = msg.senderName,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 2.dp, start = 4.dp)
                )
            }

            Surface(
                color = if (isMe) QingLianYellow else Color.White,
                shape = if (isMe) RoundedCornerShape(16.dp, 4.dp, 16.dp, 16.dp) else RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp),
                shadowElevation = 1.dp
            ) {
                Text(text = msg.content, modifier = Modifier.padding(12.dp), fontSize = 16.sp, color = Color.Black)
            }
        }

        if (isMe) {
            Spacer(modifier = Modifier.width(8.dp))
            ChatAvatar(avatarUrl)
        }
    }
}

@Composable
fun ChatAvatar(url: String?) {
    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray), contentAlignment = Alignment.Center) {
        if (url.isNullOrBlank()) Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
        else AsyncImage(model = resolveImageUrl(url), contentDescription = "Avatar", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
    }
}

suspend fun loadHistory(chatId: String, isGroup: Boolean, onResult: (List<UiMessage>) -> Unit) {
    try {
        val response = if (isGroup) {
            RetrofitClient.apiService.getGroupMessageHistory(chatId.toLong(), 1, 50)
        } else {
            RetrofitClient.apiService.getMessageHistory(chatId, 1, 50)
        }

        if (response.isSuccess()) {
            val items = response.data?.items ?: emptyList()
            val uiMessages = items.map { item ->
                UiMessage(
                    id = item.msgId, // 确保 MessageEntity 有 msgId 字段
                    senderId = item.senderId,
                    content = item.content,
                    sentAt = item.sentAt,
                    senderAvatar = null, // 历史记录无头像，依赖 UI 层 groupMembers 解析
                    senderName = null    // 历史记录无昵称，依赖 UI 层 groupMembers 解析
                )
            }
            onResult(uiMessages)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun formatChatTime(timeMillis: Long): String {
    val now = Calendar.getInstance()
    val msgTime = Calendar.getInstance().apply { timeInMillis = timeMillis }

    return when {
        now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == msgTime.get(Calendar.DAY_OF_YEAR) -> {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timeMillis))
        }
        now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR) -> {
            SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(Date(timeMillis))
        }
        else -> {
            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(timeMillis))
        }
    }
}
