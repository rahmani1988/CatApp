package com.reza.home.presentation

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.data.models.Cat
import com.reza.home.data.repository.DefaultCatRepository
import com.reza.home.domain.usecase.GetCatsUseCase
import com.reza.home.domain.usecase.InsertFavouriteCatUseCase
import com.reza.home.domain.usecase.RemoveFavouriteCatUseCase
import com.reza.threading.common.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 *
 * This ViewModel is responsible for fetching cats using [getCatsUseCase],
 * inserting favorite cats using [insertFavouriteCatUseCase], and
 * removing favorite cats using [removeFavouriteCatUseCase].
 */
@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val insertFavouriteCatUseCase: InsertFavouriteCatUseCase,
    private val removeFavouriteCatUseCase: RemoveFavouriteCatUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    /**
     * A mutable state list to hold the list of cats.
     */
    private val _catList = mutableStateListOf<Cat>()

    /**
     * An immutable snapshot state list exposing the list of cats.
     */
    val catList: SnapshotStateList<Cat> = _catList

    /**
     * The current page number for pagination. Initialized to 0.
     */
    private var page: Int = 0

    /**
     * Sets the current page number for pagination.
     *
     * @param page The new page number.
     */
    @VisibleForTesting
    fun setPage(page: Int) {
        this.page = page
    }

    /**
     * Indicates whether more pages are available for pagination.
     */
    private var _canPaginate by mutableStateOf(false)

    /**
     * Sets the flag indicating whether more pages are available for pagination.
     *
     * @param canPaginate The new flag value.
     */
    @VisibleForTesting
    fun canPaginate(canPaginate: Boolean) {
        _canPaginate = canPaginate
    }

    /**
     * Indicates whether more pages are available for pagination, exposed as a read-only property.
     */
    val canPaginate: Boolean
        get() = _canPaginate

    /**
     * The current state of the list.
     */
    private var _listState by mutableStateOf(ListState.IDLE)

    /**
     * The current state of the list, exposed as a read-only property.
     */
    val listState: ListState
        get() = _listState

    /**
     * Holds the current error message to be displayed.
     */
    private var _errorMessage = mutableStateOf("")

    /**
     * The current error message to be displayed, exposed as a read-only property.
     */
    val errorMessage: State<String> = _errorMessage

    /**
     * A CoroutineExceptionHandler that sets the list state to [ListState.ERROR] when an exception occurs.
     */
    private val handler = CoroutineExceptionHandler { _, exception ->
        _listState = ListState.ERROR
        _errorMessage.value = exception.message.toString()
    }

    /**
     * Initializes the ViewModel by fetching the initial list of cats.
     */
    init {
        getCats()
    }

    /**
     * Handles events for the Home screen.
     *
     * @param event The [HomeUiEvent] to handle.
     */
    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnFavouriteCatClick -> {
                toggleFavourite(event.cat)
            }

            is HomeUiEvent.GetCats -> {
                getCats()
            }
        }
    }

    private fun getCats() = viewModelScope.launch(mainDispatcher + handler) {
        if (page == 0 || (page != 0 && canPaginate) && listState == ListState.IDLE) {
            _listState = if (page == 0) ListState.LOADING else ListState.PAGINATING

            getCatsUseCase.getCats(page).let { cats ->
                if (cats.isNotEmpty()) {
                    _canPaginate = cats.size == DefaultCatRepository.PAGE_LIMIT

                    if (page == 0) {
                        catList.clear()
                        catList.addAll(cats)
                    } else {
                        catList.addAll(cats)
                    }

                    _listState = ListState.IDLE

                    if (canPaginate)
                        page++
                } else {
                    _listState = if (page == 0) ListState.ERROR else ListState.PAGINATION_EXHAUST
                }
            }
        }
    }

    /**
     * Toggles the favorite status of a cat.
     *
     * If the cat is currently a favorite, it will be removed from favorites.
     * Otherwise, it will be added as a favorite.
     *
     * @param cat The cat to toggle the favorite status for.
     */
    private fun toggleFavourite(cat: Cat) {
        viewModelScope.launch(mainDispatcher + handler) {
            if (cat.isFavourite) {
                removeFavouriteCatUseCase.removeFavoriteCat(catId = cat.id)
            } else {
                insertFavouriteCatUseCase.insertFavoriteCat(cat = cat)
            }
            updateList(cat)
        }
    }

    /**
     * Updates the favourite status of a cat in the list.
     *
     * @param cat The cat object to update.
     */
    private fun updateList(cat: Cat) {
        val index = catList.indexOfFirst { it.id == cat.id }
        catList[index] = catList[index].copy(isFavourite = !cat.isFavourite)
    }

    /**
     * Called when the ViewModel is cleared.
     *
     * Resets the pagination state and list state to their initial values.
     */
    override fun onCleared() {
        page = 0
        _listState = ListState.IDLE
        _canPaginate = false
        super.onCleared()
    }
}


/**
 * Represents the different states of a list.
 */
enum class ListState {
    /**
     * The initial state of the list.
     */
    IDLE,

    /**
     * The list is currently loading data.
     */
    LOADING,

    /**
     * The list is currently paginating, loading more data.
     */
    PAGINATING,

    /**
     * An error occurred while loading or paginating the list.
     */
    ERROR,

    /**
     * The list has reached the end and there is no more data to load.
     */
    PAGINATION_EXHAUST,
}