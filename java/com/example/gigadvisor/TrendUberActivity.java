package com.example.gigadvisor;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class TrendUberActivity extends AppCompatActivity {

    //int[] colorClassArray = new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.YELLOW,Color.MAGENTA};
    String[] legendName = {"Febbraio","Marzo","Aprile","Maggio", "Giugno","Luglio"};

    private LineChart mChartUber;

    private BarChart mChart;
    public static float valueAmazon, valueFoodora, valueJusteat, valueDeliveroo, valueUber, valueUpwork;
    public static ArrayList<Float> values;
    Toolbar toolbarGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_uber);
        /*mChart = (BarChart) findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);
        unGrandeMetodoPerUber();*/

        toolbarGraph = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarGraph.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarGraph);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mChartUber = (LineChart) findViewById(R.id.linechartUber);

        mChartUber.setDragEnabled(true);
        mChartUber.setScaleEnabled(false);



        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(1,2.0f));
        yValues.add(new Entry(2,4.0f));
        yValues.add(new Entry(3,2.0f));
        yValues.add(new Entry(4,3.0f));
        yValues.add(new Entry(5,4.0f));
        yValues.add(new Entry(6,1.0f));

        ArrayList<Entry> yValues1 = new ArrayList<>();
        yValues1.add(new Entry(1,1.3f));
        yValues1.add(new Entry(2,2.4f));
        yValues1.add(new Entry(3,0.9f));
        yValues1.add(new Entry(4,4.0f));
        yValues1.add(new Entry(5,2.0f));
        yValues1.add(new Entry(6,3.0f));


        ArrayList<Entry> yValues2 = new ArrayList<>();
        yValues2.add(new Entry(1,2.4f));
        yValues2.add(new Entry(2,3.4f));
        yValues2.add(new Entry(3,0.9f));
        yValues2.add(new Entry(4,3.0f));
        yValues2.add(new Entry(5,5.0f));
        yValues2.add(new Entry(6,1.2f));

        ArrayList<Entry> yValues3 = new ArrayList<>();
        yValues3.add(new Entry(1,1.4f));
        yValues3.add(new Entry(2,5.0f));
        yValues3.add(new Entry(3,3.2f));
        yValues3.add(new Entry(4,2.1f));
        yValues3.add(new Entry(5,3.7f));
        yValues3.add(new Entry(6,2.5f));


        //String[] xAxisLabel = new String[]{"Gennaio","Aprile","Giugno"};
        //XAxis xAxis = mChartUber.getXAxis();
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));

        /*Legend legend = mChartUber.getLegend();
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
            //entry.formColor = colorClassArray[y];
            entry.label = String.valueOf(legendName[y]);
            legendEntry[y]=entry;
        }
        legend.setCustom(legendEntry);*/


        LineDataSet set1 = new LineDataSet(yValues,"Safety At Work");
        LineDataSet set2 = new LineDataSet(yValues1,"Contracts Transparency");
        LineDataSet set3 = new LineDataSet(yValues2,"Payment Timeliness");
        LineDataSet set4 = new LineDataSet(yValues3,"Platforms fairness");

        set1.setColor(Color.GREEN);
        set1.setLineWidth(1.5f);
        set1.setFillAlpha(110);
        set1.setValueTextSize(8f);
        set1.setValueTextColor(Color.BLACK);

        set2.setColor(Color.RED);
        set2.setLineWidth(1.5f);
        set2.setFillAlpha(110);
        set1.setValueTextSize(8f);
        set2.setValueTextColor(Color.BLACK);

        set3.setColor(Color.YELLOW);
        set3.setLineWidth(1.5f);
        set3.setFillAlpha(110);
        set1.setValueTextSize(8f);
        set3.setValueTextColor(Color.BLACK);

        set4.setColor(Color.CYAN);
        set4.setLineWidth(1.5f);
        set4.setFillAlpha(110);
        set1.setValueTextSize(8f);
        set4.setValueTextColor(Color.BLACK);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);

        LineData data = new LineData(dataSets);

        mChartUber.setData(data);


    }


    /*public void unGrandeMetodoPerUber(){
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

                                Toast.makeText(GeneralGraphActivity.this,"YES MAN",Toast.LENGTH_LONG).show();
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

    }*/

}
