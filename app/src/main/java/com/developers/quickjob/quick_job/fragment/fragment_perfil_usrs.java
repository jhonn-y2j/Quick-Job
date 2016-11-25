package com.developers.quickjob.quick_job.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.modelo.Postulante;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class fragment_perfil_usrs extends Fragment {

    @BindView(R.id.text_nomb_ape)
    EditText nomb_apell;
    @BindView(R.id.text_estudio)
    EditText estudio;
    @BindView(R.id.text_carrera)
    EditText carrera;
    @BindView(R.id.text_fecha_nac)
    EditText fecha_nac;
    @BindView(R.id.text_genero)
    EditText genero;
    @BindView(R.id.text_direccion)
    EditText direccion;
    @BindView(R.id.text_telef)
    EditText telefono;
    @BindView(R.id.text_centroestudio)
    EditText centro_estd;
    @BindView(R.id.text_profesion)
    EditText profesion;
    @BindView(R.id.text_buscar)
    EditText buscar;
    @BindView(R.id.text_sit_acacd)
    EditText sit_acad;
    @BindView(R.id.text_cond_acad)
    EditText cond_acd;
    @BindView(R.id.text_exp)
    EditText experiencia;
    @BindView(R.id.text_exp_laboral)
    EditText experiencia_laboral;

    public static final String ID="id";
    int idusers;
    Operacionesbd db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_user, container, false);

        ButterKnife.bind(this,view);

        db=Operacionesbd.getInstancia(getActivity());

        idusers=getArguments().getInt(ID);

        Postulante postulante=db.getPerfilPostulante(idusers);

        if (postulante!=null){
            nomb_apell.setText(postulante.getApellidos()+ ", " +postulante.getNombres());
            estudio.setText(postulante.getCentro_estudios());
            carrera.setText(postulante.getCarrera());
            fecha_nac.setText(postulante.getFecha_nacimiento());
            genero.setText(postulante.getGenero());
            direccion.setText(postulante.getDireccion());
            telefono.setText(""+postulante.getTelefono());
            centro_estd.setText(postulante.getCentro_estudios());
            profesion.setText(postulante.getCarrera());
            buscar.setText(postulante.getTipo_buscqda());
            sit_acad.setText(postulante.getCondicion_acad());
            cond_acd.setText(postulante.getCategorizacion_estud());
            experiencia_laboral.setText(postulante.getEmpresa_exp()+ " / " + postulante.getEmpresa_cargo());
        }

        Log.d(fragment_perfil_usrs.class.getName(),"holo "+ idusers );

        return view;
    }
}
