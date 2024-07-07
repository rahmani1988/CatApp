package com.reza.catapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.reza.detail.data.model.Detail
import com.reza.detail.navigation.detail
import com.reza.home.data.model.local.Home
import com.reza.home.presentation.navigation.home

/**
 * Defines the navigation graph for the application.
 *
 * @param modifier The modifier to apply to the NavHost.
 * @param navController The [NavHostController] used for navigation.
 */
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        startDestination = Home,
        navController = navController
    ) {
        home { cat ->
            navController.navigate(
                Detail(
                    image = cat.image.url,
                    name = cat.name,
                    description = cat.description
                )
            )
        }

        detail {
            navController.navigateUp()
        }
    }
}

