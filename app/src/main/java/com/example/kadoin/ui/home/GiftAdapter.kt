package com.example.kadoin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.databinding.ItemGiftBinding

class GiftAdapter(
    private val gifts: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {

    inner class GiftViewHolder(private val binding: ItemGiftBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gift: GiftItem) {
            binding.tvGiftName.text = gift.name
            binding.tvGiftPrice.text = gift.price

            // Set placeholder image
            val imageRes = when (gift.image) {
                "gift_1" -> R.drawable.jam_tangan
                "gift_2" -> R.drawable.headphone
                "gift_3" -> R.drawable.parfum_pria
                else -> R.drawable.sample_gift_box
            }
            binding.ivGiftImage.setImageResource(imageRes)

            binding.root.setOnClickListener {
                onItemClick(gift)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val binding = ItemGiftBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GiftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
        holder.bind(gifts[position])
    }

    override fun getItemCount() = gifts.size
}
