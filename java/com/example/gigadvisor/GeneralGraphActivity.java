package com.example.gigadvisor;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GeneralGraphActivity extends AppCompatActivity {

    //int colorArray[]={R.color.Amazon,R.color.Foodora,R.color.JustEat,R.color.Delivero,R.color.Uber,R.color.UpWork};
    int[] colorClassArray = new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.YELLOW,Color.MAGENTA};
    String[] legendName = {"Amazon","Foodora","JustEat","Deliveroo", "Uber","UpWork"};
    Toolbar toolbarGraph;

    private BarChart mChart;
    public static float valueAmazon, valueFoodora, valueJusteat, valueDeliveroo, valueUber, valueUpwork;
    public static ArrayList<Float>values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_graph);

        toolbarGraph = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarGraph.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarGraph);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);
        unGrandeMetodo();
    }

    public void unGrandeMetodo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_amazon.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Amazon");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String amazon = object.getString("RATING_AMAZON").trim();
                                try{
                                    valueAmazon = Float.parseFloat(amazon);
                                    System.out.println("Rating Amazon: "+valueAmazon);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoUpWork.setText("Up Work: "+upwork);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_foodora.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Foodora");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String foodora = object.getString("RATING_FOODORA").trim();
                                try{
                                    valueFoodora = Float.parseFloat(foodora);
                                    System.out.println("Rating Foodora: "+valueFoodora);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoFoodora.setText("Foodora: "+foodora);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);
                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_justeat.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("JustEat");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String justeat = object.getString("RATING_JUSTEAT").trim();
                                try{
                                    valueJusteat = Float.parseFloat(justeat);
                                    System.out.println("Rating Just Eat: "+valueJusteat);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoJusteat.setText("Just Eat: "+justeat);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        StringRequest stringRequest3 = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_deliveroo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Deliveroo");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String deliveroo = object.getString("RATING_DELIVEROO").trim();
                                try{
                                    valueDeliveroo = Float.parseFloat(deliveroo);
                                    System.out.println("Rating Delivero: "+valueDeliveroo);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoDelivero.setText("Deliveroo: "+deliveroo);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        StringRequest stringRequest4 = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_uber.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
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
                                //testoUber.setText("Uber: "+uber);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };
        StringRequest stringRequest5 = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_upwork.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("UpWork");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String upwork = object.getString("RATING_UPWORK").trim();
                                try{
                                    valueUpwork = Float.parseFloat(upwork);
                                    System.out.println("Up Work: "+valueUpwork);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoUpWork.setText("Up Work: "+upwork);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                //Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                            System.out.println("Amazon:"+valueAmazon+" Foodora:"+valueFoodora+" JustEat:"+valueJusteat
                                    +" Deliveroo: "+valueDeliveroo+" Uber: "+valueUber+" UpWork:"+valueUpwork);

                            ArrayList<BarEntry> yVals = new ArrayList<>();

                            values = new ArrayList<>();
                            values.add(valueAmazon);
                            values.add(valueFoodora);
                            values.add(valueJusteat);
                            values.add(valueDeliveroo);
                            values.add(valueUber);
                            values.add(valueUpwork);

                            for (int i=0; i < values.size(); i++){
                                yVals.add(new BarEntry(i,values.get(i)));
                            }

                            String[] xAxisLabel = new String[]{"Amazon","Foodora","JustEat","Deliveroo",
                                    "Uber","UpWork"};
                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));

                            Legend legend = mChart.getLegend();
                            legend.setEnabled(true);
                            legend.setTextColor(Color.BLACK);
                            legend.setTextSize(10);
                            legend.setForm(Legend.LegendForm.CIRCLE);
                            legend.setFormSize(10);
                            legend.setXEntrySpace(10);
                            legend.setFormToTextSpace(5);

                            LegendEntry[] legendEntry = new LegendEntry[6];
                            for(int y=0;y<legendEntry.length;y++){
                                LegendEntry entry = new LegendEntry();
                                entry.formColor = colorClassArray[y];
                                entry.label = String.valueOf(legendName[y]);
                                legendEntry[y]=entry;
                            }
                            legend.setCustom(legendEntry);

                            BarDataSet set = new BarDataSet(yVals,"Platforms");
                            //set.setColors(Color.RED);
                            set.setColors(Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.YELLOW,Color.MAGENTA);
                            //set.setColors(ColorTemplate.MATERIAL_COLORS);
                            //set.setDrawValues(true);

                            //BarDataSet set1 = new BarDataSet(yVals,"prova 2");
                            //set.setColors(Color.MAGENTA);
                            //set.setColors(Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.YELLOW,Color.MAGENTA);
                            //set.setColors(ColorTemplate.MATERIAL_COLORS);
                            //set.setDrawValues(true);

                            //BarDataSet set2 = new BarDataSet(yVals,"prova 3");
                            //set.setColors(Color.BLUE);
                            //set.setColors(Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.YELLOW,Color.MAGENTA);
                            //set.setColors(ColorTemplate.MATERIAL_COLORS);
                            //set.setDrawValues(true);

                            BarData data = new BarData(set);
                            mChart.setData(data);
                            mChart.invalidate();
                            mChart.animateY(500);
                            mChart.setFitBars(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.add(stringRequest1);
        requestQueue.add(stringRequest2);
        requestQueue.add(stringRequest3);
        requestQueue.add(stringRequest4);
        requestQueue.add(stringRequest5);

    }

    /*
    public float viewAverageAmazon() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_amazon.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("Amazon", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Amazon");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String amazon = object.getString("RATING_AMAZON").trim();
                                try{
                                    valueAmazon = Float.parseFloat(amazon);
                                    System.out.println("Rating Amazon: "+valueAmazon);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoAmazon.setText("Amazon: "+amazon);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Valore Amazon", String.valueOf(valueAmazon));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return valueAmazon;
    }


    private float viewAverageFoodora() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_foodora.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("Foodora", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Foodora");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String foodora = object.getString("RATING_FOODORA").trim();
                                try{
                                    valueFoodora = Float.parseFloat(foodora);
                                    System.out.println("Rating Foodora: "+valueFoodora);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoFoodora.setText("Foodora: "+foodora);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return valueFoodora;
    }


    private float viewAverageJustEat() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_justeat.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("JustEat", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("JustEat");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String justeat = object.getString("RATING_JUSTEAT").trim();
                                try{
                                    valueJusteat = Float.parseFloat(justeat);
                                    System.out.println("Rating Just Eat: "+valueJusteat);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoJusteat.setText("Just Eat: "+justeat);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return valueFoodora;
    }


    private float viewAverageDeliveroo() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_deliveroo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("Deliveroo: ", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Deliveroo");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String deliveroo = object.getString("RATING_DELIVEROO").trim();
                                try{
                                    valueDeliveroo = Float.parseFloat(deliveroo);
                                    System.out.println("Rating Delivero: "+valueDeliveroo);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoDelivero.setText("Deliveroo: "+deliveroo);//E' una stringa, per usarlo fare il Float.parseFloat
                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return valueDeliveroo;
    }


    private float viewAverageUber() {
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
                                //testoUber.setText("Uber: "+uber);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return valueUber;
    }


    private float viewAverageUpWork() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://myprojectadvisor.000webhostapp.com/View_Average_Platform/view_rating_upwork.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("UpWork", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("UpWork");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String upwork = object.getString("RATING_UPWORK").trim();
                                try{
                                    valueUpwork = Float.parseFloat(upwork);
                                    System.out.println("Up Work: "+valueUpwork);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                //testoUpWork.setText("Up Work: "+upwork);//E' una stringa, per usarlo fare il Float.parseFloat

                                //float t = Float.parseFloat(testoAmazon.getText().toString().trim());
                                //System.out.println("Valore del cazzo: "+t);

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(GeneralGraphActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GeneralGraphActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return valueUpwork;
    }
*/


}
