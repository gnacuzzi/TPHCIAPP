package com.example.peraapp

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "phone",
    group = "size",
    device = "spec:width=411dp,height=891dp"
)

@Preview(
    name = "tablet",
    group = "size",
    device = "spec:width=1280dp,height=800dp,dpi=240"
)

annotation class PreviewSizes()
