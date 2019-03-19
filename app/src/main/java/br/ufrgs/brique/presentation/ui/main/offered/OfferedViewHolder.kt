package br.ufrgs.brique.presentation.ui.main.offered

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.R
import br.ufrgs.brique.data.OfferItem
import br.ufrgs.brique.data.repository.network.response.Bem
import kotlinx.android.synthetic.main.item_offer.view.*

class OfferedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Bem) = with(itemView){
        tv_patrimonio.text = "Patrimonio ${item.NrPatrimonio}"
        tv_title.text = item.Nome
        tv_description.text = item.Descricao ?: ""
    }

    companion object {
        fun instance(parent: ViewGroup) =
                OfferedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false))
    }
}