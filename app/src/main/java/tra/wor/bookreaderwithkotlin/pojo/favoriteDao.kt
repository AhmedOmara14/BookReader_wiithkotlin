package tra.wor.bookreaderwithkotlin.pojo

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface favoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: favorite)

    @Update
    fun update(favorite: favorite)

    @Delete
    fun delete(favorite: favorite)

    @Query("DELETE FROM favorite_tab")
    fun deleteallrecord()

    @Query("SELECT * FROM favorite_tab")
    fun selectallrecord(): LiveData<List<favorite>>

}