package com.reza.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.reza.data.models.Cat
import com.reza.home.R
import com.reza.home.presentation.ListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable function that displays the main content of the screen, including a LazyColumn of cat items.
 *
 * @param lazyColumnListState The LazyListState of the LazyColumn.
 * @param catList The list of [Cat] objects to display.
 * @param onItemClicked Callback invoked when a cat item is clicked.
 * @param onFavouriteClicked Callback invoked when the favorite button on a cat item is clicked.
 * @param listState The current state of the list.
 * @param coroutineScope The CoroutineScope used for scrolling and other asynchronous operations.
 * @param modifier Modifier used to adjust the layout of the content.
 */
@Composable
internal fun ParentContent(
    lazyColumnListState: LazyListState,
    catList: List<Cat>,
    onItemClicked: (Cat) -> Unit,
    onFavouriteClicked: (Cat) -> Unit,
    listState: ListState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = lazyColumnListState
    ) {
        items(
            items = catList,
            key = { it.id },
        ) { cat ->
            CatItem(
                cat = cat,
                onItemClicked = onItemClicked,
                onFavouriteClicked = onFavouriteClicked
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item(
            key = listState,
        ) {
            when (listState) {
                ListState.LOADING -> LoadingItem(modifier = Modifier.fillParentMaxSize())

                ListState.PAGINATING -> PagingItem()

                ListState.PAGINATION_EXHAUST ->
                    PagingExhaustedItem(
                        coroutineScope = coroutineScope,
                        lazyColumnListState = lazyColumnListState
                    )

                else -> Unit
            }
        }
    }
}

/**
 * Composable function that displays a loading item with a loading indicator.
 *
 * @param modifier Modifier used to adjust the layout of the item.
 */
@Composable
private fun LoadingItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = stringResource(R.string.refresh_loading)
        )

        CircularProgressIndicator(
            modifier = Modifier.width(24.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

/**
 * Composable function that displays a paging item with a loading indicator.
 *
 * @param modifier Modifier used to adjust the layout of the item.
 */
@Composable
private fun PagingItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.pagination_loading))

        CircularProgressIndicator(
            modifier = Modifier.width(24.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

/**
 * Composable function that displays a paging exhausted item with a button to scroll to the top.
 *
 * @param modifier Modifier used to adjust the layout of the item.
 * @param coroutineScope The CoroutineScope used to launch the scroll animation.
 * @param lazyColumnListState The LazyListState of the LazyColumn.
 */
@Composable
fun PagingExhaustedItem(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    lazyColumnListState: LazyListState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")

        Text(text = stringResource(R.string.nothing_left))

        TextButton(
            modifier = Modifier
                .padding(top = 8.dp),
            onClick = {
                coroutineScope.launch {
                    lazyColumnListState.animateScrollToItem(0)
                }
            },
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )

                    Text(text = stringResource(R.string.back_to_top))

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}