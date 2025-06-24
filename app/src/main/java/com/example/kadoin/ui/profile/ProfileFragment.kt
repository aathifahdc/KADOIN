package com.example.kadoin.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kadoin.ui.auth.SignInActivity
import com.example.kadoin.databinding.FragmentProfileBinding
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.LinearLayout
import com.example.kadoin.R

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // Add this property at the top of the class
    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                // Update profile data
                val nama = data.getStringExtra("nama")
                val phone = data.getStringExtra("phone")

                nama?.let { binding.tvUserName.text = it }
                phone?.let { binding.tvUserPhone.text = it }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProfile()
        setupServiceMenu()
        setupClickListeners()
    }

    private fun setupProfile() {
        // Load user data from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("user_data", android.content.Context.MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "user123456")
        val userEmail = sharedPref.getString("user_email", "+62 819 3456 7890")

        binding.tvUserName.text = userName
        binding.tvUserPhone.text = userEmail

        // Add click listener to profile section
        binding.root.findViewById<LinearLayout>(R.id.profile_section)?.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            editProfileLauncher.launch(intent)
        }
    }

    private fun setupServiceMenu() {
        binding.btnGantikan.setOnClickListener {
            // Handle exchange/replace action
        }

        binding.btnHadiah.setOnClickListener {
            // Handle gift action
        }

        binding.btnPesan.setOnClickListener {
            // Handle message/order action
        }

        binding.btnFaq.setOnClickListener {
            // Handle FAQ action
        }
    }

    private fun setupClickListeners() {
        binding.btnSettings.setOnClickListener {
            // Handle settings click
        }

        binding.btnNotification.setOnClickListener {
            // Handle notifications click
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // Clear user session
        val sharedPref = requireActivity().getSharedPreferences("user_data", android.content.Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("is_logged_in", false)
            // Optionally clear all user data
            // clear()
            apply()
        }

        // Redirect to sign in
        val intent = Intent(requireContext(), SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
