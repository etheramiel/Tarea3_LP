package Entidades;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Combate.Batalla;
import Javaling.Javaling;
import Objetos.Objeto;
import Piso.Piso;

public class Jugador implements Batalla{
    
    private String nombre;
    private List<Objeto> bolsa;
    private Javaling[] equipo; //= new Javaling[6];
    private Piso pisoActual;

    //Constructor()
    public Jugador(){
        this.equipo = new Javaling[6];
        this.bolsa = new ArrayList<>();
        this.pisoActual = new Piso();
        this.pisoActual.setNumPiso(1);
    }

    //Getters y Setters ------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Objeto> getBolsa() {
        return bolsa;
    }
    public void setBolsa(List<Objeto> bolsa) {
        this.bolsa = bolsa;
    }

    public Javaling[] getEquipo() {
        return equipo;
    }
    public void setEquipo(Javaling[] equipo) {
        this.equipo = equipo;
    }

    public Piso getPisoActual() {
        return pisoActual;
    }
    public void setPisoActual(Piso pisoActual) {
        this.pisoActual = pisoActual;
    }
    //-----------------------------------------------------------------------------------


    //Interface
    public Javaling elegirJavalingActivo(){
        Scanner scanner = new Scanner(System.in);

        while(true){

            boolean hayDisponible = false;

            for (int i = 0; i < equipo.length; i++) {
                if (equipo[i] != null && equipo[i].getHpActual() > 0) {
                    hayDisponible = true;
                }
            }

            if(!hayDisponible){
                System.out.println("Todos tus javalings han quedado fuera de combate");
                return null;
            }

            System.out.println("Elige tu javaling activo:");
            for (int i = 0; i < equipo.length; i++) {
                Javaling j = equipo[i];
                if (j != null) {
                    String estado = j.getHpActual() > 0 ? "Disponible" : "KO";
                    System.out.println(i+1 + " " + j.getNombre() + " " + " " + j.getHpActual() + "/" + j.getHpTotal() + " " + j.getTipo() + " " + estado);
                }
                else{
                    System.out.println(i+1 + ". Vacio");
                }
                

            }
            System.out.println("\n>Ingrese el numero de Javaling:");
            int opcion = scanner.nextInt();

            if(opcion < 1 || opcion > 6){
                System.out.println(">Opcion no valida");
                continue;
            }

            Javaling elegido = equipo[opcion - 1];

            if(elegido == null){
                System.out.print("\033[H\033[2J");
                System.out.println("Esta casilla está vacia");
                continue;
            }
            else if(elegido.getHpActual() == 0){
                System.out.print("\033[H\033[2J");
                System.out.println("Javaling fuera de combate :(");
                continue;
            }
            else{
                System.out.print("\033[H\033[2J");
                return elegido;
            }

        }
        
    }

    public void agregarJavaling(Javaling nuevo){
        Scanner scanner = new Scanner(System.in);
        int contador = 0;

        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                contador++;
            }
        }

        if(contador < 6){
            for (int i = 0; i < equipo.length; i++) {
                if(equipo[i] == null){
                    equipo[i] = nuevo;
                    return;
                }
            }
        }
        else{
            System.out.println("Tu equipo ya tiene 6 Javaling ¿Deseas reemplazar uno?\n1. Si\n2. No");
            int opcion = scanner.nextInt();
            ;
            switch (opcion) {
                case 2 -> {
                    System.out.println("El javaling no fue agregado");
                    return;
                }
                case 1 -> {
                    printEquipo();
                    System.out.println("Ingrese el numero de pokemon a eliminar:");
                    int eliminar = scanner.nextInt();
                    eliminarJavaling(eliminar - 1);
                    equipo[eliminar - 1] = nuevo;
                    break;
                }
                default -> {
                    return;
                }
            }
        }

    }
    
    public void eliminarJavaling(int indice){
        this.equipo[indice] = null;
    }


    public void printEquipo(){
        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                System.out.println(i+1 + ". Nombre: " + equipo[i].getNombre() + " || Tipo: " + equipo[i].getTipo() + " || HP: " + equipo[i].getHpActual() + "/"+equipo[i].getHpTotal() + " || Velocidad: " + equipo[i].getVelocidad() + " || Nivel: " + equipo[i].getNivel());
            }
            else{
                System.out.println(i+1 + ". Vacío");
            }
        }

        // String[] lista = new String[6];
        // for (int i = 0; i < equipo.length; i++) {
        //     if (equipo[i] == null) {
        //         lista[i] = "Vacío";
        //     }
        //     else{
        //         lista[i] = equipo[i].getNombre();
        //     }
        // }
        // System.out.println(Arrays.toString(lista));
    }

    public void subirEquipoNivel(int nivel){
        for (int i = 0; i < equipo.length; i++) {
            if(equipo[i] != null){
                equipo[i].subirNivel(nivel);
            }
        }
        if (nivel == 1) {
            System.out.println("Todos tus Javalings han subido " + nivel + " nivel");
        }
        else{
            System.out.println("Todos tus Javalings han subido " + nivel + " niveles");
        }
        
    }

    public void agregarObjeto(Objeto nuevo){
        for (int i = 0; i < bolsa.size(); i++) {
            Objeto o = bolsa.get(i);
            if (o.getNombre().equals(nuevo.getNombre())) {
                o.aumentarCantidad();
                return;
            }
        }
        bolsa.add(nuevo);
    }


    public void usarObjeto(Scanner scanner){
        if(bolsa.isEmpty()){
            System.out.println(">Tu bolsa está vacia");
            return;
        }

        System.out.println(">Objetos Disponibles:");

        for (int i = 0; i < bolsa.size(); i++) {
            Objeto o = bolsa.get(i);
            if (o.getCantidad() > 0) {
                System.out.println(i+1  + ". " + o.getNombre() + " x" + o.getCantidad());
            }
        }
        System.out.println("\n>Elige un objeto para usar \n(Ingresa 9 para salir)");
        int opcion = scanner.nextInt();

        if (opcion == 9) {
            return;
        }
        opcion--;
        if(opcion < 0 || opcion > bolsa.size()){
            return;
        }

        Objeto o = bolsa.get(opcion);

        if (o.getCantidad() <= 0) {
            System.out.println("No tienes ese objeto.");
            return;
        }

        o.usar(this, scanner);

        if (o.getCantidad() == 0) {
            bolsa.remove(opcion);
        }
    }

}
