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
		
		//Esc�ner necesario para introducir datos
		Scanner teclado = new Scanner(System.in);
		
		//Variable que guarda la elecci�n del usuario
		int opcion;
		
		//Nombre de art�culo que introducir� el usuario
		String nombre_articulo;
		
		//Precio de art�culo que introducir� el usuario
		float precio;
		
		//Instancia de la clase con todos los m�todos
		GestorFruteria gestor = new GestorFruteria(new File("res/registro.xml"));
		
		//Bucle infinito
		while(true) 
		{
			System.out.println("Introduzca la opci�n deseada: \n1: Modificar art�culo\n 2: Mover un elemento a fuera de stock\n 3: Mostrar art�culos\n 4: Salir\n >");
			opcion=teclado.nextInt();
			
			//Switch case con el men�
			switch(opcion) 
			{
				case 1:
						//Limpieza del b�fer de teclado
						teclado.nextLine();
						System.out.println("Introduzca el nombre del art�culo a modificar: ");
						nombre_articulo=teclado.nextLine();
						System.out.println("Introduzca el nuevo precio: ");
						precio=teclado.nextFloat();
						gestor.modificarElemento(nombre_articulo, precio);
					break;
				
				case 2:
					//Limpieza del b�fer de teclado
					teclado.nextLine();
					System.out.println("Introduzca el nombre del art�culo a mover: ");
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
					System.out.println("�Hasta pronto!");
					//Se cierra el teclado
					teclado.close();
					//Se cierra el programa
					System.exit(0);
				break;
			}
		}
		
		}

	}
