package miganado.Loginyregistro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.KeyEvent;
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

import miganado.Data.GlobalVariable;
import miganado.Operaciones.Seleccionanimal;
import miganado.Operaciones.AltaanimalActivity;
import miganado.Operaciones.BusquedasActivity;
import miganado.Operaciones.ExplotacionesActivity;
import miganado.Data.ExplotacionDbHelper;

public class ZonaclienteActivity extends AppCompatActivity {


    //Hacemos checkBox global para poder acceder desde las funciones
    private ArrayList<CheckBox> checkBox = new ArrayList<CheckBox>();
    private ExplotacionDbHelper mydb;
    private GlobalVariable gb = new GlobalVariable();
    private ArrayList<String> gbExp = gb.getExplotaciones();

    SessionManager sessionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonacliente);


        // Session class instance
        sessionManager = new SessionManager(getApplicationContext());


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

        ArrayList<String> explotaciones = mydb.getExplotaciones(); //Nombre de las explotaciones

        //Referencia al layout
        LinearLayout relative = (LinearLayout) findViewById(R.id.layout_checkbox);
        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //Tenemos todas las checkBox en el arrayList


        for (int i = 0; i < explotaciones.size(); i++) {

            CheckBox aux = new CheckBox(this);
            aux.setLayoutParams(lparams);
            aux.setText(explotaciones.get(i));
            aux.setTextColor(Color.BLACK);
            aux.setTextSize(20);
            aux.setPadding(1, 1, 1, 1);
            aux.setBackgroundColor(Color.WHITE);
            aux.setId(i); //Añadimos un id para poder referenciarle luego de  0 a n-1 explotaciones
            if(gbExp.contains(explotaciones.get(i))){
                aux.setChecked(true);
            }
            relative.addView(aux);
            checkBox.add(aux);

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
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
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

        gb.actualizarExplotaciones(aux);
        Intent intent = new Intent(this, BusquedasActivity.class);
        startActivity(intent);

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


    }
}
