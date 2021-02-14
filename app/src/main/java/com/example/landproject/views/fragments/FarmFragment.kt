package com.example.landproject.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.landproject.R
import com.example.landproject.views.activities.SearchFarmActivity
import com.example.landproject.databinding.FragmentFarmBinding

class FarmFragment : Fragment() {
    private lateinit var binding: FragmentFarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm,container,false)
        binding.fragment=this@FarmFragment
        return binding.root
    }
    fun goSearchFarm(){
        startActivity(Intent(this.context, SearchFarmActivity::class.java))
    }

}