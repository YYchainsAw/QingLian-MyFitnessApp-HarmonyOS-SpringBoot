package com.yychainsaw.qinglianapp.ui.community

import android.Manifest // Import Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yychainsaw.qinglianapp.data.model.dto.PostCreateDTO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import com.yychainsaw.qinglianapp.utils.UriUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    // 存储已上传成功的图片 URL 列表
    var imageUrls by remember { mutableStateOf<List<String>>(emptyList()) }
    var isUploading by remember { mutableStateOf(false) } // 图片上传中
    var isPosting by remember { mutableStateOf(false) }   // 帖子发布中
    var showDialog by remember { mutableStateOf(false) } // 控制选择弹窗

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // ---- 相机相关 ----
    var photoUri by remember { mutableStateOf<Uri?>(null) } // 拍照保存的URI

    // 创建临时文件并返回 URI
    fun createImageUri(): Uri? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        // 使用 cacheDir 或 externalCacheDir
        val storageDir = File(context.cacheDir, "my_images")
        if (!storageDir.exists()) storageDir.mkdirs()

        val image = File.createTempFile(imageFileName, ".jpg", storageDir)

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            image
        )
    }

    // 上传逻辑封装
    fun uploadUris(uris: List<Uri>) {
        if (uris.isEmpty()) return

        // 检查是否超过总数限制
        if (imageUrls.size + uris.size > 9) {
            Toast.makeText(context, "最多只能上传9张图片", Toast.LENGTH_SHORT).show()
            return
        }

        isUploading = true
        scope.launch {
            val uploadJobs = uris.map { uri ->
                async {
                    try {
                        val part = UriUtils.prepareFilePart(context, uri, "file")
                        if (part != null) {
                            val response = RetrofitClient.apiService.upload(part)
                            if (response.isSuccess() && response.data != null) {
                                response.data // 返回 URL string
                            } else {
                                null
                            }
                        } else {
                            null
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
            }
            // 拿到所有结果，过滤 null
            val results = uploadJobs.awaitAll().filterNotNull()
            if (results.isNotEmpty()) {
                imageUrls = imageUrls + results
            }

            isUploading = false
            Toast.makeText(context, "上传处理完成", Toast.LENGTH_SHORT).show()
        }
    }

    // 相机启动器
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            uploadUris(listOf(photoUri!!))
        }
    }

    // 权限请求启动器 (相机)
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            val uri = createImageUri()
            if (uri != null) {
                photoUri = uri
                cameraLauncher.launch(uri)
            }
        } else {
            Toast.makeText(context, "需要相机权限才能拍照", Toast.LENGTH_SHORT).show()
        }
    }

    // 图片选择器 (多选，最多9张)
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(9)
    ) { uris ->
        uploadUris(uris)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("选择图片来源") },
            text = {
                Column {
                    ListItem(
                        headlineContent = { Text("拍照") },
                        leadingContent = { Icon(Icons.Default.CameraAlt, null) },
                        modifier = Modifier
                            .clickable {
                                showDialog = false
                                // 检查权限
                                val permission = Manifest.permission.CAMERA
                                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                                    val uri = createImageUri()
                                    if (uri != null) {
                                        photoUri = uri
                                        cameraLauncher.launch(uri)
                                    }
                                } else {
                                    permissionLauncher.launch(permission)
                                }
                            }
                    )
                    ListItem(
                        headlineContent = { Text("从相册选择") },
                        leadingContent = { Icon(Icons.Default.Photo, null) },
                        modifier = Modifier
                            .clickable {
                                showDialog = false
                                pickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    )
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("取消")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("发布动态", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("取消", color = Color.Gray)
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            if (content.isBlank() && imageUrls.isEmpty()) {
                                Toast.makeText(context, "请输入内容或上传图片", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            isPosting = true
                            scope.launch {
                                try {
                                    val postDto = PostCreateDTO(content, imageUrls)
                                    val response = RetrofitClient.apiService.createPost(postDto)
                                    if (response.isSuccess()) {
                                        Toast.makeText(context, "发布成功", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    } else {
                                        Toast.makeText(context, "发布失败: ${response.message}", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(context, "网络错误: ${e.message}", Toast.LENGTH_SHORT).show()
                                } finally {
                                    isPosting = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = QingLianYellow),
                        enabled = !isUploading && !isPosting,
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        if (isPosting) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.Black)
                        } else {
                            Text("发布", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 文本输入框
            BasicTextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // 占据剩余空间，把图片区域推到底部或中间
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (content.isEmpty()) {
                            Text("分享你的健身心得...", color = Color.LightGray, fontSize = 16.sp)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 图片九宫格区域
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp) // 限制最大高度
            ) {
                // 1. 显示已上传的图片
                items(imageUrls) { url ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        AsyncImage(
                            model = resolveImageUrl(url), // 使用 CommunityScreen 中的辅助函数
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        // 删除按钮
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(4.dp)
                                .size(20.dp)
                                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                                .clickable {
                                    imageUrls = imageUrls - url
                                }
                        )
                    }
                }

                // 2. 显示添加按钮 (如果未满9张)
                if (imageUrls.size < 9) {
                    item {
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .background(Color(0xFFF5F7FA), RoundedCornerShape(8.dp))
                                .clickable(enabled = !isUploading) {
                                    // 点击添加按钮，弹出选择对话框
                                    showDialog = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isUploading) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = QingLianYellow)
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Image",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}