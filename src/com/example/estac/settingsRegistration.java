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

public class settingsRegistration extends Fragment implements OnClickListener {

    public EditText edLug, edCost,edPens;
    public Button btnGua;
    public DataBase bd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		// Al tratarse de un fragment el layout se infla creando una view que hay que devolver
		// y que se debe usar para obtener las referencias a los elementos del layout
		
		View view = inflater.inflate(R.layout.activity_conf, container, false);
		Button btnGua = (Button) view.findViewById(R.id.btnGua);
    	btnGua.setOnClickListener(this);

        edLug = (EditText) view.findViewById(R.id.edLug);
        edCost= (EditText) view.findViewById(R.id.edCost);
        edPens= (EditText) view.findViewById(R.id.edPens);
        
      
    	
    	return view;
	}
	
public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnGua:
			
			if (edLug.getText().toString().isEmpty() || edCost.getText().toString().isEmpty() || edPens.getText().toString().isEmpty()) {
				
				Toast.makeText(getActivity(), R.string.er, Toast.LENGTH_LONG).show();
			}
			else {
				if ((Integer.parseInt(edLug.getText().toString())== 0) || (Integer.parseInt(edCost.getText().toString()) == 0) || (Integer.parseInt(edPens.getText().toString()) == 0))	{
					Toast.makeText(getActivity(), R.string.er2, Toast.LENGTH_LONG).show();
				}
			else {
				try {
					guardar();
					
					}catch (Exception e) {
						
					} 
	        
            break;
			}
	}	
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
	sql2 = "CREATE TABLE IF NOT EXISTS configs (id INTEGER PRIMARY KEY ,numlug INTEGER,costo INTEGER,pension INTEGER);";
	sql = "insert into configs values(";
	sql = sql + '1' +",'";
	sql = sql + Integer.parseInt(edLug.getText().toString())+"','";
	sql = sql + Integer.parseInt(edCost.getText().toString())+"','";
	
	sql = sql +Integer.parseInt(edPens.getText().toString())+"');";
	try {
		base.execSQL(sql2);
		base.execSQL(sql);
		Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
		

	}catch(SQLException ex){
		Toast.makeText(getActivity(), R.string.er3, Toast.LENGTH_LONG).show();
	}
	base.close();
	cone.close();
}
}