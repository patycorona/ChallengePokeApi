package com.example.challengepokeapi.ui.login.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.challengepokeapi.R
import com.example.challengepokeapi.databinding.FragmentLoginBinding
import com.example.challengepokeapi.domain.model.ConstantGeneral
import com.example.challengepokeapi.domain.model.ResultModel
import com.example.challengepokeapi.domain.model.UserModel
import com.example.challengepokeapi.ui.login.viewmodel.FbAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    private val fbAuthViewModel: FbAuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context), null, false)

        initListener()
        initObserver()
        return binding?.root
    }

    private var authResultObserver  = Observer<ResultModel> { resultModel ->
        if (resultModel.code == ConstantGeneral.CODE) {
            Toast.makeText(
                requireContext(), R.string.msg_your_welcome,
                Toast.LENGTH_SHORT
            ).show()

        } else {
            Toast.makeText(
                requireContext(), ConstantGeneral.MSG_ERROR,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initObserver() {
        fbAuthViewModel.userResultModel.observe(viewLifecycleOwner, authResultObserver)
    }

    private fun initListener(){

        binding?.apply {
            btnSingUp?.setOnClickListener {

                if(!edUserName?.text.isNullOrEmpty() && !edPassword?.text.isNullOrEmpty())
                    validaUser(edUserName?.text.toString(),edPassword?.text.toString())
                    else Toast.makeText(
                    requireContext(), R.string.msg_empty_field,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private  fun validaUser(email: String, pwd :String) {

        val user = UserModel(email, pwd)
        fbAuthViewModel.authFireBase(user)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}