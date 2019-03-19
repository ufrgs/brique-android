package br.ufrgs.brique.presentation.ui.main.offered

import br.ufrgs.brique.domain.usecase.OfferedBensUseCase
import br.ufrgs.brique.presentation.ui.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OfferedPresenter(private val offeredBensUseCase: OfferedBensUseCase) : BasePresenter<OfferedView>() {
    override fun initialize() {
        GlobalScope.launch(Dispatchers.IO) {
            val res = offeredBensUseCase.fetchOfferedBens(1).await()
            val list = res.items
            val totalCount = res._meta.pageCount

            if (totalCount - 1 > 0) {
                repeat(totalCount - 1) {
                    val tempList = offeredBensUseCase.fetchOfferedBens(totalCount + 1).await().items
                }
            }

            withContext(Dispatchers.Main) {
                view?.updateList(list)
            }
        }
    }

    override fun pause() {
        //TODO("not implemented")
    }

    override fun stop() {
        //TODO("not implemented")
    }

    override fun destroy() {
        //TODO("not implemented")
    }
}