package miganado.Operaciones;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miganado.Configuracion.ActionBarActivity;
import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static miganado.Loginyregistro.R.id.etBuscarCrotal;

public class BusquedasActivity extends ActionBarActivity {



    private String textCrotal;
    private String textCrotalMadre;
    private String fecha1;
    private String fecha2;
    private String baja;
    private String sexo;

    private GlobalVariable gb = new GlobalVariable();

    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView dateSetText;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquedas);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final EditText etBuscarCrotal = (EditText) findViewById(R.id.etBuscarCrotal);
        final EditText etBuscarCrotalMadre = (EditText) findViewById(R.id.etBuscarCrotalMadre);

        final EditText in_date = (EditText) findViewById(R.id.in_date);
        final EditText in_date2 = (EditText) findViewById(R.id.in_date2);

        final CheckBox historico = (CheckBox) findViewById(R.id.cbHistorico);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rgSexo);

        //Desseleccionamos los dos radioButtons
        rg.clearCheck();

        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        Button btnRestablecer = (Button) findViewById(R.id.btnRestablecer);

        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                textCrotal =etBuscarCrotal.getText().toString();
                textCrotalMadre = etBuscarCrotalMadre.getText().toString();
                fecha1 = in_date.getText().toString();
                fecha2 = in_date2.getText().toString();
                baja = (historico.isChecked())?"1":"0";
                int aux = rg.getCheckedRadioButtonId();
                RadioButton aux2 = (RadioButton) findViewById(aux);
                //Si no está seleccionado, buscará en ambos sexos
                if (aux != -1)
                    sexo = ((String) aux2.getText()).toUpperCase();
                else sexo = "AMBOS";

                if(fecha1.equals("")){
                    fecha1="0000-00-00";
                }
                if(fecha2.equals("")){
                    fecha2="9999-00-00";
                }

                Pattern pat = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
                Matcher mat1 = pat.matcher(fecha1);
                Matcher mat2 = pat.matcher(fecha2);

                if(!mat1.matches()){
                    Snackbar.make(view , "Fecha1 mal introducido", Snackbar.LENGTH_LONG)
                            .show();
                }
                else if(!mat2.matches()){
                    Snackbar.make(view, "Fecha2 mal introducido", Snackbar.LENGTH_LONG)
                            .show();
                }
                else {

                /*System.out.println(textCrotal);
                System.out.println(textCrotalMadre);
                System.out.println(fecha1);
                System.out.println(fecha2);*/

                    ExplotacionDbHelper mydb;
                    mydb = new ExplotacionDbHelper(getApplicationContext());


                    ArrayList<String> datos = mydb.Busqueda(textCrotal, textCrotalMadre, fecha1, fecha2, baja, sexo);

                    if (datos.isEmpty()) {

                        Snackbar.make(view, "No hay resultados", Snackbar.LENGTH_LONG)
                                .show();

                    } else {

                        gb.setAuxBusqueda(datos);
                        Intent intent = new Intent(getApplicationContext(), ResultadosBusquedasActivity.class);
                        startActivity(intent);

                    }
                }

            }



        });

        btnRestablecer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), BusquedasActivity.class);
                startActivity(intent);

            }



        });


    }

  /*  public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
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
        Intent setIntent = new Intent(this, ZonaclienteActivity.class);
        startActivity(setIntent);
        finish();
    }
    public void onDateSet(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        int vista = v.getBottom(); //.getId();
        args.putLong("vista",vista);
        newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "datePicker");


    }

}
