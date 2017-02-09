package miganado.Operaciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Configuracion.ActionBarActivity;
import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class ResultadosBusquedasActivity extends ActionBarActivity {

    private ArrayList<String> b;
    private String key1;
    private GlobalVariable gb = new GlobalVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busquedas);

        LinearLayout relative = (LinearLayout) findViewById(R.id.listado_busquedas);

        final Context context = getApplicationContext();

        b=gb.getAuxBusqueda();

        TextView coincidencias = new TextView(this);
        coincidencias.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        String nombreExp = Integer.toString(b.size())+" coincidencias.";
        coincidencias.setText(nombreExp);
        coincidencias.setTextColor(Color.WHITE);
        coincidencias.setTextSize(20);
        coincidencias.setPadding(1,1,1,1);
        relative.addView(coincidencias);

        for(int i = 0; i<b.size(); i++){


            final TextView aux = new TextView(this);
            aux.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            aux.setText(b.get(i));
            aux.setTextColor(Color.parseColor(MI_GANADO_PRIMARY_COLOR));
            aux.setTextSize(23);
            aux.setPadding(1,1,1,1);
            aux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bun = new Bundle();
                    bun.putString((String) aux.getText(), (String) aux.getText());
                    Intent intent = new Intent(context, FichaanimalActivity.class);
                    intent.putExtras(bun);
                    gb.setActivityAnterior(ResultadosBusquedasActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            relative.addView(aux);




        }
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
        Intent setIntent = new Intent(this, BusquedasActivity.class);
        startActivity(setIntent);
        finish();
    }
}
