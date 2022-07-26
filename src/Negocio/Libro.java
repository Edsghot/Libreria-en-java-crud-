
package Negocio;

/**
 *
 * @author Angel
 */
public class Libro extends Documento implements Rentable, Compra {
        //Atributos
       
	private boolean prestado;

    public Libro(boolean prestado, int codigo, String editorial, String autor, String titulo, int anoPub) {
        super(codigo, editorial, autor, titulo, anoPub);
        this.prestado = prestado;
    }
    public boolean isPrestado(){
        return prestado;
    }
    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }
    
    @Override
    public String toString(){
        return super.toString()+"prestado= "+prestado +"\n"+"-----------"+"\n";
    }

    @Override
    public boolean prestar() {
        return prestado;
    }

    @Override
    public String verMedioDeCompra() {
        return "Usted puede comprar este libro por internet";
    }
        
}
