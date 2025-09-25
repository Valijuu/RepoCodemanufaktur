package myprojects.longboardtracks.pages.basepage


import androidx.compose.runtime.Composable
import myprojects.longboardtracks.navigation.LocalNavController
import myprojects.longboardtracks.sealed.NavigationItem
import myprojects.longboardtracks.ui.basics.GenericScaffold

@Composable
fun BasePage(
    titleText: String,
    iconTopBarEnabled: Boolean = false,
    bottomBarEnabled: Boolean = true,
    iconTopBarClickEvent: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val navController = LocalNavController.current
    GenericScaffold(
        iconHomeClickEvent = {
            if (navController.currentDestination?.route != NavigationItem.StartPage.route){
                navController.navigate(NavigationItem.StartPage.route) {
                    launchSingleTop = true
                }
            }
        },
        iconSettingClickEvent = { },//TODO: implement Settings Screen Navigation
        titleText = titleText,
        iconTopBarEnabled = iconTopBarEnabled,
        iconTopBarClickEvent = iconTopBarClickEvent,
        bottomBarEnabled = bottomBarEnabled,
        content = content
    )
}

