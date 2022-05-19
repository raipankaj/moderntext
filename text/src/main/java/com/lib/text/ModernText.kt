package com.lib.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ModernText(
    text: ModernStringBuilder.() -> ModernStringBuilder,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: ((String, String) -> Unit)? = null
) {

    val stringBuilder = ModernStringBuilder().builder()
    stringBuilder.text()

    val annotatedString = rememberAnnotatedString(stringBuilder.get())

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            stringBuilder.get().forEach {
                if (it.url.isNotEmpty()) {
                    annotatedString.getStringAnnotations(it.tag, offset, offset).firstOrNull()
                        ?.let {
                            onClick?.invoke(it.tag, it.item)
                        }
                }
            }
        },
        modifier = modifier,
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}