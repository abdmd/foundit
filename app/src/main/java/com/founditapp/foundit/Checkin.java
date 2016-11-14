package com.founditapp.foundit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class Checkin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCheckin);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final WebView mWebview  = (WebView)findViewById(R.id.webView);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.setInitialScale(1);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setBuiltInZoomControls(true);

        final Activity activity = this;
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        loadWebPage(mWebview, "https://checkin.airasia.com/");

        // navigate to airasia
        ImageView bAA = (ImageView)findViewById(R.id.btnAA);
        assert bAA != null;
        bAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadWebPage(mWebview, "https://checkin.airasia.com/");
            }
        });

        // navigate to malindo
        ImageView bMA = (ImageView)findViewById(R.id.btnMA);
        assert bMA != null;
        bMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadWebPage(mWebview, "http://www.malindoair.com/malindoair-home/check-in-online");
            }
        });

        // navigate to tiger
        ImageView bTA = (ImageView)findViewById(R.id.btnTA);
        assert bTA != null;
        bTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadWebPage(mWebview, "https://booking.tigerair.com/WebCheckIn.aspx");
            }
        });

        // navigate to jetstar
        ImageView bJA = (ImageView)findViewById(R.id.btnJA);
        assert bJA != null;
        bJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadWebPage(mWebview, "https://checkin.jetstar.com/");
            }
        });
    }

    public void loadWebPage(WebView wv, String url)
    {
        WebView mWebView = wv;

        if (mWebView.getUrl() != url) {
            mWebView.getSettings().setJavaScriptEnabled(true);

            final Activity activity = this;

            mWebView.setWebChromeClient(new WebChromeClient() {

                public void onProgressChanged(WebView view, int progress) {
                    activity.setTitle("Loading...");
                    activity.setProgress(progress * 100);
                    if (progress == 100)
                        activity.setTitle("Foundit");
                }
            });

            mWebView.loadUrl(url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
