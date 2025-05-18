package Javaling;

import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

/*
 * Clase que representa un Javaling de tipo Fuego.
 * Hereda de la clase Javaling.
 */
public class Fuego extends Javaling{
    private int enLlamas;
    private boolean estadoEnLlamas;

    /*
     * Constructor de la clase Fuego.
     * Inicializa los atributos de la clase padre Javaling
     * y genera los movimientos específicos de tipo Fuego.
     */
    public Fuego(String nombre){
        super(nombre, 60 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.FUEGO);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.FUEGO);
        this.setMovimientos(movs);
    }

    /**
    * Nombre: getMultiplicadorHabilidad
    * ---------------------------
    * Descripción:
    *   Calcula el multiplicador de habilidad del movimiento
    *   dependiendo de si el Javaling está en un estado de llamas.
    *   Si el movimiento tiene potencia mayor a 0, se incrementa el contador de enLlamas.
    *   Si el contador de enLlamas llega a 3, se activa el estadoEnLlamas
    ****************************************************************************************
    * Parámetros:
    *   - mov: Movimiento que se está ejecutando.
    *   - objetivo: Javaling objetivo del movimiento.
    *   
    **************************************************************************************
    * Retorno:
    *   - double: El multiplicador de habilidad.
    ***********************************************************************************
    */ 
    public double getMultiplicadorHabilidad(Movimiento mov, Javaling ovjetivo){
        if(mov.getPotencia() > 0){
            enLlamas++;
        }
        if(!estadoEnLlamas && enLlamas >= 3){
            estadoEnLlamas = true;
            System.out.println(getNombre() + " ha entrado en llamas (daño x1.2)");
        }
        return estadoEnLlamas ? 1.2 : 1.0;
    }


    /**
    * Nombre: restaurarEstadoCombate
    * ---------------------------
    * Descripción:
    *   Inicializa el estado del Javaling al inicio de su turno.
    *   Resetea el contador de enLlamas y el estadoEnLlamas.
    *   Llama al método de la clase padre para restaurar el estado del combate.
    ****************************************************************************************
    * Parámetros:
    *   - Ninguno.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */ 
    public void restaurarEstadoCombate() {
        super.restaurarEstadoCombate();
        this.enLlamas = 0;
        this.estadoEnLlamas = false;
    }
}
