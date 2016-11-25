package com.developers.quickjob.quick_job.modelo;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class Oferta {

    private String id;
    private String puesto;
    private String area;
    private String tipo_trabajo;
    //full time / Part time
    private String disponibilidad;
    private String sueldo;
    private String fecha_inicio;
    private String ubicacion_job;
    // requisitos
    private String requisitos;
    private String funciones;
    //estudiante/ recienegresado
    private String perfil;
    //M/F
    private String genero;
    private String ramas_carrera;
    private int id_empresa;

    //adicional
    private String fecha_publicacion;
    private int cantidad_postulantes;

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTipo_trabajo() {
        return tipo_trabajo;
    }

    public void setTipo_trabajo(String tipo_trabajo) {
        this.tipo_trabajo = tipo_trabajo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getUbicacion_job() {
        return ubicacion_job;
    }

    public void setUbicacion_job(String ubicacion_job) {
        this.ubicacion_job = ubicacion_job;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRamas_carrera() {
        return ramas_carrera;
    }

    public void setRamas_carrera(String ramas_carrera) {
        this.ramas_carrera = ramas_carrera;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getCantidad_postulantes() {
        return cantidad_postulantes;
    }

    public void setCantidad_postulantes(int cantidad_postulantes) {
        this.cantidad_postulantes = cantidad_postulantes;
    }
}
