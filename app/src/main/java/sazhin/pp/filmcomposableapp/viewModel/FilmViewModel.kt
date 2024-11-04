package sazhin.pp.filmcomposableapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.urfu.consecutivepractice.coroutinesUtils.launchLoadingAndError
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.models.Film
import sazhin.pp.filmcomposableapp.state.ListState

class FilmViewModel(
    private val repository: IFilmRepository
) : ViewModel() {

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    init {
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.loading = it }
        ) {
            mutableState.error = null

            mutableState.items = repository.getFilms(viewState.searchName)
        }
    }

    private class MutableListState : ListState {
        override var searchName: String by mutableStateOf("скубиду")
        override var items: List<Film> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}