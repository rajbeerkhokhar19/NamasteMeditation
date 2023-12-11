package com.demo.namastemeditation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.namastemeditation.R
import com.demo.namastemeditation.database.RatingsEntity

class RatingsListAdapter(
    val itemList: ArrayList<RatingsEntity>,
    private val onItemSelectedListener: (RatingsEntity) -> Unit
) :
    RecyclerView.Adapter<RatingsListAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RatingsListAdapter.ModelViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ratings_review, parent, false)
        return ModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: RatingsListAdapter.ModelViewHolder, position: Int) {

        Glide.with(holder.image.context)
            .load(
                itemList[position].image
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(holder.image)
        holder.name.text = itemList[position].name
        holder.description.text = itemList[position].description
        holder.ratingBar.rating = itemList[position].ratings ?: 0.0F
        holder.constraintLayout.setOnClickListener {
            onItemSelectedListener(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var constraintLayout: ConstraintLayout
        var image: ImageView
        var name: TextView
        var description: TextView
        var ratingBar: RatingBar

        init {
            constraintLayout = itemView.findViewById(
                R.id.constraintLayout
            ) as ConstraintLayout
            image = itemView.findViewById(R.id.ivImage) as ImageView
            name = itemView.findViewById(R.id.tvName) as TextView
            description = itemView.findViewById(R.id.tvDescription) as TextView
            ratingBar = itemView.findViewById(R.id.ratingBar) as RatingBar
        }

    }


}