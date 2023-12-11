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
import com.demo.namastemeditation.login.LoginActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setPassword
import com.google.android.material.snackbar.Snackbar

class CreateNewPasswordActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val etNewPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnCreatePassword = findViewById<Button>(R.id.btnCreatePassword)
        val prefs = PreferenceHelper.customPreference(this)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnCreatePassword.setOnClickListener {
            val password = etNewPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (password.isEmpty()) {
                val snackbar = Snackbar
                    .make(
                        constraintLayout,
                        "Password field cannot be empty!",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (confirmPassword.isEmpty()) {
                val snackbar = Snackbar
                    .make(
                        constraintLayout,
                        "Confirm password field cannot be empty!",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (password != confirmPassword) {
                val snackbar = Snackbar
                    .make(
                        constraintLayout,
                        "Confirm password and Password should be same!",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else {
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    prefs.setPassword = password
                    hideProgress()
                    Toast.makeText(this, "Password change successful!", Toast.LENGTH_LONG).show()
                    val myIntent = Intent(this, LoginActivity::class.java)
                    startActivity(myIntent)
                    finish()

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