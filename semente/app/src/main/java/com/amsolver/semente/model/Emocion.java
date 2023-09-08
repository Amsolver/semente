package com.amsolver.semente.model;

public class Emocion {

    public int id;
    public int number;
    public String emocion;
    public int completed;

    public Emocion() {
    }

    public Emocion(int id, int number, String emocion, int completed) {
        this.id = id;
        this.number = number;
        this.emocion = emocion;
        this.completed = completed;
    }

    public Emocion(int number, String emocion, int completed) {
        this.number = number;
        this.emocion = emocion;
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

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
