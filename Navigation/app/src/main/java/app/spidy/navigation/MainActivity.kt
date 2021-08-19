package app.spidy.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import app.spidy.navigation.ui.theme.NavigationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun App() {
    val navController = rememberAnimatedNavController()
    var size by remember { mutableStateOf(IntSize.Zero) }

    NavigationTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.onSizeChanged { size = it }
        ) {
            AnimatedNavHost(navController = navController,
                startDestination = HomeScreen.name) {

                composable(
                    route = HomeScreen.name,
                    exitTransition = {_, _ ->
                        slideOutHorizontally(
                            targetOffsetX = {-size.width},
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        )
                    },
                    popEnterTransition = {_, _ ->
                        slideInHorizontally(
                            initialOffsetX = {-size.width},
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        )
                    }
                ) {
                    HomeScreen(navController)
                }
                composable(
                    route = AboutScreen.name,
                    popExitTransition = {_, _ ->
                        slideOutHorizontally(
                            targetOffsetX = {size.width},
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        )
                    },
                    enterTransition = {_, _ ->
                        slideInHorizontally(
                            initialOffsetX = {size.width},
                            animationSpec = tween(300, easing = FastOutSlowInEasing)
                        )
                    }
                ) {
                    AboutScreen(navController)
                }

            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}