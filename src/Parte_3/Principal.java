package Parte_3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

public class Principal {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		int opcion;
		
		String nombre_articulo;
		
		float precio;
		
		GestorFruteria gestor = new GestorFruteria(new File("res/registro.xml"));
		
		while(true) 
		{
			System.out.println("Introduzca la opción deseada: \n1: Modificar artículo\n 2: Mover un elemento a fuera de stock\n 3: Mostrar artículos\n 4: Salir\n >");
			opcion=teclado.nextInt();
			
			switch(opcion) 
			{
				case 1:
						System.out.println("Introduzca el nombre del archivo a modificar: ");
						nombre_articulo=teclado.nextLine();
						System.out.println("Introduzca el nuevo precio: ");
						precio=teclado.nextFloat();
						gestor.modificarElemento(nombre_articulo, precio);
					break;
				
				case 2:
					System.out.println("Introduzca el nombre del archivo a mover: ");
					nombre_articulo=teclado.nextLine();
					try {
						gestor.fueraDeStock(nombre_articulo);
					} catch (SAXException | IOException | ParserConfigurationException
						| TransformerFactoryConfigurationError | TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				
				case 3:
				try {
					gestor.mostrarArticulos();
				} catch (SAXException | IOException | ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
				default:
					System.out.println("¡Hasta pronto!");
					teclado.close();
					System.exit(0);
				break;
			}
		}
		
		}

	}
