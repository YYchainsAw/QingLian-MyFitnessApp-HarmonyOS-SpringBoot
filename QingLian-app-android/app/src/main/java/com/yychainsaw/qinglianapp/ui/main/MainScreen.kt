// file: app/src/main/java/com/yychainsaw/qinglianapp/ui/main/MainScreen.kt
package com.yychainsaw.qinglianapp.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.ui.MainViewModel
import com.yychainsaw.qinglianapp.ui.community.CommunityScreen
import com.yychainsaw.qinglianapp.ui.fitness.FitnessScreen
import com.yychainsaw.qinglianapp.ui.message.MessageListScreen
import com.yychainsaw.qinglianapp.ui.profile.ProfileScreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianGreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    // 注入 ViewModel
    mainViewModel: MainViewModel = viewModel()
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    // 使用 ViewModel 中的状态，不再轮询
    val unreadCount by mainViewModel.totalUnreadCount.collectAsState()

    BackHandler(enabled = selectedItemIndex != 0) {
        selectedItemIndex = 0
    }

    val items = listOf(
        BottomNavItem("社区", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("健身", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter),
        BottomNavItem("消息", Icons.Filled.ChatBubble, Icons.Outlined.ChatBubbleOutline, hasBadge = true),
        BottomNavItem("我的", Icons.Filled.Person, Icons.Outlined.Person)
    )

    Scaffold(
        bottomBar = {
            // 使用 Surface 添加阴影，增加层次感
            Surface(
                shadowElevation = 16.dp,
                color = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp // 由 Surface 接管阴影
                ) {
                    items.forEachIndexed { index, item ->
                        val isSelected = selectedItemIndex == index

                        // 图标弹性缩放动画
                        val scale by animateFloatAsState(
                            targetValue = if (isSelected) 1.2f else 1.0f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            ),
                            label = "iconScale"
                        )

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { selectedItemIndex = index },
                            label = {
                                Text(
                                    text = item.label,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 11.sp
                                )
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.hasBadge && unreadCount > 0) {
                                            Badge(
                                                containerColor = Color.Red,
                                                contentColor = Color.White
                                            ) {
                                                Text(
                                                    text = if (unreadCount > 99) "99+" else unreadCount.toString(),
                                                    fontSize = 10.sp
                                                )
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label,
                                        modifier = Modifier.scale(scale) // 应用缩放动画
                                    )
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = QingLianBlue,
                                selectedTextColor = QingLianBlue,
                                indicatorColor = QingLianYellow.copy(alpha = 0.4f), // 更柔和的指示器颜色
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            ),
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // 使用 Crossfade 实现平滑的页面切换动画
        Crossfade(
            targetState = selectedItemIndex,
            label = "MainScreenTransition",
            animationSpec = tween(durationMillis = 300),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { targetIndex ->
            when (targetIndex) {
                0 -> CommunityScreen(
                    onPostCreate = { navController.navigate("post_create") }
                )
                1 -> FitnessScreen()
                // 将 ViewModel 传递给 MessageListScreen，以便它能触发刷新
                2 -> MessageListScreen(navController, mainViewModel)
                3 -> ProfileScreen(navController)
            }
        }
    }
}
