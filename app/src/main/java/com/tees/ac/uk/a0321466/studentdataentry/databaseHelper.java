package com.tees.ac.uk.a0321466.studentdataentry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {


    public databaseHelper(@Nullable Context context) {
        //set database name, version .
        super(context,"studentDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
