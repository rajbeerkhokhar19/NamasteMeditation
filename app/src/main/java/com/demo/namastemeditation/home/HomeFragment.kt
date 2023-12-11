package com.demo.namastemeditation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.codebyashish.autoimageslider.AutoImageSlider
import com.codebyashish.autoimageslider.Enums.ImageActionTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Interfaces.ItemsListener
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import com.demo.namastemeditation.R
import com.demo.namastemeditation.details.DetailsActivity
import com.demo.namastemeditation.home.adapter.ForYouListAdapter
import com.demo.namastemeditation.home.model.ForYouResponse
import com.demo.namastemeditation.home.model.ForYouResponseList
import com.demo.namastemeditation.player.PlayerActivity
import com.demo.namastemeditation.search.SearchActivity
import com.demo.namastemeditation.utils.PreferenceHelper
import com.demo.namastemeditation.utils.PreferenceHelper.setTime
import com.demo.namastemeditation.utils.PreferenceHelper.setType

class HomeFragment : Fragment(), ItemsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val prefs = PreferenceHelper.customPreference(requireContext())

        val cvReminder = root.findViewById<ConstraintLayout>(R.id.cvReminder)
        val imageSearch = root.findViewById<AppCompatImageView>(R.id.imageSearch)
        val tvNotifactionMessage = root.findViewById<TextView>(R.id.tvNotifactionMessage)
        val cvMeditateHead = root.findViewById<CardView>(R.id.cvMeditateHead)

        if (prefs.setTime?.isNotEmpty() == true && prefs.setType?.isNotEmpty() == true) {
            tvNotifactionMessage.text = "Reminder set to ${prefs.setTime} ${prefs.setType}"
            cvReminder.visibility = View.VISIBLE
        } else {
            cvReminder.visibility = View.GONE
        }

        imageSearch.setOnClickListener {
            val myIntent = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(myIntent)
        }

        cvMeditateHead.setOnClickListener {
            val myIntent = Intent(requireActivity(), PlayerActivity::class.java)
            myIntent.putExtra(DetailsActivity.KEY_URL, "https://images.pexels.com/photos/5184327/pexels-photo-5184327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
            myIntent.putExtra(DetailsActivity.KEY_SOUND_URL, "sound1")
            myIntent.putExtra(DetailsActivity.KEY_TITLE, "Your Meditation Companion for Inner Peace")
            myIntent.putExtra(DetailsActivity.KEY_DESCRIPTION, "'Your Meditation Companion for Inner Peace' is a comprehensive resource dedicated to guiding individuals on a transformative journey towards inner tranquility. With a variety of expertly crafted guided meditations, calming visuals, and soothing sounds, this companion provides a versatile and immersive experience tailored to individual preferences. It incorporates mindful breathing exercises, progress tracking, and personalized meditation plans to support users in cultivating a consistent and fulfilling meditation practice. Daily reminders ensure adherence to routines, while a supportive community fosters a sense of connection and encouragement. Embrace this holistic tool to unlock the profound benefits of mindfulness and embark on a path to lasting inner peace and balance in your daily life.")
            startActivity(myIntent)
        }

        //auto image slider//
        val autoImageList: ArrayList<ImageSlidesModel> = ArrayList()

        val autoImageSlider = root.findViewById<AutoImageSlider>(R.id.autoImageSlider)
        autoImageList.add(
            ImageSlidesModel(
                "https://images.pexels.com/photos/7625258/pexels-photo-7625258.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Learn to Motivate Yourself"
            )
        )
        autoImageList.add(
            ImageSlidesModel(
                "https://images.pexels.com/photos/1174086/pexels-photo-1174086.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Unleashing Your Inner Motivation"
            )
        )
        autoImageList.add(
            ImageSlidesModel(
                "https://images.pexels.com/photos/8728414/pexels-photo-8728414.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Elevate Your Energy"
            )
        )

        autoImageSlider.setImageList(autoImageList, ImageScaleType.CENTER_CROP)

        autoImageSlider.setDefaultAnimation()
        autoImageSlider.onItemClickListener(this)
        //auto image slider//


        //for you recycler//
        val recyclerView = root.findViewById<RecyclerView>(R.id.rvForYou)
        val adapter =
            ForYouListAdapter(ForYouResponseList.getModel() as ArrayList<ForYouResponse>) {
                val myIntent = Intent(requireActivity(), DetailsActivity::class.java)
                myIntent.putExtra(KEY_ID, it.id)
                myIntent.putExtra(KEY_URL, it.image)
                myIntent.putExtra(KEY_SOUND_URL, it.soundUrl)
                myIntent.putExtra(KEY_TITLE, it.title)
                myIntent.putExtra(KEY_DESCRIPTION, it.description)
                myIntent.putExtra(KEY_TIME, it.time)
                myIntent.putExtra(KEY_RATING, it.rating)
                startActivity(myIntent)
            }

        recyclerView.adapter = adapter
        //for you recycler//

        return root
    }

    companion object {
        const val KEY_ID = "_id"
        const val KEY_URL = "_url"
        const val KEY_SOUND_URL = "_sound_url"
        const val KEY_TITLE = "_title"
        const val KEY_DESCRIPTION = "_description"
        const val KEY_TIME = "_time"
        const val KEY_RATING = "_rating"
        fun newInstance() = HomeFragment()
    }

    override fun onItemChanged(position: Int) {
    }

    override fun onTouched(actionTypes: ImageActionTypes?, position: Int) {
    }

    override fun onItemClicked(position: Int) {
    }
}