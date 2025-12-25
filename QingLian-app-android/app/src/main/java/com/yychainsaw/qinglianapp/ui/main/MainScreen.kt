package com.yychainsaw.qinglianapp.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.yychainsaw.qinglianapp.ui.community.CommunityScreen
import com.yychainsaw.qinglianapp.ui.fitness.FitnessScreen
import com.yychainsaw.qinglianapp.ui.profile.ProfileScreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianBlue
import com.yychainsaw.qinglianapp.ui.theme.QingLianGreen
import com.yychainsaw.qinglianapp.ui.theme.QingLianYellow

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun MainScreen(navController: NavController) {
    // 修改 1: 使用 rememberSaveable 替代 remember
    // 作用：当从子页面（如设置、好友）popBackStack 回来时，保持当前选中的 Tab 索引，而不是重置为 0
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    // 修改 2: 添加 BackHandler 处理物理返回键
    // 逻辑：如果当前不在 Tab 0 (社区)，按下返回键时切换到 Tab 0；
    // 如果已经在 Tab 0，则不拦截（执行系统默认的退出或后台操作）
    BackHandler(enabled = selectedItemIndex != 0) {
        selectedItemIndex = 0
    }

    val items = listOf(
        BottomNavItem("社区", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("健身", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter),
        BottomNavItem("我的", Icons.Filled.Person, Icons.Outlined.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = QingLianYellow.copy(alpha = 0.1f)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        label = { Text(item.label) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = QingLianBlue,
                            selectedTextColor = QingLianBlue,
                            indicatorColor = QingLianGreen.copy(alpha = 0.3f),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedItemIndex) {
                0 -> CommunityScreen(
                    onPostCreate = { navController.navigate("post_create") }
                )
                1 -> FitnessScreen()
                2 -> ProfileScreen(navController)
            }
        }
    }
}
