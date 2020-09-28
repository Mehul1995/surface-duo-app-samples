package com.microsoft.device.display.samples.extendedcanvas

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberZoomableController
import androidx.compose.foundation.gestures.zoomable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offsetPx
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.microsoft.device.display.samples.extendedcanvas.viewmodel.AppStateViewModel

@Composable
fun MainPage(viewModel: AppStateViewModel) {
    val isScreenPortraitLiveData = viewModel.getIsScreenPortraitLiveData()
    val isScreenPortrait = isScreenPortraitLiveData.observeAsState(initial = true).value
    ScaleImage(isScreenPortrait)
}

@Composable
fun ScaleImage(isPortrait: Boolean) {
    val max = 392.dp
    val min = -392.dp
    val offsetXPosition = remember { mutableStateOf(0f) }
    val offsetYPosition = remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }
    val zoomableController = rememberZoomableController { scale *= it }
    val scaleValue: ContentScale = if (isPortrait) ContentScale.FillHeight else ContentScale.FillWidth

    Image(asset = imageResource(R.drawable.new_york),
          contentScale = scaleValue,
          alignment = Alignment.Center,
          modifier = Modifier.fillMaxSize()
              .offsetPx(x = offsetXPosition, y = offsetYPosition)
              .drawLayer(scaleX = scale, scaleY = scale)
              .draggable(orientation = Orientation.Horizontal) { delta ->
                  val newValue = offsetXPosition.value + delta
                  offsetXPosition.value = newValue.coerceIn(min.toPx(), max.toPx())
              }
              .draggable(orientation = Orientation.Vertical) { delta ->
                  val newValue = offsetYPosition.value + delta
                  offsetYPosition.value = newValue.coerceIn(min.toPx(), max.toPx())
              }
              .zoomable(zoomableController)
    )
}