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
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduzca palabras:");
		
		String input="";
		
		BufferedWriter escritor = null;
		try {
			escritor = new BufferedWriter(new FileWriter("src/Parte_1/Nombres.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		do 
		{
			
			input = teclado.nextLine();
			
			
				
			try {
				
				
				
				if(!input.equalsIgnoreCase("Salir")) {
					
					for(int x=0 ; x<input.length() ; x++) 
					{
						
						if(input.substring(x, x+1).equalsIgnoreCase("a") || input.substring(x, x+1).equalsIgnoreCase("e") || input.substring(x, x+1).equalsIgnoreCase("i") || input.substring(x, x+1).equalsIgnoreCase("o") || input.substring(x, x+1).equalsIgnoreCase("u")) 
						{
							escritor.write(".");
						}
						else
						{
							escritor.write(input.substring(x, x+1));
						}
						
					}
					
				}
				
				escritor.newLine();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
		}while(!input.equalsIgnoreCase("SALIR"));
		
		
		try {
			escritor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader("src/Parte_1/Nombres.txt"));
			
			String texto = "";
			
			while(texto!=null) 
			{
				
				texto = lector.readLine();
				
				if(texto!=null) {
					System.out.println(texto);
				}
				
			}
			
			lector.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		teclado.close();

	}

}
