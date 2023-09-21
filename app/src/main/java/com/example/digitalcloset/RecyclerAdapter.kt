package com.example.digitalcloset

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list: ArrayList<ClothesDetailData>):RecyclerView.Adapter<OneViewHolder>() {

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
        holder.clothes_type.text = list[position].ClothesType
        holder.clothes_color.text = list[position].ClothesColor
        holder.clothes_name.text = list[position].ClothesName
    }
}