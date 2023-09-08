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

import com.amsolver.semente.R;
import com.amsolver.semente.dao.implementations.WordleDaoImp;
import com.amsolver.semente.model.Wordle;
import com.amsolver.semente.utilities.ListUtilities;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class WordleActivity extends AppCompatActivity {

    TextView tvPanelControlTitle;
    ImageView ivBack;
    CardView letterBox11,letterBox12,letterBox13,letterBox14,letterBox15,letterBox21,letterBox22,letterBox23,letterBox24,letterBox25,letterBox31,letterBox32,letterBox33,letterBox34,letterBox35,letterBox41,letterBox42,letterBox43,letterBox44,letterBox45,letterBox51,letterBox52,letterBox53,letterBox54,letterBox55,letterBox61,letterBox62,letterBox63,letterBox64,letterBox65;
    TextView tvBox11,tvBox12,tvBox13,tvBox14,tvBox15,tvBox21,tvBox22,tvBox23,tvBox24,tvBox25,tvBox31,tvBox32,tvBox33,tvBox34,tvBox35,tvBox41,tvBox42,tvBox43,tvBox44,tvBox45,tvBox51,tvBox52,tvBox53,tvBox54,tvBox55,tvBox61,tvBox62,tvBox63,tvBox64,tvBox65;
    CardView cvQ,cvE,cvR,cvT,cvU,cvI,cvO,cvP,cvA,cvS,cvD,cvF,cvG,cvH,cvL,cvÑ,cvZ,cvX,cvC,cvV,cvB,cvN,cvM,cvDelete,cvCheck;


    public final int TYPE_NOT_IN_WORD = 0;
    public final int TYPE_IN_OTHER_POSITION = 1;
    public final int TYPE_CORRECT = 2;

    int level;

    int rowSelected = 1;
    String wrote = "";
    String wordToGuess = "";


    WordleDaoImp wordleDaoImp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDaos();
        level = getIntent().getIntExtra("level", -1);
        getLevelWord(level);
        loadViews();
        setLevelInTitle();
        loadListeners();

    }

    public void loadDaos() {
        wordleDaoImp = new WordleDaoImp();
    }

    public void loadViews() {
        setContentView(R.layout.activity_wordle_layout);
        tvPanelControlTitle = findViewById(R.id.tvPanelControlTitle);
        ivBack = findViewById(R.id.ivBack);
        //Grid Row 1
        letterBox11 = findViewById(R.id.letterBox11);
        letterBox12 = findViewById(R.id.letterBox12);
        letterBox13 = findViewById(R.id.letterBox13);
        letterBox14 = findViewById(R.id.letterBox14);
        letterBox15 = findViewById(R.id.letterBox15);

        //Grid Row 2
        letterBox21 = findViewById(R.id.letterBox21);
        letterBox22 = findViewById(R.id.letterBox22);
        letterBox23 = findViewById(R.id.letterBox23);
        letterBox24 = findViewById(R.id.letterBox24);
        letterBox25 = findViewById(R.id.letterBox25);

        //Grid Row 3
        letterBox31 = findViewById(R.id.letterBox31);
        letterBox32 = findViewById(R.id.letterBox32);
        letterBox33 = findViewById(R.id.letterBox33);
        letterBox34 = findViewById(R.id.letterBox34);
        letterBox35 = findViewById(R.id.letterBox35);

        //Grid Row 4
        letterBox41 = findViewById(R.id.letterBox41);
        letterBox42 = findViewById(R.id.letterBox42);
        letterBox43 = findViewById(R.id.letterBox43);
        letterBox44 = findViewById(R.id.letterBox44);
        letterBox45 = findViewById(R.id.letterBox45);

        //Grid Row 5
        letterBox51 = findViewById(R.id.letterBox51);
        letterBox52 = findViewById(R.id.letterBox52);
        letterBox53 = findViewById(R.id.letterBox53);
        letterBox54 = findViewById(R.id.letterBox54);
        letterBox55 = findViewById(R.id.letterBox55);

        //Grid Row 6
        letterBox61 = findViewById(R.id.letterBox61);
        letterBox62 = findViewById(R.id.letterBox62);
        letterBox63 = findViewById(R.id.letterBox63);
        letterBox64 = findViewById(R.id.letterBox64);
        letterBox65 = findViewById(R.id.letterBox65);

        //Grid Row 1 Text
        tvBox11 = findViewById(R.id.tvBox11);
        tvBox12 = findViewById(R.id.tvBox12);
        tvBox13 = findViewById(R.id.tvBox13);
        tvBox14 = findViewById(R.id.tvBox14);
        tvBox15 = findViewById(R.id.tvBox15);

        //Grid Row 2 Text
        tvBox21 = findViewById(R.id.tvBox21);
        tvBox22 = findViewById(R.id.tvBox22);
        tvBox23 = findViewById(R.id.tvBox23);
        tvBox24 = findViewById(R.id.tvBox24);
        tvBox25 = findViewById(R.id.tvBox25);

        //Grid Row 3 Text
        tvBox31 = findViewById(R.id.tvBox31);
        tvBox32 = findViewById(R.id.tvBox32);
        tvBox33 = findViewById(R.id.tvBox33);
        tvBox34 = findViewById(R.id.tvBox34);
        tvBox35 = findViewById(R.id.tvBox35);

        //Grid Row 4 Text
        tvBox41 = findViewById(R.id.tvBox41);
        tvBox42 = findViewById(R.id.tvBox42);
        tvBox43 = findViewById(R.id.tvBox43);
        tvBox44 = findViewById(R.id.tvBox44);
        tvBox45 = findViewById(R.id.tvBox45);

        //Grid Row 5 Text
        tvBox51 = findViewById(R.id.tvBox51);
        tvBox52 = findViewById(R.id.tvBox52);
        tvBox53 = findViewById(R.id.tvBox53);
        tvBox54 = findViewById(R.id.tvBox54);
        tvBox55 = findViewById(R.id.tvBox55);

        //Grid Row 6 Text
        tvBox61 = findViewById(R.id.tvBox61);
        tvBox62 = findViewById(R.id.tvBox62);
        tvBox63 = findViewById(R.id.tvBox63);
        tvBox64 = findViewById(R.id.tvBox64);
        tvBox65 = findViewById(R.id.tvBox65);

        //Keyboard letters
        cvQ = findViewById(R.id.cvQ);
        cvE = findViewById(R.id.cvE);
        cvR = findViewById(R.id.cvR);
        cvT = findViewById(R.id.cvT);
        cvU = findViewById(R.id.cvU);
        cvI = findViewById(R.id.cvI);
        cvO = findViewById(R.id.cvO);
        cvP = findViewById(R.id.cvP);
        cvA = findViewById(R.id.cvA);
        cvS = findViewById(R.id.cvS);
        cvD = findViewById(R.id.cvD);
        cvF = findViewById(R.id.cvF);
        cvG = findViewById(R.id.cvG);
        cvH = findViewById(R.id.cvH);
        cvL = findViewById(R.id.cvL);
        cvÑ = findViewById(R.id.cvÑ);
        cvZ = findViewById(R.id.cvZ);
        cvX = findViewById(R.id.cvX);
        cvC = findViewById(R.id.cvC);
        cvV = findViewById(R.id.cvV);
        cvB = findViewById(R.id.cvB);
        cvN = findViewById(R.id.cvN);
        cvM = findViewById(R.id.cvM);
        cvDelete = findViewById(R.id.cvDelete);
        cvCheck = findViewById(R.id.cvCheck);

    }

    public void setLevelInTitle() {
        tvPanelControlTitle.setText("Nivel " + level);
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        cvQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("Q");
            }
        });

        cvE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("E");
            }
        });

        cvR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("R");
            }
        });

        cvT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("T");
            }
        });

        cvU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("U");
            }
        });

        cvI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("I");
            }
        });

        cvO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("O");
            }
        });

        cvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("P");
            }
        });

        cvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("A");
            }
        });

        cvS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("S");
            }
        });

        cvD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("D");
            }
        });

        cvF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("F");
            }
        });

        cvG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("G");
            }
        });

        cvH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("H");
            }
        });

        cvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("L");
            }
        });

        cvÑ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("Ñ");
            }
        });

        cvZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("Z");
            }
        });

        cvX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("X");
            }
        });

        cvC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("C");
            }
        });

        cvV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("V");
            }
        });

        cvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("B");
            }
        });

        cvN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("N");
            }
        });

        cvM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeLetter("M");
            }
        });

        cvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLetter();
            }
        });

        cvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wrote.length() == 5) {
                    setColorByCheck(checkWord());
                }
            }
        });

    }

    public void getLevelWord(int numberLevel) {
        Wordle wordle = wordleDaoImp.getLevelByNumber(getApplicationContext(), numberLevel);
        wordToGuess = wordle.getWord();
    }

    public List<Integer> checkWord() {
        List<Integer> result = new ArrayList<>();
        List<String> auxWrote = ListUtilities.getStringListFromCharArray(wrote.toCharArray());
        List<String> auxWordToGuess = ListUtilities.getStringListFromCharArray(wordToGuess.toCharArray());
        List<String> auxChecked = new ArrayList<>();
        result.add(TYPE_NOT_IN_WORD);
        result.add(TYPE_NOT_IN_WORD);
        result.add(TYPE_NOT_IN_WORD);
        result.add(TYPE_NOT_IN_WORD);
        result.add(TYPE_NOT_IN_WORD);

        //Primero buscamos los aciertos
        for (int i = 0; i<auxWrote.size(); i++) {
            if(auxWrote.get(i).equals(auxWordToGuess.get(i))) {
                result.set(i, TYPE_CORRECT);
                auxChecked.add(auxWrote.get(i));
            }
        }

        //Ahora buscamos las posiciones mal colocadas y letras no presentes
        for (int i = 0; i<auxWrote.size(); i++) {
            if (!auxWrote.get(i).equals(auxWordToGuess.get(i))) {
                auxChecked.add(auxWrote.get(i));
                if (auxWordToGuess.contains(auxWrote.get(i))) {
                    if (ListUtilities.howManyTimesInList(auxChecked, auxWrote.get(i)) <= ListUtilities.howManyTimesInList(auxWordToGuess, auxWrote.get(i))) {
                        result.set(i, TYPE_IN_OTHER_POSITION);
                    } else {
                        result.set(i, TYPE_NOT_IN_WORD);
                    }
                } else {
                    result.set(i, TYPE_NOT_IN_WORD);
                }
            }
        }

        return result;
    }

    public void writeLetter(String letter) {
        if (wrote.length() <= 4) {
            wrote += letter;
            if (rowSelected == 1) {
                if (wrote.length() == 1) {
                    tvBox11.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox12.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox13.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox14.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox15.setText(letter);
                }
            } else if (rowSelected == 2) {
                if (wrote.length() == 1) {
                    tvBox21.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox22.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox23.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox24.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox25.setText(letter);
                }
            } else if (rowSelected == 3) {
                if (wrote.length() == 1) {
                    tvBox31.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox32.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox33.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox34.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox35.setText(letter);
                }
            } else if (rowSelected == 4) {
                if (wrote.length() == 1) {
                    tvBox41.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox42.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox43.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox44.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox45.setText(letter);
                }
            } else if (rowSelected == 5) {
                if (wrote.length() == 1) {
                    tvBox51.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox52.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox53.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox54.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox55.setText(letter);
                }
            }  else if (rowSelected == 6) {
                if (wrote.length() == 1) {
                    tvBox61.setText(letter);
                } else if (wrote.length() == 2) {
                    tvBox62.setText(letter);
                } else if (wrote.length() == 3) {
                    tvBox63.setText(letter);
                } else if (wrote.length() == 4) {
                    tvBox64.setText(letter);
                } else if (wrote.length() == 5) {
                    tvBox65.setText(letter);
                }
            }

        }
    }

    public void deleteLetter() {
        if (wrote.length() > 0) {
            wrote = wrote.substring(0, wrote.length() - 1);
            if (rowSelected == 1) {
                if (wrote.length() == 0) {
                    tvBox11.setText("");
                } else if (wrote.length() == 1) {
                    tvBox12.setText("");
                } else if (wrote.length() == 2) {
                    tvBox13.setText("");
                } else if (wrote.length() == 3) {
                    tvBox14.setText("");
                } else if (wrote.length() == 4) {
                    tvBox15.setText("");
                }
            } else if (rowSelected == 2) {
                if (wrote.length() == 0) {
                    tvBox21.setText("");
                } else if (wrote.length() == 1) {
                    tvBox22.setText("");
                } else if (wrote.length() == 2) {
                    tvBox23.setText("");
                } else if (wrote.length() == 3) {
                    tvBox24.setText("");
                } else if (wrote.length() == 4) {
                    tvBox25.setText("");
                }
            } else if (rowSelected == 3) {
                if (wrote.length() == 0) {
                    tvBox31.setText("");
                } else if (wrote.length() == 1) {
                    tvBox32.setText("");
                } else if (wrote.length() == 2) {
                    tvBox33.setText("");
                } else if (wrote.length() == 3) {
                    tvBox34.setText("");
                } else if (wrote.length() == 4) {
                    tvBox35.setText("");
                }
            } else if (rowSelected == 4) {
                if (wrote.length() == 0) {
                    tvBox41.setText("");
                } else if (wrote.length() == 1) {
                    tvBox42.setText("");
                } else if (wrote.length() == 2) {
                    tvBox43.setText("");
                } else if (wrote.length() == 3) {
                    tvBox44.setText("");
                } else if (wrote.length() == 4) {
                    tvBox45.setText("");
                }
            } else if (rowSelected == 5) {
                if (wrote.length() == 0) {
                    tvBox51.setText("");
                } else if (wrote.length() == 1) {
                    tvBox52.setText("");
                } else if (wrote.length() == 2) {
                    tvBox53.setText("");
                } else if (wrote.length() == 3) {
                    tvBox54.setText("");
                } else if (wrote.length() == 4) {
                    tvBox55.setText("");
                }
            }  else if (rowSelected == 6) {
                if (wrote.length() == 0) {
                    tvBox61.setText("");
                } else if (wrote.length() == 1) {
                    tvBox62.setText("");
                } else if (wrote.length() == 2) {
                    tvBox63.setText("");
                } else if (wrote.length() == 3) {
                    tvBox64.setText("");
                } else if (wrote.length() == 4) {
                    tvBox65.setText("");
                }
            }

        }
    }

    public void setColorByCheck(List<Integer> checked) {
        if (checked.size() == 5) {
            if (rowSelected == 1) {
                setUpColorByLetter(letterBox11, checked.get(0));
                setUpColorByLetter(letterBox12, checked.get(1));
                setUpColorByLetter(letterBox13, checked.get(2));
                setUpColorByLetter(letterBox14, checked.get(3));
                setUpColorByLetter(letterBox15, checked.get(4));
            } else if (rowSelected == 2) {
                setUpColorByLetter(letterBox21, checked.get(0));
                setUpColorByLetter(letterBox22, checked.get(1));
                setUpColorByLetter(letterBox23, checked.get(2));
                setUpColorByLetter(letterBox24, checked.get(3));
                setUpColorByLetter(letterBox25, checked.get(4));
            } else if (rowSelected == 3) {
                setUpColorByLetter(letterBox31, checked.get(0));
                setUpColorByLetter(letterBox32, checked.get(1));
                setUpColorByLetter(letterBox33, checked.get(2));
                setUpColorByLetter(letterBox34, checked.get(3));
                setUpColorByLetter(letterBox35, checked.get(4));
            } else if (rowSelected == 4) {
                setUpColorByLetter(letterBox41, checked.get(0));
                setUpColorByLetter(letterBox42, checked.get(1));
                setUpColorByLetter(letterBox43, checked.get(2));
                setUpColorByLetter(letterBox44, checked.get(3));
                setUpColorByLetter(letterBox45, checked.get(4));
            } else if (rowSelected == 5) {
                setUpColorByLetter(letterBox51, checked.get(0));
                setUpColorByLetter(letterBox52, checked.get(1));
                setUpColorByLetter(letterBox53, checked.get(2));
                setUpColorByLetter(letterBox54, checked.get(3));
                setUpColorByLetter(letterBox55, checked.get(4));
            }  else if (rowSelected == 6) {
                setUpColorByLetter(letterBox61, checked.get(0));
                setUpColorByLetter(letterBox62, checked.get(1));
                setUpColorByLetter(letterBox63, checked.get(2));
                setUpColorByLetter(letterBox64, checked.get(3));
                setUpColorByLetter(letterBox65, checked.get(4));
            }
            if (isCorrectWord(checked)) {
                //Acabaría el juego ganando
                showDialogLevelFinished(true);
            } else {
                if (rowSelected < 6) {
                    rowSelected ++;
                    wrote = "";
                } else {
                    showDialogLevelFinished(false);
                }
            }

        }
    }

    public void setUpColorByLetter(View view, int type) {

        if (type == TYPE_CORRECT) {
            view.setBackgroundColor(getColor(R.color.green));
        } else if (type == TYPE_IN_OTHER_POSITION) {
            view.setBackgroundColor(getColor(R.color.yellow));
        } else if (type == TYPE_NOT_IN_WORD) {
            view.setBackgroundColor(getColor(R.color.goodgrey));
        }
    }

    public boolean isCorrectWord(List<Integer> checked) {
        for (int type: checked) {
            if (type != TYPE_CORRECT) {
                return false;
            }
        }
        return true;
    }

    public void deleteAllLetters() {
        tvBox11.setText("");
        tvBox12.setText("");
        tvBox13.setText("");
        tvBox14.setText("");
        tvBox15.setText("");

        tvBox21.setText("");
        tvBox22.setText("");
        tvBox23.setText("");
        tvBox24.setText("");
        tvBox25.setText("");

        tvBox31.setText("");
        tvBox32.setText("");
        tvBox33.setText("");
        tvBox34.setText("");
        tvBox35.setText("");

        tvBox41.setText("");
        tvBox42.setText("");
        tvBox43.setText("");
        tvBox44.setText("");
        tvBox45.setText("");

        tvBox51.setText("");
        tvBox52.setText("");
        tvBox53.setText("");
        tvBox54.setText("");
        tvBox55.setText("");

        tvBox61.setText("");
        tvBox62.setText("");
        tvBox63.setText("");
        tvBox64.setText("");
        tvBox65.setText("");
    }

    public void restartAllCardViews() {
        letterBox11.setBackgroundColor(getColor(R.color.white));
        letterBox12.setBackgroundColor(getColor(R.color.white));
        letterBox13.setBackgroundColor(getColor(R.color.white));
        letterBox14.setBackgroundColor(getColor(R.color.white));
        letterBox15.setBackgroundColor(getColor(R.color.white));

        letterBox21.setBackgroundColor(getColor(R.color.white));
        letterBox22.setBackgroundColor(getColor(R.color.white));
        letterBox23.setBackgroundColor(getColor(R.color.white));
        letterBox24.setBackgroundColor(getColor(R.color.white));
        letterBox25.setBackgroundColor(getColor(R.color.white));

        letterBox31.setBackgroundColor(getColor(R.color.white));
        letterBox32.setBackgroundColor(getColor(R.color.white));
        letterBox33.setBackgroundColor(getColor(R.color.white));
        letterBox34.setBackgroundColor(getColor(R.color.white));
        letterBox35.setBackgroundColor(getColor(R.color.white));

        letterBox41.setBackgroundColor(getColor(R.color.white));
        letterBox42.setBackgroundColor(getColor(R.color.white));
        letterBox43.setBackgroundColor(getColor(R.color.white));
        letterBox44.setBackgroundColor(getColor(R.color.white));
        letterBox45.setBackgroundColor(getColor(R.color.white));

        letterBox51.setBackgroundColor(getColor(R.color.white));
        letterBox52.setBackgroundColor(getColor(R.color.white));
        letterBox53.setBackgroundColor(getColor(R.color.white));
        letterBox54.setBackgroundColor(getColor(R.color.white));
        letterBox55.setBackgroundColor(getColor(R.color.white));

        letterBox61.setBackgroundColor(getColor(R.color.white));
        letterBox62.setBackgroundColor(getColor(R.color.white));
        letterBox63.setBackgroundColor(getColor(R.color.white));
        letterBox64.setBackgroundColor(getColor(R.color.white));
        letterBox65.setBackgroundColor(getColor(R.color.white));
    }

    public void showDialogLevelFinished(boolean win) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(WordleActivity.this, R.style.DialogStyle);
        bottomSheetDialog.setCancelable(true);
        LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = li.inflate(R.layout.level_finished_layout, null);

        if (win) {
            //Marcar como compeltado
            wordleDaoImp.markLevelAsCompleted(getApplicationContext(), level);
            vista.findViewById(R.id.llWin).setVisibility(View.VISIBLE);
            vista.findViewById(R.id.llLoose).setVisibility(View.GONE);
            //TODO: engadir puntos conseguidos e totales
            vista.findViewById(R.id.ivRestart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rowSelected = 1;
                    wrote = "";
                    deleteAllLetters();
                    restartAllCardViews();
                    bottomSheetDialog.dismiss();
                }
            });

            vista.findViewById(R.id.ivNext).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Actualizar vista y cargar nuevo nivel
                    rowSelected = 1;
                    wrote = "";
                    deleteAllLetters();
                    restartAllCardViews();
                    level++;
                    Wordle nextLev = wordleDaoImp.getLevelByNumber(getApplicationContext(), level);
                    if (nextLev != null) {
                        setLevelInTitle();
                        wordToGuess = nextLev.getWord();
                    }
                    bottomSheetDialog.dismiss();
                }
            });

        } else {
            vista.findViewById(R.id.llLoose).setVisibility(View.VISIBLE);
            vista.findViewById(R.id.llWin).setVisibility(View.GONE);
            //TODO: engadir puntos conseguidos e totales
            vista.findViewById(R.id.ivRestartLoose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rowSelected = 1;
                    wrote = "";
                    deleteAllLetters();
                    restartAllCardViews();
                    bottomSheetDialog.dismiss();
                }
            });
        }

        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(vista);
        bottomSheetDialog.show();
    }
}
