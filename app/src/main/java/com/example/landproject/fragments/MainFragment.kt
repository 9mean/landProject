package com.example.landproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.landproject.R
import com.example.landproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        binding.fragment=this@MainFragment
        return binding.root
    }
    fun goLand(){
        val direction: NavDirections =
                MainFragmentDirections.actionMainFragmentToLandFragment()
        findNavController().navigate(direction)
    }
    fun goFarm(){
        val direction: NavDirections =
                MainFragmentDirections.actionMainFragmentToFarmFragment()
        findNavController().navigate(direction)
    }
    fun goStore(){
        val direction: NavDirections =
                MainFragmentDirections.actionMainFragmentToStoreFragment()
        findNavController().navigate(direction)
    }

}