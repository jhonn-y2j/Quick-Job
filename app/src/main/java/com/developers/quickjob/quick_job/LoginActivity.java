package com.developers.quickjob.quick_job;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_login_correo)
    EditText correo;
    @BindView(R.id.edit_login_pass)
    EditText password;
    @BindView(R.id.btn_entrar)
    Button iniciar;
    @BindView(R.id.btn_registrar)
    Button registrarusrs;
    Operacionesbd db;
    @BindView(R.id.group_iniciar)
    RadioGroup groupIniciar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // quitas la barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        db = Operacionesbd.getInstancia(getApplicationContext());

    }

    // cambiar activity para crear empreso o users

    @OnClick(R.id.btn_registrar)
    public void handleRegistrar() {
        if (groupIniciar.getCheckedRadioButtonId()==R.id.radio_postulante) {
            startActivity(new Intent(getApplicationContext(), RegistersUsrsActivity.class));
        }else{
            startActivity(new Intent(getApplicationContext(), RegistersEmpActivity.class));
        }
    }

    @OnClick(R.id.btn_entrar)
    public void handleIniciar() {
        String email = correo.getText().toString();
        String pass = password.getText().toString();
        Intent intent;
        String id=null;
        if (groupIniciar.getCheckedRadioButtonId()==R.id.radio_postulante){
            id = db.verficarusrs(email, pass);
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }else{
            id = db.verficaremprs(email, pass);
            intent = new Intent(getApplicationContext(), MainActivityEmp.class);
        }
        if (id != null) {
            intent.putExtra(MainActivity.ID, id);
            startActivity(intent);
        } else {
            db.obtenerEmpresa();
            db.obtenerOferta();
            Toast.makeText(getApplicationContext(), " Usuario no registrado ", Toast.LENGTH_SHORT).show();
        }
    }

}
