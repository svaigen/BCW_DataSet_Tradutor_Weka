package classes;


import java.util.ArrayList;

public class Tupla {
    private int numeroAtributos;
    protected ArrayList<Dado> dados;

    public Tupla(int numeroAtributos) {
        this.numeroAtributos = numeroAtributos;
        this.dados = new ArrayList<>();
    }
    
    
}
