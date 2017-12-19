package com.hfad.bitsandpizzas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ShareActionProvider
import android.widget.TextView

class PizzaDetailActivity : Activity() {
    private var shareActionProvider : ShareActionProvider? = null

    companion object {
        val EXTRA_PIZZA_NO = "pizzaNo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//
//        val fab = findViewById(R.id.fab) as FloatingActionButton
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        actionBar.setDisplayHomeAsUpEnabled(true)

        val pizzaNo = intent.extras.get(EXTRA_PIZZA_NO) as Int
        val pizzaName = Pizza.pizzas[pizzaNo].name

        val textView = findViewById(R.id.pizza_text) as TextView
        textView.text = pizzaName

        val pizzaImage = Pizza.pizzas[pizzaNo].imageResourceId

        val imageView = findViewById(R.id.pizza_image) as ImageView
        imageView.setImageDrawable(resources.getDrawable(pizzaImage))
        imageView.contentDescription = pizzaName
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val textView = findViewById(R.id.pizza_text) as TextView
        val pizzaName = textView.text
        val menuItem = menu?.findItem(R.id.action_share)

        shareActionProvider = menuItem?.actionProvider as ShareActionProvider

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, pizzaName)
        shareActionProvider!!.setShareIntent(intent)

        return true

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
