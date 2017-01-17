package miganado.Configuracion;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 16/01/2017.
 */

public class UpdatePreferencesRequest extends StringRequest {
    private static final String PREFERENCES_REQUEST_URL = "http://Sample-env-1.jqgicx3rm6.eu-west-1.elasticbeanstalk.com/movil/preferencias.php";
    private Map<String, String> params;

    public UpdatePreferencesRequest (String username, String dato1, String dato2, String dato3, String dato4, String dato5, String dato6, Response.Listener<String> Listener){
        super (Request.Method.POST, PREFERENCES_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put ("username", username);
        params.put ("dato1", dato1);
        params.put ("dato2", dato2);
        params.put ("dato3", dato3);
        params.put ("dato4", dato4);
        params.put ("dato5", dato5);
        params.put ("dato6", dato6);
    }
    @Override
    public Map<String,String> getParams (){
        return params;}
}
