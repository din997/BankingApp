package com.dinhodzic.bankingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import coil.compose.AsyncImage
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import com.dinhodzic.bankingapp.ui.navigation.MainNavGraph
import com.dinhodzic.bankingapp.ui.common.components.BackIconButton
import com.dinhodzic.bankingapp.ui.navigation.MainRoutes
import com.dinhodzic.bankingapp.ui.navigation.mainNavItems
import com.dinhodzic.bankingapp.ui.theme.Primary900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, profileInfo: BankingProfile) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val mainRoutes = mainNavItems.map { it.route.route }
    val isMainRoute = currentDestination?.route in mainRoutes
    Scaffold(
        containerColor = if (isMainRoute) Primary900 else Color.White,
        topBar = {
            if (isMainRoute) {
                MainTopBar(profileInfo)
            } else {
                StandardTopAppBar(title = "Payment history",
                    navigation = {
                        BackIconButton(onClick = { navController.navigateUp() })
                    })
            }
        },
        bottomBar = { if (isMainRoute) BankingBottomBar(navController = navController) },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    color = Color(0xFFFBFBFC),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
        ) {
            MainNavGraph(navController = navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    profileInfo: BankingProfile,
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = profileInfo.avatar,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    "Hi, ${profileInfo.name}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        actions = {
            NotificationBell(count = 3)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTopAppBar(
    title: String,
    textColor: Color = Color.Black,
    navigation: @Composable (() -> Unit),
    actions: @Composable () -> Unit = {}
) {
    val topPadding = 12.dp
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = textColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = topPadding)
            )
        },
        navigationIcon = navigation,
        actions = {
            Box(modifier = Modifier.padding(top = topPadding)) {
                actions()
            }
        },
        modifier = Modifier.systemBarsPadding()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationBell(count: Int) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge(
                    containerColor = Color(0xFFE57373),
                    contentColor = Color.White
                ) {
                    Text("$count")
                }
            }
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}


@Composable
fun BankingBottomBar(
    navController: NavHostController,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        mainNavItems.forEach { item ->
            val selected = item.route.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navigateToMainScreen(item.route, navController) },

                icon = {
                    if (selected) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painterResource(item.icon), null, tint = Color.White)
                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = stringResource(item.name),
                                color = Color.White,
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Visible
                            )
                        }
                    } else {
                        Icon(painter = painterResource(item.icon), null)
                    }
                },

                alwaysShowLabel = false,
                label = { },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color(0xFF4F46E5),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                )
            )
        }
    }
}

fun navigateToMainScreen(mainDestinations: MainRoutes, navController: NavHostController) {
    val mainScreenNavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    navController.navigate(mainDestinations.route, mainScreenNavOptions)
}