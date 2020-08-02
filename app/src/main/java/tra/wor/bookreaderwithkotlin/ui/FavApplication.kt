package tra.wor.bookreaderwithkotlin.ui

import android.app.Application
import com.vinay.shoppinglist.data.repositories.FavRepository
import com.vinay.shoppinglist.ui.shoppinglist.FavViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tra.wor.bookreaderwithkotlin.pojo.favoritedatabase


class FavApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@FavApplication))
        bind() from singleton { favoritedatabase(instance()) }
        bind() from singleton {
            FavRepository(
                instance()
            )
        }
        bind() from provider {
            FavViewModelFactory(
                instance()
            )
        }
    }
}