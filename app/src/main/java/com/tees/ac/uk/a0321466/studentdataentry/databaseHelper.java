package com.tees.ac.uk.a0321466.studentdataentry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {

    //database parameters
    public static final String DATABASE_NAME = "students.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_AGE = "age";
    public static final String CONTACTS_COLUMN_STUDENT_ID = "studentID";
    //studentModule studentModle;


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


    //method to insert data in the database
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

    //get data or view data stored in the database table

    public List<studentModule> viewAll(){
        //define list type
        List<studentModule> returnList= new ArrayList<>();
        String getQuery= "select * from " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor= db.rawQuery(getQuery,null);
       if(cursor.moveToFirst()){
           do{
               int id= cursor.getInt(0);
               String studentName= cursor.getString(1);
               Integer studentAge= cursor.getInt(2);
               Long studentId= cursor.getLong(3);
              studentModule studentModle= new studentModule(id,studentName,studentAge,studentId);
               returnList.add(studentModle);

           }while (cursor.moveToNext());
          db.close();
       }

       //return student contacts array
        return returnList;
    }

    //delete database contact(student entry)
    public boolean deleteOne(studentModule studentModle){
        String query= "delete from " + CONTACTS_TABLE_NAME + " where " + CONTACTS_COLUMN_ID + " = " + studentModle.getId();
        SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor= db.rawQuery(query,null);
         if(cursor.moveToFirst()){
             return false;
         }
         else{
             return true;
         }
    }


}
