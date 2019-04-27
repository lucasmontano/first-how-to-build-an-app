package com.lucasmontano.fearsetting.onboard

import androidx.lifecycle.*
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.lucasmontano.fearsetting.BuildConfig
import org.json.JSONObject

class OnboardViewModel : ViewModel(), LifecycleObserver {

  private val illustrationUrl: MutableLiveData<String> = MutableLiveData()
  private val quote: MutableLiveData<String> = MutableLiveData()
  private val author: MutableLiveData<String> = MutableLiveData()

  private val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

  init {
    if (BuildConfig.DEBUG) {
      val configSettings = FirebaseRemoteConfigSettings.Builder()
        .setDeveloperModeEnabled(BuildConfig.DEBUG)
        .setMinimumFetchIntervalInSeconds(3600)
        .build()
      firebaseRemoteConfig.setConfigSettings(configSettings)
    }
  }

  fun initData() {
    firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener {
      if (it.isSuccessful) {
        val onboardJSon = JSONObject(firebaseRemoteConfig.getString("onboard"))
        illustrationUrl.value = onboardJSon.getString("illustration")
        quote.value = onboardJSon.getString("quote")
        author.value = onboardJSon.getString("author")
      }
    }
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
