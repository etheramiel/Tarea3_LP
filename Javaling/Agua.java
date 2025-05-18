package Javaling;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;

/**
 * Clase Agua que representa un tipo de Javaling.
 * Hereda de la clase Javaling.
 */
public class Agua extends Javaling{

    private boolean oleaje = false;
    private boolean fueAtacadoTurnoAnterior = false;

    //Getters y setters--------------
    public boolean getOleaje(){
        return oleaje;
    }
    //-------------------------------

    /*
     * Constructor de la clase Agua.
     * Inicializa los atributos de la clase padre Javaling
     * y genera los movimientos específicos de tipo Agua.
     */
    public Agua(String nombre){
        super(nombre, 55 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.AGUA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.AGUA);
        this.setMovimientos(movs);
    }


    /**
    * Nombre: getMultiplicadorHabilidad
    * ---------------------------
    * Descripción:
    *    Calcula el multiplicador de habilidad del movimiento
    *    dependiendo de si el Javaling está en un estado de oleaje.
    *    Si el Javaling fue atacado en el turno anterior, se activa el oleaje
    *    y se devuelve un multiplicador de 1.15.
    ****************************************************************************************
    * Parámetros:
    *   -  mov: Movimiento que se está ejecutando.
    *   -  objetivo: Javaling objetivo del movimiento.
    *   
    **************************************************************************************
    * Retorno:
    *   - double: El multiplicador de habilidad.
    ***********************************************************************************
    */
    public double getMultiplicadorHabilidad(Movimiento mov, Javaling objetivo) {
    if (oleaje) {
        oleaje = false;
        return 1.15;
    }
        return 1.0;
    }


    /**
    * Nombre: recibirDaño
    * ---------------------------
    * Descripción:
    *   Recibe el daño infligido al Javaling y determina si fue atacado
    *   en el turno anterior. Si fue atacado, se activa el estado de oleaje.
    *   Se llama al método de la clase padre para aplicar el daño.
    ****************************************************************************************
    * Parámetros:
    *   - danio: Daño recibido por el Javaling.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void recibirDaño(int danio) {
        super.recibirDaño(danio);
        fueAtacadoTurnoAnterior = danio > 0;
    }


    /**
    * Nombre: iniciarTurno
    * ---------------------------
    * Descripción:
    *   Inicializa el turno del Javaling. Si fue atacado en el turno anterior,
    *   se activa el estado de oleaje. Se llama al método de la clase padre
    *   para iniciar el turno.
    *   Se restablece el estado de fueAtacadoTurnoAnterior a false.
    *   Esto permite que el Javaling pueda beneficiarse del oleaje
    ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void iniciarTurno() {
        oleaje = fueAtacadoTurnoAnterior;
        fueAtacadoTurnoAnterior = false;
    }


    /**
    * Nombre: restaurarEstadoCombate
    * ---------------------------
    * Descripción:
    *   Restaurar el estado del combate del Javaling.
    *   Se llama al método de la clase padre para restaurar el estado
    *   y se restablecen los estados de oleaje y fueAtacadoTurnoAnterior a false.
    ****************************************************************************************
    * Parámetros:
    *   - void: No recibe parámetros.
    *   
    **************************************************************************************
    * Retorno:
    *   - void: No retorna nada.
    ***********************************************************************************
    */
    public void restaurarEstadoCombate() {
    super.restaurarEstadoCombate();
    this.oleaje = false;
    this.fueAtacadoTurnoAnterior = false;
    }
}
