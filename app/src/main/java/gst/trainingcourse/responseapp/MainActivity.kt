package gst.trainingcourse.responseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import gst.trainingcourse.responseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view=binding.root
        setContentView(view)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController :NavController =navHostFragment.navController

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =findNavController(R.id.myNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()

    }
}