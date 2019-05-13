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
class InterfaceFragment : android.support.v4.app.Fragment() {


    private lateinit var HomeInterface: CardView
    private lateinit var OfficeInterface: CardView
    private lateinit var MarketInterface: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_interface, container, false)

        HomeInterface = view.findViewById<CardView>(R.id.home_card)
        OfficeInterface = view.findViewById<CardView>(R.id.office_card)
        MarketInterface = view.findViewById<CardView>(R.id.market_card)

        HomeInterface.setOnClickListener {

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, HomeCategories()).addToBackStack(null).commit()
        }

        OfficeInterface.setOnClickListener {
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,OfficeCatogeries()).commit()
        }

        MarketInterface.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,MarketCategries()).addToBackStack(null).commit()
        }

        return view

    }

}
