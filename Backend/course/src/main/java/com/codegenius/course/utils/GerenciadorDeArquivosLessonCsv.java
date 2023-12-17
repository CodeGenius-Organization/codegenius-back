package com.codegenius.course.utils;

import com.codegenius.course.domain.dto.CourseCsvDTO;
import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.ModuleLessonModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GerenciadorDeArquivosLessonCsv {
    public static void gravaArquivoCsv(ListaObj<ModuleLessonModel> lista, String nomeArq) {
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
                ModuleLessonModel lesson = lista.getElemento(i);
                System.out.println("num: " + lesson.getLessonOrder());
                saida.format("%d; %s\n", lesson.getLessonOrder(),
                        lesson.getContentDescription());
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

//    public static ListaObj<ModuleLessonModel> importarArquivoCsv(String nomeArq) {
//        FileReader arq = null;
//        Scanner entrada = null;
//        Boolean deuRuim = false;
//
//        nomeArq += ".csv";
//
//        List<CourseModel> listaCursos = new ArrayList<>();
//
//
//// Bloco try-catch para abrir o arquivo
//        try {
//            System.out.println(nomeArq);
//            arq = new FileReader(nomeArq);
//            entrada = new Scanner(arq).useDelimiter(";|\\n");
//        } catch (FileNotFoundException erro) {
//            System.out.println("Arquivo nao encontrado");
//            System.exit(1);
//        }
//
//
//// Bloco try-catch para ler o arquivo
//        try {
////Leia e formate a saída no console aqui:
//
//// 0
//            System.out.printf(" %-15S %-30S %50S %12S\n", "Title", "Course_description",
//                    "content_description", "available");
//            while (entrada.hasNext()) {
//                String title = entrada.next();
//                String course_description = entrada.next();
//                String content_description = entrada.next();
//                String image = entrada.next();
//                Boolean available = entrada.nextBoolean();
//
//
//                System.out.printf("%-15s %-30s %-50s %-12b\n" , title, course_description, content_description,
//                        available);
//
//                listaCursos.add(new CourseModel(title, course_description, content_description, image, available));
//            }
//        } catch (NoSuchElementException erro) {
//            System.out.println("Arquivo com problemas");
//            deuRuim = true;
//        } catch (IllegalStateException erro) {
//            System.out.println("Erro na leitura do arquivo");
//            deuRuim = true;
//        } finally {
//            entrada.close();
//            try {
//                arq.close();
//            } catch (IOException erro) {
//                System.out.println("Erro ao fechar o arquivo");
//                deuRuim = true;
//            }
//            if (deuRuim) {
//                System.exit(1);
//            }
//            return listaCursos;
//        }
//    }

}


