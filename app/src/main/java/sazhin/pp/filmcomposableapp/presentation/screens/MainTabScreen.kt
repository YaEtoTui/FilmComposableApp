package sazhin.pp.filmcomposableapp.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.github.terrakok.modo.animation.SlideTransition
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.multiscreen.selectScreen
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.pp.filmcomposableapp.utils.cache.CacheService
import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel

@Parcelize
class MainTabScreenFinal(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        ProfileScreen(),
        ListScreen(),
        FavoriteScreen(),
        SettingsScreen(),
        selected = 0
    )
) : MultiScreen(navModel) {

    @Composable
    override fun Content(modifier: Modifier) {
        MainTabContent(
            modifier = modifier,
            selectedTabPos = navigationState.selected,
            onTabClick = { pos ->
                selectScreen(pos)
            }
        ) {
            SelectedScreen(
                Modifier
                    .padding(this)
                    .fillMaxSize()
            ) { innerModifier ->
                SlideTransition(innerModifier)
            }
        }
    }
}

@Composable
fun MainTabContent(
    selectedTabPos: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable PaddingValues.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar {
                for ((pos, tab) in MainTabs.entries.withIndex()) {
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onTabClick(pos)
                        },
                    ) {
                        val contentColor = LocalContentColor.current
                        val color by animateColorAsState(
                            contentColor.copy(
                                alpha = if (pos == selectedTabPos) contentColor.alpha else 0.5f
                            ), label = ""
                        )

                        val viewModel = koinViewModel<FilmViewModel>()
                        val cacheService = CacheService(viewModel)

                        if (cacheService.isNotDefault() && tab.title == "Settings") {
                            BadgedBox(
                                badge = {
                                    Badge()
                                }
                            ) {
                                Icon(imageVector = tab.icon, contentDescription = "")
                            }
                        } else {
                            Icon(
                                rememberVectorPainter(tab.icon),
                                tint = color,
                                contentDescription = tab.title
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        paddingValues.content()
    }
}

enum class MainTabs(
    val icon: ImageVector,
    val title: String
) {
    PROFILE(Icons.Default.Face, "Profile"),
    HOME(Icons.AutoMirrored.Rounded.List, "List"),
    FAVORITES(Icons.Default.Favorite, "Favorite"),
    SETTINGS(Icons.Default.Settings, "Settings")
}