package br.ufrgs.brique.presentation.ui.edit

import br.ufrgs.brique.data.repository.network.ApiBuilder
import br.ufrgs.brique.data.repository.network.AppServices
import br.ufrgs.brique.data.repository.network.request.EditBemBody
import br.ufrgs.brique.domain.usecase.GetBemImageUseCase
import br.ufrgs.brique.presentation.extensions.logD
import br.ufrgs.brique.presentation.ui.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class EditPresenter(private val apiBuilder: ApiBuilder, private val getBemImageUseCase: GetBemImageUseCase) :
    BasePresenter<EditView>() {
    override fun initialize() {
        val bem = view?.getCurrentBem()

        if (bem != null) {
            view?.setPatrimonio(bem.NrPatrimonio)
            view?.setName(bem.Nome)
            bem.Descricao?.let { view?.setDescription(it) }
            bem.NomeOrgaoOrigem?.let { view?.setOrigin(it) }
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val image = getBemImageUseCase.getImage(bem.NrSeqItem).await()
                    withContext(Dispatchers.Main) {
                        view?.showImage(image)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            view?.hideChangeImageLabel()
        }

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

    fun onAddImageClick() {
        view?.showImagePopup()
    }

    fun onSaveButtonClick(name: String, description: String) {
        val currentBem = view?.getCurrentBem()
        if (currentBem != null) {
            editItem(currentBem.NrSeqItem, name, description)
        }

    }

    fun choseImageFromGallery() {

    }

    fun openCamera() {

    }

    private fun editItem(nrSeq: String, name: String, description: String) {
        GlobalScope.launch {
            try {
                val service = apiBuilder.retrofit().create(AppServices::class.java)
                val res = service.editItem(nrSeq, EditBemBody(name, description)).await()
                val json = JSONObject(res.string())

                withContext(Dispatchers.Main) {
                    if (json["message"] == "OK") {
                        view?.showError("Editado com sucesso")
                        view?.closeActivity()
                    } else {
                        view?.showError(json["message"].toString())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                view?.showError(e.localizedMessage)
            }
        }
    }
}