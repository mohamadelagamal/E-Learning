package com.main.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.e_learning.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {

    companion object {
        const val TEST_VERIFY_CODE = "1234"
    }

    private var _binding: FragmentConfirmationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        initFocus()
        setListeners()
    }

    private fun setListeners() {
        binding.frameLayoutRoot.setOnClickListener {
            val inputMethodManager =
                activity?.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        setTextChangedListener(fromEditText = binding.et1, targetEditText = binding.et2)
        setTextChangedListener(fromEditText = binding.et2, targetEditText = binding.et3)
        setTextChangedListener(fromEditText = binding.et3, targetEditText = binding.et4)
        setTextChangedListener(fromEditText = binding.et4, done = {
            verifyOTPCode()
        })

        setKeyListener(fromEditText = binding.et2, backToEditText = binding.et1)
        setKeyListener(fromEditText = binding.et3, backToEditText = binding.et2)
        setKeyListener(fromEditText = binding.et4, backToEditText = binding.et3)

    }

    private fun initFocus() {
        binding.et1.isEnabled = true

        binding.et1.postDelayed({
            binding.et1.requestFocus()
            val inputMethodManager =
                activity?.getSystemService(android.view.inputmethod.InputMethodManager::class.java) as InputMethodManager
            inputMethodManager.showSoftInput(binding.et1, InputMethodManager.SHOW_FORCED)
        }, 500)
    }

    private fun resetFocus() {
        binding.et1.isEnabled = false
        binding.et2.isEnabled = false
        binding.et3.isEnabled = false
        binding.et4.isEnabled = false

        binding.et1.text.clear()
        binding.et2.text.clear()
        binding.et3.text.clear()
        binding.et4.text.clear()

        initFocus()
    }

    private fun setTextChangedListener(
        fromEditText: EditText,
        targetEditText: EditText? = null,
        done: (() -> Unit)? = null
    ) {
        fromEditText.addTextChangedListener { it1 ->
            it1?.let { String ->
                if (String.isNotEmpty()) {
                    targetEditText?.let { it ->
                        it.isEnabled = true
                        it.requestFocus()
                    }
                } else {
                    return@let
                } ?: run {
                    done?.let { done ->
                        done()
                    }
                }

                Log.d("ConfirmationFragment", "setKeyListener: KEYCODE")
                fromEditText.clearFocus()
                fromEditText.isEnabled = false
            }
        }
    }

    private fun setKeyListener(fromEditText: EditText, backToEditText: EditText) {
        fromEditText.setOnKeyListener { _, _, event ->
            if (null != event && event.keyCode == KeyEvent.KEYCODE_DEL) {
                backToEditText.isEnabled = true
                backToEditText.requestFocus()
                backToEditText.text.clear()

                fromEditText.clearFocus()
                fromEditText.isEnabled = false
                Log.d("ConfirmationFragment", "setKeyListener: KEYCODE_DEL")
            }
            false
        }
    }

    private fun verifyOTPCode() {
        val otpCode = binding.et1.text.toString()
            .trim() + binding.et2.text.toString()
            .trim() + binding.et3.text.toString()
            .trim() + binding.et4.text.toString().trim()
        if (4 != otpCode.length) {
            return
        }
        if (otpCode == TEST_VERIFY_CODE) {
            Toast.makeText(context, "Verified", Toast.LENGTH_SHORT).show()

            val inputMethodManager =
                activity?.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.et1.windowToken, 0)
            return
        }

        Toast.makeText(context, "Invalid OTP Code", Toast.LENGTH_SHORT).show()
        resetFocus()
    }
}