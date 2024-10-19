package kr.lul.android.ui.sample.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.sample.ui.navigator.FirstNavigator
import kr.lul.android.ui.sample.viewmodel.FirstViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.state.ProgressState
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.viewmodel.compose.baseViewModel

@Composable
fun FirstPage(
    navigator: FirstNavigator,
    viewModel: FirstViewModel = baseViewModel()
) {
    Log.v(TAG, "#FirstPage args : navigator=$navigator, viewModel=$viewModel")

    val progress by viewModel.progress.state.collectAsStateWithLifecycle()

    FirstPageContent(
        navigator = navigator,
        progress = if (progress.isEmpty()) {
            null
        } else {
            progress.random()
        },
        onClickBlocking = viewModel::onClickBlocking,
        onClickNonBlocking = viewModel::onClickNonBlocking
    )
}

@Composable
private fun FirstPageContent(
    navigator: FirstNavigator,
    progress: ProgressState?,
    onClickBlocking: () -> Unit = {},
    onClickNonBlocking: () -> Unit = {}
) {
    Log.v(
        TAG,
        listOf(
            "navigator=$navigator",
            "progress=$progress",
            "onClickBlocking=$onClickBlocking",
            "onClickNonBlocking=$onClickNonBlocking"
        ).joinToString(", ", "#FirstPageContent args : ")
    )
    if (BlockingProgressState == progress) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (NonBlockingProgressState == progress) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        Spacer(Modifier.weight(1F))
        Text(
            TextState(text = "1st Page", style = MaterialTheme.typography.displayLarge),
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = navigator::settings, modifier = Modifier.padding(16.dp)) {
            Text(TextState("앱 정보"))
        }
        Button(onClick = { navigator.web("https://blog.lul.kr") }, modifier = Modifier.padding(16.dp)) {
            Text(TextState("https://blog.lul.kr"))
        }
        Button(onClick = { navigator.call("012-3456-7890") }, modifier = Modifier.padding(16.dp)) {
            Text(TextState("012-3456-7890"))
        }
        Button(onClick = navigator::second, modifier = Modifier.padding(16.dp)) {
            Text(TextState("2nd Page"))
        }

        Row(Modifier.padding(16.dp)) {
            Button(onClick = onClickBlocking, modifier = Modifier.weight(1F)) {
                Text(TextState("Blocking"))
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = onClickNonBlocking, modifier = Modifier.weight(1F)) {
                Text(TextState("Non-Blocking"))
            }
        }
        Spacer(Modifier.weight(1F))
    }
}

private data class FirstPageContentState(
    val progress: ProgressState?
)

private class FirstPageContentStateProvider : PreviewParameterProvider<FirstPageContentState> {
    override val values = sequenceOf(
        FirstPageContentState(null),
        FirstPageContentState(BlockingProgressState),
        FirstPageContentState(NonBlockingProgressState),
    )
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstPageContent(@PreviewParameter(FirstPageContentStateProvider::class) state: FirstPageContentState) {
    MaterialTheme {
        FirstPageContent(FirstNavigator(rememberBaseNavigator()), state.progress)
    }
}