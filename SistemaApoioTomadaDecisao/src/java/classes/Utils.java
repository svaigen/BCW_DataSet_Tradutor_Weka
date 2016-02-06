package classes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import classes.Atributo;
import classes.Dado;
import classes.Relacao;
import classes.Tupla;

public class Utils {

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
        if (dado.matches("[0-9]")){
            return Atributo.TIPO_INT;
        } 
        return Atributo.TIPO_STRING;
    }
}
