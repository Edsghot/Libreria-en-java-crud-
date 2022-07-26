
package Negocio;

/**
 *
 * @author Angel
 */
public class Revista extends Documento implements Rentable, Compra
{       //ATRIBUTOS
        private int numero;
	private String mes;
	private int volumen;

    public Revista(int numero, String mes, int volumen, int codigo, String editorial, String autor, String titulo, int anoPub) {
        super(codigo, editorial, autor, titulo, anoPub);
        this.numero = numero;
        this.mes = mes;
        this.volumen = volumen;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
	
    @Override
    public String toString(){
        return super.toString()+"volumen= "+volumen+"\n"+ ", numero= "+numero+"\n"+",mes= "+mes+"\n";
    }

    @Override
    public boolean prestar() {
        return volumen > 50;
    }

    @Override
    public String verMedioDeCompra() {
        return "Usted puede comprar esta revista por paqueteria";
    }
}
