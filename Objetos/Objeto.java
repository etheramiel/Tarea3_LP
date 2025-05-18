package Objetos;
import java.util.Scanner;

import Entidades.Jugador;
import Javaling.Javaling;

public class Objeto {
    private String nombre;
    private boolean esCurativo;
    private int cantidad;
    private boolean esRevivir;
    private int poder;

    public Objeto(String nombre, boolean esCurativo, boolean esRevivir, int poder){
        this.nombre = nombre;
        this.esCurativo = esCurativo;
        this.esRevivir = esRevivir;
        this.poder = poder;
        this.cantidad = 1;
    }


    //Getters and setters---------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPoder() {
        return poder;
    }
    public void setPoder(int poder) {
        this.poder = poder;
    }

    public boolean getEsCurativo(){
        return esCurativo;
    }
    public void setEsCurativo(boolean esCurativo) {
        this.esCurativo = esCurativo;
    }

    public boolean getEsRevivir(){
        return esRevivir;
    }
    public void setEsRevivir(boolean esRevivir) {
        this.esRevivir = esRevivir;
    }
    //-------------------------------------------------------------------

    public void aumentarCantidad(){
        this.cantidad ++;
    }

    public void usar(Jugador jugador, Scanner scanner){
        if(esCurativo){
            jugador.printEquipo();
            System.out.println("Elige un Javaling para aplicar el objeto");
            int index = scanner.nextInt() - 1;

            Javaling j = jugador.getEquipo()[index];

            if (j == null) {
                return;
            }

            if(esRevivir){
                if(j.getHpActual() == 0){
                    j.setHpActual(j.getHpTotal());
                    System.out.println(j.getNombre() + " fue revivido al 100%.");
                }
                else{
                    System.out.println(j.getNombre() + " no está abatido.");
                }
            }
            else{
                int curar = (j.getHpTotal() * poder / 100);
                if(curar > j.getHpActual() + curar){
                    j.setHpActual(j.getHpTotal());
                    System.out.println(j.getNombre() + " fue curado al 100%.");
                }
                else{
                    j.setHpActual(j.getHpActual() + curar);
                    System.out.println(j.getNombre() + " fue curado por " + curar + " puntos.");
                }
            }
        }
        else{
            jugador.subirEquipoNivel(poder);
        }

        this.cantidad --;
    }

}
