public abstract class Javaling {
    
    private String nombre;
    protected int hpBase;
    protected int velocidad;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento[] movimientos = new Movimiento[4];

    public int atacar(Javaling objetivo, int indiceMov){

        return 0;
    }

    public void subirNivel(int nivel){
        
    }
}
