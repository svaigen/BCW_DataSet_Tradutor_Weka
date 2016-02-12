package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Relacao {

    private String rotulo;
    protected List<Atributo> atributos;
    protected List<Tupla> tuplas;

    public Relacao(String rotulo) {
        this.rotulo = rotulo;
        this.atributos = new ArrayList<>();
        this.tuplas = new ArrayList<>();
    }

    @Override
    public String toString() {
        String descricao = "Relação: " + this.rotulo + "\n";
        descricao += "Lista de atributos:\n";
        for (int i = 0; i < atributos.size(); i++) {
            descricao += "  " + i + ") " + atributos.get(i).getRotulo() + "\n";
        }
        descricao += "Lista de tuplas (" + tuplas.size() + "):\n";
        for (Tupla tupla : tuplas) {
            for (Dado dado : tupla.dados) {
                descricao += "" + dado.toString() + " ";
            }
            descricao += "\n";
        }
        return descricao;
    }

    public String getRotulo() {
        return rotulo;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public Tupla getTupla(int index) {
        return this.tuplas.get(index);
    }

    public List<Tupla> getTuplas() {
        return tuplas;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public void updateAtributo(int index, String rotulo) {
        Atributo a = this.atributos.get(index);
        a.setRotulo(rotulo);
        this.atributos.set(index, a);
    }

    void discretizaAtributo(int i) {
        if (this.atributos.get(i).getTipoAtributo() == Atributo.TIPO_STRING) {
            discretizaString(this.atributos.get(i), this.tuplas, i);
        } else {
            discretizaNumero(this.atributos.get(i), this.tuplas, i);
            this.atributos.get(i).setTipoAtributo(Atributo.TIPO_STRING); //pois agora se tornou um intervalo
        }
    }

    private void discretizaString(Atributo atributo, List<Tupla> tuplas, int indiceAtributo) {
        HashMap<String, String> hash = new HashMap<>();
        for (Tupla tupla : tuplas) {
            hash.put(tupla.dados.get(indiceAtributo).getStr(), tupla.dados.get(indiceAtributo).getStr());
        }
        Set<String> keys = hash.keySet();
        for (String key : keys) {
            atributo.valoresPossiveis.add(key);
        }
    }

    private void discretizaNumero(Atributo atributo, List<Tupla> tuplas, int indiceAtributo) {
        
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (Tupla tupla : tuplas) {
            min = (Double.parseDouble(tupla.showDado(indiceAtributo)) < min) ? Double.parseDouble(tupla.showDado(indiceAtributo)) : min;
            max = (Double.parseDouble(tupla.showDado(indiceAtributo)) > max) ? Double.parseDouble(tupla.showDado(indiceAtributo)) : max;
        }

        double fator = (max - min) / 10;
        double limiteAcumulado = min + fator;
        double limites[] = new double[10];
        for (int i = 0; i < limites.length; i++) {
            limites[i] = limiteAcumulado;
            if (i == 0) {
                atributo.valoresPossiveis.add(String.format("%.4f",min).replace(",", ".") +
                        "_" + String.format("%.4f",limiteAcumulado).replace(",", "."));
            } else if (i+1 == limites.length) {
                atributo.valoresPossiveis.add(String.format("%.4f",limiteAcumulado).replace(",",".")
                        + "_" + String.format("%.4f",(max)).replace(",","."));
            } else {
                atributo.valoresPossiveis.add(String.format("%.4f", limiteAcumulado).replace(",", ".")
                        + "_" + String.format("%.4f", (limiteAcumulado + fator)).replace(",", "."));                
            }
            limiteAcumulado += fator;
        }

        for (Tupla tupla : tuplas) {
            for (int i = 0; i<limites.length; i++) {
                if (Double.parseDouble(tupla.showDado(indiceAtributo)) <= limites[i]){
                    tupla.updateDadoStringValue(indiceAtributo,atributo.valoresPossiveis.get(i));
                    break;
                } else if((i+1) == limites.length){
                    tupla.updateDadoStringValue(indiceAtributo,atributo.valoresPossiveis.get(i));
                }
            }
        }
    }
}
