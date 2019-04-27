package com.lucasmontano.fearsetting.onboard

import androidx.lifecycle.*

class OnboardViewModel : ViewModel(), LifecycleObserver {

  private val illustrationUrl: MutableLiveData<String> = MutableLiveData()
  private val quote: MutableLiveData<String> = MutableLiveData()
  private val author: MutableLiveData<String> = MutableLiveData()

  fun initData() {
    illustrationUrl.value = "https://www.pensarcontemporaneo.com/content/uploads/2018/03/Seneca.jpeg"
    quote.value = "\"We suffer more often in imagination than in reality.\""
    author.value = "– Seneca"
  }

  fun observeIllustrationUrl(
    owner: LifecycleOwner,
    observer: Observer<String>
  ) = illustrationUrl.observe(owner, observer)

  fun observeQuote(
    owner: LifecycleOwner,
    observer: Observer<String>
  ) = quote.observe(owner, observer)

  fun observeAuthor(
    owner: LifecycleOwner,
    observer: Observer<String>
  ) = author.observe(owner, observer)
}
