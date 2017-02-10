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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miganado.Configuracion.ActionBarActivity;
import miganado.Configuracion.CheckConnectivity;
import miganado.Configuracion.UpdateDataRequest;
import miganado.Data.Explotacion;
import miganado.Data.ExplotacionContract;
import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.Downloaddata;
import miganado.Loginyregistro.DownloaddataRequest;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.SessionManager;
import miganado.Loginyregistro.ZonaclienteActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.view.Menu;

public class AltaanimalActivity extends ActionBarActivity {

    private GlobalVariable gb = new GlobalVariable();
    private EditText etCrotalMadre, etCrotal, etExp;
    private static EditText in_date;
    private RadioGroup rg;
    private Spinner ceas;

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //long vista = getArguments().getLong("vista");
            // Do something with the date chosen by the user
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = new Date (year-1900,month,day);
            in_date.setText(String.valueOf(df.format(fecha)));

        }



    }

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
        ceas.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, valores));

    }

    public void darAlta(View v) {

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
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
        if(crotal.equals("")){
            Snackbar.make(v, "Introduzca un crotal", Snackbar.LENGTH_LONG)
                    .show();
        }


        else if(!mat3.matches()){
            Snackbar.make(v, "Fecha mal introducido", Snackbar.LENGTH_LONG)
                    .show();
        }
        else if((CheckConnectivity.isConnectedMobile(getApplicationContext())|| CheckConnectivity.isConnectedWifi(getApplicationContext()))) {
            //char a = crotal.toCharArray()[0];



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
                    "0",
                    "0");

            if(!mydb.existCrotal(crotal)){
                mydb.insertVaca(vaca);
                Snackbar.make(v, "Alta realizada correctamente", Snackbar.LENGTH_LONG)
                        .show();
            } else{
                Snackbar.make(v, "Crotal ya existente", Snackbar.LENGTH_LONG)
                        .show();
            }


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
            UpdateDataRequest UpdateDataRequest = new UpdateDataRequest(username,crotal,
                    "",
                    "",
                    crotalMadre.equals("vacio") ? "" : crotalMadre,
                    sexo,
                    "",
                    fecha,
                    "",
                    "",
                    "",
                    ceaLocalizacion,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    time,
                    "0",
                    "alta",
                    responseListener);
            RequestQueue queue = Volley.newRequestQueue(AltaanimalActivity.this);
            queue.add(UpdateDataRequest).hasHadResponseDelivered();


        } else{

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
                    "0",
                    "alta");

            if(!mydb.existCrotal(crotal)){
                mydb.insertVaca(vaca);
                Snackbar.make(v, "Alta realizada correctamente", Snackbar.LENGTH_LONG)
                        .show();
            } else{
                Snackbar.make(v, "Crotal ya existente", Snackbar.LENGTH_LONG)
                        .show();
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
        Intent setIntent = new Intent(this, ZonaclienteActivity.class);
        startActivity(setIntent);
        finish();
    }
    public void onDateSet(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        //Bundle args = new Bundle();

       // int vista = v.getBottom(); //.getId();
        //newFragment.setArguments(args);
        newFragment.show(getFragmentManager(), "datePicker");


    }

}
