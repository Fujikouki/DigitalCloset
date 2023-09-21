package com.example.digitalcloset

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalcloset.databinding.OneViewBinding

class OneViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val clothes_type = view.findViewById<TextView>(R.id.clothes_type)
    val clothes_color = view.findViewById<TextView>(R.id.clothes_color)
    val clothes_name = view.findViewById<TextView>(R.id.huku_name)

}