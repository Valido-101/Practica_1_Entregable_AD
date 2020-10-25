package Parte_2;

import java.io.File;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		//Instanciamos un objeto de la clase GestorFicherosBinarios, donde tenemos todos los métodos
		GestorFicherosBinarios gestor = new GestorFicherosBinarios(new File("src/Parte_2/Empleados.dat"));
		
		//Instanciamos un escáner
		Scanner teclado = new Scanner(System.in);
		
		//Instanciamos una variable para elegir una opción del menú
		int opcion;
		
		//Instanciamos una variable para cada dato de empleado
		int id_empleado;
		String apellido;
		int departamento;
		float salario;
		
		//Arrays de String, int y float que guardan los apellidos, departamentos y salarios que deberemos introducir en el método escribirDatos
		String[] apellidos = {"ALONSO","RODRIGUEZ","OTERO","JIMENEZ","MEJIAS"};
		
		int[] departamentos = {10, 80, 40, 20, 10};
		
		float[] salarios =  {(float) 4000.45, (float) 2500.03, (float) 1100.25, (float) 1687.30, (float) 6300.40};
		
		//Bucle infinito que sólo se detendrá cuando escojamos la opción 5.
		while(true) 
		{
			
			System.out.println("Bienvenido al menú. Elija una opción: \n 1: Escribir datos en fichero \n 2: Leer datos \n 3: Consultar datos \n 4: Insertar empleado \n 5: Salir");
			
			//Se introduce la opción deseada del menú
			opcion = teclado.nextInt();
			
			//Se hace un switch case para que se realice un método distinto dependiendo de la opción
			switch(opcion) 
			{
			
				case 1:
						//Si se introduce 1 se llama al método escribir datos, que recibe por parámetros los arrays del principio
						gestor.escribirDatos(apellidos, departamentos, salarios);
						System.out.println("Datos escritos correctamente\n");
					break;
					
				case 2: 
						//Si se introduce 2 se llama al método que lee el documento y muestra los datos por pantalla
						gestor.leerDatos();
					break;
					
				case 3:
						//Si se pulsa 3 se pide por teclado el id del empleado que se quiere buscar
						System.out.println("Introduzca el id del empleado que quiera buscar:");
						id_empleado = teclado.nextInt();
						//Se llama al método consultarDatos y se pasa por parámetro el id del empleado que se quiera buscar
						gestor.consultarDatos(id_empleado);
					break;
					
				case 4:
						//Si se introduce 4 se piden los datos del nuevo empleado que se quiera insertar
						System.out.print("Introduzca los datos del nuevo empleado:\n Id: ");
						id_empleado=teclado.nextInt();
						//Hacemos una limpieza del búfer de teclado para que no haya problemas al introducir un String después de un int
						teclado.nextLine();
						System.out.print("Apellido: ");
						apellido=teclado.nextLine();
						System.out.print("Departamento: ");
						departamento=teclado.nextInt();
						System.out.print("Salario: ");
						//Es importante tener en cuenta que el salario debe introducirse con una coma separando la parte entera de la
						//decimal. Parece una tontería pero he estado literalmente dos horas atascado porque lo estaba metiendo con 
						//un punto como separador entre entero y decimal y me daba un InputMismatchException, haciéndome pensar
						//que algo estaba mal :)
						salario=teclado.nextFloat();
						//Se llama al método insertarDatos y se le pasa por parámetro los datos nuevos a insertar
						gestor.insertarDatos(id_empleado, apellido, departamento, salario);
					break;
					
				case 5: 
						//Si se pulsa 5 el programa se despide del usuario, cierra el escáner y sale del programa.
						System.out.println("¡Hasta pronto!");
						teclado.close();
						System.exit(0);
					break;
			
			}
			
		}
		
		

	}

}
