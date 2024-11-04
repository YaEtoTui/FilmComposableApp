package sazhin.pp.filmcomposableapp.state

import androidx.compose.runtime.Stable
import sazhin.pp.filmcomposableapp.domain.models.Film

@Stable
interface ListState {
    val searchName: String
    val items: List<Film>
    val error: String?
    var loading: Boolean
}