package com.obennouna.imedia24.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to the Imedia24 App!\nYou can navigate on the left by Category or Search a specific product.\nBtw : Thanks again for the IOC explanation, I hope you'll still enjoy my profile :)"
    }
    val text: LiveData<String> = _text
}