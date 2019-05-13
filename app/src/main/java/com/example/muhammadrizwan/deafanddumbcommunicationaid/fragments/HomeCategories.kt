package com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.muhammadrizwan.deafanddumbcommunicationaid.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeCategories : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home_categories, container, false)

        val Clothes_card = view.findViewById<CardView>(R.id.Clothes_card)
        Clothes_card.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,ClothesItem()).addToBackStack(null).commit()
        }
        val food_card = view.findViewById<CardView>(R.id.food_card)
        food_card.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,FoodItems()).addToBackStack(null).commit()
        }
        val EA_card = view.findViewById<CardView>(R.id.EA_card)
        EA_card.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,ElectricalAppliancesItem()).addToBackStack(null).commit()
        }

        return view
    }
}
