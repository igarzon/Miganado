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

        public static final String CROTAL = "Crotal";
        public static final String CROTAL_ORIGINAL = "CrotalOriginal";
        public static final String MARCA_LIDIA = "MarcaLidia";
        public static final String CROTAL_MADRE = "CrotalMadre";
        public static final String SEXO = "Sexo";
        public static final String RAZA = "Raza";
        public static final String FECHA_NACIMIENTO = "FechaNacimiento";
        public static final String CEA_NACIMIENTO = "CeaNacimiento";
        public static final String CEA_ORIGEN = "CeaOrigen";
        public static final String FECHA_LLEGADA = "FechaLlegada";
        public static final String CEA_LOCALIZACION = "CeaLocalizacion";
        public static final String ULTIMO_PARTO = "UltimoParto";
        public static final String DATO1 = "Dato1";
        public static final String DATO2 = "Dato2";
        public static final String DATO3 = "Dato3";
        public static final String DATO4 = "Dato4";
        public static final String DATO5 = "Dato5";
        public static final String DATO6 = "Dato6";
        public static final String FECHA_MODIFICACION = "FechaModificacion";
        public static final String BAJA = "Baja";
        public static final String MODIFICADO = "Modificado";


    }
}

/*
En el anterior código podemos notar los siguientes detalles:

Creamos la clase interna ExplotacionEntry para guardar el nombre de las columnas de la tabla.
Se implementó la interfaz BaseColumns con el fin de agregar una columna extra que se recomienda tenga toda tabla.
Estas declaraciones facilitan el mantenimiento del esquema, por si en algún momento cambian los nombres de las tablas o columnas.

*/