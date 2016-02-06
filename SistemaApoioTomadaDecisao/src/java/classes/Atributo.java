package classes;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 'Alisson
 */
public class Atributo {

    static final int TIPO_INT = 1;
    static final int TIPO_FLOAT = 2;
    static final int TIPO_STRING = 3;

    private String rotulo;
    private int tipoAtributo;
    protected ArrayList<String> valoresPossiveis;

    public Atributo(String rotulo, int tipoAtributo) {
        this.rotulo = rotulo;
        valoresPossiveis = new ArrayList<>();
        this.tipoAtributo = tipoAtributo;
    }

    public String getRotulo() {
        return rotulo;
    }

    public int getTipoAtributo() {
        return tipoAtributo;
    }

}
