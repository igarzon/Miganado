package miganado.Operaciones;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;

public class ExplotacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explotaciones);

        Bundle b = this.getIntent().getExtras();
        LinearLayout relative = (LinearLayout) findViewById(R.id.activity_explotaciones);
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);

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
                TextView aux2 = new TextView(this);
                aux2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                aux2.setText(crotal);
                aux2.setTextColor(Color.BLUE);
                aux2.setTextSize(25);
                aux2.setPadding(1,1,1,1);
                relative.addView(aux2);
        }


        }
    }
}
