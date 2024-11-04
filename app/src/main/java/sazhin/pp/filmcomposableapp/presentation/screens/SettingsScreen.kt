package sazhin.pp.filmcomposableapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.pp.filmcomposableapp.R
import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel

@Parcelize
class SettingsScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content(modifier: Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val viewModel = koinViewModel<FilmViewModel>()
            val state = viewModel.viewState

            var searchText by remember { mutableStateOf(state.searchName) }
            var countSearchFilm by remember { mutableStateOf(state.countSearchFilm) }
            var year by remember { mutableStateOf(state.year) }

            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = {
                    Text("Введите название фильма")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            TextField(
                value = countSearchFilm.toString(),
                onValueChange = {
                    countSearchFilm = it.toInt()
                },
                placeholder = {
                    Text("Введите количество фильмов")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            TextField(
                value = year.toString(),
                onValueChange = {
                    year = it.toInt()
                },
                placeholder = {
                    Text("Введите год релиза")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            TextButton(
                onClick = {
                    viewModel.updateParameters(searchText, countSearchFilm, year)
                }
            ) {
                Text(stringResource(R.string.confirm))
            }
        }
    }
}