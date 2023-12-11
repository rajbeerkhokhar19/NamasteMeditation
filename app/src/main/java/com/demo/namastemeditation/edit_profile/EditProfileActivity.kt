package com.demo.namastemeditation.edit_profile

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.demo.namastemeditation.R
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setEmail
import com.demo.namastemeditation.utils.PreferenceHelper.setName
import com.google.android.material.snackbar.Snackbar


class EditProfileActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnEdit = findViewById<AppCompatButton>(R.id.btnEdit)


        val prefs = PreferenceHelper.customPreference(this)

        if (prefs.setName?.isNotEmpty() == true) {
            etName.setText(prefs.setName)
        }
        if (prefs.setEmail?.isNotEmpty() == true) {
            etEmail.setText(prefs.setEmail)
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnEdit.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (name.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Name field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else if (email.isEmpty()) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Email field cannot be empty!", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else if (!email.contains("@")) {
                val snackbar = Snackbar
                    .make(constraintLayout, "Enter valid email!", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                prefs.setName = name
                prefs.setEmail = email
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress()
                    Toast.makeText(this, "Profile edited Successful!", Toast.LENGTH_LONG).show()
                    onBackPressedDispatcher.onBackPressed()
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