package gst.trainingcourse.responseapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subject_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val name :String,
    val date : String,
    val startTime:String ,
    val endTime :String
) :Parcelable