package com.codegenius.game.domain.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Fila<T>{
    // Atributos
    private int tamanho;
    private T[] fila;

    // Construtor
    public Fila(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    @JsonIgnore
    public boolean isEmpty() {
         return tamanho == 0;
    }
    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    @JsonIgnore
    public boolean isFull() {
        return fila.length == tamanho;
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Lançar IllegalStateException caso a fila esteja cheia
     */
    public void insert(T info) {
        try {
            fila[tamanho++] = info;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException();
        }
    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public T peek() {
        return fila[0] != null ? fila[0] : null;
    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
       Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
     */
    public T poll() {
        for (int c = 0; c < fila.length - 1; c++) {
            T aux = fila[c];
            fila[c] = fila[c + 1];
            fila[c + 1] = aux;
        }
        T aux2 = fila[fila.length - 1];
        fila[fila.length - 1] = null;
        tamanho--;
        return aux2;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        for (int c = 0; c < fila.length; c++) {
            System.out.println(fila[c]);
        }
    }

    /* Usado nos testes  - complete para que fique certo */
    @JsonIgnore
    public int getTamanho(){
        return tamanho;
    }

    public T[] getQuestions() {
        return fila;
    }
}

