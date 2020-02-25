package com.example.gigadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gigadvisor.data.adapter.RatingAdapter;
import com.example.gigadvisor.data.model.Rating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ViewRatingActivityUber extends AppCompatActivity {


    private static String URL_AVERAGE_UBER="http://myprojectadvisor.000webhostapp.com/AveragePlatform/average_uber.php";
    public static float valueSaw,valueCt,valuePtl,valuePf;
    float valueUber;
    TextView visualizzaSaw, visualizzaCt, visualizzaPtl, visualizzaPf;
    TextView testoUber,linktrend;
    ListView listView;
    List<Rating> ratingList;
    Toolbar toolbarU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating_uber);

        toolbarU = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarU.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarU);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linktrend=(TextView) findViewById(R.id.trendUber);
        visualizzaSaw = (TextView) findViewById(R.id.viewSAW);
        visualizzaCt = (TextView) findViewById(R.id.viewCT);
        visualizzaPtl = (TextView) findViewById(R.id.viewPTL);
        visualizzaPf = (TextView) findViewById(R.id.viewPF);

        testoUber = (TextView) findViewById(R.id.txtUber);

        listView = (ListView) findViewById(R.id.listRating);
        ratingList = new ArrayList<>();
        showList();
        viewAverage();
        viewAverageUber();
        System.out.println(valueUber);

        linktrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trendUberActivity = new Intent(ViewRatingActivityUber.this, TrendUberActivity.class);
                startActivity(trendUberActivity);
            }
        });
    }

    private void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://myprojectadvisor.000webhostapp.com/View_Rating/view_rating_uber.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(URLDecoder.decode( response, "UTF-8" ) );
                            JSONArray jsonArray = jsonObject.getJSONArray("ViewUber");
                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Rating r = new Rating(object.getString("titolo"),object.getString("data"),object.getString("descrizione"));
                                ratingList.add(r);
                            }
                            RatingAdapter ratingAdapter = new RatingAdapter(ratingList,getApplicationContext());
                            listView.setAdapter(ratingAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    private void viewAverage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_AVERAGE_UBER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("PERCORTESIA", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Uber");

                            for(int i = 0; i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String saw = object.getString("SAW").trim();
                                    try{
                                        valueSaw = Float.parseFloat(saw);
                                        System.out.println(valueSaw);
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                    }
                                    visualizzaSaw.setText(saw);
                                    String ct = object.getString("CT").trim();
                                    try{
                                        valueCt = Float.parseFloat(ct);
                                        System.out.println(valueCt);
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                    }
                                    visualizzaCt.setText(ct);
                                    String ptl = object.getString("PTL").trim();
                                    try{
                                        valuePtl = Float.parseFloat(ptl);
                                        System.out.println(valuePtl);
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                    }
                                    visualizzaPtl.setText(ptl);
                                    String pf = object.getString("PF").trim();
                                    try{
                                        valuePf = Float.parseFloat(pf);
                                        System.out.println(valuePf);
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                    }
                                    visualizzaPf.setText(pf);
                                    //Toast.makeText(ViewRatingActivityUber.this,"YES",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ViewRatingActivityUber.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewRatingActivityUber.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void viewAverageUber() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_uber.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("Uber", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Uber");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String uber = object.getString("RATING_UBER").trim();
                                try{
                                    valueUber = Float.parseFloat(uber);
                                    System.out.println("Rating Uber: "+valueUber);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                testoUber.setText(""+valueUber);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(ViewRatingActivityUber.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ViewRatingActivityUber.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewRatingActivityUber.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
