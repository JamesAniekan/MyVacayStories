package com.android.example.myvacaystories.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.example.myvacaystories.R
import com.android.example.myvacaystories.databinding.FragmentSignInRegisterBinding

class SignInRegisterFragment : Fragment() {

    private lateinit var binding: FragmentSignInRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in_register, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            binding.etName.visibility = View.VISIBLE
        }
        binding.signInButton.setOnClickListener {
            binding.etName.visibility = View.GONE
        }
    }
}