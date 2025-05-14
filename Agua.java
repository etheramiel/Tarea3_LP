public class Agua extends Javaling{

    private boolean oleaje;

    public Agua(String nombre){
        super(nombre, 55 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.AGUA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.AGUA);
        this.setMovimientos(movs);
    }
}
