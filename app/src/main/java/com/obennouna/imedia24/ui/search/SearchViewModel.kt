package com.obennouna.imedia24.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "In due time, this screen can implement a search feature with the name (only found by categoryID or SKU in the doc)"
    }
    val text: LiveData<String> = _text
}