package miganado.Operaciones;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import miganado.Loginyregistro.R;

public class ExplotacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explotaciones);

        Bundle b = this.getIntent().getExtras();
        LinearLayout relative = (LinearLayout) findViewById(R.id.activity_explotaciones);

        for(String key : b.keySet()){

            TextView aux = new TextView(this);
            aux.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            aux.setText((String)b.get(key));
            aux.setTextColor(Color.BLACK);
            aux.setPadding(1,1,1,1);
            aux.setBackgroundColor(Color.WHITE);
            relative.addView(aux);

        }
    }
}
