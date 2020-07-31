package tra.wor.bookreaderwithkotlin.pojo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [favorite::class], version = 2, exportSchema = false)
abstract class favoritedatabase : RoomDatabase() {
    abstract fun favoriteDao(): favoriteDao


    companion object{
        @Volatile

        private var instance: favoritedatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                favoritedatabase::class.java, "favorite.data")
                .allowMainThreadQueries()
                .build()
    }
}