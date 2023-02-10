package com.staynight.numbersconvertercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.staynight.numbersconvertercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val tabConverter = 0
    private val tabOperations = 1

    private val converterFragment = ConverterFragment()
    private val operationFragment = OperationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            listOf(converterFragment, operationFragment)
        )
        binding?.viewPager?.adapter = adapter

        TabLayoutMediator(binding?.tabLayout!!, binding?.viewPager!!) { tab, position ->
            when (position) {
                tabConverter -> {
                    tab.text = "Converter"
                }

                tabOperations -> {
                    tab.text = "Operations"
                }
            }
        }.attach()
    }
}