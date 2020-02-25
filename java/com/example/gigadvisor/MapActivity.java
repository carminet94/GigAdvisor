package com.example.gigadvisor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gigadvisor.data.model.Latitudine;
import com.example.gigadvisor.data.model.Longitudin;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;
        //viewCoordinate();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/MarkerMap.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Coordinate");
                            List<Latitudine> latitudine = new ArrayList<Latitudine>();
                            List<Longitudin> longitudine = new ArrayList<Longitudin>();
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Latitudine lat = new Latitudine();
                                Longitudin lon= new Longitudin();
                                lat.setLatitudine(object.getString("latitudine"));
                                lon.setLongitudin(object.getString("longitudine"));
                                latitudine.add(lat);
                                System.out.println(lat.getLatitudine());
                                longitudine.add(lon);
                                System.out.println(lon.getLongitudin());

                                try {
                                    double lt = Double.parseDouble(lat.getLatitudine());
                                    double ln = Double.parseDouble(lon.getLongitudin());
                                    System.out.println(lt+" / "+ln);
                                    LatLng prova = new LatLng(lt,ln);
                                    map.addMarker(new MarkerOptions().position(prova).title("VABBE"));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MapActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        float zoom =5.5f;

        LatLng DI = new LatLng(40.775035, 14.789083);
        LatLng Gaeta = new LatLng(41.210006, 13.569617);
        LatLng prova5 = new LatLng(44.493219, 11.342326);
        LatLng prova1 = new LatLng(42.124856, 12.741258);
        LatLng prova2 = new LatLng(40.525716, 17.674126);
        LatLng prova3 = new LatLng(37.906170, 15.245095);
        LatLng prova4 = new LatLng(41.848783, 12.623104);

        BitmapDescriptor iconFooroda = BitmapDescriptorFactory.fromResource(R.drawable.foodoramarker);
        BitmapDescriptor iconUber= BitmapDescriptorFactory.fromResource(R.drawable.ubermarker);
        BitmapDescriptor iconAmazon = BitmapDescriptorFactory.fromResource(R.drawable.amazonmarker);
        BitmapDescriptor iconJusteat= BitmapDescriptorFactory.fromResource(R.drawable.justeatmarker);
        BitmapDescriptor iconDeliveroo = BitmapDescriptorFactory.fromResource(R.drawable.deliveroomarker);
        BitmapDescriptor iconUpwork= BitmapDescriptorFactory.fromResource(R.drawable.upworkmarker);

        map.addMarker(new MarkerOptions().position(DI).title("Dipartimento di informatica").snippet("Prova Georeferenziazione"));
        map.addMarker(new MarkerOptions().position(Gaeta).title("Uber mai più").snippet("Insoddisfatto").icon(iconUber));
        map.addMarker(new MarkerOptions().position(new LatLng(45.464880, 9.184253)).title("Up Work").snippet("Molto male").icon(iconUpwork));
        map.addMarker(new MarkerOptions().position(prova1).title("Lavoro improponibile").snippet("Mai più per questa DLP").icon(iconAmazon));
        map.addMarker(new MarkerOptions().position(prova2).title("Eccellente").snippet("Pagamento veloce").icon(iconJusteat));
        map.addMarker(new MarkerOptions().position(prova3).title("Ottimo").snippet("Sicurezza importante").icon(iconDeliveroo));
        map.addMarker(new MarkerOptions().position(prova4).title("Estremamente soddisfatto").snippet("Ci siamo trovati molto molto bene").icon(iconUber));
        map.addMarker(new MarkerOptions().position(prova5).title("Recensione negativa").snippet("Non sono per nulla soddisfatto!").icon(iconFooroda));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(DI,zoom));

    }

/*
    private void viewCoordinate() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/prova2.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Coordinate");
                            List<Coordinates> dataList = new ArrayList<Coordinates>();
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Coordinates coordinate = new Coordinates();
                                coordinate.setLatitudine(object.getString("latitudine"));
                                coordinate.setLongitudine(object.getString("longitudine"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MapActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MapActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/
}
