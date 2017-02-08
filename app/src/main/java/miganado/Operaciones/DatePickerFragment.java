package miganado.Operaciones;

/**
 * Created by User on 07/02/2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import miganado.Loginyregistro.R;

import static miganado.Loginyregistro.R.id.altadate;
import static miganado.Loginyregistro.R.id.in_date;
import static miganado.Loginyregistro.R.id.in_date2;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
    private String Infecha;

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

       // if (vista == altadate) {
       //     in_date.setText(String.valueOf(df.format(fecha)));
       // }
       // else if (vista == in_date){
            //in_date.setText(String.valueOf(df.format(fecha)));
        //}
        //else if (vista == in_date2){
            //in_date2.setText(String.valueOf(df.format(fecha)));

        //}
    }



}
