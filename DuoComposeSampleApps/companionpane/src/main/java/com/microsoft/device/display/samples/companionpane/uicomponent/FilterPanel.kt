/*
 *
 *  * Copyright (c) Microsoft Corporation. All rights reserved.
 *  * Licensed under the MIT License.
 *  *
 *
 */

package com.microsoft.device.display.samples.companionpane.uicomponent

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.device.display.samples.companionpane.ImagePanel

@Composable
fun FilterPanel(modifier: Modifier) {
    Column(modifier = modifier,
        horizontalGravity = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 5.dp)
    ) {
        FilterTopPanel()
        FilterBottomPanel(Modifier.fillMaxWidth())
    }
}

@Composable
fun FilterTopPanel() {
    Column(horizontalGravity = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(space = 5.dp)
    ) {
        Spacer(Modifier.preferredWidth(10.dp))
        Text(
            text = "Filter",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 12.sp
        )
        Divider(color = Color.White, thickness = 1.dp)
        Spacer(Modifier.preferredWidth(5.dp))
        LeftAlignText("Straightening")
        ImagePanel(Modifier.height(40.dp))
        Spacer(Modifier.preferredWidth(5.dp))
        SliderControl(title = "Filter intensity")
    }
}

@Composable
fun FilterBottomPanel(modifier: Modifier) {
    LeftAlignText("Choose a filter")
    ImageRow()
    ImageRow()
}

@Composable
fun LeftAlignText(title: String) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start) {
        Text(text = title, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun ImageRow() {
    Row(
        modifier = Modifier.height(120.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalGravity = Alignment.CenterVertically
    ) {
        SmallEvenImage(1/6f)
        SmallEvenImage(1/6f)
        SmallEvenImage(1/6f)
        SmallEvenImage(1/6f)
        SmallEvenImage(1/6f)
        SmallEvenImage(1/6f)
    }
}

@Composable
fun SmallEvenImage(weightPercent: Float) {
    ImagePanel(Modifier.height(15.dp).fillMaxWidth().weight(weightPercent))
}