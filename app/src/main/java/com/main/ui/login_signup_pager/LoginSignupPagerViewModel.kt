package com.example.ontime.ui.login_signup_pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginSignupPagerViewModel : ViewModel() {
    val pagerItem = MutableLiveData<Int>()

    fun setPagerSelectedItem(item: Int) {
        pagerItem.value = item
    }
}