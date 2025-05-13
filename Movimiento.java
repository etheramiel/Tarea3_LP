public class Movimiento {

    private String nombre;
    private int potencia;
    private int precision;
    private Tipo tipo;
    private boolean esEstado;


    public Movimiento(String nombre, int potencia, int precision, Tipo tipo, boolean esEstado){
        this.nombre = nombre;
        this.potencia = potencia;
        this.tipo = tipo;
        this.esEstado = esEstado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getPrecision() {
        return precision;
    }
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return esEstado;
    }
    public void setEstado(boolean esEstado) {
        this.esEstado = esEstado;
    }

    
    
}
