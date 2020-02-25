package com.example.gigadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class LoginActivity extends AppCompatActivity {

    TextView linkLocal;
    EditText usernameLocal,passwordLocal;
    Button buttonLoginLocal;
    ProgressBar loadingLocal;
    Toolbar toolbarLog;

    private static String URL_LOGIN="http://myprojectadvisor.000webhostapp.com/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        toolbarLog = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarLog.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarLog);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        usernameLocal=findViewById(R.id.textUsernameLogin);
        passwordLocal=findViewById(R.id.textPasswordLogin);
        buttonLoginLocal=findViewById(R.id.buttonLogin);
        loadingLocal=findViewById(R.id.caricamentoLogin);
        linkLocal=findViewById(R.id.registrationLink);

        linkLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        buttonLoginLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUsername = usernameLocal.getText().toString().trim();
                String myPassword = passwordLocal.getText().toString().trim();
                if(!myUsername.isEmpty() || !myPassword.isEmpty()){
                    Login(myUsername,myPassword);
                }else{
                    usernameLocal.setError("Insert correct username");
                    passwordLocal.setError("Insert correct password");
                    
                }
            }
        });

    }

    private void Login(final String usernameMethod, final String passwordMethod) {
        loadingLocal.setVisibility(View.GONE);
        buttonLoginLocal.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                                for(int i = 0; i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String n = object.getString("nome").trim();
                                    String u = object.getString("username").trim();
                                    String id = object.getString("id").trim();
                                    Toast.makeText(LoginActivity.this,"Login Successful "+n,Toast.LENGTH_LONG).show();

                                    sessionManager.createSession(n,u,id);

                                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                    intent.putExtra("nome",n);
                                    intent.putExtra("username",u);
                                    intent.putExtra("id",id);

                                    String controllo = "control";
                                    intent.putExtra("control",controllo);

                                    //PlatformActivity.voteButton.setVisibility(View.VISIBLE);
                                    startActivity(intent);
                                    finish();
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"Error "+e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Error "+error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",usernameMethod);
                params.put("password",passwordMethod);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        toolbarLog.getMenu().findItem(R.id.Ratings).setVisible(false);
        //toolbarLog.getMenu().findItem(R.id.choseLanguage).setVisible(false);
        switch (id){
            case R.id.Map:
                startActivity(new Intent(LoginActivity.this,MapActivity.class));
                return true;

            case R.id.Login:
                Toast.makeText(this,"Sei già nella pagina di Login",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Home:
                Intent homeactivity = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(homeactivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
