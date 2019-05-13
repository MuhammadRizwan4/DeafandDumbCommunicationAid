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
import com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter.FoodAdapter
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
class FoodItems : android.support.v4.app.Fragment() {

    lateinit var food_recyclerView : RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_food_items, container, false)

        var database = DataBaseHelper(activity!!)
        var sql = database.readableDatabase
        food_recyclerView = view.findViewById<RecyclerView>(R.id.food_recyclerView)
        food_recyclerView.layoutManager = LinearLayoutManager(activity)
        var adapter = FoodAdapter(activity!!,database!!.FoodData())
        food_recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        val itemTouch = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
            override fun onMove(p0: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                var source = source!!.adapterPosition
                var target = target!!.adapterPosition
                Collections.swap(database.FoodData(),source,target)
                adapter.notifyItemMoved(source,target)
                return true
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

            }

        })
        itemTouch.attachToRecyclerView(food_recyclerView)

        return view
    }
}
