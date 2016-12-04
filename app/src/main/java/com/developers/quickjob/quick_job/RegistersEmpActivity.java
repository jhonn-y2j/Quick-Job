package com.developers.quickjob.quick_job;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.Dialog.DateDialog;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class RegistersEmpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Operacionesbd db;
    @BindView(R.id.edit_ruc)
    EditText ruc;
    @BindView(R.id.edit_nomb_comerc)
    EditText nombre;
    @BindView(R.id.edit_email_emp)
    EditText email;
    @BindView(R.id.edit_pass_emp)
    EditText pass;
    @BindView(R.id.edit_passconf_emp)
    EditText pass_conf;
    @BindView(R.id.edit_tipo_emp)
    EditText tipo;
    @BindView(R.id.edit_sector)
    EditText sector;
    @BindView(R.id.edit_nro_jobs)
    EditText nrojobs;
    @BindView(R.id.edit_anho)
    EditText anho;
    @BindView(R.id.edit_tefl_emp)
    EditText telefemp;
    @BindView(R.id.edit_dpto)
    EditText dpto;
    @BindView(R.id.edit_prov)
    EditText prov;
    @BindView(R.id.edit_distrt)
    EditText distr;
    @BindView(R.id.btn_cuenta_emp)
    Button btn_crear_cuenta;
    @BindView(R.id.show_date)
    ImageButton showDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registers_emp);

        this.setTitle("Crea tu cuenta");

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        db = Operacionesbd.getInstancia(getApplicationContext());

    }


    @OnClick(R.id.btn_cuenta_emp)
    public void handleCuentaEmpresa() {

        if (pass.getText().toString().equals(pass_conf.getText().toString())) {
            Empresa empresa = new Empresa();
            empresa.setRuc(ruc.getText().toString());
            empresa.setNombre_comercial(nombre.getText().toString());
            empresa.setCorreo(email.getText().toString());
            empresa.setContrasenha(pass.getText().toString());
            empresa.setTipo_empresa(tipo.getText().toString());
            empresa.setSector(sector.getText().toString());
            empresa.setNro_trabajadores(Integer.parseInt(nrojobs.getText().toString()));
            empresa.setFundacion_anho(anho.getText().toString());
            empresa.setTelef_referencia(Integer.parseInt(telefemp.getText().toString()));
            empresa.setUbic_dprt(dpto.getText().toString());
            empresa.setUbic_provincia(prov.getText().toString());
            empresa.setUbic_direccion(distr.getText().toString());

            registrarEmpresa(empresa);

            /*if (db.registrarEmpresa(empresa) == true) {
                ///db.obtenerEmpresa();
                //String id = db.verficaremprs(empresa.getCorreo(), empresa.getContrasenha());
                //Intent intent = new Intent(getApplicationContext(), MainActivityEmp.class);
                //intent.putExtra(MainActivityEmp.ID, id);
                //Log.d(RegistersEmpActivity.class.getName(), " Registrado  + " + id);
                //startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), " Completar campos requeridos ", Toast.LENGTH_SHORT).show();
            }*/

        }

    }

    public void registrarEmpresa(Empresa empresa){

        String url="http://unmsmquickjob.pe.hu/quickjob/registrar_empresa.php";

        final String ruc=empresa.getRuc();
        final String nomb=empresa.getNombre_comercial();
        final String correo=empresa.getCorreo();
        final String pass=empresa.getContrasenha();
        final String tipo=empresa.getTipo_empresa();
        final String sector=empresa.getSector();
        final String nro=String.valueOf(empresa.getNro_trabajadores());
        final String fund=String.valueOf(empresa.getFundacion_anho());
        final String telef=String.valueOf(empresa.getTelef_referencia());
        final String dpto=empresa.getUbic_dprt();
        final String prov=empresa.getUbic_provincia();
        final String distrit=empresa.getUbic_direccion();
        final String direc=empresa.getUbic_direccion();

        HashMap<String,String> map = new HashMap<>();
        map.put("ruc",ruc);
        map.put("nomb",nomb);
        map.put("correo",correo);
        map.put("pass",pass);
        map.put("tipo",tipo);
        map.put("sector",sector);
        map.put("num",nro);
        map.put("anio",fund);
        map.put("telf",telef);
        map.put("dpto",dpto);
        map.put("provincia",prov);
        map.put("distrito",distrit);
        map.put("direccion",direc);

        JSONObject jsonObject= new JSONObject(map);

        Log.d(RegistersEmpActivity.class.getName(),jsonObject.toString());

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(
                new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuesta(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(RegistersEmpActivity.class.getName(), "Error Volley: " + error.getMessage());
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String ,String> headers= new HashMap<String, String>();
                        headers.put("Content-Type","application/json; charset=utf-8");
                        headers.put("Accept","application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                });

    }


    private void procesarRespuesta(JSONObject response){
        try {
            String estado=response.getString("estado");
            String mensaje= response.getString("mensaje");
            switch (estado){
                case "1":
                    Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case "2":
                    Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        anho.setText("" + i + "-" + (i1+1) + "-" + i2);
    }

    @OnClick(R.id.show_date)
    public void onClick() {
        DateDialog dateDialog =new DateDialog();
        dateDialog.show(getSupportFragmentManager(), "datepicker");
    }
}
