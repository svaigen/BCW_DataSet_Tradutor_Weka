package classes;


import java.util.ArrayList;

public class Tupla {
    private int numeroAtributos;
    protected ArrayList<Dado> dados;

    public Tupla(int numeroAtributos) {
        this.numeroAtributos = numeroAtributos;
        this.dados = new ArrayList<>();
    }

    public int getNumeroAtributos() {
        return numeroAtributos;
    }
    
    public String showDado(int index){
        return this.dados.get(index).toString();
    }

    void updateDadoStringValue(int index, String data) {
        Dado d = this.dados.get(index);
        d.setDoublee(Double.MAX_VALUE);
        d.setIntt(Integer.MAX_VALUE);
        d.setStr(data);
        this.dados.set(index, d);
    }
}
