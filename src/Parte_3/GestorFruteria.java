package Parte_3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestorFruteria {
	
	//res\registro.xml
	
	private File archivo_xml;
	
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
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
			
			//Especie de arraylist que contiene los elementos del xml
			NodeList lista_articulos = doc.getElementsByTagName("articulo");
			
			//For que recorre el documento xml
			for(int x=0 ; x<lista_articulos.getLength() ; x++ ) 
			{
				
				Element element = (Element) lista_articulos.item(x);
	            // elejir un elemento especifico por algun atributo
	            if (element.getAttribute("nombre").equalsIgnoreCase(articulo)) {
	                // borrar elemento
	                NodeList atributos = element.getParentNode().getChildNodes();
	                
	                atributos.item(1).setTextContent(String.valueOf(precio));
	            }

	            Transformer transf;
				try {
					transf = TransformerFactory.newInstance().newTransformer();
					DOMSource origen = new DOMSource(doc);
			        StreamResult destino = new StreamResult(archivo_xml);
			        transf.transform(origen, destino);
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
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fueraDeStock (String articulo) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {


	       Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo_xml);
	       NodeList items = doc.getElementsByTagName("articulo");
	        for (int i = 0; i < items.getLength(); i++) {
	            Element element = (Element) items.item(i);
	            // elejir un elemento especifico por algun atributo
	            if (element.getAttribute("nombre").equalsIgnoreCase(articulo)) {
	                // borrar elemento
	                element.getParentNode().removeChild(element);
	            }
	        }
	        
	        NodeList fuera_de_stock = doc.getElementsByTagName("fuera-stock");
	        
	        
	        Transformer transf = TransformerFactory.newInstance().newTransformer();

	        // 3. Exportar nuevamente el XML
	        DOMSource origen = new DOMSource(doc);
	        StreamResult destino = new StreamResult(archivo_xml);
	        transf.transform(origen, destino);
	    }

}
