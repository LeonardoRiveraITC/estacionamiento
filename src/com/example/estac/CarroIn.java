package com.example.estac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CarroIn extends Fragment implements OnClickListener, OnItemSelectedListener {

	  Button btnIn;
	     EditText plates,lug;
	    String [] ad = {"toyota","chevrolet","BMW"};
	     String [] to = {"Alphard","Belta","Hiacis"};
	    String [] chev = {"Aguile","Aveo","Fleetline"};
		 String [] bmw = {"Alphard","Belta","HiGran Tourer"};
		 String [] nombre;
	     int a [];
		 public DataBase bd;
		String [] pay = {"por hora/per hour","pension"};
		boolean no = true;
		
		 Spinner  spBrand,spModel,spPay;
		 public ArrayAdapter<String> arrayAdapter;
		 public ArrayAdapter<String> arrayAdapter2;
		 public ArrayAdapter<String> arrayAdapter3;
		 public ArrayAdapter<String> arrayAdapter4;
		 public ArrayAdapter<String> arrayAdapter5;
		  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
 
		
		// Al tratarse de un fragment el layout se infla creando una view que hay que devolver
		// y que se debe usar para obtener las referencias a los elementos del layout
		
	
		View view = inflater.inflate(R.layout.activity_carro_in, container, false);
		 
		 arrayAdapter = new ArrayAdapter<String>(
		            getActivity(), 
		            android.R.layout.simple_list_item_1, ad);
		 arrayAdapter2 = new ArrayAdapter<String>(
		            getActivity(), 
		            android.R.layout.simple_list_item_1, to);
		 arrayAdapter3 = new ArrayAdapter<String>(
		            getActivity(), 
		            android.R.layout.simple_list_item_1, chev);
		 arrayAdapter4 = new ArrayAdapter<String>(
		            getActivity(), 
		            android.R.layout.simple_list_item_1, bmw);
		 arrayAdapter5 = new ArrayAdapter<String>(
		            getActivity(), 
		            android.R.layout.simple_list_item_1, pay);
		 
		lug = (EditText) view.findViewById(R.id.txtLug);
		 spBrand = (Spinner) view.findViewById(R.id.spMar);
		 spModel = (Spinner) view.findViewById(R.id.spMod);
		 spPay = (Spinner) view.findViewById(R.id.spPay);
		 btnIn = (Button) view.findViewById(R.id.btnIn);
		 plates = (EditText) view.findViewById(R.id.txtPlac);
         
		spBrand.setAdapter(arrayAdapter);    	
		spPay.setAdapter(arrayAdapter5);
		spBrand.setOnItemSelectedListener(this);
		btnIn.setOnClickListener(this);
		
		 
    	return view;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.btnIn:
		if ((lug.getText().toString().isEmpty()) || (plates.getText().toString().isEmpty() || (spBrand.getSelectedItem().toString().isEmpty() || (spModel.getSelectedItem().toString().isEmpty() || (spPay.getSelectedItem().toString().isEmpty()))))) {
			Toast.makeText(getActivity(), R.string.er, Toast.LENGTH_LONG).show();
		}
		
		DataBase cone = new DataBase(getActivity(),"configs",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		Cursor datos;
		
		String sql = "select numlug from configs where id = 1";
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
				
				 
				 
			 }
			 if (Integer.valueOf(nombre [0]) < Integer.valueOf(lug.getText().toString())){
				 Toast.makeText(getActivity(), R.string.l, Toast.LENGTH_LONG).show();
			 }
			 else {
				 guardar();
			 }
			 datos.close();
			 base.close();
			 cone.close();
    	
    	
	}catch (SQLException e) {
		
	}
		
    }
	}
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		     switch (position) {
		case 0:
			spModel.setAdapter(arrayAdapter2);
			break;
		case 1:
			spModel.setAdapter(arrayAdapter3);
			break;
		case 2:
			spModel.setAdapter(arrayAdapter4);
			break;
		}
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	private void guardar() {
		DataBase cone = new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		Cursor datos;
		
		String sql = "select numlug from carros; ";
		try{
			datos = base.rawQuery(sql, null);
			 if (datos.moveToFirst()){
				 int num = datos.getCount();
			
				 a = new int [num];
				 int i = 0;
				 datos.moveToFirst();
				 
				 do{
					
					 a [i] = datos.getInt(0);
					 if (Integer.parseInt(lug.getText().toString()) == a [i]) {
							Toast.makeText(getActivity(), R.string.lugr, Toast.LENGTH_LONG).show(); 
							no = false;
						}
					 
						
					 i++;
						
				 }while (datos.moveToNext());
	 
			 
			 
	}}catch (SQLException e) {
		
	}
		if (no == true) {
		Calendar calendario = Calendar.getInstance();
		
		int hora =calendario.get(Calendar.HOUR_OF_DAY);
		
		sql = "insert into carros values('";
		sql = sql + plates.getText().toString()+"','";
		sql = sql + spBrand.getSelectedItem().toString()+"','";
		sql = sql + spModel.getSelectedItem().toString()+"','";
		sql = sql + lug.getText().toString()+"','";
		sql = sql + spPay.getSelectedItem().toString()+"',";
		sql = sql +hora+");";
		
		try {
			base.execSQL(sql);
			Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
			
			
		}catch(SQLException ex){
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		base.close();
		cone.close();
	}
	}
		
	}	
	


	