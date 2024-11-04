package sazhin.pp.filmcomposableapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Long,
    val name: String,
    val photo: String,
    val type: String,
    val year: Int,
    val description: String,
    val shortDescription: String,
    val rating: Double,
    val movieLength: Int,
) : Parcelable