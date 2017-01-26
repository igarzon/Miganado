package miganado.Loginyregistro;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegistroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPassword2 = (EditText) findViewById(R.id.etPassword2);
        final EditText etNombre = (EditText) findViewById(R.id.etNombreyapellidos);
        final Button btnRegisro = (Button) findViewById(R.id.btnRegistro);
        final EditText etDato1 = (EditText) findViewById(R.id.etDato1);
        final EditText etDato2 = (EditText) findViewById(R.id.etDato2);
        final EditText etDato3 = (EditText) findViewById(R.id.etDato3);
        final EditText etDato4 = (EditText) findViewById(R.id.etDato4);
        final EditText etDato5 = (EditText) findViewById(R.id.etDato5);
        final EditText etDato6 = (EditText) findViewById(R.id.etDato6);
        final RadioButton rbTitular = (RadioButton) findViewById(R.id.rbTitular);

        etPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
        etPassword2.setTypeface(Typeface.DEFAULT);
        etPassword2.setTransformationMethod(new PasswordTransformationMethod());

        btnRegisro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String password2 = etPassword2.getText().toString();
                final String nombre = etNombre.getText().toString();

                boolean estado = rbTitular.isChecked();
                String tipoacceso="";
                if (estado) {
                    tipoacceso  = "titular";
                }
                else
                {
                    tipoacceso = "terceros";
                }


                final String dato1 = etDato1.getText().toString();
                final String dato2 = etDato2.getText().toString();
                final String dato3 = etDato3.getText().toString();
                final String dato4 = etDato4.getText().toString();
                final String dato5 = etDato5.getText().toString();
                final String dato6 = etDato6.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String error=jsonResponse.getString("message");
                            if (success){
                                Intent intent=new Intent(RegistroActivity.this, LoginActivity.class);
                                intent.putExtra("username", username);
                                RegistroActivity.this.startActivity(intent);
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder (RegistroActivity.this);
                                builder.setMessage( "Ha fallado el registro: " + error )
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }


                        } catch (JSONException e){e.printStackTrace();}
                    }
                };
                RegisterRequest RegisterRequest = new RegisterRequest(username, password, password2, nombre, tipoacceso, dato1, dato2, dato3, dato4, dato5, dato6, responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegistroActivity.this);
                queue.add(RegisterRequest);
            }
        });


    }


}
