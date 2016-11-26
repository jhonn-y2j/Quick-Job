package com.developers.quickjob.quick_job.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.modelo.Postulaciones;
import com.developers.quickjob.quick_job.modelo.Postulante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class Operacionesbd {

    private static BdQuicksJob db;

    private static Operacionesbd operacionesbd= new Operacionesbd();

    private Operacionesbd(){

    }

    public static Operacionesbd getInstancia(Context context){
        if(db==null){
            db= new BdQuicksJob(context);
        }
        return operacionesbd;
    }

    // usuario

    public boolean registrarPostulante(Postulante postulante){

        boolean estado=false;

        SQLiteDatabase sqLiteDatabase= db.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Constantebd.TABLE_POSTULANTE_NOMBS,postulante.getNombres());
        values.put(Constantebd.TABLE_POSTULANTE_APELLIDOS,postulante.getApellidos());
        values.put(Constantebd.TABLE_POSTULANTE_CORREO,postulante.getCorreo());
        values.put(Constantebd.TABLE_POSTULANTE_CONTRASENHA,postulante.getContrasenha());
        values.put(Constantebd.TABLE_POSTULANTE_TELEFONO,postulante.getTelefono());
        values.put(Constantebd.TABLE_POSTULANTE_FECHA_NAC,postulante.getFecha_nacimiento());
        values.put(Constantebd.TABLE_POSTULANTE_DIRECCION,postulante.getDireccion());
        values.put(Constantebd.TABLE_POSTULANTE_GENERO,postulante.getGenero());
        values.put(Constantebd.TABLE_POSTULANTE_CENTRO_ESTUDIOS,postulante.getCentro_estudios());
        values.put(Constantebd.TABLE_POSTULANTE_CARRERA,postulante.getCarrera());
        values.put(Constantebd.TABLE_POSTULANTE_COND_ACAD,postulante.getCondicion_acad());
        values.put(Constantebd.TABLE_POSTULANTE_CAT_ESTUD,postulante.getCategorizacion_estud());
        values.put(Constantebd.TABLE_POSTULANTE_TIPO_BUSQ,postulante.getTipo_buscqda());
        values.put(Constantebd.TABLE_POSTULANTE_ESTADO,postulante.isExperiencia());
        values.put(Constantebd.TABLE_POSTULANTE_EMPRESA,postulante.getEmpresa_exp());
        values.put(Constantebd.TABLE_POSTULANTE_CARGO,postulante.getEmpresa_cargo());

        if (sqLiteDatabase.insert(Constantebd.TABLE_POSTULANTE,null, values)>0){
            db.close();
            estado=true;
        }

        return estado;

    }

    public void obtenerPostulante(){
        SQLiteDatabase sqLiteDatabase= db.getReadableDatabase();

        String sql= " select * from postulante ";

        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Log.d(Operacionesbd.class.getName(),cursor.getString(2) + cursor.getString(3) + cursor.getString(4));
        }

        sqLiteDatabase.close();

    }

    public String verficarusrs(String correo, String pass){
        String id=null;
        SQLiteDatabase database=db.getWritableDatabase();
        String sql=String.format(" select %s, %s, %s from %s where %s=? and %s=? ", Constantebd.TABLE_POSTULANTE_CORREO,
                Constantebd.TABLE_POSTULANTE_CONTRASENHA,Constantebd.TABLE_POSTULANTE_ID,
                Constantebd.TABLE_POSTULANTE, Constantebd.TABLE_POSTULANTE_CORREO
                ,Constantebd.TABLE_POSTULANTE_CONTRASENHA );

        String parametro[]={correo,pass};

        Cursor registro=database.rawQuery(sql,parametro);

        if (registro.moveToNext()){
            id=registro.getString(2);
            database.close();

        }
        return id;
    }

    public Postulante getPerfilPostulante(int idusers){
        Postulante postulante=null;

        SQLiteDatabase database= db.getWritableDatabase();

        String sql= " select * from " + Constantebd.TABLE_POSTULANTE + " where " + Constantebd.TABLE_POSTULANTE_ID +" = " + idusers;

        Cursor registro= database.rawQuery(sql,null);

        if(registro.moveToNext()){
            postulante= new Postulante();
            postulante.setNombres(registro.getString(1));
            postulante.setApellidos(registro.getString(2));
            postulante.setCorreo(registro.getString(3));
            postulante.setTelefono(registro.getInt(5));
            postulante.setFecha_nacimiento(registro.getString(6));
            postulante.setDireccion(registro.getString(7));
            postulante.setGenero(registro.getString(8));
            postulante.setCentro_estudios(registro.getString(9));
            postulante.setCarrera(registro.getString(10));
            postulante.setCondicion_acad(registro.getString(11));
            postulante.setCategorizacion_estud(registro.getString(12));
            postulante.setTipo_buscqda(registro.getString(13));
            postulante.setEmpresa_exp(registro.getString(15));
            postulante.setEmpresa_cargo(registro.getString(16));
            database.close();
        }

        return postulante;
    }


    // Empresa

    public boolean registrarEmpresa(Empresa empresa){

        boolean estado=false;

        SQLiteDatabase sqLiteDatabase= db.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Constantebd.TABLE_EMPRESA_RUC,empresa.getRuc());
        values.put(Constantebd.TABLE_EMPRESA_NOMB_COMERCIAL,empresa.getNombre_comercial());
        values.put(Constantebd.TABLE_EMPRESA_CORREO,empresa.getCorreo());
        values.put(Constantebd.TABLE_EMPRESA_CONTRASENHA,empresa.getContrasenha());
        values.put(Constantebd.TABLE_EMPRESA_TIPO_EMPRESA,empresa.getTipo_empresa());
        values.put(Constantebd.TABLE_EMPRESA_SECTOR,empresa.getSector());
        values.put(Constantebd.TABLE_EMPRESA_NRO_JOBS,empresa.getNro_trabajadores());
        values.put(Constantebd.TABLE_EMPRESA_FUND_ANHO,empresa.getFundacion_anho());
        values.put(Constantebd.TABLE_EMPRESA_TELFN,empresa.getTelef_referencia());
        values.put(Constantebd.TABLE_EMPRESA_DPRT,empresa.getUbic_dprt());
        values.put(Constantebd.TABLE_EMPRESA_PROV,empresa.getUbic_provincia());
        values.put(Constantebd.TABLE_EMPRESA_DISTRIT,empresa.getUbic_distrito());
        values.put(Constantebd.TABLE_EMPRESA_DIRECCION,empresa.getUbic_direccion());

        if (sqLiteDatabase.insert(Constantebd.TABLE_EMPRESA,null, values)>0){
            estado=true;
        }

        sqLiteDatabase.close();

        return estado;

    }

    public String verficaremprs(String correo, String pass){
        String id=null;
        SQLiteDatabase database=db.getWritableDatabase();
        String sql=String.format(" select %s, %s, %s from %s where %s=? and %s=? ", Constantebd.TABLE_EMPRESA_CORREO,
                Constantebd.TABLE_EMPRESA_CONTRASENHA,Constantebd.TABLE_EMPRESA_ID,
                Constantebd.TABLE_EMPRESA, Constantebd.TABLE_EMPRESA_CORREO
                ,Constantebd.TABLE_EMPRESA_CONTRASENHA );

        String parametro[]={correo,pass};

        Cursor registro=database.rawQuery(sql,parametro);

        if (registro.moveToNext()){
            id=registro.getString(2);
            database.close();
        }
        return id;
    }

    // metodo de prueba

    public void obtenerEmpresa(){
        SQLiteDatabase sqLiteDatabase= db.getReadableDatabase();

        String sql= " select * from empresa ";

        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Log.d(Operacionesbd.class.getName(),cursor.getString(2) + cursor.getString(3) + cursor.getString(4));
        }

        sqLiteDatabase.close();

    }

    public Empresa getPerfilEmpresa(int idemps){
        Empresa empresa = null;
        SQLiteDatabase database = db.getWritableDatabase();
        String query = "select * from "+Constantebd.TABLE_EMPRESA+ " where "+Constantebd.TABLE_EMPRESA_ID+" = "+ idemps;
        Cursor registro = database.rawQuery(query,null);

        if(registro.moveToNext()){
            empresa=new Empresa();
            empresa.setRuc(registro.getString(1));
            empresa.setNombre_comercial(registro.getString(2));
            empresa.setCorreo(registro.getString(3));
            empresa.setTipo_empresa(registro.getString(5));
            empresa.setSector(registro.getString(6));
            empresa.setNro_trabajadores(registro.getInt(7));
            empresa.setFundacion_anho(registro.getInt(8));
            empresa.setTelef_referencia(Integer.parseInt(registro.getString(9)));
            empresa.setUbic_dprt(registro.getString(10));
            empresa.setUbic_provincia(registro.getString(11));
            empresa.setUbic_distrito(registro.getString(12));
            empresa.setUbic_direccion(registro.getString(13));
            database.close();
        }
        return empresa;
    }


    // Oferta
    public boolean publicarOferta(Oferta oferta){
        boolean estado = false;
        SQLiteDatabase database=db.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(Constantebd.TABLE_OFERTA_PUESTO,oferta.getPuesto());
        values.put(Constantebd.TABLE_OFERTA_AREA,oferta.getArea());
        values.put(Constantebd.TABLE_OFERTA_TIPO_JOB,oferta.getTipo_trabajo());
        values.put(Constantebd.TABLE_OFERTA_DISPONIBILIDAD,oferta.getDisponibilidad());
        values.put(Constantebd.TABLE_OFERTA_SUELDO,oferta.getSueldo());
        values.put(Constantebd.TABLE_OFERTA_FECHA_INICIO,oferta.getFecha_inicio());
        values.put(Constantebd.TABLE_OFERTA_UBICACION,oferta.getUbicacion_job());
        values.put(Constantebd.TABLE_OFERTA_REQUISITOS,oferta.getRequisitos());
        values.put(Constantebd.TABLE_OFERTA_FUNCIONES,oferta.getFunciones());
        values.put(Constantebd.TABLE_OFERTA_GENERO,oferta.getGenero());
        values.put(Constantebd.TABLE_OFERTA_PERFIL,oferta.getPerfil());
        values.put(Constantebd.TABLE_OFERTA_RAMAS,oferta.getRamas_carrera());
        values.put(Constantebd.TABLE_OFERTA_FECHA_PUBLICACION,oferta.getFecha_publicacion());
        values.put(Constantebd.TABLE_OFERTA_ID_EMPRESA,oferta.getId_empresa());


        if (database.insert(Constantebd.TABLE_OFERTA,null,values)>0){
            estado=true;
        }

        database.close();

        return estado;
    }

    public List<Oferta> getListOfertasPublicadasEmp(int idempresa){

        List<Oferta> ofertas=null;

        SQLiteDatabase database=db.getWritableDatabase();

        String sql=String.format(" select %s.%s, %s, %s, %s, %s from %s, %s where %s.%s=? and %s.%s=%s.%s",
                Constantebd.TABLE_OFERTA, Constantebd.TABLE_OFERTA_ID,
                Constantebd.TABLE_OFERTA_PUESTO,Constantebd.TABLE_OFERTA_UBICACION,Constantebd.TABLE_OFERTA_FECHA_PUBLICACION,
                Constantebd.TABLE_EMPRESA_NOMB_COMERCIAL, Constantebd.TABLE_EMPRESA, Constantebd.TABLE_OFERTA
                ,Constantebd.TABLE_OFERTA,Constantebd.TABLE_OFERTA_ID_EMPRESA,
                Constantebd.TABLE_OFERTA,Constantebd.TABLE_OFERTA_ID_EMPRESA,
                Constantebd.TABLE_EMPRESA,Constantebd.TABLE_EMPRESA_ID);

        String argumentos[]={String.valueOf(idempresa)};

        Cursor registro= database.rawQuery(sql,argumentos);
        ofertas= new ArrayList<>();
        while(registro.moveToNext()){
            Oferta oferta= new Oferta();
            oferta.setId(String.valueOf(registro.getInt(0)));
            oferta.setPuesto(registro.getString(1));
            oferta.setUbicacion_job(registro.getString(2));
            oferta.setFecha_publicacion(registro.getString(3));
            Empresa empresa= new Empresa();
            empresa.setNombre_comercial(registro.getString(4));
            oferta.setEmpresa(empresa);
            ofertas.add(oferta);
        }

        database.close();

        return ofertas;
    }

    public List<Oferta> getOfertasEmpleo(){
        List<Oferta> ofertas=null;

        SQLiteDatabase database=db.getWritableDatabase();

        String sql=String.format(" select %s.%s, %s, %s, %s, %s from %s, %s where %s.%s=%s.%s",
                Constantebd.TABLE_OFERTA, Constantebd.TABLE_OFERTA_ID,
                Constantebd.TABLE_OFERTA_PUESTO,Constantebd.TABLE_OFERTA_UBICACION,Constantebd.TABLE_OFERTA_FECHA_PUBLICACION,
                Constantebd.TABLE_EMPRESA_NOMB_COMERCIAL, Constantebd.TABLE_EMPRESA, Constantebd.TABLE_OFERTA
                ,Constantebd.TABLE_OFERTA,Constantebd.TABLE_OFERTA_ID_EMPRESA,
                Constantebd.TABLE_EMPRESA,Constantebd.TABLE_EMPRESA_ID);

        Cursor registro= database.rawQuery(sql,null);
        ofertas= new ArrayList<>();
        while(registro.moveToNext()){
            Oferta oferta= new Oferta();
            oferta.setId(String.valueOf(registro.getInt(0)));
            oferta.setPuesto(registro.getString(1));
            oferta.setUbicacion_job(registro.getString(2));
            oferta.setFecha_publicacion(registro.getString(3));
            Empresa empresa= new Empresa();
            empresa.setNombre_comercial(registro.getString(4));
            oferta.setEmpresa(empresa);
            ofertas.add(oferta);
        }

        database.close();

        return ofertas;
    }

    public Oferta getOfertasXId(int idoferta){

        Oferta oferta=null;

        SQLiteDatabase database=db.getWritableDatabase();

        String sql=String.format(" select * from %s where %s.%s=?",
                Constantebd.TABLE_OFERTA
                ,Constantebd.TABLE_OFERTA,Constantebd.TABLE_OFERTA_ID);

        String argumentos[]={String.valueOf(idoferta)};

        Cursor registro= database.rawQuery(sql,argumentos);

        if (registro.moveToNext()){
            oferta= new Oferta();
            oferta.setId(String.valueOf(registro.getInt(0)));
            oferta.setPuesto(registro.getString(1));
            oferta.setArea(registro.getString(2));
            oferta.setTipo_trabajo(registro.getString(3));
            oferta.setDisponibilidad(registro.getString(4));
            oferta.setSueldo(registro.getString(5));
            oferta.setFecha_inicio(registro.getString(6));
            oferta.setUbicacion_job(registro.getString(7));
            oferta.setRequisitos(registro.getString(8));
            oferta.setFunciones(registro.getString(9));
            oferta.setPerfil(registro.getString(10));
            oferta.setGenero(registro.getString(11));
            oferta.setRamas_carrera(registro.getString(12));
            oferta.setFecha_publicacion(registro.getString(13));

            String sql1=String.format(" select nomb_comercial from %s where %s.%s=?",
                    Constantebd.TABLE_EMPRESA
                    ,Constantebd.TABLE_EMPRESA,Constantebd.TABLE_EMPRESA_ID);

            String [] arg={String.valueOf(registro.getInt(14))};
            Cursor data = database.rawQuery(sql1,arg);
            if (data.moveToNext()){
                Empresa empresa= new Empresa();
                empresa.setNombre_comercial(data.getString(0));
                oferta.setEmpresa(empresa);
            }
        }

        database.close();

        return oferta;
    }

    // metodo de prueba

    public void obtenerOferta(){
        SQLiteDatabase sqLiteDatabase= db.getReadableDatabase();

        String sql= " select * from oferta ";

        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Log.d(Operacionesbd.class.getName(),cursor.getString(1) + cursor.getString(2) + cursor.getString(3));
        }

        sqLiteDatabase.close();
    }

    // postulaciones

    public boolean registrarPostulacion(Postulaciones postulaciones){
        boolean estado=false;

        SQLiteDatabase database=db.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Constantebd.TABLE_POSTULACIONES_ID_POSTULANTE,postulaciones.getIdpostulante());
        values.put(Constantebd.TABLE_POSTULACIONES_ID_OFERTA,postulaciones.getIdoferta());
        values.put(Constantebd.TABLE_POSTULACIONES_NOTIFUSRS,postulaciones.getPostular());
        values.put(Constantebd.TABLE_POSTULACIONES_NOTIFEMP,postulaciones.getMsjempresa());
        values.put(Constantebd.TABLE_POSTULACIONES_NRO_POSTULANTES,postulaciones.getNropostulantes());

        if (database.insertOrThrow(Constantebd.TABLE_POSTULACIONES,null,values)>0){
            estado=true;
        }

        database.close();

        return estado;

    }

    // metodo de prueba

    public void getPostulaciones(){
        SQLiteDatabase sqLiteDatabase= db.getReadableDatabase();

        String sql= " select * from postulaciones ";

        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);

        while(cursor.moveToNext()){
            Log.d(Operacionesbd.class.getName()," registro -> "+cursor.getInt(1) + cursor.getInt(2));
        }

        sqLiteDatabase.close();
    }




}
