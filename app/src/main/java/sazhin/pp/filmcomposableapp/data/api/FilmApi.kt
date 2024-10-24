package sazhin.pp.filmcomposableapp.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import sazhin.pp.filmcomposableapp.data.model.FilmsPagingResponse

interface FilmApi {

    @GET("movie/search")
    suspend fun getFilms(
        @Header("X-API-KEY") apiKey: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") nameSearch: String
    ): FilmsPagingResponse
}