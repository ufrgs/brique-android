package br.ufrgs.brique.domain.usecase

import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.data.repository.network.response.MuralResponse
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody

interface MuralBensUseCase {
    fun fetchBensByPage(page: Int): Deferred<MuralResponse>
}