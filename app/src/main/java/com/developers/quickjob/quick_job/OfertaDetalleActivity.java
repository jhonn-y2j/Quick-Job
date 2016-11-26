package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.modelo.Postulaciones;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class OfertaDetalleActivity extends AppCompatActivity {

    @BindView(R.id.txt_ofer_area)
    TextView ofert_area;
    @BindView(R.id.txt_ofer_puesto)
    TextView ofert_puesto;
    @BindView(R.id.btn_postular)
    Button btn_postular;

    public static final String ID = "id";
    public static final String ID_OFERT = "id_emp";
    int idusers;
    int idoferts;

    Operacionesbd db;
    @BindView(R.id.text_nomb_emp)
    TextView nomb_emp;
    @BindView(R.id.txt_ofer_tipo)
    TextView OferTipo;
    @BindView(R.id.txt_ofer_disponib)
    TextView OferDisponib;
    @BindView(R.id.txt_ofer_sueldo)
    TextView OferSueldo;
    @BindView(R.id.txt_ofer_data_home)
    TextView OferDataHome;
    @BindView(R.id.txt_ofer_ubicacion)
    TextView OferUbicacion;
    @BindView(R.id.txt_ofer_req)
    TextView OferReq;
    @BindView(R.id.txt_ofer_funciones)
    TextView OferFunciones;
    @BindView(R.id.txt_ofer_perfil)
    TextView OferPerfil;
    @BindView(R.id.txt_ofer_genero)
    TextView OferGenero;
    @BindView(R.id.txt_ofer_ramas)
    TextView OferRamas;
    @BindView(R.id.txt_ofer_fecha_publicacion)
    TextView OferFechaPublicacion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_oferta_detalle);
        this.setTitle("Oferta de Empleo");

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        idusers = getIntent().getIntExtra(ID, 0);
        idoferts = getIntent().getIntExtra(ID_OFERT, 0);
        Log.d(OfertaDetalleActivity.class.getName(), " users - > " + idusers + " / oferts -> " + idoferts);

        db = Operacionesbd.getInstancia(getApplicationContext());

        Oferta oferta = db.getOfertasXId(idoferts);
        if (oferta != null) {
            nomb_emp.setText(oferta.getEmpresa().getNombre_comercial());
            ofert_puesto.setText(oferta.getPuesto());
            ofert_area.setText(oferta.getArea());
            OferTipo.setText(oferta.getTipo_trabajo());
            OferDisponib.setText("Tiempo: "+oferta.getDisponibilidad());
            OferSueldo.setText("Sueldo: S/. " +oferta.getSueldo());
            OferDataHome.setText("Inicia: "+oferta.getFecha_inicio());
            OferUbicacion.setText("Lugar: "+ oferta.getUbicacion_job());
            OferReq.setText("Requisitos: \n" + oferta.getRequisitos());
            OferFunciones.setText("Funciones: \n" +oferta.getFunciones());
            OferPerfil.setText("Perfil: \n" +oferta.getPerfil());
            OferGenero.setText("Género: " + oferta.getGenero());
            OferRamas.setText("Ramas: \n" + oferta.getRamas_carrera());
            OferFechaPublicacion.setText("Publicado: " + oferta.getFecha_publicacion());
            Log.d(OfertaDetalleActivity.class.getName(), oferta.getEmpresa().getNombre_comercial());
        }


    }

    @OnClick(R.id.btn_postular)
    public void onClick() {

        if (idusers > 0 && idoferts > 0) {
            Postulaciones postulaciones = new Postulaciones();
            postulaciones.setIdoferta(idoferts);
            postulaciones.setIdpostulante(idusers);
            postulaciones.setPostular(1);
            postulaciones.setMsjempresa(0);
            postulaciones.setNropostulantes(1);
            if (db.registrarPostulacion(postulaciones)) {
                Toast.makeText(getApplicationContext(), " Postulado ", Toast.LENGTH_SHORT).show();
                db.getPostulaciones();
            } else {
                Toast.makeText(getApplicationContext(), " Problema en postular ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
