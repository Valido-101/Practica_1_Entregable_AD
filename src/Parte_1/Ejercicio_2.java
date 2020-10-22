package Parte_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio_2 {

	public static void main(String[] args) {
		
		int contador=0;
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader("src/Parte_1/quijote.txt"));
			
			while(lector.read()!=-1) 
			{
				
				if((char)lector.read()==' ') 
				{
					
					contador++;
					
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
		
		System.out.println(contador);

	}

}
