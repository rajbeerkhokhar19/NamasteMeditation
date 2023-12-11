package com.demo.namastemeditation.details

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.namastemeditation.R
import com.demo.namastemeditation.database.RatingsDatabase
import com.demo.namastemeditation.database.RatingsEntity
import com.demo.namastemeditation.home.adapter.RatingsListAdapter
import com.demo.namastemeditation.home.model.RatingsAndReviewResponseList
import com.demo.namastemeditation.player.PlayerActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setName
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DetailsActivity : AppCompatActivity(), CoroutineScope {
    private val id: Int? by lazy {
        intent.getIntExtra(
            KEY_ID, 0
        )
    }

    private val url: String? by lazy {
        intent.getStringExtra(
            KEY_URL
        )
    }

    private val soundUrl: String? by lazy {
        intent.getStringExtra(
            KEY_SOUND_URL
        )
    }
    private val title: String? by lazy {
        intent.getStringExtra(
            KEY_TITLE
        )
    }

    private val description: String? by lazy {
        intent.getStringExtra(
            KEY_DESCRIPTION
        )
    }

    private val time: String? by lazy {
        intent.getStringExtra(
            KEY_TIME
        )
    }

    private val rating: Double? by lazy {
        intent.getDoubleExtra(KEY_RATING, 0.0)
    }

    var progressDialog: ProgressDialog? = null


    companion object {
        const val KEY_URL = "_url"
        const val KEY_ID = "_id"
        const val KEY_SOUND_URL = "_sound_url"
        const val KEY_TITLE = "_title"
        const val KEY_DESCRIPTION = "_description"
        const val KEY_TIME = "_time"
        const val KEY_RATING = "_rating"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val database = RatingsDatabase.getDatabaseInstance(this)
        val prefs = PreferenceHelper.customPreference(this)
        var sizeOfDatabase: Int
        launch {
            sizeOfDatabase = database?.dao?.getSizeOfDatabaseById(id ?: -1) ?: 0
            if (sizeOfDatabase < 1) {
                RatingsAndReviewResponseList.getModel().forEach {
                    database?.dao?.addRating(
                        RatingsEntity(
                            detailId = id,
                            ratings = it.rating.toFloat(),
                            description = it.description,
                            image = it.image,
                            name = it.name
                        )
                    )
                }
            }
        }


        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val image = findViewById<ImageView>(R.id.imageView)
        val tvTitle = findViewById<TextView>(R.id.txtName)
        val tvDescription = findViewById<TextView>(R.id.txtDescription)
        val tvTime = findViewById<TextView>(R.id.txtTime)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val etWrite = findViewById<EditText>(R.id.etWrite)


        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        etWrite.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .create()
            val view = layoutInflater.inflate(R.layout.dialog_ratings, null)
            val llLayout = view.findViewById<LinearLayoutCompat>(R.id.llLayout)
            val rating = view.findViewById<RatingBar>(R.id.ratingBar)
            val etWrite = view.findViewById<EditText>(R.id.etWrite)
            val ivDismiss = view.findViewById<ImageView>(R.id.ivDismiss)
            val button = view.findViewById<Button>(R.id.btnDone)
            builder.setView(view)
            ivDismiss.setOnClickListener {
                builder.dismiss()
            }
            button.setOnClickListener {
                if (etWrite.text.toString().isNotEmpty()) {
                    showProgress()
                    Handler(Looper.getMainLooper()).postDelayed({
                        hideProgress()
                        launch {
                            database?.dao?.addRating(
                                RatingsEntity(
                                    detailId = id,
                                    ratings = rating.rating,
                                    description = etWrite.text.toString(),
                                    image = "https://images.pexels.com/photos/10425598/pexels-photo-10425598.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                                    name = if (prefs.setName?.isNotEmpty() == true) {
                                        prefs.setName
                                    } else {
                                        "John Doe"
                                    }
                                )
                            )
                        }
                        Toast.makeText(this, "Review added Successfully!", Toast.LENGTH_LONG).show()
                        builder.dismiss()
                    }, 1500)
                } else {
                    val snackbar = Snackbar
                        .make(
                            llLayout,
                            "Review field should not be empty!",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                    snackbar.show()
                }

            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
        fab.setOnClickListener {
            val myIntent = Intent(this, PlayerActivity::class.java)
            myIntent.putExtra(KEY_URL, url)
            myIntent.putExtra(KEY_SOUND_URL, soundUrl)
            myIntent.putExtra(KEY_TITLE, title)
            myIntent.putExtra(KEY_DESCRIPTION, description)
            startActivity(myIntent)
        }

        Glide.with(this)
            .load(
                url
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(image)
        tvTitle.text = title
        tvTime.text = time
        tvDescription.text = description
        ratingBar.rating = rating?.toFloat() ?: 0.0F

        //for you recycler//
        val recyclerView = findViewById<RecyclerView>(R.id.rvRatings)
        database?.dao?.getRatingsByDetailId(id ?: -1)?.observe(this) { result ->
            val adapter =
                RatingsListAdapter(result as ArrayList<RatingsEntity>) {}
            recyclerView.adapter = adapter
        }
    }

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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