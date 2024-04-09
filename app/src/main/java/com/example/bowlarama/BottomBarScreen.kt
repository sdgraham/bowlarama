package com.example.bowlarama

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Teams : BottomBarScreen(
        route = "teams",
        title = "Teams",
        icon = Icons.Default.Person
    )

    object Book : BottomBarScreen(
        route = "book",
        title = "Book",
        icon = Icons.Default.DateRange
    )

    object Contact : BottomBarScreen(
        route = "contact",
        title = "Contact",
        icon = Icons.Default.Phone
    )


}
