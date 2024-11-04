package sazhin.pp.filmcomposableapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import sazhin.pp.filmcomposableapp.data.mapper.FilmResponseToEntityMapper
import sazhin.pp.filmcomposableapp.data.repository.FilmRepository
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel

val rootModule = module {
    single<IFilmRepository> { FilmRepository(get(), get()) }
    factory { FilmResponseToEntityMapper() }
    viewModel { FilmViewModel(get()) }
}