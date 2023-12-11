package com.demo.namastemeditation.home

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.namastemeditation.R
import com.demo.namastemeditation.edit_profile.EditProfileActivity
import com.demo.namastemeditation.login.LoginActivity
import com.demo.namastemeditation.reset_password.ChangePasswordActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setEmail
import com.demo.namastemeditation.utils.PreferenceHelper.setImage
import com.demo.namastemeditation.utils.PreferenceHelper.setName
import com.github.drjacky.imagepicker.ImagePicker

class ProfileFragment : Fragment() {

    private var mCameraUri: Uri? = null
    private var ivProfile: ImageView? = null
    private var prefs: SharedPreferences? = null
    private lateinit var name: LiveData<String>
    private lateinit var email: LiveData<String>

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                mCameraUri = it.data?.data
                if (prefs != null) {
                    prefs?.setImage = mCameraUri.toString()
                }
                Glide.with(this)
                    .load(mCameraUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivProfile!!)
            } else {
                parseError(it)
            }
        }

    private fun parseError(activityResult: ActivityResult) {
        if (activityResult.resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(
                requireContext(),
                ImagePicker.getError(activityResult.data),
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val constraintLayout = root.findViewById<ConstraintLayout>(R.id.constraintLayout)
        val tvChangePassword = root.findViewById<TextView>(R.id.tvChangePassword)
        val tvName = root.findViewById<TextView>(R.id.tvName)
        val tvEmail = root.findViewById<TextView>(R.id.tvEmail)
        val btnEdit = root.findViewById<Button>(R.id.btnEdit)
        val btnLogout = root.findViewById<Button>(R.id.btnLogout)
        val ivEdit = root.findViewById<ImageView>(R.id.ivEdit)
        ivProfile = root.findViewById(R.id.ivProfile)
        prefs = PreferenceHelper.customPreference(requireContext())

        if (prefs?.setName?.isNotEmpty() == true) {
            tvName.text = prefs?.setName
        }

        if (prefs?.setEmail?.isNotEmpty() == true) {
            tvEmail.text = prefs?.setEmail
        }

        if (prefs?.setImage?.isNotEmpty() == true) {
            Glide.with(this)
                .load(Uri.parse(prefs?.setImage))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivProfile!!)
        }

        ivEdit?.setOnClickListener {
            cameraLauncher.launch(
                ImagePicker.with(requireActivity())
                    .crop()
                    .cameraOnly()
                    .maxResultSize(1080, 1920, true)
                    .createIntent()
            )
        }

        tvChangePassword.setOnClickListener {
            val myIntent = Intent(requireActivity(), ChangePasswordActivity::class.java)
            startActivity(myIntent)
        }

        btnLogout.setOnClickListener {
            val myIntent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(myIntent)
        }

        btnEdit.setOnClickListener {
            val myIntent = Intent(requireActivity(), EditProfileActivity::class.java)
            startActivity(myIntent)
        }

        return root;
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}