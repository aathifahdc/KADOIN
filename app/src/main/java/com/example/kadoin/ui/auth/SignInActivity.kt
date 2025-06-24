package com.example.kadoin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kadoin.databinding.ActivitySignInBinding
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnDaftar.setOnClickListener {
            handleRegistration()
        }

        binding.tvMasuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Fitur Google Sign In belum tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.btnFacebook.setOnClickListener {
            Toast.makeText(this, "Fitur Facebook Sign In belum tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.btnApple.setOnClickListener {
            Toast.makeText(this, "Fitur Apple Sign In belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleRegistration() {
        if (validateForm()) {
            val nama = binding.etNama.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.btnDaftar.isEnabled = false
            binding.btnDaftar.text = "Mendaftar..."

            val user = hashMapOf(
                "nama" to nama,
                "email" to email,
                "password" to password,
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("users")
                .document(email) // gunakan email sebagai ID unik
                .set(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Pendaftaran berhasil! Selamat datang, $nama", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("registered_email", email)
                    intent.putExtra("show_success", true)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    binding.btnDaftar.isEnabled = true
                    binding.btnDaftar.text = "DAFTAR"
                    Toast.makeText(this, "Gagal mendaftar: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        binding.tilNama.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null

        val nama = binding.etNama.text.toString().trim()
        if (nama.isEmpty()) {
            binding.tilNama.error = "Nama lengkap harus diisi"
            isValid = false
        } else if (nama.length < 2) {
            binding.tilNama.error = "Nama minimal 2 karakter"
            isValid = false
        }

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
        } else if (password.length < 6) {
            binding.tilPassword.error = "Kata sandi minimal 6 karakter"
            isValid = false
        }

        val confirmPassword = binding.etConfirmPassword.text.toString()
        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Konfirmasi kata sandi harus diisi"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Kata sandi tidak cocok"
            isValid = false
        }

        if (!binding.cbTerms.isChecked) {
            Toast.makeText(this, "Anda harus menyetujui Syarat & Ketentuan", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }
}
