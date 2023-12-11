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
import com.demo.namastemeditation.home.model.ForYouResponse

class ForYouListAdapter(
    val itemList: ArrayList<ForYouResponse>,
    private val onItemSelectedListener: (ForYouResponse) -> Unit
) :
    RecyclerView.Adapter<ForYouListAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForYouListAdapter.ModelViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_for_you, parent, false)
        return ModelViewHolder(v)
    }

    override fun onBindViewHolder(holder: ForYouListAdapter.ModelViewHolder, position: Int) {

        Glide.with(holder.image.context)
            .load(
                itemList[position].image
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(holder.image)
        holder.title.text = itemList[position].title
        holder.time.text = itemList[position].time
        holder.type.text = itemList[position].type
        holder.ratingBar.rating = itemList[position].rating.toFloat()
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
        var title: TextView
        var time: TextView
        var type: TextView
        var ratingBar: RatingBar

        init {
            constraintLayout = itemView.findViewById(
                R.id.constraintLayout
            ) as ConstraintLayout
            image = itemView.findViewById(R.id.ivImage) as ImageView
            title = itemView.findViewById(R.id.tvTitle) as TextView
            time = itemView.findViewById(R.id.tvTime) as TextView
            type = itemView.findViewById(R.id.tvType) as TextView
            ratingBar = itemView.findViewById(R.id.ratingBar) as RatingBar
        }

    }


}