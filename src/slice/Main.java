package slice;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Logica l = a�adirEstado();

		while (true) {
			Boolean breakWhile = a�adirProd(l);
			if (breakWhile) {
				break;
			}
		}

		Double precio = l.getTotalPrice();
		JOptionPane.showMessageDialog(null, "Precio total: " + precio.toString(), "Error",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);

	}

	public static Logica a�adirEstado() {
		Boolean invalidCountry = true;
		while (invalidCountry) {
			try {
				String estado = JOptionPane.showInputDialog("Ingrese un codigo de estado");
				return new Logica(estado);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Pa�s inv�lido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	public static Boolean a�adirProd(Logica l) {
		try {
			JFrame frame = new JFrame();
			int n = JOptionPane.showConfirmDialog(frame, "�Desea a�adir un producto?", "msg",
					JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION) {
				String producto = JOptionPane.showInputDialog("Ingrese un producto");
				Integer cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad"));
				Producto p = new Producto(producto, cantidad);
				l.addProd(p);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al a�adir producto", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
