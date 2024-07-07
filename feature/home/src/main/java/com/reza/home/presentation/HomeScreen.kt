package com.reza.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.reza.data.models.Cat
import com.reza.home.R
import com.reza.home.presentation.component.ParentContent
import com.reza.systemdesign.components.CatTopAppBar

/**
 * Composable function for the Home screen.
 *
 * @param modifier Modifier used to adjust the layout of the screen.
 * @param viewModel The [HomeViewModel] for this screen.
 * @param onItemClicked Callback invoked when a cat item is clicked.
 */
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClicked: (Cat) -> Unit,
) {
    /**
     * Remembers the scroll state of the LazyColumn.
     */
    val lazyColumnListState = rememberLazyListState()

    /**
     * Remembers a CoroutineScope for this composable.
     */
    val coroutineScope = rememberCoroutineScope()

    /**
     * Remembers the SnackbarHostState for this composable.
     */
    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * A derived state that indicates whether pagination should start.
     *
     * Pagination should start when more items are available ([viewModel.canPaginate] is true)
     * and the user has scrolled close to the end of the list.
     */
    val shouldStartPaginate by remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    /**
     * A LaunchedEffect that triggers pagination when [shouldStartPaginate] is true and the list is idle.
     */
    LaunchedEffect(key1 = shouldStartPaginate) {
        if (shouldStartPaginate && viewModel.listState == ListState.IDLE) {
            viewModel.onEvent(HomeUiEvent.GetCats)
        }
    }

    /**
     * A LaunchedEffect that shows a Snackbar when an error message is present.
     */
    LaunchedEffect(key1 = viewModel.errorMessage.value) {
        if (viewModel.errorMessage.value.isNotEmpty()) {
            snackbarHostState.showSnackbar(
                message = viewModel.errorMessage.value,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { CatTopAppBar(title = stringResource(R.string.home)) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        ParentContent(
            lazyColumnListState = lazyColumnListState,
            catList = viewModel.catList,
            onItemClicked = onItemClicked,
            onFavouriteClicked = {
                viewModel.onEvent(HomeUiEvent.OnFavouriteCatClick(it))
            },
            listState = viewModel.listState,
            coroutineScope = coroutineScope,
            modifier = modifier.padding(paddingValues)
        )
    }
}