package gst.trainingcourse.responseapp.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.annotation.Nullable
import gst.trainingcourse.responseapp.model.SubjectDAO
import gst.trainingcourse.responseapp.model.SubjectDatabase

class SampleContentProvider : ContentProvider() {
    companion object{
        const val AUTHORITY = "gst.trainingcourse.responseapp.content_provider.SampleContentProvider"

        val URI_SUBJECT =Uri.parse("content://$AUTHORITY/subject_table")

        private const val CODE_SUBJECT_DIR =1

        private const val CODE_SUBJECT_ITEM =2

        private val MATCHER =UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(
                AUTHORITY,"subject_table", CODE_SUBJECT_DIR
            )
            MATCHER.addURI(
                AUTHORITY,"subject_table/*", CODE_SUBJECT_ITEM
            )
        }
    }

    override fun onCreate(): Boolean {
      return true
    }

    override fun query(
        uri: Uri,
        @Nullable projection: Array<String>?,
       @Nullable selection: String? ,
        @Nullable selectionArgs :Array<String>?,
        @Nullable sortOrder : String?
    ): Cursor? {
      val code = MATCHER.match(uri)
       if (code == CODE_SUBJECT_DIR || code == CODE_SUBJECT_ITEM) run {

           val subjectDAO: SubjectDAO = SubjectDatabase.getDatabase(context!!).subjectDAO()
           val cursor :Cursor =subjectDAO.readAllData()
           cursor.setNotificationUri(context!!.contentResolver,uri)
           return cursor
       } else{
            throw IllegalAccessException("Unknown URI : $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when(MATCHER.match(uri)){
            CODE_SUBJECT_DIR -> "vnd.android.cursir.dir/$AUTHORITY.subject_table"
            CODE_SUBJECT_ITEM -> "vnd.android.cursir.item/$AUTHORITY.subject_table"
            else -> throw IllegalAccessException("Unknown URI :$uri")
        }
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}