package miganado.Loginyregistro;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;

import miganado.Data.Explotacion;
import miganado.Data.ExplotacionDbHelper;

import static java.security.AccessController.getContext;

public class Downloaddata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaddata);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Button btDownload = (android.widget.Button) findViewById(R.id.btnDownloaddata);





        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            //
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                //System.out.println(jsonArray.get(i));

                                //Almacenamos linea a linea cada una de las filas de la tabla
                                String Linea=jsonArray.get(i).toString();

                                //Limpiamos de caracteres las líneas
                                String newLine1 = Linea.replaceAll("\"\"", "vacio");
                                String newLine2 = newLine1.replaceAll("\"", "");
                                String newLine3 = newLine2.replaceAll("\\{", "");
                                String newLine4 = newLine3.replaceAll("\\}", "");
                                String newLine = newLine4.replaceAll(",", ":");

                                //Estructura para diseccionar la cadena
                                String Datos[] = new String[jsonArray.length()];

                                //Diseccionamos la cadena
                                String[] parts = newLine.split(":");

                                //Actualizamos los String de cada uno de los elementos de la tabla
                                 String crotal = parts[3];
                                 String crotalOriginal = parts[7];
                                 String marcaLidia= parts[11];
                                 String crotalMadre = parts[15];
                                 String sexo= parts[19];
                                 String raza= parts[23];
                                 String fechaNacimiento=parts[27];;
                                 String ceaNacimiento=parts[31];;
                                 String fechaLlegada=parts[35];;
                                 String ceaLocalizacion=parts[39];;
                                 String ultimoParto=parts[43];;
                                 String dato1=parts[47];;
                                 String dato2=parts[51];;
                                 String dato3=parts[55];;
                                 String dato4=parts[59];;
                                 String dato5=parts[63];;
                                 String dato6=parts[67];;

                              /*  for (int j = 3; j < parts.length; j+=4) {
                                    System.out.println(parts[j]);
                                }*/

                                Datos[i]=Linea;


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                DownloaddataRequest DownloaddataRequest = new DownloaddataRequest(username,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(Downloaddata.this);
                queue.add(DownloaddataRequest);
            }
        });
    }
}
