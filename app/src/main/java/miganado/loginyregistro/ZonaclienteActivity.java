package miganado.Loginyregistro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import miganado.Operaciones.Seleccionanimal;
import miganado.Operaciones.AltaanimalActivity;
import miganado.Operaciones.BusquedasActivity;
import miganado.Operaciones.ExplotacionesActivity;
import miganado.Data.ExplotacionDbHelper;

public class ZonaclienteActivity extends AppCompatActivity {


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonacliente);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView Username = (TextView) findViewById(R.id.tvUsername);
        final TextView Welcomessage = (TextView) findViewById(R.id.tvMensajebienvenida);

        Intent intent =getIntent();
        String username = intent.getStringExtra("username");
        String message ="Bienvenido a tu área de cliente";
        Welcomessage.setText(message);
        Username.setText(username);
    }
    NO SE SI SIRVE DE ALGO*/

    //Hacemos checkBox global para poder acceder desde las funciones
    private ArrayList<CheckBox> checkBox = new ArrayList<CheckBox>();
    private ExplotacionDbHelper mydb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonacliente);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        TextView username = (TextView) findViewById(R.id.tvUser);
        username.setText(name);
        username.setTextColor(Color.GREEN);

        mydb = new ExplotacionDbHelper(this);

        //Bundle b = this.getIntent().getExtras(); //Obtenemos las explotaciones del login
        //Ver si es mejor cogerlas de la base de datos
        ArrayList<String> explotaciones = mydb.getExplotaciones(); //Nombre de las explotaciones
        //Pruebas, hay que borrarlo adelate
        //explotaciones.add("explotacion 1");
        //explotaciones.add("explotacion 2");
        //explotaciones.add("explotacion 3");
        //explotaciones.add("explotacion 4");
        /*

        /*
        for(String key : b.keySet()){
            //hay que ver como devuelve el nombre de la explotacion y ver si es mejor añadir key
            explotaciones.add((String)b.get(key));
        }
        */

        //Referencia al layout
        LinearLayout relative = (LinearLayout) findViewById(R.id.layout_checkbox);
        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //Tenemos todas las checkBox en el arrayList


        for(int i=0;i<explotaciones.size();i++){

            CheckBox aux = new CheckBox(this);
            aux.setLayoutParams(lparams);
            aux.setText(explotaciones.get(i));
            aux.setTextColor(Color.BLACK);
            aux.setTextSize(20);
            aux.setPadding(1,1,1,1);
            aux.setBackgroundColor(Color.WHITE);
            aux.setId(i); //Añadimos un id para poder referenciarle luego de  0 a n-1 explotaciones
            relative.addView(aux);
            checkBox.add(aux);

        }

    }

    //Funcion que se realiza al pulsar el boton explotaciones
    public void clickExplotaciones(View v) {
        Bundle b = new Bundle();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                b.putString((String) checkBox.get(i).getText(), (String) checkBox.get(i).getText());
                System.out.println((String) checkBox.get(i).getText());
            }
        }
        if(!b.isEmpty()){
            Intent intent = new Intent(this, ExplotacionesActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void clickFichaAnimal(View v) {
        Bundle b = new Bundle();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                b.putString((String) checkBox.get(i).getText(), (String) checkBox.get(i).getText());
                System.out.println((String) checkBox.get(i).getText());
            }
        }
        if(!b.isEmpty()){
            Intent intent = new Intent(this, Seleccionanimal.class);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void clickBusqueda(View v) {
        Bundle b = new Bundle();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                b.putString((String) checkBox.get(i).getText(), (String) checkBox.get(i).getText());
                System.out.println((String) checkBox.get(i).getText());
            }
        }
        if(!b.isEmpty()){
            Intent intent = new Intent(this, BusquedasActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void clickAlta(View v) {
        Bundle b = new Bundle();
        for(int i=0; i<checkBox.size(); i++){
            if(checkBox.get(i).isChecked()) {
                b.putString((String) checkBox.get(i).getText(), (String) checkBox.get(i).getText());
                System.out.println((String) checkBox.get(i).getText());
            }
        }
        if(b.size()>1){
            Snackbar.make(v, "Debes seleccionar SOLO UNA explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
        else if(!b.isEmpty()){
            Intent intent = new Intent(this, AltaanimalActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar alguna explotación", Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}
