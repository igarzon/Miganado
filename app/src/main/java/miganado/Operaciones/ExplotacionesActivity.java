package miganado.Operaciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Operaciones.FichaanimalActivity;
import miganado.Loginyregistro.R;

public class ExplotacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explotaciones);

        Bundle b = this.getIntent().getExtras();
        LinearLayout relative = (LinearLayout) findViewById(R.id.listado);
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        final Context context = this;

        for(String key : b.keySet()){

            TextView aux = new TextView(this);
            aux.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            aux.setText((String)b.get(key));
            aux.setTextColor(Color.BLACK);
            aux.setTextSize(20);
            aux.setPadding(1,1,1,1);
            relative.addView(aux);

            ArrayList<String> vacas = mydb.getVacasExplotacion(key);

            for(String crotal : vacas){
                final TextView aux2 = new TextView(this);
                aux2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                aux2.setText(crotal);
                aux2.setTextColor(Color.BLUE);
                aux2.setTextSize(25);
                aux2.setPadding(1,1,1,1);
                aux2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString((String) aux2.getText(), (String) aux2.getText());
                        Intent intent = new Intent(context, FichaanimalActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                relative.addView(aux2);
        }


        }
    }
}
