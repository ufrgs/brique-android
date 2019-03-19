package br.ufrgs.brique.presentation.ui.main

import android.content.Context
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.ufrgs.brique.R
import br.ufrgs.brique.presentation.ui.RootActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import org.kodein.di.generic.instance

class MainActivity : RootActivity<MainView>(), MainView {
    override val layoutResourceId = R.layout.activity_main
    override val presenter: MainPresenter by instance()

    override fun initializeUI() {
        setupNavigation()
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun showError(messageId: Int) {
        bottomNavigationView.snackbar(messageId)
    }

    override fun showError(message: String) {
        bottomNavigationView.snackbar(message)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.mainFragment).navigateUp()

    private fun setupNavigation() {
        val navController = findNavController(R.id.mainFragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity<MainActivity>()
        }
    }
}
