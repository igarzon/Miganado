package miganado.Loginyregistro;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import java.util.ArrayList;
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

        final Button btAcceso = (android.widget.Button) findViewById(R.id.btnAcceso);

        final boolean[] begin = new boolean[1];





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
                                System.out.println(jsonArray.get(i));

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


                                ArrayList<String> datos = new ArrayList<String>();

                                datos.add(parts[3]);
                                datos.add(parts[7]);
                                datos.add(parts[11]);
                                datos.add(parts[15]);
                                datos.add(parts[19]);
                                datos.add(parts[23]);
                                datos.add(parts[27]);
                                datos.add(parts[31]);
                                datos.add(parts[35]);
                                datos.add(parts[39]);
                                datos.add(parts[43]);
                                datos.add(parts[47]);
                                datos.add(parts[51]);
                                datos.add(parts[55]);
                                datos.add(parts[59]);
                                datos.add(parts[63]);
                                datos.add(parts[67]);
                                datos.add(parts[71]);
                                datos.add(parts[75]);

                                darAlta(datos);

                                begin[0] =true;

                                //Actualizamos los String de cada uno de los elementos de la tabla
                                 String crotal = parts[3];
                                 String crotalOriginal = parts[7];
                                 String marcaLidia= parts[11];
                                 String crotalMadre = parts[15];
                                 String sexo= parts[19];
                                 String raza= parts[23];
                                 String fechaNacimiento=parts[27];
                                 String ceaNacimiento=parts[31];
                                 String fechaLlegada=parts[35];
                                 String ceaLocalizacion=parts[39];
                                 String ultimoParto=parts[43];
                                 String dato1=parts[47];
                                 String dato2=parts[51];
                                 String dato3=parts[55];
                                 String dato4=parts[59];
                                 String dato5=parts[63];
                                 String dato6=parts[67];
                                String fechaModificacion=parts[71];
                                String baja=parts[74];


                                for (int j = 3; j < parts.length; j+=4) {
                                    System.out.println(parts[j]);
                                }

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

        btAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(begin[0]==true) {
                    Intent intent = new Intent(Downloaddata.this, ZonaclienteActivity.class);
                    Downloaddata.this.startActivity(intent);
                }

            }});



    }


    public void darAlta(ArrayList<String> datos) {

        //cur.
        Explotacion vaca = new Explotacion(datos.get(0),
                datos.get(1),
                datos.get(2),
                datos.get(3),
                datos.get(4),
                datos.get(5),
                datos.get(6),
                datos.get(7),
                datos.get(8),
                datos.get(9),
                datos.get(10),
                datos.get(11),
                datos.get(12),
                datos.get(13),
                datos.get(14),
                datos.get(15),
                datos.get(16),
                datos.get(17),
                datos.get(18));
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.insertVaca(vaca);

    }
}
