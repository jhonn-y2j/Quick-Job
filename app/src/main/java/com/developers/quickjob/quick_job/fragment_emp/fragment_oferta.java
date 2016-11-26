package com.developers.quickjob.quick_job.fragment_emp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class fragment_oferta extends Fragment implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.edit_puesto)
    EditText puesto;
    @BindView(R.id.edit_area)
    EditText area;
    @BindView(R.id.edit_tipotrabajo)
    EditText tipojob;
    @BindView(R.id.spinner_disponibilidad)
    Spinner disponibilidad;
    @BindView(R.id.edit_sueldooferta)
    EditText sueldo;
    @BindView(R.id.edit_fechainicio)
    EditText fecha;
    @BindView(R.id.edit_ubicacion)
    EditText ubicacion;
    @BindView(R.id.edit_requisitos)
    EditText requisitos;
    @BindView(R.id.edit_funciones)
    EditText funciones;
    @BindView(R.id.spinner_perfil)
    Spinner perfil;
    @BindView(R.id.spinner_genero)
    Spinner genero;
    @BindView(R.id.edit_ramas)
    EditText ramas;
    @BindView(R.id.btn_publicar)
    Button publicar;

    public static final String ID="id";
    int idemps;
    Operacionesbd db;

    String sdiponb;
    String sperfil;
    String ssexo;

    // firebase

    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publ_oferta, container, false);
        ButterKnife.bind(this, view);
        db=Operacionesbd.getInstancia(getActivity());

        databaseReference= FirebaseDatabase.getInstance().getReference();

        idemps=getArguments().getInt(ID);

        disponibilidad.setOnItemSelectedListener(this);
        perfil.setOnItemSelectedListener(this);
        genero.setOnItemSelectedListener(this);

        return view;
    }

    @OnClick(R.id.btn_publicar)
    public void onClick() {

        Oferta oferta= new Oferta();
        oferta.setPuesto(puesto.getText().toString());
        oferta.setArea(area.getText().toString());
        oferta.setTipo_trabajo(tipojob.getText().toString());
        oferta.setDisponibilidad(sdiponb);
        oferta.setSueldo(sueldo.getText().toString());
        oferta.setFecha_inicio(fecha.getText().toString());
        oferta.setUbicacion_job(ubicacion.getText().toString());
        oferta.setRequisitos(requisitos.getText().toString());
        oferta.setFunciones(funciones.getText().toString());
        oferta.setPerfil(sperfil);
        oferta.setGenero(ssexo);
        oferta.setRamas_carrera(ramas.getText().toString());

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEE MMMM dd");
        String publicacion=simpleDateFormat.format(System.currentTimeMillis());
        oferta.setFecha_publicacion(publicacion);
        Log.d(fragment_oferta.class.getName(),publicacion);
        oferta.setId_empresa(idemps);

        if (db.publicarOferta(oferta)){
            databaseReference.child("ofertas").child(String.valueOf(idemps)).setValue(oferta);
            Toast.makeText(getActivity()," Registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Completar datos", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int spinner_id=adapterView.getId();
        switch (spinner_id){
            case R.id.spinner_disponibilidad:
                sdiponb=adapterView.getItemAtPosition(i).toString();
                Log.d(fragment_oferta.class.getName(),sdiponb);
                break;
            case R.id.spinner_genero:
                ssexo=adapterView.getItemAtPosition(i).toString();
                Log.d(fragment_oferta.class.getName(),ssexo);
                break;
            case R.id.spinner_perfil:
                sperfil=adapterView.getItemAtPosition(i).toString();
                Log.d(fragment_oferta.class.getName(),sperfil);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
