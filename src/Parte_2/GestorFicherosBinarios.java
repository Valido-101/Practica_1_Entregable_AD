package Parte_2;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;

public class GestorFicherosBinarios {

	//Atributos de la clase
	
	private File f1;

	private RandomAccessFile flujo;
	
	//Constructor de la clase GestorFicherosBinarios que recibe un objeto File por parámetro
	public GestorFicherosBinarios(File f1) 
	{
		
		this.f1 = f1;
		
	}
	
	public void escribirDatos(String[] apellidos, int[] departamento, float[] salario) 
	{
		
		try {
			//Se crea un nuevo objeto RandomAccessFile con permisos de lectura y escritura
			flujo=new RandomAccessFile(f1,"rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//Nos aseguramos de estar situados al inicio del documento
			flujo.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Bucle que recorre el array de apellidos e introduce los valores de cada array (puesto que todos tienen la misma longitud)
		for(int x=0 ; x<apellidos.length ; x++) 
		{
			
			try {
				//Escribimos primero el id del empleado
				flujo.writeInt(x+1);
				//Recorremos la posición x del array de apellidos y la llenamos de espacios para que tenga justo 10 caracteres
				while(apellidos[x].length()<10) 
				{
					apellidos[x]+=" ";
				}
				//Escribimos la posición x del array de apellidos en bytes
				flujo.writeBytes(apellidos[x].toString());
				//Escribimos el int Departamento
				flujo.writeInt(departamento[x]);
				//Escribimos el float Salario
				flujo.writeFloat(salario[x]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			//Cerramos el RandomAccessFile
			flujo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void leerDatos() 
	{
		
		try {
			//Instanciamos un objeto RandomAccessFile con permiso de lectura únicamente, puesto que sólo queremos mostrar datos
			flujo = new RandomAccessFile(f1,"r");
				
			//Mientras la posición actual del puntero sea menor o igual a la longitud en bytes del fichero, se repite el bucle
			while(flujo.getFilePointer()<=flujo.length()) 
			{
				
				try 
				{
					//Leemos un int y lo imprimimos por pantalla (id de empleado)
					System.out.print("Id: "+flujo.readInt());
					//Creamos un array de bytes con una longitud de 10
					byte[] buff = new byte[10];
					//Leemos esa cantidad de bytes (apellido)
					flujo.read(buff);
					//Almacenamos el valor en String del array de bytes
					String apellido = new String(buff);
					//Lo imprimimos por pantalla con el método trim
					System.out.print(", Apellido: "+apellido.trim());
					//Leemos e imprimimos el int departamento
					System.out.print(", Departamento: "+flujo.readInt());
					//Leemos e imprimimos el float salario
					System.out.print(", Salario: "+flujo.readFloat()+"\n");			
				}
				//Si llegamos al final del archivo antes de que se corte el bucle, recogemos la excepción y salimos del bucle para evitar errores
				catch(EOFException e) 
				{
					break;
				}
			
		}
		
		//Cerramos el RandomAccessFile
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
			//Creamos un RandomAccessFile con únicamente permiso de lectura
			flujo = new RandomAccessFile(f1,"r");
			
			//Esto coloca el puntero al final del registro anterior
			flujo.seek(22*(id-1));
				
			//Seguimos exactamente el mismo proceso de lectura que en el método leerDatos
			System.out.print("Id: "+flujo.readInt());
			byte[] buff = new byte[10];
			flujo.read(buff);
			String apellido = new String(buff);
			System.out.print(", Apellido: "+apellido);
			System.out.print(", Departamento: "+flujo.readInt());
			System.out.print(", Salario: "+flujo.readFloat()+"\n");		
			
			//Cerramos el RandomAccessFile
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
		//Instanciamos una variable que nos servirá para saber si el empleado ya existe
		boolean existe = false;
		try {
			//Instanciamos un nuevo objeto RandomAccessFile
			flujo = new RandomAccessFile(f1,"rw");
			
			//Esto coloca el puntero a la altura del final del registro anterior
			flujo.seek(22*(id-1));
				
				try 
				{
					//Leemos el id a la altura del id introducido para comprobar si existe ese empleado
					if(flujo.readInt()==id) 
					{
						//Si el id leído es igual al introducido, se informa al usuario y la variable existe pasa a ser true
						System.out.println("Este empleado ya existe.");
						existe=true;
					}
				}catch(EOFException e) 
				{
					
				}
			
			//Si la variable existe es false (no existe el empleado), se introducen los datos en el fichero	
			if(existe==false) 
			{
				try {
					//Colocamos el puntero al final del fichero
					flujo.seek(flujo.length());
					//Seguimos exactamente el mismo proceso de escritura que en el método escribirDatos
					flujo.writeInt(id);
					while(apellido.length()<10) 
					{
						apellido+=" ";
					}
					flujo.writeBytes(apellido.toString());
					flujo.writeInt(departamento);
					flujo.writeFloat((float)salario);
					
					//Se informa al usuario de que la tarea se ha realizado con éxito
					System.out.println("Tarea realizada con éxito");
				}
				//Si se introduce algún dato erróneo se informa al usuario
				catch(InputMismatchException e) 
				{
					System.out.println("Dato no válido.");
					System.out.println(e.getCause());
				}
			}
					
			//Cerramos el RandomAccessFile
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
