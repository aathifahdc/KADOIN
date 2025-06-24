package com.example.kadoin.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadoin.databinding.FragmentSearchBinding
import com.example.kadoin.ui.home.GiftItem
import com.example.kadoin.ui.explore.FilterAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchFunctionality()
        setupCategoryFilters()
        setupPriceFilters()
        setupTrendingSection()
        setupMomenSpesialSection()
        setupBudgetRecommendations()
        setupInspirasiKado()
        setupParfumSignature()
    }

    private fun setupSearchFunctionality() {
        // Setup search functionality
        binding.etSearch.setOnClickListener {
            // Handle search click
        }
    }

    private fun setupCategoryFilters() {
        val categories = listOf("Semua", "Ulang Tahun", "Anniversary", "Wisuda")
        val categoryAdapter = FilterAdapter(categories) { category ->
            // Handle category filter
        }

        binding.rvCategoryFilters.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun setupPriceFilters() {
        // Price filter chips are handled in the layout
        binding.chipPrice1.setOnClickListener {
            // Handle Rp 100k - 200k filter
        }

        binding.chipPrice2.setOnClickListener {
            // Handle Rp 300k+ filter
        }
    }

    private fun setupTrendingSection() {
        val trendingItems = listOf(
            GiftItem("trending_1", "TWS Earbuds Noise Cancelling", "Rp 199.000", 4.7f, "(128)", "", "", "Terjual 128"),
            GiftItem("trending_2", "Smart Watch Premium", "Rp 764.000", 4.8f, "(85)", "", "", "Terjual 85"),
            GiftItem("trending_3", "Speaker Bluetooth Premium", "Rp 375.000", 4.9f, "(210)", "", "", "Terjual 210")
        )

        val trendingAdapter = TrendingAdapter(trendingItems) { item ->
            // Handle trending item click
        }

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }
    }

    private fun setupMomenSpesialSection() {
        val momenItems = listOf(
            GiftItem("momen_1", "Smartphone Premium Pro Max", "Rp 12.999.000", 4.8f, "(299)", "", "", "Terjual 299"),
            GiftItem("momen_2", "Headphone Wireless Premium", "Rp 1.299.000", 4.9f, "(156)", "", "", "Terjual 156")
        )

        val momenAdapter = MomenSpesialAdapter(momenItems) { item ->
            // Handle momen spesial click
        }

        binding.rvMomenSpesial.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = momenAdapter
        }
    }

    private fun setupBudgetRecommendations() {
        val budgetItems = listOf(
            GiftItem("budget_1", "TWS Earbuds Noise Cancelling", "Rp 199.000", 4.7f, "(128)", "", "", "Terjual 128"),
            GiftItem("budget_2", "Smart Home Hub dengan Speaker", "Rp 599.000", 4.8f, "(85)", "", "", "Terjual 85")
        )

        val budgetAdapter = BudgetAdapter(budgetItems) { item ->
            // Handle budget item click
        }

        binding.rvBudget.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = budgetAdapter
        }
    }

    private fun setupInspirasiKado() {
        val inspirasiItems = listOf(
            GiftItem("inspirasi_1", "Bunga Mawar Merah Premium", "Rp 249.000", 4.8f, "(95)", "", "", "Terjual 95"),
            GiftItem("inspirasi_2", "Jam Tangan Mewah Premium", "Rp 359.000", 4.9f, "(78)", "", "", "Terjual 78")
        )

        val inspirasiAdapter = InspirasiAdapter(inspirasiItems) { item ->
            // Handle inspirasi click
        }

        binding.rvInspirasi.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = inspirasiAdapter
        }
    }

    private fun setupParfumSignature() {
        val parfumItems = listOf(
            GiftItem("parfum_1", "Parfum Signature Pria", "Rp 865.000", 4.7f, "(124)", "", "", "Terjual 124"),
            GiftItem("parfum_2", "Parfum Signature Wanita", "Rp 425.000", 4.8f, "(67)", "", "", "Terjual 67")
        )

        val parfumAdapter = ParfumAdapter(parfumItems) { item ->
            // Handle parfum click
        }

        binding.rvParfum.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = parfumAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
