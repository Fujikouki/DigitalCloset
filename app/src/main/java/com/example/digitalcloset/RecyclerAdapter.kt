package com.example.digitalcloset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter:RecyclerView.Adapter<OneViewHolder>() {

    private val  list = listOf("Tシャツ","スーツ","ジャケット","パンツ","短パン")
    private val  imgli = listOf(R.drawable.huku,R.drawable.huku,R.drawable.huku,R.drawable.huku,R.drawable.huku)

    //一行分のXMLとOneViewHolderを連結させる
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneViewHolder {
        val OneVi = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_view,parent,false)
        return OneViewHolder(OneVi)
    }
    //データの数を数える
    override fun getItemCount(): Int {
        return list.size
    }
    //position番目のデータをXMLに表示させる
    //データの数を数える
    override fun onBindViewHolder(holder: OneViewHolder, position: Int) {
        holder.oneImage.setImageResource(imgli[position])
        holder.oneText.text = list[position]
    }
}