package com.example.kadoin.ui.profile

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kadoin.databinding.ActivityEditProfileBinding
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private var selectedImageUri: Uri? = null
    private val calendar = Calendar.getInstance()

    // Image picker launcher
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            selectedImageUri?.let { uri ->
                binding.ivProfilePhoto.setImageURI(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        loadUserData()
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        binding.layoutProfilePhoto.setOnClickListener {
            showImagePickerDialog()
        }

        binding.etBirthDate.setOnClickListener {
            showDatePicker()
        }

        binding.tilBirthDate.setEndIconOnClickListener {
            showDatePicker()
        }

        binding.btnDeleteAccount.setOnClickListener {
            showDeleteAccountDialog()
        }
    }

    private fun loadUserData() {
        // Load existing user data
        binding.etNama.setText("user123456")
        binding.etPhone.setText("+62 812-3456-7890")
        binding.etEmail.setText("user123456@gmail.com")
        binding.rbFemale.isChecked = true
        binding.etBirthDate.setText("06/15/2005")
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Ambil Foto", "Pilih dari Galeri", "Batal")

        AlertDialog.Builder(this)
            .setTitle("Ubah Foto Profil")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                    2 -> dialog.dismiss()
                }
            }
            .show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            imagePickerLauncher.launch(intent)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                binding.etBirthDate.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set max date to today
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun saveProfile() {
        if (validateForm()) {
            // Get form data
            val nama = binding.etNama.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val gender = if (binding.rbMale.isChecked) "Laki-laki" else "Perempuan"
            val birthDate = binding.etBirthDate.text.toString().trim()

            // TODO: Save to database or API
            // For now, just show success message
            Toast.makeText(this, "Profil berhasil disimpan", Toast.LENGTH_SHORT).show()

            // Return result to previous activity
            val resultIntent = Intent()
            resultIntent.putExtra("nama", nama)
            resultIntent.putExtra("phone", phone)
            resultIntent.putExtra("email", email)
            resultIntent.putExtra("gender", gender)
            resultIntent.putExtra("birthDate", birthDate)
            selectedImageUri?.let {
                resultIntent.putExtra("imageUri", it.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Validate nama
        if (binding.etNama.text.toString().trim().isEmpty()) {
            binding.tilNama.error = "Nama lengkap harus diisi"
            isValid = false
        } else {
            binding.tilNama.error = null
        }

        // Validate phone
        if (binding.etPhone.text.toString().trim().isEmpty()) {
            binding.tilPhone.error = "Nomor telepon harus diisi"
            isValid = false
        } else {
            binding.tilPhone.error = null
        }

        // Validate birth date
        if (binding.etBirthDate.text.toString().trim().isEmpty()) {
            binding.tilBirthDate.error = "Tanggal lahir harus diisi"
            isValid = false
        } else {
            binding.tilBirthDate.error = null
        }

        // Validate gender
        if (!binding.rbMale.isChecked && !binding.rbFemale.isChecked) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun showDeleteAccountDialog() {
        AlertDialog.Builder(this)
            .setTitle("Hapus Akun")
            .setMessage("Apakah Anda yakin ingin menghapus akun? Tindakan ini tidak dapat dibatalkan.")
            .setPositiveButton("Hapus") { _, _ ->
                deleteAccount()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteAccount() {
        // TODO: Implement account deletion logic
        Toast.makeText(this, "Fitur hapus akun akan segera tersedia", Toast.LENGTH_SHORT).show()
    }
}
