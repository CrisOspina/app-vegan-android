package com.example.vegan.actividades;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vegan.R;
import com.example.vegan.actividades.ui.main.SectionsPagerAdapter;
import com.example.vegan.formularios.FormAgricultoresActivity;
import com.example.vegan.formularios.FormComercianteActivity;
import com.example.vegan.formularios.FormCompradorActivity;
import com.example.vegan.fragmentos.AgricultoresFragment;
import com.example.vegan.fragmentos.ComerciantesFragment;
import com.example.vegan.fragmentos.CompradoresFragment;

public class ContenedorInstruccionesActivity extends AppCompatActivity implements AgricultoresFragment.OnFragmentInteractionListener,
        ComerciantesFragment.OnFragmentInteractionListener, CompradoresFragment.OnFragmentInteractionListener {

    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos = findViewById(R.id.idLinearPuntos);
        agregarIndicadorPuntos(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    //puntos de sliders
    private void agregarIndicadorPuntos(int pos) {
        puntosSlide = new TextView[3];
        linearPuntos.removeAllViews();

        for(int i = 0; i < puntosSlide.length; i++){
            puntosSlide[i] = new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226"));
            puntosSlide[i].setTextSize(35);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));
            linearPuntos.addView(puntosSlide[i]);
        }

        if(puntosSlide.length > 0) {
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.colorBlanco));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            agregarIndicadorPuntos(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //registro
    public void registro(View view) {
        dialogoTipoDeUsuario().show();
    }

    //Alerta para elegir el formulario según tipo de usuario
    public AlertDialog dialogoTipoDeUsuario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ContenedorInstruccionesActivity.this);
        builder.setTitle(R.string.saludo)
                .setMessage(R.string.tipoUsuarios)
                .setPositiveButton("Agricultor", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lo_intent = new Intent(getApplicationContext(), FormAgricultoresActivity.class);
                        startActivity(lo_intent);
                        finish();
                        dialog.cancel();
                    }
                })
                .setNeutralButton("Comerciante", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lo_intent = new Intent(getApplicationContext(), FormComercianteActivity.class);
                        startActivity(lo_intent);
                        finish();

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Comprador", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lo_intent = new Intent(getApplicationContext(), FormCompradorActivity.class);
                        startActivity(lo_intent);
                        finish();
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    //login
    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}