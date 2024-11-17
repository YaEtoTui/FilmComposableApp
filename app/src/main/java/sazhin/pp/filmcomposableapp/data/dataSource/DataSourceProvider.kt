package sazhin.pp.filmcomposableapp.data.dataSource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import ru.urfu.consecutivepractice.domain.model.ProfileEntity
import sazhin.pp.filmcomposableapp.data.serializer.ProfileSerializer

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}