package sazhin.pp.filmcomposableapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.pp.filmcomposableapp.domain.models.Film
import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel
import sazhin.pp.filmcomposableapp.ui.components.FullScreenProgress

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Suppress("MagicNumber")
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<FilmViewModel>()
        val state = viewModel.viewState

        Column(Modifier.fillMaxSize()) {

            val lazyColumnState = rememberSaveable(saver = LazyListState.Saver) {
                LazyListState(
                    0,
                    0
                )
            }

            state.error?.let {
                Text(text = it)
            }

            LazyColumn(
                Modifier.fillMaxSize(),
                lazyColumnState
            ) {
                items(state.items) {
                    ConstructorItem(item = it, navigation)
                }
            }
        }

        if (state.loading) {
            FullScreenProgress()
        }
    }
}

@Composable
private fun ConstructorItem(
    item: Film,
    navigation: StackNavContainer? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigation?.forward(DetailsScreen(item)) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.photo,
            modifier = Modifier
                .size(64.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(text = item.name)
            Text(
                text = item.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConstructorItemPreview() {
    ConstructorItem(
        item = Film(
            1,
            "Скуби-Ду",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/6201401/7fddea40-2855-4eeb-8f9e-784f0cfa756f/300x450",
            "Type.FILM",
            2002,
            "Два года спустя после того, как Тайная корпорация распалась из-за возникших противоречий Скуби-Ду и его сообразительных приятелей, раскрывших не одно преступление, Фреда, Дафну, Шэгги и Велму, поодиночке вызывают на Зловещий остров, чтобы они начали расследование серии паранормальных явлений, происходящих в Спринг-Брейк.\n" +
                    "\n" +
                    "Опасаясь, что его невероятно популярный курорт на самом деле может быть населен привидениями, владелец Зловещего острова Эмиль Мондавариус пытается воссоединить легендарных детективов, чтобы те разрешили загадку, пока сверхъестественная тайна не распугала всех отдыхающих студентов.\n" +
                    "\n" +
                    "Скуби-Ду и его команде придется преодолеть личные разногласия и по-новому взглянуть на мнимых вампиров и нереальных привидений. И все это, чтобы распутать дело, спасти самих себя, а возможно... и весь мир...",
            "Скуби-Ду и его команде придется преодолеть личные разногласия и по-новому взглянуть на мнимых вампиров и нереальных привидений.",
            10.0,
            89,
        )
    )
}

