package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE udetails(name TEXT primary key, contact TEXT, age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("drop table if exists udetails");
    }
    public boolean insertData(String name, String contact, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
contentValues.put("contact",contact);
contentValues.put("age",age);
contentValues.put("name",name);
long res=db.insert("udetails",null,contentValues);
if (res==-1){
    return  false;
}
else{
    return true;
}
    }
    public boolean updateData(String name, String contact, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("age", age);
        Cursor cursor=db.rawQuery("Select * from udetails where name=?", new String[]{name});
        if(cursor.getCount()>0){
            long res=db.update("udetails",contentValues,"name=?",new String[]{name});
            if(res==-1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public boolean deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor=db.rawQuery("Select * from udetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
long res=db.delete("udetails","name=?",new String[]{name});
        if(res==-1)
            return false;
        else
            return true;
        }
        else
            return false;
    }
    public Cursor viewD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from udetails", null);
        return cursor;
    }
}
