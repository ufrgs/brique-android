package br.ufrgs.brique.domain.usecase

import br.ufrgs.brique.data.repository.network.AppServices
import br.ufrgs.brique.data.repository.network.response.MuralResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class OfferedBensUseCaseImpl(private val appServices: AppServices) : OfferedBensUseCase {
    override fun fetchOfferedBens(page: Int): Deferred<MuralResponse> =
        GlobalScope.async {
            appServices.getBensOfertados(page).await().data
        }
}