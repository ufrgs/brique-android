package br.ufrgs.brique.presentation.ui.main.board

import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.data.repository.network.response.Meta
import br.ufrgs.brique.presentation.ui.BaseView

interface BoardView : BaseView {
    fun updateList(meta: Meta, list: List<Bem>)
}