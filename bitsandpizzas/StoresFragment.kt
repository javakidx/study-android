package com.hfad.bitsandpizzas

import android.app.ListFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * Created by bioyang on 26/06/2017.
 */
class StoresFragment : ListFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(inflater?.context,
                                                                android.R.layout.simple_list_item_1,
                                                                resources.getStringArray(R.array.stores))

        listAdapter = arrayAdapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}