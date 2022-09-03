package edu.utec.uy.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.utec.uy.bo.PersonaBO;
import edu.utec.uy.model.Persona;
import edu.utec.uy.model.Rol;
import edu.utec.uy.model.TipoRol;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField inputMail;
	private JPasswordField inputClave;
	private PersonaBO pBO = new PersonaBO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInicio = new JLabel("Iniciar sesion");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(10, 10, 534, 24);
		contentPane.add(lblInicio);

		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setBounds(100, 100, 90, 24);
		contentPane.add(lblCorreo);

		inputMail = new JTextField();
		inputMail.setBounds(200, 100, 200, 24);
		contentPane.add(inputMail);
		inputMail.setColumns(10);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(100, 135, 90, 24);
		contentPane.add(lblClave);

		inputClave = new JPasswordField();
		inputClave.setBounds(200, 135, 200, 24);
		contentPane.add(inputClave);

		JButton btnLogin = new JButton("Ingresar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = inputMail.getText();
				String clave = inputClave.getText();
				
				Persona p = pBO.login(mail, clave);
				if (p == null) {
					JOptionPane.showMessageDialog(null, "Los datos ingresados no son válidos");
				} else {
					Rol rol = p.getRol();
					String mensaje = "Se ha ingresado como "+ p.getRol().getTipo() +" al Sistema";
					if (rol.getTipo().equals(TipoRol.ADMINISTRADOR)) {
						MainWindow panel = new MainWindow();
						panel.setDefaultCloseOperation(HIDE_ON_CLOSE);
						dispose();
						panel.setVisible(true);
					}
					JOptionPane.showMessageDialog(null, mensaje);
				}
			}
		});
		btnLogin.setBounds(240, 200, 90, 24);
		contentPane.add(btnLogin);
	}
}
