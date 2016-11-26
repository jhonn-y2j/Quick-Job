package com.developers.quickjob.quick_job.fragment_emp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.fragment.fragment_perfil_usrs;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;

public class fragment_perfil_empresa extends Fragment {

    @BindView(R.id.text_mom_emp_fap)
    EditText nom_emp;
    @BindView(R.id.text_sector_fap)
    EditText sector;
    @BindView(R.id.text_tipoempresa_fap)
    EditText tipo_emp;
    @BindView(R.id.text_anio_fund_fap)
    EditText anio_fund;
    @BindView(R.id.text_num_trab_fap)
    EditText num_trab;
    @BindView(R.id.text_telef_fap)
    EditText telf_emp;
    @BindView(R.id.text_ruc_fap)
    EditText ruc_emp;
    @BindView(R.id.text_ubic_depart_fap)
    EditText departamento_emp;
    @BindView(R.id.text_ubic_provin_fap)
    EditText provincia_emp;
    @BindView(R.id.text_ubic_dist_fap)
    EditText distrito;
    @BindView(R.id.text_ubic_direcc_fap)
    EditText direcc_emp;

    public static final String ID="id";
    int idemps;
    Operacionesbd db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_perfil_emp, container, false);
        ButterKnife.bind(this,view);
        db=Operacionesbd.getInstancia(getActivity());

        idemps=getArguments().getInt(ID);

        Empresa empresa =db.getPerfilEmpresa(idemps);

        if (empresa!=null){
            nom_emp.setText(empresa.getNombre_comercial());
            sector.setText(empresa.getSector());
            tipo_emp.setText(empresa.getTipo_empresa());
            anio_fund.setText(empresa.getFundacion_anho());
            num_trab.setText(empresa.getNro_trabajadores());
            telf_emp.setText(empresa.getTelef_referencia());
            ruc_emp.setText(empresa.getRuc());
            departamento_emp.setText(empresa.getUbic_dprt());
            provincia_emp.setText(empresa.getUbic_provincia());
            distrito.setText(empresa.getUbic_distrito());
            direcc_emp.setText(empresa.getUbic_direccion());
        }

        Log.d(fragment_perfil_usrs.class.getName(),"Bienvenido :  "+ idemps );

        return view;
    }
}
