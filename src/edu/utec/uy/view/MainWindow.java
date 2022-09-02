package edu.utec.uy.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.utec.uy.bo.PersonaBO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    private JPanel contentPane;
    private JTextField txtCorreo;
    private JPasswordField passwordField;
    private PersonaBO pBO = new PersonaBO();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainWindow frame = new MainWindow();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public MainWindow() {
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
	
	txtCorreo = new JTextField();
	txtCorreo.setBounds(200, 100, 200, 24);
	contentPane.add(txtCorreo);
	txtCorreo.setColumns(10);
	
	JLabel lblClave = new JLabel("Clave");
	lblClave.setBounds(100, 135, 90, 24);
	contentPane.add(lblClave);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(200, 135, 200, 24);
	contentPane.add(passwordField);
	
	JButton btnLogin = new JButton("Aceptar");
	btnLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    String msg = pBO.login(txtCorreo.getText(), passwordField.getText());
		    JOptionPane.showMessageDialog(null, msg);
		}
	});
	btnLogin.setBounds(240, 200, 90, 24);
	contentPane.add(btnLogin);
    }
}
