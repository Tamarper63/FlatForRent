package data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flateforrentdemo.R
import com.example.flateforrentdemo.FeedFragment

class AdAdapter(
    private val context: FeedFragment,
    private val addModelArrayList: ArrayList<AdModel>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AdAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // to inflate the layout for each item of recycler view.
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_view, parent, false)
        return ViewHolder(view, listener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // to set data to textview and imageview of each card layout
        val model: AdModel = addModelArrayList[position]
        holder.flatid.setText("Flat ID " + model.getFlatId())
        holder.flatPrice.setText("Price " + model.getFlatPrice())
    }

    override fun getItemCount(): Int {
        // this method is used for showing number of card items in recycler view.
        return addModelArrayList.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    class ViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val flatid: TextView = itemView.findViewById(R.id.flateId)
        val flatPrice: TextView = itemView.findViewById(R.id.priceId)

        init {
            itemView.findViewById<View>(R.id.myButton).setOnClickListener(this)
            itemView.findViewById<View>(R.id.starId).setOnClickListener(this)

            // Set up click listener for the unLike view
            itemView.findViewById<View>(R.id.unLike).setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                // Check which view was clicked
                when (v?.id) {
                    R.id.myButton -> listener.onImageClick(position) // Button clicked
                    R.id.unLike -> listener.onItemClick(position) // unLike view clicked
                    R.id.starId -> listener.onLikeClick(position) // unLike view clicked
                }
            }
        }
    }
}

