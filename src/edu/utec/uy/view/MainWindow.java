package edu.utec.uy.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static MainWindow frame = new MainWindow();
	private PersonaVIEW pV = new PersonaVIEW();
	private RolVIEW rV = new RolVIEW();
	private FuncionalidadVIEW fV = new FuncionalidadVIEW();
	
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
	
	public static void visibilidad(boolean value) {
		frame.setVisible(value);
	}
	
	private JPanel contentPane;

	public MainWindow() {
		pV.setDefaultCloseOperation(HIDE_ON_CLOSE);
		rV.setDefaultCloseOperation(HIDE_ON_CLOSE);
		fV.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Ventana principal");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitulo.setBounds(111, 10, 171, 35);
		contentPane.add(lblTitulo);
		
		JButton btnPersona = new JButton("Gestionar Personas");
		btnPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pV.setVisible(true);
			}
		});
		btnPersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPersona.setBounds(90, 55, 209, 56);
		contentPane.add(btnPersona);
		
		JButton btnRol = new JButton("Gestionar Roles");
		btnRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rV.setVisible(true);
			}
		});
		btnRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRol.setBounds(90, 121, 209, 56);
		contentPane.add(btnRol);
		
		JButton btnFuncionalidad = new JButton("Gestionar Funcionalidades");
		btnFuncionalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fV.setVisible(true);
			}
		});
		btnFuncionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFuncionalidad.setBounds(90, 188, 209, 56);
		contentPane.add(btnFuncionalidad);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrar.setBounds(90, 255, 209, 56);
		contentPane.add(btnCerrar);	
	}
}
