package app.spidy.listapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier) {
        items(names) { name ->
            Text(
                text = name,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}