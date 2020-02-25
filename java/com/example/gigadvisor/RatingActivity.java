package com.example.gigadvisor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class RatingActivity extends AppCompatActivity {

    int position = 0;
    RatingBar ratingBarSafety, ratingBarContr, ratingBarPaytime, ratingBarPlatf;
    TextView titleView;
    TextView descriptionView;
    TextView saw, ct, ptl, pf;
    Button buttonCoordinate, buttonNO;
    TextView latitudine, longitudine;
    TextView regSel;
    private LocationManager locationManager;
    private FusedLocationProviderClient client;
    Button rateAmazon;
    Button region;
    private static String URL_RATE_AMAZON = "http://myprojectadvisor.000webhostapp.com/rating.php";

    //String listItem[]={"Campania","Calabria","Niente"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        final AlertDialog.Builder builderInit = new AlertDialog.Builder(this);
        builderInit.setTitle("Important Notice");
        builderInit.setMessage(R.string.alertMessage);
        builderInit.setIcon(R.drawable.ic_attention);
        builderInit.setPositiveButton("Ho capito!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builderInit.create().show();

        int idPlat = getIntent().getExtras().getInt("ID");
        System.out.println(idPlat);

        requestPermissions();
        ratingBarSafety = (RatingBar) findViewById(R.id.ratingSafety);
        ratingBarContr = (RatingBar) findViewById(R.id.ratingContrTras);
        ratingBarPaytime = (RatingBar) findViewById(R.id.ratingPayTime);
        ratingBarPlatf = (RatingBar) findViewById(R.id.ratingPlatFair);
        rateAmazon =(Button) findViewById(R.id.rateButton);
        titleView =(TextView) findViewById(R.id.textTitle);
        descriptionView=(TextView) findViewById(R.id.textDescription);
        regSel = (TextView) findViewById(R.id.regioneSelezionata);
        buttonCoordinate = (Button) findViewById(R.id.buttonLatLong);
        buttonNO =(Button) findViewById(R.id.buttonLatLongNO);

        latitudine =(TextView) findViewById(R.id.textLatitudine);
        longitudine = (TextView) findViewById(R.id.textLongitudine);

        saw = (TextView) findViewById(R.id.txtSafety);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        saw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Safety At Work");
                builder.setMessage(R.string.safetyatwork);
                builder.setIcon(R.drawable.ic_safetyatwork_24dp);
                builder.setPositiveButton("Ho capito!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RatingActivity.this,"Per altre informazioni consulta il sito",Toast.LENGTH_LONG).show();
                    }
                });
                builder.create().show();
            }
        });

        ct = (TextView) findViewById(R.id.txtContract);
        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Safety At Work");
                builder.setMessage(R.string.safetyatwork);
                builder.setIcon(R.drawable.ic_safetyatwork_24dp);
                builder.setPositiveButton("Ho capito!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RatingActivity.this,"Per altre informazioni consulta il sito",Toast.LENGTH_LONG).show();
                    }
                });
                builder.create().show();
            }
        });

        ptl = (TextView) findViewById(R.id.txtPayness);
        ptl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Safety At Work");
                builder.setMessage(R.string.safetyatwork);
                builder.setIcon(R.drawable.ic_safetyatwork_24dp);
                builder.setPositiveButton("Ho capito!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RatingActivity.this,"Per altre informazioni consulta il sito",Toast.LENGTH_LONG).show();
                    }
                });
                builder.create().show();
            }
        });

        pf = (TextView) findViewById(R.id.txtFairness);
        pf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Safety At Work");
                builder.setMessage(R.string.safetyatwork);
                builder.setIcon(R.drawable.ic_safetyatwork_24dp);
                builder.setPositiveButton("Ho capito!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RatingActivity.this,"Per altre informazioni consulta il sito",Toast.LENGTH_LONG).show();
                    }
                });
                builder.create().show();
            }
        });
        region=(Button)findViewById(R.id.selectRegion);

        region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RatingActivity.this);
                final String list []= getResources().getStringArray(R.array.region_item);
                mBuilder.setTitle("Select Region");
                //mBuilder.setIcon(R.drawable.icon);
                mBuilder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                        System.out.println("Posizione: "+position);
                        System.out.println(list[which]);
                        regSel.setText(list[which]);
                        dialog.dismiss();
                    }
                })/*.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })*/;
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
            }
        });

        client = LocationServices.getFusedLocationProviderClient(this);
        buttonCoordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(RatingActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(RatingActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!=null){
                            //location.getLongitude();
                            latitudine.setText(String.valueOf(location.getLatitude()));
                            longitudine.setText(String.valueOf(location.getLongitude()));
                        }
                        //System.out.println("Latitudine:"+location.getLatitude());
                        //System.out.println("Longitudine:"+location.getLongitude());

                    }
                });

            }
        });

        buttonNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitudine.setText("0.000000");
                longitudine.setText("0.000000");
                Toast.makeText(RatingActivity.this,"Le tue coordinate non saranno salvate",Toast.LENGTH_LONG).show();
            }
        });

        /*
        if(buttonCoordinate.isPressed()==false){
            latitudine.setText("0.000000");
            longitudine.setText("0.000000");
        }*/

        //Valutare in caso fare un solo script per il rating delle piattaforme
        System.out.println("Activity parent: "+getParentActivityIntent());
        String activity="Intent { cmp=com.example.gigadvisor/.PlatformActivity }";
        if(activity.equals("Intent { cmp=com.example.gigadvisor/.PlatformActivity }")){
            System.out.println("FORSE");
        }
        rateAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });

    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }

    private void Regist(){

        final String titolo = titleView.getText().toString();
        final String descrizione = descriptionView.getText().toString();
        final String platform_id = String.valueOf(getIntent().getExtras().getInt("ID"));
        final String safetyatwork = String.valueOf(ratingBarSafety.getRating());
        final String contractstrasparency = String.valueOf((int) ratingBarContr.getRating());
        final String paymenttimeliness = String.valueOf((int) ratingBarPaytime.getRating());
        final String platformsfairness = String.valueOf((int) ratingBarPlatf.getRating());
        final String regione = String.valueOf(position);
        final String idNow = getIntent().getExtras().getString("id");
        final String lat = latitudine.getText().toString().trim();
        final String lon = longitudine.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RATE_AMAZON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(RatingActivity.this,"Rate Success! ",Toast.LENGTH_SHORT).show();
                                //String idMoment = getIntent().getExtras().getString("id");
                                //System.out.println(idMoment);
                                Intent intent = new Intent(RatingActivity.this,PlatformActivity.class);
                                intent.putExtra("id",idNow);
                                System.out.println(idNow);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RatingActivity.this,"Rate Error! "+e.toString(),Toast.LENGTH_SHORT).show();
                            //loadingbar.setVisibility(View.GONE);
                            //button_register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RatingActivity.this,"Register Error! "+error.toString(),Toast.LENGTH_SHORT).show();
                        //loadingbar.setVisibility(View.GONE);
                        //button_register.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("titolo",titolo);
                params.put("descrizione",descrizione);
                params.put("platform_id",platform_id);
                params.put("SAW", safetyatwork);
                params.put("CT", contractstrasparency);
                params.put("PTL", paymenttimeliness);
                params.put("PF", platformsfairness);
                params.put("regione", regione);
                params.put("user_id",idNow);
                params.put("latitudine",lat);
                params.put("longitudine",lon);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
