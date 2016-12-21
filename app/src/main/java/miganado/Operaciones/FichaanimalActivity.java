package miganado.Operaciones;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;

import java.util.ArrayList;

import miganado.Data.Explotacion;
import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class FichaanimalActivity extends AppCompatActivity {

    private ArrayList<EditText> editText = new ArrayList<EditText>();
    private Bundle b = new Bundle();
    private TextView crotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaanimal);

        b = this.getIntent().getExtras();
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

            linear.addView(aux1);

            if(i>1) {
                EditText aux2 = new EditText(this);
                aux2.setLayoutParams(lparams);
                aux2.setText(datos.getString(i));
                aux2.setTextColor(Color.BLACK);
                aux2.setTextSize(25);
                editText.add(aux2);
                linear.addView(aux2);
            }

            else{
                crotal = new TextView(this);
                crotal.setLayoutParams(lparams);
                crotal.setText(datos.getString(i));
                crotal.setTextColor(Color.BLACK);
                crotal.setTextSize(25);
                linear.addView(crotal);
            }




        }

    }

    public void borrar(View v) {
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.deleteCrotal(crotal.getText().toString());
        crotal.setText("");
        for(int i = 0; i<editText.size(); i++){
            editText.get(i).setText("");
        }
        Snackbar.make(v, "Borrado realizado correctamente", Snackbar.LENGTH_LONG)
                .show();
    }

    public void restablecer(View v) {
        Intent setIntent = new Intent(this, FichaanimalActivity.class);
        setIntent.putExtras(b);
        startActivity(setIntent);
    }

    public void modificar(View v) {
        ArrayList<String> datos = new ArrayList<String>();
        datos.add(crotal.getText().toString());
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
        mydb.deleteCrotal(datos.get(0));
        mydb.insertVaca(vaca);
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
        Bundle bun = b.getBundle("Explotaciones");
        for(String key : bun.keySet()){
            bun.putString(key,key);
        }
        Intent setIntent = new Intent(this, ExplotacionesActivity.class);
        setIntent.putExtras(bun);
        startActivity(setIntent);
    }

}
