package app.spidy.navigationdrawer

sealed class DrawerScreens(val title: String, val route: String) {
    object Home: DrawerScreens("Home", "home")
    object Account: DrawerScreens("Account", "account")
    object Help: DrawerScreens("Help", "help")
}