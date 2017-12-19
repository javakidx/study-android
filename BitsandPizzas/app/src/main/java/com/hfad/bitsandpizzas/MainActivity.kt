package com.hfad.bitsandpizzas

import android.app.Activity
import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ShareActionProvider

class MainActivity : Activity() {

    private var shareActionProvider : ShareActionProvider? = null

    private var titles: Array<String>? = null
    private var drawerList: ListView? = null
    private var drawerLayout: DrawerLayout? = null

    private var drawerToggle: ActionBarDrawerToggle? = null

    private var currentPosition = 0

    companion object {
        val TAG_NAME = "visible_fragment"
    }

    inner class DrawerItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectItem(position)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titles = resources.getStringArray(R.array.titles)

        drawerList = findViewById(R.id.drawer) as ListView

        drawerList?.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)

        drawerList?.onItemClickListener = DrawerItemClickListener()

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position")
            setActionBarTitle(currentPosition)
        } else {
            selectItem(0)
        }

        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }
        }

        drawerLayout?.addDrawerListener(drawerToggle as ActionBarDrawerToggle)

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)

        fragmentManager.addOnBackStackChangedListener {
            val fragment = fragmentManager.findFragmentByTag(TAG_NAME)

            currentPosition = when(fragment) {
                is TopFragment -> 0
                //is PizzaFragment -> 1
                is PizzaMaterialFragment -> 1
                is PastaFragment -> 2
                is StoresFragment -> 4
                else -> 0
            }

            setActionBarTitle(currentPosition)
            drawerList?.setItemChecked(currentPosition, true)
        }
    }
    
    private fun selectItem(position: Int) {
        this.currentPosition = position

        val fragment: Fragment = when(position) {
            //1 -> PizzaFragment()
            1 -> PizzaMaterialFragment()
            2 -> PastaFragment()
            3 -> StoresFragment()
            else -> TopFragment()
        }

        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.content_frame, fragment, TAG_NAME)
        ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()

        setActionBarTitle(position)

        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        drawerLayout?.closeDrawer(drawerList)
    }

    private fun setActionBarTitle(position: Int) {
        val title: String = when(position) {
            0 -> resources.getString(R.string.app_name)

            else -> titles!![position]
        }

        actionBar.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu?.findItem(R.id.action_share)
        shareActionProvider = menuItem?.actionProvider as ShareActionProvider
        setIntent("This is example text")

        return super.onCreateOptionsMenu(menu)
    }

    private fun  setIntent(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)

        shareActionProvider?.setShareIntent(intent)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }

        return when (item?.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                
                return true
            }
            R.id.action_settings -> true

            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val drawerOpen = drawerLayout?.isDrawerOpen(drawerList)
        menu?.findItem(R.id.action_share)?.isVisible = !drawerOpen!!

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt("position", currentPosition)
    }
}
