package com.hfad.bitsandpizzas

import android.os.Bundle
import android.app.Activity


class OrderActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        actionBar.setDisplayHomeAsUpEnabled(true)
    }

}
