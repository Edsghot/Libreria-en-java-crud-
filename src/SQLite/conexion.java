package SQLite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import Negocio.Persona;

public class conexion {
	Connection cn;
	
	public void conexion() {
		String url = "jdbc:sqlite:Documento.db";
		
		try {
			cn = DriverManager.getConnection(url);
			if(cn != null) {
				JOptionPane.showMessageDialog(null, "Conectado  a Base de datos");
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
	
	public void suscribirse(int dni) {
		try{
			Statement st = cn.createStatement();
			
	
			ResultSet rs = st.executeQuery("SELECT * FROM persona");
			
			if(rs.getBoolean(6) == false && rs.getInt(4)>= 18) {
				PreparedStatement ps = cn.prepareStatement("UPDATE persona SET suscrito = ? WHERE DNI=?;");
				ps.setBoolean(1, true);
				ps.setInt(2, dni);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null,"Suscrito exitosamente!!!");
			}else {
				JOptionPane.showMessageDialog(null,"No se puede suscribir :c");
			}
		
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void listarPersonas() {
		try {
			Statement st = cn.createStatement();
			
			//mostrando personas
			ResultSet rs = st.executeQuery("SELECT * FROM persona");
			
			String cadena = "::::::::::::::::::PERSONA::::::::::::::::::::::::\n";
			while(rs.next()) {
				cadena += "DNI: "+rs.getInt(1)+"\nNombre: "+rs.getString(2)+"\nDireccion: "+rs.getString(3)+"\nedad: "+rs.getString(4)+"\ntipo: "+rs.getString(5)+"\n suscrito: "+rs.getString(6)+"\n\n";
			}
			
			
			JOptionPane.showMessageDialog(null,cadena);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//eliminar
	public void eliminar(int cod){
		int op = Integer.parseInt(JOptionPane.showInputDialog("Que desea eliminar:\n1. manga\n2. libro\n3. Articulo\n4.Revista"));

		switch(op){
			case 1:
				try{
					PreparedStatement ps = cn.prepareStatement("DELETE FROM manga WHERE codigo = ?;");
					ps.setInt(1, cod);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Fue eliminado de la base de datos exitosamente!!!");
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				break;
			case 2:
				try{
					PreparedStatement ps = cn.prepareStatement("DELETE FROM libro WHERE codigo = ?;");
					ps.setInt(1, cod);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Fue eliminado de la base de datos exitosamente!!!");
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				break;
			case 3:
				try{
					PreparedStatement ps = cn.prepareStatement("DELETE FROM articulo WHERE codigo = ?;");
					ps.setInt(1, cod);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Fue eliminado de la base de datos exitosamente!!!");
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				break;
			case 4:
				try{
					PreparedStatement ps = cn.prepareStatement("DELETE FROM revista WHERE codigo = ?;");
					ps.setInt(1, cod);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Fue eliminado de la base de datos exitosamente!!!");
					
				}catch(SQLException e){
					e.printStackTrace();
				}
	
			break;
			default:
				JOptionPane.showMessageDialog(null,"Esa opcion no existe!");
		}

	}
	public void listarRentados() {
		try {
			Statement st = cn.createStatement();
			
			//mostrando libros
			ResultSet rs = st.executeQuery("SELECT * FROM libro");
			
			String cadena = "::::::::::::::::::LIBRO::::::::::::::::::::::::\n";
			
			
				while(rs.next()) {
					if(rs.getBoolean(2) == true) {
					cadena += "Codigo: "+rs.getString(1)+"\nPrestado: "+rs.getString(2)+"\nEditorial: "+rs.getString(3)+"\nAutor: "+rs.getString(4)+"\nTitulo: "+rs.getString(5)+"\n A�o de publicacion: "+rs.getString(6)+"\n\n";
				}
			}
			
			
			//mostrando Articulos
			
			rs = st.executeQuery("SELECT * FROM articulo");
			
			cadena = cadena+"::::::::::::::::::::::::ARTICULOS:::::::::::::::::::::::::\n";
			while(rs.next()) {
				if(rs.getBoolean(2) == true) {
				cadena += "Codigo: "+rs.getString(1)+"\nPrestado: "+rs.getString(2)+"\nEditorial: "+rs.getString(3)+"\nAutor: "+rs.getString(4)+"\nTitulo: "+rs.getString(5)+"\nAño de publicacion: "+rs.getString(6)+"\n";
				}
			}
			
			JOptionPane.showMessageDialog(null,cadena);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(int cod) {
		
			int op = Integer.parseInt(JOptionPane.showInputDialog("Que desea Rentar:\n1. libro\n2. Articulo\n "));

			
			switch(op){
				case 1:
					
					try{
						Statement st = cn.createStatement();
						
				
						ResultSet rs = st.executeQuery("SELECT * FROM libro");
						
						if(rs.getBoolean(2) == false) {
							PreparedStatement ps = cn.prepareStatement("UPDATE libro SET prestado = ? WHERE codigo=?;");
							ps.setBoolean(1, true);
							ps.setInt(2, cod);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null,"Rentado exitosamente!!!");
						}else {
							JOptionPane.showMessageDialog(null,"El documento ya esta rentado :c");
						}
					
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					
					break;
				case 2:
					try{
						Statement st = cn.createStatement();
						
						
						ResultSet rs = st.executeQuery("SELECT * FROM articulo");
						
						if(rs.getString(2) == "0") {
							PreparedStatement ps = cn.prepareStatement("UPDATE articulo SET prestado = ? WHERE codigo=?;");
							ps.setBoolean(1, true);
							ps.setInt(2, cod);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null,"Rentado exitosamente!!!");
						}else {
							JOptionPane.showMessageDialog(null,"El documento ya esta rentado :c");
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
					break;
				
				default:
					JOptionPane.showMessageDialog(null,"Esa opcion no existe!");
			}

		
	}
	public void registrarPersona(int DNI,String nombre,String direccion,int edad,String tipo){
		try{
			PreparedStatement ps = cn.prepareStatement("INSERT INTO persona values(?,?,?,?,?,?)");
			ps.setInt(1,DNI);
			ps.setString(2,nombre);
			ps.setString(3,direccion);
			ps.setInt(4,edad);
			ps.setString(5,tipo);
			ps.setBoolean(6, false);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "La persona fue registrado en la base de datos Exitosamente!!");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void RegistrarLibro(int codigo,boolean prestado,String editorial,String autor,String titulo,int anoP) {
		try {
			PreparedStatement ps = cn.prepareStatement("INSERT INTO libro values(?,?,?,?,?,?);");
			ps.setInt(1, codigo);
			ps.setBoolean(2, prestado);
			ps.setString(3, editorial);
			ps.setString(4, autor);
			ps.setString(5, titulo);
			ps.setInt(6, anoP);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Su libro fue registrado en la base de datos Exitosamente!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void RegistrarManga(int codigo,String anime,String editorial,String autor,String titulo,int anoP,String idioma) {
		try {
			PreparedStatement ps = cn.prepareStatement("INSERT INTO manga values(?,?,?,?,?,?,?)");
			ps.setInt(1, codigo);
			ps.setString(2, anime);
			ps.setString(3, editorial);
			ps.setString(4, autor);
			ps.setString(5, titulo);
			ps.setInt(6, anoP);
			ps.setString(7, idioma);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Su Manga fue registrado en la base de datos Exitosamente!!");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registrarRevista(int codigo,int numero,String mes,int volumen,String editorial,String autor,String titulo,int anoP){
		try{
			PreparedStatement ps = cn.prepareStatement("INSERT INTO revista values(?,?,?,?,?,?,?,?);");
			ps.setInt(1, codigo);
			ps.setInt(2,numero);
			ps.setString(3, mes);
			ps.setInt(4,volumen);
			ps.setString(5,editorial);
			ps.setString(6,autor);
			ps.setString(7,titulo);
			ps.setInt(8,anoP);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Su Revista fue registrado en la base de datos Exitosamente!!");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public void registrarArticulos(int codigo,boolean prestado,String editorial,String autor,String titulo,int anoP){
		try{
			PreparedStatement ps = cn.prepareStatement("INSERT INTO articulo values(?,?,?,?,?,?);");
			ps.setInt(1,codigo);
			ps.setBoolean(2,prestado);
			ps.setString(3,editorial);
			ps.setString(4,autor);
			ps.setString(5,titulo);
			ps.setInt(6,anoP);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Su Articulo fue registrado en la base de datos Exitosamente!!");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public void listar() {
			try {
				Statement st = cn.createStatement();
				
				//mostrando libros
				ResultSet rs = st.executeQuery("SELECT * FROM libro");
				
				String cadena = "::::::::::::::::::LIBRO::::::::::::::::::::::::\n";
				while(rs.next()) {
					cadena += "Codigo: "+rs.getString(1)+"\nPrestado: "+rs.getString(2)+"\nEditorial: "+rs.getString(3)+"\nAutor: "+rs.getString(4)+"\nTitulo: "+rs.getString(5)+"\n A�o de publicacion: "+rs.getString(6)+"\n\n";
				}
				
				
				//mostrando Manga

				rs = st.executeQuery("SELECT * FROM manga");
				
				cadena = cadena+"::::::::::::::::::::::::MANGA:::::::::::::::::::::::::\n";
				while(rs.next()) {
					cadena += "Codigo: "+rs.getString(1)+"\nAnime: "+rs.getString(2)+"\nEditorial: "+rs.getString(3)+"\nAutor: "+rs.getString(4)+"\nTitulo: "+rs.getString(5)+"\nA�o de publicacion: "+rs.getString(6)+"\nIdioma: "+rs.getString(7)+"\n";
				}
				
				//mostrando Articulos
				
				rs = st.executeQuery("SELECT * FROM articulo");
				
				cadena = cadena+"::::::::::::::::::::::::ARTICULOS:::::::::::::::::::::::::\n";
				while(rs.next()) {
					cadena += "Codigo: "+rs.getString(1)+"\nPrestado: "+rs.getString(2)+"\nEditorial: "+rs.getString(3)+"\nAutor: "+rs.getString(4)+"\nTitulo: "+rs.getString(5)+"\nAño de publicacion: "+rs.getString(6)+"\n";
					System.out.println();
				}
				
				//mostrando REVISTA
				rs = st.executeQuery("SELECT * FROM revista");
				cadena = cadena+"::::::::::::::::::::::::REVISTA:::::::::::::::::::::::::\n";
				
				while(rs.next()){
					cadena += "Codigo: "+rs.getString(1)+"\n Numero: "+rs.getString(2)+"\nMes: "+rs.getString(3)+"\nVolumen: "+rs.getString(4)+"\nEditorial: "+rs.getString(5)+"\nAutor: "+rs.getString(6)+"\nTitulo: "+rs.getString(7)+"\nAño de publicacion: "+rs.getString(8)+"\n";
				}
				JOptionPane.showMessageDialog(null,cadena);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		
	}
	public void generandoXML1(String manga1) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document documento = implementation.createDocument(null,"Documentos", null);
			documento.setXmlVersion("1.0");
			
			Element librerias = documento.createElement("librerias");
			Element libreria = documento.createElement("libreria");
			
			Element Manga = documento.createElement("Manga");
			Text textManga = documento.createTextNode(manga1);
			Manga.appendChild(textManga);
			libreria.appendChild(Manga);

			//agregando todo a la libreria
			librerias.appendChild(libreria);
			
			documento.getDocumentElement().appendChild(librerias);
			
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new File("Documento1.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generandoXML2(String libro1) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document documento = implementation.createDocument(null,"Documentos", null);
			documento.setXmlVersion("1.0");
			
			Element librerias = documento.createElement("librerias");
			Element libreria = documento.createElement("libreria");
			
		
			
			Element libro = documento.createElement("libro");
			Text textLibro = documento.createTextNode(libro1);
			libro.appendChild(textLibro);
			libreria.appendChild(libro);
					
					
			
			//agregando todo a la libreria
			librerias.appendChild(libreria);
			
			documento.getDocumentElement().appendChild(librerias);
			
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new File("Documento2.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void generandoXML3(String revista1) {
	
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		
		Document documento = implementation.createDocument(null,"Documentos", null);
		documento.setXmlVersion("1.0");
		
		Element librerias = documento.createElement("librerias");
		Element libreria = documento.createElement("libreria");
				
				
		Element articulo = documento.createElement("articulo");
		Text textArticulo = documento.createTextNode(revista1);
		articulo.appendChild(textArticulo);
		libreria.appendChild(articulo);
		
		
		
		Element revista = documento.createElement("revista");
		Text textRevista = documento.createTextNode("articuloss");
		revista.appendChild(textRevista);
		libreria.appendChild(revista);
		
		//agregando todo a la libreria
		librerias.appendChild(libreria);
		
		documento.getDocumentElement().appendChild(librerias);
		
		Source source = new DOMSource(documento);
		Result result = new StreamResult(new File("Documento3.xml"));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerFactoryConfigurationError e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}