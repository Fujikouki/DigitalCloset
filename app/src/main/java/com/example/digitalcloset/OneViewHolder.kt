package com.example.digitalcloset

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcloset.databinding.OneViewBinding

class OneViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val oneImage = view.findViewById<ImageView>(R.id.oneIm)
    val oneText = view.findViewById<TextView>(R.id.oneTe)

}