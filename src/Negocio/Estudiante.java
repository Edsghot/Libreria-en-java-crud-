package Negocio;

public class Estudiante extends Persona {

    public Estudiante(int dni, String nombre, String direccion, int edad) {
        super(dni, nombre, direccion, edad);
    }
    
    @Override
    public boolean puedeSuscribirse() {
        return edad > 18;
    }
    
    public int getEdad() {
    	return edad;
    }
    public void setEdad(int edad) {
    	this.edad = edad;
    }
    
}
