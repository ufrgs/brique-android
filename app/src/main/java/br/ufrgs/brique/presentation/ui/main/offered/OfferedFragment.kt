package br.ufrgs.brique.presentation.ui.main.offered

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufrgs.brique.R
import br.ufrgs.brique.data.OfferItem
import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.presentation.ui.RootFragment
import br.ufrgs.brique.presentation.ui.edit.EditActivity
import kotlinx.android.synthetic.main.fragment_offered.*
import org.jetbrains.anko.toast
import org.kodein.di.generic.instance

class OfferedFragment : RootFragment<OfferedView>(), OfferedView, OfferClick {

    override val layoutResourceId = R.layout.fragment_offered
    override val presenter: OfferedPresenter by instance()
    private val adapter: OfferedAdapter by lazy { OfferedAdapter(this) }

    override fun initializeUI() {
        setupRecyclerView()

        fab.setOnClickListener {
            activity?.let { act -> EditActivity.start(act) }
        }
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun updateList(list: List<Bem>) {
        adapter.updateList(list)
    }

    override fun onItemClick(bem: Bem) {
        activity?.let { EditActivity.start(this, bem) }
    }

    override fun showError(messageId: Int) {
        activity?.toast(messageId)
    }

    override fun showError(message: String) {
        activity?.toast(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == EditActivity.EDIT_REQUEST_CODE) {
            adapter.clear()
            presenter.initialize()
        }
    }

    private fun setupRecyclerView() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
    }

}