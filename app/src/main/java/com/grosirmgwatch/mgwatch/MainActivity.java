package com.grosirmgwatch.mgwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;
    LinearLayout superLinearLayout;
    String myCurrentUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superProgressBar = findViewById(R.id.myProgressBar);
        superImageView =findViewById(R.id.myImageView);
        superWebView =findViewById(R.id.myWebView);
        superLinearLayout=findViewById(R.id.myLinearLayout);
        superProgressBar.setMax(100);

        superWebView.loadUrl("https://grosirmgwatch.com");
        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.getSettings().setAllowContentAccess(true);
        superWebView.getSettings().setAllowFileAccess(true);
        superWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        superWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        superWebView.getSettings().setAppCacheEnabled(true);
        superWebView.getSettings().setDomStorageEnabled(true);
        superWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        superWebView.getSettings().setLoadsImagesAutomatically(true);
        superWebView.setWebChromeClient(new WebChromeClient(){});
        superWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                superLinearLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                superLinearLayout.setVisibility(View.GONE);
                super.onPageFinished(view, url);
                myCurrentUrl=url;
            }
        });
        superWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                getSupportActionBar().setTitle(title);
//            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.super_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.menu_back:
//                onBackPressed();
//                break;
//
//            case R.id.menu_forward:
//                onForwardPressed();
//                break;
//
//            case R.id.menu_refresh:
//                superWebView.reload();
//                break;
//
//            case R.id.menu_share:
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                String shareSubject="Copied URL";
//                String shareTitle="Share With Your Friends";
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
//                shareIntent.putExtra(Intent.EXTRA_TEXT, myCurrentUrl);
//                startActivity(Intent.createChooser(shareIntent,shareTitle));
//
//
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    private void onForwardPressed() {
        if (superWebView.canGoForward())
        {
            superWebView.goForward();
        }
        else{
            Toast.makeText(this, "Cant't Go Further!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        if(superWebView.canGoBack()){
            superWebView.goBack();
        }
        else{
            finish();
        }
    }
}
