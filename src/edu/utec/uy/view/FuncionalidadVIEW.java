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

import edu.utec.uy.bo.FuncionalidadBO;
import edu.utec.uy.model.Funcionalidad;

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

public class FuncionalidadVIEW extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDescripcion;
	private DefaultTableModel model;
	private JTable tFuncionalidad;
	private JTextField inputBuscador;

	private FuncionalidadBO fBO = new FuncionalidadBO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionalidadVIEW frame = new FuncionalidadVIEW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FuncionalidadVIEW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestor de Funcionalidad");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(40, 20, 289, 29);
		contentPane.add(lblTitulo);

		inputNombre = new JTextField();
		inputNombre.setBounds(40, 80, 290, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(40, 60, 91, 14);
		contentPane.add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcion.setBounds(41, 110, 91, 14);
		contentPane.add(lblDescripcion);

		inputDescripcion = new JTextField();
		inputDescripcion.setColumns(10);
		inputDescripcion.setBounds(40, 130, 290, 55);
		contentPane.add(inputDescripcion);

		inputBuscador = new JTextField();
		inputBuscador.setBounds(355, 288, 281, 19);
		contentPane.add(inputBuscador);
		inputBuscador.setColumns(10);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		btnInsertar.setBounds(41, 257, 91, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modficar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(142, 257, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(241, 257, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		btnListar.setBounds(646, 287, 89, 23);
		contentPane.add(btnListar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 20, 380, 257);
		contentPane.add(scrollPane);

		tFuncionalidad = new JTable();
		tFuncionalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					tFuncionalidad.clearSelection();
				}
			}
		});
		tFuncionalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

		tFuncionalidad.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					int row = tFuncionalidad.getSelectedRow();
					if (row >= 0) {
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

	public Funcionalidad obtenerDatos() {
		String nombre = inputNombre.getText();
		String description = inputDescripcion.getText();

		Funcionalidad f = new Funcionalidad();

		f.setNombre(nombre);
		f.setDescripcion(description);
		return f;
	}

	public void insertar() {
		Funcionalidad f = obtenerDatos();
		String msg = fBO.agregarFuncionalidad(f);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
		limpiarInput();
	}

	public void modificar() {
		int id = extraerIDSeleccion();
		Funcionalidad f = obtenerDatos();
		f.setId(id);
		String msg = fBO.modificarFuncionalidad(f);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
	}

	public void eliminar() {
		int id = extraerIDSeleccion();
		String msg = fBO.eliminarFuncionalidad(id);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
		limpiarInput();
	}

	public void actualizarTabla() {
		String filter = inputBuscador.getText();
		LinkedList<Funcionalidad> lista = fBO.listarFuncionalidad(filter);
		model.setRowCount(0);
		for (Funcionalidad f : lista) {
			Object[] fila = new Object[3];
			fila[0] = f.getId();
			fila[1] = f.getNombre();
			fila[2] = f.getDescripcion();
			model.addRow(fila);
		}
	}

	public void limpiarInput() {
		inputNombre.setText("");
		inputDescripcion.setText("");
	}

	public void llenarCampos(int row) {
		inputNombre.setText(tFuncionalidad.getValueAt(row, 1) + "");
		inputDescripcion.setText(tFuncionalidad.getValueAt(row, 2) + "");
	}

	public int extraerIDSeleccion() {
		int row = tFuncionalidad.getSelectedRow();
		int id = (int) tFuncionalidad.getValueAt(row, 0);
		return id;
	}
}
