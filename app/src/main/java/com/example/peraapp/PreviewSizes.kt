package com.example.peraapp

import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showSystemUi = true,
    name = "phone",
    group = "size",
    device = "spec:width=411dp,height=891dp"
)

@Preview(
    showSystemUi = true,
    name = "phonerotate",
    group = "size",
    device = "spec:width=891dp,height=411dp"
)

@Preview(
    showSystemUi = true,
    name = "tablet",
    group = "size",
    device = "spec:width=1280dp,height=800dp,dpi=240"
)

@Preview(
    showSystemUi = true,
    name = "tabletrotate",
    group = "size",
    device = "spec:width=800dp,height=1280dp,dpi=240"
)

annotation class PreviewSizes()
