package com.izhar.mapdemo1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.izhar.mapdemo1.objects.Place;

import java.util.ArrayList;
import java.util.List;

public class Places extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public Places(Context context) {
        super(context, "Database", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table places(category text, name text, latitude real, longtude real)");
        inset(db, "Restaurant", "Mafi Restaurant", "8.542888873435183", "39.269254189966404");
        inset(db, "Restaurant", "Kaldis coffee", "8.542323901230235", "39.26887599852602");
        inset(db, "Restaurant", "Salt and light", "8.542530792557328", "39.26888136294361");
        inset(db, "Bank", "CBE", "8.54267402494881", "39.269696754417915");
        inset(db, "Park", "Yerosen Park", "8.541225834182104", "39.26854693748332");
        /*ContentValues contentValues = new ContentValues();
        contentValues.put("category", "Restaurant");
        contentValues.put("name", "Mafi Restaurant");
        contentValues.put("latitude", "8.542888873435183");
        contentValues.put("longtude", "39.269254189966404");
        db.insert("places", null, contentValues);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Place> getPlaces(){
        List<Place> places = new ArrayList<>();
        Cursor cursor = db.query("places", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            places.add(new Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return places;
    }

    private void inset(SQLiteDatabase db, String cat, String name, String la, String lo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", cat);
        contentValues.put("name", name);
        contentValues.put("latitude", la);
        contentValues.put("longtude", lo);
        db.insert("places", null, contentValues);
    }

    public Place getPlace(String na){
        Cursor cursor = db.rawQuery("select * from places where name=" + na , null);
        return new Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }
}
