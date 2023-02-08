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


public class settingsDel extends Fragment implements OnClickListener {

    public EditText edLug2, edCost2,edPens2;
    public Button btnGua2;
    

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		// Al tratarse de un fragment el layout se infla creando una view que hay que devolver
		// y que se debe usar para obtener las referencias a los elementos del layout
		
		View view = inflater.inflate(R.layout.activity_mod, container, false);
		Button btnGua2 = (Button) view.findViewById(R.id.btnGua2);
    	btnGua2.setOnClickListener(this);

        edLug2 = (EditText) view.findViewById(R.id.edLug2);
        edCost2= (EditText) view.findViewById(R.id.edCost2);
        edPens2= (EditText) view.findViewById(R.id.edPens2);

        
    	
    	return view;
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnGua2:
			
			if (edLug2.getText().toString().isEmpty() || edCost2.getText().toString().isEmpty() || edPens2.getText().toString().isEmpty()) {
				
				Toast.makeText(getActivity(), R.string.er, Toast.LENGTH_LONG).show();
			}
			else {
				if ((Integer.parseInt(edLug2.getText().toString())== 0) || (Integer.parseInt(edCost2.getText().toString()) == 0) || (Integer.parseInt(edPens2.getText().toString()) == 0))	{
					Toast.makeText(getActivity(), R.string.er2, Toast.LENGTH_LONG).show();
				}
			else {
				try {
					guardar();
					
					}catch (Exception e) {
						Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
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
	String sql;
	sql = "UPDATE configs SET numlug = '";
	sql = sql + Integer.parseInt(edLug2.getText().toString())+"',costo ='";
	sql = sql + Integer.parseInt(edCost2.getText().toString())+"',pension = '";
	
	sql = sql +Integer.parseInt(edPens2.getText().toString())+"' where id = 1;";
	
	try {
		base.execSQL(sql);
		Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
		
	
	}catch(SQLException ex){
		Toast.makeText(getActivity(),R.string.er4, Toast.LENGTH_LONG).show();
	}
	base.close();
	cone.close();
}
}