package com.android.example.myvacaystories.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.example.myvacaystories.R
import com.android.example.myvacaystories.databinding.FragmentNewPostBinding
import com.android.example.myvacaystories.model.NetworkStatus
import com.android.example.myvacaystories.viewmodels.NewPostViewModel


private const val TAG = "NewPost"

class NewPostFragment : Fragment() {

private lateinit var binding: FragmentNewPostBinding
private var imageUri: Uri? = null
private lateinit var getResult: ActivityResultLauncher<Intent>
private lateinit var viewModel: NewPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                imageUri = it.data?.data
                Log.i(TAG, "imageUri $imageUri")
                binding.postImage.setImageURI(imageUri)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_post, container, false)
        viewModel = ViewModelProvider(this).get(NewPostViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chooseImgBtn.setOnClickListener {
            chooseImage()
        }
        binding.submitBtn.setOnClickListener {
            val description = binding.postDescription.text.toString()
            if (description.isBlank()){
                Toast.makeText(requireContext(), "Enter description", Toast.LENGTH_LONG).show()
            }
            if (imageUri == null){
                Toast.makeText(requireContext(), "No image to show", Toast.LENGTH_LONG).show()
            }

            imageUri?.let { imageUri ->
                viewModel.newPost(description, imageUri) }
            }

        viewModel.networkState.observe(viewLifecycleOwner, {
            when(it){
                NetworkStatus.DONE -> Toast.makeText(requireContext(), "POST SAVED", Toast.LENGTH_LONG).show()
                NetworkStatus.ERROR -> Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
                NetworkStatus.LOADING -> Toast.makeText(requireContext(), "LOADING", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun chooseImage() {
        val getImage = Intent(Intent.ACTION_GET_CONTENT)
        getImage.type = "image/*"
        if(getImage.resolveActivity(requireActivity().packageManager)!= null){
            getResult.launch(getImage)
        }

    }


}