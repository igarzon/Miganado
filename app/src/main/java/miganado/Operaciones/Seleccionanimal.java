package miganado.Operaciones;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import miganado.Loginyregistro.R;

public class Seleccionanimal extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionanimal);

        Bundle b = this.getIntent().getExtras();

        for(String key : b.keySet()){

            //Aqui obtendriamos las explotaciones seleccionadas

        }

        spinner = (Spinner) findViewById(R.id.spinner);
        //Estos valores serian los crotales que habria que obtenerlos de la base de datos
        String[] valores = {"uno","dos","tres","cuatro","cinco","seis", "siete", "ocho"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

    }

    public void clickAceptar(View v) {
        Bundle b = new Bundle();
        b.putString((String) spinner.getSelectedItem(), (String) spinner.getSelectedItem());

        if(!b.isEmpty()){
            Intent intent = new Intent(this, FichaanimalActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
        else {
            Snackbar.make(v, "Debes seleccionar un crotal", Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}
