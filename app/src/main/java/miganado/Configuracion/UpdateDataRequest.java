package miganado.Configuracion;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 16/01/2017.
 */

public class UpdateDataRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://Sample-env-1.jqgicx3rm6.eu-west-1.elasticbeanstalk.com/movil/updatedata.php";
    private Map<String, String> params;

    public UpdateDataRequest (String username, String crotal, String crotaloriginal ,String marcadelidia, String crotalmadre, String sexo, String raza, String fechadenacimiento,String ceanacimiento, String ceaorigen, String fechallegada, String cealocalizacion, String ultimoparto,  String dato1, String dato2, String dato3, String dato4, String dato5, String dato6, String modificado, String baja, Response.Listener<String> Listener){
        super (Request.Method.POST, UPDATE_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put ("username", username);
        params.put ("crotal", crotal);
        params.put ("crotal original", crotaloriginal);
        params.put ("marca de lidida", marcadelidia);
        params.put ("crotal madre", crotalmadre);
        params.put ("sexo", sexo);
        params.put ("raza", raza);
        params.put ("fecha de nacimiento", fechadenacimiento);
        params.put ("cea nacimiento", ceanacimiento);
        params.put ("cea origen", ceaorigen);
        params.put ("fecha llegada", fechallegada);
        params.put ("cea localización", cealocalizacion);
        params.put ("ultimo parto", ultimoparto);
        params.put ("dato1", dato1);
        params.put ("dato2", dato2);
        params.put ("dato3", dato3);
        params.put ("dato4", dato4);
        params.put ("dato5", dato5);
        params.put ("dato6", dato6);
        params.put ("baja", baja);
        params.put ("modificado", modificado);
    }
    @Override
    public Map<String,String> getParams (){
        return params;}}