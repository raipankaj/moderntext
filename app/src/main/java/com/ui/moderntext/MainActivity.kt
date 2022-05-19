package com.ui.moderntext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lib.text.ModernText
import com.lib.text.data.ModernTextItem
import com.ui.moderntext.ui.theme.ModerntextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModerntextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(Modifier.padding(16.dp)) {
                        LinkText()
                        Spacer(modifier = Modifier.padding(12.dp))
                        MultiColorText()
                        Spacer(modifier = Modifier.padding(12.dp))
                        HotKeyText()
                        Spacer(modifier = Modifier.padding(12.dp))
                        PersonTag()
                        Spacer(modifier = Modifier.padding(12.dp))
                        MultiPersonTag()
                    }
                }
            }
        }
    }
}

@Composable
fun LinkText() {
    val uriHandler = LocalUriHandler.current

    ModernText(text = {
        append("Please accept the")
        append(
            "terms and conditions",
            tag = "terms", url = "https://www.google.com",
            spanStyle = SpanStyle(Color.Red, fontWeight = FontWeight.ExtraBold)
        )
        append("& also the")
        append(
            "privacy policy", tag = "privacy", url = "https://www.google.com",
            spanStyle = SpanStyle(Color.Red, fontWeight = FontWeight.ExtraBold)
        )
    }, onClick = { tag, uri ->
        uriHandler.openUri(uri)
    })
}

@Composable
fun MultiColorText() {
    ModernText(text = {
        append("This simple string is shown through the")
        append("ModernText", spanStyle = SpanStyle(color = Color.Green))
        append("composable to showcase", spanStyle = SpanStyle(Color.Gray))
        append("multicolor text")
        append("in a single line", spanStyle = SpanStyle(Color.Blue))
    })
}

@Composable
fun HotKeyText() {
    ModernText(text = {
        append(
            "You can also add a hotkey like #ModernTextRocks in this case",
            hotKey = "#",
            spanStyle = SpanStyle(Color.Blue)
        )
        append(
            "just adding a hashtag or any other key based on what you provide",
        )
        append(
            "as the hotKey parameter #done",
            hotKey = "#",
            spanStyle = SpanStyle(Color.Blue)
        )
    })
}

@Composable
fun PersonTag() {
    val uriHandler = LocalUriHandler.current

    ModernText(text = {
        append("You can also tag a person with a symbol (@)")
        append(
            "like this, hey @username wassup?",
            tag = "username",
            hotKey = "@",
            spanStyle = SpanStyle(Color.Blue),
            url = "https://www.google.com"
        )
        append("Now above text have a link annotated with the username")
    }, onClick = { tag, uri ->
        uriHandler.openUri(uri)
    })
}

@Composable
fun MultiPersonTag() {
    val uriHandler = LocalUriHandler.current

    ModernText(text = {
        append("Now let's try a dummy person tag for two different username")
        append(
            "like this, hey @sam wassup?",
            tag = "sam",
            hotKey = "@",
            spanStyle = SpanStyle(Color.Red),
            url = "https://www.google.com"
        )
        append("\nNow above text have a link annotated with the profile of Sam")
        append(
            string = "and another for James how's everything going @james",
            tag = "james",
            hotKey = "@",
            url = "https://www.twitter.com",
            spanStyle = SpanStyle(Color.Red)
        )
    }, onClick = { tag, uri ->
        uriHandler.openUri(uri)
    })
}