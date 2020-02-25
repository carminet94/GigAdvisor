package com.example.gigadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CommunityActivity extends AppCompatActivity {

    private WebView webView;
    private Toolbar toolbarComm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        toolbarComm = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarComm.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarComm);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //toolbarComm = (Toolbar) findViewById(R.id.my_toolbar);
        //toolbarComm.setLogo(R.drawable.gigadvisorapp);
        //setSupportActionBar(toolbarComm);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        webView = (WebView) findViewById(R.id.webViewCommunity);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://myprojectadvisor.000webhostapp.com/phpBB3/index.php?sid=bbb519c9d375967d823318ad2f6a7d9f");
        //webView.loadUrl("https://gigadvisor.herokuapp.com/GigAdvisor/forum/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.canGoBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        toolbarComm.getMenu().findItem(R.id.Login).setVisible(false);
        toolbarComm.getMenu().findItem(R.id.Profile).setVisible(false);
        //toolbarComm.getMenu().findItem(R.id.choseLanguage).setVisible(false);
        toolbarComm.getMenu().findItem(R.id.Map).setVisible(false);
        int id = item.getItemId();
        switch (id){

            case R.id.Ratings:
                Intent generalGraph = new Intent(CommunityActivity.this, GeneralGraphActivity.class);
                startActivity(generalGraph);
                return true;

            case R.id.Home:
                Intent homeactivity = new Intent( CommunityActivity.this, MainActivity.class);
                startActivity(homeactivity);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
