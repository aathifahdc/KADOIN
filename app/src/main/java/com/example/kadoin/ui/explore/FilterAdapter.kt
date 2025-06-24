package com.example.kadoin.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class FilterAdapter(
    private val filters: List<String>,
    private val onFilterClick: (String) -> Unit
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private var selectedPosition = 0

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFilter: TextView = itemView.findViewById(R.id.tv_filter)

        fun bind(filter: String, position: Int) {
            tvFilter.text = filter

            // Handle selection state
            if (position == selectedPosition) {
                tvFilter.setBackgroundResource(R.drawable.filter_selected_background)
                tvFilter.setTextColor(itemView.context.getColor(R.color.white))
            } else {
                tvFilter.setBackgroundResource(R.drawable.filter_unselected_background)
                tvFilter.setTextColor(itemView.context.getColor(R.color.gray_text))
            }

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onFilterClick(filter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filters[position], position)
    }

    override fun getItemCount() = filters.size
}
