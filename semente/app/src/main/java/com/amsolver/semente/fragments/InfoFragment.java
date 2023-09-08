package com.amsolver.semente.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.amsolver.semente.MainActivity;
import com.amsolver.semente.R;
import com.amsolver.semente.activities.AutoriaActivity;
import com.amsolver.semente.activities.RepositorioActivity;
import com.amsolver.semente.activities.ShowPdfActivity;

import java.util.Objects;

public class InfoFragment extends Fragment {

    TextView textView_Web;
    TextView textView_Orixe;
    TextView textView_Repositorio;
    TextView textView_Autoria;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
        loadListeners();
    }

    public void loadViews(View view) {
        textView_Web = view.findViewById(R.id.textView_Web);
        textView_Orixe = view.findViewById(R.id.textView_Orixe);
        textView_Repositorio = view.findViewById(R.id.textView_Repositorio);
        textView_Autoria = view.findViewById(R.id.textView_Autoria);
    }

    public void loadListeners() {
        textView_Web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://corunha.semente.gal/"));
                startActivity(browserIntent);
            }
        });

        textView_Orixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar pdf de a nosa orixe
                Intent intent = new Intent(requireContext(), ShowPdfActivity.class);
                intent.putExtra("pdf_filename", "a_nosa_orixe.pdf");
                startActivity(intent);
            }
        });

        textView_Repositorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar el repositorio
                Intent intent = new Intent(requireContext(), RepositorioActivity.class);
                startActivity(intent);
            }
        });

        textView_Autoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar la autoría
                Intent intent = new Intent(requireContext(), AutoriaActivity.class);
                startActivity(intent);
            }
        });
    }

}
