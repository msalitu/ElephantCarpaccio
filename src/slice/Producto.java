package slice;

public class Producto {
	
	public String nombre;
	public Integer cantidad;
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto(String nombre, Integer cantidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
