package br.ufrgs.brique.presentation.ui.main.board

import br.ufrgs.brique.domain.usecase.MuralBensUseCase
import br.ufrgs.brique.presentation.ui.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BoardPresenter(private val muralBensUseCase: MuralBensUseCase) : BasePresenter<BoardView>() {
    override fun initialize() {
        fetchBens(0)
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


    fun fetchBens(page: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = muralBensUseCase.fetchBensByPage(page).await()
                withContext(Dispatchers.Main) {
                    view?.updateList(res._meta, res.items)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}