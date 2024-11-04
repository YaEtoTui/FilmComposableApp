package sazhin.pp.filmcomposableapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.models.Film
import sazhin.pp.filmcomposableapp.state.FavoriteFilmsState

class FavoriteFilmViewModel(
    private val repository: IFilmRepository
) : ViewModel() {
    private var films: List<Film> = emptyList()

    private val mutableState = MutableFilmsState()
    val viewState = mutableState as FavoriteFilmsState

    init {
        loadFavoriteFilms()
    }

    private fun loadFavoriteFilms() {
        viewModelScope.launch {
            films = repository.getFavoriteFilms()
            mutableState.items = films
        }
    }

    private class MutableFilmsState : FavoriteFilmsState {
        override var items: List<Film> by mutableStateOf(emptyList())
    }
}