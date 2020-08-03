package riyan.subekti.retrofitkotlinmvvm

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import riyan.subekti.retrofitkotlinmvvm.data.db.AppDatabase
import riyan.subekti.retrofitkotlinmvvm.data.network.MyApi
import riyan.subekti.retrofitkotlinmvvm.data.network.NetworkConnectionInterceptor
import riyan.subekti.retrofitkotlinmvvm.data.repositories.UserRepository
import riyan.subekti.retrofitkotlinmvvm.ui.auth.AuthViewModelFactory

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from  singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }


}