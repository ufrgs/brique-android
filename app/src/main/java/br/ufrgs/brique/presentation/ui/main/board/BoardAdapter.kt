package br.ufrgs.brique.presentation.ui.main.board

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.data.repository.network.response.Meta

class BoardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var meta: Meta? = null
    private val list = arrayListOf<Bem>()

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

    fun updateList(meta: Meta, newList: List<Bem>) {
        this.list.addAll(newList)
        this.meta = meta
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int) =
        list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            BOARD_HEADER -> BoardHeaderViewHolder.instance(parent)
            else -> BoardViewHolder.instance(parent)
        }

    override fun getItemCount(): Int = list.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BoardHeaderViewHolder -> holder.bindView(meta)
            is BoardViewHolder -> holder.bindView(list[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> BOARD_HEADER
            else -> BOARD_ITEM
        }

    companion object {
        private const val BOARD_HEADER = 0
        private const val BOARD_ITEM = 1
    }
}