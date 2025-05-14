public abstract class Javaling {
    
    private String nombre;
    protected int hpBase;
    protected int velocidad;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento[] movimientos = new Movimiento[4];


    public Javaling(String name, int hpBase, int velocidad, int nivel, Tipo tipo){
        this.nombre = name;
        this.hpBase = hpBase;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.tipo = tipo;

        this.hpTotal = hpBase;
        this.hpActual = hpBase;
    }


    public int calcularHPtotal(){
        return(2*hpBase * nivel) / 100 + nivel + 10; 
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
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    //----------------------------------------------------------------------------------

    public int atacar(Javaling objetivo, int indiceMov){

        return 0;
    }

    public void subirNivel(int nivel){
        
    }
}
