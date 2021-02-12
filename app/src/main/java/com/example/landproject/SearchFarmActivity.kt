package com.example.landproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.landproject.databinding.ActivitySearchFarmBinding
import com.example.landproject.viewpagers.FarmViewPagerAdapter
import com.example.landproject.viewpagers.farm.CropFragment
import com.google.android.material.tabs.TabLayoutMediator

class SearchFarmActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchFarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_farm)
        binding.lifecycleOwner = this
        binding.activity = this@SearchFarmActivity

        val tabLayout = binding.tabLayout
        val tabLayoutTextArray = arrayOf("작물","가격","크기","장소")
        val viewPager=binding.pager
        viewPager.apply {
            adapter=FarmViewPagerAdapter(this@SearchFarmActivity)
            orientation=ViewPager2.ORIENTATION_HORIZONTAL
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }
}