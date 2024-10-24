package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep

@Keep
data class FilmsPagingResponse(
    val docs: List<FilmResponse?>?,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)