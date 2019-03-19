package br.ufrgs.brique.presentation.ui.main.offered

import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.data.repository.network.response.Meta
import br.ufrgs.brique.presentation.ui.BaseView

interface OfferedView : BaseView {
    fun updateList(list: List<Bem>)
}