package Parte_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio_1 {

	public static void main(String[] args) {
		
		//Instanciamos un escáner para introducir datos por teclado
		Scanner teclado = new Scanner(System.in);
		
		//Indicación al usuario
		System.out.println("Introduzca palabras:");
		
		//Variable donde guardaremos lo que va a recibir el programa
		String input="";
		
		//Referencia al Buffered writer 
		BufferedWriter escritor = null;
		try {
			//Nuevo objeto BufferedWriter que recibe por parámetro en el constructor un FileWriter con la ruta relativa
			//del archivo que queremos usar
			escritor = new BufferedWriter(new FileWriter("src/Parte_1/Nombres.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Inicio del bucle do-while
		do 
		{
			
			//Introducimos las palabras una a una
			input = teclado.nextLine();
			
			
				
			try {
				
				
				//Si la palabra introducida no es "salir", se intercambian las vocales por puntos
				if(!input.equalsIgnoreCase("Salir")) {
					
					//Se hace un bucle con la longitud de la palabra que se ha introducido
					for(int x=0 ; x<input.length() ; x++) 
					{
						//Si el carácter actual es igual a una vocal, se sustituye por un punto al introducirse en el fichero
						if(input.substring(x, x+1).equalsIgnoreCase("a") || input.substring(x, x+1).equalsIgnoreCase("e") || input.substring(x, x+1).equalsIgnoreCase("i") || input.substring(x, x+1).equalsIgnoreCase("o") || input.substring(x, x+1).equalsIgnoreCase("u")) 
						{
							escritor.write(".");
						}
						//De lo contrario, se escribe ese carácter con normalidad
						else
						{
							escritor.write(input.substring(x, x+1));
						}
						
					}
					
				}
				
				//Se hace un salto de línea en el archivo para que no salga todo junto
				escritor.newLine();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		//El bucle termina cuando se introduce SALIR
		}while(!input.equalsIgnoreCase("SALIR"));
		//Fin del bucle do-while
		
		try {
			//Se cierra el BufferedWriter
			escritor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			//Se instancia un objeto BufferedReader para leer el archivo
			BufferedReader lector = new BufferedReader(new FileReader("src/Parte_1/Nombres.txt"));
			
			//Variable donde se guarda lo que lee el BufferedReader
			String texto = "";
			
			//Mientras la variable texto no sea null, se repite el bucle
			while(texto!=null) 
			{
				
				//Método que lee una línea del documento y la almacena en la variable texto
				texto = lector.readLine();
				
				//Si texto no es null (es decir, si no ha llegado al final del archivo), se imprime su valor
				if(texto!=null) {
					System.out.println(texto);
				}
				
			}
			
			//Se cierra el BufferedReader
			lector.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Se cierra el escáner
		teclado.close();

	}

}
