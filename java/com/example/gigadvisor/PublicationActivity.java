package com.example.gigadvisor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PublicationActivity extends AppCompatActivity {

    Toolbar toolbarPub;
    TextView link1,link2,link3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication);

        toolbarPub = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarPub.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarPub);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        link1 = (TextView) findViewById(R.id.seemore1);
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.mdpi.com/1999-5903/11/7/163/htm"));
                startActivity(intent);
            }
        });
        link2 = (TextView) findViewById(R.id.seemore2);
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://oa.inapp.org/xmlui/handle/123456789/371"));
                startActivity(intent);
            }
        });
        link3 = (TextView) findViewById(R.id.seemore3);
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://prezi.com/htu90mezgz09/digital-labour-platforms-algorithmic-governance-and-techno-regulation-towards-the-computational-evolution-of-law-and-labor-studies/?utm_campaign=share&utm_medium=copy"));
                startActivity(intent);
            }
        });
        //Inizializzo il menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.publication);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.publication:
                        return true;
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
