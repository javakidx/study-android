package com.hfad.bitsandpizzas


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout


/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val layout = inflater?.inflate(R.layout.fragment_top, container, false) as RelativeLayout

        val pizzaRecycler = layout.findViewById(R.id.pizza_recycler) as RecyclerView

        val pizzaNames = Array(2, {i -> Pizza.pizzas[i].name})
        val pizzaImages = Array(2, {i -> Pizza.pizzas[i].imageResourceId})

        val layoutManager = GridLayoutManager(activity, 2)

        pizzaRecycler.layoutManager = layoutManager

        val adapter = CaptionedImageAdapter(pizzaNames, pizzaImages)
        pizzaRecycler.adapter = adapter

        adapter.listener = object : CaptionedImageAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, PizzaDetailActivity::class.java)
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_NO, position)
                activity.startActivity(intent)
            }

        }
        // Inflate the layout for this fragment
//        return inflater!!.inflate(R.layout.fragment_top, container, false)
        return layout
    }

}// Required empty public constructor
