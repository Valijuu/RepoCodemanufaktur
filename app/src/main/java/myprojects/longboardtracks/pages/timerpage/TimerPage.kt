package myprojects.longboardtracks.pages.timerpage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import myprojects.longboardtracks.navigation.LocalNavController
import myprojects.longboardtracks.sealed.NavigationItem
import myprojects.longboardtracks.ui.theme.ButtonColor
import myprojects.longboardtracks.ui.theme.ButtonTextColor
import myprojects.longboardtracks.ui.basics.CustomCircle

@Composable
fun TimerPage(timerViewModel: TimerViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val timerValue by timerViewModel.timerValue.collectAsState()

    LaunchedEffect(Unit) {
        timerViewModel.startTimer()
    }

    LaunchedEffect(timerValue) {
        if (timerValue == 0) {
            timerViewModel.stopTimer()
            navController.navigate(NavigationItem.MapPage.route) {
                launchSingleTop = true
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            CustomCircle()
            AnimatedContent(
                targetState = timerValue,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) { value ->
                Text(value.toString())
            }
        }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    timerViewModel.stopTimer()
                    navController.navigate(NavigationItem.MapPage.route) {
                        launchSingleTop = true
                    }
                },
                colors = ButtonColors(
                    containerColor = ButtonColor,
                    contentColor = ButtonTextColor,
                    disabledContainerColor = ButtonColor,
                    disabledContentColor = ButtonTextColor
                )
            ) {
                Text("Jetzt Starten")
            }
    }
}

