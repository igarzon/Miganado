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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.Explotacion;
import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class AltaanimalActivity extends AppCompatActivity {

    private ArrayList<EditText> editText = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaanimal);

        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        Cursor dbCursor = mydb.getReadableDatabase().query("Miganado", null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();

        LinearLayout linear = (LinearLayout) findViewById(R.id.alta);
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Bundle b = this.getIntent().getExtras(); //b.getString((String) b.keySet().toArray()[0]) Explotacion
        //Button btn = (Button) findViewById(R.id.btnAlta);
        //btn.

        for(int i = 1; i<columnNames.length; i++){

            TextView aux1 = new TextView(this);
            aux1.setLayoutParams(lparams);
            aux1.setText(columnNames[i]);
            //aux1.s
            aux1.setTextColor(Color.BLACK);
            aux1.setTextSize(25);

            EditText aux2 = new EditText(this);
            if(!columnNames[i].equals("ceaLocalizacion")) {
                aux2.setLayoutParams(lparams);
                aux2.setHint(columnNames[i]);
                aux2.setTextSize(25);
            }
            else{
                aux2.setHint(columnNames[i]);
                aux2.setLayoutParams(lparams);
                aux2.setText(b.getString((String) b.keySet().toArray()[0]));
                aux2.setTextSize(25);
            }

            editText.add(aux2);

            linear.addView(aux1);
            linear.addView(aux2);
        }
    }

    public void darAlta(View v) {
        ArrayList<String> datos = new ArrayList<String>();
        for(int i = 0; i<editText.size(); i++){
            if(editText.get(i).getText().toString()!=null && !editText.get(i).getText().toString().equals(""))
                datos.add(editText.get(i).getText().toString());
            else
                datos.add("---");
        }
        //cur.
        Explotacion vaca = new Explotacion(datos.get(0),
                datos.get(1),
                datos.get(2),
                datos.get(3),
                datos.get(4),
                datos.get(5),
                datos.get(6),
                datos.get(7),
                datos.get(8),
                datos.get(9),
                datos.get(10),
                datos.get(11),
                datos.get(12),
                datos.get(13),
                datos.get(14),
                datos.get(15),
                datos.get(16));
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.insertVaca(vaca);
        for(int i = 0; i<editText.size(); i++){
            editText.get(i).setText("");
        }
        Snackbar.make(v, "Alta realizada correctamente", Snackbar.LENGTH_LONG)
                .show();
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
    }

}
