package com.example.flateforrentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.Apatmentdapter
import data.ApartmentModel
import data.OnItemClickListener


class MyFavoritFlats : Fragment() {

    private var favoriteItems: ArrayList<ApartmentModel>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Apatmentdapter
    private val listener = object : OnItemClickListener {
        override fun onItemClick(position: Int) {
            // Handle item click event
            // For example, show a toast message or navigate to another fragment
        }

        override fun onUnlikeClick(position: Int) {
            // Handle image click event
            // For example, show a dialog or perform some action
        }

        override fun onLikeClick(position: Int) {
            // For example, show a dialog or perform some action

        }

        override fun onAddFlatClick() {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_favorit_flats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rcId)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Retrieve favoriteItems list from arguments Bundle
        favoriteItems = arguments?.getParcelableArrayList<ApartmentModel>("favoriteItems")

        // Initialize and set adapter to RecyclerView
        adapter = Apatmentdapter(FeedFragment(), favoriteItems ?: ArrayList(), listener)
        recyclerView.adapter = adapter
    }

}