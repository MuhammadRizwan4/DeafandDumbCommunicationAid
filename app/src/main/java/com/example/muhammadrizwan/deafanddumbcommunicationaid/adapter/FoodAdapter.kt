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
import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.DataBaseHelper
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.FrequentItems
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.User
import java.util.*

class FoodAdapter(var ctx: Context, var arrayList: ArrayList<User>) : RecyclerView.Adapter<FoodAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val inflate = LayoutInflater.from(ctx).inflate(R.layout.row_fooditems,null)
        return CustomViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val position = arrayList[position]
        holder!!.foodText.text = position.Description
        val bitmap = BitmapFactory.decodeByteArray(position.Image,0,position.Image!!.size)
        try {
            holder.foodImage.setImageBitmap(bitmap)
        }catch (e:Exception) {
            e.printStackTrace()
        }

        holder.food_layout?.setOnClickListener {
            tts = TextToSpeech(ctx,object : TextToSpeech.OnInitListener
            {
                override fun onInit(status: Int) {
                    if(status == TextToSpeech.SUCCESS)
                    {
                        tts.setLanguage(Locale.ENGLISH)
                        tts.speak(position.Description,TextToSpeech.QUEUE_FLUSH,null)
                    }
                }

            })

            val item = FrequentItems()
            val db = DataBaseHelper(ctx)
            item.Description = position.Description
            item.Image = position.Image
            db.FrequentData(item)
        }

    }

    lateinit var tts : TextToSpeech





    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val foodImage = view.findViewById<ImageView>(R.id.foodImage)
        val foodText = view.findViewById<TextView>(R.id.foodText)
        val food_layout = view.findViewById<CardView>(R.id.food_layout)
    }

}
