package com.hfad.bitsandpizzas


/**
 * Created by bioyang on 15/11/2017.
 */

class Pizza(val name: String, val imageResourceId: Int) {
    companion object {
        val pizzas = arrayOf(Pizza("Diavolo", R.drawable.diavolo), Pizza("Funghi", R.drawable.funghi))
    }
}
