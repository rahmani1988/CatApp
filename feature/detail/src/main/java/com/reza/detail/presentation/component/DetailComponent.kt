package com.reza.detail.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.reza.systemdesign.R

/**
 * A simple composable that displays a title with the 'headlineMedium' style from Material Theme.
 *
 * @param name The text to display as the title.
 * @param modifier Modifier to be applied to the Text composable.
 */
@Composable
internal fun ItemTitle(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = name,
        style = MaterialTheme.typography.headlineMedium
    )
}

/**
 * A simple composable that displays a image with the 'CircleShape' from Material Theme.
 *
 * @param image The url of the image.
 * @param modifier Modifier to be applied to the Text composable.
 */
@Composable
internal fun ItemImage(
    image: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier
            .size(200.dp)
            .clip(CircleShape),
        model = image,
        placeholder = painterResource(R.drawable.baseline_smart_toy_24),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

/**
 * Displays a description text with padding and a specific typography style.
 *
 * @param description The text to display as the description.
 * @param modifier The modifier to apply to the Text composable.
 */
@Composable
internal fun ItemDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(horizontal = 8.dp),
        text = description,
        style = MaterialTheme.typography.bodyMedium
    )
}