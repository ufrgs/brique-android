package br.ufrgs.brique.presentation.ui.main.board

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.R
import br.ufrgs.brique.data.repository.network.ApiBuilder
import br.ufrgs.brique.data.repository.network.AppServices
import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.domain.ImageConverter
import br.ufrgs.cpd.coresdk.token.UfrgsTokenManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_board.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val builder = ApiBuilder(itemView.context, UfrgsTokenManager())

    fun bindView(item: Bem) {
        itemView.tv_title.text = item.Nome

        val service = builder.retrofit().create(AppServices::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = service.getItemImage(item.NrSeqItem).await()
                val bytes = res.bytes()
                val file = File.createTempFile("${item.NrPatrimonio}", null, itemView.context.cacheDir)

                val fos = FileOutputStream(file)
                fos.write(bytes)
                fos.close()

                withContext(Dispatchers.Main) {
                    Picasso.get().load(file).into(itemView.image)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        fun instance(parent: ViewGroup) =
            BoardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false))
    }

}