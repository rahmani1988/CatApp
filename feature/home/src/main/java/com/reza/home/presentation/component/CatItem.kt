package com.reza.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.reza.data.models.Cat

/**
 * Composable function that displays a single cat item.
 *
 * @param cat The [Cat] object to display.
 * @param onFavouriteClicked Callback invoked when the favorite button is clicked.
 * @param onItemClicked Callback invoked when the item is clicked.
 * @param modifier Modifier used to adjust the layout of the item.
 */
@Composable
internal fun CatItem(
    cat: Cat,
    onFavouriteClicked: (Cat) -> Unit,
    onItemClicked: (Cat) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onItemClicked(cat) }, verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                model = cat.image.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(text = cat.name)
            Spacer(modifier = modifier.weight(1f))
            FavouriteButton(
                cat = cat,
                isFavourite = cat.isFavourite,
                onFavouriteClicked = onFavouriteClicked
            )
        }
    }
}

/**
 * Composable function that displays a favorite button for a cat.
 *
 * @param cat The [Cat] object associated with the favorite button.
 * @param isFavourite Indicates whether the cat is currently a favorite.
 * @param onFavouriteClicked Callback invoked when the favorite button is clicked.
 * @param modifier Modifier used to adjust the layout of the button.
 */
@Composable
fun FavouriteButton(
    cat: Cat,
    isFavourite: Boolean,
    onFavouriteClicked: (Cat) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.padding(8.dp),
        onClick = {
            onFavouriteClicked(cat)
        }) {
        Icon(
            imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            tint = Color.Red,
            contentDescription = null
        )
    }
}