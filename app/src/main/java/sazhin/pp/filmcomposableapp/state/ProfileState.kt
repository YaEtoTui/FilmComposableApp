package sazhin.pp.filmcomposableapp.state

import android.net.Uri

interface ProfileState {
    val name: String
    val photoUri: Uri
}