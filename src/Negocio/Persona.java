package Negocio;

public abstract class Persona {
    
    protected int dni;
    protected String nombre;
    protected String direccion;
    protected int edad;

    public Persona(int dni, String nombre, String direccion, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", edad=" + edad + '}';
    }
    
    public abstract boolean puedeSuscribirse();

    
}
