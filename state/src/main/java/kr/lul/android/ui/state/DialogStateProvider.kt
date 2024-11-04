package kr.lul.android.ui.state

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DialogStateProvider : PreviewParameterProvider<DialogState<State>> {
    override val values: Sequence<DialogState<State>> = sequenceOf(
        object : DialogState<State> {
            override val show = true
            override val properties = DialogProperties()
            override val content = TextState(text = "Dialog content", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            override val key = Uuid.random()
            override val testTag = key.toString()
            override fun toString() = "DialogState.SHOW"
        },
        object : DialogState<State> {
            override val show = false
            override val properties = DialogProperties()
            override val content = TextState(text = "Dialog content", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            override val key = Uuid.random()
            override val testTag = key.toString()
            override fun toString() = "DialogState.HIDE"
        }
    )
}