package com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.speech.tts.TextToSpeech
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.FrequentItems
import java.util.*

class frequentListItemsAdapter(var ctx: Context, var arrayList: ArrayList<FrequentItems>) : RecyclerView.Adapter<frequentListItemsAdapter.frequentData>() {

    lateinit var tts:TextToSpeech
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): frequentData {
        val inflate = LayoutInflater.from(ctx).inflate(R.layout.row_frequentlist,null)
        return frequentData(inflate)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: frequentData, position: Int) {
        val position = arrayList[position]
        holder?.frequentText?.text = position.Description
        val bitmap = BitmapFactory.decodeByteArray(position.Image,0,position.Image!!.size)
        try {
            if(bitmap!==null)
                holder?.frequentImage?.setImageBitmap(bitmap)
        }catch (e:Exception)
        {
            e.printStackTrace()
        }

        holder!!.frequent_layout.setOnClickListener {
            tts = TextToSpeech(ctx,object :TextToSpeech.OnInitListener{
                override fun onInit(status: Int) {
                    if (status == TextToSpeech.SUCCESS) {
                        tts.setLanguage(Locale.ENGLISH)
                        tts.speak(position.Description, TextToSpeech.QUEUE_FLUSH, null)
                    }else{
                        Toast.makeText(ctx,"Language not Supported",Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }




    inner class frequentData(view : View) : RecyclerView.ViewHolder(view)
    {
        var frequentImage = view.findViewById<ImageView>(R.id.frequentImage)
        var frequentText = view.findViewById<TextView>(R.id.frequentText)
        var frequent_layout = view.findViewById<CardView>(R.id.frequent_layout)
    }

}