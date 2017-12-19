package com.hfad.bitsandpizzas

/**
 * Created by bioyang on 19/11/2017.
 */
class Pasta(val name: String, val imageResourceId: Int) {
    companion object {
        val pastas = arrayOf(Pasta("Spaghetti Bolognese", R.drawable.funghi), Pasta("Lasagne", R.drawable.diavolo))
    }
}