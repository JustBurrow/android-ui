package kr.lul.android.ui.state

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InspectableValue

fun Modifier.hasElement(name: String): Boolean = foldIn(false) { found, element ->
    found || (element is InspectableValue && element.nameFallback == name)
}

fun Modifier.hasTestTag(): Boolean = hasElement("testTag")