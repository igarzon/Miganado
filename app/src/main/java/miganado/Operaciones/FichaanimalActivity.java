package miganado.Operaciones;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class FichaanimalActivity extends AppCompatActivity {

    private ArrayList<EditText> editText = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaanimal);

        Bundle b = this.getIntent().getExtras();
        //ImageView relative = (ImageView) findViewById(R.id.ivAnimal);

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        Cursor datos = mydb.getCrotal(b.getString((String) b.keySet().toArray()[0]));

        LinearLayout linear = (LinearLayout) findViewById(R.id.ficha);
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i = 1; i<datos.getColumnCount();i++) {
            datos.moveToFirst();
            String a = datos.getString(i);

            TextView aux1 = new TextView(this);
            aux1.setLayoutParams(lparams);
            aux1.setText(Character.toUpperCase(datos.getColumnName(i).charAt(0))+datos.getColumnName(i).substring(1));
            aux1.setTextColor(Color.BLUE);
            aux1.setTextSize(25);

            EditText aux2 = new EditText(this);
            aux2.setLayoutParams(lparams);
            aux2.setText(datos.getString(i));
            aux2.setTextColor(Color.BLACK);
            aux2.setTextSize(25);

            editText.add(aux2);

            linear.addView(aux1);
            linear.addView(aux2);

        }

    }

    public void borrar(View v) {
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.deleteCrotal(editText.get(0).getText().toString());
        Intent intent = new Intent(this, ZonaclienteActivity.class);
        startActivity(intent);
    }

}
