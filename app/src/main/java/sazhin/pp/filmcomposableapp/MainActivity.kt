package sazhin.pp.filmcomposableapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationManagerCompat
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel
import sazhin.pp.filmcomposableapp.presentation.profile.channelManager.NotificationChannelManager
import sazhin.pp.filmcomposableapp.presentation.screens.MainTabScreenFinal
import sazhin.pp.filmcomposableapp.ui.theme.FilmComposableAppTheme

class MainActivity : ComponentActivity() {

    private val channelManager: NotificationChannelManager by lazy {
        NotificationChannelManager(NotificationManagerCompat.from(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        channelManager.createNotificationChannels()
        setContent {
            val rootScreen: RootScreen<DefaultStackScreen> = rememberRootScreen {
                DefaultStackScreen(
                    StackNavModel(
                        MainTabScreenFinal()
                    )
                )
            }

            FilmComposableAppTheme {
                Surface(color = Color.White) {
                    rootScreen.Content(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}