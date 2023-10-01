package com.example.digitalcloset

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class ItemFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("服の詳細")



            val id = requireArguments()!!.getString("id", "")

            builder.setPositiveButton("服の詳細",DialogButtonClickLister(id))
            builder.setNegativeButton("服を削除",DialogButtonClickLister(id))

            builder.setMessage(id)

            Log.d("dialog1",id.toString())

            builder.create()
        }
        return dialog ?: throw IllegalAccessException("アクティビティがnull")
    }

    private inner class DialogButtonClickLister(_id:String):DialogInterface.OnClickListener{
        private val id = _id
        override fun onClick(dialog: DialogInterface?, which: Int) {
            var msg = ""
            val Dbcloth = DbRelated(requireContext())
            when(which){
                DialogInterface.BUTTON_POSITIVE ->
                    msg = "OK"
                DialogInterface.BUTTON_NEGATIVE ->
                    Dbcloth.Dbdeletion(id)
            }
            Log.d("dialog",id.toString())
        }
    }

}