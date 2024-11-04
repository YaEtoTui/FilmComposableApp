package sazhin.pp.filmcomposableapp.state

import androidx.compose.runtime.Stable
import sazhin.pp.filmcomposableapp.domain.models.Film

@Stable
interface FavoriteFilmsState {
    val items: List<Film>
}