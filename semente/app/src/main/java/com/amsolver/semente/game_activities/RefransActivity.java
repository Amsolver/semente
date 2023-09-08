package com.amsolver.semente.game_activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.amsolver.semente.R;
import com.amsolver.semente.dao.implementations.RefranDaoImp;
import com.amsolver.semente.model.Refran;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RefransActivity extends AppCompatActivity {

    ConstraintLayout clOneRefranResolved;
    ConstraintLayout clMain;

    ImageView ivBack;
    TextView tvRefran;
    TextView tvOptionAValue;
    TextView tvOptionBValue;
    TextView tvOptionCValue;
    TextView tvOptionDValue;
    CardView cvOptionA;
    CardView cvOptionB;
    CardView cvOptionC;
    CardView cvOptionD;
    TextView tvOptionATitle;
    TextView tvOptionBTitle;
    TextView tvOptionCTitle;
    TextView tvOptionDTitle;

    Refran refran;

    boolean anySelected = false;

    RefranDaoImp refranDaoImp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDaos();
        loadViews();
        loadListeners();
        loadRefran();
    }

    public void loadDaos() {
        refranDaoImp = new RefranDaoImp();
    }

    public void loadViews() {
        setContentView(R.layout.activity_refrans_layout);
        clOneRefranResolved = findViewById(R.id.clOneRefranResolved);
        clMain = findViewById(R.id.clMainGame);
        ivBack = findViewById(R.id.ivBack);
        tvRefran = findViewById(R.id.tvRefran);
        tvOptionAValue = findViewById(R.id.tvOptionAValue);
        tvOptionBValue = findViewById(R.id.tvOptionBValue);
        tvOptionCValue = findViewById(R.id.tvOptionCValue);
        tvOptionDValue = findViewById(R.id.tvOptionDValue);
        cvOptionA = findViewById(R.id.cvOptionA);
        cvOptionB = findViewById(R.id.cvOptionB);
        cvOptionC = findViewById(R.id.cvOptionC);
        cvOptionD = findViewById(R.id.cvOptionD);
        tvOptionATitle = findViewById(R.id.tvOptionATitle);
        tvOptionBTitle = findViewById(R.id.tvOptionBTitle);
        tvOptionCTitle = findViewById(R.id.tvOptionCTitle);
        tvOptionDTitle = findViewById(R.id.tvOptionDTitle);
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cvOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!anySelected) {
                    anySelected = true;
                    if (tvOptionAValue.getText().equals(refran.getOpcionCorrecta())) {
                        //Acierto
                        tvOptionATitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionAValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionA.setCardBackgroundColor(getApplicationContext().getColor(R.color.green));
                        String refranGuessed = refran.getRefran().replace("(", "").replace(")","");
                        tvRefran.setText(refranGuessed);
                        //Marcar como resuelto

                        showDialogLevelFinished(true);
                    } else {
                        //Fallo
                        tvOptionATitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionAValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionA.setCardBackgroundColor(getApplicationContext().getColor(R.color.red));
                        showDialogLevelFinished(false);
                    }
                }
            }
        });

        cvOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!anySelected) {
                    anySelected = true;
                    if (tvOptionBValue.getText().equals(refran.getOpcionCorrecta())) {
                        //Acierto
                        tvOptionBTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionBValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionB.setCardBackgroundColor(getApplicationContext().getColor(R.color.green));
                        String refranGuessed = refran.getRefran().replace("(", "").replace(")","");
                        tvRefran.setText(refranGuessed);
                        showDialogLevelFinished(true);
                    } else {
                        //Fallo
                        tvOptionBTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionBValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionB.setCardBackgroundColor(getApplicationContext().getColor(R.color.red));
                        showDialogLevelFinished(false);
                    }
                }
            }
        });

        cvOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!anySelected) {
                    anySelected = true;
                    if (tvOptionCValue.getText().equals(refran.getOpcionCorrecta())) {
                        //Acierto
                        tvOptionCTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionCValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionC.setCardBackgroundColor(getApplicationContext().getColor(R.color.green));
                        String refranGuessed = refran.getRefran().replace("(", "").replace(")","");
                        tvRefran.setText(refranGuessed);
                        showDialogLevelFinished(true);
                    } else {
                        //Fallo
                        tvOptionCTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionCValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionC.setCardBackgroundColor(getApplicationContext().getColor(R.color.red));
                        showDialogLevelFinished(false);
                    }
                }

            }
        });

        cvOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!anySelected) {
                    anySelected = true;
                    if (tvOptionDValue.getText().equals(refran.getOpcionCorrecta())) {
                        //Acierto
                        tvOptionDTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionDValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionD.setCardBackgroundColor(getApplicationContext().getColor(R.color.green));
                        String refranGuessed = refran.getRefran().replace("(", "").replace(")","");
                        tvRefran.setText(refranGuessed);
                        showDialogLevelFinished(true);
                    } else {
                        //Fallo
                        tvOptionDTitle.setTextColor(getApplicationContext().getColor(R.color.white));
                        tvOptionDValue.setTextColor(getApplicationContext().getColor(R.color.white));
                        cvOptionD.setCardBackgroundColor(getApplicationContext().getColor(R.color.red));
                        showDialogLevelFinished(false);
                    }
                }
            }
        });
    }

    public void loadRefran() {
         if (refranDaoImp.anyRefranResolvedToday(getApplicationContext())) {
                clMain.setVisibility(View.GONE);
                clOneRefranResolved.setVisibility(View.VISIBLE);
         } else  {
             clMain.setVisibility(View.VISIBLE);
             clOneRefranResolved.setVisibility(View.GONE);
             refran = refranDaoImp.getRefranToPlay(getApplicationContext());
             if (refran != null) {
                 tvRefran.setText(refran.getRefranToShow());
                 List<String> opciones = getRandomOrderedOptions();
                 tvOptionAValue.setText(opciones.get(0));
                 tvOptionBValue.setText(opciones.get(1));
                 tvOptionCValue.setText(opciones.get(2));
                 tvOptionDValue.setText(opciones.get(3));
             }
         }
    }

    public List<String> getRandomOrderedOptions() {
        List<String> opciones = new ArrayList<>();
        opciones.add(refran.getOpcionCorrecta());
        opciones.add(refran.getOpcion2());
        opciones.add(refran.getOpcion3());
        opciones.add(refran.getOpcion4());
        // Mezcla aleatoriamente los elementos de la lista
        Collections.shuffle(opciones);
        return opciones;
    }

    public void retry() {
        tvOptionATitle.setTextColor(getApplicationContext().getColor(R.color.blue));
        tvOptionAValue.setTextColor(getApplicationContext().getColor(R.color.blue));
        cvOptionA.setCardBackgroundColor(getApplicationContext().getColor(R.color.white));

        tvOptionBTitle.setTextColor(getApplicationContext().getColor(R.color.blue));
        tvOptionBValue.setTextColor(getApplicationContext().getColor(R.color.blue));
        cvOptionB.setCardBackgroundColor(getApplicationContext().getColor(R.color.white));

        tvOptionCTitle.setTextColor(getApplicationContext().getColor(R.color.blue));
        tvOptionCValue.setTextColor(getApplicationContext().getColor(R.color.blue));
        cvOptionC.setCardBackgroundColor(getApplicationContext().getColor(R.color.white));

        tvOptionDTitle.setTextColor(getApplicationContext().getColor(R.color.blue));
        tvOptionDValue.setTextColor(getApplicationContext().getColor(R.color.blue));
        cvOptionD.setCardBackgroundColor(getApplicationContext().getColor(R.color.white));
        anySelected = false;


    }

    public void showDialogLevelFinished(boolean win) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(RefransActivity.this, R.style.DialogStyle);
        bottomSheetDialog.setCancelable(true);
        LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = li.inflate(R.layout.level_finished_layout, null);

        if (win) {
            refranDaoImp.markARefranAsCompleted(getApplicationContext(), refran.getNumber());
            vista.findViewById(R.id.llWin).setVisibility(View.VISIBLE);
            vista.findViewById(R.id.llLoose).setVisibility(View.GONE);
            //TODO: engadir puntos conseguidos e totales
            vista.findViewById(R.id.ivRestart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bottomSheetDialog.dismiss();
                }
            });

            vista.findViewById(R.id.ivNext).setVisibility(View.GONE);

        } else {
            vista.findViewById(R.id.llLoose).setVisibility(View.VISIBLE);
            vista.findViewById(R.id.llWin).setVisibility(View.GONE);
            //TODO: engadir puntos conseguidos e totales
            vista.findViewById(R.id.ivRestartLoose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retry();
                    bottomSheetDialog.dismiss();
                }
            });
        }

        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(vista);
        bottomSheetDialog.show();
    }

}
