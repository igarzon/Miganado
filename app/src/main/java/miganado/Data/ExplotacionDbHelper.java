package miganado.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import miganado.Data.ExplotacionContract.ExplotacionEntry;

/**
 * Manejador de la base de datos
 */
public class ExplotacionDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExplotacionPrueba.db";

    public ExplotacionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ExplotacionEntry.TABLE_NAME + " ("
                + ExplotacionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ExplotacionEntry.CROTAL + " TEXT NOT NULL,"
                + ExplotacionEntry.CROTAL_ORIGINAL + " TEXT NOT NULL,"
                + ExplotacionEntry.MARCA_LIDIA + " TEXT NOT NULL,"
                + ExplotacionEntry.CROTAL_MADRE + " TEXT NOT NULL,"
                + ExplotacionEntry.SEXO + " TEXT NOT NULL,"
                + ExplotacionEntry.RAZA + " TEXT NOT NULL,"
                + ExplotacionEntry.FECHA_NACIMIENTO + " TEXT NOT NULL,"
                + ExplotacionEntry.CEA_NACIMIENTO + " TEXT NOT NULL,"
                + ExplotacionEntry.FECHA_LLEGADA + " TEXT NOT NULL,"
                + ExplotacionEntry.CEA_LOCALIZACION + " TEXT NOT NULL,"
                + ExplotacionEntry.ULTIMO_PARTO + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO1 + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO2 + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO3 + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO4 + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO5 + " TEXT NOT NULL,"
                + ExplotacionEntry.DATO6 + " TEXT NOT NULL,"
                + ExplotacionEntry.FECHA_MODIFICACION + " TEXT NOT NULL,"
                + ExplotacionEntry.BAJA + " TEXT NOT NULL,"
                + "UNIQUE (" + ExplotacionEntry.CROTAL + "))");

    }



    public long mockExplotacion(SQLiteDatabase db, Explotacion vaca) {
        return db.insert(
                ExplotacionEntry.TABLE_NAME,
                null,
                vaca.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveExplotacion(Explotacion vaca) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                ExplotacionEntry.TABLE_NAME,
                null,
                vaca.toContentValues());

    }

    public ArrayList<String> getExplotaciones() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = null;
        res = db.rawQuery("SELECT "+ExplotacionEntry.CEA_LOCALIZACION+" FROM "+ExplotacionEntry.TABLE_NAME,null);
        res.moveToFirst();

        while(!res.isLast()){
            String a = res.getString(0);
            if(!array_list.contains(a)) {
                array_list.add(a);
            }
            res.moveToNext();
        }
        if(!array_list.contains(res.getString(0))) {
            array_list.add(res.getString(0));
        }
        res.close();
        return array_list;
    }


    public boolean existExplotaciones() {

        SQLiteDatabase db = this.getReadableDatabase();

        boolean result = false;

        String sql = "SELECT COUNT (*) FROM " + ExplotacionEntry.TABLE_NAME;

        Cursor mcursor = db.rawQuery(sql, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){
            //hay contenido
            result = true;

        }

        return result;

    }

    public ArrayList<String> getVacasExplotacion(String exp) {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = null;
        res = db.rawQuery("SELECT "+ExplotacionEntry.CROTAL+" FROM "+ExplotacionEntry.TABLE_NAME+" WHERE "+ExplotacionEntry.CEA_LOCALIZACION+" LIKE '"+exp+"'",null);
        res.moveToFirst();

        while(!res.isLast()){
            String a = res.getString(0);
            array_list.add(a);
            res.moveToNext();
        }
        array_list.add(res.getString(0));

        res.close();
        return array_list;
    }

    public Cursor /*ArrayList<String>*/ getCrotal(String crotal) {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = null;
        res = db.rawQuery("SELECT * FROM "+ExplotacionEntry.TABLE_NAME+" WHERE "+ExplotacionEntry.CROTAL+" LIKE '"+crotal+"'",null);

        /*for(int i = 1; i<res.getColumnCount();i++) {
            res.moveToFirst();
            String a = res.getString(i);
            array_list.add(a);
        }*/

        //res.close();
        return res; //array_list;
    }

    public Cursor getExplotacionCrotal(String crotal) {
        Cursor c = getReadableDatabase().query(
                ExplotacionEntry.TABLE_NAME,
                null,
                ExplotacionEntry.CROTAL + " LIKE ?",
                new String[]{crotal},
                null,
                null,
                null);
        return c;
    }

    public int deleteExplotacion(String vacaCrotal) {
        return getWritableDatabase().delete(
                ExplotacionEntry.TABLE_NAME,
                ExplotacionEntry.CROTAL + " LIKE ?",
                new String[]{vacaCrotal});
    }

    public int updateExplotacion(Explotacion vaca, String vacaCrotal) {
        return getWritableDatabase().update(
                ExplotacionEntry.TABLE_NAME,
                vaca.toContentValues(),
                ExplotacionEntry.CROTAL + " LIKE ?",
                new String[]{vacaCrotal}
        );
    }

    public int deleteCrotal(String crotal) {
        return getWritableDatabase().delete(
                ExplotacionEntry.TABLE_NAME,
                ExplotacionEntry.CROTAL + " LIKE ?",
                new String[]{crotal});
    }

    public int updateCrotal(Explotacion crotal, String CrotalID) {
        return getWritableDatabase().update(
                ExplotacionEntry.TABLE_NAME,
                crotal.toContentValues(),
                ExplotacionEntry.CROTAL + " LIKE ?",
                new String[]{CrotalID}
        );
    }

    public void insertVaca(Explotacion vaca){
        getWritableDatabase().insert(ExplotacionEntry.TABLE_NAME, null, vaca.toContentValues());
    }

}
