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
    private lateinit var recyclerView:RecyclerView
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
        recyclerView.adapter = RecyclerAdapter(hukuiro)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return rootView

    }
    private inner class bccreta: View.OnClickListener{
        override fun onClick(view: View) {
            parentFragmentManager.popBackStack()
        }
    }




    companion object {

    }

}