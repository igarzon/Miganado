package miganado.Loginyregistro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ZonaclienteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonacliente);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView Username = (TextView) findViewById(R.id.tvUsername);
        final TextView Welcomessage = (TextView) findViewById(R.id.tvMensajebienvenida);

        Intent intent =getIntent();
        String username = intent.getStringExtra("username");
        String message ="Bienvenido a tu área de cliente";
        Welcomessage.setText(message);
        Username.setText(username);
    }
}
