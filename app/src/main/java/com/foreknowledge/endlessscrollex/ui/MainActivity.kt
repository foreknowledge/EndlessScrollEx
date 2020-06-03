package com.foreknowledge.endlessscrollex.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.foreknowledge.endlessscrollex.R
import com.foreknowledge.endlessscrollex.adapter.TvShowsAdapter
import com.foreknowledge.endlessscrollex.databinding.ActivityMainBinding
import com.foreknowledge.endlessscrollex.network.TvShow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val tvShowsAdapter = TvShowsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = tvShowsAdapter.apply {
                setItemClickListener {
                    showDialog(it)
                }
            }
        }

        subscribeUI()
    }

    private fun showDialog(item: TvShow) = with (item) {
        val message = "TV Show Name : $name, \n" +
                "Original Countries : ${originCountries.joinToString(",")}, \n" +
                "First Air Date : $firstAirDate, \n" +
                "Popularity : $popularity, \n" +
                "Vote Average : $voteAverage, \n" +
                "Overview : $overview"

        AlertDialog.Builder(this@MainActivity)
            .setTitle("TV Show Information")
            .setMessage(message)
            .create()
            .show()
    }

    private fun subscribeUI() = with(viewModel) {
        val owner = this@MainActivity
        isLoading.observe(owner, Observer { binding.isLoading = it })
        tvShowList.observe(owner, Observer { tvShowsAdapter.submitList(it) })
    }
}
