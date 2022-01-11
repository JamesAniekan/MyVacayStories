package com.android.example.myvacaystories.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.example.myvacaystories.R
import com.android.example.myvacaystories.databinding.FragmentPostsBinding


class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabNewPost.setOnClickListener{
            this.findNavController().navigate(
                PostsFragmentDirections.actionPostsFragmentToNewPostFragment()
            )
        }
    }

}