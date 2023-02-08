package com.example.estac;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {
//Context ctx;
	public String base = "CREATE TABLE carros (id TEXT PRIMARY KEY ,marca TEXT,modelo TEXT,numlug INTEGER,pago TEXT,inicio INTEGER);";
	public String base2 = "CREATE TABLE configs (id INTEGER PRIMARY KEY,numlug INTEGER,costo INTEGER,pension INTEGER);";

	public DataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		
	//	ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("CREATE TABLE configs (id INTEGER PRIMARY KEY,numlug INTEGER,costo INTEGER,pension INTEGER);");
		//db.execSQL("CREATE TABLE carros (id TEXT PRIMARY KEY,marca TEXT,modelo TEXT,numlug INTEGER,pago TEXT,inicio DATETIME  DEFAULT CURRENT_TIMESTAMP,fin DATETIME CURRENT_TIMESTAMP);");
		db.execSQL(base);
		db.execSQL(base2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table configs if exists carros");
		db.execSQL(base2);
		db.execSQL("drop table carros if exists configs");
		db.execSQL(base);
	}
	public String pag (String plac) {
		String res = "";
		String sql = "select pago from carros where id = '" + plac + "';";
		Cursor c = this.getReadableDatabase().rawQuery(sql, null);
		int id = c.getColumnIndex("pago");
		res = c.getString(id);
		return res;
	}

}
	