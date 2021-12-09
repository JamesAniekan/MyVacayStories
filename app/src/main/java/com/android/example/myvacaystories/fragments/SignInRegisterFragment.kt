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
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in_register, container, false)

        val application = requireNotNull(this.activity).application

        signInRegisterViewModel = ViewModelProvider(this, SrViewModelFactory(application)).get(SignInRegisterViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegister.setOnClickListener {
            binding.apply {
                etName.visibility = View.VISIBLE
                registerButton.visibility = View.VISIBLE
                signInButton.visibility = View.GONE
                tvLogIn.visibility = View.VISIBLE
            }
            it.visibility = View.GONE
        }

        binding.tvLogIn.setOnClickListener {
            binding.apply {
                etName.visibility = View.GONE
                registerButton.visibility = View.GONE
                signInButton.visibility = View.VISIBLE
                tvRegister.visibility = View.VISIBLE
            }
            it.visibility = View.GONE
        }

        binding.registerButton.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            signInRegisterViewModel.register(name,email,password)

        }
        binding.signInButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            signInRegisterViewModel.signInUser(email, password)
           }
    }
}