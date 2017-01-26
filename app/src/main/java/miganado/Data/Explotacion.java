package miganado.Data;

import android.content.ContentValues;
import android.database.Cursor;


public class Explotacion {
    private String crotal;
    private String crotalOriginal;
    private String marcaLidia;
    private String crotalMadre;
    private String sexo;
    private String raza;
    private String fechaNacimiento;
    private String ceaNacimiento;
    private String ceaOrigen;
    private String fechaLlegada;
    private String ceaLocalizacion;
    private String ultimoParto;
    private String dato1;
    private String dato2;
    private String dato3;
    private String dato4;
    private String dato5;
    private String dato6;
    private String fechaModificacion;
    private String baja;
    private String modificado;

    public Explotacion(String crotal,
             String crotalOriginal,
             String marcaLidia,
             String crotalMadre,
             String sexo,
             String raza,
             String fechaNacimiento,
             String ceaNacimiento,
             String ceaOrigen,
             String fechaLlegada,
             String ceaLocalizacion,
             String ultimoParto,
             String dato1,
             String dato2,
             String dato3,
             String dato4,
             String dato5,
             String dato6,
             String fechaModificacion,
             String baja,
             String modificado) {

        this.crotal=crotal;
        this.crotalOriginal=crotalOriginal;
        this.marcaLidia=marcaLidia;
        this.crotalMadre=crotalMadre;
        this.sexo=sexo;
        this.raza=raza;
        this.ceaNacimiento=ceaNacimiento;
        this.ceaOrigen=ceaOrigen;
        this.fechaNacimiento=fechaNacimiento;
        this.ceaLocalizacion=ceaLocalizacion;
        this.fechaLlegada=fechaLlegada;
        this.ultimoParto=ultimoParto;
        this.dato1=dato1;
        this.dato2=dato2;
        this.dato3=dato3;
        this.dato4=dato4;
        this.dato5=dato5;
        this.dato6=dato6;
        this.fechaModificacion=fechaModificacion;
        this.baja=baja;
        this.modificado=modificado;
    }

    public Explotacion(Cursor cursor) {
        crotal = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CROTAL));
        crotalOriginal = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CROTAL_ORIGINAL));
        marcaLidia = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.MARCA_LIDIA));
        crotalMadre = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CROTAL_MADRE));
        sexo = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.SEXO));
        raza = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.RAZA));
        fechaNacimiento = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.FECHA_NACIMIENTO));
        ceaNacimiento = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CEA_NACIMIENTO));
        ceaOrigen = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CEA_ORIGEN));
        fechaLlegada = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.FECHA_LLEGADA));
        ceaLocalizacion = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.CEA_LOCALIZACION));
        ultimoParto = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.ULTIMO_PARTO));
        dato1 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO1));
        dato2 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO2));
        dato3 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO3));
        dato4 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO4));
        dato5 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO5));
        dato6 = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.DATO6));
        fechaModificacion = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.FECHA_MODIFICACION));
        baja = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.BAJA));
        modificado = cursor.getString(cursor.getColumnIndex(ExplotacionContract.ExplotacionEntry.MODIFICADO));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ExplotacionContract.ExplotacionEntry.CROTAL, crotal);
        values.put(ExplotacionContract.ExplotacionEntry.CROTAL_ORIGINAL, crotalOriginal);
        values.put(ExplotacionContract.ExplotacionEntry.MARCA_LIDIA, marcaLidia);
        values.put(ExplotacionContract.ExplotacionEntry.CROTAL_MADRE, crotalMadre);
        values.put(ExplotacionContract.ExplotacionEntry.SEXO, sexo);
        values.put(ExplotacionContract.ExplotacionEntry.RAZA, raza);
        values.put(ExplotacionContract.ExplotacionEntry.FECHA_NACIMIENTO, fechaNacimiento);
        values.put(ExplotacionContract.ExplotacionEntry.CEA_NACIMIENTO, ceaNacimiento);
        values.put(ExplotacionContract.ExplotacionEntry.CEA_ORIGEN, ceaOrigen);
        values.put(ExplotacionContract.ExplotacionEntry.CEA_LOCALIZACION, ceaLocalizacion);
        values.put(ExplotacionContract.ExplotacionEntry.FECHA_LLEGADA, fechaLlegada);
        values.put(ExplotacionContract.ExplotacionEntry.ULTIMO_PARTO, ultimoParto);
        values.put(ExplotacionContract.ExplotacionEntry.DATO1, dato1);
        values.put(ExplotacionContract.ExplotacionEntry.DATO2, dato2);
        values.put(ExplotacionContract.ExplotacionEntry.DATO3, dato3);
        values.put(ExplotacionContract.ExplotacionEntry.DATO4, dato4);
        values.put(ExplotacionContract.ExplotacionEntry.DATO5, dato5);
        values.put(ExplotacionContract.ExplotacionEntry.DATO6, dato6);
        values.put(ExplotacionContract.ExplotacionEntry.FECHA_MODIFICACION, fechaModificacion);
        values.put(ExplotacionContract.ExplotacionEntry.BAJA, baja);
        values.put(ExplotacionContract.ExplotacionEntry.MODIFICADO, modificado);


        return values;
    }


}
