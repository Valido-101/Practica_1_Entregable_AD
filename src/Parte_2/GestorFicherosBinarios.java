package Parte_2;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GestorFicherosBinarios {
	
	private File f1;
	private RandomAccessFile flujo;
	
	public GestorFicherosBinarios(File f1) 
	{
		
		this.f1 = f1;
		
	}
	
	public void escribirDatos(String[] apellidos, int[] departamento, float[] salario) 
	{
		
		try {
			flujo=new RandomAccessFile(f1,"rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String s: apellidos) 
		{
			
			if(s.length()<10) 
			{
			
				while(s.length()<10) 
				{
					
					s+=" ";
					
				}
				
			}
			
		}
		
		try {
			flujo.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int x=0 ; x<apellidos.length ; x++) 
		{
			
			try {
				flujo.writeInt(x+1);
				flujo.writeUTF(apellidos[x]);
				flujo.writeInt(departamento[x]);
				flujo.writeFloat(salario[x]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			flujo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void leerDatos() 
	{
		
		try {
			flujo = new RandomAccessFile(f1,"r");
				
			while(true) 
			{
				
				try 
				{
					System.out.println("Id: "+flujo.readInt()+", Apellido: "+flujo.readUTF()+", Departamento: "+flujo.readInt()+", Salario: "+flujo.readFloat()+"\n");
				}
				catch(EOFException e) 
				{
					break;
				}
				
				/*for(int x=0 ; x<=4 ; x++) 
				{
					System.out.print(flujo.readInt());
				}
				
				System.out.print(", Apellido: ");
				
				for(int x=0 ; x<=20 ; x++) 
				{
					System.out.print(flujo.readUTF());
				}
				
				System.out.print(", Departamento: ");
				
				for(int x=0 ; x<=8 ; x++) 
				{
					System.out.print(flujo.readInt());
				}
				
				System.out.print(", Salario: ");
				
				for(int x=0 ; x<=24 ; x++) 
				{
					System.out.print(flujo.readFloat());
				}
				*/
			
		}
		
		flujo.close();
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void consultarDatos(int id) 
	{
		
		try {
			flujo = new RandomAccessFile(f1,"r");
			
			//Esto coloca el puntero a la altura del siguiente id
			//flujo.seek(flujo.getFilePointer+52);
			
			while(flujo.getFilePointer()<flujo.length()) 
			{
				
				try 
				{
					if(flujo.readInt()==id) 
					{
						flujo.seek(flujo.getFilePointer()-4);
						System.out.println("Id: "+flujo.readInt()+", Apellido: "+flujo.readUTF()+", Departamento: "+flujo.readInt()+", Salario: "+flujo.readFloat()+"\n");
					}
					else 
					{
						
						flujo.readUTF();
						flujo.readInt();
						flujo.readFloat();
						
					}
				}
				catch(EOFException e) 
				{
					break;
				}
			}
				
				//Este bucle seria el encargado de leer el id y verificar si es el que se ha introducido
				System.out.println("Id: "+flujo.readInt()+", Apellido: "+flujo.readUTF()+", Departamento: "+flujo.readInt()+", Salario: "+flujo.readFloat()+"\n");
				
				
			
			flujo.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
