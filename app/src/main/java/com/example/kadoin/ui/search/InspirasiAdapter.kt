package com.example.kadoin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class InspirasiAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<InspirasiAdapter.InspirasiViewHolder>() {

    inner class InspirasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_inspirasi_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_inspirasi_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_inspirasi_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price

            // Set image based on item type
            val imageRes = when (item.image) {
                "inspirasi_1" -> R.drawable.parfum_mawar
                "inspirasi_2" -> R.drawable.jam_tangan
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspirasiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspirasi, parent, false)
        return InspirasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspirasiViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
