package com.developers.quickjob.quick_job.fragment_emp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class fragment_notif_emp extends Fragment implements AdapterPublicaciones.OnItemClickOferta {

    LinearLayoutManager layoutManager;
    RecyclerView notificaciones;
    List<Oferta> ofertas;
    AdapterPublicaciones adaptador;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_not_emp, container, false);

        notificaciones = (RecyclerView) view.findViewById(R.id.recycler_notificaciones);

        layoutManager = new LinearLayoutManager(getActivity());

        notificaciones.setLayoutManager(layoutManager);


        return view;
    }

    @Override
    public void itemClick(Oferta app) {

    }

}