package gst.trainingcourse.responseapp.fragment.update

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import gst.trainingcourse.responseapp.R
import gst.trainingcourse.responseapp.databinding.FragmentUpdateBinding
import gst.trainingcourse.responseapp.fragment.add.AddFragmentDirections
import gst.trainingcourse.responseapp.model.Subject
import gst.trainingcourse.responseapp.model.SubjectViewModel
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding :FragmentUpdateBinding ?= null
    private val binding get() = _binding!!

    private lateinit var mSubjectViewModel :SubjectViewModel

    val cal : Calendar = Calendar.getInstance()
    val day =cal.get(Calendar.DAY_OF_MONTH)
    val month =cal.get(Calendar.MONTH)
    val year =cal.get(Calendar.YEAR)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater ,container,false)

        mSubjectViewModel =ViewModelProvider(this)[SubjectViewModel::class.java]


        binding.updateEdtName.setText(args.currentSubject.name)
        binding.updateEdtStartDate.setText(args.currentSubject.date)
        binding.updateEdtStartTime.setText(args.currentSubject.startTime)
        binding.updateEdtEndTime.setText(args.currentSubject.endTime)

        binding.updatePickDate.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(),
                { _ : DatePicker, mYear :Int, mMonth :Int, mDay :Int
                    -> binding.updateEdtStartDate.text = "$mDay/$mMonth/$mYear"
                },year,month,day )
            dpd.show()

        }

        binding.updatePickStartTime.setOnClickListener {
            getTime(binding.updateEdtStartTime,binding.updatePickStartTime,requireContext())
        }

        binding.updatePickStartTime.setOnClickListener {
            getTime(binding.updateEdtEndTime,binding.updatePickEndTime ,requireContext())
        }

        binding.btnUpdate.setOnClickListener {
             updateItem()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun getTime(textView : TextView, button: Button, context: Context){
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, mHour :Int, mMinute :Int ->
            cal.set(Calendar.HOUR_OF_DAY,mHour)
            cal.set(Calendar.MINUTE,mMinute)

            textView.text= SimpleDateFormat("HH:mm").format(cal.time)

        }
        button.setOnClickListener {
            TimePickerDialog(context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
    }

    private fun updateItem() {
        val name = binding.updateEdtName.text.toString()
        val date =binding.updateEdtStartDate.text.toString()
        val startTime =binding.updateEdtStartTime.text.toString()
        val endTime =binding.updateEdtEndTime.text.toString()
        if(inputCheck(name,date,startTime,endTime)){
            val updateCurrentSubject = Subject(args.currentSubject.id,name,date,startTime,endTime)
            //add data to database
            mSubjectViewModel.updateSubject(updateCurrentSubject)
            Toast.makeText(requireContext(),"Updated Successfully ! ", Toast.LENGTH_SHORT).show()

            findNavController().navigate(UpdateFragmentDirections.updateToListFragment())

        }else{
            Toast.makeText(requireContext(),"Please fill out all fields ! ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name :String ,date :String ,startTime:String,endTime :String) :Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(date) && TextUtils.isEmpty(startTime) && TextUtils.isEmpty(endTime))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return when(item.itemId){
          R.id.menu_delete ->{
              deleteUser()
              true
          }
          else ->   super.onOptionsItemSelected(item)
      }


    }

    private fun deleteUser() {
     val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
            _,_ -> mSubjectViewModel.deleteSubject(args.currentSubject)
            Toast.makeText(requireContext(),"Deleted ${args.currentSubject.name} Successfully ! ",Toast.LENGTH_SHORT).show()
            findNavController().navigate(UpdateFragmentDirections.updateToListFragment())
        }
        builder.setNegativeButton("No"){
            _,_ ->
        }
        builder.setTitle("Delete ${args.currentSubject.name} begun on ${args.currentSubject.date} ?")
        builder.setMessage("Are you sure you want to delete ${args.currentSubject.name} ?")
        builder.create().show()
    }
}