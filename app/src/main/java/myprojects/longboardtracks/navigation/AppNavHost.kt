package myprojects.longboardtracks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import myprojects.longboardtracks.pages.mapepage.MapPage
import myprojects.longboardtracks.pages.splashpage.SplashPage
import myprojects.longboardtracks.pages.homepage.HomePage
import myprojects.longboardtracks.pages.timerpage.TimerPage
import myprojects.longboardtracks.sealed.NavigationItem

//Setup static navController
val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavController not provided")
}

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.StartPage.route
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.SplashPage.route) {
                SplashPage()
            }
            composable(NavigationItem.StartPage.route) {
                HomePage()
            }
            composable(NavigationItem.TimerPage.route){
                TimerPage()
            }
            composable(NavigationItem.MapPage.route) {
                MapPage()
            }
        }
    }
}