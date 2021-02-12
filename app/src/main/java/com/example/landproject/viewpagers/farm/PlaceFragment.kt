package com.example.landproject.viewpagers.farm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.landproject.FarmMapActivity
import com.example.landproject.MainActivity
import com.example.landproject.R
import com.example.landproject.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {
lateinit var binding:FragmentPlaceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPlaceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.okBtn.setOnClickListener {
            startActivity(Intent(this.context, FarmMapActivity::class.java))
        }
    }
}