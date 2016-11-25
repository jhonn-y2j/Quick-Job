package com.developers.quickjob.quick_job.modelo;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class Postulaciones {

    private int idpostulante;
    private int idoferta;
    private int postular; // 0 || 1
    private int msjempresa; // 0 || 1

    private int nropostulantes;
    private Postulante postulante;

    private Oferta oferta;

    public int getIdpostulante() {
        return idpostulante;
    }

    public int getPostular() {
        return postular;
    }

    public void setPostular(int postular) {
        this.postular = postular;
    }

    public int getMsjempresa() {
        return msjempresa;
    }

    public void setMsjempresa(int msjempresa) {
        this.msjempresa = msjempresa;
    }

    public int getNropostulantes() {
        return nropostulantes;
    }

    public void setNropostulantes(int nropostulantes) {
        this.nropostulantes = nropostulantes;
    }

    public void setIdpostulante(int idpostulante) {
        this.idpostulante = idpostulante;
    }

    public int getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(int idoferta) {
        this.idoferta = idoferta;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
}
