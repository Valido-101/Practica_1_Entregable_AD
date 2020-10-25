package Parte_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio_2 {

	public static void main(String[] args) {
		
		//Contador de palabras del documento
		int contador_palabras=0;
		
		//Variable donde guardamos el valor numérico del carácter leído
		int caracter_leido=0;
		
		try {
			
			//Instancia del lector del documento que recibe como parámetro en el constructor un nuevo objeto FileReader
			//con la ruta relativa del fichero que hay que leer.
			BufferedReader lector = new BufferedReader(new FileReader("src/Parte_1/quijote.txt"));
			
			//Bucle que se repite mientras la variable caracter_leido no sea igual a -1 (fin del archivo)
			while(caracter_leido!=-1) 
			{
				
				//Se almacena el valor recogido por lector.read() en la variable caracter_leido
				caracter_leido = lector.read();
				
				//Si caracter_leido tiene como valor 32 (código del espacio en ASCII), aumenta el contador de palabras
				if(caracter_leido==32) 
				{
					
					contador_palabras++;
					
				}
				
			}
			
			//Se cierra el contador de palabras
			lector.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Imprimimos por pantalla el valor del contador de palabras
		System.out.println(contador_palabras);

	}

}
