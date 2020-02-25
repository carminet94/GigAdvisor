package com.example.gigadvisor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    ImageView changeLanguage, forum;
    TextView rights, gig, connect, rating, link;
    Toolbar toolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbarSearch = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarSearch.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*loadLocale();

        changeLanguage = (ImageView) findViewById(R.id.cambiaLingua);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        forum = (ImageView) findViewById(R.id.community);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,CommunityActivity.class));
            }
        });*/

        /*
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.platform:
                        startActivity(new Intent(getApplicationContext(),PlatformActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.team:
                        startActivity(new Intent(getApplicationContext(), TeamActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        return true;
                }
                return false;
            }
        });*/

        link=(TextView)findViewById(R.id.downloadrawdata);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle(R.string.dialoglogin);
                builder.setMessage(R.string.messagedownload);
                builder.setIcon(R.drawable.ic_attention);
                builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SearchActivity.this,LoginActivity.class));
                    }
                });
                builder.create().show();
            }
        });

        rights =(TextView) findViewById(R.id.knowyourrights);
        rights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, KnowYourRightsActivity.class));
            }
        });
        rating = (TextView) findViewById(R.id.platformrating);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,GeneralGraphActivity.class));
            }
        });
        connect =(TextView) findViewById(R.id.connectwithother);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,CommunityActivity.class));
            }
        });
        gig = (TextView) findViewById(R.id.rateyourgig);
        gig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,PlatformActivity.class));
            }
        });

        //Inizializzo il menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                    case R.id.publication:
                        startActivity(new Intent(getApplicationContext(), PublicationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        return true;
                }
                return false;
            }
        });

    }

    private void showChangeLanguageDialog() {

        final String[] listLanguages = {"Italian","English"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listLanguages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("it");
                    recreate();
                }
                if(i==1){
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void setLocale(String Lang) {
        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My Lang", Lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences preferences= getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My Lang","");
        setLocale(language);
    }
}
