package com.developers.quickjob.quick_job.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developers.quickjob.quick_job.OfertaDetalleActivity;
import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.adapter_emp.AdapterPublicaciones;
import com.developers.quickjob.quick_job.fragment_emp.fragmetn_ofertas_publicadas;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class fragment_ofertas_empleo extends Fragment implements AdapterPublicaciones.OnItemClickOferta{

    LinearLayoutManager layoutManager;
    RecyclerView publicaciones;
    List<Oferta> ofertas;
    AdapterPublicaciones adaptador;
    Operacionesbd db;

    public static final String ID="id";
    public static final String ID_OFERT="id_emp";
    int idusers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_ofertasempleo,container,false);

        publicaciones=(RecyclerView)view.findViewById(R.id.recycler_ofertas_empleo);

        layoutManager= new LinearLayoutManager(getActivity());

        idusers=getArguments().getInt(ID);

        db=Operacionesbd.getInstancia(getActivity());

        ofertas=db.getOfertasEmpleo();

        adaptador= new AdapterPublicaciones(getActivity(),ofertas,this);
        publicaciones.setLayoutManager(layoutManager);

        publicaciones.setAdapter(adaptador);

        return view;
    }

    @Override
    public void itemClick(Oferta app) {
        Intent intent= new Intent(getActivity(), OfertaDetalleActivity.class);
        intent.putExtra(ID,idusers);
        intent.putExtra(ID_OFERT,Integer.parseInt(app.getId()));
        startActivity(intent);

    }
}
