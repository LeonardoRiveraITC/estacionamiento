package com.example.estac;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	public Button btnSettings,btnOperation,btnExit,btnMan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSettings = (Button) findViewById (R.id.btnSettings);
		btnOperation = (Button) findViewById (R.id.btnOperation);
		btnMan = (Button) findViewById (R.id.btnManu);
		btnExit = (Button) findViewById (R.id.btnExit);
		btnSettings.setOnClickListener(this);
		btnOperation.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		btnMan.setOnClickListener(this);
		
		
		
	
}
				
				 

			 
		
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSettings:
			Intent i = new Intent (this,Conf.class);
			startActivity (i);
			
			break;
		case R.id.btnOperation:
			DataBase cone2 = new DataBase(this,"configs",null,1);
			SQLiteDatabase base2 = cone2.getWritableDatabase();
			Cursor datos2;
			String sql2 = "SELECT * FROM configs where id = 1;";
			try {
				datos2 = base2.rawQuery(sql2, null);
				
				if(datos2.moveToFirst()) {
					Intent i2 = new Intent (this,Conf2.class);
					startActivity (i2);
					 }
					else {
						Toast.makeText(this, R.string.inic, Toast.LENGTH_LONG).show();
					}
					 }catch (SQLException e) {
						 Toast.makeText(this, R.string.inic, Toast.LENGTH_LONG).show();
					 }
		
			
			break;
		case R.id.btnManu:
		Intent i3= new Intent(this,PagWeb.class);
		startActivity (i3);
		   break;
		
		case R.id.btnExit:
			System.exit(0);
			break;
		}
		
		}
		
	}



