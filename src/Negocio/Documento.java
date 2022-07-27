
package Negocio;

/**
 *
 * @author Angel
 */
public abstract class Documento {
    //A la superclase le cambie a abstract
	//SuperClase o denominada Clase Padre
	//Atributos de la Clase Material
        protected int codigo;
	protected String editorial;
	protected String autor;
	protected String titulo;
        protected int anoPub;
        
	//Constructores
	public Documento(int codigo, String editorial, String autor, String titulo, int anoPub) {
        this.codigo = codigo;
        this.editorial = editorial;
        this.autor = autor;
        this.titulo = titulo;
        this.anoPub = anoPub;
	}

    //Metodos Getter and Setter

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }
        
        
        @Override
        public String toString(){
            return "codigo= "+ codigo + "\n"+ "titulo= "+ titulo + "\n"+ "autor= "+autor +"\n"+ "editorial= "+ 
                    editorial +"\n"+"aniopublicacion="+ anoPub+"\n";
        }
}
