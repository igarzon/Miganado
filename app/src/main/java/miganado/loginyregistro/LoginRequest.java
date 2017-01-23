package miganado.Loginyregistro;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 05/12/2016.
 */

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://Sample-env.ecc6cmb68n.eu-west-1.elasticbeanstalk.com/movil/login.php";
    private Map<String, String> params;

    public LoginRequest (String username, String password, Response.Listener<String> Listener){
        super (Request.Method.POST, LOGIN_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put ("username", username);
        params.put ("password", password);
    }
@Override
    public Map<String,String> getParams (){
        return params;}
    }
