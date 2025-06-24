package com.example.kadoin.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kadoin.databinding.ActivityRelationshipBinding

class RelationshipActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRelationshipBinding
    private lateinit var relationshipAdapter: RelationshipAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        val relationships = listOf(
            RelationshipItem("Pasangan", "ic_couple"),
            RelationshipItem("Keluarga", "ic_family"),
            RelationshipItem("Teman", "ic_friends"),
            RelationshipItem("Guru/Dosen", "ic_teacher"),
            RelationshipItem("Lainnya", "ic_others")
        )
        
        relationshipAdapter = RelationshipAdapter(relationships) { relationship ->
            // Handle relationship selection
        }
        
        binding.rvRelationships.apply {
            layoutManager = GridLayoutManager(this@RelationshipActivity, 2)
            adapter = relationshipAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.btnLanjutkan.setOnClickListener {
            startActivity(Intent(this, MomentActivity::class.java))
        }
        
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}

data class RelationshipItem(
    val name: String,
    val icon: String
)
