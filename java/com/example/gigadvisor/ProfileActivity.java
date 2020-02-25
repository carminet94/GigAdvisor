package com.example.gigadvisor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    int position=0;
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private TextView nome,user;
    Button b_logout, b_profile, sex;
    SessionManager sessionManager;
    String getId;
    EditText birthday, resid, titolostudio;
    private Menu action;
    private static String URL_READ="http://myprojectadvisor.000webhostapp.com/read_data.php";
    private static String URL_EDIT="http://myprojectadvisor.000webhostapp.com/edit_data.php";
    private static String URL_PROFILE="https://myprojectadvisor.000webhostapp.com/profile.php";
    private static String URL_READ_PROFILE ="https://myprojectadvisor.000webhostapp.com/readDataForProfile.php";
    TextView goPlat,sexText, nomeView, cognomeView, bottoneProfile, datNasc, res, studytitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PlatformActivity.showButton = true;

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //nome = findViewById(R.id.nome);
        //user = findViewById(R.id.username);
        nomeView = (TextView) findViewById(R.id.viewNomeProfilo);
        cognomeView = (TextView) findViewById(R.id.viewCognomeProfilo);
        getDetailForProfile();
        bottoneProfile = (Button) findViewById(R.id.showForm);
        b_logout = findViewById(R.id.buttonLogout);
        b_profile = findViewById(R.id.createProfile);
        datNasc = (TextView) findViewById(R.id.txtDataNascita);
        res = (TextView) findViewById(R.id.txtResidenza);
        studytitle = (TextView) findViewById(R.id.txtTitoloStudio);
        birthday = findViewById(R.id.dataNascita);
        resid = findViewById(R.id.residence);
        titolostudio = findViewById(R.id.studyTitle);

        final String controlPlat = getIntent().getExtras().getString("set");
        System.out.println(controlPlat);

        sex=(Button)findViewById(R.id.choiceSex);
        sexText=findViewById(R.id.sexSel);
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
                final String list []= getResources().getStringArray(R.array.item_sex);
                mBuilder.setTitle("Select Sex");
                //mBuilder.setIcon(R.drawable.icon);
                mBuilder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                        System.out.println("Sesso: "+position);
                        System.out.println(list[which]);
                        sexText.setText(list[which]);
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

        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        bottoneProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthday.setVisibility(View.VISIBLE);
                resid.setVisibility(View.VISIBLE);
                titolostudio.setVisibility(View.VISIBLE);
                sex.setVisibility(Button.VISIBLE);
                sexText.setVisibility(View.VISIBLE);
                datNasc.setVisibility(View.VISIBLE);
                res.setVisibility(View.VISIBLE);
                studytitle.setVisibility(View.VISIBLE);
                b_profile.setVisibility(Button.VISIBLE);
            }
        });

        /*Intent intent = getIntent();
        String extraNome = intent.getStringExtra("nome");
        String extraUser = intent.getStringExtra("username");*/

        HashMap<String,String> users = sessionManager.getUserDetail();
        getId=users.get(sessionManager.ID);

        /*String mNome = users.get(SessionManager.NAME);
        String mUsername = users.get(SessionManager.USERNAME);

        nome.setText(mNome);
        user.setText(mUsername);*/

        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        //Ho passato l'id dalla LoginActivity quando mi sono loggato
        //String idNow = getIntent().getExtras().getString("id");
        //System.out.println(idNow);

        goPlat=(TextView)findViewById(R.id.goToPlatoform);
        goPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlatform();
            }
        });

    }

    private void getDetailForProfile() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")){

                                for(int i=0; i <jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strNome = object.getString("nome").trim();
                                    String strUsername = object.getString("cognome").trim();

                                    nomeView.setText(strNome);
                                    cognomeView.setText(strUsername);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this,"Error Reading "+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this,"Error Reading "+error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void updateProfile() {
        String sesso = "";
        if(position==0){
            sesso="M";
        }else if(position==1){
            sesso="F";
        }
        final String data = this.birthday.getText().toString().trim();
        final String residenza = this.resid.getText().toString().trim();
        final String titstudio = this.titolostudio.getText().toString().trim();
        final String idUpdate = getIntent().getExtras().getString("id");
        final String finalSesso = sesso;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(ProfileActivity.this,"Create Profile Success! ",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this,"Profile Error! "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this,"Profile Error! "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_user",idUpdate);
                params.put("data_nascita",data);
                params.put("sesso", finalSesso);
                params.put("residenza",residenza);
                params.put("titolo_studio",titstudio);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void goToPlatform() {
        String controllo = getIntent().getExtras().getString("control");
        String idNow = getIntent().getExtras().getString("id");
        System.out.println(idNow);
        System.out.println(controllo);

        Intent intent = new Intent(ProfileActivity.this, PlatformActivity.class);
        intent.putExtra("id",idNow);
        intent.putExtra("control",controllo);
        String idProfilo="1";
        intent.putExtra("Profilo",idProfilo);
        startActivity(intent);
    }




    //GetuserDetail
    private void getUserDetail (){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG,response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")){

                                for(int i=0; i <jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strNome = object.getString("nome").trim();
                                    String strUsername = object.getString("username").trim();

                                    nome.setText(strNome);
                                    user.setText(strUsername);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this,"Error Reading "+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this,"Error Reading "+error.toString(),Toast.LENGTH_LONG).show();
                    }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action,menu);

        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_edit:
                nome.setFocusableInTouchMode(true);
                user.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(nome, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                SaveEditDetail();
                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                nome.setFocusableInTouchMode(false);
                user.setFocusableInTouchMode(false);
                nome.setFocusable(false);
                user.setFocusable(false);

                return true;

                default:
                    return super.onOptionsItemSelected(item);

        }
    }*/

    //Save
    private void SaveEditDetail() {
        final String nome = this.nome.getText().toString().trim();
        final String username = this.user.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            Log.i(response,"NIENTE?");
                            if(success.equals("1")){
                                Toast.makeText(ProfileActivity.this,"Success! ",Toast.LENGTH_LONG).show();
                                sessionManager.createSession(nome,username,id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this,"Error! "+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this,"Error! "+error.toString(),Toast.LENGTH_LONG).show();
                }
             })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("username",username);
                params.put("id",id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
