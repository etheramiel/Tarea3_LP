package Entidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Combate.Batalla;
import Javaling.Agua;
import Javaling.Dragon;
import Javaling.Fuego;
import Javaling.Javaling;
import Javaling.Planta;
import Movimientos.DatosMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;
import Piso.Piso;

public class Entrenador implements Batalla{
    
    private boolean esCampeon;
    private Javaling[] equipo;
    private String nombre;


    //Getters y Setters---------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Javaling[] getEquipo() {
        return equipo;
    }
    public void setEquipo(Javaling[] equipo) {
        this.equipo = equipo;
    }

    public boolean getEsCampeon(){
        return esCampeon;
    }
    //-----------------------------------------------------------------------------

    public Entrenador(Piso piso){

        this.equipo = new Javaling[6];
        Random rand = new Random();

        if(piso.getNumPiso() >= 30){
            int chance = 5 + (piso.getNumPiso() - 29);
            this.esCampeon = rand.nextInt(100) < chance;
            if(esCampeon){
                this.nombre = "Gary Oak";
            }
            else{
                this.nombre = "Cazabichos";
            }
        }
        else{
            this.esCampeon = false;
            this.nombre = "Cazabichos";
        }
        generarEquipoAleatorio(piso.getNumPiso());
    }



    private Javaling generarJavaling(Tipo tipo, int nivel, boolean esCampeon, int casilla){
        Javaling j;
        String nombre = generarNombrejavaling();

        switch (tipo) {
            case AGUA -> j = new Agua(nombre);
            case FUEGO -> j = new Fuego(nombre);
            case PLANTA -> j = new Planta(nombre);
            case DRAGON -> j = new Dragon(nombre);
            default -> j = new Agua(nombre);
        }

        j.setNivel(nivel);

        boolean tienePotente = false;
        Movimiento[] movs = j.getMovimientos();

        if (esCampeon) {
            for (int i = 0; i < 4; i++) {
                if(movs[i] != null){
                    if(movs[i].getPotencia() >= 100){
                    tienePotente = true;
                    }
                }
            }
        }

        if(!tienePotente && esCampeon){
            Movimiento mov = buscarMovimientoPotente(j);
            if(mov != null){
                j.getMovimientos()[3] = mov;;
            }

        }

        return j;
    }

    private Movimiento buscarMovimientoPotente(Javaling j){
        
        List<Movimiento> todos = new ArrayList<>();
        todos.addAll(DatosMovimientos.movimientosAgua());
        todos.addAll(DatosMovimientos.movimientosPlanta());
        todos.addAll(DatosMovimientos.movimientosFuego());
        todos.addAll(DatosMovimientos.movimientosDragon());
        // todos.addAll(DatosMovimientos.movimientosNormal());

        for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getPotencia() >= 100){
                return todos.get(i);
            }
        }

        return null;
    }

    public static String generarNombrejavaling(){
        String[] prefijos = {
        "Char", "Lilli", "Meta", "Magi", "Snor", "Eev", "Vapo", "Cubo", "Slow", "Ab", "Odd",
        "Zap", "Drago", "Flare", "Bub", "Pika", "Raich", "Gengar", "Mew", "Luc", "Toxi"
        };

        String[] sufijos = {
        "izard", "pup", "pod", "karp", "lax", "vee", "reon", "bone", "poke", "ra", "dish",
        "chu", "osaur", "dude", "gas", "lock", "bat", "mite", "buzz", "quake", "tail"
        };

        Random rand = new Random();
        String prefijo = prefijos[rand.nextInt(prefijos.length)];
        String sufijo = sufijos[rand.nextInt(sufijos.length)];

        return prefijo + sufijo;
    }

    private Tipo elegirTipoAleatorio(Random rand){
        Tipo[] tipos = {Tipo.AGUA, Tipo.FUEGO, Tipo.PLANTA};
        return tipos[rand.nextInt(tipos.length)];
    }


    public int calcularCantJavalings(int piso){

        if(esCampeon) return 6;
        if(piso<= 20) return 1 + new Random().nextInt(2);
        if (piso <= 30) return 2 + new Random().nextInt(2);
        if (piso <= 40) return 3 + new Random().nextInt(2);
        if (piso <= 50) return 4 + new Random().nextInt(2);
        return 6;
    }

    public void generarEquipoAleatorio(int piso){
        Random rand = new Random();

        int cantidad = calcularCantJavalings(piso);

        for (int i = 0; i < cantidad; i++) {
            int nivel;
            if(esCampeon){
                nivel = piso + 5;
            }
            else{
                if(piso <= 5){
                    nivel = 4 + rand.nextInt(3);
                }
                else{
                    int base = (int) Math.floor(1.3 * piso);
                    nivel = base + (rand.nextInt(7) - 3);
                    if (nivel < 1) {
                        nivel = 1;
                    }
                }
            }
           
            Tipo tipo;
            boolean esDragon = false;
            
            if(esCampeon){
                if(i == 0){
                    esDragon = true;
                }
                else{
                    esDragon = rand.nextInt(100) < 20;
                }
            }
            else{
                esDragon = rand.nextInt(100) < 5;
            }

            tipo = esDragon ? Tipo.DRAGON : elegirTipoAleatorio(rand);
            Javaling j = generarJavaling(tipo, nivel, esCampeon, i);
            equipo[i] = j;

        }

    }

    public Javaling elegirJavalingActivo(){

        for (int i = 0; i < equipo.length; i++) {
            if( equipo[i] != null){
                Javaling j = equipo[i];
                if (j.getHpActual() > 0) {
                    return j;
                }
            }   
        }
        return null;
    }


    public void mostrarEquipo() {
    System.out.println(this.nombre);
    for (int i = 0; i < equipo.length; i++) {
        Javaling j = equipo[i];
        if (j != null) {
            System.out.printf("%d. %s (%s) - Nivel %d | HP: %d/%d | Velocidad: %d\n", i + 1, j.getNombre(), j.getTipo(), j.getNivel(), j.getHpActual(), j.getHpTotal(), j.getVelocidad());
        }
    }
    }

    public int escogerAtaque(Javaling activo){
        int cantAtaques = 0;
        Random rand = new Random();

        for (int i = 0; i < activo.getMovimientos().length; i++) {
            if(activo.getMovimientos()[i] != null){
                cantAtaques++;
            }
        }
        int indice = rand.nextInt(cantAtaques);
        System.out.println("Movimiento entrenador: " + activo.getMovimientos()[indice].getNombre());
        return indice;
    }
}
