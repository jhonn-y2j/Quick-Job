package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.developers.quickjob.quick_job.fragment.fragment_perfil_usrs;
import com.developers.quickjob.quick_job.fragment_emp.fragment_oferta;
import com.developers.quickjob.quick_job.fragment_emp.fragment_perfil_empresa;
import com.developers.quickjob.quick_job.fragment_emp.fragmetn_ofertas_publicadas;

import butterknife.ButterKnife;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class MainActivityEmp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ID="id";
    String idemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_emp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        idemps=getIntent().getStringExtra(ID);

        Log.d(MainActivityEmp.class.getName(),idemps);
        Fragment fragment= new fragmetn_ofertas_publicadas();
        Bundle bundle= new Bundle();
        bundle.putInt(ID,Integer.parseInt(idemps));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment= null;
        if(id==R.id.nav_perfil_emp){
            fragment = new fragment_perfil_empresa();
        }else if (id==R.id.nav_ofertas_emp){
            fragment= new fragment_oferta();
        }else if (id== R.id.nav_publicaciones_emp){
            fragment = new fragmetn_ofertas_publicadas();
        }else if (id==R.id.nav_close_emp){
            this.finish();
        }

        if (fragment!=null){
            Bundle bundle= new Bundle();
            bundle.putInt(ID,Integer.parseInt(idemps));
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
