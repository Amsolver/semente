package com.amsolver.semente.model;

import android.database.Cursor;

import com.amsolver.semente.utilities.ListUtilities;

import java.util.ArrayList;
import java.util.List;

public class Wordle {

    public int id;
    public int number;
    public String word;
    public int completed;

    public Wordle() {
    }

    public Wordle(int id, int number, String word, int completed) {
        this.id = id;
        this.number = number;
        this.word = word;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return getCompleted() == 1;
    }

    public List<Integer> checkWord(String word) {
        int TYPE_NOT_IN_WORD = 0;
        final int TYPE_IN_OTHER_POSITION = 1;
        final int TYPE_CORRECT = 2;
        List<Integer> result = new ArrayList<>();
        List<String> auxWrote = ListUtilities.getStringListFromCharArray(word.toCharArray());
        List<String> auxWordToGuess = ListUtilities.getStringListFromCharArray(getWord().toCharArray());
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
}
