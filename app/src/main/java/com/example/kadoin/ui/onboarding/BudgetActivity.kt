package com.example.kadoin.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kadoin.MainActivity
import com.example.kadoin.R
import java.text.NumberFormat
import java.util.Locale

class BudgetActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var tvBudgetAmount: TextView
    private lateinit var seekbarBudget: SeekBar
    private lateinit var etBudgetInput: EditText
    private lateinit var btnLanjutkan: Button

    private lateinit var llKadoEkonomis: LinearLayout
    private lateinit var llKadoMenengah: LinearLayout
    private lateinit var llKadoMahal: LinearLayout
    private lateinit var llKadoMewah: LinearLayout

    private val numberFormat = NumberFormat.getNumberInstance(Locale("id", "ID"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        initViews()
        setupSeekBar()
        setupEditText()
        setupClickListeners()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        tvBudgetAmount = findViewById(R.id.tv_budget_amount)
        seekbarBudget = findViewById(R.id.seekbar_budget)
        etBudgetInput = findViewById(R.id.et_budget_input)
        btnLanjutkan = findViewById(R.id.btn_lanjutkan)

        llKadoEkonomis = findViewById(R.id.ll_kado_ekonomis)
        llKadoMenengah = findViewById(R.id.ll_kado_menengah)
        llKadoMahal = findViewById(R.id.ll_kado_mahal)
        llKadoMewah = findViewById(R.id.ll_kado_mewah)
    }

    private fun setupSeekBar() {
        seekbarBudget.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateBudgetDisplay(progress)
                    etBudgetInput.setText(progress.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Set initial value
        val initialValue = 500000
        seekbarBudget.progress = initialValue
        updateBudgetDisplay(initialValue)
    }

    private fun setupEditText() {
        etBudgetInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    try {
                        val value = input.toInt()
                        if (value in 50000..5000000) {
                            seekbarBudget.progress = value
                            updateBudgetDisplay(value)
                        }
                    } catch (e: NumberFormatException) {
                        // Handle invalid input
                    }
                }
            }
        })
    }

    private fun updateBudgetDisplay(amount: Int) {
        val formattedAmount = "Rp ${numberFormat.format(amount)}"
        tvBudgetAmount.text = formattedAmount
    }

    private fun setupClickListeners() {
        btnLanjutkan.setOnClickListener {
            // Complete onboarding and go to main app with Kado tab selected
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selected_tab", "kado")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Budget category click listeners
        llKadoEkonomis.setOnClickListener {
            setBudgetCategory(125000) // Average of 50k-200k
        }

        llKadoMenengah.setOnClickListener {
            setBudgetCategory(350000) // Average of 200k-500k
        }

        llKadoMahal.setOnClickListener {
            setBudgetCategory(750000) // Average of 500k-1M
        }

        llKadoMewah.setOnClickListener {
            setBudgetCategory(1500000) // 1.5M for luxury category
        }
    }

    private fun setBudgetCategory(amount: Int) {
        // Update seekbar progress
        seekbarBudget.progress = amount

        // Update budget display
        updateBudgetDisplay(amount)

        // Update input field
        etBudgetInput.setText(amount.toString())

        // Add visual feedback (optional)
        // You can add animation or highlight effect here
    }
}
