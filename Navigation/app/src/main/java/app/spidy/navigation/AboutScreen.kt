package app.spidy.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


object AboutScreen {
    val name = "about"
}


@Composable
fun AboutScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "About Screen")
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Goback")
            }
        }
    }
}