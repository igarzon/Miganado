
package miganado.Loginyregistro;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;
        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.appindexing.Thing;
        import com.google.android.gms.common.api.GoogleApiClient;


        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONArray;

        import java.sql.Array;

        import miganado.Data.ExplotacionDbHelper;
        import miganado.Loginyregistro.LoginRequest;
        import miganado.Loginyregistro.R;
        import miganado.Loginyregistro.RegistroActivity;
        import miganado.Loginyregistro.ZonaclienteActivity;

public class LoginActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btLogin = (Button) findViewById(R.id.btnLogin);
        final TextView registerlink = (TextView) findViewById(R.id.tvLinkregistro);
        final TextView resetlink = (TextView) findViewById(R.id.tvLinkreseteopassword);

        registerlink.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegistroActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        }));

        resetlink.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://gestiondemiganado.com/resetearpassword"));
                LoginActivity.this.startActivity(intent);
            }
        }));


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                               ExplotacionDbHelper mydb;
                                mydb = new ExplotacionDbHelper(getApplicationContext());

                                System.out.print("DDBB=   " + mydb.existExplotaciones());
                                if (mydb.existExplotaciones()==false) {

                                    Intent intent = new Intent(LoginActivity.this, Downloaddata.class);
                                    LoginActivity.this.startActivity(intent);

                                }
                                if (mydb.existExplotaciones()==true) {
                                    Intent intent = new Intent(LoginActivity.this, ZonaclienteActivity.class);
                                    intent.putExtra("username", username);
                                    LoginActivity.this.startActivity(intent);
                                }


                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage(" Ha fallado el Login")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                LoginRequest LoginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(LoginRequest);
            }
        });

    }

}