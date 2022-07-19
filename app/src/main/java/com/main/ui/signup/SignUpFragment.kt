package com.main.ui.signup

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.Slide
import android.view.*
import android.widget.Button
import android.widget.PopupWindow
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentSignupBinding
import com.example.e_learning.databinding.PopupChooseConfirmationBinding

class SignUpFragment() : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    private var _popBinding: PopupChooseConfirmationBinding? = null
    private val popBinding get() = _popBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignup.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val builder =
            AlertDialog.Builder(
                this.context, R.style.CustomAlertDialog
            )
                .create()

        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup_choose_confirmation, null)

        _popBinding = PopupChooseConfirmationBinding.bind(view)
        /*val lp = WindowManager.LayoutParams()
        lp.dimAmount = 0.9f
        builder.window?.attributes = lp*/

        builder.setView(view)
        popBinding.btnSendCode.setOnClickListener {
            builder.dismiss()
            findNavController().navigate(R.id.action_LoginSignupPagerFragment_to_confirmationFragment)
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
}
