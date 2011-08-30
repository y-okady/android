package jp.co.cybozu.yuki_okada.testbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.graphics.Bitmap;
import android.view.KeyEvent;

public class TestBrowser extends Activity {
	private WebView mWebView;
	private ScrollView mScrollView;
	private EditText mEditTextUrl;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.main);
        
        // èâä˙âª
        mWebView = (WebView)findViewById(R.id.webview);
        mScrollView = (ScrollView)findViewById(R.id.scrollview);
        mEditTextUrl = (EditText)findViewById(R.id.url);
        String strHome = "http://ipn.yahoo.co.jp";
        
        mWebView.setWebViewClient(new WebViewClient() {
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon) {
        		super.onPageStarted(view, url, favicon);
        		setProgressBarIndeterminateVisibility(true);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url) {
        		super.onPageFinished(view, url);
        		setProgressBarIndeterminateVisibility(false);
        		mScrollView.scrollTo(0, 0);
            	//mWebView.requestFocus();
            	mEditTextUrl.setText(mWebView.getUrl());
        	}
        	/*
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		//super.shouldOverrideUrlLoading(view, url);
        		mScrollView.scrollTo(0, 0);
        		return false;
        	}
        	*/
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLightTouchEnabled(true);
        go(strHome);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
    		back();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    private void go(String strUrl) {
    	mWebView.loadUrl(strUrl);
    }
    
    private void back() {
    	if(mWebView.canGoBack()) {
    		mWebView.goBack();
    	}
    }
    
    private void forward() {
    	if(mWebView.canGoForward()) {
    		mWebView.goForward();
    	}
    }
    
    private void reload() {
    	mWebView.reload();
    }
    
    public void onClickGo(View v) {
    	go(mEditTextUrl.getText().toString());
    }
    
    public void onClickBack(View v) {
    	back();
    }
    
    public void onClickForward(View v) {
    	forward();
    }
    
    public void onClickReload(View v) {
    	reload();
    }
    
    public void onClickYahoo(View v) {
    	go("http://ipn.yahoo.co.jp");
    }
    
    public void onClickCybozuLive(View v) {
    	go("https://m.cybozulive.com");
    }
}