package miganado.Loginyregistro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import miganado.Configuracion.ActionBarActivity;
import miganado.Configuracion.CheckConnectivity;
import miganado.Configuracion.PreferenciasActivity;
import miganado.Configuracion.UpdateDataRequest;
import miganado.Configuracion.UpdatePreferencesRequest;
import miganado.Data.Explotacion;
import miganado.Data.GlobalVariable;
import miganado.Operaciones.FichaanimalActivity;
import miganado.Operaciones.Seleccionanimal;
import miganado.Operaciones.AltaanimalActivity;
import miganado.Operaciones.BusquedasActivity;
import miganado.Operaciones.ExplotacionesActivity;
import miganado.Data.ExplotacionDbHelper;

public class ZonaclienteActivity extends ActionBarActivity {


    //Hacemos checkBox global para poder acceder desde las funciones
    private ArrayList<CheckBox> checkBox = new ArrayList<CheckBox>();
    private ExplotacionDbHelper mydb;
    private GlobalVariable gb = new GlobalVariable();
    private ArrayList<String> gbExp = gb.getExplotaciones();

    SessionManager sessionManager;
    RequestQueue queue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonacliente);


        // Session class instance
        sessionManager = new SessionManager(getApplicationContext());


        queue = Volley.newRequestQueue(ZonaclienteActivity.this);


        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */

        sessionManager.checkLogin();

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("username");
        //TextView username = (TextView) findViewById(R.id.tvUser);
        //username.setText(name);
        //username.setTextColor(Color.GREEN);

        gb.setActivityAnterior(ZonaclienteActivity.class);
        mydb = new ExplotacionDbHelper(this);

        //Bundle b = this.getIntent().getExtras(); //Obtenemos las explotaciones del login
        //Ver si es mejor cogerlas de la base de datos

        if(sessionManager.isLoggedIn()&& mydb.existExplotaciones()){

            ArrayList<String> explotaciones = mydb.getExplotaciones(); //Nombre de las explotaciones

            //Referencia al layout
            LinearLayout relative = (LinearLayout) findViewById(R.id.layout_checkbox);
            LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            //Tenemos todas las checkBox en el arrayList


            for (int i = 0; i < explotaciones.size(); i++) {

                CheckBox aux = new CheckBox(this);
                aux.setLayoutParams(lparams);
                aux.setText(explotaciones.get(i));
                aux.setTextColor(Color.WHITE);
                aux.setTextSize(20);
                aux.setPadding(1, 1, 1, 1);
                aux.setBackgroundColor(Color.TRANSPARENT);
                aux.setId(i); //Añadimos un id para poder referenciarle luego de  0 a n-1 explotaciones
                if(gbExp.contains(explotaciones.get(i))){
                    aux.setChecked(true);
                }
                relative.addView(aux);
                checkBox.add(aux);

            }


            if((CheckConnectivity.isConnectedMobile(getApplicationContext())|| CheckConnectivity.isConnectedWifi(getApplicationContext()))){


                ExplotacionDbHelper mydb;
                mydb = new ExplotacionDbHelper(getApplicationContext());

                ArrayList<String> array_list_mod = new ArrayList<String>();
                array_list_mod=mydb.getVacasModificado();

                if (!array_list_mod.isEmpty())actualizacion(array_list_mod);

                actualizacionPref();
            }
        }

    }

    //Funcion que se realiza al pulsar el boton explotaciones
    public void clickExplotaciones(View v) {

        ArrayList<String> aux = new ArrayList<String>();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                aux.add((String) checkBox.get(i).getText());
            }
        }

        if(!aux.isEmpty()){
            gb.actualizarExplotaciones(aux);
            Intent intent = new Intent(this, ExplotacionesActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    //Funcion que se realiza al pulsar el boton explotaciones
    public void ajustes(View v) {

            Intent intent = new Intent(this, PreferenciasActivity.class);
            startActivity(intent);


    }

    public void clickFichaAnimal(View v) {


        ArrayList<String> aux = new ArrayList<String>();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                aux.add((String) checkBox.get(i).getText());
            }
        }

        if(!aux.isEmpty()){

            gb.actualizarExplotaciones(aux);
            Intent intent = new Intent(this, Seleccionanimal.class);
            startActivity(intent);
            finish();
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void clickBusqueda(View v) {

        ArrayList<String> aux = new ArrayList<String>();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                aux.add((String) checkBox.get(i).getText());
            }
        }

        if(!aux.isEmpty()){

            gb.actualizarExplotaciones(aux);
            Intent intent = new Intent(this, BusquedasActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }


    }

    public void clickAlta(View v) {

        ArrayList<String> aux = new ArrayList<String>();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                aux.add((String) checkBox.get(i).getText());
            }
        }

        gb.actualizarExplotaciones(aux);
        Intent intent = new Intent(this, AltaanimalActivity.class);
        startActivity(intent);
        finish();

    }

   /*public void clickGuardarExplotaciones(View v) {
        ArrayList<String> aux = new ArrayList<String>();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                aux.add((String) checkBox.get(i).getText());
            }
        }
        if(!aux.isEmpty()){
            gb.actualizarExplotaciones(aux);
            Snackbar.make(v, "Explotaciones seleccionadas guardadas", Snackbar.LENGTH_LONG)
                    .show();
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }*/

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

        this.finishAffinity();

    }

    public void actualizacionPref(){

        // Session class instance
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        String user = sessionManager.getUserDetails().toString();
        String user1 = user.replaceAll("\\{", "");
        String user2 = user1.replaceAll("\\}", "");

        //Diseccionamos la cadena
        String[] users = user2.split("=");

        String username = users[1];

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ZonaclienteActivity.this);
        String [] s = new String[6];
        s[0] = pref.getString("dato1", "");
        s[1] = pref.getString("dato2", "");
        s[2] = pref.getString("dato3", "");
        s[3] = pref.getString("dato4", "");
        s[4] = pref.getString("dato5", "");
        s[5] = pref.getString("dato6", "");


        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                System.out.println("Respuesta preferencias: "+response);

            }

        };
        UpdatePreferencesRequest updatePreferencesRequest = new UpdatePreferencesRequest(username,
                s[0],
                s[1],
                s[2],
                s[3],
                s[4],
                s[5],
                responseListener);

        queue.add(updatePreferencesRequest).hasHadResponseDelivered();

    }


    public void actualizacion(ArrayList<String> array_list_mod){

            ExplotacionDbHelper mydb;
            mydb = new ExplotacionDbHelper(getApplicationContext());

            // Session class instance
            SessionManager sessionManager = new SessionManager(getApplicationContext());

            String user = sessionManager.getUserDetails().toString();
            String user1 = user.replaceAll("\\{", "");
            String user2 = user1.replaceAll("\\}", "");

            //Diseccionamos la cadena
            String[] users = user2.split("=");

            String username = users[1];

            //MODIFICAR
            for (int i = 0 ; i<array_list_mod.size(); i++) {
                Cursor datos = mydb.getCrotal(array_list_mod.get(i).toString());
                datos.moveToFirst();

                System.out.println("Datos: "+ datos.getString(1));

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        System.out.println("Respuesta: "+response);

                    }

                };
                UpdateDataRequest UpdateDataRequest = new UpdateDataRequest(username,
                        (datos.getString(1).equals("vacio") ? "" : datos.getString(1)),
                        (datos.getString(2).equals("vacio") ? "" : datos.getString(2)),
                        (datos.getString(3).equals("vacio") ? "" : datos.getString(3)),
                        (datos.getString(4).equals("vacio") ? "" : datos.getString(4)),
                        (datos.getString(5).equals("vacio") ? "" : datos.getString(5)),
                        (datos.getString(6).equals("vacio") ? "" : datos.getString(6)),
                        (datos.getString(7).equals("vacio") ? "" : datos.getString(7)),
                        (datos.getString(8).equals("vacio") ? "" : datos.getString(8)),
                        (datos.getString(9).equals("vacio") ? "" : datos.getString(9)),
                        (datos.getString(10).equals("vacio") ? "" : datos.getString(10)),
                        (datos.getString(11).equals("vacio") ? "" : datos.getString(11)),
                        (datos.getString(12).equals("vacio") ? "" : datos.getString(12)),
                        (datos.getString(13).equals("vacio") ? "" : datos.getString(13)),
                        (datos.getString(14).equals("vacio") ? "" : datos.getString(14)),
                        (datos.getString(15).equals("vacio") ? "" : datos.getString(15)),
                        (datos.getString(16).equals("vacio") ? "" : datos.getString(16)),
                        (datos.getString(17).equals("vacio") ? "" : datos.getString(17)),
                        (datos.getString(18).equals("vacio") ? "" : datos.getString(18)),
                        (datos.getString(19).equals("vacio") ? "" : datos.getString(19)),
                        (datos.getString(20).equals("vacio") ? "" : datos.getString(20)),
                        datos.getString(21),
                        responseListener);

                queue.add(UpdateDataRequest).hasHadResponseDelivered();


                Explotacion vaca = new Explotacion(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7),
                        datos.getString(8),
                        datos.getString(9),
                        datos.getString(10),
                        datos.getString(11),
                        datos.getString(12),
                        datos.getString(13),
                        datos.getString(14),
                        datos.getString(15),
                        datos.getString(16),
                        datos.getString(17),
                        datos.getString(18),
                        datos.getString(19),
                        datos.getString(20),
                        "0");

                mydb.deleteCrotal(datos.getString(1));
                if(!(datos.getString(21).equals("borrar")))mydb.insertVaca(vaca);

            }

    }

}
