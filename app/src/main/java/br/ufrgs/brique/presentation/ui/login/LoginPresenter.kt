package br.ufrgs.brique.presentation.ui.login

import android.content.Context
import br.ufrgs.brique.presentation.ui.BasePresenter
import br.ufrgs.cpd.coresdk.token.UfrgsTokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginPresenter(
    private val context: Context,
    private val tokenManager: UfrgsTokenManager
) : BasePresenter<LoginView>() {

    override fun initialize() {}

    override fun pause() {}

    override fun stop() {}

    override fun destroy() {}

    fun onClickLoginButton() {
        //132034
        val userCard = view?.getUserCard()
        val password = view?.getUserPassword()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val tokenRequest = tokenManager.requestNewToken(context, userCard!!, password!!).await()
                if (tokenRequest.success != "false") {
                    view?.startMain()
                } else {
                    val json = JSONObject(tokenRequest.message)
                    view?.showError(json.getString("message"))
                }
            } catch (e: Exception) {
                view?.showError(e.localizedMessage)
            }
        }
    }
}