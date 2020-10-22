package Parte_2;

import java.io.File;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		GestorFicherosBinarios gestor = new GestorFicherosBinarios(new File("src/Parte_2/Empleados.dat"));
		Scanner teclado = new Scanner(System.in);
		int opcion;
		int id_empleado;
		
		String[] apellidos = {"ALONSO","RODRIGUEZ","OTERO","JIMENEZ","MEJIAS"};
		
		int[] departamentos = {10, 80, 40, 20, 10};
		
		float[] salarios =  {(float) 4000.45, (float) 2500.00, (float) 1100.25, (float) 1687.30, (float) 6300.40};
		
		while(true) 
		{
			System.out.println("Bienvenido al menú. Elija una opción: \n 1: Escribir datos en fichero \n 2: Leer datos \n 3: Consultar datos \n 4: Insertar empleado \n 5: Salir");
			
			opcion = teclado.nextInt();
			
			switch(opcion) 
			{
			
				case 1:
						gestor.escribirDatos(apellidos, departamentos, salarios);
						System.out.println("Datos escritos correctamente\n");
					break;
					
				case 2: 
						gestor.leerDatos();
					break;
					
				case 3:
						System.out.println("Introduzca el id del empleado que quiera buscar:");
						id_empleado = teclado.nextInt();
						gestor.consultarDatos(id_empleado);
					break;
					
				case 5: 
					teclado.close();
					System.exit(0);
					break;
			
			}
			
		}
		
		

	}

}
