
package Negocio;

/**
 *
 * @author Angel
 */
public class Articulos extends Documento{ 
    //Atributos Articulos
	private boolean prestado;

    public Articulos(boolean prestado, int codigo, String editorial, String autor, String titulo, int anoPub) {
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
	
	
}
