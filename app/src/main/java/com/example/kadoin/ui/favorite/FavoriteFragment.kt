package com.example.kadoin.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadoin.databinding.FragmentFavoriteBinding
import com.example.kadoin.ui.home.GiftItem

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHeader()
        setupFavoriteItems()
    }

    private fun setupHeader() {
        binding.btnBack.setOnClickListener {
            // Handle back navigation
            requireActivity().onBackPressed()
        }
    }

    private fun setupFavoriteItems() {
        val favoriteItems = listOf(
            GiftItem("fav_1", "Kotak Kado Premium dengan Pita Elegan", "Rp 168.000", 4.8f, "(133)", "", "Rp 230.000", "Terjual 133"),
            GiftItem("fav_2", "Set Lilin Aromaterapi Premium", "Rp 215.000", 4.9f, "(87)", "", "", "Terjual 87"),
            GiftItem("fav_3", "Jam Tangan Mewah Exclusive", "Rp 1.250.000", 4.7f, "(45)", "", "", "Terjual 45"),
            GiftItem("fav_4", "Kotak Coklat Premium Assorted", "Rp 175.000", 4.6f, "(156)", "", "Rp 250.000", "Terjual 156"),
            GiftItem("fav_5", "Hampers Premium Spesial Raya", "Rp 450.000", 4.9f, "(78)", "", "Rp 500.000", "Terjual 78"),
            GiftItem("fav_6", "Parfum Premium Elegant Collection", "Rp 325.000", 4.8f, "(92)", "", "", "Terjual 92")
        )

        val adapter = FavoriteAdapter(
            favoriteItems,
            onItemClick = { item ->
                // Handle item click - navigate to product detail
            },
            onFavoriteClick = { item ->
                // Handle remove from favorites
            }
        )

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
