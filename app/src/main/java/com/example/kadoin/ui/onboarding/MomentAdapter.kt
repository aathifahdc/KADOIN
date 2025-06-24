package com.example.kadoin.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class MomentAdapter(
    private val moments: List<MomentItem>,
    private val onItemClick: (MomentItem) -> Unit
) : RecyclerView.Adapter<MomentAdapter.MomentViewHolder>() {

    private var selectedPosition = -1

    inner class MomentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivMomentIcon: ImageView = itemView.findViewById(R.id.iv_moment_icon)
        private val tvMomentName: TextView = itemView.findViewById(R.id.tv_moment_name)
        private val tvMomentDescription: TextView = itemView.findViewById(R.id.tv_moment_description)

        fun bind(moment: MomentItem, position: Int) {
            tvMomentName.text = moment.name
            tvMomentDescription.text = moment.description

            // Set icon based on moment type
            val iconRes = when (moment.icon) {
                "ic_birthday" -> R.drawable.ic_birthday
                "ic_wedding" -> R.drawable.ic_wedding
                "ic_graduation" -> R.drawable.ic_graduation
                "ic_anniversary" -> R.drawable.ic_anniversary
                "ic_baby_shower" -> R.drawable.ic_baby_shower
                "ic_proposal" -> R.drawable.ic_proposal
                else -> R.drawable.ic_gift
            }
            ivMomentIcon.setImageResource(iconRes)

            // Handle selection state
            itemView.isSelected = position == selectedPosition

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onItemClick(moment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_moment, parent, false)
        return MomentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomentViewHolder, position: Int) {
        holder.bind(moments[position], position)
    }

    override fun getItemCount() = moments.size
}

data class MomentItem(
    val name: String,
    val description: String,
    val icon: String
)
