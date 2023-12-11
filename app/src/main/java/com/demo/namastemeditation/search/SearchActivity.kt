package com.demo.namastemeditation.search

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.demo.namastemeditation.R
import com.demo.namastemeditation.home.HomeFragment
import com.demo.namastemeditation.player.PlayerActivity
import com.demo.namastemeditation.search.adapter.SearchAdapter
import com.demo.namastemeditation.search.model.SearchResponse
import com.demo.namastemeditation.search.model.SearchResponseList
import java.util.Locale


class SearchActivity : AppCompatActivity() {
    var adapter: SearchAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val etSearch = findViewById<SearchView>(R.id.etSearch)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })

        val recyclerView = findViewById<RecyclerView>(R.id.rvSearch)
        adapter =
            SearchAdapter(SearchResponseList.getModel() as ArrayList<SearchResponse>) {
                val myIntent = Intent(this, PlayerActivity::class.java)
                myIntent.putExtra(HomeFragment.KEY_URL, it.image)
                myIntent.putExtra(HomeFragment.KEY_SOUND_URL, it.soundUrl)
                myIntent.putExtra(HomeFragment.KEY_TITLE, it.title)
                myIntent.putExtra(HomeFragment.KEY_DESCRIPTION, it.title)
                startActivity(myIntent)
            }

        recyclerView.adapter = adapter
    }

    private fun filter(text: String) {
        val list: ArrayList<SearchResponse> = ArrayList()
        for (item in SearchResponseList.getModel()) {
            if (item.title.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                list.add(item)
            }
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show()
        } else {
            adapter?.filterList(list)
        }
    }
}