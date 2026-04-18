package com.example.hzbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hzbank.ui.theme.HZBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HZBankTheme {
                BankApp()
            }
        }
    }
}

@Composable
fun BankApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val selectedBlue = Color(0xFF3AA7F2)
    val unselectedGray = Color(0xFF9E9E9E)

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.navigationBarsPadding()
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "首页") },
                    label = { Text("首页") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedBlue,
                        selectedTextColor = selectedBlue,
                        unselectedIconColor = unselectedGray,
                        unselectedTextColor = unselectedGray,
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.CreditCard, contentDescription = "信用卡") },
                    label = { Text("信用卡") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedBlue,
                        selectedTextColor = selectedBlue,
                        unselectedIconColor = unselectedGray,
                        unselectedTextColor = unselectedGray,
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.AccountBalance, contentDescription = "财富") },
                    label = { Text("财富") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedBlue,
                        selectedTextColor = selectedBlue,
                        unselectedIconColor = unselectedGray,
                        unselectedTextColor = unselectedGray,
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.RoomService, contentDescription = "生活") },
                    label = { Text("生活") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedBlue,
                        selectedTextColor = selectedBlue,
                        unselectedIconColor = unselectedGray,
                        unselectedTextColor = unselectedGray,
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 4,
                    onClick = { selectedTab = 4 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "我的") },
                    label = { Text("我的") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedBlue,
                        selectedTextColor = selectedBlue,
                        unselectedIconColor = unselectedGray,
                        unselectedTextColor = unselectedGray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F8FC))
        ) {
            when (selectedTab) {
                0 -> HomePage()
                4 -> MyPage()
                1 -> PlaceholderPage("信用卡页面开发中")
                2 -> PlaceholderPage("财富页面开发中")
                3 -> PlaceholderPage("生活页面开发中")
            }
        }
    }
}

@Composable
fun PlaceholderPage(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F8FC)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.Gray,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun HomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6FC1F5))
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = "首页背景",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun SimpleMenu(title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(
                    color = Color(0xFFD8F0FF),
                    shape = RoundedCornerShape(14.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(title)
    }
}

@Composable
fun MyPage() {
    var showAmount by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F8FC))
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "安全退出",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD6EEFF))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "*雨",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("上次登录：2026-04-06 10:03:38")
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoItem("2", "银行卡")
                    InfoItem("50000", "积分")
                    InfoItem("0", "铜板")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "我的资产",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = if (showAmount) "显示" else "隐藏",
                        color = Color(0xFF2D9CDB),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            showAmount = !showAmount
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "账户资产",
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text("总资产(元)", color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (showAmount) "****" else "5,639,789.34",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("本月收入(元)", color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (showAmount) "****" else "102,017.39",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("本月支出(元)", color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (showAmount) "****" else "548.00",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun InfoItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(label)
    }
}