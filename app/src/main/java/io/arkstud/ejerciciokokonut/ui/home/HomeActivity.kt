package io.arkstud.ejerciciokokonut.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.ui.home.articles.ArticlesFragment
import io.arkstud.ejerciciokokonut.ui.home.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.LinkedList

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(homeToolbar)
        supportActionBar?.title = getString(R.string.app_name)
        setUpViewPager()
    }

    /**
     *
     */
    override fun onBackPressed() { moveTaskToBack(true) }

    /**
     *
     */
    private fun setUpViewPager() {
        val adapter = HomeViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ArticlesFragment(), getString(R.string.articles))
        adapter.addFragment(ProfileFragment(), getString(R.string.profile))
        homeViewPager.adapter = adapter
        homeTabLayout.setupWithViewPager(homeViewPager)
    }

    /**
     *
     */
    class HomeViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val fragments = LinkedList<Fragment>()
        private val fragmentTitles = LinkedList<String>()

        fun addFragment(fragment: Fragment, title: String){
            fragments.add(fragment)
            fragmentTitles.add(title)
        }

        /**
         *
         * @param position
         * @return [Fragment]
         */
        override fun getItem(position: Int): Fragment = fragments[position]

        /**
         *
         * @return [Int]
         */
        override fun getCount(): Int = fragments.size

        /**
         *
         * @param position
         * @return [CharSequence]
         */
        override fun getPageTitle(position: Int): CharSequence? = fragmentTitles[position]

    }
}
