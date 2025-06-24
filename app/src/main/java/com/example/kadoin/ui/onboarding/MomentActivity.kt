package com.example.kadoin.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class MomentActivity : AppCompatActivity() {

    private lateinit var momentAdapter: MomentAdapter
    private lateinit var rvMoments: RecyclerView
    private lateinit var btnLanjutkan: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moment)

        initViews()
        setupMoments()
        setupClickListeners()
    }

    private fun initViews() {
        rvMoments = findViewById(R.id.rv_moments)
        btnLanjutkan = findViewById(R.id.btn_lanjutkan)
        btnBack = findViewById(R.id.btn_back)
    }

    private fun setupMoments() {
        val moments = listOf(
            MomentItem("Ulang Tahun", "Rayakan hari spesial", "ic_birthday"),
            MomentItem("Pernikahan", "Momen bahagia selamanya", "ic_wedding"),
            MomentItem("Wisuda", "Merayakan kesuksesan", "ic_graduation"),
            MomentItem("Anniversary", "Perayaan cinta", "ic_anniversary"),
            MomentItem("Baby Shower", "Menyambut si kecil", "ic_baby_shower"),
            MomentItem("Lamaran", "Awal kisah cinta", "ic_proposal")
        )

        momentAdapter = MomentAdapter(moments) { moment ->
            // Handle moment selection
        }

        rvMoments.apply {
            layoutManager = GridLayoutManager(this@MomentActivity, 2)
            adapter = momentAdapter
        }
    }

    private fun setupClickListeners() {
        btnLanjutkan.setOnClickListener {
            startActivity(Intent(this, BudgetActivity::class.java))
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
