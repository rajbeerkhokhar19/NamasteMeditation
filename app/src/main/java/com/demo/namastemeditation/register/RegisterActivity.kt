package com.demo.namastemeditation.register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.demo.namastemeditation.R
import com.demo.namastemeditation.login.LoginActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setEmail
import com.demo.namastemeditation.utils.PreferenceHelper.setName
import com.demo.namastemeditation.utils.PreferenceHelper.setPassword
import com.google.android.material.snackbar.Snackbar


class RegisterActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        val prefs = PreferenceHelper.customPreference(this)

        btnLogin.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnSignUp.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (name.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Name field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (email.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Email field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (password.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Password field cannot be empty!", Snackbar.LENGTH_LONG)
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
            } else if (!email.contains("@")) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Enter valid email!", Snackbar.LENGTH_LONG)
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
                prefs.setEmail = email
                prefs.setPassword = password
                prefs.setName = name
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress()
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
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