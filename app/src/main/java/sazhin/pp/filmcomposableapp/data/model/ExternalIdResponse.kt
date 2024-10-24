package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ExternalIdResponse(
    @SerializedName("kpHD") val kpHD: String?,
    @SerializedName("imdb") val imdb: String?,
    @SerializedName("tmdb") val tmdb: Long?
)