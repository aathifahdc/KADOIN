package com.example.kadoin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class BudgetHomeAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<BudgetHomeAdapter.BudgetViewHolder>() {

    inner class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_budget_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_budget_name)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_budget_rating)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_budget_reviews)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_budget_price)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btn_budget_favorite)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price
            tvRating.text = item.rating.toString()
            tvReviews.text = item.reviews

            // Set image based on item type
            val imageRes = when (item.image) {
                "earbuds_premium" -> R.drawable.tws
                "smart_home_hub" -> R.drawable.smarthome
                "fitness_tracker" -> R.drawable.fitness
                "power_bank_premium" -> R.drawable.powerbank
                "budget_1" -> R.drawable.tws
                "budget_2" -> R.drawable.smarthome
                "budget_3" -> R.drawable.fitness
                "budget_4" -> R.drawable.powerbank
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            btnFavorite.setOnClickListener {
                // Handle favorite toggle
            }

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_budget_home, parent, false)
        return BudgetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
