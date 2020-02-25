package com.example.gigadvisor;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KnowYourRightsActivity extends AppCompatActivity {

    private Toolbar toolbarInf;
    TextView downloadLink, continueactivity;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_rights);

        toolbarInf = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarInf.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarInf);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        continueactivity = (TextView) findViewById(R.id.continuemore);
        continueactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KnowYourRightsActivity.this,KnowYourRightsTwo.class));
            }
        });
        /*downloadLink = (TextView) findViewById(R.id.download);
        downloadLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                //Uri uri = Uri.parse("http://myprojectadvisor.000webhostapp.com/Dati_GigAdvisor.pdf");
                Uri uri = Uri.parse("http://myprojectadvisor.000webhostapp.com/grigliatutele.pdf");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;                    case R.id.publication:
                        startActivity(new Intent(getApplicationContext(), PublicationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.team:
                        startActivity(new Intent(getApplicationContext(), TeamActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    /*case R.id.about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;*/
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

}
