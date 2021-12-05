package gst.trainingcourse.responseapp.fragment.list

import android.app.AlertDialog
import android.database.Cursor
import android.os.Bundle
import android.os.RecoverySystem
import android.view.*
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.responseapp.R
import gst.trainingcourse.responseapp.adapter.SubjectAdapter
import gst.trainingcourse.responseapp.content_provider.SampleContentProvider
import gst.trainingcourse.responseapp.databinding.FragmentListBinding
import gst.trainingcourse.responseapp.fragment.update.UpdateFragmentDirections
import gst.trainingcourse.responseapp.model.Subject
import gst.trainingcourse.responseapp.model.SubjectViewModel

class ListFragment : Fragment() ,LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding :FragmentListBinding ?= null
    private val binding get() = _binding!!

    private val LOADER_SUBJECT =1

    private lateinit var mSubjectViewModel :SubjectViewModel
    private lateinit var subjectAdapter: SubjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container, false)
         val viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        binding.viewModel =viewModel
        binding.lifecycleOwner=this

        viewModel.toAddFragment.observe(viewLifecycleOwner, Observer {
            this.findNavController()
                .navigate(ListFragmentDirections.listToAddFragment())
        })

        subjectAdapter = SubjectAdapter()
        binding.recycler1.adapter=subjectAdapter
        binding.recycler1.layoutManager =LinearLayoutManager(requireContext())

        //SubjectViewModel
//        loaderManager.initLoader(LOADER_SUBJECT,null,this)



      //Add Menu
        setHasOptionsMenu(true)

        return binding.root
    }
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
//    return CursorLoader(requireContext(),SampleContentProvider.URI_SUBJECT,"subject_table",null,null,null)
        TODO("Not yet implemented")
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_delete ->{
                deleteAllSubjects()
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }


    }

    private fun deleteAllSubjects() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
                _,_ -> mSubjectViewModel.deleteAllSubjects()
            Toast.makeText(requireContext(),"Deleted Successfully ! ",
                Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){
                _,_ ->
        }
        builder.setTitle("Delete All schedule ?")
        builder.setMessage("Are you sure you want to delete all schedules ?")
        builder.create().show()
    }




}

