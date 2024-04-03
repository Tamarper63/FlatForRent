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
import data.AdAdapter
import data.AdModel
import data.OnItemClickListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FeedFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentSecondBinding? = null
    val adModelArrayList: ArrayList<AdModel> = ArrayList()
    private lateinit var adAdapter: AdAdapter // Declare adAdapter as a class-level variable

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file for FragmentSecondBinding
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        // Access views from FragmentSecondBinding


        // Inflate the layout XML file containing the unLike button
        val otherLayout = inflater.inflate(R.layout.item_card_view, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adRV = view.findViewById<RecyclerView>(R.id.rcId)

        // Here, we have created new array list and added data to it
        adModelArrayList.add(AdModel("65", "5000"))
        adModelArrayList.add(AdModel("85", "6000"))
        adModelArrayList.add(AdModel("77", "7000"))
        adModelArrayList.add(AdModel("88", "8000"))
        adModelArrayList.add(AdModel("90", "8000"))

        adAdapter = AdAdapter(this, adModelArrayList, this)

        adRV.layoutManager = LinearLayoutManager(requireContext())

        adRV.adapter = adAdapter

        binding.bottomButton.setOnClickListener {
            findNavController().navigate(R.id.action_feed_to_favoritFlat)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemClick(position: Int) {
        val clickedItem = adModelArrayList[position]
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Thank you for your feedback!")
            .setMessage("We will review it carefully")
        builder.create().show()
        val favoriteItems: ArrayList<AdModel> = ArrayList()
        favoriteItems.add(clickedItem)
    }

    override fun onImageClick(position: Int) {
        adModelArrayList.removeAt(position)

        // Notify the adapter that an item has been removed
        adAdapter.notifyItemRemoved(position)
    }

    override fun onLikeClick(position: Int) {
        val clickedItem = adModelArrayList[position]
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("This flat was added to your flats list")
        builder.create().show()
        val favoriteItems: ArrayList<AdModel> = ArrayList()
        favoriteItems.add(clickedItem)
    }

}