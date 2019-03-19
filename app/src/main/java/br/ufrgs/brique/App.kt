package br.ufrgs.brique

import android.app.Application
import android.content.Context
import br.ufrgs.brique.data.repository.network.ApiBuilder
import br.ufrgs.brique.data.repository.network.AppServices
import br.ufrgs.brique.domain.usecase.*
import br.ufrgs.brique.presentation.ui.edit.EditPresenter
import br.ufrgs.brique.presentation.ui.login.LoginPresenter
import br.ufrgs.brique.presentation.ui.main.MainPresenter
import br.ufrgs.brique.presentation.ui.main.board.BoardPresenter
import br.ufrgs.brique.presentation.ui.main.offered.OfferedPresenter
import br.ufrgs.cpd.coresdk.UfrgsSdk
import br.ufrgs.cpd.coresdk.token.UfrgsTokenManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        UfrgsSdk.initialize(this, false)
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidModule(this@App))
        bind<Context>() with instance(this@App)
        bind<ApiBuilder>() with singleton { ApiBuilder(instance(), instance()) }
        bind<AppServices>() with singleton { instance<ApiBuilder>().retrofit().create(AppServices::class.java) }
        bind<UfrgsTokenManager>() with singleton { UfrgsTokenManager() }
        bind<MainPresenter>() with provider { MainPresenter() }
        bind<OfferedPresenter>() with provider { OfferedPresenter(instance()) }
        bind<BoardPresenter>() with provider { BoardPresenter(instance()) }
        bind<LoginPresenter>() with provider { LoginPresenter(instance(), instance()) }
        bind<EditPresenter>() with provider { EditPresenter(instance(), instance()) }
        bind<MuralBensUseCase>() with singleton { MuralBensUseCaseImpl(instance()) }
        bind<OfferedBensUseCase>() with singleton { OfferedBensUseCaseImpl(instance()) }
        bind<GetBemImageUseCase>() with singleton { GetBemImageUseCaseImpl(instance(), instance()) }
    }
}