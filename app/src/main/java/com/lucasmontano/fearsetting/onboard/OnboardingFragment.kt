package com.lucasmontano.fearsetting.onboard

import android.view.View
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lucasmontano.fearsetting.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment : Fragment() {

  private lateinit var onboardViewModel: OnboardViewModel

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    onboardViewModel = ViewModelProviders.of(this).get(OnboardViewModel::class.java)

    lifecycle.addObserver(onboardViewModel)

    observeOnboardContent()
  }

  override fun onDestroyView() {
    lifecycle.removeObserver(onboardViewModel)
    super.onDestroyView()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_onboarding, container, false)
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