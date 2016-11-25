package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.quickjob.quick_job.modelo.Postulaciones;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class OfertaDetalleActivity extends AppCompatActivity {

    @BindView(R.id.txt_ofer_emp)
    TextView ofert_emp;
    @BindView(R.id.txt_ofer_puesto)
    TextView ofert_puesto;
    @BindView(R.id.btn_postular)
    Button btn_postular;

    public static final String ID="id";
    public static final String ID_OFERT="id_emp";
    int idusers;
    int idoferts;

    Operacionesbd db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_oferta_detalle);
        ButterKnife.bind(this);

        idusers=getIntent().getIntExtra(ID,0);
        idoferts=getIntent().getIntExtra(ID_OFERT,0);
        Log.d(OfertaDetalleActivity.class.getName()," users - > " +idusers + " / oferts -> " +idoferts);

        db=Operacionesbd.getInstancia(getApplicationContext());

    }

    @OnClick(R.id.btn_postular)
    public void onClick() {

        if (idusers>0 && idoferts>0){
            Postulaciones postulaciones= new Postulaciones();
            postulaciones.setIdoferta(idoferts);
            postulaciones.setIdpostulante(idusers);
            postulaciones.setPostular(1);
            postulaciones.setMsjempresa(0);
            postulaciones.setNropostulantes(1);
            if (db.registrarPostulacion(postulaciones)){
                Toast.makeText(getApplicationContext()," Postulado ",Toast.LENGTH_SHORT).show();
                db.getPostulaciones();
            }else {
                Toast.makeText(getApplicationContext()," Problema en postular ",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
