package br.ufrgs.brique.presentation.ui


abstract class BasePresenter<V : BaseView> {

    var view: V? = null

    fun start(v: V?) {
        this.view = v
        if (v == null) {
            throw RuntimeException()
        }
        this.initialize()
    }

    abstract fun initialize()

    abstract fun pause()

    abstract fun stop()

    abstract fun destroy()

}