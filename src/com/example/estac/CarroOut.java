package com.example.estac;

import java.sql.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CarroOut extends Fragment implements OnItemClickListener  {
   public int row;
    public DataBase db;
    public ListView lvCarro;
    public CursorAdapter adapter;
    public String id[];
    public String nombre[];
    public String nombre2[];
    public String nombre3[];
    public String nombre4[];
    public String nombre5[];
    public int hor[];
    public int in[];
    public ArrayAdapter<String> arrayAdaptercar;
    public ArrayAdapter<String> arrayAdaptercar2;
    String [] vacio = {""};
    public int indice;
    public int pos  ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Al tratarse de un fragment el layout se infla creando una view que hay que devolver
		// y que se debe usar para obtener las referencias a los elementos del layout
		DataBase cone = new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		Cursor datos;
		View view = inflater.inflate(R.layout.activity_carro_out, container, false);
		String sql = "select id,numlug from carros ";
		try{
			datos = base.rawQuery(sql, null);
			 if (datos.moveToFirst()){
				 int num = datos.getCount();
			
				 nombre = new String [num];
				 int i = 0;
				 datos.moveToFirst();
				 
				 do{
					
					 nombre [i] = datos.getString(0);
					 i++;
				 }while (datos.moveToNext());
				 lvCarro = (ListView) view.findViewById (R.id.lvCarro);
			        arrayAdaptercar = new ArrayAdapter<String>(
				            getActivity(), 
				            android.R.layout.simple_list_item_1, nombre);
				 lvCarro.setAdapter(arrayAdaptercar);    
				 
				 
				 
			 }
			 
			 else {
				 lvCarro = (ListView) view.findViewById (R.id.lvCarro);
			        arrayAdaptercar2 = new ArrayAdapter<String>(
				            getActivity(), 
				            android.R.layout.simple_list_item_1, vacio);
				 lvCarro.setAdapter(arrayAdaptercar2);    
			 }
			 datos.close();
			 base.close();
			 cone.close();
    	
    	
	}catch (SQLException e) {
		
	}
		lvCarro.setOnItemClickListener(this);
		return view;
		
	
	
}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		DataBase cone2 = new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base2 = cone2.getWritableDatabase();
		Cursor datos2;
		String sql2 = "select pago from carros where id = '" + lvCarro.getItemAtPosition(position).toString() + "';";
		try{
			datos2 = base2.rawQuery(sql2, null);
			 if (datos2.moveToFirst()){
				 int num2 = datos2.getCount();
			
				 nombre2 = new String [num2];
				 int i2 = 0;
				 datos2.moveToFirst();
				 
				 do{
					
					 nombre2 [i2] = datos2.getString(0);
					 i2++;
				 }while (datos2.moveToNext());
				
				 if (nombre2 [0].contains("pension") ) {
					 DataBase cone3 = new DataBase(getActivity(),"configs",null,1);
						SQLiteDatabase base3 = cone3.getWritableDatabase();
						Cursor datos3;
						String sql3 = "select pension from configs where id = 1;";
						String sql9 =  "delete  from carros where id = '" + lvCarro.getItemAtPosition(position).toString() + "';";
						try {
							
							datos3 = base3.rawQuery(sql3, null);
							if (datos3.moveToFirst()) {
								int num3 = datos3.getCount();
								
								 nombre3 = new String [num3];
								 int i3 = 0;
								 datos3.moveToFirst();
								 
								 do{
									
									 nombre3 [i3] = datos3.getString(0);
									 i3++;
								 }while (datos3.moveToNext());
							}
						
							base3.execSQL(sql9);
						//}catch (SQLException e) {
							
						//}
						 DataBase cone7 = new DataBase(getActivity(),"carros",null,1);
							SQLiteDatabase base7 = cone7.getWritableDatabase();
							Cursor datos7;
							
							
								Toast.makeText(getActivity(), R.string.pag , Toast.LENGTH_LONG).show();
								Toast.makeText(getActivity(),  nombre3 [0].toString(), Toast.LENGTH_LONG).show();
								base7.execSQL(sql9);
							}catch (SQLException e) {
								
							}
						
				 }
				 else {
					 DataBase cone4 = new DataBase(getActivity(),"configs",null,1);
						SQLiteDatabase base4 = cone4.getWritableDatabase();
						Cursor datos4;
						String sql4 = "select costo from configs where id = 1;";
						try {
							
							datos4 = base4.rawQuery(sql4, null);
							//if (datos4.moveToFirst()) {
								int num4 = datos4.getCount();
								
								 nombre4 = new String [num4];
								 int i4 = 0;
								 datos4.moveToFirst();
								 
								 do{
									
									 nombre4 [i4] = datos4.getString(0);
									 i4++;
								 }while (datos4.moveToNext());
							//}
							
						//}catch (SQLException e) {
							
					//	}
						 DataBase cone5 = new DataBase(getActivity(),"carros",null,1);
							SQLiteDatabase base5 = cone5.getWritableDatabase();
							Cursor datos5;
							String sql5 = "select inicio from carros where id = '" + lvCarro.getItemAtPosition(position).toString() + "';";
							String sql6 = "delete  from carros where id = '" + lvCarro.getItemAtPosition(position).toString() + "';";
						//	try {
								
								datos5 = base5.rawQuery(sql5, null);
								if (datos5.moveToFirst()) {
									int num5 = datos5.getCount();
									
									 in = new int[num5];
									 int i5 = 0;
									 datos5.moveToFirst();
									 
									 do{
										
										 in [i5] = datos5.getInt(0);
										 i5++;
									 }while (datos5.moveToNext());
								}
								
								Calendar calendario = Calendar.getInstance();
								int hora =calendario.get(Calendar.HOUR_OF_DAY);
								int fin = ( 1 + hora-in [0]) * Integer.parseInt(nombre4 [0]);
								Toast.makeText(getActivity(), R.string.pag , Toast.LENGTH_LONG).show();
								Toast.makeText(getActivity(),  String.valueOf(fin), Toast.LENGTH_LONG).show();
								base5.execSQL(sql6);
								
							}catch (SQLException e) {
								
							}
				 }
			/*	else {
					 DataBase cone4 = new DataBase(getActivity(),"configs",null,1);
						SQLiteDatabase base4 = cone4.getWritableDatabase();
						Cursor datos4;
						 DataBase cone5 = new DataBase(getActivity(),"carros",null,1);
							SQLiteDatabase base5 = cone5.getWritableDatabase();
							Cursor datos5;
						String sql4 = "select costo from configs where id = 1";
						String sqle = "select inicio from carros where id ='" + lvCarro.getItemAtPosition(position).toString() + "';";;
					 try {
						 datos4 = base4.rawQuery(sql4, null);
							if (datos4.moveToFirst()) {
								Calendar calendario = Calendar.getInstance();
								
								int hora =calendario.get(Calendar.HOUR_OF_DAY);
								int num4 = datos4.getCount();
								
								 hor = new int [num4];
								 int i4 = 0;
								 datos4.moveToFirst();
								 
								 do{
									
									 hor [i4] = datos4.getInt(0);
									 i4++;
								 }while (datos4.moveToNext());
								 datos5 = base5.rawQuery(sqle, null);
									
									int num5 = datos5.getCount();
									
									 in = new int [num5];
									 int i5 = 0;
									 datos5.moveToFirst();
									 
									 do{
										
										 hor [i5] = datos5.getInt(0);
										 i5++;
										 int fin = ((in [0] - hora) + 1) * hor [0];
										 Toast.makeText(getActivity(), R.string.pag + fin, Toast.LENGTH_LONG).show();
									 }while (datos5.moveToNext());
									

							}
					 }catch (SQLException e) {
							
						}
					 
					 
				 }*/
				 datos2.close();
				 base2.close();
				 cone2.close();
			 }
			 
			 else {
				 Toast.makeText(getActivity(),R.string.er5 ,Toast.LENGTH_LONG ).show();
			 }
       
    	
    	
	}catch (SQLException e) {
		
	}
		/*AlertDialog.Builder pregunta = new AlertDialog.Builder(getActivity());
	    
	pos = position;
	
		pregunta.setCancelable(false).setPositiveButton(R.string.el, new DialogInterface.OnClickListener() {
			DataBase cone2 = new DataBase(getActivity(),"carros",null,1);
			SQLiteDatabase base2 = cone2.getWritableDatabase();
			Cursor datos2;
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//String sql = "delete from carros where id = '"+ lvCarro.getSelectedItem().toString() + "';";
				//String sqlp = "select pension from configs where id = 1;";
				//String sqlp2 = "select costo from configs where id = 1;";
				//String sql2 = "select pago from carros where id = '"+lvCarro.getSelectedItem().toString()+"';";
				
		       
		    	
		    	
		
				

		    	
			
			}
		}).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(getActivity(), R.string.can, Toast.LENGTH_LONG).show();
				dialog.cancel();
			}
		});pregunta.show();
	} */
	}
}