package br.ufrgs.brique.presentation.ui.login

import br.ufrgs.brique.presentation.ui.BaseView

interface LoginView : BaseView {
    fun startMain()
    fun startPasswordRecovery()
    fun getUserCard(): String
    fun getUserPassword(): String
}