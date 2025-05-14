public class Planta extends Javaling{
    private int fotosintesis;

    public Planta(String nombre){
        super(nombre, 65 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.PLANTA);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.PLANTA);
        this.setMovimientos(movs);
    }
}
