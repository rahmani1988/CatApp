package com.reza.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.reza.detail.data.model.Detail
import com.reza.detail.presentation.DetailScreen

/**
 * Adds the Detail screen to the NavGraph.
 *
 * @param onBackPressed Callback invoked when the back button is pressed on the Detail screen.
 */
fun NavGraphBuilder.detail(onBackPressed: () -> Unit) {
    composable<Detail> { backStackEntry ->
        val detail: Detail = backStackEntry.toRoute()
        DetailScreen(detail = detail, onBackPressed = onBackPressed)
    }
}