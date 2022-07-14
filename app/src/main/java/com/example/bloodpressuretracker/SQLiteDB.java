package com.example.bloodpressuretracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class SQLiteDB extends SQLiteOpenHelper {

    private static final String DBname="user.db";
    private static final String TBname="user_records";
    private static final String sno="SERIAL";
    private static final String Systol="SYSTOL";
    private static final String Diastol="diastol";
    private static final String BPM="bpm";
    private static final String Date="date";
    private static final String Comment="comment";
    private static final String CREATE_TABLE="Create Table "+TBname+" ("+sno+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Systol+" VARCHAR(3), "+Diastol+" VARCHAR(3), "+BPM+" VARCHAR(4), "+Comment+" VARCHAR(100));"  ;
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TBname;
    private static final int version=1;
    private Context context;
//+Date+" VARCHAR(10), "

    public SQLiteDB( Context context ) {
        super(context, DBname, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"OnCreate ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context,"Onupgrade ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String systol,String diastol,String bpm, String comment)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Systol,systol);
        contentValues.put(Diastol,diastol);
        contentValues.put(BPM,bpm);
        contentValues.put(Comment,comment);

        long rowID=sqLiteDatabase.insert(TBname,null ,contentValues);
        sqLiteDatabase.close();
        return rowID;
    }
}