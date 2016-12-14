package miganado.Operaciones;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;

public class FichaanimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaanimal);

        Bundle b = this.getIntent().getExtras();
        //ImageView relative = (ImageView) findViewById(R.id.ivAnimal);

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        ArrayList<String> datos = mydb.getCrotal(b.getString((String) b.keySet().toArray()[0]));


        TextView crotal = (TextView) findViewById(R.id.Crotal);
        crotal.setText(datos.get(1));

    }
}
