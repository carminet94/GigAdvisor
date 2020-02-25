package com.example.gigadvisor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

;

public class PlatformActivity extends AppCompatActivity {

    Button voteButtonAmazon,voteButtonFoodora,voteButtonJustEat,voteButtonDeliveroo,voteButtonUber,voteButtonUpWork;
    ImageView uberimg, amazonimg;
    public static int idPlatform;
    public static boolean showButton=false;
    Toolbar toolbarPlat;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);

        toolbarPlat = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarPlat.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarPlat);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        /*TextView textView = new TextView(PlatformActivity.this);
        textView.setText(R.string.dialoglogin);
        textView.setPadding(20, 30, 20, 30);
        textView.setTextSize(20F);
        textView.setBackgroundColor(R.color.colorTitle);
        textView.setTextColor(Color.BLACK);*/

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialoglogin);
        builder.setMessage(R.string.messagelogin);
        builder.setIcon(R.drawable.ic_attention);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PlatformActivity.this,LoginActivity.class));
            }
        });
        builder.create().show();

        //Inizializzo il menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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


        voteButtonAmazon=(Button) findViewById(R.id.rateButtonAmazon);
        voteButtonAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String idNow = getIntent().getExtras().getString("id");
                    Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                    ratingActivity.putExtra("id",idNow);
                    idPlatform=1;
                    ratingActivity.putExtra("ID",idPlatform);
                    System.out.println(idNow);
                    startActivity(ratingActivity);
            }
        });

        voteButtonFoodora=(Button) findViewById(R.id.rateButtonFoodora);
        voteButtonFoodora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNow = getIntent().getExtras().getString("id");
                Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                ratingActivity.putExtra("id",idNow);
                idPlatform=2;
                ratingActivity.putExtra("ID",idPlatform);
                System.out.println(idNow);
                startActivity(ratingActivity);
            }
        });

        voteButtonJustEat=(Button) findViewById(R.id.rateButtonJustEat);
        voteButtonJustEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNow = getIntent().getExtras().getString("id");
                Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                ratingActivity.putExtra("id",idNow);
                idPlatform=3;
                ratingActivity.putExtra("ID",idPlatform);
                System.out.println(idNow);
                startActivity(ratingActivity);
            }
        });

        voteButtonDeliveroo=(Button) findViewById(R.id.rateButtonDeliveroo);
        voteButtonDeliveroo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNow = getIntent().getExtras().getString("id");
                Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                ratingActivity.putExtra("id",idNow);
                idPlatform=4;
                ratingActivity.putExtra("ID",idPlatform);
                System.out.println(idNow);
                startActivity(ratingActivity);
            }
        });

        voteButtonUber=(Button) findViewById(R.id.rateButtonUber);
        voteButtonUber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNow = getIntent().getExtras().getString("id");
                Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                ratingActivity.putExtra("id",idNow);
                idPlatform=5;
                ratingActivity.putExtra("ID",idPlatform);
                System.out.println(idNow);
                startActivity(ratingActivity);
            }
        });

        voteButtonUpWork=(Button) findViewById(R.id.rateButtonUpWork);
        voteButtonUpWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNow = getIntent().getExtras().getString("id");
                Intent ratingActivity = new Intent(PlatformActivity.this, RatingActivity.class);
                ratingActivity.putExtra("id",idNow);
                idPlatform=6;
                ratingActivity.putExtra("ID",idPlatform);
                System.out.println(idNow);
                startActivity(ratingActivity);
            }
        });

        if(showButton==true){
            voteButtonAmazon.setEnabled(true);
            voteButtonAmazon.setEnabled(true);
            voteButtonFoodora.setEnabled(true);
            voteButtonJustEat.setEnabled(true);
            voteButtonDeliveroo.setEnabled(true);
            voteButtonUber.setEnabled(true);
            voteButtonUpWork.setEnabled(true);

        }
        System.out.println(showButton);

        uberimg=(ImageView) findViewById(R.id.uberImage);
        uberimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRating = new Intent(PlatformActivity.this, ViewRatingActivityUber.class);
                startActivity(viewRating);
            }
        });

        /*amazonimg=(ImageView) findViewById(R.id.amazonImage);
        amazonimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRating = new Intent(PlatformActivity.this, ViewRatingActivityAmazon.class);
                startActivity(viewRating);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toolbarPlat.getMenu().findItem(R.id.Ratings).setVisible(false);
        if(showButton==true){
            toolbarPlat.getMenu().findItem(R.id.Login).setVisible(false);
            toolbarPlat.getMenu().findItem(R.id.Profile).setVisible(true);
        }
        int id = item.getItemId();
        switch (id){

            /*case R.id.choseLanguage:
                    loadLocale();
                    showChangeLanguageDialog();
                    return true;*/

            case R.id.Profile:
                Intent profileActivity = new Intent(PlatformActivity.this,ProfileActivity.class);
                String controlloProfilo ="set";
                profileActivity.putExtra("set",controlloProfilo);
                startActivity(profileActivity);
                return true;

            case R.id.Login:
                /*if(showButton==true){
                    toolbarPlat.getMenu().findItem(R.id.Login).setVisible(false);
                    toolbarPlat.getMenu().findItem(R.id.Logout).setVisible(true);*/
                //}else{
                    Intent loginactivity = new Intent(PlatformActivity.this,LoginActivity.class);
                    startActivity(loginactivity);
                //}
                return true;

            case R.id.Home:
                Intent homeactivity = new Intent( PlatformActivity.this, MainActivity.class);
                startActivity(homeactivity);
                return true;

            case R.id.Map:
                Intent mapActivity = new Intent( PlatformActivity.this, MapActivity.class);
                startActivity(mapActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*private void showChangeLanguageDialog() {

        final String[] listLanguages = {"Italian","English"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlatformActivity.this);
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

    }*/

    /*
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
    }*/
}
