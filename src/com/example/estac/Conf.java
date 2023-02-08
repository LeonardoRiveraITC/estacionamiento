package com.example.estac;


import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity principal donde se mostrará la aplicación y se presentará
 * la ActionBar con las pestañas
 * Desde esta Activity se cargan las pestañas. La funcionalidad de cada una de ellas
 * se hará en su correspondiente fichero de código
 * En esta aplicación se muestra cómo trabajar con Bases de Datos en Android con SQLite
 * y cómo presentar la información utilizando Tabs
 
 */
public class Conf extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.princ);
        
        loadTabs();
    }
    
    /*
     * Carga las pestañas para formar el TabHost
     */
    public void loadTabs() {
    	
    	Resources res = getResources();
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Crea las tabs
        ActionBar.Tab tab1 = actionBar.newTab().setText(res.getString(R.string.initial));
        ActionBar.Tab tab2 = actionBar.newTab().setText(res.getString(R.string.modify));
        ActionBar.Tab tab3 = actionBar.newTab().setText(res.getString(R.string.restore));
        
        // Crea cada Fragment para luego asociarla con la pestaña que corresponda
        Fragment tabFragment1 = new settingsRegistration();
        Fragment tabFragment2 = new settingsDel();
        Fragment tabFragment3 = new settingsRestore();
        
        // Asocia cada Fragment con su tab
        tab1.setTabListener(new TabsListener(tabFragment1));
        tab2.setTabListener(new TabsListener(tabFragment2));
        tab3.setTabListener(new TabsListener(tabFragment3));
        
        // Añade las tabs a la ActionBar
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }
}
