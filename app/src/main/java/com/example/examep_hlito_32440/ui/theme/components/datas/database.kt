package com.example.examep_hlito_32440.ui.theme.components.datas

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

// 1. A ENTIDADE (Tabela)
@Entity(tableName = "Lista_ODS") // Mudar nome da tabela
data class DadoEntity(
    @PrimaryKey
    val code: Int,
    val title: String,
    val description: String
)

// 2. O DAO (Consultas)
@Dao
interface DadoDao {
    @Query("SELECT * FROM Lista_ODS")
    fun getAll(): Flow<List<DadoEntity>> // Flow para UI reativa

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dados: List<DadoEntity>)

    @Insert
    suspend fun insert(dado: DadoEntity)

    @Delete
    suspend fun delete(dado: DadoEntity)
}

// 3. A BASE DE DADOS (Singleton)
@Database(entities = [DadoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): DadoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "exame_db")
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}