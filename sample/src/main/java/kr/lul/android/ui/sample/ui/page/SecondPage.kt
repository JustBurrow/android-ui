package kr.lul.android.ui.sample.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.sample.ui.navigator.SecondNavigator
import kr.lul.android.ui.sample.viewmodel.SecondViewModel
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.viewmodel.compose.baseViewModel

@Composable
fun SecondPage(
    navigator: SecondNavigator,
    viewModel: SecondViewModel = baseViewModel()
) {
    Log.v(TAG, "#SecondPage args : navigator=$navigator, viewModel=$viewModel")

    SecondPageContent(
        navigator = navigator,
        onClickBlocking = viewModel::onClickBlocking,
        onClickNonBlocking = viewModel::onClickNonBlocking
    )
}

@Composable
private fun SecondPageContent(
    navigator: SecondNavigator,
    onClickBlocking: () -> Unit = {},
    onClickNonBlocking: () -> Unit = {}
) {
    Log.v(
        TAG,
        listOf(
            "navigator=$navigator",
            "onClickBlocking=$onClickBlocking",
            "onClickNonBlocking=$onClickNonBlocking"
        ).joinToString(", ", "#SecondPageContent args : ")
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1F))
        Text(
            TextState(text = "2nd Page", style = MaterialTheme.typography.displayLarge),
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = navigator::back, modifier = Modifier.padding(16.dp)) {
            Text(TextState("돌아가기"))
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

private class SecondPageContentState

private class SecondPageContentStateProvider : PreviewParameterProvider<SecondPageContentState> {
    override val values = sequenceOf(
        SecondPageContentState()
    )
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewSecondPageContent(@PreviewParameter(SecondPageContentStateProvider::class) state: SecondPageContentState) {
    MaterialTheme {
        SecondPageContent(SecondNavigator(rememberBaseNavigator()))
    }
}