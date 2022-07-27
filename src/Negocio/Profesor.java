package Negocio;

public class Profesor extends Persona {

    private String escuela;

    public Profesor(int dni, String nombre, String direccion, int edad, String escuela) {
        super(dni, nombre, direccion, edad);
        this.escuela = escuela;
    }
    
    @Override
    public boolean puedeSuscribirse() {
        return escuela.equals("UTP");
    }
    
}
