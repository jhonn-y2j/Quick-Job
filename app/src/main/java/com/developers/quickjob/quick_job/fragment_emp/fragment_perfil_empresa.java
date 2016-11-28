package com.developers.quickjob.quick_job.fragment_emp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.LoginActivity;
import com.developers.quickjob.quick_job.MainActivity;
import com.developers.quickjob.quick_job.MainActivityEmp;
import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.fragment.fragment_perfil_usrs;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.text_ubic_direcc_fap)
    EditText direcc_emp;

    public static final String ID="id";
    int idemps;
    //Operacionesbd db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_perfil_emp, container, false);
        ButterKnife.bind(this,view);
        //db=Operacionesbd.getInstancia(getActivity());

        idemps=getArguments().getInt(ID);

        String url="http://unmsmquickjob.pe.hu/quickjob/perfil_empresa.php?id="+idemps;
        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarRespuesta(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(getActivity()).addRequestQueue(jsonArrayRequest);

        /*Empresa empresa =db.getPerfilEmpresa(idemps);

        if (empresa!=null){
            nom_emp.setText(empresa.getNombre_comercial());
            sector.setText(empresa.getSector());
            tipo_emp.setText(empresa.getTipo_empresa());
            anio_fund.setText(""+empresa.getFundacion_anho());
            Log.d(fragment_perfil_empresa.class.getName(),"anho "+ empresa.getFundacion_anho());
            num_trab.setText(""+empresa.getNro_trabajadores());
            telf_emp.setText(""+empresa.getTelef_referencia());
            ruc_emp.setText(empresa.getRuc());
            departamento_emp.setText(empresa.getUbic_dprt());
            provincia_emp.setText(empresa.getUbic_provincia());
            direcc_emp.setText(empresa.getUbic_direccion());
        }*/

        Log.d(fragment_perfil_usrs.class.getName(),"Bienvenido :  "+ idemps );

        return view;
    }

    private void procesarRespuesta(JSONObject response) {

        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("empresa");
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    nom_emp.setText(jsonObject.getString("empresa_nom_comercial"));
                    sector.setText(jsonObject.getString("empresa_sector"));
                    tipo_emp.setText(jsonObject.getString("empresa_tipo_empresa"));
                    anio_fund.setText(jsonObject.getString("empresa_anio_fund"));
                    num_trab.setText(jsonObject.getString("empresa_num_trabs"));
                    telf_emp.setText(jsonObject.getString("empresa_telef"));
                    ruc_emp.setText(jsonObject.getString("empresa_ruc"));
                    departamento_emp.setText(jsonObject.getString("empresa_depart"));
                    provincia_emp.setText(jsonObject.getString("empresa_provincia"));
                    direcc_emp.setText(jsonObject.getString("empresa_direccion"));
                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
