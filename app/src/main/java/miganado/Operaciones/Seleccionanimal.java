package miganado.Operaciones;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class Seleccionanimal extends AppCompatActivity {

    private Spinner spinner;
    private GlobalVariable gb = new GlobalVariable();
    private ArrayList<String> gbExp = gb.getExplotaciones();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionanimal);

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        ArrayList<String> spin = new ArrayList<String>();

        for(String key : gbExp){

            //Aqui obtendriamos las explotaciones seleccionadas
            ArrayList<String> vacas = mydb.getVacasExplotacion(key);
            spin.addAll(vacas);


        }

        Collections.sort(spin, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return  o1.compareTo(o2);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        //Estos valores serian los crotales que habria que obtenerlos de la base de datos
        String[] valores = spin.toArray(new String [spin.size()]);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

    }

    public void clickAceptar(View v) {
        Bundle bun = new Bundle();
        bun.putString((String) spinner.getSelectedItem(), (String) spinner.getSelectedItem());
        gb.setActivityAnterior(Seleccionanimal.class);

        if(!bun.isEmpty()){
            Intent intent = new Intent(this, FichaanimalActivity.class);
            intent.putExtras(bun);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar un crotal", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

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
        Intent setIntent = new Intent(this, ZonaclienteActivity.class);
        startActivity(setIntent);
    }
}
