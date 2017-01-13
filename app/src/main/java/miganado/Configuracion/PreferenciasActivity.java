package miganado.Configuracion;

import android.preference.PreferenceActivity;
import android.os.Bundle;

import miganado.Loginyregistro.R;

public class PreferenciasActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);

    }
}
