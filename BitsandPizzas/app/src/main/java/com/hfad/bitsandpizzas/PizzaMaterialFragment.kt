package com.hfad.bitsandpizzas

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by bioyang on 19/11/2017.
 */
class PizzaMaterialFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val pizzaRecycler = inflater?.inflate(R.layout.fragment_pizza_material, container, false) as RecyclerView

        val pizzaNames = Array(Pizza.pizzas.size, {i -> Pizza.pizzas[i].name})

        val pizzaImages = Array(Pizza.pizzas.size, {i -> Pizza.pizzas[i].imageResourceId})

        val adapter = CaptionedImageAdapter(pizzaNames, pizzaImages)
        pizzaRecycler.adapter = adapter

        val layoutManager = LinearLayoutManager(activity)
        pizzaRecycler.layoutManager = layoutManager

        adapter.listener = object : CaptionedImageAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, PizzaDetailActivity::class.java)
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_NO, position)
                activity.startActivity(intent)
            }
        }

        return pizzaRecycler
    }
}