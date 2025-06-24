package com.example.kadoin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kadoin.databinding.ActivityLoginBinding
import com.example.kadoin.ui.onboarding.RelationshipActivity
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        setupClickListeners()
        handleRegistrationSuccess()
    }

    private fun setupClickListeners() {
        binding.btnMasuk.setOnClickListener {
            handleLogin()
        }

        binding.tvDaftar.setOnClickListener {
            finish() // Kembali ke halaman daftar
        }

        binding.tvForgotPassword.setOnClickListener {
            handleForgotPassword()
        }
    }

    private fun handleRegistrationSuccess() {
        val showSuccess = intent.getBooleanExtra("show_success", false)
        val registeredEmail = intent.getStringExtra("registered_email")

        if (showSuccess && registeredEmail != null) {
            binding.etEmail.setText(registeredEmail)
            Toast.makeText(this, "Akun berhasil dibuat! Silakan masuk", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleLogin() {
        if (validateLoginForm()) {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.btnMasuk.isEnabled = false
            binding.btnMasuk.text = "Masuk..."

            firestore.collection("users").document(email).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val storedPassword = document.getString("password")
                        val userName = document.getString("nama")

                        if (password == storedPassword) {
                            // Login berhasil
                            Toast.makeText(this, "Selamat datang, $userName!", Toast.LENGTH_SHORT).show()

                            // Simpan status login jika ingin (opsional)
                            val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putBoolean("is_logged_in", true)
                                putString("user_email", email)
                                putString("user_name", userName)
                                apply()
                            }

                            // Arahkan ke halaman berikutnya
                            val intent = Intent(this, RelationshipActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        } else {
                            showLoginFailed("Kata sandi salah")
                        }
                    } else {
                        showLoginFailed("Akun tidak ditemukan")
                    }
                }
                .addOnFailureListener { e ->
                    showLoginFailed("Terjadi kesalahan: ${e.message}")
                }
        }
    }

    private fun validateLoginForm(): Boolean {
        var isValid = true
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email harus diisi"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Format email tidak valid"
            isValid = false
        }

        val password = binding.etPassword.text.toString()
        if (password.isEmpty()) {
            binding.tilPassword.error = "Kata sandi harus diisi"
            isValid = false
        }

        return isValid
    }

    private fun showLoginFailed(message: String) {
        binding.btnMasuk.isEnabled = true
        binding.btnMasuk.text = "MASUK"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun handleForgotPassword() {
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            Toast.makeText(this, "Masukkan email terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulasi reset password
        Toast.makeText(this, "Link reset password telah dikirim ke $email", Toast.LENGTH_LONG).show()
    }
}
