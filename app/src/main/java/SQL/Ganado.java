package SQL;

/**
 * Created by Sergio on 24/11/2016.
 */

public class Ganado {

    //private variables
    String _crotal;
    String _sexo;

    // Empty constructor
    public Ganado(){

    }

    // constructor
    public Ganado(String _crotal, String _sexo){
        this._crotal = _crotal;
        this._sexo = _sexo;
    }

    // getting name
    public String getCrotal(){
        return this._crotal;
    }

    // setting name
    public void setCrotal(String crotal){
        this._crotal = crotal;
    }

    // getting phone number
    public String getSexo(){
        return this._sexo;
    }

    // setting phone number
    public void setSexo(String sexo){
        this._sexo = sexo;
    }
}



