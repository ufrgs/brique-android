package br.ufrgs.brique.presentation.ui

interface BaseView {
    fun showError(messageId: Int)
    fun showError(message: String)
}