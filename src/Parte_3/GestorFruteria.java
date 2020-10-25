package Parte_3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
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
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	              DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	              Document doc = dBuilder.parse(archivo_xml);

	              NodeList nod = doc.getElementsByTagName("articulo");

	              for(int i = 0; i < nod.getLength(); i++) {

	                  Node nNode = nod.item(i);

	                  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element eElement = (Element) nNode;

	                    if( eElement.getElementsByTagName("nombre").item(0).getTextContent().equalsIgnoreCase(articulo)) {
	                        eElement.getElementsByTagName("precio").item(0).setTextContent(String.valueOf(precio));
	                        System.out.println("Precio cambiado correctamente");
	                    }

	                  }
	                }
	              Transformer transf = TransformerFactory.newInstance().newTransformer();
	                DOMSource origen = new DOMSource(doc);
	                StreamResult destino = new StreamResult(archivo_xml);
	                transf.transform(origen,destino);

	        }catch(Exception e) {
	              e.printStackTrace();
	            }
	}
	
	public void fueraDeStock (String articulo) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{
		
		   String nombre_articulo = "", precio = "";

	       Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	       NodeList items = doc.getElementsByTagName("articulo");
	        for (int i = 0; i < items.getLength(); i++) {
	            Element element = (Element) items.item(i);
	            // elegir un elemento especifico por algun atributo
	            if (element.getElementsByTagName("nombre").item(0).getTextContent().equalsIgnoreCase(articulo)) {
	                // borrar elemento
	            	nombre_articulo=element.getElementsByTagName("nombre").item(0).getTextContent();
	            	precio=element.getElementsByTagName("precio").item(0).getTextContent();
	                element.getParentNode().removeChild(element);
	            }
	        }
	        
	        Element elementoPadre = doc.createElement("articulo");
			elementoPadre.appendChild(doc.createElement("nombre"));
			elementoPadre.appendChild(doc.createElement("precio"));
			elementoPadre.getChildNodes().item(0).appendChild(doc.createTextNode(nombre_articulo));
			//convertir el precio a string
			elementoPadre.getChildNodes().item(1).appendChild(doc.createTextNode(String.valueOf(precio)));
			doc.getElementsByTagName("fuera-stock").item(0).appendChild(elementoPadre);
	        
	        Transformer transf = TransformerFactory.newInstance().newTransformer();
	        
	        

	        // 3. Exportar nuevamente el XML
	        DOMSource origen = new DOMSource(doc);
	        StreamResult destino = new StreamResult(archivo_xml);
	        transf.transform(origen, destino);
	    }
	
	
	public void mostrarArticulos() throws SAXException, IOException, ParserConfigurationException 
	{
		
		Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	    NodeList items = doc.getElementsByTagName("articulo");
	    NodeList fuera_stock = doc.getElementsByTagName("fuera-stock");
	    cont_fuera_stock=0;
	    id_articulo=1;
	    
	    System.out.println("/--ARTÍCULOS EN STOCK--/\n");
	    
	    for(int temp = 0; temp < items.getLength(); temp++) 
	    {
	    	
	    	Node nNode = items.item(temp);
	    	
	    	if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	    	
	    	  
	    	    Element eElement = (Element) nNode;
	    	    
	    	    if(eElement.getParentNode()==fuera_stock.item(0) && cont_fuera_stock==0) 
	    	    {
	    	    	System.out.println("\n/--ARTÍCULOS FUERA DE STOCK--/\n");
	    	    	cont_fuera_stock++;
	    	    	id_articulo=1;
	    	    }
	
	    	    System.out.println("\nArtículo: "+id_articulo+", Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent()+", Precio: "+ eElement.getElementsByTagName("precio").item(0).getTextContent());
	    	
	    	    id_articulo++;
	    	    
	    	}
		
	    }
		
    }
}
