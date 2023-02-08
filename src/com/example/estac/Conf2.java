package com.example.estac;


import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Conf2 extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.princ);
        
        loadTabs();
    }
    
  
    public void loadTabs() {
    	
    	Resources res = getResources();
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        

        ActionBar.Tab tab1 = actionBar.newTab().setText(res.getString(R.string.ingr));
        ActionBar.Tab tab2 = actionBar.newTab().setText(res.getString(R.string.elim));
        ActionBar.Tab tab3 = actionBar.newTab().setText(res.getString(R.string.list));
        ActionBar.Tab tab4 = actionBar.newTab().setText(res.getString(R.string.modif));
        
      
        Fragment tabFragment1 = new CarroIn();
        Fragment tabFragment2 = new CarroOut();
        Fragment tabFragment3 = new CarroList();
        Fragment tabFragment4 = new CarroModif();
        //Fragment tabFragment4 = new settingsRestore();
        
    
        tab1.setTabListener(new TabsListener(tabFragment1));
        tab2.setTabListener(new TabsListener(tabFragment2));
        tab3.setTabListener(new TabsListener(tabFragment3));
        tab4.setTabListener(new TabsListener(tabFragment4));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
        actionBar.addTab(tab4);
    }
}