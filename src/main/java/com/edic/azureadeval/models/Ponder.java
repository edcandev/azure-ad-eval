package com.edic.azureadeval.models;

import java.util.Arrays;

public class Ponder {
    private Integer seccion;
    private Integer numPreguntas;
    private Integer maxPosible;
    private Integer[] rangos;

    public Integer getSeccion() {
        return seccion;
    }

    public void setSeccion(Integer seccion) {
        this.seccion = seccion;
    }

    public Integer getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(Integer numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

    public Integer getMaxPosible() {
        return maxPosible;
    }

    public void setMaxPosible(Integer maxPosible) {
        this.maxPosible = maxPosible;
    }

    public Integer[] getRangos() {
        return rangos;
    }

    public void setRangos(Integer[] rangos) {
        this.rangos = rangos;
    }

    public Ponder() {
    }
    public Ponder(Integer seccion, Integer numPreguntas, Integer maxPosible) {
    }
    public Ponder(Integer seccion, Integer numPreguntas, Integer maxPosible, Integer[] rangos) {
    }

    @Override
    public String toString() {
        return "Ponder{" +
                "seccion=" + seccion +
                ", numPreguntas=" + numPreguntas +
                ", maxPosible=" + maxPosible +
                ", rangos=" + Arrays.toString(rangos) +
                '}';
    }
}
