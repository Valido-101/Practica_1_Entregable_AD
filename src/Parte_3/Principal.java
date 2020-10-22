package Parte_3;

import java.io.File;

public class Principal {

	public static void main(String[] args) {
		
		GestorFruteria gestor = new GestorFruteria(new File("res/registro.xml"));
		
		gestor.modificarElemento("Manzana", (float)1.8);

	}

}
