package Parte_2;

public class Empleado {
	
	private String apellidos;
	private int departamento;
	private float salario;
	
	public Empleado() {}
	
	public Empleado(String apellidos, int departamento, float salario) 
	{
		this.apellidos = apellidos;
		this.departamento = departamento;
		this.salario = salario;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

}
