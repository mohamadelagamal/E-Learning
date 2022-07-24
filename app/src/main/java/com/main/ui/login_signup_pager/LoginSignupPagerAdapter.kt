package com.main.ui.login_signup_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.e_learning.R
import com.main.ui.login.LoginFragment
import com.main.ui.signup.SignUpFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class LoginSignupPagerAdapter(fm: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {
    val titles = TAB_TITLES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment()
            1 -> SignUpFragment()
           else->{
               LoginFragment()
           }
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}