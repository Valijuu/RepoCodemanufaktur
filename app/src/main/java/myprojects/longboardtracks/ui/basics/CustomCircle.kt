package myprojects.longboardtracks.ui.basics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import myprojects.longboardtracks.ui.theme.ButtonColor

@Composable
fun CustomCircle() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val strokeWidth = 20f
        drawCircle(
            color = ButtonColor,
            radius = 300f,
            style = Stroke(width = strokeWidth)
        )
    }
}
