package myprojects.longboardtracks.pages.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import myprojects.longboardtracks.R
import myprojects.longboardtracks.navigation.LocalNavController
import myprojects.longboardtracks.pages.basepage.BasePage
import myprojects.longboardtracks.sealed.NavigationItem
import myprojects.longboardtracks.ui.theme.ButtonColor
import myprojects.longboardtracks.ui.theme.ButtonTextColor

@Composable
fun HomePage(homeViewModel: HomeViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    BasePage(
        titleText = "Longboard Tracks"
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.longboard_dude),
                    contentDescription = "Start page vector",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(5.dp)
                )

                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .width(300.dp),
                    onClick = { navController.navigate(NavigationItem.TimerPage.route){
                        launchSingleTop = true
                    } },
                    colors = ButtonColors(
                        containerColor = ButtonColor,
                        contentColor = ButtonTextColor,
                        disabledContainerColor = ButtonColor,
                        disabledContentColor = ButtonTextColor
                    )
                )
                { Text("Start tracking") }
            }
        }
    }
}