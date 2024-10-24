package sazhin.pp.filmcomposableapp.domain

import sazhin.pp.filmcomposableapp.models.Film

interface IFilmRepository {
    suspend fun getFilms(nameSearch: String): List<Film>
}