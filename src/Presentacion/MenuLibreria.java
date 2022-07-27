package Presentacion;

import Datos.Libreria;
import Negocio.Documento;
import javax.swing.JOptionPane;

import Negocio.Articulos;
import Negocio.Estudiante;
import Negocio.Libro;
import Negocio.Manga;
import Negocio.Persona;
import Negocio.Profesor;
import Negocio.Revista;
import SQLite.conexion;

public class MenuLibreria {
	 public static conexion co = new conexion();
    private Libreria libreria;
    
    public static void main(String[] args) {
    	co.conexion();
        new MenuLibreria().menu();
    }

    public MenuLibreria() {
        libreria = Libreria.singleton();
    }
    
    private void menu() {
    	int codigo1;
        String op;
        do {
            op = JOptionPane.showInputDialog("[1] Agregar documento"
                    + "\n[2] Eliminar documento\n[3] Rentar\n[4] Ver medios de compra "
                    + "\n[5] Agregar persona\n[6] Suscribirse \n[7] Listar \n[8] listar documentos Rentados\n[9] Listar Personas\n[10] Salir");
            switch (op) {
                case "1":
                    libreria.añadirDocumento(crearDocumento());
                    break;
                case "2":
                	codigo1 = Integer.valueOf(JOptionPane.showInputDialog("Numero de codigo:"));
                	co.eliminar(codigo1);
                    //libreria.eliminarDoc(codigo1);
                    break;
                case "3":
                		codigo1 = Integer.valueOf(JOptionPane.showInputDialog("Numero de codigo:"));
                    	co.update(codigo1);
                    break;
                case "4":
                    libreria.verMediosDeCompra(Integer.valueOf(JOptionPane.showInputDialog("Codigo del documento a verificar:")));
                    break;
                case "5":
                    libreria.agregarPersona(crearPersona());
                    break;
                case "6":
                    co.suscribirse(Integer.valueOf(JOptionPane.showInputDialog("DNI de la persona a suscribir: ")));
                    break;
                case "7":
                	co.listar();
                   // libreria.listar();
                	break;
                case "8":
                	co.listarRentados();
                    break;
                case "9":
                	co.listarPersonas();
                	break;
                case "10":
                    JOptionPane.showMessageDialog(null, "ADIOOS!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
            }

        } while (!op.equals("10"));
    }

    private Documento crearDocumento() {
        Documento doc = null;
        int cod = Integer.valueOf(JOptionPane.showInputDialog("Ingrese codigo"));
        String editorial = JOptionPane.showInputDialog("Ingrese editorial");
        String autor = JOptionPane.showInputDialog("Ingrese autor");
        String titulo = JOptionPane.showInputDialog("Ingrese titulo");
        int anoPub = Integer.valueOf(JOptionPane.showInputDialog("AÃ±o publicacion: "));
        String tipo = JOptionPane.showInputDialog("Ingrese tipo\n[Libro, Manga, Revista, Articulo]");
        switch (tipo.toLowerCase()) {
            case "libro":
                doc = new Libro(false, cod, editorial, autor, titulo, anoPub);
                co.RegistrarLibro(cod, false, editorial, autor, titulo, anoPub);
                co.generandoXML2(titulo);
                break;
            case "manga":
                String idioma = JOptionPane.showInputDialog("Ingrese idioma");
                String anime = JOptionPane.showInputDialog("Ingrese anime");
                doc = new Manga(idioma, anime, cod, editorial, autor, titulo, anoPub);
                co.RegistrarManga(cod, anime, editorial, autor, titulo, anoPub, idioma);
                co.generandoXML1(titulo);
                break;
            case "revista":
                int numero = Integer.valueOf(JOptionPane.showInputDialog("Ingrese numero"));
                String mes = JOptionPane.showInputDialog("Ingrese mes");
                int volumen = Integer.valueOf(JOptionPane.showInputDialog("Ingrese volumen"));
                doc = new Revista(numero, mes, volumen, cod, editorial, autor, titulo, anoPub);
                co.registrarRevista(cod, numero, mes, volumen, editorial, autor, titulo, anoPub);
                co.generandoXML3(titulo);
                break;
            case "articulo":
                doc = new Articulos(false, cod, editorial, autor, titulo, anoPub);
                co.registrarArticulos(cod, false, editorial, autor, titulo, anoPub);
                break;
            default:
                doc = null;
        }
        return doc;
    }

    private Persona crearPersona() {
        Persona persona = null;
        int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese dni: "));
        String nombre = JOptionPane.showInputDialog("Ingrese nombre: ");
        String direccion = JOptionPane.showInputDialog("Ingrese direccion: ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad: "));
        switch (JOptionPane.showInputDialog("Ingrese tipo: [Alumno, Profesor]").toLowerCase()) {
            case "alumno":
                persona = new Estudiante(dni, nombre, direccion, edad);
                co.registrarPersona(dni, nombre, direccion, edad, "alumno");
                break;
            case "profesor":
                persona = new Profesor(dni, nombre, direccion, edad, JOptionPane.showInputDialog("Ingrese nombre de su escuela: "));
                co.registrarPersona(dni, nombre, direccion, edad,"profesor");
                break;
            default:
                persona = null;
        }
        return persona;
    }

}
