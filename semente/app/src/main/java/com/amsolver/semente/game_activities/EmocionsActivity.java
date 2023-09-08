package com.amsolver.semente.game_activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amsolver.semente.R;
import com.amsolver.semente.dao.implementations.EmocionDaoImp;
import com.amsolver.semente.dao.implementations.ImageDaoImp;
import com.amsolver.semente.model.Emocion;
import com.amsolver.semente.model.Image;
import com.amsolver.semente.utilities.LetterUtilities;
import com.amsolver.semente.utilities.ListUtilities;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EmocionsActivity extends AppCompatActivity {

    int level;
    String emocionToGuess;

    TextView tvPanelControlTitle;
    ImageView ivBack;
    List<String> lettersToGuess;
    List<String> wrote;

    ImageView ivImage1;
    ImageView ivImage2;
    ImageView ivImage3;
    ImageView ivImage4;

    TextView tvLetter1;
    TextView tvLetter2;
    TextView tvLetter3;
    TextView tvLetter4;
    TextView tvLetter5;
    TextView tvLetter6;

    TextView tvWrote;

    ImageView ivDelete;

    EmocionDaoImp emocionDaoImp;
    ImageDaoImp imageDaoImp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDaos();
        level = getIntent().getIntExtra("level", -1);
        getLevelEmocion(level);
        loadViews();
        setLevelInTitle();
        loadImages();
        loadTextViews();
        loadListeners();
    }

    public void loadDaos() {
        emocionDaoImp = new EmocionDaoImp();
        imageDaoImp = new ImageDaoImp();
    }

    public void getLevelEmocion(int level) {
        wrote = new ArrayList<>();
        emocionToGuess = emocionDaoImp.getLevelByNumber(getApplicationContext(), level).getEmocion();
        lettersToGuess = ListUtilities.getStringListFromCharArray(emocionToGuess.toCharArray());
    }

    @SuppressLint("ClickableViewAccessibility")
    public void loadViews() {
        setContentView(R.layout.activity_emocions_layout);
        tvPanelControlTitle = findViewById(R.id.tvPanelControlTitle);
        ivBack = findViewById(R.id.ivBack);

        //Images
        ivImage1 = findViewById(R.id.ivImage1);
        ivImage2 = findViewById(R.id.ivImage2);
        ivImage3 = findViewById(R.id.ivImage3);
        ivImage4 = findViewById(R.id.ivImage4);

        //TextViews
        tvLetter1 = findViewById(R.id.tvLetter1);
        tvLetter2 = findViewById(R.id.tvLetter2);
        tvLetter3 = findViewById(R.id.tvLetter3);
        tvLetter4 = findViewById(R.id.tvLetter4);
        tvLetter5 = findViewById(R.id.tvLetter5);
        tvLetter6 = findViewById(R.id.tvLetter6);

        tvWrote = findViewById(R.id.tvWrote);

        ivDelete = findViewById(R.id.ivDelete);
    }

    public void setLevelInTitle() {
        tvPanelControlTitle.setText("Nivel " + level);
    }

    public void loadImages() {
        List<Image> images = imageDaoImp.getImagesForEmocionsLevel(getApplicationContext(), emocionToGuess);
        if (images.size() > 0) {
            byte[] imageBytes = images.get(0).getImage(); // Reemplaza con tu lógica para obtener los bytes de la base de datos
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ivImage1.setImageBitmap(bitmap);
        }

        if (images.size() > 1) {
            byte[] imageBytes = images.get(1).getImage(); // Reemplaza con tu lógica para obtener los bytes de la base de datos
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ivImage2.setImageBitmap(bitmap);
        }

        if (images.size() > 2) {
            byte[] imageBytes = images.get(2).getImage(); // Reemplaza con tu lógica para obtener los bytes de la base de datos
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ivImage3.setImageBitmap(bitmap);
        }

        if (images.size() > 3) {
            byte[] imageBytes = images.get(3).getImage(); // Reemplaza con tu lógica para obtener los bytes de la base de datos
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ivImage4.setImageBitmap(bitmap);
        }
    }

    public void loadTextViews() {
        unselectAllTvs();
        //Máximo 6 letras
        List<String> lettersRandomOrder = new ArrayList<>();
        if (lettersToGuess.size() == 6) {
            lettersRandomOrder.addAll(lettersToGuess);
            //Orden aleatorio
        } else {
            //Es menor
            lettersRandomOrder.addAll(lettersToGuess);
            while (lettersRandomOrder.size() < 6) {
                lettersRandomOrder.add(LetterUtilities.getRandomLetter());
            }
        }
        Collections.shuffle(lettersRandomOrder);
        tvLetter1.setText(lettersRandomOrder.get(0).toUpperCase());
        tvLetter2.setText(lettersRandomOrder.get(1).toUpperCase());
        tvLetter3.setText(lettersRandomOrder.get(2).toUpperCase());
        tvLetter4.setText(lettersRandomOrder.get(3).toUpperCase());
        tvLetter5.setText(lettersRandomOrder.get(4).toUpperCase());
        tvLetter6.setText(lettersRandomOrder.get(5).toUpperCase());
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        tvLetter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter1);
            }
        });

        tvLetter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter2);
            }
        });

        tvLetter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter3);
            }
        });

        tvLetter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter4);
            }
        });

        tvLetter5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter5);
            }
        });

        tvLetter6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLetter(tvLetter6);
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLetters();
            }
        });
    }

    public boolean isCorrect() {
        if (lettersToGuess.size() != wrote.size()) {
            return false;
        } else {
            for (int i=0; i< lettersToGuess.size(); i++) {
                if (!compareStrings(lettersToGuess.get(i), wrote.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean compareStrings(String str1, String str2) {
        str1 = str1.toLowerCase(Locale.ROOT);
        str2 = str2.toLowerCase(Locale.ROOT);

        String normalizedStr1 = Normalizer.normalize(str1, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        String normalizedStr2 = Normalizer.normalize(str2, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return normalizedStr1.equals(normalizedStr2);
    }


    public void deleteLetters() {
        wrote.clear();
        updateTvWrote();
        unselectAllTvs();
    }

    public void unselectAllTvs() {
        tvLetter1.setTextColor(getColor(R.color.colorPrimary));
        tvLetter1.setBackgroundResource(R.drawable.circle_white);
        tvLetter2.setTextColor(getColor(R.color.colorPrimary));
        tvLetter2.setBackgroundResource(R.drawable.circle_white);
        tvLetter3.setTextColor(getColor(R.color.colorPrimary));
        tvLetter3.setBackgroundResource(R.drawable.circle_white);
        tvLetter4.setTextColor(getColor(R.color.colorPrimary));
        tvLetter4.setBackgroundResource(R.drawable.circle_white);
        tvLetter5.setTextColor(getColor(R.color.colorPrimary));
        tvLetter5.setBackgroundResource(R.drawable.circle_white);
        tvLetter6.setTextColor(getColor(R.color.colorPrimary));
        tvLetter6.setBackgroundResource(R.drawable.circle_white);
    }

    public void updateTvWrote() {
        if (wrote.size() > 0) {
            tvWrote.setVisibility(View.VISIBLE);
            tvWrote.setText(getWroteLikeString());
        } else {
            tvWrote.setVisibility(View.INVISIBLE);
        }
    }

    public String getWroteLikeString() {
        String result = "";
        for (String s: wrote) {
            result += s;
        }
        return result;
    }

    public void selectLetter(TextView tv) {
        boolean isSelected = tv.getCurrentTextColor() != getColor(R.color.colorPrimary);
        if (!isSelected) {
            tv.setTextColor(getColor(R.color.white));
            tv.setBackgroundResource(R.drawable.circle_color_primary);
            wrote.add(tv.getText().toString());
            if (isCorrect()) {
                showDialogLevelFinished(true);
            }
        }
        updateTvWrote();
    }



    public void showDialogLevelFinished(boolean win) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EmocionsActivity.this, R.style.DialogStyle);
        bottomSheetDialog.setCancelable(true);
        LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = li.inflate(R.layout.level_finished_layout, null);

        if (win) {
            //Marcar como compeltado
            emocionDaoImp.markLevelAsCompleted(getApplicationContext(), level);
            vista.findViewById(R.id.llWin).setVisibility(View.VISIBLE);
            vista.findViewById(R.id.llLoose).setVisibility(View.GONE);
            //TODO: engadir puntos conseguidos e totales
            vista.findViewById(R.id.ivRestart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wrote.clear();
                    updateTvWrote();
                    loadTextViews();
                    bottomSheetDialog.dismiss();
                }
            });

            vista.findViewById(R.id.ivNext).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Actualizar vista y cargar nuevo nivel
                    wrote.clear();
                    updateTvWrote();
                    level++;
                    Emocion nextLev = emocionDaoImp.getLevelByNumber(getApplicationContext(), level);
                    if (nextLev != null) {
                        setLevelInTitle();
                        emocionToGuess = nextLev.getEmocion();
                        lettersToGuess = ListUtilities.getStringListFromCharArray(emocionToGuess.toCharArray());
                        loadImages();
                        loadTextViews();
                    }
                    bottomSheetDialog.dismiss();
                }
            });

        }

        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(vista);
        bottomSheetDialog.show();
    }
}
