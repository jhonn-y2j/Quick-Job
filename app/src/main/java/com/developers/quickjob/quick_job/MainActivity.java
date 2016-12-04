package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.developers.quickjob.quick_job.fragment.fragment_notf_usrs;
import com.developers.quickjob.quick_job.fragment.fragment_ofertas_empleo;
import com.developers.quickjob.quick_job.fragment.fragment_perfil_usrs;
import com.developers.quickjob.quick_job.fragment.fragment_postulaciones;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ID="id";
    String idusers;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" Ofertas Empleo ");
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        idusers=getIntent().getStringExtra(ID);

       // Log.d(MainActivity.class.getName(),idusers);

        Fragment fragment=new fragment_ofertas_empleo();
        Bundle bundle= new Bundle();
        bundle.putInt(ID,Integer.parseInt(idusers));
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

        if (id==R.id.nav_perfil){
            toolbar.setTitle("Perfil");
            fragment= new fragment_perfil_usrs();
        }else if (id==R.id.nav_ofertas){
            toolbar.setTitle("Ofertas Empleo");
            fragment= new fragment_ofertas_empleo();
        }else if (id==R.id.nav_postulaciones){
            toolbar.setTitle(" ");
            fragment= new fragment_postulaciones();
        }else if (id==R.id.nav_close){
            this.finish();
        }else if (id==R.id.nav_notification){
            fragment=new fragment_notf_usrs();
            toolbar.setTitle(" ");
        }

        if (fragment!=null){
            Bundle bundle= new Bundle();
            bundle.putInt(ID,Integer.parseInt(idusers));
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
