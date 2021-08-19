package app.spidy.listapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.spidy.listapp.screens.HomeScreen
import app.spidy.listapp.ui.theme.ListAppTheme
import app.spidy.listapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    ListAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                HomeScreen(mainViewModel, modifier = Modifier.weight(1f), loadMore = {
                    mainViewModel.loadMore()
                })
            }
        }
    }
}