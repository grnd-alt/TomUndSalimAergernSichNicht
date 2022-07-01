import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("TESTFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("JFrame Example");
		JButton button = new JButton();
		button.setText("HALLO TOM");
		panel.add(label);
		panel.add(button);
		frame.add(panel);
		frame.setSize(200,300);
		frame.setVisible(true);
		System.out.println("Hello Tom And Salim");
	}

}
