package br.ufrgs.brique.presentation.ui.main.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.R
import br.ufrgs.brique.data.repository.network.response.Meta
import kotlinx.android.synthetic.main.item_board_header.view.*

class BoardHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(meta: Meta?){
        val total = meta?.totalCount ?: 0
        itemView.tv_header.text = "$total OFERTAS DISPONÍVEIS"
    }

    companion object {
        fun instance(parent: ViewGroup) =
                BoardHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_board_header, parent, false))
    }

}