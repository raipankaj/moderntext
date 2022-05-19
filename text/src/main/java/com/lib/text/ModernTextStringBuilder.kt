package com.lib.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.lib.text.data.ModernTextItem

@Composable
fun rememberAnnotatedString(textItem: List<ModernTextItem>): AnnotatedString {

    val annotatedString = remember {
        buildAnnotatedString {
            textItem.forEachIndexed { index, modernTextItem ->
                val (text, spanStyle, tag, url, hotKey) = modernTextItem

                when {

                    hotKey.isNotEmpty() -> {

                        val splitSeq = text.split(" ")
                        for ((wordIndex, charSeq) in splitSeq.withIndex()) {
                            when {
                                charSeq.startsWith(hotKey) -> {
                                    if (url.isNotEmpty()) {
                                        addUriLinkToText(tag, url, spanStyle, charSeq)
                                    } else {
                                        addColorToText(spanStyle, charSeq)
                                    }
                                }

                                else -> {
                                    append(charSeq)
                                }
                            }

                            if (wordIndex != splitSeq.size - 1) {
                                append(" ")
                            }
                        }
                    }

                    url.isNotEmpty() -> {
                        addUriLinkToText(tag, url, spanStyle, text)
                    }

                    spanStyle.color.isSpecified -> {
                        addColorToText(spanStyle, text)
                    }


                    else -> {
                        append(text)
                    }
                }

                if (index != textItem.size - 1) {
                    append(" ")
                }
                toAnnotatedString()
            }
        }
    }

    return annotatedString
}

private fun AnnotatedString.Builder.addUriLinkToText(
    tag: String,
    url: String,
    spanStyle: SpanStyle,
    text: String
) {
    pushStringAnnotation(tag, annotation = url)
    withStyle(style = spanStyle) {
        append(text)
    }
    pop()
}

private fun AnnotatedString.Builder.addColorToText(
    spanStyle: SpanStyle,
    text: String
) {
    pushStyle(spanStyle)
    append(text)
    pop()
}

class ModernStringBuilder {
    private val modernTextList = ArrayList<ModernTextItem>()

    fun builder(): ModernStringBuilder {
        return this
    }

    fun append(string: String,
               spanStyle: SpanStyle = SpanStyle(),
               tag: String = "",
               url: String = "",
               hotKey: String = ""): ModernStringBuilder {

        val modernTextItem = ModernTextItem(string, spanStyle, tag, url, hotKey)
        modernTextList.add(modernTextItem)

        return this
    }

    fun get() = modernTextList
}