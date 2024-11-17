package sazhin.pp.filmcomposableapp.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sazhin.pp.filmcomposableapp.domain.IProfileRepository
import sazhin.pp.filmcomposableapp.state.ProfileState

class ProfileViewModel(
    private val repository: IProfileRepository
): ViewModel() {

    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState

    init {
        viewModelScope.launch {
            repository.observeProfile().collect {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
            }
        }
    }

    private class MutableProfileState: ProfileState {
        override var name by mutableStateOf("")
        override var photoUri by mutableStateOf(Uri.EMPTY)

    }
}