package com.example.estac;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PagWeb extends Activity {
private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pag_web);
		
       mWebView = (WebView) findViewById(R.id.webview);
        
       mWebView.getSettings().setBuiltInZoomControls(true);

        // Url que carga la app (webview)
        mWebView.loadUrl("file:///android_asset/web/Manual.html");
        mWebView.setWebViewClient(new WebViewClient());
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pag_web, menu);
		return true;
	}
	
	   public void onBackPressed() {
           if(mWebView.canGoBack()) {
               mWebView.goBack();
           } else {
               super.onBackPressed();
           }
       }



}