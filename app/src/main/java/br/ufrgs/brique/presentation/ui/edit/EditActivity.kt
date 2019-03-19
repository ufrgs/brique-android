package br.ufrgs.brique.presentation.ui.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import br.ufrgs.brique.R
import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.presentation.extensions.hide
import br.ufrgs.brique.presentation.extensions.show
import br.ufrgs.brique.presentation.ui.RootActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.kodein.di.generic.instance
import java.io.File

class EditActivity : RootActivity<EditView>(), EditView {
    override val layoutResourceId: Int = R.layout.activity_edit
    override val presenter: EditPresenter by instance()
    private val bem: Bem? by lazy { intent.getParcelableExtra<Bem>(KEY_BEM) }

    override fun initializeUI() {
        btn_image_card.setOnClickListener {
            presenter.onAddImageClick()
        }

        btn_save.setOnClickListener {
            presenter.onSaveButtonClick(tv_name.text.toString(), tv_description.text.toString())
        }
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun showError(messageId: Int) {
        toast(messageId)
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun hideAddImageLabel() {
        lbl_add_image.hide()
    }

    override fun hideChangeImageLabel() {
        lbl_change_image.hide()
    }

    override fun showAddImageLabel() {
        lbl_add_image.show()
    }

    override fun showChangeImageLabel() {
        lbl_change_image.show()
    }

    override fun showImage(image: File) {
        Picasso.get().load(image).into(iv_image)
    }

    override fun setDescription(string: String) {
        tv_description.setText(string)
    }

    override fun setName(string: String) {
        tv_name.setText(string)
    }

    override fun setOrigin(string: String) {
        tv_origin.setText(string)
    }

    override fun setPatrimonio(string: String) {
        tv_patrimonio.setText(string)
    }

    override fun getCurrentBem(): Bem? = bem

    override fun showImagePopup() {
        val options = listOf("Câmera", "Galeria")
        selector("Escolher imagem", options) { dialogInterface, i ->
            when (i) {
                0 -> presenter.openCamera()
                1 -> presenter.choseImageFromGallery()
            }

            dialogInterface.dismiss()
        }
    }

    override fun closeActivity() {
        setResult(RESULT_OK)
        finish()
    }

    companion object {
        private const val KEY_BEM = "bem"
        const val EDIT_REQUEST_CODE = 90

        fun start(context: Context) {
            context.startActivity<EditActivity>()
        }

        fun start(fragment: Fragment, bem: Bem) {
            val i = Intent(fragment.activity, EditActivity::class.java)
                .apply { putExtra(KEY_BEM, bem) }
            fragment.startActivityForResult(i, EDIT_REQUEST_CODE)
        }
    }
}