package myprojects.longboardtracks.ui.basics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import myprojects.longboardtracks.R
import myprojects.longboardtracks.ui.theme.BottomBarColor
import myprojects.longboardtracks.ui.theme.ButtonTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericScaffold(
    iconSettingClickEvent: () -> Unit,
    iconHomeClickEvent: () -> Unit,
    titleText: String,
    iconTopBarEnabled: Boolean,
    bottomBarEnabled: Boolean,
    iconTopBarClickEvent: () -> Unit,
    content: @Composable () -> Unit,
) {

    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            if (iconTopBarEnabled) {
                IconButton(onClick = { iconTopBarClickEvent() }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        tint = ButtonTextColor,
                        contentDescription = "Go Back"
                    )
                }
            } else null
        },
            colors = TopAppBarColors(
                containerColor = BottomBarColor,
                scrolledContainerColor = BottomBarColor,
                navigationIconContentColor = Color.White,
                titleContentColor = ButtonTextColor,
                actionIconContentColor = ButtonTextColor
            ),
            title = {
                Text(
                    text = titleText
                )
            }
        )
    },
        bottomBar = {
            if (bottomBarEnabled) {
                BottomAppBar(
                    actions = {
                        IconButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(), onClick = { iconHomeClickEvent() }) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(Icons.Filled.Home, contentDescription = "Home Screen")
                                Text("Home")
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            onClick = { iconSettingClickEvent() }) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Filled.Settings,
                                    contentDescription = "Settings Screen",
                                )
                                Text("Settings")
                            }
                        }
                    },
                    containerColor = BottomBarColor,
                    contentColor = ButtonTextColor
                )
            }
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            content()
        }
    }
}