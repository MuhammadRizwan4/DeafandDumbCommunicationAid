package com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.speech.tts.TextToSpeech
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.DataBaseHelper
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.FrequentItems
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.User
import java.util.*

class DairyItemAdapter(var ctx: Context, var arrayList: ArrayList<User>) : RecyclerView.Adapter<DairyItemAdapter.CustomViewHOlder>() {

    lateinit var tts: TextToSpeech
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHOlder {
        val inflate = LayoutInflater.from(ctx).inflate(R.layout.row_dairy_item, null)
        return CustomViewHOlder(inflate)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomViewHOlder, position: Int) {
        val position = arrayList[position]
        holder.DairyText.text = position.Description
        val bitmap = BitmapFactory.decodeByteArray(position.Image,0,position.Image!!.size)
        try {
            holder.DairyImage.setImageBitmap(bitmap)
        }catch (e:Exception)
        {
            Log.d("Exception","${e.printStackTrace()}")
            //e.printStackTrace()
        }
        holder.dairy_layout.setOnClickListener {
            tts = TextToSpeech(ctx,object :TextToSpeech.OnInitListener{
                override fun onInit(status: Int) {
                    if(status == TextToSpeech.SUCCESS)
                    {
                        tts.setLanguage(Locale.ENGLISH)
                        tts.speak(position.Description,TextToSpeech.QUEUE_FLUSH,null)
                    }else{
                        Toast.makeText(ctx,"Language Not Supported",Toast.LENGTH_SHORT).show()
                    }
                }

            })


            val item = FrequentItems()
            item.Description = position.Description
            item.Image = position.Image
            val d = DataBaseHelper(ctx)
            d.FrequentData(item)
            //Log.d("F-Text",item.Description)
        }


    }




    inner class CustomViewHOlder(view: View) : RecyclerView.ViewHolder(view) {
        val DairyImage = view.findViewById<ImageView>(R.id.DairyImage)
        val DairyText = view.findViewById<TextView>(R.id.DairyText)
        val dairy_layout = view.findViewById<CardView>(R.id.dairy_layout)
    }
}