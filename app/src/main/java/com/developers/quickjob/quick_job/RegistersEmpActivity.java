package com.developers.quickjob.quick_job;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class RegistersEmpActivity extends AppCompatActivity {

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

        if (this.getSupportActionBar()!=null){
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        db = Operacionesbd.getInstancia(getApplicationContext());

    }


    @OnClick(R.id.btn_cuenta_emp)
    public void handleCuentaEmpresa(){

        if (pass.getText().toString().equals(pass_conf.getText().toString())){
            Empresa empresa= new Empresa();
            empresa.setRuc(ruc.getText().toString());
            empresa.setNombre_comercial(nombre.getText().toString());
            empresa.setCorreo(email.getText().toString());
            empresa.setContrasenha(pass.getText().toString());
            empresa.setTipo_empresa(tipo.getText().toString());
            empresa.setSector(sector.getText().toString());
            empresa.setNro_trabajadores(Integer.parseInt(nrojobs.getText().toString()));
            empresa.setFundacion_anho(Integer.parseInt(anho.getText().toString()));
            empresa.setTelef_referencia(Integer.parseInt(telefemp.getText().toString()));
            empresa.setUbic_dprt(dpto.getText().toString());
            empresa.setUbic_provincia(prov.getText().toString());
            empresa.setUbic_direccion(distr.getText().toString());

            if (db.registrarEmpresa(empresa)==true){
                ///db.obtenerEmpresa();
                String id=db.verficaremprs(empresa.getCorreo(),empresa.getContrasenha());
                Intent intent= new Intent(getApplicationContext(),MainActivityEmp.class);
                intent.putExtra(MainActivityEmp.ID,id);
                Log.d(RegistersEmpActivity.class.getName(), " Registrado  + " + id);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), " Completar campos requeridos ", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
