package com.agricultura.inteligente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class sistemaAgricultura {
    private Scanner entrada; 
    private ArrayList<Cultivo> listaCultivos; 

    public sistemaAgricultura() {
        entrada = new Scanner(System.in); 
        listaCultivos = new ArrayList<>();
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("Bienvenido señor usuario. Bienvenido al sistema inteligente agropeuario SISAGRO. Por favor seleccione una de las siguientes opciones");
            System.out.println("1. Agregar Cultivo");
            System.out.println("2. Listar Cultivos");
            System.out.println("3. Verificar Cosecha");
            System.out.println("4. Regar cultivo");
            System.out.println("5. Fertilizar cultivo");
            System.out.println("6. Agregar plaga");
            System.out.println("7. Combatir plagas");
            System.out.println("8. Enfrentar mal clima");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine(); 

            switch (opcion) {
                case 1:
                    agregarCultivo();
                    break;
                case 2:
                    listarCultivos();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del cultivo: ");
                    String nombreVerif = entrada.nextLine();
                    verificarCosecha(nombreVerif);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del cultivo: ");
                    String nombreRiego = entrada.nextLine();
                    System.out.print("Ingrese la cantidad de agua: ");
                    int cantidadAgua = entrada.nextInt();
                    regarCultivo(nombreRiego, cantidadAgua);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del cultivo: ");
                    String nombreFertilizar = entrada.nextLine();
                    System.out.print("Ingrese la cantidad de nutrientes: ");
                    int cantidadNutrientes = entrada.nextInt();
                    fertilizarCultivo(nombreFertilizar, cantidadNutrientes);
                    break;
                case 6:
                    agregarPlagaACultivo();
                    break;
                case 7:
                    combatirPlaga();
                    break;
                case 8:
                    combatirMalclima(); // Asegúrate de que este método esté llamado
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 9);
    }

    public void agregarCultivo() {
        System.out.println("Ingresa el nombre del cultivo");
        String nombre = entrada.next();
        System.out.println("Ingresa la cantidad de hectáreas del cultivo");
        int cantidad = entrada.nextInt();
        LocalDate fechaSiembra = LocalDate.now();
        System.out.println("Ingresa los días hasta cosecha");
        int diasCosecha = entrada.nextInt();
        Cultivo nuevoCultivo = new Cultivo(nombre, nombre, cantidad, fechaSiembra, diasCosecha);
        listaCultivos.add(nuevoCultivo);
        System.out.println("Cultivo agregado.");
    }

    public void listarCultivos() {
        for (Cultivo cultivo : listaCultivos) {
            cultivo.mostrarInfo();
        }
    }

    public void regarCultivo(String nombre, int cantidad) {
        for (Cultivo cultivo : listaCultivos) {
            if (cultivo.getnombre().equalsIgnoreCase(nombre)) {
                cultivo.regar(cantidad);
                return;
            }
        }
        System.out.println("Cultivo no encontrado.");
    }

    public void fertilizarCultivo(String nombre, int cantidad) {
        for (Cultivo cultivo : listaCultivos) {
            if (cultivo.getnombre().equalsIgnoreCase(nombre)) {
                cultivo.fertilizar(cantidad);
                return;
            }
        }
        System.out.println("Cultivo no encontrado.");
    }

    public void verificarCosecha(String nombre) {
        LocalDate hoy = LocalDate.now();
        for (Cultivo cultivo : listaCultivos) {
            if (hoy.isEqual(cultivo.getfechacosecha()) || hoy.isAfter(cultivo.getfechacosecha())) {
                System.out.println(cultivo.getnombre() + " está listo para cosecha.");
            }
        }
    }

    public void agregarPlagaACultivo() {
        System.out.print("Ingrese el nombre del cultivo: ");
        String nombreCultivo = entrada.nextLine();

        for (Cultivo cultivo : listaCultivos) {
            if (cultivo.getnombre().equalsIgnoreCase(nombreCultivo)) {
                cultivo.crearplaga(); 
                return;
            }
        }
        System.out.println("Cultivo no encontrado.");
    }

    public void combatirPlaga() {
        System.out.print("Ingrese el nombre del cultivo: ");
        String nombreCultivo = entrada.nextLine();
        
        for (Cultivo cultivo : listaCultivos) {
            if (cultivo.getnombre().equalsIgnoreCase(nombreCultivo)) {
                boolean plagaCombatida = false; 
                
                for (Plaga plaga : cultivo.getPlagas()) { 
                    if (plaga.getDaño() >= plaga.getUmbral()) { 
                        plagaCombatida = true;
                        System.out.println("La plaga " + plaga.getTipo() + " está causando daño crítico en " + cultivo.getnombre() + ".");
                        
                        cultivo.setEstado("Muerto"); 
                        System.out.println(cultivo.getnombre() + " ha muerto debido a la plaga " + plaga.getTipo() + ".");
                        break; 
                    }
                }
                
                if (!plagaCombatida) {
                    System.out.println("No hay plagas críticas en " + cultivo.getnombre() + ".");
                }
                return; 
            }
        }
        System.out.println("Cultivo no encontrado.");
    }

    public void combatirMalclima() {
        System.out.println("Seleccione el tipo de clima:");
        System.out.println("1. Lluvia intensa");
        System.out.println("2. Sequía");
        int clima = entrada.nextInt();

        for (Cultivo cultivo : listaCultivos) {
            switch (clima) {
                case 1:
                    cultivo.setnivelagua(35); 
                    if(cultivo.getnivelagua()>=85){
                        System.out.println("El cultivo " + cultivo.getnombre() + " Ha muerto");
                        cultivo.setEstado("Muerto"); 
                    }

                    else{
                        System.out.println("El nivel de agua de cultivo esta en " + cultivo.getnivelagua() +  ", es decir, a punto de llegar a su maximo de 85. Se procede a cubrir el cultivo para evitar su deterioro");
                    }
                    break;
                case 2:
                cultivo.setnivelagua(-35);
                cultivo.setnutrientes(-35);
                    if(cultivo.getnivelagua()<35 || cultivo.cantidadnutrientes<30){
                        System.out.println("El cultivo " + cultivo.getnombre() + " Ha muerto");
                        cultivo.setEstado("Muerto"); 

                    }

                    else{
                        System.out.println("El nivel de agua de cultivo esta en " + cultivo.getnivelagua() +   "y la cantidad de nutrientes esta en " + cultivo.getcantidadnutrientes() + ",es decir, a punto de llegar a su maximo de 85. Se procede a cubrir el cultivo para evitar su deterioro");

                        System.out.println("Se procede a regar y fertilizar para evitar su deterioro ");
                        System.out.println("Ingrese el nivel de agua que desea agregar");
                        int nuevonivelagua = entrada.nextInt();
                        regarCultivo(cultivo.getnombre(), nuevonivelagua);
                        System.out.println("Ingrese el nivel de nutrientes que desea agregar");
                        int nuevacantnutrientes = entrada.nextInt();
                        fertilizarCultivo(cultivo.getnombre(), nuevacantnutrientes);


                }
                    break;                       
                default:
                    System.out.println("Tipo de clima no válido.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        sistemaAgricultura sistema = new sistemaAgricultura();
        sistema.menu(); 
        sistema.entrada.close();
    }
}
