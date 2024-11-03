package com.example.tripadvisor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {


    private lateinit var searchBox: EditText
    private lateinit var hotelRecyclerView: RecyclerView
    private lateinit var restaurantRecyclerView: RecyclerView

    private lateinit var emptyImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)


        searchBox = view.findViewById(R.id.search_box)
        hotelRecyclerView = view.findViewById(R.id.hotelRecycleView)
        restaurantRecyclerView = view.findViewById(R.id.resturantRecycleView)
        emptyImageView = view.findViewById(R.id.iv_empty)

        hotelRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        restaurantRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val searchID = arguments?.getInt("searchID")

        when (searchID) {
            11 -> {
                searchBox.hint = "Search Hotel"
                hotelRecyclerView.visibility = View.VISIBLE
                restaurantRecyclerView.visibility = View.GONE
            }

            else -> {
                searchBox.hint = "Search Restaurant"
                restaurantRecyclerView.visibility = View.VISIBLE
                hotelRecyclerView.visibility = View.GONE
            }
        }


        return TODO("Provide the return value")
    }
        }
