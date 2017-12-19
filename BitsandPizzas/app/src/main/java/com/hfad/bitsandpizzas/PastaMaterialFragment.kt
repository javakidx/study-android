package com.hfad.bitsandpizzas

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by bioyang on 19/11/2017.
 */
class PastaMaterialFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val pastaRecycler = inflater?.inflate(R.layout.fragment_pasta_material, container, false) as RecyclerView

        val pastaNames = Array(Pasta.pastas.size, {i -> Pasta.pastas[i].name})
        val pastaImages = Array(Pasta.pastas.size, {i -> Pasta.pastas[i].imageResourceId})

        val adapter = CaptionedImageAdapter(pastaNames, pastaImages)
        pastaRecycler.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        pastaRecycler.layoutManager = layoutManager


//        return super.onCreateView(inflater, container, savedInstanceState)
        return pastaRecycler
    }
}