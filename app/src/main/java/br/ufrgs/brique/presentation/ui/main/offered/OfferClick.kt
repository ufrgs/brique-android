package br.ufrgs.brique.presentation.ui.main.offered

import br.ufrgs.brique.data.repository.network.response.Bem

interface OfferClick {
    fun onItemClick(bem: Bem)
}