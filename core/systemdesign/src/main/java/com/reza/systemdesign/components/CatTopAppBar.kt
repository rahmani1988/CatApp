package com.reza.systemdesign.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * A custom top app bar with Material Design 3 styling.
 *
 * @param title The text to be displayed as the title of the app bar.
 * @param modifier A [Modifier] object that allows you to customize the appearance and behavior of the app bar.
 * @param isBackIconVisible A boolean flag that determines whether a back icon should bedisplayed in the app bar.
 * @param onBackPressed A lambda function that is executed when the back icon is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    isBackIconVisible: Boolean = false,
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (isBackIconVisible) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer)
    )
}
