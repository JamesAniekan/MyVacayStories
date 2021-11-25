package com.android.example.myvacaystories.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.example.myvacaystories.R
import com.android.example.myvacaystories.databinding.FragmentSignInRegisterBinding
import com.android.example.myvacaystories.viewmodels.SignInRegisterViewModel
import com.android.example.myvacaystories.viewmodels.SrViewModelFactory

class SignInRegisterFragment : Fragment() {

    private lateinit var binding: FragmentSignInRegisterBinding
    private lateinit var signInRegisterViewModel: SignInRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in_register, container, false)

        val application = requireNotNull(this.activity).application

        signInRegisterViewModel = ViewModelProvider(this, SrViewModelFactory(application)).get(SignInRegisterViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            signInRegisterViewModel.register(email,password)

        }
        //binding.signInButton.setOnClickListener {
          //  }
    }
}