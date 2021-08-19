package app.spidy.navigationdrawer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.spidy.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

var appTitle: String = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AppScreen()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    NavigationDrawerTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var lock = false
    val context = LocalContext.current

    val openDrawer = {
        lock = false
        scope.launch { drawerState.open() }
        Toast.makeText(context, "Title: $appTitle", Toast.LENGTH_SHORT).show()
    }

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                onItemClicked = { screen ->
                    if (drawerState.isOpen) {
                        scope.launch { drawerState.close() }
                    }
                    if (!lock && screen.title != appTitle) {
                        navController.navigate(screen.route)
                        lock = true
                    }
                }
            )
        }
    ) {
        NavHost(navController = navController,
            startDestination = DrawerScreens.Home.route) {

            composable(DrawerScreens.Home.route) {
                Home { openDrawer() }
            }
            composable(DrawerScreens.Account.route) {
                Account { openDrawer() }
            }
            composable(DrawerScreens.Help.route) {
                Help(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        AppScreen()
    }
}


private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)


@Composable
fun Drawer(modifier: Modifier = Modifier, onItemClicked: (screen: DrawerScreens) -> Unit) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 48.dp)) {
        Image(painter = painterResource(android.R.drawable.stat_sys_warning),
            contentDescription = "App icon")
        screens.forEach { screen ->
            Spacer(Modifier.height(14.dp))
            Text(text = screen.title, style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClicked(screen)
                    }
                    .padding(start = 24.dp, end = 24.dp, top = 10.dp, bottom = 10.dp)
            )
        }
    }
}

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    appTitle = title
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onButtonClicked) {
                Icon(imageVector = buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}





/* Screens */

@Composable
fun Home(openDrawer: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBar(buttonIcon = Icons.Filled.Menu, title = "Home",
            onButtonClicked = openDrawer)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home page content is here.", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun Account(openDrawer: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBar(buttonIcon = Icons.Filled.Menu, title = "Account",
            onButtonClicked = openDrawer)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Account page content is here.", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun Help(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBar(buttonIcon = Icons.Filled.ArrowBack, title = "Help",
            onButtonClicked = {
                navController.popBackStack()
            })
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Help page content is here.", style = MaterialTheme.typography.h4)
        }
    }
}
