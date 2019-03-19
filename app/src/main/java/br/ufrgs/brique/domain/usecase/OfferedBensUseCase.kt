package br.ufrgs.brique.domain.usecase

import br.ufrgs.brique.data.repository.network.response.MuralResponse
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody

interface OfferedBensUseCase {
    fun fetchOfferedBens(page: Int) : Deferred<MuralResponse>
}