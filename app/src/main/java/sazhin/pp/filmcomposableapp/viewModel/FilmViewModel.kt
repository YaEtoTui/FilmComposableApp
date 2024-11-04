package sazhin.pp.filmcomposableapp.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.urfu.consecutivepractice.coroutinesUtils.launchLoadingAndError
import sazhin.pp.filmcomposableapp.data.model.dto.PreferencesDto
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.models.Film
import sazhin.pp.filmcomposableapp.state.ListState

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class FilmViewModel(
    private val repository: IFilmRepository,
    private val context: Context
) : ViewModel() {

    val KEY_SEARCH_NAME = stringPreferencesKey("search_name")
    val KEY_COUNT = intPreferencesKey("count")
    val KEY_YEAR = intPreferencesKey("year")
    val KEY_IS_NOT_DEFAULT = booleanPreferencesKey("is_not_default")
    val counterFlow: Flow<PreferencesDto> = context.dataStore.data
        .map { preferences ->
            val searchName: String = preferences[KEY_SEARCH_NAME] ?: "Скуби-ду"
            val count: Int = preferences[KEY_COUNT] ?: 5
            val year: Int = preferences[KEY_YEAR] ?: 2004
            val isNotDefault: Boolean = preferences[KEY_IS_NOT_DEFAULT] ?: false

            return@map PreferencesDto(
                searchName,
                count,
                year,
                isNotDefault
            )
        }

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    init {
        loadFilms()

        viewModelScope.launch {
            counterFlow.collect {
                setSearchName(it.searchName)
                setCountSearchFilm(it.count)
                setYear(it.year)
                setIsNotDefault(it.isNotDefault)
            }
        }
    }

    private fun setIsNotDefault(notDefault: Boolean) {
        mutableState.isNotDefault = notDefault
    }

    fun getParameterIsNotDefault(): Boolean {
        return mutableState.isNotDefault
    }

    private fun loadFilms() {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.loading = it }
        ) {

            mutableState.items = emptyList()
            mutableState.error = null

            mutableState.items = repository.getFilms(viewState.searchName, viewState.countSearchFilm)
                .filter { it.year == viewState.year }
                .take(viewState.countSearchFilm)
        }
    }

    private fun setCountSearchFilm(countSearchFilm: Int) {
        mutableState.countSearchFilm = countSearchFilm
    }

    private fun setSearchName(nameFilm: String) {
        mutableState.searchName = nameFilm
    }

    private fun setYear(year: Int) {
        mutableState.year = year
    }

    fun updateParameters(nameFilm: String, countSearchFilm: Int, year: Int) {
        viewModelScope.launch {
            saveParameters(nameFilm, countSearchFilm, year)
        }

        changeParameters(nameFilm, countSearchFilm, year)
    }

    private fun changeParameters(nameFilm: String, countSearchFilm: Int, year: Int) {
        mutableState.searchName = nameFilm
        mutableState.countSearchFilm = countSearchFilm
        mutableState.year = year
    }

    private suspend fun saveParameters(nameFilm: String, countSearchFilm: Int, year: Int) {
        context.dataStore.edit { settings ->
            settings[KEY_SEARCH_NAME] = nameFilm
            settings[KEY_COUNT] = countSearchFilm
            settings[KEY_YEAR] = year
            settings[KEY_IS_NOT_DEFAULT] = true
        }
    }

    fun onFavoriteClicked(film: Film) {
        viewModelScope.launch {
            mutableState.items.find { it.name == film.name && it.year == film.year }
                ?.let { repository.saveFilm(film) }
        }
    }

    private class MutableListState : ListState {
        override var searchName: String by mutableStateOf("Скуби-ду")
        override var countSearchFilm: Int by mutableIntStateOf(5)
        override var year: Int by mutableIntStateOf(2004)
        override var items: List<Film> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
        override var isNotDefault: Boolean by mutableStateOf(false)
    }
}