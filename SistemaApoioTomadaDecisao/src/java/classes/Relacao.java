package classes;


import java.util.ArrayList;

public class Relacao {
    private String rotulo;
    protected ArrayList<Atributo> atributos;
    protected ArrayList<Tupla> tuplas;

    public Relacao(String rotulo) {
        this.rotulo = rotulo;
        this.atributos = new ArrayList<>();
        this.tuplas = new ArrayList<>();
    }

    @Override
    public String toString() {
        String descricao = "Relação: "+this.rotulo+"\n";
        descricao += "Lista de atributos:\n";
        for(int i=0; i<atributos.size(); i++){
            descricao += "  "+i+") "+atributos.get(i).getRotulo()+"\n";
        }
        descricao += "Lista de tuplas ("+tuplas.size()+"):\n";
        for (Tupla tupla : tuplas) {
            for (Dado dado : tupla.dados) {
                descricao += ""+dado.toString()+" ";
            }
            descricao +="\n";
        }
        return descricao;
    }
    
    
}
