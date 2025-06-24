package com.example.kadoin.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadoin.databinding.FragmentSearchBinding
import com.example.kadoin.ui.home.GiftItem
import com.example.kadoin.ui.search.TrendingAdapter
import com.example.kadoin.ui.search.BudgetAdapter
import com.example.kadoin.ui.search.ParfumAdapter
import com.example.kadoin.ui.search.InspirasiAdapter
import com.example.kadoin.ui.search.MomenSpesialAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var allTrendingItems: List<GiftItem>

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
        setupPriceFilters()
        setupTrendingSection()
        setupMomenSpesialSection()
        setupBudgetRecommendations()
        setupInspirasiKado()
        setupParfumSignature()
    }

    private fun setupSearchFunctionality() {
        binding.etSearch.setOnClickListener {
            // TODO: Implement search behavior
        }
    }

    private fun setupPriceFilters() {
        binding.chipPrice1.setOnClickListener {
            filterTrendingItemsByPrice(100_000, 200_000)
        }

        binding.chipPrice2.setOnClickListener {
            filterTrendingItemsByPrice(300_000, Int.MAX_VALUE)
        }
    }

    private fun filterTrendingItemsByPrice(minPrice: Int, maxPrice: Int) {
        val filtered = allTrendingItems.filter {
            val hargaNumber = it.price.replace("[^\\d]".toRegex(), "").toIntOrNull() ?: 0
            hargaNumber in minPrice..maxPrice
        }

        val adapter = TrendingAdapter(filtered) { item ->
            // TODO: Handle item click
        }

        binding.rvTrending.adapter = adapter
    }

    private fun setupTrendingSection() {
        allTrendingItems = listOf(
            GiftItem("trending_1", "TWS Earbuds Noise Cancelling", "Rp 199.000", 4.7f, "(128)", "", "", "Terjual 128"),
            GiftItem("trending_2", "Smart Watch Premium", "Rp 764.000", 4.8f, "(85)", "", "", "Terjual 85"),
            GiftItem("trending_3", "Speaker Bluetooth Premium", "Rp 375.000", 4.9f, "(210)", "", "", "Terjual 210")
        )

        val adapter = TrendingAdapter(allTrendingItems) { item ->
            Toast.makeText(requireContext(), "Klik: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupMomenSpesialSection() {
        val items = listOf(
            GiftItem("momen_1", "Smartphone Premium Pro Max", "Rp 12.999.000", 4.8f, "(299)", "", "", "Terjual 299"),
            GiftItem("momen_2", "Headphone Wireless Premium", "Rp 1.299.000", 4.9f, "(156)", "", "", "Terjual 156")
        )

        val adapter = MomenSpesialAdapter(items) { item ->
            // TODO: Handle item click
        }

        binding.rvMomenSpesial.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupBudgetRecommendations() {
        val items = listOf(
            GiftItem("budget_1", "TWS Earbuds Noise Cancelling", "Rp 199.000", 4.7f, "(128)", "", "", "Terjual 128"),
            GiftItem("budget_2", "Smart Home Hub dengan Speaker", "Rp 599.000", 4.8f, "(85)", "", "", "Terjual 85")
        )

        val adapter = BudgetAdapter(items) { item ->
            // TODO: Handle item click
        }

        binding.rvBudget.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupInspirasiKado() {
        val items = listOf(
            GiftItem("inspirasi_1", "Bunga Mawar Merah Premium", "Rp 249.000", 4.8f, "(95)", "", "", "Terjual 95"),
            GiftItem("inspirasi_2", "Jam Tangan Mewah Premium", "Rp 359.000", 4.9f, "(78)", "", "", "Terjual 78")
        )

        val adapter = InspirasiAdapter(items) { item ->
            // TODO: Handle item click
        }

        binding.rvInspirasi.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupParfumSignature() {
        val items = listOf(
            GiftItem("parfum_1", "Parfum Signature Pria", "Rp 865.000", 4.7f, "(124)", "", "", "Terjual 124"),
            GiftItem("parfum_2", "Parfum Signature Wanita", "Rp 425.000", 4.8f, "(67)", "", "", "Terjual 67")
        )

        val adapter = ParfumAdapter(items) { item ->
            // TODO: Handle item click
        }

        binding.rvParfum.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
