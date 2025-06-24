package com.example.kadoin.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.databinding.ItemRelationshipBinding

class RelationshipAdapter(
    private val relationships: List<RelationshipItem>,
    private val onItemClick: (RelationshipItem) -> Unit
) : RecyclerView.Adapter<RelationshipAdapter.RelationshipViewHolder>() {

    private var selectedPosition = -1

    inner class RelationshipViewHolder(private val binding: ItemRelationshipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(relationship: RelationshipItem, position: Int) {
            binding.tvRelationshipName.text = relationship.name
            
            // Set icon based on relationship type
            val iconRes = when (relationship.icon) {
                "ic_couple" -> com.example.kadoin.R.drawable.ic_couple
                "ic_family" -> com.example.kadoin.R.drawable.ic_family
                "ic_friends" -> com.example.kadoin.R.drawable.ic_friends
                "ic_teacher" -> com.example.kadoin.R.drawable.ic_teacher
                else -> com.example.kadoin.R.drawable.ic_others
            }
            binding.ivRelationshipIcon.setImageResource(iconRes)
            
            // Handle selection state
            binding.root.isSelected = position == selectedPosition
            
            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onItemClick(relationship)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelationshipViewHolder {
        val binding = ItemRelationshipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelationshipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelationshipViewHolder, position: Int) {
        holder.bind(relationships[position], position)
    }

    override fun getItemCount() = relationships.size
}
