package com.codegenius.course.domain.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class Pilha<T> {

    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public Pilha(int capacidade) {
        this.pilha = (T[])new Object[capacidade];
        this.topo = -1;
    }

    // 03) MÃ©todo isEmpty
    @JsonIgnore
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) MÃ©todo isFull
    @JsonIgnore
    public Boolean isFull() {
        return topo == pilha.length -1;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        try {
            topo++;
            pilha[topo] = info;
        } catch (IndexOutOfBoundsException e) {throw new IllegalStateException();}
    }

    // 06) MÃ©todo pop
    public T pop() {
        T aux = pilha[topo];
        pilha[topo--] = null;
        return aux;
    }

    // 07) MÃ©todo peek
    public T peek() {
        return topo != -1 ? pilha[topo] : null;
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        if (topo == -1) {
            System.out.println("Pilha vazia");
        } else {
            for (int c= 0; c <= topo; c++) {
                System.out.println(pilha[c]);
            }
        }
    }

    //Getters & Setters (manter)
    @JsonIgnore
    public int getTopo() {
        return topo;
    }

    public T[] getCourses() {
        return pilha;
    }

    @Override
    public String toString() {
        return "Pilha{" +
                "pilha=" + Arrays.toString(pilha) +
                '}';
    }
}