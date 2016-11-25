package com.developers.quickjob.quick_job.fragment_emp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.adapter_emp.AdapterPublicaciones;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import java.util.List;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class fragmetn_ofertas_publicadas extends Fragment implements AdapterPublicaciones.OnItemClickOferta {

    LinearLayoutManager layoutManager;
    RecyclerView publicaciones;
    List<Oferta> ofertas;
    AdapterPublicaciones adaptador;
    public static final String ID="id";
    int idemps;
    Operacionesbd db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_publicaciones,container,false);

        publicaciones=(RecyclerView)view.findViewById(R.id.recycler_publicaciones);

        layoutManager= new LinearLayoutManager(getActivity());

        db=Operacionesbd.getInstancia(getActivity());

        idemps=getArguments().getInt(ID);

        Log.d(fragmetn_ofertas_publicadas.class.getName(),"holi "+ idemps);

        ofertas=db.getListOfertasPublicadasEmp(idemps);

        adaptador= new AdapterPublicaciones(getActivity(),ofertas,this);
        publicaciones.setLayoutManager(layoutManager);

        publicaciones.setAdapter(adaptador);

        return view;
    }

    @Override
    public void itemClick(Oferta app) {

    }
}
