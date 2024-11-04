package sazhin.pp.filmcomposableapp.utils.cache

import sazhin.pp.filmcomposableapp.viewModel.FilmViewModel

class CacheService(
    private val viewModel: FilmViewModel
) {

    fun isNotDefault(): Boolean {
        return viewModel.getParameterIsNotDefault()
    }
}