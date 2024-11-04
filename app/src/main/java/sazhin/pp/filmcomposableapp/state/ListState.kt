package sazhin.pp.filmcomposableapp.state

import androidx.compose.runtime.Stable
import sazhin.pp.filmcomposableapp.domain.models.Film

@Stable
interface ListState {
    val searchName: String
    val countSearchFilm: Int
    val year: Int
    val items: List<Film>
    val error: String?
    var loading: Boolean
    var isNotDefault: Boolean
}