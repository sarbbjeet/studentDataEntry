package com.tees.ac.uk.a0321466.studentdataentry;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {

    //database parameters
    public static final String DATABASE_NAME = "students.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_AGE = "age";
    public static final String CONTACTS_COLUMN_STUDENT_ID = "studentID";


    public databaseHelper(@Nullable Context context) {
        //set database name, version .
        super(context,"studentDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //we can create query in the form of string as written below()
//        String createTableStatement= "create table contacts " +
//                "(id integer primary key, name text,age int,studentID int)";
        //write query using global variables
        String createTableStatement= "create table " + CONTACTS_TABLE_NAME +
                " ( " + CONTACTS_COLUMN_ID + " integer primary key, " + CONTACTS_COLUMN_NAME + " text, "
                   + CONTACTS_COLUMN_AGE + " int, " + CONTACTS_COLUMN_STUDENT_ID + " int)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact(studentModule studentmodel){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv= new ContentValues();
    cv.put(CONTACTS_COLUMN_NAME, studentmodel.getName().toString());
    cv.put(CONTACTS_COLUMN_AGE, studentmodel.getAge());
    cv.put(CONTACTS_COLUMN_STUDENT_ID, studentmodel.getStudentId());
    long insert= db.insert(CONTACTS_TABLE_NAME,null,cv);
    if(insert==-1){
        return false;
    }
    else{
        return true;
    }
    }
}
