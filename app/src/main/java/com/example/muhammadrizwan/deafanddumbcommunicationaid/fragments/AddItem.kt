package com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.DataBaseHelper
import com.example.muhammadrizwan.deafanddumbcommunicationaid.models.User
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddItem : android.support.v4.app.Fragment() {


    lateinit var imageBtn: Button
    lateinit var Description: EditText
    private lateinit var user: User
    lateinit var category: EditText
    lateinit var add: Button
    private val GalleryCode: Int = 5
    var byteImage: ByteArray? = null
    private lateinit var db: DataBaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_item, container, false)

        user = User()
        db = DataBaseHelper(activity!!.applicationContext)
        db.readableDatabase
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        imageBtn = view.findViewById<Button>(R.id.imageBtn)
        Description = view.findViewById<EditText>(R.id.Description)
        category = view.findViewById<EditText>(R.id.category)
        add = view.findViewById<Button>(R.id.add)

        imageBtn.setOnClickListener {
            OpenGallery()
        }
        add.setOnClickListener {
            if (!Description.text.isEmpty()) {
                user.Description = Description.text.toString().trim()
                if (!category.text.isEmpty()) {
                    user.Category = category.text.toString().toUpperCase().trim()
                } else {
                    category.setError("Enter Category Name")
                }

            } else {
                Description.setError("Enter Description")
            }
            db.insertData(user)
            Log.d("User", "${user.Image},${user.Category},${user.Description}")
        }
    }
    private fun OpenGallery() {
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GalleryCode)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(activity!!,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), GalleryCode)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GalleryCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            OpenGallery()
        } else {
            Toast.makeText(activity, "Error Opening Gallery", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GalleryCode && resultCode == Activity.RESULT_OK) {

            val uri = data!!.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
            val drawable  = BitmapDrawable(bitmap)
            imageBtn.setBackgroundDrawable(drawable)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            byteImage = stream.toByteArray()
            if (byteImage !== null) {
                user.Image = byteImage
            }
        }
    }

}
