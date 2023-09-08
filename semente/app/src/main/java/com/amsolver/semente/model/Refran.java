package com.amsolver.semente.model;

import java.util.Date;

public class Refran {

    public int id;
    public int number;
    public String refran;
    public String opcionCorrecta;
    public String opcion2;
    public String opcion3;
    public String opcion4;
    public int completed;
    public Date dateCompleted;

    public Refran() {
    }

    public Refran(int number, String refran, String opcionCorrecta, String opcion2, String opcion3, String opcion4, int completed, Date dateCompleted) {
        this.number = number;
        this.refran = refran;
        this.opcionCorrecta = opcionCorrecta;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.completed = completed;
        this.dateCompleted = dateCompleted;
    }

    public Refran(int id, int number, String refran, String opcionCorrecta, String opcion2, String opcion3, String opcion4, int completed, Date dateCompleted) {
        this.id = id;
        this.number = number;
        this.refran = refran;
        this.opcionCorrecta = opcionCorrecta;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.completed = completed;
        this.dateCompleted = dateCompleted;
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

    public String getRefran() {
        return refran;
    }

    public void setRefran(String refran) {
        this.refran = refran;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getRefranToShow() {
        int initKeywordIndex = this.refran.indexOf("(");
        int endKeywordIndex = this.refran.indexOf(")");
        if (endKeywordIndex < this.refran.length()-1) {
            return this.refran.substring(0,initKeywordIndex) + "_________" + this.refran.substring(endKeywordIndex+1, this.refran.length());
        } else {
            return this.refran.substring(0,initKeywordIndex) + "_________";
        }
    }
}
