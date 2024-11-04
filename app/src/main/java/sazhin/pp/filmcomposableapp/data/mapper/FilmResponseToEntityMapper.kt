package sazhin.pp.filmcomposableapp.data.mapper

import sazhin.pp.filmcomposableapp.data.model.FilmsPagingResponse
import sazhin.pp.filmcomposableapp.domain.models.Film

class FilmResponseToEntityMapper {
    fun mapFilms(response: FilmsPagingResponse): List<Film> {
        return response.docs?.map {
            Film(
                id = it?.id ?: 0L,
                name = it?.name.orEmpty(),
                photo = it?.poster?.previewUrl.orEmpty(),
                type = it?.type.orEmpty(),
                year = it?.year ?: 0,
                description = it?.description.orEmpty(),
                shortDescription = it?.shortDescription.orEmpty(),
                rating = it?.rating?.kp ?: 0.0,
                movieLength = it?.movieLength ?: 0,
            )
        }.orEmpty()
    }
}