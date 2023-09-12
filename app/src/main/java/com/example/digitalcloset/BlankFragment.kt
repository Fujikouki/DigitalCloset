package com.example.digitalcloset

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    private var namelist = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)

        val saveButton = rootView.findViewById<Button>(R.id.savebutton)
        val listener = SaveListener()



        saveButton.setOnClickListener(listener)

        return rootView
    }

    private inner class SaveListener:View.OnClickListener{
        override fun onClick(view: View?) {

            Log.d("save", view.toString())


            val hukuSpsyu = view?.findViewById<Spinner>(R.id.spinner_huku) ?: requireView().findViewById(R.id.spinner_huku)
            val hukuSpiro = view?.findViewById<Spinner>(R.id.spinner_iro) ?: requireView().findViewById(R.id.spinner_iro)
            val hukuEdit = requireView().findViewById<EditText>(R.id.huku_name)

            val hukusyu:String
            val hukuiro:String
            val hukuname:String

            hukusyu = hukuSpsyu.selectedItem.toString()
            hukuiro = hukuSpiro.selectedItem.toString()
            hukuname = hukuEdit.text.toString()
            Log.d("seikou",hukuiro)
            Log.d("seikou",hukusyu)
            Log.d("seikou",hukuname)
            

        }
    }

}