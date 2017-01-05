package miganado.Operaciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;

public class ResultadosBusquedasActivity extends AppCompatActivity {

    private ArrayList<String> b;
    private String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busquedas);

        /*final Bundle*/ b = this.getIntent().getExtras().getStringArrayList("resultado");
        LinearLayout relative = (LinearLayout) findViewById(R.id.activity_resultados_busquedas);

        final Context context = getApplicationContext();

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
                    bun.putBundle("Explotaciones",null);//Arreglar vuelta back
                    Intent intent = new Intent(context, FichaanimalActivity.class);
                    intent.putExtras(bun);
                    startActivity(intent);
                }
            });

            relative.addView(aux);




        }
    }
}
