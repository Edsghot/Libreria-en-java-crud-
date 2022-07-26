
package Negocio;

/**
 *
 * @author Angel
 */
public class Manga extends Documento implements Rentable, Compra {
    //Atributos
	private String idioma;
	private String anime;

    public Manga(String idioma, String anime, int codigo, String editorial, String autor, String titulo, int anoPub) {
        super(codigo, editorial, autor, titulo, anoPub);
        this.idioma = idioma;
        this.anime = anime;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }
	
    @Override
    public String toString(){
        return super.toString()+"idioma= "+idioma +"\n"+"anime= "+anime +"-----------"+"\n";
    }  

    @Override
    public boolean prestar() {
        return idioma.equals("español");
    }

    @Override
    public String verMedioDeCompra() {
        return "Usted puede comprar este manga solamente en fisico";
    }

}
