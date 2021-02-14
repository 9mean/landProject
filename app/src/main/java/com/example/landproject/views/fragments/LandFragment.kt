package com.example.landproject.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.landproject.R
import com.example.landproject.databinding.FragmentFarmBinding
import com.example.landproject.databinding.FragmentLandBinding
import com.example.landproject.views.activities.LandActivity
import com.example.landproject.views.activities.SearchFarmActivity

class LandFragment : Fragment() {
    private lateinit var binding: FragmentLandBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_land,container,false)
        binding.fragment=this@LandFragment
        return binding.root
    }
    fun goAddLand(){
        startActivity(Intent(this.context, LandActivity::class.java))
    }
}