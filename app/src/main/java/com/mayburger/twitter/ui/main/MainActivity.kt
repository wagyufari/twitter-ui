package com.mayburger.twitter.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.mayburger.twitter.R
import com.mayburger.twitter.databinding.ActivityMainBinding
import com.mayburger.twitter.databinding.TabItemBinding
import com.mayburger.twitter.utils.TabPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        val profileUrl =
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"

        Glide.with(this)
            .load(profileUrl)
            .circleCrop()
            .into(binding.profile)

        binding.pager.adapter = TabPagerAdapter(
            this,
            arrayListOf(
                HomeFragment.newInstance(),
                HomeFragment.newInstance(),
                HomeFragment.newInstance(),
                HomeFragment.newInstance()
            )
        )

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.customView = TabItemBinding.inflate(LayoutInflater.from(this)).apply {
                this.image.setImageResource(getTabIcons()[position])
            }.root
        }.attach()
    }

    fun getTabIcons():ArrayList<Int>{
        return arrayListOf(
            R.drawable.ic_home_primary,
            R.drawable.ic_hot_primary,
            R.drawable.ic_notification_primary,
            R.drawable.ic_message_primary,
        )
    }

}