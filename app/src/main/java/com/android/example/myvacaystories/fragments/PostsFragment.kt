package com.android.example.myvacaystories.fragments

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.myvacaystories.R
import com.android.example.myvacaystories.adapters.PostsAdapter
import com.android.example.myvacaystories.databinding.FragmentPostsBinding
import com.android.example.myvacaystories.viewmodels.PostsViewModel


class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)

        binding.lifecycleOwner = this

        binding.postsList.layoutManager = LinearLayoutManager(this.requireContext())
        val adapter =  PostsAdapter()
        binding.postsList.adapter = adapter

        viewModel.postLists.observe(viewLifecycleOwner, {
            it?.let{
                adapter.submitList(it)
            }
        })
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

