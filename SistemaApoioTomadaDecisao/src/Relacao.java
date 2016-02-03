
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
    
    
}
