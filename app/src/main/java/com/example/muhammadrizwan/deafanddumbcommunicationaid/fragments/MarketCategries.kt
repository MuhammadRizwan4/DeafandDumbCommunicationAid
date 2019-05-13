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
class MarketCategries : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_market_categries, container, false)

        val Dairy_card = view.findViewById<CardView>(R.id.Dairy_card)
        Dairy_card.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,DairyItems()).addToBackStack(null).commit()
        }
        val vegetable_card = view.findViewById<CardView>(R.id.vegetable_card)
        vegetable_card.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,VegetablesItems()).addToBackStack(null).commit()
        }


        return view
    }
}
