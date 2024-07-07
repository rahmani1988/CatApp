package com.reza.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.reza.data.models.Cat
import com.reza.home.data.model.local.Home
import com.reza.home.presentation.HomeScreen

/**
 * Adds the Home screen to the NavGraph.
 *
 * @param onItemClicked Callback invoked when a cat item is clicked.
 */
fun NavGraphBuilder.home(onItemClicked: (Cat) -> Unit) {
    composable<Home> {
        HomeScreen(onItemClicked = onItemClicked)
    }
}