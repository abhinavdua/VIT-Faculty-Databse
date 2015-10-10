package com.abhinav.facultydatabasedev;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper{

	int a;
	public static final String TAG = "HELLO";
	public static String ans = "";
	private static final String DATABASE_NAME = "scse2.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public Cursor getCursor(String g) {
 
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String [] sqlSelect = {"id","name","cabin", "email","dec"};
		String sqlTables = g;
		System.out.println("sql tables is "+g);
		
		qb.setTables(sqlTables);
		Cursor c = qb.query(db,sqlSelect , null, null,
				null, null, null);
		c.moveToFirst();
	
		return c;
		

	}
    
    public int getCnt(String g) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String [] sqlSelect = {"id","name","cabin", "email","dec"};
		String sqlTables = g;

		qb.setTables(sqlTables);
		Cursor c = qb.query(db,sqlSelect , null, null,
				null, null, null);

		return c.getCount();
		

	}
    
    
    
}
