package miganado.Operaciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.ZonaclienteActivity;
import miganado.Operaciones.FichaanimalActivity;
import miganado.Loginyregistro.R;

public class ExplotacionesActivity extends AppCompatActivity {

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explotaciones);

        /*final Bundle*/ b = this.getIntent().getExtras();
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
                        Bundle bun = new Bundle();
                        bun.putString((String) aux2.getText(), (String) aux2.getText());
                        bun.putBundle("Explotaciones",b);
                        Intent intent = new Intent(context, FichaanimalActivity.class);
                        intent.putExtras(bun);
                        startActivity(intent);
                    }
                });
                relative.addView(aux2);
        }


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
        Intent setIntent = new Intent(this, ZonaclienteActivity.class);
        startActivity(setIntent);
    }
}
