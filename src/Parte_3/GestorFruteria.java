package Parte_3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestorFruteria {
	
	//res\registro.xml
	
	private File archivo_xml;
	
	private int cont_fuera_stock=0;
	
	private int id_articulo=1;
	
	public GestorFruteria(File archivo_xml) 
	{
		
		this.archivo_xml = archivo_xml;
		
	}
	
	//Ejemplo de clase para crear un elemento
	public void crearElemento(String nombre,long precio) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
		Element elementoPadre = doc.createElement("articulo");
		elementoPadre.appendChild(doc.createElement("nombre"));
		elementoPadre.appendChild(doc.createElement("precio"));
		elementoPadre.getChildNodes().item(0).appendChild(doc.createTextNode(nombre));
		//convertir el precio a string
		elementoPadre.getChildNodes().item(1).appendChild(doc.createTextNode(String.valueOf(precio)));
		doc.getElementsByTagName("en-stock").item(0).appendChild(elementoPadre);
		
		Transformer transf = TransformerFactory.newInstance().newTransformer();
		DOMSource origen = new DOMSource(doc);
		StreamResult destino = new StreamResult(archivo_xml);
		
		transf.transform(origen, destino);
		
		
		
		}
	
	public void modificarElemento(String articulo, float precio ) 
	{
	        try {
	        	
	        	  //Instanciamos un nuevo objeto Document
	        	  Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	
	        	  //Lista de nodos articulo
	              NodeList articulos = doc.getElementsByTagName("articulo");
	              
	              //Instanciamos la variable existe
	              boolean existe = false;
	
	              //Bucle que recorre la longitud del nodelist
	              for(int i = 0; i < articulos.getLength(); i++) {
	
	            	  //Se creará un objeto de tipo nodo por cada elemento del nodelist
	                  Node nodo_articulo = articulos.item(i);
	
	                  //Se comprueba si el nodo es de tipo elemento (es decir, no es un atributo, por ejemplo)
	                  if(nodo_articulo.getNodeType() == Node.ELEMENT_NODE) {
	                	  
	                	//Si es así, el nodo se castea a elemento para poder hacer las siguientes comprobaciones
	                    Element elemento = (Element) nodo_articulo;
	
	                    //Si el texto del primer elemento con la etiqueta nombre coincide con el nombre de artículo introducido
	                    //se cambia el contenido del elemento precio por el nuevo precio que se ha introducido
	                    if(elemento.getElementsByTagName("nombre").item(0).getTextContent().equalsIgnoreCase(articulo)) 
	                    {
	                    	elemento.getElementsByTagName("precio").item(0).setTextContent(String.valueOf(precio));
	                    	//Se informa al usuario
	                        System.out.println("Precio cambiado correctamente");
	                        //La variable existe pasa a ser true
	                        existe = true;
	                        //Se crea un objeto TransformerFactory
	                        Transformer transf = TransformerFactory.newInstance().newTransformer();
	                        //Se crea un objeto DOMSource para determinar cuál es el documento modificado que vamos a guardar
	      	                DOMSource origen = new DOMSource(doc);
	      	                //Se crea un objeto StreamResult para determinar cuál es el archivo en el que vamos a guardar el
	      	                //xml modificado
	      	                StreamResult destino = new StreamResult(archivo_xml);
	      	                //Se llama al método correspondiente para que se guarde el xml modificado
	      	                transf.transform(origen,destino);
	                    }
	
	                  }
	                }
	              
	              //Si la variable existe es false, se indica al usuario que el artículo que ha introducido no existe
	              if(existe==false) 
	              {
	            	  
	            	  System.out.println("\nLo sentimos, el artículo introducido no existe.\n");
	            	  
	              }

	        }catch(Exception e) {
	              e.printStackTrace();
	            }
	}
	
	public void fueraDeStock (String articulo) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{
		
		   //Instanciamos las variables que necesitamos para almacenar el valor del elemento que vamos a mover
		   String nombre_articulo = "", precio = "";

		   //Documento fuente que usaremos para modificar
	       Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	       
	       //Instanciamos un nodelist que guarde todos los elementos con la etiqueta articulo
	       NodeList articulos = doc.getElementsByTagName("articulo");
	       
	       //Variable para saber si el artículo en cuestión existe
	       boolean existe=false;
	       
	        //Bucle que recorre el nodelist
	        for (int i = 0; i < articulos.getLength(); i++) 
	        {
	        	//Se castea el elemento actual del nodelist a un objeto Element
	            Element elemento = (Element) articulos.item(i);
	            //SSe comprueba si el elemento actual tiene otro elemento cuyo texto coincide con el introducido por parámetro
	            if (elemento.getElementsByTagName("nombre").item(0).getTextContent().equalsIgnoreCase(articulo)) {
	                //Si coincide, se guardan los valores nombre y precio y se borra el elemento de en-stock
	            	nombre_articulo=elemento.getElementsByTagName("nombre").item(0).getTextContent();
	            	precio=elemento.getElementsByTagName("precio").item(0).getTextContent();
	            	elemento.getParentNode().removeChild(elemento);
	                //Existe pasa a ser true
	                existe=true;
	                //Creamos un elemento padre articulo
	                Element elementoPadre = doc.createElement("articulo");
	                //Creamos un elemento hijo nombre y lo asignamos a articulo
	    			elementoPadre.appendChild(doc.createElement("nombre"));
	    			//Creamos un elemento hijo precio y lo asignamos a articulo
	    			elementoPadre.appendChild(doc.createElement("precio"));
	    			//Asignamos a nombre y precio loss valores que tenían anteriormente
	    			elementoPadre.getChildNodes().item(0).appendChild(doc.createTextNode(nombre_articulo));
	    			elementoPadre.getChildNodes().item(1).appendChild(doc.createTextNode(String.valueOf(precio)));
	    			//Asignamos al elemento fuera-stock el elemento artículo que hemos creado con suss elementos hijos
	    			doc.getElementsByTagName("fuera-stock").item(0).appendChild(elementoPadre);
	    			//Creamos un objeto del tipo TransformerFactory
	    			Transformer transf = TransformerFactory.newInstance().newTransformer();
                    //Se crea un objeto DOMSource para determinar cuál es el documento modificado que vamos a guardar
	    	        DOMSource origen = new DOMSource(doc);
	    	        //Se crea un objeto StreamResult para determinar cuál es el archivo en el que vamos a guardar el
  	                //xml modificado
	    	        StreamResult destino = new StreamResult(archivo_xml);
  	                //Se llama al método correspondiente para que se guarde el xml modificado
	    	        transf.transform(origen, destino);
	            }
	        }
	        
            //Si la variable existe es false, se indica al usuario que el artículo que ha introducido no existe
	        if(existe==false) 
	        {
	        	
	        	System.out.println("\nLo sentimos, el artículo introducido no existe.\n");
	        	
	        }      
	    }
	
	
	public void mostrarArticulos() throws SAXException, IOException, ParserConfigurationException 
	{
		//Documento fuente que usaremos para modificar
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	    //Instanciamos un nodelist que guarde todos los elementos con la etiqueta articulo
		NodeList nodos = doc.getElementsByTagName("articulo");
	    //Instanciamos un nodo que guarde el elemnto con la etiqueta fuera-stock
	    Node fuera_stock = doc.getElementsByTagName("fuera-stock").item(0);
	    //Contador que necesitaremos para que el formato de salida sea el especificado
	    cont_fuera_stock=0;
	    //Id de artículo
	    id_articulo=1;
	    
	    //Inicio del apartado de artículos en stock
	    System.out.println("/--ARTÍCULOS EN STOCK--/\n");
	    
	    //Bucle que recorre todos los nodos del nodelist
	    for(int x = 0; x < nodos.getLength(); x++) 
	    {
	    	
	    	//Nodo actual del nodelist
	    	Node nodo_articulo = nodos.item(x);
	    	
	    	//Comprobamos si el nodo actual es del tipo elemento
	    	if(nodo_articulo.getNodeType() == Node.ELEMENT_NODE) 
	    	{
	    	
	    	    //Si lo es, hacemos un casteo para poder usar los métodos de la clase Element
	    	    Element elemento = (Element) nodo_articulo;
	    	    
	    	    //Si el elemento padre del elemento actual es fuera-stock y el contador es igual a 0 (es el primer elemento de este elemento padre), se imprime esto por pantalla 
	    	    if(elemento.getParentNode()==fuera_stock && cont_fuera_stock==0) 
	    	    {
	    	    	System.out.println("\n/--ARTÍCULOS FUERA DE STOCK--/\n");
	    	    	//Se aumenta el contador para que no se vuelva a escribir
	    	    	cont_fuera_stock++;
	    	    	//Se resetea el id de artículo a 1
	    	    	id_articulo=1;
	    	    }
	
	    	    //Se muestran los datos de este elemento
	    	    System.out.println("\nArtículo: "+id_articulo+", Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent()+", Precio: "+ elemento.getElementsByTagName("precio").item(0).getTextContent());
	    	
	    	    //Se aumenta el id
	    	    id_articulo++;
	    	    
	    	}
		
	    }
		
    }
}
