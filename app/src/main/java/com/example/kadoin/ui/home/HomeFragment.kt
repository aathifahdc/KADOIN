package com.example.kadoin.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadoin.databinding.FragmentHomeBinding
import com.example.kadoin.ui.onboarding.RelationshipActivity
import com.example.kadoin.ui.onboarding.MomentActivity
import com.example.kadoin.ui.onboarding.BudgetActivity
import com.example.kadoin.ui.product.ProductDetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HomeFragment", "onCreateView called")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated called")

        setupActionButtons()
        setupMomenSpesial()
        setupBudgetRecommendations()
        setupInspirasiKado()
    }

    private fun setupActionButtons() {
        binding.btnUntukSiapa.setOnClickListener {
            startActivity(Intent(requireContext(), RelationshipActivity::class.java))
        }

        binding.btnMomenApa.setOnClickListener {
            startActivity(Intent(requireContext(), MomentActivity::class.java))
        }

        binding.btnBudget.setOnClickListener {
            startActivity(Intent(requireContext(), BudgetActivity::class.java))
        }
    }

    private fun setupMomenSpesial() {
        val momenItems = listOf(
            GiftItem("smartphone_premium", "Smartphone Pro Max 128GB", "Rp 5.999.000", 4.8f, "(299)", "25% OFF", "Rp 7.999.000", "Terjual 299"),
            GiftItem("headphone_wireless", "Headphone Wireless Premium", "Rp 1.399.000", 4.9f, "(156)", "30% OFF", "Rp 1.999.000", "Terjual 156"),
            GiftItem("smartwatch_premium", "Smart Watch Series 8", "Rp 4.299.000", 4.7f, "(89)", "40% OFF", "Rp 7.199.000", "Terjual 89")
        )

        val momenAdapter = MomenSpesialHomeAdapter(momenItems) { item ->
            navigateToProductDetail(item)
        }

        binding.rvMomenSpesial.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = momenAdapter
        }
    }

    private fun setupBudgetRecommendations() {
        val budgetItems = listOf(
            GiftItem("earbuds_premium", "TWS Earbuds Noise Cancelling", "Rp 799.000", 4.7f, "(128)", "", "", "Terjual 128"),
            GiftItem("smart_home_hub", "Smart Home Hub dengan Asisten Suara", "Rp 1.299.000", 4.8f, "(85)", "", "", "Terjual 85"),
            GiftItem("fitness_tracker", "Fitness Tracker dengan Monitor Detak Jantung", "Rp 649.000", 4.9f, "(210)", "", "", "Terjual 210"),
            GiftItem("power_bank_premium", "Power Bank 20000mAh Fast Charging", "Rp 399.000", 4.6f, "(156)", "", "", "Terjual 156")
        )

        val budgetAdapter = BudgetHomeAdapter(budgetItems) { item ->
            navigateToProductDetail(item)
        }

        binding.rvBudgetRecommendations.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = budgetAdapter
        }
    }

    private fun setupInspirasiKado() {
        val inspirasiItems = listOf(
            GiftItem("gift_box_luxury", "Kotak Hadiah Mewah Premium", "Rp 249.000", 4.8f, "(95)", "", "", "Terjual 95"),
            GiftItem("watch_classic", "Jam Tangan Klasik Elegan", "Rp 599.000", 4.9f, "(78)", "", "", "Terjual 78"),
            GiftItem("parfum_signature", "Parfum Signature Collection", "Rp 785.000", 4.7f, "(124)", "", "", "Terjual 124"),
            GiftItem("wallet_leather", "Dompet Kulit Asli Premium", "Rp 425.000", 4.8f, "(67)", "", "", "Terjual 67")
        )

        val inspirasiAdapter = InspirasiHomeAdapter(inspirasiItems) { item ->
            navigateToProductDetail(item)
        }

        binding.rvInspirasiKado.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = inspirasiAdapter
        }
    }

    private fun navigateToProductDetail(item: GiftItem) {
        val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_NAME, item.name)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_PRICE, item.price)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_IMAGE, item.image)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_RATING, item.rating)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_REVIEWS, item.reviews)
            putExtra(ProductDetailActivity.EXTRA_PRODUCT_ORIGINAL_PRICE, item.originalPrice)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
