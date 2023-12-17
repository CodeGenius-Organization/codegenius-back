package com.codegenius.course.domain.model;

import com.codegenius.course.utils.ListaObj;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

    public class GerenciadorDeArquivos {
        public static void gravaArquivoCsv(ListaObj<CourseModel> lista, String nomeArq) {
            FileWriter arq = null;
            Formatter saida = null;
            Boolean deuRuim = false;

            nomeArq += ".csv";

            // Bloco try-catch para abrir o arquivo
            try {
                arq = new FileWriter(nomeArq);
                saida = new Formatter(arq);
            } catch (IOException erro) {
                System.out.println("Erro ao abrir o arquivo");
                System.exit(1);
            }

            // Bloco try-catch para gravar o arquivo
            try {
                for (int i = 0; i < lista.getTamanho(); i++) {
                    //Recupere um elemento da lista e formate aqui:
                    CourseModel curso = lista.getElemento(i);

                    saida.format("%s; %s;%s;%b\n",
                            curso.getTitle(),
                            curso.getCourseDescription(),
                            curso.getContentDescription(),
                            curso.getAvailable());

                }
            } catch (FormatterClosedException erro) {
                System.out.println("Erro ao gravar o arquivo");
                deuRuim = true;
            } finally {
                saida.close();
                try {
                    arq.close();
                } catch (IOException erro) {
                    System.out.println("Erro ao fechar o arquivo");
                    deuRuim = true;
                }
                if (deuRuim) {
                    System.exit(1);
                }
            }
        }

    }

