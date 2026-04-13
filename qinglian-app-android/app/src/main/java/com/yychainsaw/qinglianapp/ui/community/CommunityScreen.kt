// file: app/src/main/java/com/yychainsaw/qinglianapp/ui/community/CommunityScreen.kt
package com.yychainsaw.qinglianapp.ui.community

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.yychainsaw.qinglianapp.data.model.vo.PostVO
import com.yychainsaw.qinglianapp.network.RetrofitClient
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.OutputStream

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
        time.split("T")[0]
    } catch (e: Exception) {
        time
    }
}

// === 保存图片到相册的辅助函数 ===
suspend fun saveImageToGallery(context: Context, imageUrl: String) {
    try {
        val loader = context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(resolveImageUrl(imageUrl))
            .allowHardware(false) // 必须禁用硬件位图才能复制
            .build()

        val result = loader.execute(request)
        if (result is SuccessResult) {
            val bitmap = (result.drawable as BitmapDrawable).bitmap
            val filename = "QL_${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { context.contentResolver.openOutputStream(it) }
            } else {
                // 兼容旧版本 Android (需确保有 WRITE_EXTERNAL_STORAGE 权限)
                val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val image = java.io.File(imagesDir, filename)
                fos = java.io.FileOutputStream(image)
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "图片已保存到相册", Toast.LENGTH_SHORT).show()
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "保存失败: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(onPostCreate: () -> Unit) {
    var posts by remember { mutableStateOf<List<PostVO>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    // 新增状态：当前正在查看的大图 URL
    var viewingImageUrl by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getFeed()
            if (response.isSuccess() && response.data != null) {
                posts = response.data.items
            } else {
                Toast.makeText(context, "加载动态失败: ${response.message}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "网络请求异常", Toast.LENGTH_SHORT).show()
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White,
                modifier = Modifier.zIndex(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "社区动态",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onPostCreate,
                containerColor = QingLianYellow,
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                shape = CircleShape
            ) {
                Text("+", fontSize = 32.sp, fontWeight = FontWeight.Light)
            }
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = QingLianYellow)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(posts) { post ->
                    // 传递点击事件
                    CommunityCard(post, onImageClick = { url -> viewingImageUrl = url })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    // === 全屏图片查看器 ===
    if (viewingImageUrl != null) {
        FullScreenImageViewer(
            imageUrl = viewingImageUrl!!,
            onDismiss = { viewingImageUrl = null }
        )
    }
}

@Composable
fun CommunityCard(post: PostVO, onImageClick: (String) -> Unit) {
    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableIntStateOf(post.likeCount) }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = resolveImageUrl(post.authorAvatar),
                    contentDescription = "Author Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFFEEEEEE), CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(post.authorName, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333333))
                    Text(formatDate(post.createTime), fontSize = 12.sp, color = Color(0xFF999999))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* TODO */ }) {
                    Icon(Icons.Default.MoreHoriz, contentDescription = "More", tint = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = post.content,
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (!post.imageUrls.isNullOrEmpty()) {
                // 传递点击事件
                PostImageGrid(images = post.imageUrls, onImageClick = onImageClick)
                Spacer(modifier = Modifier.height(16.dp))
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFEEEEEE))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                InteractionButton(
                    icon = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    text = if (likeCount > 0) likeCount.toString() else "点赞",
                    color = if (isLiked) Color.Red else Color.Gray,
                    onClick = {
                        isLiked = !isLiked
                        if (isLiked) likeCount++ else likeCount--
                    },
                    animateIcon = true
                )
            }
        }
    }
}

@Composable
fun PostImageGrid(images: List<String>, onImageClick: (String) -> Unit) {
    val imageCount = images.size

    if (imageCount == 1) {
        AsyncImage(
            model = resolveImageUrl(images[0]),
            contentDescription = "Post Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 250.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onImageClick(images[0]) } // 点击事件
        )
    } else {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(images) { imageUrl ->
                AsyncImage(
                    model = resolveImageUrl(imageUrl),
                    contentDescription = "Post Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onImageClick(imageUrl) } // 点击事件
                )
            }
        }
    }
}

// === 全屏图片查看器组件 ===
@Composable
fun FullScreenImageViewer(imageUrl: String, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // 缩放状态
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) // 全屏
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 3f) // 限制缩放倍数 1x - 3x
                        offset += pan
                    }
                }
        ) {
            // 图片
            AsyncImage(
                model = resolveImageUrl(imageUrl),
                contentDescription = "Full Screen Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            )

            // 顶部操作栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 关闭按钮
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }

                // 保存按钮
                IconButton(
                    onClick = {
                        scope.launch {
                            saveImageToGallery(context, imageUrl)
                        }
                    },
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(Icons.Default.Download, contentDescription = "Save", tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun InteractionButton(
    icon: ImageVector,
    text: String,
    color: Color,
    onClick: () -> Unit,
    animateIcon: Boolean = false
) {
    val scale by animateFloatAsState(
        targetValue = if (animateIcon && color == Color.Red) 1.2f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "iconScale"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .size(20.dp)
                .scale(scale)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text, color = color, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
