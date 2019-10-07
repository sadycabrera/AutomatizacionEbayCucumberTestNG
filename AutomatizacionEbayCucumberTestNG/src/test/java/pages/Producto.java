package pages;

public class Producto {
	private String nombre;
	private Double precio;
	
	public Producto(String nombre, Double precio) {
		this.nombre=nombre;
		this.precio=precio;
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	

}
