package miganado.Operaciones;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import android.app.DatePickerDialog.OnDateSetListener;
import miganado.Loginyregistro.R;



public class Busquedas_Fragment extends Fragment {

    private EditText textCrotal;

    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView dateSetText;
    private DatePicker datePicker;

    public Busquedas_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_busquedas, container, false);

        textCrotal = (EditText)view.findViewById(R.id.etBuscarCrotal);


        Button btnDatePicker = (Button) view.findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf = new DatePickerFragment();
                //dpf.setCallBack(onDate);
                //dpf.show(getFragmentManager(), "DatePickerFragment");
                //DialogFragment picker = new DatePickerFragment();
                //picker.show(getFragmentManager(), "datePicker");
            }
        });

        return view;
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            TextView tvDate = (TextView) getActivity().findViewById(R.id.in_date);
            tvDate.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };


}

