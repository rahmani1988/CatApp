package com.reza.home.presentation

import com.reza.data.models.Cat

/**
 * Represents UI events that can occur on the Home screen.
 */
internal sealed interface HomeUiEvent {
    /**
     * Event triggered to fetch a list of cats.
     */
    data object GetCats : HomeUiEvent

    /**
     * Event triggered when the favorite button on a cat item is clicked.
     *
     * @property cat The [Cat] object associated with the clicked favorite button.
     */
    data class OnFavouriteCatClick(val cat: Cat) : HomeUiEvent
}