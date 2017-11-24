package miganado.Loginyregistro;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 13/12/2016.
 */

public class DownloaddataRequest extends StringRequest {
    private static final String DOWNLOADDATA_REQUEST_URL = "http://www.gestiondemiganado.com/movil/downloaddata.php";
    private Map<String, String> params;

    public DownloaddataRequest(String username, Response.Listener<String> Listener){
        super (Request.Method.POST, DOWNLOADDATA_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put ("username", username);
    }
    @Override
    protected Map<String,String> getParams(){
        return params;}


}
