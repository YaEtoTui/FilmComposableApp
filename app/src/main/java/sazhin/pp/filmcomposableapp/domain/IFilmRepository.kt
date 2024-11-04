package sazhin.pp.filmcomposableapp.domain

import sazhin.pp.filmcomposableapp.domain.models.Film

interface IFilmRepository {
    suspend fun getFilms(nameSearch: String): List<Film>
}