public class Dragon extends Javaling{
    private boolean multiescamas;

    public Dragon(String nombre){
        super(nombre, 70 + (int)(Math.random() * 11) - 5 , 200 + (int)(Math.random() * 399) - 199 , 1, Tipo.DRAGON);
        Movimiento[] movs = GeneradorMovimientos.generadorMovimientos(Tipo.DRAGON);
        this.setMovimientos(movs);
    }
}
