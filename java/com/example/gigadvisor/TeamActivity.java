package com.example.gigadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeamActivity extends AppCompatActivity {

    Toolbar toolbarTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        toolbarTeam = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarTeam.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarTeam);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Inizializzo il menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.team);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    /*case R.id.platform:
                        startActivity(new Intent(getApplicationContext(),PlatformActivity.class));
                        overridePendingTransition(0,0);
                        return true;*/
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.publication:
                        startActivity(new Intent(getApplicationContext(), PublicationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.team:
                        return true;
                    /*case R.id.about:
                        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;*/
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
