package miganado.Operaciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Data.GlobalVariable;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.ZonaclienteActivity;

public class ResultadosBusquedasActivity extends AppCompatActivity {

    private ArrayList<String> b;
    private String key1;
    private GlobalVariable gb = new GlobalVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busquedas);

        LinearLayout relative = (LinearLayout) findViewById(R.id.activity_resultados_busquedas);

        final Context context = getApplicationContext();

        b=gb.getAuxBusqueda();

        for(String key : b){

            key1=key;

            TextView aux = new TextView(this);
            aux.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            aux.setText(key);
            aux.setTextColor(Color.BLACK);
            aux.setTextSize(20);
            aux.setPadding(1,1,1,1);
            aux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bun = new Bundle();
                    bun.putString(key1, key1);
                    Intent intent = new Intent(context, FichaanimalActivity.class);
                    intent.putExtras(bun);
                    gb.setActivityAnterior(ResultadosBusquedasActivity.class);
                    startActivity(intent);
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
    }
}
