package com.lib.text.data

import androidx.annotation.Keep
import androidx.compose.ui.text.SpanStyle

/**
 * Create individual line based on the modern text item, where based on
 * the provided parameter the following properties can be changed
 * 1. Color
 * 2. Color for individual word based on hot key
 * 3. Making text clickable
 */
@Keep
data class ModernTextItem(
    val string: String,
    val spanStyle: SpanStyle = SpanStyle(),
    val tag: String = "",
    val url: String = "",
    val hotKey: String = ""
)