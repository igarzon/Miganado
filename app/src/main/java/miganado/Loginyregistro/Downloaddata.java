package miganado.Loginyregistro;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ProgressBar;


import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import miganado.Data.Explotacion;
import miganado.Data.ExplotacionDbHelper;
import miganado.Operaciones.FichaanimalActivity;
import miganado.Configuracion.ActionBarActivity;

import static java.security.AccessController.getContext;

public class Downloaddata extends ActionBarActivity {

    SessionManager sessionManager;
    private boolean into=false;

    private int progressStatus = 0;
    private Handler handler = new Handler();
    private TextView tv ;
    private ProgressBar pb;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaddata);

        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);

        getSupportActionBar().hide();


        // Set the progress status zero on each button click
        progressStatus = 0;

        // Session class instance
        sessionManager = new SessionManager(getApplicationContext());

                System.out.println(sessionManager.getUserDetails().toString());
                String user = sessionManager.getUserDetails().toString();
                String user1 = user.replaceAll("\\{", "");
                String user2 = user1.replaceAll("\\}", "");

                 //Diseccionamos la cadena
                String[] users = user2.split("=");

                String username = users[1];


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            System.out.println(response.length());

                            System.out.println(response);



                            //Diseccionamos la cadena
                            String[] partsDatos = response.split("\\[");

                            String datosusuario=partsDatos[0];

                            //Limpiamos de caracteres las líneas
                            String newLineuser1 = datosusuario.replaceAll("\"\"", "vacio");
                            System.out.println(newLineuser1);
                            String newLineuser2= newLineuser1.replaceAll(",", ":");
                            System.out.println(newLineuser2);
                            String newLineuser3 = newLineuser2.replaceAll("\\{", "");
                            System.out.println(newLineuser3);
                            String newLineuser4 = newLineuser3.replaceAll("\\}", "");
                            System.out.println(newLineuser4);
                            String newLineuser5 = newLineuser4.replaceAll("\"", "");
                            System.out.println(newLineuser5);

                            //Diseccionamos la cadena
                            String[] partsPreferencias = newLineuser5.split(":");

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Downloaddata.this);
                            SharedPreferences.Editor editor = pref.edit();

                            for (int i = 1; i < 12; i+=2) {
                                System.out.println(partsPreferencias[i]);
                            }

                            editor.putString("dato1",partsPreferencias[1].equals("vacio") ? "Dato 1" : partsPreferencias[1]);
                            editor.putString("dato2",partsPreferencias[3].equals("vacio") ? "Dato 2" : partsPreferencias[3]);
                            editor.putString("dato3",partsPreferencias[5].equals("vacio") ? "Dato 3" : partsPreferencias[5]);
                            editor.putString("dato4",partsPreferencias[7].equals("vacio") ? "Dato 4" : partsPreferencias[7]);
                            editor.putString("dato5",partsPreferencias[9].equals("vacio") ? "Dato 5" : partsPreferencias[9]);
                            editor.putString("dato6",partsPreferencias[11].equals("vacio") ? "Dato 6" : partsPreferencias[11]);
                            editor.commit();

                            pref.edit();

                            //sessionManager.setDatos(partsPreferencias[1],partsPreferencias[3],partsPreferencias[5],partsPreferencias[7],partsPreferencias[9],partsPreferencias[11]);

                            String respuesta1="["+partsDatos[1];

                            /*System.out.println(datosusuario);
                            System.out.println(respuesta);*/



                            String respuesta=partsDatos[1].replaceAll("\\]", "");
                            //Diseccionamos la cadena
                            jsonArray = new JSONArray(respuesta1);
                            String[] partsDatosJson = respuesta.split("\\}");





                           for(int i=0; i<jsonArray.length(); i++){

                               progressStatus=(i*100)/jsonArray.length();
                                //jsonArray.getString(i);

                                //System.out.println(jsonArray.getString(i));

                                //Almacenamos linea a linea cada una de las filas de la tabla
                                //String Linea=jsonArray.get(i).toString();
                               String Linea = partsDatosJson[i];
                               System.out.println("Linea=   " + Linea);


                                //Limpiamos de caracteres las líneas
                                String newLine1 = Linea.replaceAll("\"\"", "vacio");

                               //System.out.println("aaaaaaaaa:  " + newLine1);
                                String newLine2 = newLine1.replaceAll("\"", "");
                               //System.out.println("bbbbbbbbb:  " + newLine2);
                                String newLine3 = newLine2.replaceAll("\\{", "");
                               //System.out.println("cccccccc:  " + newLine3);
                                String newLine4 = newLine3.substring(1);
                               //System.out.println("dddddddddddd:  " + newLine3);
                                String newLine = newLine4.replaceAll(",", ":");
                               //System.out.println("eeeeeeeeee:  " + newLine);

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


                                String fechaModificacion=parts[73]+":"+parts[74]+":"+parts[75];

                                datos.add(fechaModificacion);
                                datos.add(parts[81]);

                                darAlta(datos);

                                //Actualizamos los String de cada uno de los elementos de la tabla
                                 String crotal = parts[3];
                                 String crotalOriginal = parts[7];
                                 String marcaLidia= parts[11];
                                 String crotalMadre = parts[15];
                                 String sexo= parts[19];
                                 String raza= parts[23];
                                 String fechaNacimiento=parts[27];
                                 String ceaNacimiento=parts[31];
                                 String ceaOrigen=parts[35];
                                 String fechaLlegada=parts[39];
                                 String ceaLocalizacion=parts[43];
                                 String ultimoParto=parts[47];
                                 String dato1=parts[51];
                                 String dato2=parts[55];
                                 String dato3=parts[59];
                                 String dato4=parts[63];
                                 String dato5=parts[67];
                                 String dato6=parts[71];
                                 fechaModificacion=parts[73]+":"+parts[74]+":"+parts[75];
                                 String baja=parts[81];


                                /*for (int j = 3; j < parts.length; j+=4) {
                                    System.out.println(parts[j]);
                                }

                                for (int k = 0; k < parts.length; k+=1) {
                                    System.out.println(k+" :"+parts[k]);
                                }*/

                                //Datos[i]=Linea;

                               if (i == jsonArray.length()-1){
                                   into=true;

                                   Toast.makeText(getApplicationContext(), "DATOS CORRECTAMENTE DESCARGADOS", Toast.LENGTH_LONG).show();

                               }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (into==true || jsonArray.length()<1) {
                            Intent intent = new Intent(Downloaddata.this, ZonaclienteActivity.class);
                            Downloaddata.this.startActivity(intent);
                        }


                    }

                };
                DownloaddataRequest DownloaddataRequest = new DownloaddataRequest(username,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(Downloaddata.this);
                queue.add(DownloaddataRequest).hasHadResponseDelivered();


            }

    //Funcion que se realiza al pulsar el boton explotaciones
    public void Acceso(View v) {

            if (into==true) {
                Intent intent = new Intent(Downloaddata.this, ZonaclienteActivity.class);
                Downloaddata.this.startActivity(intent);
            }

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
                datos.get(18),
                datos.get(19),
                "0");
        ExplotacionDbHelper mydb;
        mydb = new ExplotacionDbHelper(this);
        mydb.insertVaca(vaca);
        mydb.close();

    }
}
