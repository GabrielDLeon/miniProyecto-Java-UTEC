package edu.utec.uy.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.utec.uy.dao.PersonaDAO;
import edu.utec.uy.model.Persona;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PersonaVIEW extends JFrame {

	private JPanel contentPane;
	private JTextField inputDocumento;
	private JTextField inputNombre1;
	private DefaultTableModel model;
	private JTable tPersona;
	private JTextField inputNombre2;
	private JTextField inputApellido2;
	private JTextField inputApellido1;
	private JTextField inputBusquedaNombre;
	private JTextField inputBusquedaApellido;
	
	private PersonaDAO pDAO = new PersonaDAO();
	private JTextField textField;
	private JTextField textField_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonaVIEW frame = new PersonaVIEW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersonaVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestor de Personas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(41, 28, 289, 29);
		contentPane.add(lblTitulo);
		
		inputDocumento = new JTextField();
		inputDocumento.setBounds(142, 127, 188, 20);
		contentPane.add(inputDocumento);
		inputDocumento.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(41, 130, 91, 14);
		contentPane.add(lblDocumento);
		                       
		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setBounds(41, 160, 91, 14);
		contentPane.add(lblNombre1);
		
		inputNombre1 = new JTextField();
		inputNombre1.setColumns(10);
		inputNombre1.setBounds(142, 157, 188, 20);
		contentPane.add(inputNombre1);
		
		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setBounds(41, 189, 91, 14);
		contentPane.add(lblNombre2);
		
		inputNombre2 = new JTextField();
		inputNombre2.setColumns(10);
		inputNombre2.setBounds(142, 187, 188, 20);
		contentPane.add(inputNombre2);
		
		inputApellido2 = new JTextField();
		inputApellido2.setColumns(10);
		inputApellido2.setBounds(142, 251, 188, 20);
		contentPane.add(inputApellido2);
		
		JLabel lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setBounds(41, 223, 91, 14);
		contentPane.add(lblApellido1);
		
		inputApellido1 = new JTextField();
		inputApellido1.setColumns(10);
		inputApellido1.setBounds(142, 221, 188, 20);
		contentPane.add(inputApellido1);
		
		JLabel lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setBounds(41, 253, 91, 14);
		contentPane.add(lblApellido2);
		
		inputBusquedaNombre = new JTextField();
		inputBusquedaNombre.setBounds(340, 259, 135, 19);
		contentPane.add(inputBusquedaNombre);
		inputBusquedaNombre.setColumns(10);
		
		inputBusquedaApellido = new JTextField();
		inputBusquedaApellido.setColumns(10);
		inputBusquedaApellido.setBounds(486, 259, 135, 19);
		contentPane.add(inputBusquedaApellido);
		
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarPersona();
			}
		});
		btnInsertar.setBounds(41, 290, 91, 23);
		contentPane.add(btnInsertar);
		
		JButton btnModificar = new JButton("Modficar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPersona();
			}
		});
		btnModificar.setBounds(142, 290, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPersona();
			}
		});
		btnEliminar.setBounds(241, 290, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		btnListar.setBounds(631, 257, 89, 23);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 28, 380, 219);
		contentPane.add(scrollPane);
		
		tPersona = new JTable();
		tPersona.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					tPersona.clearSelection();
				}
			}
		});
		tPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tPersona);
		model = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tPersona.setModel(model);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(142, 68, 188, 20);
		contentPane.add(textField);
		
		JLabel lblMail = new JLabel("Email");
		lblMail.setBounds(41, 71, 91, 14);
		contentPane.add(lblMail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(142, 96, 188, 20);
		contentPane.add(textField_1);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(41, 99, 91, 14);
		contentPane.add(lblClave);
		model.addColumn("ID");
		model.addColumn("Documento");
		model.addColumn("Nombre");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Apellido");
		model.addColumn("Nacimiento");
		
		tPersona.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
		        if (!lse.getValueIsAdjusting()) {
		        	int row = tPersona.getSelectedRow();
		        	if (row >=0) {
		        		llenarCampos(row);
		        		btnEliminar.setEnabled(true);
		        		btnModificar.setEnabled(true);
		        	} else {
		        		limpiarInput();
		        		btnEliminar.setEnabled(false);
		        		btnModificar.setEnabled(false);
		        	}
		        }
		    }
		});
		
		actualizarTabla();
	}
	
	public void insertarPersona() {
		
	}
	
	public void modificarPersona() {

	}
	
	public void eliminarPersona() {

	}
	
	public void actualizarTabla() {
		LinkedList<Persona> lista = pDAO.getList();
        model.setRowCount(0);
        for (Persona persona : lista) {
            Object[] fila = new Object[7];
            fila[0] = persona.getId();
            fila[1] = persona.getDocumento();
            fila[2] = persona.getNombre1();
            fila[3] = persona.getNombre2();
            fila[4] = persona.getApellido1();
            fila[5] = persona.getApellido2();
            fila[6] = persona.getFechaNac();
            model.addRow(fila);
        }
	}
	
	public void limpiarInput() {

	}
	
	public void llenarCampos(int row) {

	}
	
	public Persona extraerCampos() {
		return new Persona();
	}
	
	public int extraerIDSeleccion() {
		int row = tPersona.getSelectedRow();
		int id = (int) tPersona.getValueAt(row, 0);
		return id;
	}
}
