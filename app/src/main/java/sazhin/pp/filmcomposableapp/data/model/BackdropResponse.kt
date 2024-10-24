package sazhin.pp.filmcomposableapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BackdropResponse(
    val url: String?,
    @SerializedName("previewUrl") val previewUrl: String?
)
