package com.practice.weatherapp.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SearchAppBar(
    title: String = "Search",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController,
    onSearchClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.outline,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.onSecondary),
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon")
                }
            } else Box { }
        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onButtonClicked() }) {
                    Icon(imageVector = icon, contentDescription = "Back Icon")
                }
            }
        })

}