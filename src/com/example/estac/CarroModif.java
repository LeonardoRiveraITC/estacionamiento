package com.example.estac;





import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarroModif extends Fragment implements OnClickListener {
public EditText txtPlm,txtPls,txtMo;
public Button btnPls, btnBuscar;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.activity_carro_modif, container, false);
		txtPlm = (EditText) view.findViewById (R.id.txtPlm);
		txtPls = (EditText) view.findViewById(R.id.txtPls);
		txtMo = (EditText) view.findViewById(R.id.txtMo);
		btnPls = (Button) view.findViewById(R.id.btnPls1);
		btnBuscar= (Button ) view.findViewById(R.id.btnBuscar);
		
		txtPlm.setEnabled(false);
		txtMo.setEnabled(false);
		
		btnPls.setOnClickListener(this);
		btnBuscar.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnPls1:actualiza();break;
		case R.id.btnBuscar:buscar();break;
		}

	}
	private void buscar() {
		// TODO Auto-generated method stub
		DataBase cone= new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		 Cursor datos;
		 
		 String sql="select * from carros where id="+this.txtPls.getText().toString();
		 
		 try{
			 datos= base.rawQuery(sql,null);
			 if(datos.moveToFirst()){
				 this.txtPls.setText(datos.getString(0));
				 this.txtMo.setText(datos.getString(1));
				 this.txtPlm.setText(datos.getString(3));
				 Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
				 txtPls.setEnabled(false);
				 txtPlm.setEnabled(true);
				 
			 }
			 else{
				 txtMo.setText("");
				 txtPls.setText("");
				 txtPlm.setText("");
				 Toast.makeText(getActivity(), R.string.er6, Toast.LENGTH_LONG).show();
			 }
			 datos.close();
			 cone.close();
			 base.close();
		 }catch(SQLException ex){
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
		 }
	}
	private void actualiza() {
		// TODO Auto-generated method stub
		DataBase cone = new DataBase(getActivity(),"carros",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		
		
		String sql="update carros set numlug='"+Integer.valueOf(txtPlm.getText().toString())+"'where id="+txtPls.getText().toString()+"";
		 
		try{
			base.execSQL(sql);
			Toast.makeText(getActivity(),R.string.ok , Toast.LENGTH_LONG).show();
			base.close();
			cone.close();
		}catch(SQLiteException ex){
			Toast.makeText(getActivity(),ex.getMessage() , Toast.LENGTH_LONG).show();
		}
	}
	
	

}
