package com.example.digitalcloset

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.fragment.app.FragmentManager

class RecyclerAdapter(private val list: List<YourDataModel>):RecyclerView.Adapter<RecyclerAdapter.OneViewHolder>() {

    inner class OneViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val clothe_id = view.findViewById<TextView>(R.id.ID)
        val clothes_type = view.findViewById<TextView>(R.id.clothes_type)
        val clothes_color = view.findViewById<TextView>(R.id.clothes_color)
        val clothes_name = view.findViewById<TextView>(R.id.clothes_name)
        val clothes_image = view.findViewById<ImageView>(R.id.one_image)

        init {
            view.setOnClickListener{
                val position = adapterPosition
                val listpostion = list[position]
            }
        }


    }

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
        Glide.with(holder.itemView.getContext()).load(Uri.parse(index.ClothesImage)).into(holder.clothes_image)
        //holder.clothes_image.setImageURI(Uri.parse(index.ClothesImage))
        Log.d("URLAd",Uri.parse(index.ClothesImage).toString())
        holder.itemView.setOnClickListener(){
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val doalogFragment = ItemFragment()
            val args = Bundle()
            args.putString("id",index.id.toString())
            doalogFragment.setArguments(args);
            doalogFragment.show(fragmentManager,"ItemFragment")

        }

    }
}