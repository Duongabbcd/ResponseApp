package gst.trainingcourse.responseapp.model

import android.content.Context
import android.provider.SyncStateContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Subject::class],version = 1,exportSchema = false)
abstract class SubjectDatabase :RoomDatabase(){
   abstract fun subjectDAO() :SubjectDAO

   companion object {
       @Volatile
       private var INSTANCE: SubjectDatabase? = null


       fun getDatabase(context: Context): SubjectDatabase = INSTANCE ?: synchronized(this) {
       INSTANCE ?: Room.databaseBuilder(
       context.applicationContext,
       SubjectDatabase::
       class.java,
       "subject_database"
       )
           .build().also{
           INSTANCE = it
       }

     }
   }
}