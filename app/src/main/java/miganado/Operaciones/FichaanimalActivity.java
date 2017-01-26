package miganado.Operaciones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.CacheRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miganado.Configuracion.CheckConnectivity;
import miganado.Configuracion.UpdateDataRequest;
import miganado.Data.Explotacion;
import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.SessionManager;
import miganado.Loginyregistro.ZonaclienteActivity;

public class FichaanimalActivity extends AppCompatActivity {

    private ArrayList<EditText> editText = new ArrayList<EditText>();
    private Bundle b = new Bundle();
    private TextView crotal;
    private GlobalVariable gb = new GlobalVariable();
    private static final String MI_GANADO_PRIMARY_COLOR = "#70ac47";

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaanimal);

        queue = Volley.newRequestQueue(FichaanimalActivity.this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        b = this.getIntent().getExtras();
        //ImageView relative = (ImageView) findViewById(R.id.ivAnimal);

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        Cursor datos = mydb.getCrotal(b.getString((String) b.keySet().toArray()[0]));

        LinearLayout linear = (LinearLayout) findViewById(R.id.ficha);
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i = 1; i<datos.getColumnCount()-9;i++) {
            datos.moveToFirst();

            TextView aux1 = new TextView(this);
            aux1.setLayoutParams(lparams);
            aux1.setText(editarCadenaTexto(datos.getColumnName(i)));
            aux1.setTextColor(Color.parseColor(MI_GANADO_PRIMARY_COLOR));
            aux1.setTextSize(30);

            linear.addView(aux1);

            if(i>1) {
                EditText aux2 = new EditText(this);
                aux2.setLayoutParams(lparams);
                aux2.setText(datos.getString(i));
                aux2.setTextColor(Color.WHITE);
                aux2.setTextSize(23);
                editText.add(aux2);
                linear.addView(aux2);
            }

            else{
                crotal = new TextView(this);
                crotal.setLayoutParams(lparams);
                crotal.setText(datos.getString(i));
                crotal.setTextColor(Color.WHITE);

                crotal.setTextSize(23);
                linear.addView(crotal);
            }

        }

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FichaanimalActivity.this);
        String [] s = new String[6];
        s[0] = pref.getString("dato1", "").equals("") ? "Dato1" : pref.getString("dato1", "");
        s[1] = pref.getString("dato2", "").equals("") ? "Dato2" : pref.getString("dato2", "");
        s[2] = pref.getString("dato3", "").equals("") ? "Dato3" : pref.getString("dato3", "");
        s[3] = pref.getString("dato4", "").equals("") ? "Dato4" : pref.getString("dato4", "");
        s[4] = pref.getString("dato5", "").equals("") ? "Dato5" : pref.getString("dato5", "");
        s[5] = pref.getString("dato6", "").equals("") ? "Dato6" : pref.getString("dato6", "");


        for(int i = datos.getColumnCount()-9; i<datos.getColumnCount()-3;i++) {
            datos.moveToFirst();
            String a = datos.getString(i);

            TextView aux1 = new TextView(this);
            aux1.setLayoutParams(lparams);
            aux1.setText(s[i - (datos.getColumnCount() - 9)]);
            aux1.setTextColor(Color.parseColor(MI_GANADO_PRIMARY_COLOR));
            aux1.setTextSize(30);

            linear.addView(aux1);

            EditText aux2 = new EditText(this);
            aux2.setLayoutParams(lparams);
            aux2.setText(datos.getString(i));
            aux2.setTextColor(Color.WHITE);
            aux2.setTextSize(25);
            editText.add(aux2);
            linear.addView(aux2);

        }
    }


    public String editarCadenaTexto(String s){
        int contador_mayusculas = 0;
        for (int i=0;i<s.length();i++)
            if (Character.isUpperCase(s.charAt(i))){
                if ( contador_mayusculas == 1){
                    String textoEditado = s.substring(0, i) + " " + s.substring(i);
                    return Character.toUpperCase(textoEditado.charAt(0))+textoEditado.substring(1);
                }
                contador_mayusculas++;
            }
        return s;

    }

    public void borrar(View v) {
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);

        CheckConnectivity cc = new CheckConnectivity();

        if((CheckConnectivity.isConnectedMobile(getApplicationContext())|| CheckConnectivity.isConnectedWifi(getApplicationContext()))){

        mydb.deleteCrotal(crotal.getText().toString());
        Snackbar.make(v, "Borrado realizado correctamente", Snackbar.LENGTH_LONG)
                .show();

        // Session class instance
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        String user = sessionManager.getUserDetails().toString();
        String user1 = user.replaceAll("\\{", "");
        String user2 = user1.replaceAll("\\}", "");

        //Diseccionamos la cadena
        String[] users = user2.split("=");

        String username = users[1];



            System.out.println("Si hay conexion");

            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    System.out.println("Respuesta: "+response);


                }

            };
            UpdateDataRequest UpdateDataRequest = new UpdateDataRequest(username,
                    crotal.getText().toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "borrar",
                    responseListener);

            UpdateDataRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(UpdateDataRequest).hasHadResponseDelivered();

        }
        else{

            System.out.println("No hay conexion");


            ArrayList<String> datos = new ArrayList<String>();
            datos.add(crotal.getText().toString());
            for(int i = 0; i<editText.size(); i++){
                if(editText.get(i).getText().toString()!=null && !editText.get(i).getText().toString().equals(""))
                    datos.add(editText.get(i).getText().toString());
                else
                    datos.add("vacio");
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = dateFormat.format(date);

            Explotacion vaca = new Explotacion(datos.get(0),
                    datos.get(1),
                    datos.get(2),
                    datos.get(3),
                    datos.get(4),
                    datos.get(5),
                    datos.get(6),
                    datos.get(7),
                    datos.get(8),
                    datos.get(9),
                    datos.get(10),
                    datos.get(11),
                    datos.get(12),
                    datos.get(13),
                    datos.get(14),
                    datos.get(15),
                    datos.get(16),
                    datos.get(17),
                    time,
                    "1",
                    "3");

            mydb.deleteCrotal(datos.get(0));
            mydb.insertVaca(vaca);

        }





        Intent setIntent = new Intent(this, gb.getActivityAnterior());
        startActivity(setIntent);
    }

    public void restablecer(View v) {
        Intent setIntent = new Intent(this, FichaanimalActivity.class);
        setIntent.putExtras(b);
        startActivity(setIntent);
    }

    public void darBaja(View v) {
        ArrayList<String> datos = new ArrayList<String>();
        datos.add(crotal.getText().toString());
        for(int i = 0; i<editText.size(); i++){
            if(editText.get(i).getText().toString()!=null && !editText.get(i).getText().toString().equals(""))
                datos.add(editText.get(i).getText().toString());
            else
                datos.add("vacio");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        Explotacion vaca = new Explotacion(datos.get(0),
                datos.get(1),
                datos.get(2),
                datos.get(3),
                datos.get(4),
                datos.get(5),
                datos.get(6),
                datos.get(7),
                datos.get(8),
                datos.get(9),
                datos.get(10),
                datos.get(11),
                datos.get(12),
                datos.get(13),
                datos.get(14),
                datos.get(15),
                datos.get(16),
                datos.get(17),
                time,
                "1",
                "");
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.deleteCrotal(datos.get(0));
        mydb.insertVaca(vaca);

        // Session class instance
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        String user = sessionManager.getUserDetails().toString();
        String user1 = user.replaceAll("\\{", "");
        String user2 = user1.replaceAll("\\}", "");

        //Diseccionamos la cadena
        String[] users = user2.split("=");

        String username = users[1];

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                System.out.println("Respuesta: "+response);


            }

        };
        UpdateDataRequest UpdateDataRequest = new UpdateDataRequest(username,
                (datos.get(0).equals("vacio") ? "" : datos.get(0)),
                (datos.get(1).equals("vacio") ? "" : datos.get(1)),
                (datos.get(2).equals("vacio") ? "" : datos.get(2)),
                (datos.get(3).equals("vacio") ? "" : datos.get(3)),
                (datos.get(4).equals("vacio") ? "" : datos.get(4)),
                (datos.get(5).equals("vacio") ? "" : datos.get(5)),
                (datos.get(6).equals("vacio") ? "" : datos.get(6)),
                (datos.get(7).equals("vacio") ? "" : datos.get(7)),
                (datos.get(8).equals("vacio") ? "" : datos.get(8)),
                (datos.get(9).equals("vacio") ? "" : datos.get(9)),
                (datos.get(10).equals("vacio") ? "" : datos.get(10)),
                (datos.get(11).equals("vacio") ? "" : datos.get(11)),
                (datos.get(12).equals("vacio") ? "" : datos.get(12)),
                (datos.get(13).equals("vacio") ? "" : datos.get(13)),
                (datos.get(14).equals("vacio") ? "" : datos.get(14)),
                (datos.get(15).equals("vacio") ? "" : datos.get(15)),
                (datos.get(16).equals("vacio") ? "" : datos.get(16)),
                (datos.get(17).equals("vacio") ? "" : datos.get(17)),
                time,
                "1",
                "modificar",
                responseListener);
        //RequestQueue queue = Volley.newRequestQueue(FichaanimalActivity.this);
        UpdateDataRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(UpdateDataRequest).hasHadResponseDelivered();



    }

    public void modificar(View v) {
        ArrayList<String> datos = new ArrayList<String>();


        datos.add(crotal.getText().toString());
        for(int i = 0; i<editText.size(); i++){
            if(editText.get(i).getText().toString()!=null && !editText.get(i).getText().toString().equals(""))
                datos.add(editText.get(i).getText().toString());
            else
                datos.add("vacio");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        Pattern pat = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        Matcher mat1 = pat.matcher(datos.get(6)); //Fecha nacimiento
        Matcher mat2 = pat.matcher(datos.get(9)); //Fecha llegada

        if(!mat1.matches()||datos.get(6).equals("vacio")){
            Snackbar.make(v, "Fecha nacimiento mal introducida", Snackbar.LENGTH_LONG)
                    .show();
        }
        else if(!mat2.matches()||datos.get(9).equals("vacio")){
            Snackbar.make(v, "Fecha llegada mal introducida", Snackbar.LENGTH_LONG)
                    .show();
        }
        else {
            Explotacion vaca = new Explotacion(datos.get(0),
                    datos.get(1),
                    datos.get(2),
                    datos.get(3),
                    datos.get(4),
                    datos.get(5),
                    datos.get(6),
                    datos.get(7),
                    datos.get(8),
                    datos.get(9),
                    datos.get(10),
                    datos.get(11),
                    datos.get(12),
                    datos.get(13),
                    datos.get(14),
                    datos.get(15),
                    datos.get(16),
                    datos.get(17),
                    time,
                    "0",
                    "modificar");
            ExplotacionDbHelper mydb;
            mydb = new ExplotacionDbHelper(this);
            mydb.deleteCrotal(datos.get(0));
            mydb.insertVaca(vaca);

            CheckConnectivity cc = new CheckConnectivity();

            if((CheckConnectivity.isConnectedMobile(getApplicationContext())|| CheckConnectivity.isConnectedWifi(getApplicationContext()))) {
                // Session class instance
                SessionManager sessionManager = new SessionManager(getApplicationContext());

                String user = sessionManager.getUserDetails().toString();
                String user1 = user.replaceAll("\\{", "");
                String user2 = user1.replaceAll("\\}", "");

                //Diseccionamos la cadena
                String[] users = user2.split("=");

                String username = users[1];


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        System.out.println("Respuesta: " + response);


                    }

                };


                UpdateDataRequest UpdateDataRequest = new UpdateDataRequest(username,
                        (datos.get(0).equals("vacio") ? "" : datos.get(0)),
                        (datos.get(1).equals("vacio") ? "" : datos.get(1)),
                        (datos.get(2).equals("vacio") ? "" : datos.get(2)),
                        (datos.get(3).equals("vacio") ? "" : datos.get(3)),
                        (datos.get(4).equals("vacio") ? "" : datos.get(4)),
                        (datos.get(5).equals("vacio") ? "" : datos.get(5)),
                        (datos.get(6).equals("vacio") ? "" : datos.get(6)),
                        (datos.get(7).equals("vacio") ? "" : datos.get(7)),
                        (datos.get(8).equals("vacio") ? "" : datos.get(8)),
                        (datos.get(9).equals("vacio") ? "" : datos.get(9)),
                        (datos.get(10).equals("vacio") ? "" : datos.get(10)),
                        (datos.get(11).equals("vacio") ? "" : datos.get(11)),
                        (datos.get(12).equals("vacio") ? "" : datos.get(12)),
                        (datos.get(13).equals("vacio") ? "" : datos.get(13)),
                        (datos.get(14).equals("vacio") ? "" : datos.get(14)),
                        (datos.get(15).equals("vacio") ? "" : datos.get(15)),
                        (datos.get(16).equals("vacio") ? "" : datos.get(16)),
                        (datos.get(17).equals("vacio") ? "" : datos.get(17)),
                        time,
                        "0",
                        "modificar",
                        responseListener);
                //RequestQueue queue = Volley.newRequestQueue(FichaanimalActivity.this);
                UpdateDataRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(UpdateDataRequest).hasHadResponseDelivered();
                //queue.getCache().initialize();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        //Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this, gb.getActivityAnterior());
        startActivity(setIntent);
        finish();
    }

}
