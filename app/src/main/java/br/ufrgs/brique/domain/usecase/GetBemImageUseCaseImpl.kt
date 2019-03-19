package br.ufrgs.brique.domain.usecase

import android.content.Context
import br.ufrgs.brique.data.repository.network.ApiBuilder
import br.ufrgs.brique.data.repository.network.AppServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_board.view.*
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream

class GetBemImageUseCaseImpl(private val context: Context, private val builder: ApiBuilder) : GetBemImageUseCase {
    override fun getImage(nrSequencia: String): Deferred<File> = GlobalScope.async {
        val service = builder.retrofit().create(AppServices::class.java)
        val res = service.getItemImage(nrSequencia).await()
        val bytes = res.bytes()
        val file = File.createTempFile("$nrSequencia", null, context.cacheDir)

        val fos = FileOutputStream(file)
        fos.write(bytes)
        fos.close()
        file
    }
}