package miganado.Configuracion;

import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import miganado.Configuracion.ActionBarActivity;

import miganado.Data.ExplotacionDbHelper;
import miganado.Loginyregistro.R;
import miganado.Loginyregistro.SessionManager;
import miganado.Operaciones.AltaanimalActivity;
import miganado.Operaciones.BusquedasActivity;

public class PreferenciasActivity extends PreferenceActivity {

    SessionManager sessionManager;
    private ExplotacionDbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);

        Preference button = getPreferenceManager().findPreference("exitlink");
        Preference button2 = getPreferenceManager().findPreference("updatelink");

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

        if (button2 != null) {
            button2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {



                    return true;
                }
            });
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nuevo:
                Intent alta = new Intent(getApplicationContext(), AltaanimalActivity.class);
                startActivity(alta);
                return true;
            case R.id.action_buscar:
                Intent busqueda = new Intent(getApplicationContext(), BusquedasActivity.class);
                startActivity(busqueda);
                return true;
            case R.id.action_settings:
                Intent settings = new Intent(getApplicationContext(), PreferenciasActivity.class);
                startActivity(settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

