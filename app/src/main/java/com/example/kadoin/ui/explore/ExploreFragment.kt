package com.example.kadoin.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class ExploreFragment : Fragment() {

    private lateinit var rvRecommendations: RecyclerView
    private lateinit var rvFilters: RecyclerView
    private lateinit var tvTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ExploreFragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ExploreFragment", "onViewCreated called")

        initViews(view)
        setupFilters()
        setupRecommendations()
    }

    private fun initViews(view: View) {
        rvRecommendations = view.findViewById(R.id.rv_recommendations)
        rvFilters = view.findViewById(R.id.rv_filters)
        tvTitle = view.findViewById(R.id.tv_title)
    }

    private fun setupFilters() {
        val filters = listOf("Semua", "Ulang Tahun", "Pernikahan", "Wisuda", "Anniversary")
        val filterAdapter = FilterAdapter(filters) { filter ->
            // Handle filter selection
        }

        rvFilters.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
        }
    }

    private fun setupRecommendations() {
        val recommendations = listOf(
            GiftItem("gift_1", "Kotak Hadiah Premium dengan Pita Elegan", "Rp 199.000", 4.8f, "(243)", "", "", "Terjual 243"),
            GiftItem("gift_2", "Jam Tangan Mewah dengan Kotak Eksklusif", "Rp 1.200.000", 4.9f, "(101)", "", "", "Terjual 101"),
            GiftItem("gift_3", "Set Lilin Aromaterapi Handmade Premium", "Rp 275.000", 4.7f, "(89)", "", "", "Terjual 89"),
            GiftItem("gift_4", "Kotak Coklat Premium Artisan Belgia", "Rp 375.000", 4.6f, "(156)", "", "", "Terjual 156"),
            GiftItem("gift_5", "Album Foto Kustom dengan Ukiran Nama", "Rp 349.000", 4.8f, "(67)", "", "", "Terjual 67"),
            GiftItem("gift_6", "Set Skincare Premium Organic", "Rp 525.000", 4.9f, "(178)", "", "", "Terjual 178"),
            GiftItem("gift_7", "Set Anggur Premium dengan Gelas Kristal", "Rp 850.000", 5.0f, "(45)", "", "", "Terjual 45"),
            GiftItem("gift_8", "Set Perhiasan Kalung dan Anting Silver 925", "Rp 675.000", 4.8f, "(123)", "", "", "Terjual 123")
        )

        val adapter = RecommendationAdapter(recommendations) { gift ->
            // Handle gift click
        }

        rvRecommendations.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
        }
    }
}
