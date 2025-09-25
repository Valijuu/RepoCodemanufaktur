package myprojects.longboardtracks.sealed

import myprojects.longboardtracks.enums.NavigationRoutes

sealed class NavigationItem(val route: String) {
    data object SplashPage: NavigationItem(NavigationRoutes.SplashPage.name)
    data object StartPage: NavigationItem(NavigationRoutes.StartPage.name)
    data object TimerPage: NavigationItem(NavigationRoutes.TimerPage.name)
    data object MapPage: NavigationItem(NavigationRoutes.MapPage.name)
}