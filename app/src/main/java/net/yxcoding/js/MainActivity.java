package net.yxcoding.js;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity
{
    private WebView webView;

    private String js;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new MyWebClient());

        webView.loadUrl("file:///android_asset/test.html");

        js = "javascript:function setValue(){x=document.getElementById(\"content\");  x.innerHTML=\"js 注入成功且方法调用成功\";}";
    }

    class MyWebClient extends WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url)
        {

            view.loadUrl("javascript:" + js);
            view.loadUrl("javascript:" + "setValue()");

            super.onPageFinished(view, url);
        }
    }
}
