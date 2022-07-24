package com.base
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel<*>>  : Fragment() {
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=makeViewModelProvider()
        subscribToLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutID(),container,false)
        return viewDataBinding.root
    }
    private fun subscribToLiveData() {
        viewModel.messageLiveData.observe(this,{
            showAlertDialog(it)
        })


        viewModel.showLoading.observe(this,{
            if (it){
                showLoading()
            }
            else{
                hideLoading()
            }
        })

    }

    private fun showAlertDialog(message:String) {
        MaterialAlertDialogBuilder(requireContext()).setMessage(message).setPositiveButton("yes")
        {dialog,which->
            dialog.dismiss()
        }.show()
    }


    var progressDialog : ProgressDialog?=null
    private fun showLoading() {
        progressDialog= ProgressDialog(requireContext())
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    private fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog=null
    }

    abstract fun getLayoutID (): Int
    abstract fun makeViewModelProvider (): VM
}