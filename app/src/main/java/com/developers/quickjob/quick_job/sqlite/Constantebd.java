package com.developers.quickjob.quick_job.sqlite;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public final class Constantebd {

    // tabla postulante
    public static final String TABLE_POSTULANTE="postulante";
    public static final String TABLE_POSTULANTE_ID="id";
    public static final String TABLE_POSTULANTE_NOMBS="nombres";
    public static final String TABLE_POSTULANTE_APELLIDOS="apellidos";
    public static final String TABLE_POSTULANTE_CORREO="correo";
    public static final String TABLE_POSTULANTE_CONTRASENHA="contrasenha";
    public static final String TABLE_POSTULANTE_TELEFONO="telefono";
    public static final String TABLE_POSTULANTE_FECHA_NAC="fecha_nac";
    public static final String TABLE_POSTULANTE_DIRECCION="direccion";
    public static final String TABLE_POSTULANTE_GENERO="genero";
    public static final String TABLE_POSTULANTE_CENTRO_ESTUDIOS="centro_estudios";
    public static final String TABLE_POSTULANTE_CARRERA="carrera";
    public static final String TABLE_POSTULANTE_COND_ACAD="cond_acad";
    public static final String TABLE_POSTULANTE_CAT_ESTUD="cat_estud";
    public static final String TABLE_POSTULANTE_TIPO_BUSQ="tipo_busq";
    public static final String TABLE_POSTULANTE_ESTADO="estado";
    public static final String TABLE_POSTULANTE_EMPRESA="empresa";
    public static final String TABLE_POSTULANTE_CARGO="cargo";

    // tabla empresa
    public static final String TABLE_EMPRESA ="empresa";
    public static final String TABLE_EMPRESA_ID="id";
    public static final String TABLE_EMPRESA_RUC="ruc";
    public static final String TABLE_EMPRESA_NOMB_COMERCIAL="nomb_comercial";
    public static final String TABLE_EMPRESA_CORREO="correo";
    public static final String TABLE_EMPRESA_CONTRASENHA="contrasenha";
    public static final String TABLE_EMPRESA_TIPO_EMPRESA="tipo_empresa";
    public static final String TABLE_EMPRESA_SECTOR="sector";
    public static final String TABLE_EMPRESA_NRO_JOBS="nro_jobs";
    public static final String TABLE_EMPRESA_FUND_ANHO="fund_anho";
    public static final String TABLE_EMPRESA_TELFN="telfn";
    public static final String TABLE_EMPRESA_DPRT="dprt";
    public static final String TABLE_EMPRESA_PROV="prov";
    public static final String TABLE_EMPRESA_DISTRIT="distrit";
    public static final String TABLE_EMPRESA_DIRECCION="direccion";

    // tabla oferta
    public static final String TABLE_OFERTA="oferta";
    public static final String TABLE_OFERTA_ID="id";
    public static final String TABLE_OFERTA_PUESTO="puesto";
    public static final String TABLE_OFERTA_AREA="area";
    public static final String TABLE_OFERTA_TIPO_JOB="tipo_job";
    public static final String TABLE_OFERTA_DISPONIBILIDAD="disponibilidad";
    public static final String TABLE_OFERTA_SUELDO="sueldo";
    public static final String TABLE_OFERTA_FECHA_INICIO="fecha_inicio";
    public static final String TABLE_OFERTA_UBICACION="ubicacion";
    public static final String TABLE_OFERTA_REQUISITOS="requisitos";
    public static final String TABLE_OFERTA_FUNCIONES="funciones";
    public static final String TABLE_OFERTA_PERFIL="perfil";
    public static final String TABLE_OFERTA_GENERO="genero";
    public static final String TABLE_OFERTA_RAMAS="ramas";
    public static final String TABLE_OFERTA_FECHA_PUBLICACION="fecha_publicacion";
    //public static final String TABLE_OFERTA_NRO_POSTULANTES="nro_postulantes";
    public static final String TABLE_OFERTA_ID_EMPRESA="id_empresa";

    // tabla postulaciones
    public static final String TABLE_POSTULACIONES="postulaciones";
    public static final String TABLE_POSTULACIONES_ID_POSTULANTE="id_postulante";
    public static final String TABLE_POSTULACIONES_ID_OFERTA="id_oferta";
    //boolean = INTERGER 0 ó 1
    public static final String TABLE_POSTULACIONES_NOTIFUSRS="notifusrs";
    public static final String TABLE_POSTULACIONES_NOTIFEMP="notifemp";
    public static final String TABLE_POSTULACIONES_NRO_POSTULANTES="nro_postulantes";

}
