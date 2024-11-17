package sazhin.pp.filmcomposableapp.di

import androidx.datastore.core.DataStore
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.urfu.consecutivepractice.domain.model.ProfileEntity
import sazhin.pp.filmcomposableapp.data.dataSource.DataSourceProvider
import sazhin.pp.filmcomposableapp.data.mapper.FilmResponseToEntityMapper
import sazhin.pp.filmcomposableapp.data.repository.FilmRepository
import sazhin.pp.filmcomposableapp.data.repository.ProfileRepository
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.IProfileRepository
import sazhin.pp.filmcomposableapp.utils.cache.CacheService
import sazhin.pp.filmcomposableapp.viewModel.EditProfileViewModel
import sazhin.pp.filmcomposableapp.viewModel.FavoriteFilmViewModel
import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel
import sazhin.pp.filmcomposableapp.viewModel.ProfileViewModel

val rootModule = module {
    single<IFilmRepository> { FilmRepository(get(), get(), get()) }
    factory { FilmResponseToEntityMapper() }

    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }
    single<IProfileRepository> { ProfileRepository() }

    viewModel { FilmViewModel(get(), get()) }
    viewModel { FavoriteFilmViewModel(get()) }
    single<CacheService> {CacheService(get())}

    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}