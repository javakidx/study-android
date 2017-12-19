package com.hfad.bitsandpizzas

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by bioyang on 18/11/2017.
 */

class CaptionedImageAdapter(private val captions: Array<String>, private val imageIds: Array<Int>) : RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder>() {
//    private var captions : Array<String>? = null
//    private var imageIds : Array<Int>? = null

     var listener: Listener? = null
//        set(value) {this.listener = value}


    interface Listener {
        fun onClick(position: Int)
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent?.context).inflate(R.layout.card_captioned_image, parent, false) as CardView

        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val cardView = holder?.cardView
        val imageView = cardView?.findViewById(R.id.info_image) as ImageView
        val drawable = cardView?.resources.getDrawable(imageIds[position])

        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]

        val textView = cardView.findViewById(R.id.info_text) as TextView
        textView.text = captions[position]

        cardView.setOnClickListener(View.OnClickListener { listener?.onClick(position) })
    }

    override fun getItemCount(): Int = this.captions.size


}
