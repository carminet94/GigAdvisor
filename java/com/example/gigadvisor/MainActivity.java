package com.example.gigadvisor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static int cont=0;
    private GestureDetectorCompat gestureObject;
    //private GestureDetectorCompat gestureDetectorCompat = null;
    //private Toolbar toolbar;
    TextView swipe, about, language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language=(TextView) findViewById(R.id.choselanguages);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLocale();
                showChangeLanguageDialog();
            }
        });
        swipe = (TextView) findViewById(R.id.swipeUp);
        about = (TextView) findViewById(R.id.goabout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InformationActivity.class));
            }
        });
        //DetectorSwipeGestureListener gestureListener = new DetectorSwipeGestureListener();
        //gestureListener.setActivity(this);
        //gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        if(cont>0){

        }else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("IMPORTANT NOTICE");
            builder.setMessage(R.string.initialMessage);
            builder.setIcon(R.drawable.ic_attention);
            builder.setPositiveButton("i got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Per altre informazioni consulta il sito", Toast.LENGTH_SHORT).show();
                    cont++;
                }
            });
            builder.create().show();
        }
        //toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //toolbar.setLogo(R.drawable.gigadvisorapp);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //gestureObject = new GestureDetectorCompat(this, new LearnGesture());


        //Inizializzo il menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.publication:
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
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        /*ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);*/

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }*/

    //class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        //@Override
        //public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //if(e2.getX() > e1.getX()){
                //Intent newintent = new Intent(MainActivity.this,InformationActivity.class);
                //finish();
                //startActivity(newintent);
                //overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
            //}/*else if(e2.getY() < e1.getY()){
                //System.out.println("BOH");
            //}
            //return true;
        //}
    //}
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void displayMessage(String mess){
        System.out.println(mess);
        //swipe.setText(mess);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.Ratings:
                Intent generalGraph = new Intent(MainActivity.this, GeneralGraphActivity.class);
                startActivity(generalGraph);
                return true;

            case R.id.Login:
                Intent loginactivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginactivity);
                return true;

            case R.id.Home:
                Toast.makeText(this, "Sei già nella Home Page", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceAsColor")
    private void showChangeLanguageDialog() {

        TextView textView = new TextView(MainActivity.this);
        textView.setText("CHOOSE LANGUAGE");
        textView.setPadding(20, 30, 20, 30);
        textView.setTextSize(20F);
        textView.setBackgroundColor(R.color.colorTitle);
        textView.setTextColor(Color.WHITE);

        final String[] listLanguages = {"Italiano","English"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setTitle("CHOOSE LANGUAGE...");
        builder.setCustomTitle(textView);
        builder.setIcon(R.drawable.ic_language);
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