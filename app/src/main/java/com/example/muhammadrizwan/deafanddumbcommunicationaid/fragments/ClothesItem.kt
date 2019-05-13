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
import com.example.muhammadrizwan.deafanddumbcommunicationaid.adapter.ClothesItemAdapter
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
class ClothesItem : android.support.v4.app.Fragment() {

    var itemTouch: ItemTouchHelper? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_clothes_item, container, false)

        val db = DataBaseHelper(activity!!)
        var sql = db.readableDatabase

        val recyclerView = view.findViewById<RecyclerView>(R.id.clothes_recyclerView)
        val adapter = ClothesItemAdapter(activity!!,db.ClothData(),recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()

        itemTouch = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
            override fun onMove(p0: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val source = source!!.adapterPosition
                val target = target!!.adapterPosition
                Collections.swap(db.ClothData(),source,target)
                adapter.notifyItemMoved(source,target)
                return true
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

            }

        })
        itemTouch?.attachToRecyclerView(recyclerView)

        return view
    }
}


