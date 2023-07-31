package com.example.lytko.widget


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.action.actionStartService
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.*
import com.example.lytko.R
import kotlinx.coroutines.MainScope

/**
 * Sample AppWidget that showcase the [ContentScale] options for [Image]
 */
class ImageAppWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Exact

    companion object {
        internal val ImageTypeKey = stringPreferencesKey("imageType")
    }

    @Composable
    override fun Content() {
        val type = currentState(ImageTypeKey) ?: "Fit"
        Column(modifier = GlanceModifier.fillMaxSize().padding(8.dp)) {
            Button(
                text = "Content Scale: $type",
                modifier = GlanceModifier.fillMaxWidth(),
                onClick = actionRunCallback<ChangeImageAction>()
            )

            Button(
                text = "Service",
                modifier = GlanceModifier.fillMaxWidth(),
                onClick = actionStartService<MyService>(isForegroundService = true)
            )

            Spacer(GlanceModifier.size(4.dp))
            Image(
                provider = ImageProvider(R.drawable.menu),//(R.drawable.compose),
                contentDescription = "Content Scale image sample (value: $type)",
                contentScale = type.toContentScale(),
                modifier = GlanceModifier.fillMaxSize().background(Color.DarkGray)
            )
        }
    }

    private fun String.toContentScale() = when (this) {
        "Fit" -> ContentScale.Fit
        "Fill Bounds" -> ContentScale.FillBounds
        "Crop" -> ContentScale.Crop
        else -> throw IllegalArgumentException()
    }
}

class ChangeImageAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { state ->
            val value = when (state[ImageAppWidget.ImageTypeKey]) {
                "Crop" -> "Fill Bounds"
                "Fill Bounds" -> "Fit"
                else -> "Crop"
            }
            state[ImageAppWidget.ImageTypeKey] = value
        }
        ImageAppWidget().update(context, glanceId)
    }
}

class ImageAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ImageAppWidget()

    private val coroutineScope = MainScope()

}
































