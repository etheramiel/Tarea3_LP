package Movimientos;
public class Movimiento {

    private String nombre;
    private int potencia;
    private int precision;
    private Tipo tipo;
    private boolean esEstado;
    private String descripcion;

    //Constructor
    public Movimiento(String nombre, int potencia, int precision, boolean esEstado, Tipo tipo, String descripcion){
        this.nombre = nombre;
        this.potencia = potencia;
        this.precision = precision;
        this.tipo = tipo;
        this.esEstado = esEstado;
        this.descripcion = descripcion;
    }

    //Getters y Setters --------------------------------------------------------------------------------------------
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

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //-------------------------------------------------------------------------
    
    
}
