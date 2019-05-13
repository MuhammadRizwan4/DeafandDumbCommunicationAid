package com.example.muhammadrizwan.deafanddumbcommunicationaid.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments.DocumentItems

class DataBaseHelper(var ctx: Context) : SQLiteOpenHelper(ctx,"My-Database",null,3) {


    private lateinit var db: SQLiteDatabase
    var Cloth = "Clothes"
    var Food = "Food"
    var Electrical_Applicances = "[Electrical Applicances]"
    var Dairy = "Dairy"
    var vegetables = "Vegetables"
    var frequentList = "[Frequent Items]"
    var document_item = "Documents"

    override fun onCreate(db: SQLiteDatabase?) {
        val ClothTable = "CREATE TABLE $Cloth(_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT , IMAGE BLOB)"
        val FoodTable = "CREATE TABLE $Food (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT , IMAGE BLOB)"
        val EATable = "CREATE TABLE $Electrical_Applicances (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT , IMAGE BLOB)"
        val DairyTable = "CREATE TABLE $Dairy (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT , IMAGE BLOB)"
        val vegetableTable = "CREATE TABLE $vegetables (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT , IMAGE BLOB)"
        val frequentListItems = "CREATE TABLE $frequentList (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT ,IMAGE BLOB)"
        val documents = "CREATE TABLE $document_item (_id INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT ,IMAGE BLOB)"

        db?.execSQL(ClothTable)
        db?.execSQL(FoodTable)
        db?.execSQL(EATable)
        db?.execSQL(DairyTable)
        db?.execSQL(vegetableTable)
        db?.execSQL(frequentListItems)
        db!!.execSQL(documents)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(user: User) {

        db = writableDatabase
        val values = ContentValues()
        if (user.Category.equals("CLOTHES")) {
            values.put("DESCRIPTION", user.Description)
            values.put("IMAGE", user.Image)
            db.insertOrThrow(Cloth, null, values)
            Toast.makeText(ctx, "Inserted in ${Cloth}", Toast.LENGTH_SHORT).show()
            //Log.d("data", "${values}")
        }
        if (user.Category == "FOOD") {
            values.put("DESCRIPTION", user.Description)
            values.put("IMAGE", user.Image)
            db.insertOrThrow(Food, null, values)
            Toast.makeText(ctx, "Inserted in ${Food}", Toast.LENGTH_SHORT).show()
        }
        if (user.Category.equals("ELECTRICAL APPLIANCES")) {
            values.put("DESCRIPTION", user.Description)
            values.put("IMAGE", user.Image)
            db.insertOrThrow(Electrical_Applicances, null, values)
            Toast.makeText(ctx, "Inserted in ${Electrical_Applicances}", Toast.LENGTH_SHORT).show()
        }
        if (user.Category.equals("DAIRY ITEMS")) {
            values.put("DESCRIPTION", user.Description)
            values.put("IMAGE", user.Image)
            db.insertOrThrow(Dairy, null, values)
            Toast.makeText(ctx, "Inserted in ${Dairy}", Toast.LENGTH_SHORT).show()
        }
        if (user.Category.equals("VEGETABLES ITEMS")) {
            values.put("DESCRIPTION", user.Description)
            values.put("IMAGE", user.Image)
            db.insertOrThrow(vegetables, null, values)
            Toast.makeText(ctx, "Inserted in ${vegetables}", Toast.LENGTH_SHORT).show()
        }
        if(user.Category.equals("DOCUMENT ITEMS"))
        {
            values.put("DESCRIPTION",user.Description)
            values.put("IMAGE",user.Image)
            db.insertOrThrow(document_item,null,values)
            Toast.makeText(ctx,"Inserted in $document_item",Toast.LENGTH_SHORT).show()
        }
    }

    fun ClothData(): ArrayList<User> {
        val clothData = ArrayList<User>()
        db = readableDatabase
        val sqlQuery = "SELECT * FROM $Cloth"
        val cursor = db.rawQuery(sqlQuery, null)
        if (cursor.moveToFirst())
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                clothData.add(user)
            } while (cursor.moveToNext())
        cursor.close()
        return clothData
    }

    fun FoodData(): ArrayList<User> {
        db = readableDatabase
        val foodData = ArrayList<User>()
        val cursor = db.rawQuery("SELECT*FROM $Food", arrayOf())
        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                foodData.add(user)
            } while (cursor.moveToNext())
        }
        return foodData
    }

    fun EaData(): ArrayList<User> {
        val EaData = ArrayList<User>()
        db = readableDatabase
        var cursor = db.rawQuery("SELECT*FROM $Electrical_Applicances", arrayOf())
        if (cursor.moveToNext()) {
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                EaData.add(user)
            } while (cursor.moveToNext())
        }
        return EaData
    }

    fun DairyData(): ArrayList<User> {
        db = readableDatabase
        val dairyList = ArrayList<User>()
        val cursor = db.rawQuery("SELECT*FROM ${Dairy}", arrayOf())
        if (cursor !== null && cursor.moveToFirst()) {
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                dairyList.add(user)
            } while (cursor.moveToNext())
        }
        return dairyList
    }

    fun vegetableData(): ArrayList<User> {
        db = readableDatabase
        val vegetableList = ArrayList<User>()
        val cursor = db.rawQuery("SELECT*FROM ${vegetables}", arrayOf())
        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                vegetableList.add(user)
            } while (cursor.moveToNext())
        }
        return vegetableList
    }

    fun FrequentData(FrequentData: FrequentItems) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("DESCRIPTION", FrequentData.Description)
        values.put("IMAGE", FrequentData.Image)
        db.insertOrThrow(frequentList, null, values)
        //Log.d("Db-Data","$values")
    }

    fun getFrequentData(): ArrayList<FrequentItems> {
        val db = this.readableDatabase
        val frequentData = ArrayList<FrequentItems>()
        val cursor = db.rawQuery("SELECT * FROM $frequentList", null)

        if (cursor.moveToFirst())
            do {
                val item = FrequentItems()
                item.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                item.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                frequentData.add(item)
            } while (cursor.moveToNext())

        return frequentData
    }

    fun documentData():ArrayList<User>
    {
        db = readableDatabase
        val documentList = ArrayList<User>()
        val cursor = db.rawQuery("SELECT * FROM $document_item", null)
        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
                user.Image = cursor.getBlob(cursor.getColumnIndex("IMAGE"))
                documentList.add(user)
            } while (cursor.moveToNext())
        }
        return documentList
    }
}