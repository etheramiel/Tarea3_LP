package Javaling;
import java.util.Random;

import Combate.efectividadElementos;
import Entidades.Entrenador;
import Movimientos.GeneradorMovimientos;
import Movimientos.Movimiento;
import Movimientos.Tipo;
import Piso.Piso;

/*
 * Clase abstracta que representa a un Javaling.
 * Contiene atributos y métodos comunes a todos los Javaling.
 */
public abstract class Javaling {
    
    private String nombre;
    protected int hpBase;
    protected int velocidad;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento[] movimientos = new Movimiento[4];
    private int nivelCaptura;

    protected double bonusDanio;
    private int velocidadCombate;
    private int bonusHpTotal;
    private int bonusHpActual;

    /*
     * Constructor de la clase Javaling.
     * Inicializa los atributos del Javaling.
     */
    public Javaling(String name, int hpBase, int velocidad, int nivel, Tipo tipo){
        this.nombre = name;
        this.hpBase = hpBase;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.tipo = tipo;

        this.bonusDanio = 1.0;
        this.velocidadCombate = velocidad;

        this.hpTotal = calcularHPtotal();
        this.hpActual = hpTotal;
    }

    /**
    * Nombre: calcularHPtotal
    * ---------------------------
    * Descripción:
    *   Calcula el HP total del Javaling en función de su nivel y HP base.
    *   La fórmula utilizada es: (2 * HP base * nivel) / 100 + nivel + 10.
    *   Esta fórmula es común a todos los Javaling y se utiliza para determinar
    *   el HP total al inicio del combate y al subir de nivel.
    ****************************************************************************************
    * Parámetros:
    *   - Ninguno.
    *   
    **************************************************************************************
    * Retorno:
    *   - int: El HP total calculado.
    ***********************************************************************************
    */
    public int calcularHPtotal(){
        double nuevoHpTotal = (2*hpBase * nivel) / 100.0 + (double)nivel + 10.0;
        return (int) nuevoHpTotal;
    }

    //Getters y Setters ----------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHpBase() {
        return hpBase;
    }
    public void setHpBase(int hpBase) {
        this.hpBase = hpBase;
    }

    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getHpTotal() {
        return hpTotal;
    }
    public void setHpTotal(int hpTotal) {
        this.hpTotal = hpTotal;
    }

    public int getHpActual() {
        return hpActual;
    }
    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }
    public void setMovimientos(Movimiento[] movimientos) {
        this.movimientos = movimientos;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.hpTotal = calcularHPtotal();
        this.hpActual = this.hpTotal;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNivelCaptura() {
        return nivelCaptura;
    }
    public void setNivelCaptura(int nivelCaptura) {
        this.nivelCaptura = nivelCaptura;
    }

    public double getBonusDanio() {
        return bonusDanio;
    }
    public void setBonusDanio(double bonusDanio) {
        this.bonusDanio = bonusDanio;
    }

    public int getVelocidadCombate() {
        return velocidadCombate;
    }
    public void setVelocidadCombate(int velocidadCombate) {
        this.velocidadCombate = velocidadCombate;
    }
    
    public int getBonusHpActual() {
        return bonusHpActual;
    }
    public void setBonusHpActual(int bonusHpActual) {
        this.bonusHpActual = bonusHpActual;
    }

    public int getBonusHpTotal() {
        return bonusHpTotal;
    }
    public void setBonusHpTotal(int bonusHpTotal) {
        this.bonusHpTotal = bonusHpTotal;
    }
    //----------------------------------------------------------------------------------


    /**
     * Nombre: atacar
     * ---------------------------
     * Descripción:
     *   Realiza un ataque a otro Javaling utilizando un movimiento específico.
     *   Calcula el daño infligido y lo aplica al objetivo.
     ****************************************************************************************
     * Parámetros:
     *   - objetivo: El Javaling objetivo del ataque.
     *   - indiceMov: El índice del movimiento a utilizar en el ataque.
     **************************************************************************************
     * Retorno:
     *   - int: El daño total infligido al objetivo.
     ***********************************************************************************
     */
    public int atacar(Javaling objetivo, int indiceMov){
        Movimiento mov = movimientos[indiceMov];
        if(mov == null){
            System.out.println("No tiene un movimiento en esa posicion");
            return 0;
        }

        int chance = new Random().nextInt(100);
        if (chance >= mov.getPrecision()) {
            System.out.println(nombre + " falló el ataque " + mov.getNombre() + "...");
            return 0;
        }

        if(mov.getEstado()){
            aplicarMovimientoEstado(mov, objetivo);
            return 0;
        }
        int nivel = this.nivel;
        int hpBase = this.hpBase;
        int potencia= mov.getPotencia();
        double habilidad = getMultiplicadorHabilidad(mov, objetivo) * bonusDanio; 

        double stab = (mov.getTipo() == this.tipo) ? 1.5 : 1.0;
        double efectividad = efectividadElementos.getEfectividad(mov.getTipo(), objetivo.getTipo());


        double base = (((((2 * nivel / 5.0) + 2) * potencia * (hpBase/100.0)))/50.0) + 2;
        int danioTotal = (int)(base * stab * efectividad * habilidad);

        objetivo.recibirDaño(danioTotal); 

        System.out.println(nombre + " usó " + mov.getNombre() + " contra " + objetivo.getNombre());
        System.out.println("Daño total inflingido: " + danioTotal + "\n");
        return (int)danioTotal;
    }

    /**
     * Nombre: recibirDaño
     * ---------------------------
     * Descripción:
     *   Aplica el daño recibido al HP actual del Javaling.
     *   Si el HP actual se vuelve negativo, se establece en 0.
     ****************************************************************************************
     * Parámetros:
     *   - danioTotal: El daño total recibido.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void recibirDaño(int danioTotal){
        this.hpActual -= danioTotal;
        if (hpActual < 0) {
            hpActual = 0;
        }
    }

    /**
     * Nombre: printMovimientos
     * ---------------------------
     * Descripción:
     *   Imprime los movimientos del Javaling en la consola.
     ****************************************************************************************
     * Parámetros:
     *   - Ninguno.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void printMovimientos(){
        for (int i = 0; i < movimientos.length; i++) {
            System.out.print((i + 1) + ". ");
            if (movimientos[i] != null) {
                System.out.println(movimientos[i].getNombre() + " | Tipo: " + movimientos[i].getTipo() + " | Potencia: " + movimientos[i].getPotencia() + " | Estado: " + (movimientos[i].getEstado() ? "Sí" : "No"));
            } else {
                System.out.println("Movimiento no asignado.");
            }
        }

    }

    /**
     * Nombre: printDatos
     * ---------------------------
     * Descripción:
     *   Imprime los datos del Javaling en la consola.
     ****************************************************************************************
     * Parámetros:
     *   - Ninguno.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void printDatos(){
        System.out.println(">Nombre: " + this.nombre);
        System.out.println(">Tipo: " + this.tipo);
        System.out.println(">Nivel: " + this.nivel);
        System.out.println(">HP Total: " + this.hpTotal);
        System.out.println(">Velocidad: " + this.velocidad);
    }
    

    /**
     * Nombre: subirNivel
     * ---------------------------
     * Descripción:
     *   Aumenta el nivel del Javaling y recalcula su HP total.
     *   Si el HP actual es 0, se establece en 0.
     *   Si el nuevo HP actual excede el HP total, se ajusta al HP total.
     *   Si el nivel de captura es mayor o igual al nuevo nivel + 7,
     *   se genera un nuevo movimiento aleatorio.
     ****************************************************************************************
     * Parámetros:
     *   - nivel: El nivel a aumentar.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void subirNivel(int nivel){
        this.nivel += nivel;
        
        int hpViejo = this.hpTotal;
        this.hpTotal = calcularHPtotal();

        if (this.hpActual == 0) {
            this.hpActual = 0;
        }
        else{
            this.hpActual = hpActual + (this.hpTotal - hpViejo);
        }

        

        if(this.hpActual > this.hpTotal){
            this.hpActual = this.hpTotal;
        }

        if(movimientos[3] == null && nivelCaptura >= nivel + 7){
            Movimiento nuevo = GeneradorMovimientos.generadorMovimientoAleatorio(movimientos);
            System.out.println(nombre + "ha aprendido: " + nuevo.getNombre());
        }
    }

    /**
     * Nombre: generadorSalvaje
     * ---------------------------
     * Descripción:
     *   Genera un Javaling salvaje aleatorio en función del piso.
     *   El tipo y nivel se determinan aleatoriamente.
     ****************************************************************************************
     * Parámetros:
     *   - piso: El piso en el que se encuentra el Javaling salvaje.
     **************************************************************************************
     * Retorno:
     *   - Javaling: El Javaling salvaje generado.
     ***********************************************************************************
     */
    public static Javaling generadorSalvaje(Piso piso){
        Random rand = new Random();

        Tipo tipo = (rand.nextInt(100) < 3) ? Tipo.DRAGON : elegirTipoAleatorio(rand);
        String nombre = Entrenador.generarNombrejavaling();

        Javaling j = switch (tipo) {
            case AGUA -> new Agua(nombre);
            case FUEGO -> new Fuego(nombre);
            case PLANTA -> new Planta(nombre);
            case DRAGON -> new Dragon(nombre);
            default -> new Agua(nombre);
        };

        int base = (int) Math.floor(1.3 * piso.getNumPiso());
        int nivel = base + rand.nextInt(7) - 3;
        if (nivel < 1) {
            nivel = 1;
        }

        j.setNivel(nivel);
        j.setNivelCaptura(nivel);

        Movimiento[] movimientos = GeneradorMovimientos.generadorMovimientos(tipo);
        j.setMovimientos(movimientos);
        
        return j;
    }

    /**
     * Nombre: elegirTipoAleatorio
     * ---------------------------
     * Descripción:
     *   Elige un tipo aleatorio de Javaling.
     ****************************************************************************************
     * Parámetros:
     *   - rand: Objeto Random para generar números aleatorios.
     **************************************************************************************
     * Retorno:
     *   - Tipo: El tipo aleatorio elegido.
     ***********************************************************************************
     */
    public static Tipo elegirTipoAleatorio(Random rand){
        Tipo[] tipos = {Tipo.AGUA, Tipo.FUEGO, Tipo.PLANTA};
        return tipos[rand.nextInt(tipos.length)];
    }

    /**
     * Nombre: getMultiplicadorHabilidad
     * ---------------------------
     * Descripción:
     *   Calcula el multiplicador de habilidad del movimiento
     *   dependiendo del tipo de Javaling y el objetivo.
     ****************************************************************************************
     * Parámetros:
     *   - mov: Movimiento que se está ejecutando.
     *   - objetivo: Javaling objetivo del movimiento.
     **************************************************************************************
     * Retorno:
     *   - double: El multiplicador de habilidad.
     ***********************************************************************************
     */
    public double getMultiplicadorHabilidad(Movimiento mov, Javaling objetivo) {
    return 1.0;
    }

    /**
     * Nombre: aplicarMovimientoEstado
     * ---------------------------
     * Descripción:
     *   Aplica el efecto del movimiento de estado al objetivo.
     ****************************************************************************************
     * Parámetros:
     *   - mov: Movimiento que se está ejecutando.
     *   - objetivo: Javaling objetivo del movimiento.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void iniciarTurno() {
    }

    /**
     * Nombre: restaurarEstadoCombate
     * ---------------------------
     * Descripción:
     *   Restaura el estado del Javaling al inicio de su turno.
     *   Resetea el bonus de daño y la velocidad de combate.
     ****************************************************************************************
     * Parámetros:
     *   - Ninguno.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    public void restaurarEstadoCombate() {
    this.bonusDanio = 1.0;
    this.velocidadCombate = this.velocidad;
    }

    /**
     * Nombre: aplicarMovimientoEstado
     * ---------------------------
     * Descripción:
     *   Aplica el efecto del movimiento de estado al objetivo.
     ****************************************************************************************
     * Parámetros:
     *   - mov: Movimiento que se está ejecutando.
     *   - objetivo: Javaling objetivo del movimiento.
     **************************************************************************************
     * Retorno:
     *   - void: No retorna nada.
     ***********************************************************************************
     */
    protected void aplicarMovimientoEstado(Movimiento mov, Javaling objetivo) {
    
        switch (mov.getNombre()) {
            case "Refuerzo Acuatico" -> {
                int porcentaje = (this instanceof Agua agua && agua.getOleaje()) ? 60 : 30;
                int curado = (getHpTotal() * porcentaje) / 100;
                setHpActual(Math.min(getHpActual() + curado, getHpTotal()));
                System.out.println(getNombre() + " se curó " + curado + " con Refuerzo Acuático.\n");
            }
            case "Avivar" -> {
                int selfDamage = (int)(getHpActual() * 0.05);
                setHpActual(getHpActual() - selfDamage);
                setVelocidad((int)(getVelocidad() * 1.15));
                bonusDanio *= 1.15;
                System.out.println(getNombre() + " se avivó: velocidad y daño aumentados.\n");
            }
            case "Crecimiento" -> {
                int nuevoHpTotal = (int)(getHpTotal() * 1.2);
                int aumento = nuevoHpTotal - getHpTotal();
                setHpTotal(nuevoHpTotal);
                setHpActual(Math.min(getHpActual() + aumento, getHpTotal()));
                System.out.println(getNombre() + " creció: HP aumentado.\n");
            }
            case "Danza Dragon" -> {
                setVelocidad((int)(getVelocidad() * 1.5));
                bonusDanio *= 1.5;
                System.out.println(getNombre() + " usó Danza Dragón: velocidad y daño aumentaron.\n");
            }   
            }
    }


}
