package miganado.Loginyregistro;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 07/12/2016.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.gestiondemiganado.com/movil/registro.php";
    private Map<String, String> params;

    public RegisterRequest (String username, String password, String password2 ,String nombre, String tipoacceso, String dato1, String dato2, String dato3, String dato4, String dato5, String dato6, Response.Listener<String> Listener){
        super (Request.Method.POST, REGISTER_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put ("username", username);
        params.put ("password", password);
        params.put ("password2", password2);
        params.put ("nombre", nombre);
        params.put ("tipoacceso", tipoacceso);
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