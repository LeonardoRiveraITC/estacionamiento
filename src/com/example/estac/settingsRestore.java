package com.example.estac;

import android.app.Fragment;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class settingsRestore extends Fragment implements OnClickListener {

   
    public Button btnRes;
   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		// Al tratarse de un fragment el layout se infla creando una view que hay que devolver
		// y que se debe usar para obtener las referencias a los elementos del layout
		
		View view = inflater.inflate(R.layout.activity_res, container, false);
		final Button btnRes = (Button) view.findViewById(R.id.btnRes);
    	btnRes.setOnClickListener(this);


      
    	
    	return view;
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnRes:
		
			
				try {
				guardar();
					
					 
				}catch (SQLException e) {
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
	           

            break;
		
			}
	}	
	private void cancelar() {
		//lblId.setText(String.valueOf(id));
		//txtNombre.setText("");
		//txtPaterno.setText("");
		//txtMaterno.setText("");
		//txtDireccion.setText("");
		//txtTelefono.setText("");
		//txtNombre.requestFocus();
		}
	private void guardar() {
		DataBase cone = new DataBase(getActivity(),"configs",null,1);
		SQLiteDatabase base = cone.getWritableDatabase();
		String sql,sql2;
		sql = "drop table configs;";
        
		try {
			base.execSQL(sql);
			Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
			

		}catch(SQLException ex){
			Toast.makeText(getActivity(), R.string.er4, Toast.LENGTH_LONG).show();
		}
		base.close();
		cone.close();
	}
	}
