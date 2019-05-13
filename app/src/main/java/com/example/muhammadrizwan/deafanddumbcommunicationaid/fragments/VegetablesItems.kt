package com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter.VegetableItemAdapeter
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.DataBaseHelper
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class VegetablesItems : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_vegetables_items, container, false)
        var database = DataBaseHelper(activity!!)
        var sql = database.readableDatabase

        val vegetable_recyclerView = view.findViewById<RecyclerView>(R.id.vegetable_recyclerView)
        var adapter = VegetableItemAdapeter(activity!!,database.vegetableData())
        vegetable_recyclerView.layoutManager = LinearLayoutManager(activity)
        vegetable_recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        val itemTouch = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,0){
            override fun onMove(p0: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                var source = source!!.adapterPosition
                var target = target!!.adapterPosition
                adapter.notifyItemMoved(source,target)
                Collections.swap(database.vegetableData(),source,target)
                return true
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

            }

        })

        itemTouch.attachToRecyclerView(vegetable_recyclerView)
        return view

    }
}
