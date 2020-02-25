package com.example.gigadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.example.gigadvisor.data.model.JavaMailAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,surname,email,username,password,c_password;
    private Button button_register;
    private ProgressBar loadingbar;
    private static String URL_REGISTER="http://myprojectadvisor.000webhostapp.com/register.php";
    private Toolbar toolbarReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbarReg = (Toolbar) findViewById(R.id.my_toolbar);
        toolbarReg.setLogo(R.drawable.gigadvisorapp);
        setSupportActionBar(toolbarReg);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadingbar = findViewById(R.id.loading);
        name = findViewById(R.id.textName);
        surname = findViewById(R.id.textSurname);
        email = findViewById(R.id.textEmail);
        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.textPasswordLogin);
        c_password = findViewById(R.id.textConfirmPassword);
        button_register = findViewById(R.id.buttonRegister);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
                //sendMail();
            }
        });
    }

    private void sendMail() {

        String mail = email.getText().toString().trim();
        String subject = "Registrazione GigAdvisor";
        String message = "Benvenuto su GigAdvisor";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
    }

    private void Regist () {
        loadingbar.setVisibility(View.VISIBLE);
        button_register.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String surname = this.surname.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(RegisterActivity.this,"Register Success! ",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"Register Error! "+e.toString(),Toast.LENGTH_SHORT).show();
                            loadingbar.setVisibility(View.GONE);
                            button_register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register Error! "+error.toString(),Toast.LENGTH_SHORT).show();
                        loadingbar.setVisibility(View.GONE);
                        button_register.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nome",name);
                params.put("cognome",surname);
                params.put("email",email);
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
