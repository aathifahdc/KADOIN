package com.example.kadoin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class MomenSpesialAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<MomenSpesialAdapter.MomenSpesialViewHolder>() {

    inner class MomenSpesialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_momen_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_momen_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_momen_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price

            // Set image based on item type
            val imageRes = when (item.image) {
                "momen_1" -> R.drawable.iphone_promax
                "momen_2" -> R.drawable.headphone
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomenSpesialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_momen_spesial, parent, false)
        return MomenSpesialViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomenSpesialViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
