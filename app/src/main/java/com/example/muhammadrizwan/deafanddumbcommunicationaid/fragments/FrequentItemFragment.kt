package com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter.frequentListItemsAdapter
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.DataBaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FrequentItemFragment : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val database = DataBaseHelper(activity!!)
        val view =  inflater.inflate(R.layout.fragment_frequent_item, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.frequentItem_recyclerView)
        val adapter = frequentListItemsAdapter(activity!!,database.getFrequentData())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()


        return view
    }
}

