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
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.demo.namastemeditation.R
import com.demo.namastemeditation.home.MainActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setEmail
import com.demo.namastemeditation.utils.PreferenceHelper.setPassword
import com.google.android.material.snackbar.Snackbar

class EnterEmailActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_email)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnSendOtp = findViewById<Button>(R.id.btnSendOtp)

        val prefs = PreferenceHelper.customPreference(this)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnSendOtp.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Email field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (!email.contains("@")) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Enter valid email!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            }  else {
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress()
                    if (prefs.setEmail == email) {
                        Toast.makeText(this, "Otp sent successful!", Toast.LENGTH_LONG).show()
                        val myIntent = Intent(this, VerifyEmailActivity::class.java)
                        startActivity(myIntent)
                    } else {
                        Toast.makeText(this, "Email didn't match!", Toast.LENGTH_LONG)
                            .show()
                    }
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