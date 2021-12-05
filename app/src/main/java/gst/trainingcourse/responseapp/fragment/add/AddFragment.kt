package gst.trainingcourse.responseapp.fragment.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import gst.trainingcourse.responseapp.R
import gst.trainingcourse.responseapp.databinding.FragmentAddBinding
import gst.trainingcourse.responseapp.model.Subject
import gst.trainingcourse.responseapp.model.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(){
    private  var _binding :FragmentAddBinding ?= null
    private val binding get() =  _binding!!
    val cal :Calendar = Calendar.getInstance()
   val day =cal.get(Calendar.DAY_OF_MONTH)
    val month =cal.get(Calendar.MONTH)
    val year =cal.get(Calendar.YEAR)


    private lateinit var mSubjectViewModel :SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mSubjectViewModel =ViewModelProvider(this).get(SubjectViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        binding.pickDate.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(),
                { _ :DatePicker, mYear :Int, mMonth :Int, mDay :Int
                    -> binding.edtStartDate.text = "$mDay/$mMonth/$mYear"
                },year,month,day )
            dpd.show()

        }

        binding.pickStartTime.setOnClickListener {
            getTime(binding.edtStartTime,binding.pickStartTime,requireContext())
        }

        binding.pickEndTime.setOnClickListener {
            getTime(binding.edtEndTime,binding.pickEndTime ,requireContext())
        }

        return binding.root
    }

    fun getTime(textView : TextView, button: Button, context:Context){
        val timeSetListener =TimePickerDialog.OnTimeSetListener { timePicker, mHour :Int, mMinute :Int ->
                cal.set(Calendar.HOUR_OF_DAY,mHour)
                cal.set(Calendar.MINUTE,mMinute)

            textView.text=SimpleDateFormat("HH:mm").format(cal.time)

        }
        button.setOnClickListener {
            TimePickerDialog(context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
    }

    private fun insertDataToDatabase() {
       val name = binding.edtName.text.toString()
        val date =binding.edtStartDate.text.toString()
        val startTime =binding.edtStartTime.text.toString()
        val endTime =binding.edtEndTime.text.toString()
        if(inputCheck(name,date,startTime,endTime)){
            val subject = Subject(0,name,date,startTime,endTime)
            //add data to database
            mSubjectViewModel.addSubject(subject)
            Toast.makeText(requireContext(),"Added Successfully ! ",Toast.LENGTH_SHORT).show()

            findNavController().navigate(AddFragmentDirections.addToListFragment())

        }else{
            Toast.makeText(requireContext(),"Please fill out all fields ! ",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name :String ,date :String ,startTime:String,endTime :String) :Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(date) && TextUtils.isEmpty(startTime) && TextUtils.isEmpty(endTime))
    }



}