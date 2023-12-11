package com.demo.namastemeditation.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.demo.namastemeditation.R
import com.demo.namastemeditation.forgot_password.EnterEmailActivity
import com.demo.namastemeditation.home.MainActivity
import com.demo.namastemeditation.register.RegisterActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setEmail
import com.demo.namastemeditation.utils.PreferenceHelper.setPassword
import com.demo.namastemeditation.utils.PreferenceHelper.setRememberLogin
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnForgotPassword = findViewById<AppCompatTextView>(R.id.tvForgotPassword)
        val cbRememberLogin = findViewById<AppCompatCheckBox>(R.id.cb_remember)
        val btnSignUp = findViewById<AppCompatButton>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val prefs = PreferenceHelper.customPreference(this)

        if (prefs.setEmail?.isNotEmpty() == true && prefs.setPassword?.isNotEmpty() == true && prefs.setRememberLogin) {
            cbRememberLogin.isChecked = true
            etEmail.setText(prefs.setEmail)
            etPassword.setText(prefs.setPassword)
        }

        cbRememberLogin.setOnCheckedChangeListener { compoundButton, isChecked ->
            prefs.setRememberLogin = isChecked
        }

        btnForgotPassword.setOnClickListener {
            val myIntent = Intent(this, EnterEmailActivity::class.java)
            startActivity(myIntent)
        }

        btnSignUp.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Email field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else if (password.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Password field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                snackbar.show()
            } else {
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress()
                    if (prefs.setEmail == email && prefs.setPassword == password) {
                        etEmail.setText(prefs.setEmail)
                        etPassword.setText(prefs.setPassword)

                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()
                        val myIntent = Intent(this, MainActivity::class.java)
                        myIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(myIntent)
                        finish()
                    } else {
                        val snackbar = Snackbar
                            .make(constraintLayout, "Please create a account before login!", Snackbar.LENGTH_LONG)
                        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                        snackbar.show()
                        /*Toast.makeText(this, "Please create a account before login!", Toast.LENGTH_LONG)
                            .show()*/
                        /*Toast.makeText(this, "Email or Password didn't match!", Toast.LENGTH_LONG)
                            .show()*/
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