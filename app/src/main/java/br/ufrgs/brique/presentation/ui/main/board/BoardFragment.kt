package br.ufrgs.brique.presentation.ui.main.board

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ufrgs.brique.R
import br.ufrgs.brique.data.repository.network.response.Bem
import br.ufrgs.brique.data.repository.network.response.Meta
import br.ufrgs.brique.presentation.custom.EndlessRecyclerViewScrollListener
import br.ufrgs.brique.presentation.ui.RootFragment
import kotlinx.android.synthetic.main.generic_recyclerview.*
import org.jetbrains.anko.toast
import org.kodein.di.generic.instance

class BoardFragment : RootFragment<BoardView>(), BoardView {
    override val layoutResourceId = R.layout.generic_recyclerview
    override val presenter: BoardPresenter by instance()
    private val adapter: BoardAdapter by lazy { BoardAdapter() }
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var maxPages = 1

    override fun initializeUI() {
        setupRecyclerView()
    }

    override fun initializePresenter() {
        presenter.start(this)
    }

    override fun updateList(meta: Meta, list: List<Bem>) {
        maxPages = meta.pageCount
        adapter.updateList(meta, list)
    }

    override fun showError(messageId: Int) {
        activity?.toast(messageId)
    }

    override fun showError(message: String) {
        activity?.toast(message)
    }

    private fun setupRecyclerView() {
        recyclerview.setHasFixedSize(true)
        val llm = LinearLayoutManager(activity)
        scrollListener = object : EndlessRecyclerViewScrollListener(llm) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (page < maxPages) {
                    presenter.fetchBens(page)
                }
            }
        }

        recyclerview.layoutManager = llm
        recyclerview.addOnScrollListener(scrollListener)
        recyclerview.adapter = adapter
    }

}