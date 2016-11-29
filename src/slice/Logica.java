package slice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Logica {

	private ArrayList<Producto> productos;
	private String estado;
	
	
	
	public Logica(String estado){
		this.estado = estado;
		this.productos = new ArrayList<Producto>();
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public ArrayList<Producto> getProductos() {
		return productos;
	}


	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}	
	
	public void addProd (Producto p){
		
		productos.add(p);
		 
	        Connection conn;
			try {
				Class.forName("org.h2.Driver");
				conn = DriverManager.
				    getConnection("jdbc:h2:~/test", "sa", "");
				
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT PRECIO FROM PRODUCTOS WHERE NAME= '"+ p.getNombre() +"'");
			
				while (rs.next()) {
				    Integer precio = rs.getInt("PRECIO"); // Assuming there is a column called name.
				    System.out.println(precio);
				}
				
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        	// add application code here
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
	
	
}
