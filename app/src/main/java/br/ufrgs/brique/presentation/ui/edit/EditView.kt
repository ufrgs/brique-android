package br.ufrgs.brique.presentation.ui.edit

import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.presentation.ui.BaseView
import java.io.File

interface EditView : BaseView {
    fun showImage(image: File)
    fun hideAddImageLabel()
    fun showAddImageLabel()
    fun hideChangeImageLabel()
    fun showChangeImageLabel()
    fun showImagePopup()
    fun getCurrentBem() : Bem?
    fun setName(string: String)
    fun setDescription(string: String)
    fun setPatrimonio(string: String)
    fun setOrigin(string: String)
    fun closeActivity()

}