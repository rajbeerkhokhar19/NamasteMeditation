package com.demo.namastemeditation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.namastemeditation.R
import com.demo.namastemeditation.home.adapter.MeditateFeaturedListAdapter
import com.demo.namastemeditation.home.adapter.MeditatePopularListAdapter
import com.demo.namastemeditation.home.model.SleepFeaturedResponse
import com.demo.namastemeditation.home.model.SleepFeaturedResponseList
import com.demo.namastemeditation.home.model.SleepPopularResponse
import com.demo.namastemeditation.home.model.SleepPopularResponseList
import com.demo.namastemeditation.player.PlayerActivity


class MeditateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sleep, container, false)

        //featured recycler//
        val featuredRecyclerView = root.findViewById<RecyclerView>(R.id.rvFeatured)
        val layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        featuredRecyclerView.layoutManager = layoutManager
        val fAdapter =
            MeditateFeaturedListAdapter(SleepFeaturedResponseList.getModel() as ArrayList<SleepFeaturedResponse>) {
                val myIntent = Intent(requireActivity(), PlayerActivity::class.java)
                myIntent.putExtra(KEY_URL, it.image)
                myIntent.putExtra(KEY_TITLE, it.title)
                myIntent.putExtra(KEY_SOUND_URL, it.soundUrl)
                myIntent.putExtra(KEY_DESCRIPTION, it.title)
                startActivity(myIntent)
            }
        featuredRecyclerView.adapter = fAdapter
        //featured recycler//

        //popular recycler//
        val popularRecyclerView = root.findViewById<RecyclerView>(R.id.rvPopular)
        val pAdapter =
            MeditatePopularListAdapter(SleepPopularResponseList.getModel() as ArrayList<SleepPopularResponse>) {
                val myIntent = Intent(requireActivity(), PlayerActivity::class.java)
                myIntent.putExtra(KEY_URL, it.image)
                myIntent.putExtra(KEY_TITLE, it.title)
                myIntent.putExtra(KEY_SOUND_URL, it.soundUrl)
                myIntent.putExtra(KEY_DESCRIPTION, it.title)
                startActivity(myIntent)
            }

        popularRecyclerView.adapter = pAdapter
        //popular recycler//
        return root
    }

    companion object {
        const val KEY_URL = "_url"
        const val KEY_TITLE = "_title"
        const val KEY_SOUND_URL = "_sound_url"
        const val KEY_DESCRIPTION = "_description"
        fun newInstance() = MeditateFragment()
    }
}