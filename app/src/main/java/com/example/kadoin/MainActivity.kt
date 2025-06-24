package com.example.kadoin

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kadoin.databinding.ActivityMainBinding
import com.example.kadoin.ui.auth.SignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cek status login terlebih dahulu
        if (!isUserLoggedIn()) {
            redirectToAuth()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Setup bottom navigation with nav controller
        navView.setupWithNavController(navController)

        // Setup custom colors programmatically
        setupBottomNavigationColors(navView)

        // Debug: Log current destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("Navigation", "Current destination: ${destination.label}")
        }

        // Handle tab selection from intent
        val selectedTab = intent.getStringExtra("selected_tab")
        when (selectedTab) {
            "kado" -> {
                navView.selectedItemId = R.id.navigation_kado
                navController.navigate(R.id.navigation_kado)
            }
            "search" -> {
                navView.selectedItemId = R.id.navigation_search
                navController.navigate(R.id.navigation_search)
            }
            "favorite" -> {
                navView.selectedItemId = R.id.navigation_favorite
                navController.navigate(R.id.navigation_favorite)
            }
            "profile" -> {
                navView.selectedItemId = R.id.navigation_profile
                navController.navigate(R.id.navigation_profile)
            }
            else -> {
                navView.selectedItemId = R.id.navigation_home
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
        return sharedPref.getBoolean("is_logged_in", false)
    }

    private fun redirectToAuth() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun setupBottomNavigationColors(navView: BottomNavigationView) {
        // Define states for selected and unselected
        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected), // selected state
            intArrayOf(-android.R.attr.state_selected) // unselected state
        )

        // Define colors for each state
        val colors = intArrayOf(
            Color.parseColor("#E53935"), // red color for selected items
            Color.parseColor("#9E9E9E")  // gray color for unselected items
        )

        // Create ColorStateList
        val colorStateList = ColorStateList(states, colors)

        // Apply colors to icons and text
        navView.itemIconTintList = colorStateList
        navView.itemTextColor = colorStateList

        // Set ripple effect color (light red)
        navView.itemRippleColor = ColorStateList.valueOf(Color.parseColor("#FFE5E5"))

        // Set background color
        navView.setBackgroundColor(Color.WHITE)
    }
}
