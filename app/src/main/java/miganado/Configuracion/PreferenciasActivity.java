package miganado.Configuracion;

import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.view.View;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.Downloaddata;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.SessionManager;
import miganado.Loginyregistro.ZonaclienteActivity;

public class PreferenciasActivity extends PreferenceActivity {


    SessionManager sessionManager;
    private ExplotacionDbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);

        Preference button = (Preference)getPreferenceManager().findPreference("exitlink");
        if (button != null) {
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    //code for what you want it to do
                    // Session class instance
                    sessionManager = new SessionManager(getApplicationContext());

                    mydb = new ExplotacionDbHelper(getApplicationContext());

                    mydb.DeleteDatabase();

                    sessionManager.logoutUser();

                    return true;
                }
            });
        }


    }


}
