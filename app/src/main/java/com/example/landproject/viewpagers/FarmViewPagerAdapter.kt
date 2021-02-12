package com.example.landproject.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.landproject.viewpagers.farm.CropFragment
import com.example.landproject.viewpagers.farm.PlaceFragment
import com.example.landproject.viewpagers.farm.PriceFragment
import com.example.landproject.viewpagers.farm.SizeFragment

class FarmViewPagerAdapter(fragment:FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CropFragment()
            1 -> PlaceFragment()
            2 -> PriceFragment()
            3-> SizeFragment()
            //일단은 이렇게 처리
            else -> SizeFragment()
        }
    }
}