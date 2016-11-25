package com.developers.quickjob.quick_job.modelo;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class Postulante {

    private String id;
    private String nombres;
    private String apellidos;
    //usrs
    private String correo;
    // 6 caracteres
    private String contrasenha;

    private int telefono;
    private String fecha_nacimiento;
    private String direccion;
    private String genero;

    private String centro_estudios;
    private String carrera;

    //estudiante o recien egresado
    private String condicion_acad;
    //Tercio/Quinto/Décimo superior/ no especificar
    private String categorizacion_estud;
    // Practicas pre, Primer trabajo /Freelance
    private String tipo_buscqda;
    // true o false
    private boolean experiencia;
    private String empresa_exp;
    private String empresa_cargo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCentro_estudios() {
        return centro_estudios;
    }

    public void setCentro_estudios(String centro_estudios) {
        this.centro_estudios = centro_estudios;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCondicion_acad() {
        return condicion_acad;
    }

    public void setCondicion_acad(String condicion_acad) {
        this.condicion_acad = condicion_acad;
    }

    public String getCategorizacion_estud() {
        return categorizacion_estud;
    }

    public void setCategorizacion_estud(String categorizacion_estud) {
        this.categorizacion_estud = categorizacion_estud;
    }

    public String getTipo_buscqda() {
        return tipo_buscqda;
    }

    public void setTipo_buscqda(String tipo_buscqda) {
        this.tipo_buscqda = tipo_buscqda;
    }

    public boolean isExperiencia() {
        return experiencia;
    }

    public void setExperiencia(boolean experiencia) {
        this.experiencia = experiencia;
    }

    public String getEmpresa_cargo() {
        return empresa_cargo;
    }

    public void setEmpresa_cargo(String empresa_cargo) {
        this.empresa_cargo = empresa_cargo;
    }

    public String getEmpresa_exp() {
        return empresa_exp;
    }

    public void setEmpresa_exp(String empresa_exp) {
        this.empresa_exp = empresa_exp;
    }
}
