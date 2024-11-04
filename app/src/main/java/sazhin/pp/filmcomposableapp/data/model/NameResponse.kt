package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep

@Keep
data class NameResponse(
    val name: String?,
    val language: String?,
    val type: String?
)
