public class Fuego extends Javaling{
    private int enLlamas;

    public Fuego(String nombre){
        super(nombre, 60 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.FUEGO);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.FUEGO);
        this.setMovimientos(movs);
    }
}
