package Datos;

import Negocio.Compra;
import Negocio.Documento;
import Negocio.Persona;
import Negocio.Rentable;
import SQLite.conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.JOptionPane;

/**
 *
 * @author u19219160_Sanchez
 */
public class Libreria {

    private static Libreria libreria;
    
    //Implementacion de LIST
    private ArrayList<Documento> listaDocumentos = this.<Documento>supplier().get();//Le decimos ir a mi supplier y obtener documento,en este caso el get es el metodo que estamos sobreescribiendo,en la linea 150
    private ArrayList<Persona> personas = this.<Persona>supplier().get();
    //HasMap:Sirve para agregar elementos pero con la diferencia que tiene <llave y valor>
    //Llave vendria ser clase abstracta(Documento)y en valor en este caso una interfaz rentable
    //Cuando queramos agregar mediante HasMap agregamos la valle y el valor.
    private HashMap<Documento, Rentable> listaPrestados = new HashMap<>();
    //Acepta un conjunto escaso de personas,se pueden agregar siempre y cuando no tengan la misma referencia,No se puede volver a crear la misma persona,solamente
    //dejaria crear la anterior y el ultimo que ingrese no lo agrega.
    private Set<Persona> personasSuscritas = new HashSet<>();
    
    
    //SINGLETON:Lo utilizamos para solo tener una Libreria y no tener a varias.
    //Lo que hace es:Crear una sola instancia o referencia para las demas clases,y las veces que las necesites
    //Digamos que en el menu libreria quisieramos crear una referencia
    //Ejemplo Libreria=new Libreria();//No se podria instanciar la clase de esa forma ya que estoy creando un referencia (new(libreria))
    //Para implementar el patron singleton,colocamos el contructor y tener un atributo privado de tipo estatico(static),del mismo tipo que se le aplica al SINGLETON
    //"public static Libreria singleton()"
    private Libreria() {}
    //Cuando necesitemos de instancia llamamos al metodo estatico
    public static Libreria singleton() {//Este metodo devuelve libreria
        if(libreria == null) {//Si la libreria es nula osea no se creo ninguna instancia 
            libreria = new Libreria();//Se crea una nueva instancia
        }
        return libreria;//Y llama de vuelta a Libreria
    }//Cuando retorne recien podre llamar e ingresar a los metodos creados

    public ArrayList<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void añadirDocumento(Documento m) {
        listaDocumentos.add(m);
    }

    public void setListaDocumentos(ArrayList<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public void agregarDoc(Documento doc) {
        boolean repetido = false;
        for (Documento d : listaDocumentos) {
            if (d.getCodigo() == doc.getCodigo()) {
                repetido = true;
                JOptionPane.showMessageDialog(null, "no se puede agregar ya que el nro del documento ya existe");
                break;
            }
        }
        if (repetido == false) {
            listaDocumentos.add(doc);
            JOptionPane.showMessageDialog(null, "Documento agregado satisfactoriamente");
        }
    }

    public void eliminarDoc(int cod) {
        if(listaDocumentos.removeIf(d -> d.getCodigo() == cod)) {
            JOptionPane.showMessageDialog(null, "Documento Eliminado");
        }else {
            JOptionPane.showMessageDialog(null, "no existe el documento");
        }
    }

    public void listar() {
        String info = "";
        for (Documento d : listaDocumentos) {
            info += d.toString() + "\n";
        }
        
        JOptionPane.showMessageDialog(null, info);
    }

  

    public void verMediosDeCompra(int codigo) {
        Documento doc = buscar(codigo);
        if (doc != null && doc instanceof Compra) {
            JOptionPane.showMessageDialog(null, "Usted comprar este libro por " + ((Compra) doc).verMedioDeCompra());
        }
    }

    public void agregarPersona(Persona persona) {
        if(persona != null)
            personas.add(persona);
    }
    
    public void suscribirse(int dni) {
    	new conexion().suscribirse(dni);
        
    }

    private Documento buscar(int cod) {//Se utiliza aqui
        return buscar(listaDocumentos, crearPreditcateDoc(cod)).apply(cod);//El codigo
    }

    private Persona buscarPersona(int dni) {
        return buscar(personas, crearPredicatePersona(dni)).apply(dni);//El dni
    }
    //1)PREDICATE:Tiene un metodo buleano,que es una interfaz que se tiene que implementar.El metodo es boolean test(T,t);
    private Predicate<Documento> crearPreditcateDoc (int cod) {
        //Anteriormente ubiera esta de esta manera pero con la ayuda de los lambda simplifica de manera asombrosa el codigo
        //return new Predicate<Documento>(){
        //    @Override
        //    public boolean test(Documento doc){
        //        return doc.getCodigo()==cod;
        //    }
        //};
        return doc -> doc.getCodigo() == cod;//Lo que hacemos aqui es implementar esa interzas pero de forma funcional,Cuando se vea una flechita,significa que son lambda
    }//En este caso creamos una implemetacion de una interfaz(Predicate) y donde le pasamos un tipo<Documento>,para sobreescribir el mentodo boolean.El == regresa un boolean
    
    //2)PREDICATE:En esta caso tambien implementamos la interzas(Predicate),donde le pasamos un tipo <Persona>,para sobreescribir el mentodo boolean.El == regresa un boolean
    private Predicate<Persona> crearPredicatePersona(int dni) {
        return persona -> persona.getDni() == dni;
    }
    //SUPPLIER:Es una interfaz funcional ,tambien es generica y que el metodo que tiene y que tenemos que sobreescribir es el T get();
    private <T> Supplier<ArrayList<T>> supplier() {
        return ArrayList::new;//Se regresa un nuevo tipo de arraylist o una nueva referencia y se lo asignamos al arraylist de la linea 26,Practicamente en la linea 26.En nuestro arraylist nuestro <Documento> se llame listDocumento le decimos... linea 26       
    }
    //FUNCTION:Es una interfaz funcional,tambien se puede utilziar una lambda y tiene un metodo R apply(T,t) en el cual tenemos que sobreescribir
    //T es un tipo de entrada para la funcion
    //R es un tipo de resultado para la funcion
    private <T> Function<Integer, T> buscar(Collection<T> collection, Predicate<T> predicate) {//Entonces creamos un metodo donde la collecion tambien es generico,un predicate que tambien es generico,el tipo de entrada es de tipo <Integer>,e de salida es generico<T>
        return id -> collection.stream().filter(predicate).findFirst().orElse(null);//Entonces creamos el metodo buscar para recorrer la coleccion.stream(),filtrar(predicate)pasandole el predicate en el metodo filter
    }//El id es el tipo de entrada,para buscar por su dni en este caso esta por id ,//findFirst si encuentra primero y si no orElse(null)Retorna null
    //Si fuera libro (La collection es una lista de documento el predicate seria buscar por su id de documento)
    //Si fuera una persona(El collection es una lista de persona el predicate seria buscar por su id de dni)
    //y como es generico funciona para ambos
}
