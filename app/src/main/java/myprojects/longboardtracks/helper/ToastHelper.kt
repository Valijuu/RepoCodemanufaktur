package myprojects.longboardtracks.helper

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastHelper @Inject constructor(
    @ApplicationContext private val toastContext: Context
) {
    fun showMessage(message: String) {
        Toast.makeText(toastContext, message, Toast.LENGTH_LONG).show()
    }
}
