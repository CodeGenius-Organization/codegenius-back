package com.codegenius.game.domain.utils;

import com.codegenius.game.domain.dto.DadosQuestaoTxtDTO;
import com.codegenius.game.domain.model.QuestionModel;
import com.codegenius.game.domain.repository.QuestionRepository;
import com.codegenius.game.domain.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GerenciadorArquivoTxt {

    public void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }
    public List<DadosQuestaoTxtDTO> leArquivoTxt(String nomeArq, QuestionRepository repository) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        List<DadosQuestaoTxtDTO> aux = new ArrayList<>();
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;


        // Cria uma lista para armazenar os objetos criados com
        // os dados lidos do arquivo txt
        //  List<Serie> listaLida = new ArrayList<>();

        // Abre o arquivo

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }


        // Leitura do arquivo
        try {
            registro = entrada.readLine();

            while (registro != null) {
                // obtem os 2 primeiros caracteres do registro lido
                // 1o argumento do substring é o indice do que se quer obter, iniciando de zero
                // 2o argumento do substring é o indice final do que se deseja, MAIS UM


                // 012345
                // 00NOTA
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    //Exibir informações do header
                    System.out.println("Tipo de arquivo: " + registro.substring(2,8));
                    System.out.println("Data e hora:" + registro.substring(8,27));
                    System.out.println("Versão do layout: " + registro.substring(27,29));
                } else if (tipoRegistro.equals("01")) {

                    System.out.println("É um registro de trailer");

                    //Exibir quantidade de registros

                } else if (tipoRegistro.equals("03")) {
                    System.out.println("É um registro de corpo");

                    String tipoQuestao = registro.substring(2,17).trim();
                    String enunciado = registro.substring(17,62).trim();
                    String prova = registro.substring(62,66).trim();
                    String nivelDaQuestao = registro.substring(66,73).trim();



                    //Depois de guarda em variável, exiba:
                    System.out.println("Tipo da Questao " + tipoQuestao);
                    System.out.println("Enunciado: " + enunciado);
                    System.out.println("Prova: " + prova);
                    System.out.println("Nível das perguntas: " + nivelDaQuestao);



                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    // Cria um objeto com os dados lidos do registro
                    DadosQuestaoTxtDTO questaoDTO = new DadosQuestaoTxtDTO();
                    questaoDTO.setStatement(enunciado);
                    questaoDTO.setQuestionDifficulty(nivelDaQuestao);
                    questaoDTO.setQuestionType(tipoQuestao);
                    questaoDTO.setTestQuestion(prova.equalsIgnoreCase("Sim"));


                    // Se estivesse conectado a um banco de dados,
                    UUID id = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
                    QuestionModel model = new QuestionModel(null, questaoDTO.getQuestionType(),questaoDTO.getStatement(),questaoDTO.getTestQuestion(), id);

                  repository.save(model);
                  aux.add(questaoDTO);
                } else {
                    System.out.println("Registro inválido");
                }
                registro = entrada.readLine();
            }  // fim do while

            // Fecha o arquivo
            entrada.close();
        } // fim do try

        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }
        // Exibe a lista lida
        System.out.println("\nLista lida do arquivo:");
        for (DadosQuestaoTxtDTO s : aux) {
            System.out.println(s);
        }

        // Aqui tb seria possível salvar a lista no BD
        //repository.saveAll(listaLida);
        return aux;
    }

    public List<DadosQuestaoTxtDTO> gravaArquivoTxt(List<DadosQuestaoTxtDTO> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00TXT"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (DadosQuestaoTxtDTO a : lista) {
            String corpo = "03" +
                    String.format("%-15s", a.getQuestionType()) +
                    String.format("%-45s", a.getStatement()) +
                    String.format("%-4s", a.getTestQuestion() ? "Sim" : "Não") +
                    String.format("%-7s", a.getQuestionDifficulty());

            //Gravando corpo no arquivo:
            gravaRegistro(corpo, nomeArq);
            // Incrementa o contador de registros de dados gravados
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistro(trailer, nomeArq);

        return lista;
    }

}
