package com.lucasmontano.fearsetting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lucasmontano.fearsetting.onboard.OnboardViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private lateinit var onboardViewModel: OnboardViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    onboardViewModel = ViewModelProviders.of(this).get(OnboardViewModel::class.java)

    observeOnboardContent()

    onboardViewModel.initData()
  }

  private fun observeOnboardContent() {
    onboardViewModel.observeIllustrationUrl(this, Observer {
      Picasso.get()
        .load(it)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .into(onboardImageView)
    })

    onboardViewModel.observeQuote(this, Observer {
      quote.text = it
    })

    onboardViewModel.observeAuthor(this, Observer {
      author.text = it
    })
  }
}
