package com.developers.quickjob.quick_job.adapter_emp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.modelo.Oferta;

import java.util.List;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class AdapterPublicaciones extends RecyclerView.Adapter<AdapterPublicaciones.publicaciones_holder>{

    LayoutInflater layoutInflater;
    List<Oferta> ofertaList;

    OnItemClickOferta onItemClickOferta;

    public AdapterPublicaciones(Context context, List<Oferta> ofertaList, OnItemClickOferta onItemClickOferta){
        this.layoutInflater=LayoutInflater.from(context);
        this.ofertaList=ofertaList;
        this.onItemClickOferta=onItemClickOferta;
    }

    @Override
    public publicaciones_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_publicaciones,parent,false);
        return new publicaciones_holder(view);
    }

    @Override
    public void onBindViewHolder(publicaciones_holder holder, int position) {
        Oferta app=ofertaList.get(position);

        holder.puesto.setText(app.getPuesto());
        holder.empresa.setText(app.getEmpresa().getNombre_comercial());
        holder.ubicacion.setText(app.getUbicacion_job());
        holder.fecha_public.setText(app.getFecha_publicacion());

        holder.setOnItemClickAppLock(app,onItemClickOferta);
    }

    @Override
    public int getItemCount() {
        if (ofertaList!=null) {
            return this.ofertaList.size();
        }
        return 0;
    }

    public interface OnItemClickOferta{
        public void itemClick(Oferta app);
    }

    public static  class publicaciones_holder extends RecyclerView.ViewHolder{


        TextView puesto, empresa,ubicacion,fecha_public;

        public publicaciones_holder(View itemView) {
            super(itemView);

            puesto=(TextView)itemView.findViewById(R.id.txt_puesto);
            empresa=(TextView)itemView.findViewById(R.id.txt_empresa);
            ubicacion=(TextView)itemView.findViewById(R.id.txt_lugar);
            fecha_public=(TextView)itemView.findViewById(R.id.txt_fecha_publ);

        }

        public void setOnItemClickAppLock(final Oferta app, final OnItemClickOferta onItemClickOferta){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickOferta.itemClick(app);
                }
            });
        }

    }

}

