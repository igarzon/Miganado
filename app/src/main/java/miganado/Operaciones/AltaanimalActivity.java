package miganado.Operaciones;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miganado.Data.Explotacion;
import miganado.Data.ExplotacionContract;
import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class AltaanimalActivity extends AppCompatActivity {

    private GlobalVariable gb = new GlobalVariable();
    private EditText etCrotalMadre, etCrotal, in_date, etExp;
    private RadioGroup rg;
    private Spinner ceas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaanimal);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        Cursor dbCursor = mydb.getReadableDatabase().query(ExplotacionContract.ExplotacionEntry.TABLE_NAME, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();*/

        etCrotal = (EditText) findViewById(R.id.etAltaCrotal);
        etCrotalMadre = (EditText) findViewById(R.id.etAltaCrotalMadre);

        in_date = (EditText) findViewById(R.id.altadate);
        etExp = (EditText) findViewById(R.id.etExplotacion);

        rg = (RadioGroup) findViewById(R.id.rgAltaSexo);

        ceas = (Spinner) findViewById(R.id.spExp);
        String[] valores = gb.getExplotaciones().toArray(new String [gb.getExplotaciones().size()]);
        ceas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

    }

    public void darAlta(View v) {

        String crotal = etCrotal.getText().toString();
        String crotalMadre = etCrotalMadre.getText().toString();
        String fecha = in_date.getText().toString();
        int aux = rg.getCheckedRadioButtonId();
        RadioButton aux2 = (RadioButton) findViewById(aux);
        String sexo = ((String) aux2.getText()).toUpperCase();
        String ceaLocalizacion = (etExp.getText().toString().equals(""))?(String) ceas.getSelectedItem():etExp.getText().toString();

        /*Pattern pat1 = Pattern.compile("^ES[0-9]{12}$");
        Matcher mat1 = pat1.matcher(crotal);
        Matcher mat2 = pat1.matcher(crotalMadre);*/

        Pattern pat2 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        Matcher mat3 = pat2.matcher(fecha);

        /*if (!mat1.matches()) {
            Snackbar.make(v, "Crotal mal introducido", Snackbar.LENGTH_LONG)
                    .show();
        }
        else if(!mat2.matches()){
            Snackbar.make(v, "Crotal madre mal introducido", Snackbar.LENGTH_LONG)
                    .show();
        }*/
        if(!mat3.matches()){
            Snackbar.make(v, "Fecha mal introducido", Snackbar.LENGTH_LONG)
                    .show();
        }
        else {
            //char a = crotal.toCharArray()[0];

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String time = dateFormat.format(date);

            Explotacion vaca = new Explotacion(crotal,
                    "vacio",
                    "vacio",
                    crotalMadre,
                    sexo,
                    "vacio",
                    fecha,
                    "vacio",
                    "vacio",
                    "vacio",
                    ceaLocalizacion,
                    "vacio",
                    "vacio",
                    "vacio",
                    "vacio",
                    "vacio",
                    "vacio",
                    "vacio",
                    time,
                    "0");
            ExplotacionDbHelper mydb;
            mydb = new ExplotacionDbHelper(this);
            mydb.insertVaca(vaca);

            Snackbar.make(v, "Alta realizada correctamente", Snackbar.LENGTH_LONG)
                    .show();
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
        Intent setIntent = new Intent(this, ZonaclienteActivity.class);
        startActivity(setIntent);
        finish();
    }

}
