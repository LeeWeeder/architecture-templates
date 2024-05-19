package android.template.util

sealed class Screen(val route: String) {
    data object MyModelScreen: Screen("my_model_screen")
}