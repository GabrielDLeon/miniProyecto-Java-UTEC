package edu.utec.uy.view;

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
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.utec.uy.bo.FuncionalidadBO;
import edu.utec.uy.model.Funcionalidad;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class FuncionalidadVIEW2 extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDescripcion;
	private DefaultTableModel model;
	private FuncionalidadBO fBO = new FuncionalidadBO();
	private JTable tFuncionalidad;
	private int seleccion;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionalidadVIEW2 frame = new FuncionalidadVIEW2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FuncionalidadVIEW2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestión de Funcionalidades");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(45, 25, 289, 29);
		contentPane.add(lblTitulo);
		
		inputNombre = new JTextField();
		inputNombre.setBounds(45, 85, 289, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(45, 65, 75, 14);
		contentPane.add(lblNombre);
		                       
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcion.setBounds(45, 115, 75, 14);
		contentPane.add(lblDescripcion);
		
		inputDescripcion = new JTextField();
		inputDescripcion.setColumns(10);
		inputDescripcion.setBounds(45, 135, 289, 20);
		contentPane.add(inputDescripcion);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarRegistro();
			}
		});
		btnInsertar.setBounds(45, 205, 91, 23);
		contentPane.add(btnInsertar);
		
		JButton btnModificar = new JButton("Modficar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarRegistro();
			}
		});
		btnModificar.setBounds(146, 205, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRegistro();
			}
		});
		btnEliminar.setBounds(245, 205, 89, 23);
		contentPane.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 27, 341, 219);
		contentPane.add(scrollPane);
		
		tFuncionalidad = new JTable();
		tFuncionalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tFuncionalidad.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				seleccion = tFuncionalidad.rowAtPoint(e.getPoint());
				inputNombre.setText(tFuncionalidad.getValueAt(seleccion, 1)+"");
				inputDescripcion.setText(tFuncionalidad.getValueAt(seleccion, 2)+"");
			}
		});
		scrollPane.setViewportView(tFuncionalidad);
		model = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tFuncionalidad.setModel(model);
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		
		actualizarTabla();
	}

	public void insertarRegistro() {
		String nombre = inputNombre.getText();
		String descripcion = inputDescripcion.getText();
		
		Funcionalidad f = new Funcionalidad();
		f.setNombre(nombre);
		f.setDescripcion(descripcion);
		
		String msg = fBO.agregarFuncionalidad(f);
		JOptionPane.showMessageDialog(null, msg);
		
		actualizarTabla();
		limpiarInput();
	}
	
	public void modificarRegistro() {
		String nombre = inputNombre.getText();
		String descripcion = inputDescripcion.getText();
		
		Funcionalidad f = new Funcionalidad();
		f.setId((int) tFuncionalidad.getValueAt(seleccion, 0));
		f.setNombre(nombre);
		f.setDescripcion(descripcion);
		
		String msg = fBO.modificarFuncionalidad(f);
		JOptionPane.showMessageDialog(null, msg);
		
		actualizarTabla();
		limpiarInput();
	}
	
	public void eliminarRegistro() {		
		String msg = fBO.eliminarFuncionalidad((int) tFuncionalidad.getValueAt(seleccion, 0));
		JOptionPane.showMessageDialog(null, msg);

		actualizarTabla();
		limpiarInput();
	}
	
	public void limpiarInput() {
		inputNombre.setText("");
		inputDescripcion.setText("");
	}
	
	public void actualizarTabla() {
		LinkedList<Funcionalidad> listaFuncionalidad = fBO.listarFuncionalidad();
		model.setRowCount(0);
		for (Funcionalidad funcionalidad : listaFuncionalidad) {
			Object[] fila = new Object[3];
			fila[0] = funcionalidad.getId();
			fila[1] = funcionalidad.getNombre();
			fila[2] = funcionalidad.getDescripcion();
			
			model.addRow(fila);
		}
	}
}
