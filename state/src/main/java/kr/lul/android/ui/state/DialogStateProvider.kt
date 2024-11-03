package kr.lul.android.ui.state

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

class DialogStateProvider : PreviewParameterProvider<DialogState<State>> {
    override val values: Sequence<DialogState<State>> = sequenceOf(
        object : DialogState<State> {
            override val show = true
            override val properties = DialogProperties()
            override val content = TextState(text = "Dialog content", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        },
        object : DialogState<State> {
            override val show = false
            override val properties = DialogProperties()
            override val content = TextState(text = "Dialog content", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    )
}