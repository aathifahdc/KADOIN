package com.example.kadoin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class BudgetAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

    inner class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_budget_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_budget_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_budget_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price

            // Set image based on item type
            val imageRes = when (item.image) {
                "budget_1" -> R.drawable.tws
                "budget_2" -> R.drawable.smarthome
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_budget, parent, false)
        return BudgetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
