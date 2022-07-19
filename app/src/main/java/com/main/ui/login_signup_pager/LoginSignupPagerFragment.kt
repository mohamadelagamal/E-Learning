package com.main.ui.login_signup_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.e_learning.databinding.FragmentLoginSignupPagerBinding
import com.example.ontime.ui.login_signup_pager.LoginSignupPagerViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoginSignupPagerFragment : Fragment() {

    private var _binding: FragmentLoginSignupPagerBinding? = null
    private lateinit var viewModel: LoginSignupPagerViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginSignupPagerBinding.inflate(inflater, container, false)

        val fMr = childFragmentManager
        val sectionPagerAdapter = LoginSignupPagerAdapter(fMr, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = context?.resources?.getString(sectionPagerAdapter.titles[position])
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginSignupPagerViewModel::class.java]

        viewModel.pagerItem.observe(viewLifecycleOwner) {
            binding.viewPager.currentItem = it
        }
    }
}