package com.example.digitalcloset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list: List<YourDataModel>):RecyclerView.Adapter<OneViewHolder>() {

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

        val index = list[position]
        holder.clothe_id.text = index.id.toString()
        holder.clothes_name.text = index.ClothesName
        holder.clothes_type.text = index.ClothesType
        holder.clothes_color.text = index.ClothesColor

    }
}