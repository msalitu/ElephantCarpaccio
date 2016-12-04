package slice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Logica {

	private String estado;
	private Double iva;
	private Double totalPrice;

	public Logica(String estado) throws Exception {
		this.estado = estado;
		this.iva = getCountryTax();
		totalPrice = 0D;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public void addProd(Producto p) throws Exception {

		Connection conn;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:carpaccio.db");

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT PRECIO FROM PRODUCTOS WHERE NOMBRE= '" + p.getNombre() + "'");

		if (rs.next()) {
			Double precio = rs.getDouble("PRECIO");
			totalPrice += precio * p.getCantidad();
		} else {
			throw new Exception("Invalid product");
		}

		conn.close();
	}

	public Double getCountryTax() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:carpaccio.db");
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT IVA FROM PAISES WHERE NOMBRE= '" + getEstado() + "'");

		if (rs.next()) {
			Double tax = rs.getDouble("IVA");
			return tax;
		} else {
			throw new Exception("Invalid country");
		}

	}

	public Double getTotalPrice() {
		Double priceWithTax = totalPrice * (1 + (iva / 100));
		Double priceWithDiscount = getPriceWithDiscount(priceWithTax);
		return priceWithDiscount;
	}

	private Double getPriceWithDiscount(Double priceWithTax) {
		if (priceWithTax.compareTo(50000D) > 0 || priceWithTax.equals(50000D)) {
			return priceWithTax * 0.85;
		} else if (priceWithTax.compareTo(10000D) > 0 || priceWithTax.equals(10000D)) {
			return priceWithTax * 0.90;
		} else if (priceWithTax.compareTo(7000D) > 0 || priceWithTax.equals(7000D)) {
			return priceWithTax * 0.93;
		} else if (priceWithTax.compareTo(5000D) > 0 || priceWithTax.equals(5000D)) {
			return priceWithTax * 0.95;
		} else if (priceWithTax.compareTo(1000D) > 0 || priceWithTax.equals(1000D)) {
			return priceWithTax * 0.97;
		} else {
			return priceWithTax;
		}
	}

}
