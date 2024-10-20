package com.example.tripadvisor


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView


    private var receivedId: Int? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmnet_explore, container, false)

        //Grid layout
        arguments?.let {
            receivedId = it.getInt("ID", -1)  // Default value is -1 if not found
        }

        val hotelbtn=view.findViewById<Button>(R.id.hotelExplore)
        val resturantbtn=view.findViewById<Button>(R.id.restruantExplore)
        val keepExplore=view.findViewById<Button>(R.id.keepExplore)

        hotelbtn.setOnClickListener {

            navigateToSearchFragment(11)
        }
        resturantbtn.setOnClickListener {
            navigateToSearchFragment(12)
        }
        keepExplore.setOnClickListener {
            startActivity(Intent(context,webView::class.java))
        }



        // Initialize RecyclerView





        return view
    }
    private fun navigateToSearchFragment(searchID: Int) {
        val searchFragment = SearchFragment()

        val bundle = Bundle()
        bundle.putInt("searchID", searchID) // Pass the searchID to the fragment

        // Set the arguments for the fragment
        searchFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, searchFragment)
            .addToBackStack(null)
            .commit()
    }
}
