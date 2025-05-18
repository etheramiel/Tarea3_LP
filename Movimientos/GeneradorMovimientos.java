package Movimientos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * GeneradorMovimientos.java
 * Generador de movimientos para los Javaling.
 */
public class GeneradorMovimientos {

    /**
     * Nombre: generadorMovimientos
     * ---------------------------
     * Descripción:
     *   Genera un conjunto de movimientos para un Javaling de tipo Agua.
     *   Cada movimiento tiene su nombre, potencia, precisión, descripción y tipo.
     ****************************************************************************************
    * Parámetros:
    *   - tipoPrincipal: Tipo de javaling (AGUA, FUEGO, PLANTA, DRAGON).
    *   
    **************************************************************************************
    * Retorno:
    *   - Movimiento[]: Array de movimientos generados.
    ***********************************************************************************
    */
    public static Movimiento[] generadorMovimientos(Tipo tipoPrincipal){
        Movimiento[] resultado = new Movimiento[4];
        Random rand = new Random();

        List<Movimiento> tipoPropio = switch (tipoPrincipal) {
            case AGUA -> DatosMovimientos.movimientosAgua();
            case FUEGO -> DatosMovimientos.movimientosFuego();
            case PLANTA -> DatosMovimientos.movimientosPlanta();
            case DRAGON -> DatosMovimientos.movimientosDragon();
            default -> new ArrayList<>();
        };

        int cantidad = 0;
        Collections.shuffle(tipoPropio);
        for (int i = 0; i < tipoPropio.size(); i++) {
            if(!tipoPropio.get(i).getEstado() && cantidad < 2){
                resultado[cantidad] = tipoPropio.get(i);
                cantidad++;
            }
        }
 
        List<Movimiento> estado = DatosMovimientos.movimientosEstado();
        Collections.shuffle(estado);
        resultado[2] = estado.get(0);

        if (rand.nextBoolean()) {
            List<Movimiento> todos = new ArrayList<>();
            todos.addAll(DatosMovimientos.movimientosAgua());
            todos.addAll(DatosMovimientos.movimientosFuego());
            todos.addAll(DatosMovimientos.movimientosPlanta());
            todos.addAll(DatosMovimientos.movimientosDragon());
            // todos.addAll(DatosMovimientos.movimientosNormal());


            todos.removeIf(m -> m.getNombre().equals(resultado[0].getNombre()) || m.getNombre().equals(resultado[1].getNombre()) || m.getNombre().equals(resultado[2].getNombre()));
            
            if (!todos.isEmpty()){
                Collections.shuffle(todos);
                resultado[3] = todos.get(0);
            }
            else{
                resultado[3] = null;
            }
        }
        else{
            resultado[3]= null;
        }
        
        return resultado;
    }


/**
     * Nombre: generadorMovimientoAleatorio
     * ---------------------------
     * Descripción:
     *   Genera un movimiento aleatorio de todos los tipos de movimientos disponibles.
     *  Cada movimiento tiene su nombre, potencia, precisión, descripción y tipo.
     ****************************************************************************************
    * Parámetros:
    *   - movimientos: Array de movimientos del javaling.
    *   
    **************************************************************************************
    * Retorno:
    *   - Movimiento: Movimiento aleatorio generado.
    ***********************************************************************************
    */
    public static Movimiento generadorMovimientoAleatorio(Movimiento[] movimientos){
        List<Movimiento> todos = new ArrayList<>();
        todos.addAll(DatosMovimientos.movimientosAgua());
        todos.addAll(DatosMovimientos.movimientosFuego());
        todos.addAll(DatosMovimientos.movimientosPlanta());
        todos.addAll(DatosMovimientos.movimientosDragon());
        // todos.addAll(DatosMovimientos.movimientosNormal());

        todos.removeIf(m -> m.getNombre().equals(movimientos[0].getNombre()) || m.getNombre().equals(movimientos[1].getNombre()) || m.getNombre().equals(movimientos[2].getNombre()));
        Collections.shuffle(todos);
        return todos.get(0);
    }
}
