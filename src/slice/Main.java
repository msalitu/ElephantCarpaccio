package slice;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Logica l = añadirEstado();

		while (true) {
			Boolean breakWhile = añadirProd(l);
			if (breakWhile) {
				break;
			}
		}

		Double precio = l.getTotalPrice();
		JOptionPane.showMessageDialog(null, "Precio total: " + precio.toString(), "Error",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);

	}

	public static Logica añadirEstado() {
		Boolean invalidCountry = true;
		while (invalidCountry) {
			try {
				String estado = JOptionPane.showInputDialog("Ingrese un codigo de estado");
				return new Logica(estado);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "País inválido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	public static Boolean añadirProd(Logica l) {
		try {
			JFrame frame = new JFrame();
			int n = JOptionPane.showConfirmDialog(frame, "¿Desea añadir un producto?", "msg",
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
			JOptionPane.showMessageDialog(null, "Error al añadir producto", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
