package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import classes.Atributo;
import classes.Dado;
import classes.Relacao;
import classes.Tupla;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static final int ATRIBUTO_REMOVIDO = 1;
    public static final int ATRIBUTO_GERAL = 2;
    public static final int ATRIBUTO_DECISAO = 3;

    public static String lerArquivo(String caminho) throws IOException {
        BufferedReader br = criarBufferedReader(caminho);
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = br.readLine()) != null) {
            sb.append(linha);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static BufferedReader criarBufferedReader(String caminho) throws IOException {
        FileReader fr = new FileReader(caminho);
        return new BufferedReader(fr);
    }

    public static Relacao tabulaDados(String baseDados) {
        String[] tuplasTexto = baseDados.split("\n");
        int qtdeDadosTupla = tuplasTexto[0].split(",").length;
        Relacao r = new Relacao("relacao");
        processaPrimeiraLinha(r, tuplasTexto[0], qtdeDadosTupla);
        for (int i = 1; i < tuplasTexto.length; i++) {
            String[] dados = tuplasTexto[i].split(",");
            Tupla t = new Tupla(qtdeDadosTupla);
            for (int j = 0; j < dados.length; j++) {
                Dado d = null;
                switch (tipoDado(dados[j])) {
                    case Atributo.TIPO_FLOAT:
                        d = new Dado(Double.parseDouble(dados[j]));
                        break;
                    case Atributo.TIPO_INT:
                        d = new Dado(Integer.parseInt(dados[j]));
                        break;
                    case Atributo.TIPO_STRING:
                        d = new Dado(dados[j]);
                        break;
                }
                t.dados.add(d);
            }
            r.tuplas.add(t);
        }
        return r;
    }

    private static void processaPrimeiraLinha(Relacao r, String tuplas, int qtdeDadosTupla) {
        String[] dados = tuplas.split(",");
        Tupla t = new Tupla(qtdeDadosTupla);
        for (int i = 0; i < dados.length; i++) {
            Atributo a = null;
            Dado d = null;
            switch (tipoDado(dados[i])) {
                case Atributo.TIPO_FLOAT:
                    a = new Atributo("attr" + i, Atributo.TIPO_FLOAT);
                    d = new Dado(Double.parseDouble(dados[i]));
                    break;
                case Atributo.TIPO_INT:
                    a = new Atributo("attr" + i, Atributo.TIPO_INT);
                    d = new Dado(Integer.parseInt(dados[i]));
                    break;
                case Atributo.TIPO_STRING:
                    a = new Atributo("attr" + i, Atributo.TIPO_STRING);
                    d = new Dado(dados[i]);
                    break;
            }
            t.dados.add(d);
            r.atributos.add(a);
        }
        r.tuplas.add(t);
    }

    private static int tipoDado(String dado) {
        if (dado.contains(".")) {
            return Atributo.TIPO_FLOAT;
        }
        if (dado.matches("[0-9]*")) {
            return Atributo.TIPO_INT;
        }
        return Atributo.TIPO_STRING;
    }

    public static void geraRelacoesTreinamentoClassificacao(Relacao relacao, Relacao relTreinamento, Relacao relDecisao, int[] atributos, int porcentagemTreinamento, int porcentagemClassificacao) {
        int totalTuplasTreinamento = (relacao.getTuplas().size() * porcentagemTreinamento)/100;
        System.out.println(relacao.getTuplas().size());
        for (int i = 0; i < atributos.length; i++) {
            if (atributos[i] != ATRIBUTO_REMOVIDO) {
                relacao.discretizaAtributo(i);
            }
        }
        Collections.shuffle(relacao.tuplas);
        relTreinamento.atributos = relacao.atributos;
        relTreinamento.tuplas = (List<Tupla>) relacao.tuplas.subList(0, totalTuplasTreinamento + 1);
        relDecisao.atributos = relacao.atributos;
        relDecisao.tuplas = (List<Tupla>) relacao.tuplas.subList(totalTuplasTreinamento + 1, relacao.tuplas.size());

    }

    public static void geraArff(int[] atributos, Relacao relacao, String caminhoArquivoTreinamento) throws IOException {
        FileWriter arq = new FileWriter(new File(caminhoArquivoTreinamento));
        PrintWriter gravarArq = new PrintWriter(arq);
        int indexClassificacao = 0;
        gravarArq.println("@relation " + relacao.getRotulo());
        gravarArq.println();

        //Incluindo os atributos gerais
        for (int i = 0; i < atributos.length; i++) {
            if (atributos[i] == ATRIBUTO_GERAL) {
                gravarArq.print("@attribute " + relacao.getAtributos().get(i).getRotulo() + " {");
                for (int j = 0; j < relacao.atributos.get(i).getValoresPossiveis().size(); j++) {
                    gravarArq.print(relacao.atributos.get(i).getValoresPossiveis().get(j));
                    if (j + 1 == relacao.atributos.get(i).getValoresPossiveis().size()) {
                        gravarArq.println("}");
                    } else {
                        gravarArq.print(",");
                    }
                }
            /*caso o atributo seja de decisao, ele deve ser o ultimo na descricao do .arff, portanto
                não deve ser escrito agora.*/
            } else if (atributos[i] == ATRIBUTO_DECISAO) { 
                indexClassificacao = i;
            }
        }
        
        //Por fim, escrevendo no arquivo o atributo de decisao
        gravarArq.print("@attribute " + relacao.getAtributos().get(indexClassificacao).getRotulo() + " {");
        for (int j = 0; j < relacao.atributos.get(indexClassificacao).getValoresPossiveis().size(); j++) {
            gravarArq.print(relacao.atributos.get(indexClassificacao).getValoresPossiveis().get(j));
            if (j + 1 == relacao.atributos.get(indexClassificacao).getValoresPossiveis().size()) {
                gravarArq.println("}");
            } else {
                gravarArq.print(",");
            }
        }
        
        gravarArq.println();
        //Escrevendo as tuplas
        gravarArq.println("@data");
        for (Tupla tupla : relacao.tuplas) {
            for (int i = 0; i < atributos.length; i++) {
                if (atributos[i] == ATRIBUTO_GERAL) {
                    gravarArq.print(tupla.showDado(i)+",");
                }
            }
            //gravando agora o atributo de classificação
            gravarArq.println(tupla.showDado(indexClassificacao));
        }
        arq.close();
    }
}
