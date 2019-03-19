package br.ufrgs.brique.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import br.ufrgs.brique.R
import br.ufrgs.brique.presentation.ui.RootActivity
import br.ufrgs.brique.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.kodein.di.generic.instance

class LoginActivity : RootActivity<LoginView>(), LoginView {

    override val layoutResourceId = R.layout.activity_login
    override val presenter: LoginPresenter by instance()

    override fun initializeUI() {
        login_button.setOnClickListener {
            presenter.onClickLoginButton()
        }

        login_forgot_password_text_view.setOnClickListener {
            startPasswordRecovery()
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

    override fun startMain() {
        MainActivity.start(this)
    }

    override fun startPasswordRecovery() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(FORGOT_PASSWORD_URL))
        startActivity(browserIntent)
    }

    override fun getUserCard(): String = et_login_card.text.toString()

    override fun getUserPassword(): String = et_login_password.text.toString()

    companion object {
        private const val FORGOT_PASSWORD_URL = "https://www1.ufrgs.br/trocasenhas/esqueciSenha/"

        fun start(context: Context) {
            context.startActivity<LoginActivity>()
        }
    }
}