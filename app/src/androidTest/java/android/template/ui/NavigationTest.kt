package android.template.ui

import android.template.di.AppModule
import android.template.ui.mymodel.MyModelScreen
import android.template.ui.theme.MyApplicationTheme
import android.template.util.Screen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MyModelScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            MyApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.MyModelScreen.route
                ) {
                    composable(route = Screen.MyModelScreen.route) {
                        MyModelScreen()
                    }
                }
            }
        }
    }
}