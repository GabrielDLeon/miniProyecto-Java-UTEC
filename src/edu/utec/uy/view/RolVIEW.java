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

import edu.utec.uy.bo.RolBO;
import edu.utec.uy.model.Rol;
import edu.utec.uy.model.RolAdministrador;
import edu.utec.uy.model.RolJefe;
import edu.utec.uy.model.RolOperador;
import edu.utec.uy.model.TipoRol;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class RolVIEW extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDescripcion;
	private DefaultTableModel model;
	private JTable tRol;
	private JTextField inputBuscador;
	private JComboBox selectTipo;

	private RolBO rBO = new RolBO();

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
		setBounds(100, 100, 774, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestor de Rol");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(40, 20, 122, 29);
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
		inputBuscador.setBounds(355, 288, 281, 22);
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

		JLabel lblTipo = new JLabel("Tipo de Rol");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(40, 195, 91, 14);
		contentPane.add(lblTipo);

		JButton btnFuncionalidades = new JButton("Gestionar Funcionalidades");
		btnFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tRol.getSelectedRow();
				Rol rol = getRolFromTable(row);
				FuncRolVIEW panel = new FuncRolVIEW(rol);
				panel.setVisible(true);
			}
		});
		btnFuncionalidades.setEnabled(false);
		btnFuncionalidades.setBounds(40, 287, 289, 23);
		contentPane.add(btnFuncionalidades);

		List<TipoRol> lista = Arrays.asList(TipoRol.values());
		Object[] modelTipo = lista.toArray();
		selectTipo = new JComboBox(modelTipo);
		selectTipo.setBounds(40, 214, 290, 22);
		contentPane.add(selectTipo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 20, 380, 257);
		contentPane.add(scrollPane);

		tRol = new JTable();
		tRol.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyChar();
				if (key == 27) {
					tRol.clearSelection();
				}
			}
		});
		tRol.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tRol);
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tRol.setModel(model);
		model.addColumn("ID");
		model.addColumn("Tipo");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");

		tRol.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					int row = tRol.getSelectedRow();
					if (row >= 0) {
						llenarCampos(row);
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						btnFuncionalidades.setEnabled(true);
					} else {
						limpiarInput();
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnFuncionalidades.setEnabled(false);
					}
				}
			}
		});

		actualizarTabla();
	}

	public Rol getRolFromForm() {
		String nombre = inputNombre.getText();
		String description = inputDescripcion.getText();
		TipoRol tipo = (TipoRol) selectTipo.getSelectedItem();

		Rol rol = null;
		if (tipo.equals(TipoRol.ADMINISTRADOR)) {
			rol = new RolAdministrador();
		} else if (tipo.equals(TipoRol.JEFE_SECCION)) {
			rol = new RolJefe();
		} else if (tipo.equals(TipoRol.OPERADOR_SECCION)) {
			rol = new RolOperador();
		}

		rol.setNombre(nombre);
		rol.setDescripcion(description);

		return rol;
	}

	public void insertar() {
		Rol rol = getRolFromForm();
		String msg = rBO.agregarRol(rol);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
		limpiarInput();
	}

	public void modificar() {
		Rol rol = getRolFromForm();
		int id = extraerIDSeleccion();
		rol.setId(id);
		String msg = rBO.modificarRol(rol);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
	}

	public void eliminar() {
		int id = extraerIDSeleccion();
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro de la Base de datos?",
				"Confirmar acción", dialogButton);
		if (dialogResult == 0) {
			String msg = rBO.eliminarRol(id);
			actualizarTabla();
			JOptionPane.showMessageDialog(null, msg);
			limpiarInput();
		}
	}

	public void actualizarTabla() {
		LinkedList<Rol> lista;
		String filtro = inputBuscador.getText();
		lista = rBO.listarRol(filtro);

		model.setRowCount(0);
		for (Rol rol : lista) {
			Object[] fila = new Object[4];
			fila[0] = rol.getId();
			fila[1] = rol.getTipo();
			fila[2] = rol.getNombre();
			fila[3] = rol.getDescripcion();
			model.addRow(fila);
		}
	}

	public void limpiarInput() {
		inputNombre.setText("");
		inputDescripcion.setText("");
	}

	public void llenarCampos(int row) {
		Rol rol = getRolFromTable(row);
		selectTipo.setSelectedItem(rol.getTipo());
		inputNombre.setText(rol.getNombre());
		inputDescripcion.setText(rol.getDescripcion());
	}

	public int extraerIDSeleccion() {
		int row = tRol.getSelectedRow();
		int id = (int) tRol.getValueAt(row, 0);
		return id;
	}

	public Rol getRolFromTable(int row) {
		Rol rol = null;
		TipoRol tipo = (TipoRol) tRol.getValueAt(row, 1);
		if (tipo.equals(TipoRol.ADMINISTRADOR)) {
			rol = new RolAdministrador();
		} else if (tipo.equals(TipoRol.JEFE_SECCION)) {
			rol = new RolJefe();
		} else if (tipo.equals(TipoRol.OPERADOR_SECCION)) {
			rol = new RolOperador();
		}
		rol.setId((int) tRol.getValueAt(row, 0));
		rol.setNombre((String) tRol.getValueAt(row, 2));
		rol.setDescripcion((String) tRol.getValueAt(row, 3));
		return rol;
	}
}
