package com.agricultura.inteligente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cultivo {
    String nombre;
    String tipo;
    String estado;
    int cantidad, diascosecha, nivelagua, cantidadnutrientes;
    LocalDate fechasiembra;
    public ArrayList<Plaga> plagas;
    

    public Cultivo(String nombre,String tipo,int cantidad, LocalDate fechasiembra, int diascosecha){
        this.nombre= nombre;
        this.tipo= tipo;
        this.diascosecha= diascosecha;
        this.cantidad= cantidad;
        this.nivelagua= 30;
        this.cantidadnutrientes=40;
        this.estado = "Vivo";
        this.plagas = new ArrayList<>();
    }

    public String getnombre(){
        return nombre;
    }
    public String gettipo(){
        return tipo;
    }
    public String getestado(){
        return estado;
    }

    public LocalDate getfechacosecha(){
        return fechasiembra.plusDays(diascosecha);
    }

    public int getcantidad(){
        return cantidad;
    }

    public int getnivelagua(){
        return nivelagua;
    }

    public int getcantidadnutrientes(){
        return cantidadnutrientes;
    }
    public ArrayList<Plaga> getPlagas() {
        return plagas;
    }
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
    public void setnutrientes(double delta) {
        this.cantidadnutrientes += delta;
    }
    public void setnivelagua(double delta) {
        this.nivelagua += delta;
    }
    
    public void regar(int cantidadriego){
        nivelagua = Math.min(nivelagua + cantidadriego, 70);
    }

    public void fertilizar(int nutrientesadicionales){
        cantidadnutrientes = Math.min(nivelagua + nutrientesadicionales, 80);
    }

    public void mostrarInfo() {
        System.out.println("Nombre del cultivo: " + nombre);
        System.out.println("Cantidad de hectareas: " + cantidad);
        System.out.println("Nivel de agua: " + nivelagua);
        System.out.println("Nivel de nutrientes: " + cantidadnutrientes);
        System.out.println("Fecha de siembra: " + fechasiembra);
    }

    public void crearplaga(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el nombre de la plaga");
        String nombreplaga =entrada.next();
        System.out.println("Ingresa el daño de la plaga");
        double daño = entrada.nextDouble();
        System.out.println("Ingresa el umbral que puede soportar un cultivo con esta plaga");
        double umbral = entrada.nextDouble();
        Plaga nuevaplaga = new Plaga(nombreplaga,daño, umbral);
        plagas.add(nuevaplaga);
        entrada.close();
    }
    


}
