package miganado.Data;

import java.util.ArrayList;

/**
 * Created by davii on 10/01/2017.
 */

public class GlobalVariable {
    public static ArrayList<String> explotaciones = new ArrayList<String>();
    public static Class activityAnterior;

    public GlobalVariable(){}

    public ArrayList<String> getExplotaciones(){
        return explotaciones;
    }

    public void addExplotaciones(String exp){
        this.explotaciones.add(exp);
    }

    public void actualizarExplotaciones(ArrayList<String> exp){
        this.explotaciones=exp;
    }

    public void setActivityAnterior(Class activity){
        this.activityAnterior=activity;
    }

    public Class getActivityAnterior(){
        return this.activityAnterior;
    }
}