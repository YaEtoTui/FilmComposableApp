package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VotesResponse(
    @SerializedName("kp") val kp: String?,
    @SerializedName("imdb") val imdb: Int?,
    @SerializedName("filmCritics") val filmCritics: Int?,
    @SerializedName("russianFilmCritics") val russianFilmCritics: Int?,
    @SerializedName("await") val await: Int?
)