package slice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
      		
		String estado = JOptionPane.showInputDialog("Ingrese un codigo de estado");
		Logica l = new Logica(estado);

		while (true) {
			Boolean breakWhile = añadirProd(l);
			if (breakWhile) {
				break;
			}
		}

	}

	public static Boolean añadirProd(Logica l) {
		JFrame frame = new JFrame();
		int n = JOptionPane.showConfirmDialog(frame, "Desea añadir otro producto?", "msg", JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			String producto = JOptionPane.showInputDialog("Ingrese un producto");
			//Integer precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio"));
			Integer cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad"));
			Producto p = new Producto(producto, cantidad);
			frame = new JFrame();
			String[] options = new String[2];
			options[0] = new String("Agree");
			options[1] = new String("Disagree");
			JOptionPane.showOptionDialog(frame.getContentPane(), "Has comprado el producto " + producto, "Confirmacion",
					0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			l.addProd(p);
			return false;
		} else {
			System.out.println("out");
			return true;
		}
	}
}
