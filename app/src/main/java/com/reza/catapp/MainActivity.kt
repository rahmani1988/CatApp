package com.reza.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.reza.catapp.presentation.AppNavGraph
import com.reza.systemdesign.ui.theme.CatApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatApplicationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}

/**
 * Composable function that displays the main screen of the application.
 *
 * @param modifier Modifier used to adjust the layout of the screen.
 * @param navController The [NavHostController] used for navigation.
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    AppNavGraph(
        modifier = modifier,
        navController = navController
    )
}