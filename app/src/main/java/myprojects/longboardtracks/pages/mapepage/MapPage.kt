package myprojects.longboardtracks.pages.mapepage

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import myprojects.longboardtracks.navigation.LocalNavController
import myprojects.longboardtracks.pages.basepage.BasePage
import myprojects.longboardtracks.sealed.NavigationItem
import myprojects.longboardtracks.ui.basics.GenericDialog


@Composable
fun MapPage(mapViewModel: MapViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val location by mapViewModel.location.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var permissionRequested by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            showDialog = true
        } else {
            mapViewModel.onPermissionResult()
        }
    }

    BackHandler {
        navController.navigate(NavigationItem.StartPage.route) {
            launchSingleTop = true
        }
    }

    //Launch Dialog for the first time creating Composable and Request Permission for Location
    LaunchedEffect(Unit) {
        if (!permissionRequested) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            permissionRequested = true
        }
    }

    BasePage(
        titleText = "Longboard Ride",
        iconTopBarEnabled = true,
        iconTopBarClickEvent = {
            navController.navigate(NavigationItem.StartPage.route) {
                launchSingleTop = true
            }
        },
        bottomBarEnabled = false
    )
    {
        MovingMarkerMap(location)

        if (showDialog) {
            GenericDialog(
                titleText = "Hinweis",
                bodyText = "Die Berechtigungen können nachträglich in den Einstellungen aktiviert werden",
                showDialog = true,
                onOkButtonClick = {
                    showDialog = false
                    permissionRequested = false
                },
                onSettingsClick = {
                    //Go to App Settings
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}