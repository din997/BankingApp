package com.dinhodzic.bankingapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dinhodzic.bankingapp.R

data class NavItem(
    val route: MainRoutes,
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)

val mainNavItems = listOf(
    NavItem(
        MainRoutes.Home,
        R.string.home,
        R.drawable.ic_home,
    ),
    NavItem(
        MainRoutes.Search,
        R.string.search,
        R.drawable.ic_search,
    ),
    NavItem(
        MainRoutes.Messages,
        R.string.message,
        R.drawable.ic_message,
    ),
    NavItem(
        MainRoutes.Settings,
        R.string.settings,
        R.drawable.ic_settings,
    )
)