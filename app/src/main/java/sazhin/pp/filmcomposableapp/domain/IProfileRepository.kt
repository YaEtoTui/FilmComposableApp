package sazhin.pp.filmcomposableapp.domain

import kotlinx.coroutines.flow.Flow
import ru.urfu.consecutivepractice.domain.model.ProfileEntity

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(photoUri: String, name: String, url: String): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}