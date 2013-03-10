package com.example.mobilebrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WbClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url)
	{
		v.loadUrl(url);
		return true;
	}

}
