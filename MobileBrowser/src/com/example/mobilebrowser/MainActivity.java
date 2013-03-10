package com.example.mobilebrowser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnKeyListener;

public class MainActivity extends Activity implements OnClickListener {
	
	private WebView webView;
	private Button back;
	private Button forward;
	private Button home;
	private TextView url;
	private Button go;
	private InputMethodManager imm;
	
	private static String homePage = "http://www.google.com/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//finding elements of the app
		webView = (WebView) findViewById(R.id.webView);
		back = (Button) findViewById(R.id.back);
		forward = (Button) findViewById(R.id.forward);
		home = (Button) findViewById(R.id.home);
		url = (TextView) findViewById(R.id.eturl);
		go = (Button) findViewById(R.id.Go);
		
		//set OnClickListener
		webView.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		home.setOnClickListener(this);
		url.setOnClickListener(this);
		go.setOnClickListener(this);
		
		//set OnKeyListener
		url.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					webView.loadUrl(url.getText().toString());
					//to hide the keyboard
					imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
					return true;
				}
				return false;
			}
		});
		url.setTextKeepState(homePage);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.setWebViewClient(new WebViewClient() { 
            public boolean shouldOverrideUrlLoading(WebView view, String loadingUrl){
                webView.loadUrl(loadingUrl);
                url.setTextKeepState(loadingUrl);
        		return true;
            } 
        });
		webView.loadUrl(url.getText().toString());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Go:
			webView.loadUrl(url.getText().toString());
			//to hide the keyboard
			imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
			
		case R.id.back:
			if(webView.canGoBack())
			{
				webView.goBack();
				url.setText(webView.getOriginalUrl());
			}
			break;
			
		case R.id.forward:
			if(webView.canGoForward())
			{
				webView.goForward();
				url.setText(webView.getOriginalUrl());
			}
			break;
			
		case R.id.home:
			webView.loadUrl(homePage);
			url.setText(homePage);
			break;

		default:
			break;
		}
		
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		MainActivity.homePage = homePage;
	}
	
	
}
