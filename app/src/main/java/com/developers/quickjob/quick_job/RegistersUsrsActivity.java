package com.developers.quickjob.quick_job;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.developers.quickjob.quick_job.modelo.Postulante;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class RegistersUsrsActivity extends AppCompatActivity {

    @BindView(R.id.edit_nombres)
    EditText nombres;
    @BindView(R.id.edit_apellidos)
    EditText apellidos;
    @BindView(R.id.edit_email)
    EditText email;
    @BindView(R.id.edit_pass)
    EditText pass;
    @BindView(R.id.edit_pass_confir)
    EditText pass_conf;
    @BindView(R.id.edit_date_nac)
    EditText date_nac;
    @BindView(R.id.edit_donde_vives)
    EditText vive;
    @BindView(R.id.edit_tefl)
    EditText telf;
    @BindView(R.id.radio_femenino)
    RadioButton radioFemenino;
    @BindView(R.id.radio_masculino)
    RadioButton radioMasculino;
    @BindView(R.id.options_sexo)
    RadioGroup sexo;
    @BindView(R.id.edit_donde_estudias)
    EditText estudia;
    @BindView(R.id.edit_carrera)
    EditText carrera;
    @BindView(R.id.radio_estudiante)
    RadioButton radioEstudiante;
    @BindView(R.id.radio_egresado)
    RadioButton radioEgresado;
    @BindView(R.id.options_cond_acad)
    RadioGroup cond_acad;
    @BindView(R.id.radio_tercer)
    RadioButton radioTercer;
    @BindView(R.id.radio_quinto)
    RadioButton radioQuinto;
    @BindView(R.id.radio_decimo)
    RadioButton radioDecimo;
    @BindView(R.id.radio_na)
    RadioButton radioNa;
    @BindView(R.id.options_cat_acad)
    RadioGroup cat_acad;
    @BindView(R.id.radio_pract)
    RadioButton radioPract;
    @BindView(R.id.radio_free)
    RadioButton radioFree;
    @BindView(R.id.radio_jobs)
    RadioButton radioJobs;
    @BindView(R.id.options_buscar)
    RadioGroup buscar;
    @BindView(R.id.radio_exp_si)
    RadioButton radioExpSi;
    @BindView(R.id.radio_exp_no)
    RadioButton radioExpNo;
    @BindView(R.id.options_exp)
    RadioGroup optionsExp;
    @BindView(R.id.edit_empr)
    EditText emprs_exp;
    @BindView(R.id.edit_emprcargo)
    EditText emprs_cargo;
    @BindView(R.id.container_exp_si)
    LinearLayout experiencia;
    @BindView(R.id.btn_crear_cuenta)
    Button crear_cuenta;

    boolean estado;

    Operacionesbd db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registers_usrs);

        ButterKnife.bind(this);

        db= Operacionesbd.getInstancia(getApplicationContext());

    }

    public void onRadioExpClicked(View view){
        boolean marcado=((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_exp_si:
                if (marcado){
                    experiencia.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.radio_exp_no:
                if (marcado){
                    experiencia.setVisibility(View.GONE);
                    emprs_exp.setText(" ");
                    emprs_cargo.setText(" ");
                }
                break;
        }
    }

    @OnClick(R.id.btn_crear_cuenta)
    public void handleCrearCuenta() {
        Postulante postulante = new Postulante();
        if (pass.getText().toString().equals(pass_conf.getText().toString())) {
            postulante.setNombres(nombres.getText().toString());
            postulante.setApellidos(apellidos.getText().toString());
            postulante.setCorreo(email.getText().toString());
            postulante.setContrasenha(pass.getText().toString());
            postulante.setFecha_nacimiento(date_nac.getText().toString());
            postulante.setDireccion(vive.getText().toString());
            postulante.setCentro_estudios(estudia.getText().toString());
            postulante.setCarrera(carrera.getText().toString());

            if (sexo.getCheckedRadioButtonId() == R.id.radio_femenino) {
                postulante.setGenero("Femenino");
            } else {
                postulante.setGenero("Masculino");
            }

            if (cond_acad.getCheckedRadioButtonId() == R.id.radio_estudiante) {
                postulante.setCondicion_acad("Estudiante");
            } else {
                postulante.setCondicion_acad("Recién Egresado");
            }

            if (cat_acad.getCheckedRadioButtonId() == R.id.radio_tercer) {
                postulante.setCategorizacion_estud("Tercio Superior");
            } else if (cat_acad.getCheckedRadioButtonId() == R.id.radio_quinto) {
                postulante.setCategorizacion_estud("Quinto Superior");
            } else if (cat_acad.getCheckedRadioButtonId() == R.id.radio_decimo) {
                postulante.setCategorizacion_estud("Décimo Superior");
            } else {
                postulante.setCategorizacion_estud("No específicado");
            }

            if (buscar.getCheckedRadioButtonId() == R.id.radio_pract) {
                postulante.setTipo_buscqda("Prácticas Pre");
            } else if (buscar.getCheckedRadioButtonId() == R.id.radio_jobs) {
                postulante.setTipo_buscqda("Primeros Trabajos");
            } else {
                postulante.setTipo_buscqda("Freelance");
            }

            postulante.setTelefono(Integer.parseInt(telf.getText().toString()));
            postulante.setExperiencia(estado);
            postulante.setEmpresa_exp(emprs_exp.getText().toString());
            postulante.setEmpresa_cargo(emprs_cargo.getText().toString());

            if (db.registrarPostulante(postulante)) {
                db.obtenerPostulante();
                String id=db.verficarusrs(postulante.getCorreo(),postulante.getContrasenha());
                Log.d(RegistersUsrsActivity.class.getName(), " Registrado  + ");
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Completar campos requeridos", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
