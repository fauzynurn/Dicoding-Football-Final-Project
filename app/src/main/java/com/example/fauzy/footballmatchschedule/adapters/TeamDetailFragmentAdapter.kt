package com.example.fauzy.footballmatchschedule.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TeamDetailFragmentAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){

    var fragmentList  = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    fun addFragment(fragment: Fragment, title : String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
    override fun getPageTitle(position: Int) = titleList[position]

    override fun getItem(p0: Int) = fragmentList[p0]

    override fun getCount() = fragmentList.size

}