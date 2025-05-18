package Objetos;
import java.util.Scanner;

import Entidades.Jugador;
import Javaling.Javaling;

/*
 * Clase Objeto
 * Representa un objeto que puede ser usado por el jugador
 * Contiene atributos como nombre, si es curativo, cantidad, si revive y su poder.
 */
public class Objeto {
    private String nombre;
    private boolean esCurativo;
    private int cantidad;
    private boolean esRevivir;
    private int poder;

    /*
     * Constructor de la clase Objeto.
     * Inicializa los atributos de la clase.
     */
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

    /**
     * Nombre: aumentarCantidad
     * ---------------------------
     * Descripción:
     *   Aumenta la cantidad del objeto en 1.
     ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void aumentarCantidad(){
        this.cantidad ++;
    }

    /**
     * Nombre: usar
     * ---------------------------
     * Descripción:
     *   Aplica el objeto al jugador.
     ****************************************************************************************
    * Parámetros:
    *   - Jugador jugador: El jugador que usará el objeto.
    *   - Scanner scanner: Scanner para leer la entrada del usuario.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
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
                    if(j.getHpActual() != 0){
                        j.setHpActual(j.getHpTotal());
                        System.out.println(j.getNombre() + " fue curado al 100%.");
                    }
                    else{
                        System.out.println("Este Javaling esta fuera de combate");
                        return;
                    }
                    
                }
                else{
                    if(j.getHpActual() != 0){
                        j.setHpActual(j.getHpActual() + curar);
                        System.out.println(j.getNombre() + " fue curado por " + curar + " puntos.");
                    }
                    else{
                        System.out.println("Este Javaling esta fuera de combate");
                        return;
                    }
                    
                }
            }
        }
        else{
            jugador.subirEquipoNivel(poder);
        }

        this.cantidad --;
    }

}
