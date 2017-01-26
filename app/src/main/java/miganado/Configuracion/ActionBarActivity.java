package miganado.Configuracion;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import miganado.Loginyregistro.R;
import miganado.Operaciones.AltaanimalActivity;
import miganado.Operaciones.BusquedasActivity;
import miganado.Loginyregistro.SessionManager;


public class ActionBarActivity extends AppCompatActivity{
    protected static final String MI_GANADO_PRIMARY_COLOR = "#70ac47";
    protected static final String MI_GANADO_PRIMARY_COLOR_DARK = "#358200";
    private String usuario;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ActionBar ac = getSupportActionBar();
        ac.setTitle(this.getUsuario());
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

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getUsuario(){
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        String user = sessionManager.getUserDetails().toString();
        return user.replaceAll("\\{", "").substring(5,user.replaceAll("\\{", "").length()-1);
    }
}

