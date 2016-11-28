package com.developers.quickjob.quick_job;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.ConsultarNube;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    //Operacionesbd db;
    @BindView(R.id.group_iniciar)
    RadioGroup groupIniciar;

    //ConsultarNube ws = new ConsultarNube();

    final String[] id = new String[1];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // quitas la barra de notificaciones
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        //db = Operacionesbd.getInstancia(getApplicationContext());

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
        //Intent intent;
        if (groupIniciar.getCheckedRadioButtonId()==R.id.radio_postulante){
            String url="http://unmsmquickjob.pe.hu/quickjob/verificar_postulante.php?email="+correo.getText().toString()+"&pass="+password.getText().toString();
            JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    procesarRespuestaPostulante(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(jsonArrayRequest);
        }else{
            String url="http://unmsmquickjob.pe.hu/quickjob/verificar_empresa.php?email="+correo.getText().toString()+"&pass="+password.getText().toString();
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

            VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(jsonArrayRequest);
           // new consultarDatos().execute("http://unmsmquickjob.pe.hu/quickjob/consulta_empresa.php?email="+correo.getText().toString()+"&pass="+password.getText().toString());
            /*id = db.verficaremprs(email, pass);
            intent = new Intent(getApplicationContext(), MainActivityEmp.class);*/
        }
       /* if (id != null) {
            intent.putExtra(MainActivity.ID, id);
            startActivity(intent);
        } else {
            //db.obtenerEmpresa();
            //db.obtenerOferta();
            Toast.makeText(getApplicationContext(), " Usuario no registrado ", Toast.LENGTH_SHORT).show();
        }*/

    }

    private void procesarRespuesta(JSONObject response) {

        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("empresa");
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    id[0]=jsonObject.getString("empresa_id");
                    Log.d(LoginActivity.class.getName(),id[0]);
                    Intent intent = new Intent(getApplicationContext(), MainActivityEmp.class);
                    intent.putExtra(MainActivity.ID, id[0]);
                    startActivity(intent);
                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void procesarRespuestaPostulante(JSONObject response) {

        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("postulante");
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    id[0]=jsonObject.getString("id_postulante");
                    Log.d(LoginActivity.class.getName(),id[0]);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(MainActivity.ID, id[0]);
                    startActivity(intent);
                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*public class consultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return ws.downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            JSONArray json = null;
            try {
                json = new JSONArray(result);
                System.out.println("RUC : "+json.getString(1));
                System.out.println("Nombre empresa: "+json.getString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(" no obtuvo los datos");
        }
    }*/

}
