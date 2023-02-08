package com.example.estac;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CarroList extends Fragment {
	public int row;
    public DataBase db;
    public ListView lvCarro2;
    public CursorAdapter adapter;
    public String id[];
    public String nombre[];
    public String marca[];
    public String modelo[];
    public String  [] vacio = {""};
    public ArrayAdapter<String> arrayAdaptercar;
    public ArrayAdapter<String> arrayAdaptercar2;
    public int indice;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		DataBase cone = new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		Cursor datos;
		View view = inflater.inflate(R.layout.activity_carro_list, container, false);
		//String sql = "select * from carros order by id,numlug";
		//String sql = "select id,numlug from carros;";
		String sql = "select id,numlug,marca,modelo from carros;";
		try{
			datos = base.rawQuery(sql, null);
			 if (datos.moveToFirst()){
				 int num = datos.getCount();
				 row = num;
				 id = new String [num];
				 nombre = new String [num];
				 marca = new String [num];
				 modelo = new String [num];
				 int i = 0;
				 datos.moveToFirst();
				 
				 do{
					 id[i]= datos.getString(0);
					// nombre [i] = datos.getString(2) + " " + datos.getString(1);
					 nombre [i] = datos.getString(0) + " " + datos.getString(1) + " " + datos.getString(2) + " " + datos.getString(3);
					 i++;
				 }while (datos.moveToNext());
				    lvCarro2 = (ListView) view.findViewById (R.id.lvCarro2);
			        arrayAdaptercar = new ArrayAdapter<String>(
				            getActivity(), 
				            android.R.layout.simple_list_item_1, nombre);
				 lvCarro2.setAdapter(arrayAdaptercar);     
				
				 
				 
			 }
			 else {
				 lvCarro2 = (ListView) view.findViewById (R.id.lvCarro2);
			        arrayAdaptercar2 = new ArrayAdapter<String>(
				            getActivity(), 
				            android.R.layout.simple_list_item_1, vacio);
				 lvCarro2.setAdapter(arrayAdaptercar2);     
			 }
    
    	
			 datos.close();
			 base.close();
			 cone.close();
	}catch (SQLException e) {
		
	}
		
		return view;
		
	
	
}
	

}
