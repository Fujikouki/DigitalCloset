package com.example.digitalcloset

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    var addlist = ArrayList<ClothesDetailData>()
    private lateinit var recyclerView:RecyclerView
    private var recyclerAdapter = RecyclerAdapter(addlist)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val hukusyu = arguments?.getString("hukusyu")?:""
        val hukuiro = arguments?.getString("hukuiro")?:""
        val hukuname = arguments?.getString("hukuname")?:""
        Log.d("mainfragment",hukuiro)
        Log.d("mainfragment",hukusyu)
        Log.d("mainfragment",hukuname)

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val bcButton = rootView.findViewById<Button>(R.id.bcButton)
        bcButton.setOnClickListener(bccreta())
        recyclerView = rootView.findViewById(R.id.recvi)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        Log.d("始まり", "Adding data: $addlist")
        dataadd(hukusyu,hukuiro,hukuname)
        return rootView

    }

    private inner class bccreta: View.OnClickListener{
        override fun onClick(view: View) {

            parentFragmentManager.popBackStack()

        }
    }
    fun dataadd(huku_typ:String,huku_iro: String,huku_name: String){
        val data = ClothesDetailData(huku_typ,huku_iro,huku_name)
        Log.d("MainFragment", "Adding data: $data")
        addlist.add(data)
        Log.d("MainFragment", "Data added, list size: ${addlist.size}")
        recyclerAdapter.notifyItemInserted(addlist.lastIndex)
    }

    companion object {

    }

}