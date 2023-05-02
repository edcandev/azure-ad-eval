package com.edic.azureadeval.models;

public class Ranges {
    private Integer seccion;
    private Integer score;

    public Integer getSeccion() {
        return seccion;
    }

    public void setSeccion(Integer seccion) {
        this.seccion = seccion;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Ranges() {
    }

    public Ranges(Integer seccion, Integer score) {
        this.seccion = seccion;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Ranges{" +
                "seccion=" + seccion +
                ", score=" + score +
                '}';
    }
}
