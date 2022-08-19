package edu.utec.uy.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RolVIEW extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDescripcion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RolVIEW frame = new RolVIEW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RolVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Administrador de Funcionalidades");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(74, 27, 289, 29);
		contentPane.add(lblTitulo);
		
		inputNombre = new JTextField();
		inputNombre.setBounds(175, 70, 188, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(74, 73, 75, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(74, 104, 75, 14);
		contentPane.add(lblDescripcion);
		
		inputDescripcion = new JTextField();
		inputDescripcion.setColumns(10);
		inputDescripcion.setBounds(175, 101, 188, 20);
		contentPane.add(inputDescripcion);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = inputNombre.getText();
				String descripcion = inputDescripcion.getText();
				System.out.println(nombre);
				System.out.println(descripcion);
			}
		});
		btnInsertar.setBounds(74, 142, 91, 23);
		contentPane.add(btnInsertar);
		
		JButton btnModificar = new JButton("Modficar");
		btnModificar.setBounds(175, 142, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(274, 142, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(373, 142, 89, 23);
		contentPane.add(btnListar);
	}
}
