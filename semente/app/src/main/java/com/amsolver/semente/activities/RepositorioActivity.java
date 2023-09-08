package com.amsolver.semente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amsolver.semente.R;

public class RepositorioActivity extends AppCompatActivity {

    ImageView ivBack;

    TextView textView_DadosDeContacto;
    TextView textView_FichaAsociarse;
    TextView textView_TripticoInformativo;
    TextView textView_GuiaParaFamilias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio_layout);
        loadViews();
        loadListeners();
    }

    public void loadViews() {
        ivBack = findViewById(R.id.ivBack);
        textView_DadosDeContacto = findViewById(R.id.textView_DadosDeContacto);
        textView_FichaAsociarse = findViewById(R.id.textView_FichaAsociarse);
        textView_TripticoInformativo = findViewById(R.id.textView_TripticoInformativo);
        textView_GuiaParaFamilias = findViewById(R.id.textView_GuiaParaFamilias);
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView_DadosDeContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar pdf de dados de contacto
                Intent intent = new Intent(RepositorioActivity.this, ShowPdfActivity.class);
                intent.putExtra("pdf_filename", "dados_de_contacto.pdf");
                startActivity(intent);
            }
        });

        textView_FichaAsociarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar pdf de ficha para asociarse
                Intent intent = new Intent(RepositorioActivity.this, ShowPdfActivity.class);
                intent.putExtra("pdf_filename", "ficha_para_asociarse.pdf");
                startActivity(intent);
            }
        });

        textView_TripticoInformativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar pdf do triptico informativo
                Intent intent = new Intent(RepositorioActivity.this, ShowPdfActivity.class);
                intent.putExtra("pdf_filename", "triptico_informativo.pdf");
                startActivity(intent);
            }
        });

        textView_GuiaParaFamilias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar pdf da guia para familias
                Intent intent = new Intent(RepositorioActivity.this, ShowPdfActivity.class);
                intent.putExtra("pdf_filename", "guia_para_familias.pdf");
                startActivity(intent);
            }
        });
    }
}
