package com.yychainsaw.qinglianapp.ui.community

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.vo.PostVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.launch

fun resolveImageUrl(url: String?): String {
    if (url.isNullOrBlank()) return ""
    if (url.startsWith("http://") || url.startsWith("https://")) {
        return url
    }
    val cleanBase = "https://5725cab8.r12.vip.cpolar.cn".trimEnd('/')
    val cleanPath = url.trimStart('/')
    return "$cleanBase/$cleanPath"
}

fun formatDate(time: String?): String {
    if (time.isNullOrBlank()) return ""
    return try {
        time.split(" ")[0]
    } catch (e: Exception) {
        time
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(onPostCreate: () -> Unit) {
    var posts by remember { mutableStateOf<List<PostVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getFeed()
            if (response.isSuccess() && response.data != null) {
                posts = response.data.items
            } else {
                Toast.makeText(context, "加载失败: ${response.message}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onPostCreate,
                containerColor = QingLianYellow,
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Text("+", fontSize = 32.sp, fontWeight = FontWeight.Light)
            }
        },
        containerColor = Color(0xFFF2F4F7)
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = QingLianYellow)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 预留：推荐关注/达人 (getInfluencers, getRecommendFriends)
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text("推荐关注", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(5) { // 模拟5个推荐位
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.width(100.dp).clickable { /* TODO: 查看用户 */ }
                                ) {
                                    Column(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(QingLianBlue.copy(alpha = 0.2f)))
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text("健身达人", fontSize = 12.sp, maxLines = 1)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Icon(Icons.Default.PersonAdd, contentDescription = null, tint = QingLianYellow, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                items(posts) { post ->
                    CommunityCard(post)
                }
            }
        }
    }
}

@Composable
fun CommunityCard(post: PostVO) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isLiked by remember(post.isLiked) { mutableStateOf(post.isLiked) }
    var likeCount by remember(post.likeCount) { mutableIntStateOf(post.likeCount) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = resolveImageUrl(post.authorAvatar),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(QingLianBlue.copy(alpha = 0.2f))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = post.authorName, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF222222))
                    Text(text = formatDate(post.createTime), fontSize = 11.sp, color = Color(0xFF999999))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            if (post.content.isNotBlank()) {
                Text(text = post.content, fontSize = 15.sp, color = Color(0xFF333333), lineHeight = 24.sp, letterSpacing = 0.5.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (!post.imageUrls.isNullOrEmpty()) {
                PostImageGrid(post.imageUrls)
                Spacer(modifier = Modifier.height(16.dp))
            }
            Divider(color = Color(0xFFF5F5F5), thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                InteractionButton(
                    icon = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    text = if (likeCount > 0) likeCount.toString() else "点赞",
                    isActive = isLiked,
                    activeColor = QingLianYellow,
                    onClick = {
                        isLiked = !isLiked
                        likeCount += if (isLiked) 1 else -1
                        scope.launch {
                            try {
                                RetrofitClient.apiService.likePost(post.postId)
                            } catch (e: Exception) {
                                isLiked = !isLiked
                                likeCount += if (isLiked) 1 else -1
                                Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun InteractionButton(icon: ImageVector, text: String, isActive: Boolean = false, activeColor: Color = Color.Red, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(onClick = onClick).padding(4.dp)) {
        Icon(imageVector = icon, contentDescription = null, tint = if (isActive) activeColor else Color(0xFF666666), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 13.sp, color = if (isActive) activeColor else Color(0xFF666666))
    }
}

@Composable
fun PostImageGrid(imageUrls: List<String>) {
    val count = imageUrls.size
    val rows = when {
        count == 1 -> 1
        count <= 3 -> 1
        count <= 6 -> 2
        else -> 3
    }
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 0 until rows) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                val startIndex = i * 3
                val rowCount = if (count == 1) 1 else 3
                val endIndex = minOf(startIndex + rowCount, count)
                for (j in startIndex until endIndex) {
                    val url = imageUrls[j]
                    val height = if (count == 1) 220.dp else 110.dp
                    val modifier = if (count == 1) Modifier.fillMaxWidth(0.7f).height(height) else Modifier.weight(1f).height(height)
                    AsyncImage(
                        model = resolveImageUrl(url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.clip(RoundedCornerShape(8.dp)).background(QingLianBlue.copy(alpha = 0.1f))
                    )
                }
                if (count > 1) {
                    val itemsInRow = endIndex - startIndex
                    if (itemsInRow < 3) {
                        repeat(3 - itemsInRow) { Spacer(modifier = Modifier.weight(1f)) }
                    }
                }
            }
        }
    }
}
