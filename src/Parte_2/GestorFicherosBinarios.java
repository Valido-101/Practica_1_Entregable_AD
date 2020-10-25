package Parte_2;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;

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
				while(apellidos[x].length()<10) 
				{
					apellidos[x]+=" ";
				}
				flujo.writeBytes(apellidos[x].toString());
				flujo.writeInt(departamento[x]);
				flujo.writeFloat(salario[x]);
				System.out.println(flujo.length());
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
				
			while(flujo.getFilePointer()<=flujo.length()) 
			{
				
				try 
				{
					System.out.print("Id: "+flujo.readInt());
					byte[] buff = new byte[10];
					flujo.read(buff);
					String apellido = new String(buff);
					System.out.print(", Apellido: "+apellido.trim());
					System.out.print(", Departamento: "+flujo.readInt());
					System.out.print(", Salario: "+flujo.readFloat()+"\n");			
				}
				catch(EOFException e) 
				{
					break;
				}
			
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
			flujo.seek(22*(id-1));
				
			System.out.print("Id: "+flujo.readInt());
			byte[] buff = new byte[10];
			flujo.read(buff);
			String apellido = new String(buff);
			System.out.print(", Apellido: "+apellido);
			System.out.print(", Departamento: "+flujo.readInt());
			System.out.print(", Salario: "+flujo.readFloat()+"\n");			
			flujo.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertarDatos(int id, String apellido, int departamento, float salario)
	{
		boolean existe = false;
		try {
			flujo = new RandomAccessFile(f1,"rw");
			
			//Esto coloca el puntero a la altura del siguiente id
			flujo.seek(22*(id-1));
				
				try 
				{
					if(flujo.readInt()==id) 
					{
						System.out.println("Este empleado ya existe.");
						existe=true;
					}
				}catch(EOFException e) 
				{
					
				}
			
				
			if(existe==false) 
			{
				try {
					flujo.seek(flujo.length());
					flujo.writeInt(id);
					while(apellido.length()<10) 
					{
						apellido+=" ";
					}
					flujo.writeBytes(apellido.toString());
					flujo.writeInt(departamento);
					flujo.writeFloat((float)salario);
					
					System.out.println("Tarea realizada con éxito");
				}
				catch(InputMismatchException e) 
				{
					System.out.println("Dato no válido.");
					System.out.println(e.getCause());
				}
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

}
