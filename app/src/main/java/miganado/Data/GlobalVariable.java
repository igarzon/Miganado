package miganado.Data;

import java.util.ArrayList;

/**
 * Created by davii on 10/01/2017.
 */

public class GlobalVariable {
    public static ArrayList<String> explotaciones = new ArrayList<String>();
    public static ArrayList<String> auxBusqueda = new ArrayList<String>();
    public static Class activityAnterior;

    public GlobalVariable(){}

    public ArrayList<String> getExplotaciones(){
        return explotaciones;
    }

    public void addExplotaciones(String exp){
        explotaciones.add(exp);
    }

    public ArrayList<String> getAuxBusqueda(){
        return auxBusqueda;
    }

    public void setAuxBusqueda(ArrayList<String> resultado){ auxBusqueda = resultado;}

    public void actualizarExplotaciones(ArrayList<String> exp){
        explotaciones=exp;
    }

    public void setActivityAnterior(Class activity){
        activityAnterior=activity;
    }

    public Class getActivityAnterior(){
        return activityAnterior;
    }
}