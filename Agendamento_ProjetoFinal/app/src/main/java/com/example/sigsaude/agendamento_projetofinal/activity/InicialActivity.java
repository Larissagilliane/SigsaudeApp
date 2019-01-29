package com.example.sigsaude.agendamento_projetofinal.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.example.sigsaude.agendamento_projetofinal.fragments.Agendamentos;
import com.example.sigsaude.agendamento_projetofinal.fragments.Agende;
import com.example.sigsaude.agendamento_projetofinal.fragments.Inicial;
import com.example.sigsaude.agendamento_projetofinal.fragments.Sair;
import com.example.sigsaude.agendamento_projetofinal.fragments.Unidades;
import com.example.sigsaude.agendamento_projetofinal.fragments.Perfil;

//Pagina inicial da sessão do Paciente
public class InicialActivity extends AppCompatActivity {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView nvDrawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(drawerToggle);

        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = Inicial.class;

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

    }

    public void selectDrawerItem(MenuItem item){
        Fragment fragment = null;
        Class fragmentClass;

        switch (item.getItemId()) {
            case R.id.nav_agende:
                fragmentClass = Agende.class;
                break;
            case R.id.nav_unidades:
                fragmentClass = Unidades.class;
                break;
            case R.id.nav_agendamentos:
                fragmentClass = Agendamentos.class;
                break;
            case R.id.nav_perfil:
                fragmentClass = Perfil.class;
                break;
            case R.id.nav_sair:
                fragmentClass = Sair.class;
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                fragmentClass = Agende.class;
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawer(nvDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (drawerToggle.onOptionsItemSelected(item))
       {
           return true;
       }

        return super.onOptionsItemSelected(item);
    }

    public void agendamento(View view){
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = Agende.class;

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    public void cancelar(View view) {
        startActivity(new Intent(this, InicialActivity.class));
    }

}
