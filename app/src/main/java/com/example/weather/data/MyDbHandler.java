package com.example.weather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.weather.params.Params;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create=("create Table "+ Params.TABLE_NAME + "(" + Params.KEY_USERNAME+ " TEXT primary key," + Params.KEY_NAME + "TEXT,"+ Params.KEY_PASSWORD +"TEXT"+")");
        Log.d("table create","Query is run:"+ create);
        sqLiteDatabase.execSQL("create Table users(username TEXT primary key, password TEXT, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+Params.TABLE_NAME);

    }

    public boolean insertData(String name, String username, String password){
        SQLiteDatabase mydb=this.getWritableDatabase();
        ContentValues cv=new ContentValues();


        cv.put(Params.KEY_NAME,name);
        cv.put(Params.KEY_PASSWORD,password);
        cv.put(Params.KEY_USERNAME,username);
        long result= mydb.insert(Params.TABLE_NAME,null,cv);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean checkUsername(String username){
        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor c=mydb.rawQuery("select * from user_table where username=?", new String[]{username});
        if (c.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor c=mydb.rawQuery("select * from user_table where username=? and password=?", new String[]{username,password});
        if (c.getCount()>0)
            return true;
        else
            return false;

    }
}
