package com.demo.namastemeditation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.namastemeditation.R
import com.demo.namastemeditation.search.model.SearchResponse

class SearchAdapter(
    itemList: ArrayList<SearchResponse>,
    private val onItemSelectedListener: (SearchResponse) -> Unit,
) :
    RecyclerView.Adapter<SearchAdapter.ModelViewHolder?>() {
    private var newList: ArrayList<SearchResponse>

    init {
        this.newList = itemList
    }

    fun filterList(list: ArrayList<SearchResponse>) {
        newList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ModelViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ModelViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {

        Glide.with(holder.image.context)
            .load(
                newList[position].image
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(holder.image)
        holder.title.text = newList[position].title
        holder.time.text = newList[position].time
        holder.type.text = newList[position].type
        holder.constraintLayout.setOnClickListener {
            onItemSelectedListener(newList[position])
        }
    }

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var constraintLayout: ConstraintLayout
        var image: ImageView
        var title: TextView
        var time: TextView
        var type: TextView


        init {
            constraintLayout = itemView.findViewById(
                R.id.constraintLayout
            ) as ConstraintLayout
            image = itemView.findViewById(R.id.ivImage) as ImageView
            title = itemView.findViewById(R.id.tvTitle) as TextView
            time = itemView.findViewById(R.id.tvTime) as TextView
            type = itemView.findViewById(R.id.tvType) as TextView
        }
    }
}