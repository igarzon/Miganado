package miganado.Data;

import android.provider.BaseColumns;

/**
 * Esquema de la base de datos para las explotaciones
 */
public class ExplotacionContract {


    /*
    * Añade dentro del paquete data una nueva clase llamada ExplotacionContract
    * donde define una clase interna con los datos de la tabla "explotacion" que se creará en la base de datos:
    * String explotacion,
    * String crotal,
             String crotalOriginal,
             String marcaLidia,
             String crotalMadre,
             String sexo,
             String raza,
             String fechaNacimiento,
             String ceaNacimiento,
             String fechaLlegada,
             String ceaLocalizacion,
             String ultimoParto,
             String dato1,
             String dato2,
             String dato3,
             String dato4,
             String dato5,
             String dato6
    */
    public static abstract class ExplotacionEntry implements BaseColumns{
        public static final String TABLE_NAME ="Miganado";

        public static final String EXPLOTACION = "Expltacion";
        public static final String CROTAL = "crotal";
        public static final String CROTAL_ORIGINAL = "crotalOriginal";
        public static final String MARCA_LIDIA = "marcaLidia";
        public static final String CROTAL_MADRE = "crotalMadre";
        public static final String SEXO = "sexo";
        public static final String RAZA = "raza";
        public static final String FECHA_NACIMIENTO = "fechaNacimiento";
        public static final String CEA_NACIMIENTO = "ceaNacimiento";
        public static final String FECHA_LLEGADA = "fechaLlegada";
        public static final String CEA_LOCALIZACION = "ceaLocalizacion";
        public static final String ULTIMO_PARTO = "ultimoParto";
        public static final String DATO1 = "dato1";
        public static final String DATO2 = "dato2";
        public static final String DATO3 = "dato3";
        public static final String DATO4 = "dato4";
        public static final String DATO5 = "dato5";
        public static final String DATO6 = "dato6";

    }
}

/*
En el anterior código podemos notar los siguientes detalles:

Creamos la clase interna ExplotacionEntry para guardar el nombre de las columnas de la tabla.
Se implementó la interfaz BaseColumns con el fin de agregar una columna extra que se recomienda tenga toda tabla.
Estas declaraciones facilitan el mantenimiento del esquema, por si en algún momento cambian los nombres de las tablas o columnas.

*/