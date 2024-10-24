package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FilmResponse(
    val id: Long?,
    val name: String?,
    @SerializedName("alternativeName") val alternativeName: String?,
    @SerializedName("enName") val enName: String?,
    val type: String?,
    val year: Int?,
    val description: String?,
    @SerializedName("shortDescription") val shortDescription: String?,
    @SerializedName("movieLength") val movieLength: Int?,
    @SerializedName("isSeries") val isSeries: Boolean?,
    @SerializedName("ticketsOnSale") val ticketsOnSale: Boolean?,
    @SerializedName("totalSeriesLength") val totalSeriesLength: Int?,
    @SerializedName("seriesLength") val seriesLength: Int?,
    @SerializedName("ratingMpaa") val ratingMpaa: String?,
    @SerializedName("ageRating") val ageRating: Int?,
    @SerializedName("top10") val top10: Int?,
    @SerializedName("top250") val top250: Int?,
    @SerializedName("typeNumber") val typeNumber: Int?,
    @SerializedName("status") val status: String?,
    val names: List<NameResponse?>?,
    @SerializedName("externalId") val externalId: ExternalIdResponse?,
    val logo: LogoResponse?,
    val poster: PosterResponse?,
    val backdrop: BackdropResponse?,
    val rating: RatingResponse?,
    val votes: VotesResponse?,
    val genres: List<GenreResponse?>?,
    val countries: List<CountryResponse?>?,
    @SerializedName("releaseYears") val releaseYears: List<ReleaseYearResponse?>?
)