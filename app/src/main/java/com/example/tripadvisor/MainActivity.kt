package com.example.tripadvisor


import kotlinx.coroutines.*
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tripadvisor.R.id
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    private val PREFS_NAME = "MyPrefs"
    private val KEY_DATA_FETCHED = "data_fetched"
    private val KEY_LAST_FETCH_TIME = "last_fetch_time"
    private val SESSION_TIMEOUT_MS = 60 * 60 * 1000 // 1 hour in milliseconds
    private var Id:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Id = intent.getIntExtra("ID", -1)
        Log.e("TAG", " Id: $Id")
        if (Id == 1) {

            replaceFragment(AccountFragment())
            true
        }

        else if(Id==3)
        {
            startActivity(Intent(this, AddMissingPlaceActivity::class.java))
        }


        // Set default fragment
        replaceFragment(ExploreFragment())

        // Setup BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_explore -> {
                    val exploreFragment = ExploreFragment().apply {
                        arguments = Bundle().apply {
                            putInt("ID", 3)
                        }
                    } // Set the bundle as fragment arguments
                    replaceFragment(exploreFragment)
                    true
                }
                R.id.navigation_search -> {

                    replaceFragment(SearchFragment())
                    true
                }


                else -> false
            }
        }
    }



    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
