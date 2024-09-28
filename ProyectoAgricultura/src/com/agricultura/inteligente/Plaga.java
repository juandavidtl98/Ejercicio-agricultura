package com.agricultura.inteligente;


public class Plaga {
    String tipo;
    double daño;
    double umbral;
    

    public Plaga(String tipo, double daño, double umbral){
        this.tipo = tipo;
        this.daño = daño;
        this.umbral= umbral;
    }

    public String getTipo(){
        return tipo;
    }

    public double getDaño(){
        return daño;
    }

    public double getUmbral(){
        return umbral;
    }

    public boolean calculardaño(){
        return daño>=umbral;
    }
}


