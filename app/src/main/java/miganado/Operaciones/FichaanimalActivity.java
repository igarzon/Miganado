package miganado.Operaciones;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import miganado.Loginyregistro.R;

public class FichaanimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaanimal);

        Bundle b = this.getIntent().getExtras();
        //ImageView relative = (ImageView) findViewById(R.id.ivAnimal);

        TextView crotal = (TextView) findViewById(R.id.Crotal);
        crotal.setText(b.getString((String) b.keySet().toArray()[0]));

    }
}
