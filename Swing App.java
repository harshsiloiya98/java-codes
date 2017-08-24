import javax.swing.*;
import java.awt.event.*;

public class Problem6 {
	public static void main(String[] args) {
		//declaraing all components of the window
		JFrame window = new JFrame("Program6");
		JTextField userid = new JTextField();
		JPasswordField passw = new JPasswordField();
		JButton login = new JButton("Login");
		JButton reset = new JButton("Reset");
		JLabel usnm = new JLabel("User Id: ");
		JLabel pswd = new JLabel("Password: ");

		//setting the size of the components
		userid.setBounds(150,50,100,25);
		passw.setBounds(150,100,100,25);
		login.setBounds(100,200,100,30);
		reset.setBounds(100,250,100,30);
		usnm.setBounds(50,50,100,25);
		pswd.setBounds(50,100,100,25);
		window.setSize(300,400);

		//action events for the buttons
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userid.getText().isEmpty() || passw.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Text fields are empty !", "Message", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Text fields are not empty !", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userid.setText("");
				passw.setText("");
			}
		});

		//adding the components to the main window
		window.add(usnm);
		window.add(pswd);
		window.add(userid);
		window.add(passw);
		window.add(login);
		window.add(reset);

		//Some window operations
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setLayout(null);
		window.setVisible(true);
	}
}