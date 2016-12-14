package miganado.Loginyregistro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Downloaddata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaddata);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Button btDownload = (android.widget.Button) findViewById(R.id.btnDownloaddata);


        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            System.out.println(jsonArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                DownloaddataRequest DownloaddataRequest = new DownloaddataRequest(username,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(Downloaddata.this);
                queue.add(DownloaddataRequest);
            }
        });
    }
}
