package gst.trainingcourse.responseapp.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.responseapp.databinding.ScheduleCardBinding
import gst.trainingcourse.responseapp.fragment.list.ListFragmentDirections
import gst.trainingcourse.responseapp.model.Subject
import kotlinx.android.synthetic.main.schedule_card.view.*

class SubjectAdapter:RecyclerView.Adapter<SubjectAdapter.MyViewHolder>(){
    private lateinit var mCursor : Cursor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectAdapter.MyViewHolder {
      val binding = ScheduleCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

       if(mCursor.moveToPosition(position)) {
           holder.itemView.mId.text =mCursor.getString(mCursor.getColumnIndexOrThrow("id"))
           holder.itemView.mSubjectName.text =mCursor.getString(mCursor.getColumnIndexOrThrow("name"))
           holder.itemView.mDate.text =mCursor.getString(mCursor.getColumnIndexOrThrow("date"))
           holder.itemView.mStart.text =mCursor.getString(mCursor.getColumnIndexOrThrow("startTime"))
           holder.itemView.mEnd.text =mCursor.getString(mCursor.getColumnIndexOrThrow("endTime"))

//           holder.itemView.rowLayout.setOnClickListener{
//               val action =ListFragmentDirections.listToUpdateFragment()
//               holder.itemView.findNavController().navigate(action)
//           }

       }


    }

    override fun getItemCount(): Int {
       return mCursor.count
    }

    class MyViewHolder( binding:ScheduleCardBinding) :RecyclerView.ViewHolder(binding.root) {}

    fun setData(cursor: Cursor){
       mCursor =cursor
        notifyDataSetChanged()
    }
}