package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhonn_aj on 04/12/2016.
 */

public class PostuladoDetalleActivity extends AppCompatActivity {

    public static final String ID = "id_oferta";
    public static final String ID_OFERT = "id_postulante";
    int idpostulante;
    int idoferts;

    @BindView(R.id.text_nomb_ape)
    EditText nombs;
    @BindView(R.id.text_estudio)
    EditText estudio;
    @BindView(R.id.text_carrera)
    EditText carrera;
    @BindView(R.id.text_fecha_nac)
    EditText fechanac;
    @BindView(R.id.text_genero)
    EditText genero;
    @BindView(R.id.text_direccion)
    EditText direccion;
    @BindView(R.id.text_telef)
    EditText telefono;
    @BindView(R.id.text_centroestudio)
    EditText centroestudio;
    @BindView(R.id.text_profesion)
    EditText profesion;
    @BindView(R.id.text_buscar)
    EditText buscar;
    @BindView(R.id.text_sit_acacd)
    EditText situacionacaf;
    @BindView(R.id.text_cond_acad)
    EditText condicion;
    @BindView(R.id.text_exp)
    EditText experiencia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_postulante);

        this.setTitle("Información");

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        idpostulante = getIntent().getIntExtra(ID, 0);
        idoferts = getIntent().getIntExtra(ID_OFERT, 0);
        Log.d(PostuladoDetalleActivity.class.getName(), " postulante - > " + idpostulante + " / oferts -> " + idoferts);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enviar_respuesta,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_notificacion){
            Toast.makeText(getApplicationContext()," postulante - > " + idpostulante + " / oferts -> " + idoferts,Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
