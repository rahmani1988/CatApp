package com.reza.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.reza.detail.data.model.Detail
import com.reza.detail.presentation.component.ItemDescription
import com.reza.detail.presentation.component.ItemImage
import com.reza.detail.presentation.component.ItemTitle
import com.reza.systemdesign.R
import com.reza.systemdesign.components.CatTopAppBar

/**
 * Displays the details of a given item.
 *
 * @param detail The [Detail] object containing the informationto display.
 * @param modifier A [Modifier] object that allows you to customize the appearance and behavior of the screen.
 * @param onBackPressed A lambda function that is executed when the back button in the top app bar is clicked.
 */
@Composable
internal fun DetailScreen(
    detail: Detail,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CatTopAppBar(
                title = stringResource(com.reza.detail.R.string.detail),
                isBackIconVisible = true,
                onBackPressed = onBackPressed
            )
        }
    ) { paddingValues ->
        ParentContent(
            modifier = modifier.padding(paddingValues),
            detail = detail
        )
    }
}

/**
 * A Composable function that displays the details of an item.
 *
 * @param detail The [Detail] object containing the information to display.
 * @param modifier Modifier to be applied to the root Column of the layout.
 */
@Composable
private fun ParentContent(
    detail: Detail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemTitle(name = detail.name)
        Spacer(modifier = Modifier.height(8.dp))
        ItemImage(image = detail.image)
        Spacer(modifier = Modifier.height(8.dp))
        ItemDescription(description = detail.description)
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        detail = Detail(
            image = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
            name = "abys",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals"
        )
    ) { }
}