package kr.lul.ui.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.lul.navigation.compose.rememberBaseNavigator
import kr.lul.ui.ui.navigator.FirstNavigator

@Composable
fun FirstPage(
    navigator: FirstNavigator
) {
    FirstPageContent(navigator)
}

@Composable
private fun FirstPageContent(
    navigator: FirstNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "1st Page", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.displayLarge)

        Button(onClick = navigator::settings, modifier = Modifier.padding(16.dp)) {
            Text(text = "앱 정보")
        }
        Button(onClick = { navigator.web("https://blog.lul.kr") }, modifier = Modifier.padding(16.dp)) {
            Text(text = "https://blog.lul.kr")
        }
        Button(onClick = { navigator.call("012-3456-7890") }, modifier = Modifier.padding(16.dp)) {
            Text(text = "012-3456-7890")
        }
        Button(onClick = navigator::second, modifier = Modifier.padding(16.dp)) {
            Text(text = "2nd Page")
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewFirstPageContent() {
    MaterialTheme {
        FirstPageContent(FirstNavigator(rememberBaseNavigator()))
    }
}