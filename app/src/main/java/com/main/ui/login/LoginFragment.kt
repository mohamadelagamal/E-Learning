package com.main.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentLoginBinding
import com.main.ui.home.HomeActivity

class LoginFragment() : BaseFragment<FragmentLoginBinding,LoginViewModel>(),Navgator {

    override fun getLayoutID(): Int {
        return R.layout.fragment_login
    }

    override fun makeViewModelProvider(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vmLogin = viewModel
        viewModel.navigator = this
    }

    override fun openHome() {
        val intent = Intent(requireActivity(),HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}