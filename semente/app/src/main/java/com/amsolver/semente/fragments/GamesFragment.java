package com.amsolver.semente.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.amsolver.semente.R;
import com.amsolver.semente.activities.SelectEmocionsLevelActivity;
import com.amsolver.semente.activities.SelectWordleLevelActivity;
import com.amsolver.semente.game_activities.RefransActivity;

public class GamesFragment extends Fragment {

    CardView cvWordle;
    CardView cvRefrans;
    CardView cvEmocions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
        loadListeners();
    }

    public void loadViews(View view) {
        cvWordle = view.findViewById(R.id.cvWordle);
        cvRefrans = view.findViewById(R.id.cvRefrans);
        cvEmocions = view.findViewById(R.id.cvEmocions);
    }

    public void loadListeners() {
        cvWordle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectWordleLevelActivity.class);
                requireContext().startActivity(intent);
            }
        });

        cvRefrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RefransActivity.class);
                requireContext().startActivity(intent);
            }
        });

        cvEmocions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectEmocionsLevelActivity.class);
                requireContext().startActivity(intent);
            }
        });
    }
}
