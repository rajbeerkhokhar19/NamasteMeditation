package com.demo.namastemeditation.forgot_password

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.demo.namastemeditation.R
import com.google.android.material.snackbar.Snackbar

class VerifyEmailActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val etOtp = findViewById<EditText>(R.id.etOtp)
        val btnVerifyOtp = findViewById<Button>(R.id.btnVerify)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnVerifyOtp.setOnClickListener {
            val otp = etOtp.text.toString().trim()

            if (otp.isEmpty()) {
                val snackbar = Snackbar
                    .make(
                        constraintLayout,
                        "Confirmation code field cannot be empty!",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else {
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress()
                    Toast.makeText(this, "Otp verified!", Toast.LENGTH_LONG).show()
                    val myIntent = Intent(this, CreateNewPasswordActivity::class.java)
                    startActivity(myIntent)
                }, 1500)

            }
        }
    }

    private fun showProgress() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Please Wait")
        progressDialog?.setMessage("Loading ...")
        progressDialog?.show()
    }

    private fun hideProgress() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
        }
    }
}