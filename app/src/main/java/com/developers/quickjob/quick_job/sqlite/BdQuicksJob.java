package com.developers.quickjob.quick_job.sqlite;

import android.content.ComponentName;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class BdQuicksJob extends SQLiteOpenHelper {

    private static final String namedb="quicksj.db";
    private static final int versiondb=1;

    interface referencias{
        String ID_POSTULANTE=String.format("REFERENCES %s(%s)",Constantebd.TABLE_POSTULANTE, Constantebd.TABLE_POSTULANTE_ID);
        String ID_OFERTA=String.format("REFERENCES %s(%s) ON DELETE CASCADE ",Constantebd.TABLE_OFERTA, Constantebd.TABLE_OFERTA_ID);
        String ID_EMPRESA=String.format("REFERENCES %s(%s)",Constantebd.TABLE_EMPRESA,Constantebd.TABLE_EMPRESA_ID);
    }

    public BdQuicksJob(Context context) {
        super(context, namedb, null, versiondb);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaPostulante=" CREATE TABLE " + Constantebd.TABLE_POSTULANTE + "( "+
                Constantebd.TABLE_POSTULANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantebd.TABLE_POSTULANTE_NOMBS + " TEXT , " +
                Constantebd.TABLE_POSTULANTE_APELLIDOS + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_CORREO + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_CONTRASENHA + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_TELEFONO + " INTEGER , " +
                Constantebd.TABLE_POSTULANTE_FECHA_NAC + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_DIRECCION + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_GENERO+ " TEXT, " +
                Constantebd.TABLE_POSTULANTE_CENTRO_ESTUDIOS + " TEXT, " +
                Constantebd.TABLE_POSTULANTE_CARRERA + " TEXT , " +
                Constantebd.TABLE_POSTULANTE_COND_ACAD + " TEXT , "+
                Constantebd.TABLE_POSTULANTE_CAT_ESTUD + " TEXT , " +
                Constantebd.TABLE_POSTULANTE_TIPO_BUSQ+" TEXT, " +
                Constantebd.TABLE_POSTULANTE_ESTADO+" INTEGER DEFAULT 0 , " +
                Constantebd.TABLE_POSTULANTE_EMPRESA+ " TEXT , " +
                Constantebd.TABLE_POSTULANTE_CARGO +" TEXT )";

        String queryCrearTablaEmpresa=" CREATE TABLE " + Constantebd.TABLE_EMPRESA+ " ( " +
                Constantebd.TABLE_EMPRESA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantebd.TABLE_EMPRESA_RUC + " TEXT, " +
                Constantebd.TABLE_EMPRESA_NOMB_COMERCIAL + " TEXT, " +
                Constantebd.TABLE_EMPRESA_CORREO+ " TEXT, " +
                Constantebd.TABLE_EMPRESA_CONTRASENHA + " TEXT, " +
                Constantebd.TABLE_EMPRESA_TIPO_EMPRESA + " TEXT, "+
                Constantebd.TABLE_EMPRESA_SECTOR+ " TEXT, " +
                Constantebd.TABLE_EMPRESA_NRO_JOBS + " INTEGER , " +
                Constantebd.TABLE_EMPRESA_FUND_ANHO+ " INTEGER, " +
                Constantebd.TABLE_EMPRESA_TELFN + " TEXT, " +
                Constantebd.TABLE_EMPRESA_DPRT + " TEXT, " +
                Constantebd.TABLE_EMPRESA_PROV + " TEXT, " +
                Constantebd.TABLE_EMPRESA_DISTRIT + " TEXT, " +
                Constantebd.TABLE_EMPRESA_DIRECCION + " TEXT )";

        String queryCrearTablaOferta=" CREATE TABLE " + Constantebd.TABLE_OFERTA + " ( " +
                Constantebd.TABLE_OFERTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Constantebd.TABLE_OFERTA_PUESTO + " TEXT , " +
                Constantebd.TABLE_OFERTA_AREA + " TEXT, " +
                Constantebd.TABLE_OFERTA_TIPO_JOB + " TEXT, " +
                Constantebd.TABLE_OFERTA_DISPONIBILIDAD + " TEXT, "+
                Constantebd.TABLE_OFERTA_SUELDO+ " TEXT, " +
                Constantebd.TABLE_OFERTA_FECHA_INICIO + " TEXT, " +
                Constantebd.TABLE_OFERTA_UBICACION+ " TEXT, " +
                Constantebd.TABLE_OFERTA_REQUISITOS + " TEXT, " +
                Constantebd.TABLE_OFERTA_FUNCIONES + " TEXT, " +
                Constantebd.TABLE_OFERTA_PERFIL + " TEXT, " +
                Constantebd.TABLE_OFERTA_GENERO + " TEXT, " +
                Constantebd.TABLE_OFERTA_RAMAS + " TEXT, "+
                Constantebd.TABLE_OFERTA_FECHA_PUBLICACION+ " TEXT, "+
                Constantebd.TABLE_OFERTA_ID_EMPRESA + " INTEGER, " +
                "FOREIGN KEY ( " + Constantebd.TABLE_OFERTA_ID_EMPRESA + ") " + referencias.ID_EMPRESA + " )";
                ;

        String queryCrearTablaPostulaciones="  CREATE TABLE " + Constantebd.TABLE_POSTULACIONES + " ( " +
                BaseColumns._ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "+
                Constantebd.TABLE_POSTULACIONES_ID_POSTULANTE + " INTEGER, " +
                Constantebd.TABLE_POSTULACIONES_ID_OFERTA+ " INTEGER , " +
                Constantebd.TABLE_POSTULACIONES_NOTIFUSRS+ " INTEGER DEFAULT 0, " +
                Constantebd.TABLE_POSTULACIONES_NOTIFEMP + " INTEGER DEFAULT 0 , " +
                Constantebd.TABLE_POSTULACIONES_NRO_POSTULANTES + " INTEGER , " +
                " FOREIGN KEY ( " + Constantebd.TABLE_POSTULACIONES_ID_POSTULANTE + " ) " + referencias.ID_POSTULANTE + " , " +
                " FOREIGN KEY ( " + Constantebd.TABLE_POSTULACIONES_ID_OFERTA + " ) " + referencias.ID_OFERTA + " ) ";

        sqLiteDatabase.execSQL(queryCrearTablaPostulante);
        sqLiteDatabase.execSQL(queryCrearTablaEmpresa);
        sqLiteDatabase.execSQL(queryCrearTablaOferta);
        sqLiteDatabase.execSQL(queryCrearTablaPostulaciones);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST " + Constantebd.TABLE_POSTULANTE);
        db.execSQL("DROP TABLE IF EXIST " + Constantebd.TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXIST " + Constantebd.TABLE_OFERTA);
        db.execSQL("DROP TABLE IF EXIST " + Constantebd.TABLE_POSTULACIONES);

        onCreate(db);

    }

}
