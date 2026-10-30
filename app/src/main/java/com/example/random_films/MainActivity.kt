package com.example.random_films

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvMovieTitle: TextView
    private lateinit var btnGetMovie: Button
    private lateinit var btnReset: Button

    private val remainingMovies = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMovieTitle = findViewById(R.id.tvMovieTitle)
        btnGetMovie = findViewById(R.id.btnGetMovie)
        btnReset = findViewById(R.id.btnReset)

        resetMovieList()

        btnGetMovie.setOnClickListener {
            showNextMovie()
        }

        btnReset.setOnClickListener {
            resetMovieList()
            tvMovieTitle.text = getString(R.string.initial_prompt)
        }
    }

    private fun showNextMovie() {
        if (remainingMovies.isNotEmpty()) {
            val randomIndex = Random.nextInt(remainingMovies.size)
            val selectedMovie = remainingMovies.removeAt(randomIndex)
            tvMovieTitle.text = selectedMovie

            if (remainingMovies.isEmpty()) {
                btnGetMovie.isEnabled = false
            }
        } else {
            tvMovieTitle.text = getString(R.string.no_more_films)
            btnGetMovie.isEnabled = false
        }
    }

    private fun resetMovieList() {
        remainingMovies.clear()
        val moviesArray = resources.getStringArray(R.array.movies_array)
        remainingMovies.addAll(moviesArray)
        btnGetMovie.isEnabled = true
    }
}