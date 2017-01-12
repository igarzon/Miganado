package miganado.Operaciones;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

import static miganado.Loginyregistro.R.id.etBuscarCrotal;

public class BusquedasActivity extends AppCompatActivity {


    private String textCrotal;
    private String textCrotalMadre;
    private String fecha1;
    private String fecha2;


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

        final EditText etBuscarCrotal = (EditText) findViewById(R.id.etBuscarCrotal);
        final EditText etBuscarCrotalMadre = (EditText) findViewById(R.id.etBuscarCrotalMadre);

        final EditText in_date = (EditText) findViewById(R.id.in_date);
        final EditText in_date2 = (EditText) findViewById(R.id.in_date2);


        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        Button btnRestablecer = (Button) findViewById(R.id.btnRestablecer);


        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                textCrotal =etBuscarCrotal.getText().toString();

                textCrotalMadre = etBuscarCrotalMadre.getText().toString();


                fecha1 = in_date.getText().toString();

                fecha2 = in_date2.getText().toString();

                /*System.out.println(textCrotal);
                System.out.println(textCrotalMadre);
                System.out.println(fecha1);
                System.out.println(fecha2);*/

                ExplotacionDbHelper mydb;
                mydb = new ExplotacionDbHelper(getApplicationContext());


                ArrayList<String> datos = mydb.Busqueda(textCrotal,textCrotalMadre,fecha1,fecha2);

                if(datos.isEmpty()){

                    Snackbar.make(view, "Debes seleccionar algun dato valido", Snackbar.LENGTH_LONG)
                            .show();

                } else{

                    Bundle b = new Bundle();
                    b.putStringArrayList("resultado", datos);

                    Intent intent = new Intent(getApplicationContext(), ResultadosBusquedasActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);

                }


            }



        });



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
