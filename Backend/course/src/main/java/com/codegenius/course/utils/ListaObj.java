package com.codegenius.course.utils;

public class ListaObj <T> {

    // 01) Declarar vetor de int:
    // É inicializado no construtor
    private T[] vetor;

    // 02) Criar atributo nroElem:
    // Tem dupla função: representa quantos elementos foram adicionado no vetor
    // Também o índice de onde será adicionado o próximo elemento
    private int nroElem;

    // 03) Criar Construtor:
    // Recebe como argumento o tamanho máximo do vetor
    // Cria vetor com tamanho máximo informado
    // Inicializa nroElem
    // Atributos


    // Construtor
    // Recebe como argumento o tamanho máximo do vetor
    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];   // Cria o vetor
        nroElem = 0;                // Zera nroElem
    }

    // Métodos

    /* Método adiciona - recebe o elemento a ser adicionado na lista
       Se a lista estiver cheia, exibe uma mensagem
     */
    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        }
        else {
            vetor[nroElem++] = elemento;

        }
    }

    /* Método exibe - exibe os elementos da lista */
    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.print(vetor[i] + "\t");
            }
            System.out.println();
        }
    }

    /* Método busca - recebe o elemento a ser procurado na lista
       Retorna o índice do elemento, se for encontrado
       Retorna -1 se não encontrou
     */
    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {   // se encontrou
                return i;                        // retorna seu índice
            }
        }
        return -1;    // não encontrou, retorna -1
    }

    /* Método removePeloIndice - recebe o índice do elemento a ser removida
       Se o índice for inválido, retorna false
       Se removeu, retorna true
     */
    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
        // Loop para "deslocar para a esquerda" os elementos do vetor
        // sobrescrevendo o elemento removido
        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }
        nroElem--;          // decrementa nroElem
        return true;
    }

    /* Método removeElemento - recebe um elemento a ser removido
       Utiliza os métodos busca e removePeloIndice
       Retorna false, se não encontrou o elemento
       Retorna true, se encontrou e removeu o elemento
     */
    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public boolean substituirElem(T valorAntigo, T valorNovo){
        var aux = false;
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(valorAntigo)){
                vetor[i] = valorNovo;
                aux = true;
            }
        }
        return aux;
    }

    public int contaOcorrencias(T valor){
        int contador = 0;
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(valor)){
                contador++;
            }
        }
        return contador;
    }

    public boolean adicionaNoInicio(T valor){
        if (nroElem >= vetor.length){
            System.out.println("Lista está cheia");
            return false;
        } else {
            for (int i = nroElem; i > 0; i--) {
                vetor[i] = vetor[i-1];
            }

            vetor[0] = valor;
            nroElem++;

            return true;
        }
    }
    public int getTamanho() {
        return nroElem;
    }

    // 09) Método getElemento
    // Recebe um índice e retorna o elemento desse índice
    // Se o índice for inválido, retorna null
    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem){
            return null;
        }
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(indice)) {
                return vetor[i];
            }
        }

        return vetor[indice];
    }

    // 10) Método limpa
    // Limpa a lista
    public void limpa() {
        for (int i = 0; i <= nroElem +2 ; i++) {
            nroElem--;
        }
//        System.out.println(nroElem);
    }

    public void setElemento(T obj, int indice) {
        vetor[indice] = obj;
    }

    // Get do vetor
    // Não retirar, é usado nos testes
    public T[] getVetor() {
        return null;
    }
}


