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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import app.spidy.listapp.HomeScreen
import app.spidy.listapp.ui.theme.ListAppTheme
import app.spidy.listapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(viewModel)
        }
    }
}

@Composable
fun App(viewModel: MainViewModel) {
    val names: List<String> by viewModel.names.observeAsState(initial = listOf())

    ListAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                HomeScreen(names, modifier = Modifier.weight(1f))
                Button(onClick = { viewModel.loadMore() }) {
                    Text(text = "Load More")
                }
            }
        }
    }
}