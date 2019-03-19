package br.ufrgs.brique.presentation.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.ufrgs.brique.presentation.ui.login.LoginActivity
import br.ufrgs.brique.presentation.ui.main.MainActivity
import br.ufrgs.cpd.coresdk.token.UfrgsTokenManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SplashActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val tokenManager: UfrgsTokenManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (tokenManager.isLoggedIn(this)) {
            MainActivity.start(this)
        } else {
            LoginActivity.start(this)
        }

        finish()

    }
}