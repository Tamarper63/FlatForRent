package com.example.flateforrentdemo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flateforrentdemo.databinding.FragmentSecondBinding
import data.Apatmentdapter
import data.ApartmentModel
import data.OnItemClickListener
import db.SharedPreferencesManager

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FeedFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentSecondBinding? = null
    val apartmentModelArrayList: ArrayList<ApartmentModel> = ArrayList()
    private lateinit var apatmentdapter: Apatmentdapter // Declare adAdapter as a class-level variable
    private var favoriteItems: ArrayList<ApartmentModel> = ArrayList()
    private lateinit var sharedPreferencesManager: SharedPreferencesManager


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file for FragmentSecondBinding
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        sharedPreferencesManager = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adRV = view.findViewById<RecyclerView>(R.id.rcId)

        favoriteItems.addAll(sharedPreferencesManager.getFavoriteItems())

        // Here, we have created new array list and added data to it
        apartmentModelArrayList.add(ApartmentModel("65", "5000"))
        apartmentModelArrayList.add(ApartmentModel("85", "6000"))
        apartmentModelArrayList.add(ApartmentModel("77", "7000"))
        apartmentModelArrayList.add(ApartmentModel("88", "8000"))
        apartmentModelArrayList.add(ApartmentModel("90", "8000"))

        apatmentdapter = Apatmentdapter(this, apartmentModelArrayList, this)

        adRV.layoutManager = LinearLayoutManager(requireContext())

        adRV.adapter = apatmentdapter

        binding.myListOfFlatsButton.setOnClickListener {
            findNavController().navigate(R.id.action_feed_to_favoritFlat)
        }

        binding.addFlatButtonId.setOnClickListener {
            findNavController().navigate(R.id.feed_to_add_flat)
        }

        binding.myListOfFlatsButton.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelableArrayList("favoriteItems", favoriteItems)
            }
            findNavController().navigate(R.id.action_feed_to_favoritFlat, bundle)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemClick(position: Int) {
        val clickedItem = apartmentModelArrayList[position]
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Thank you for your feedback!")
            .setMessage("We will review it carefully")
        builder.create().show()
        val favoriteItems: ArrayList<ApartmentModel> = ArrayList()
        favoriteItems.add(clickedItem)
    }

    override fun onUnlikeClick(position: Int) {
        apartmentModelArrayList.removeAt(position)
        val removedItem = apartmentModelArrayList.removeAt(position)
        // Notify the adapter that an item has been removed
        apatmentdapter.notifyItemRemoved(position)
        favoriteItems.remove(removedItem)
        sharedPreferencesManager.saveFavoriteItems(favoriteItems)

    }

    override fun onLikeClick(position: Int) {
        val clickedItem = apartmentModelArrayList[position]
        val builder = AlertDialog.Builder(requireContext())
        favoriteItems.add(clickedItem)
        sharedPreferencesManager.saveFavoriteItems(favoriteItems)
        apartmentModelArrayList.removeAt(position)
        apatmentdapter.notifyItemRemoved(position)
        builder.setTitle("This flat was added to your favorite flat list")
        builder.create().show()
    }

    override fun onAddFlatClick() {
        TODO("Not yet implemented")
    }
}