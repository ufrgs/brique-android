package br.ufrgs.brique.domain.usecase

import br.ufrgs.brique.data.repository.network.AppServices
import br.ufrgs.brique.data.repository.network.response.MuralResponse
import kotlinx.coroutines.*

class MuralBensUseCaseImpl(private val appServices: AppServices) : MuralBensUseCase {
    override fun fetchBensByPage(page: Int): Deferred<MuralResponse> {
        return GlobalScope.async(Dispatchers.IO) {
            val res = appServices.getMuralBens(page + 1).await()
            return@async res.data
        }
    }
}