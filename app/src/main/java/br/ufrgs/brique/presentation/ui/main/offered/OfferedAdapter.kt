package br.ufrgs.brique.presentation.ui.main.offered

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.data.OfferItem
import br.ufrgs.brique.data.repository.network.response.Bem

class OfferedAdapter(private val clickCallback: OfferClick) : RecyclerView.Adapter<OfferedViewHolder>() {

    private val list = arrayListOf<Bem>()

    fun updateList(list: List<Bem>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int) =
        list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferedViewHolder =
        OfferedViewHolder.instance(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OfferedViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            clickCallback.onItemClick(list[position])
        }
    }
}