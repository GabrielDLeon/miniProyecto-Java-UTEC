package edu.utec.uy.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.utec.uy.bo.FuncRolBO;
import edu.utec.uy.bo.FuncionalidadBO;
import edu.utec.uy.model.Funcionalidad;
import edu.utec.uy.model.Rol;
import edu.utec.uy.model.RolAdministrador;
import edu.utec.uy.model.TipoRol;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FuncRolVIEW extends JFrame {

	private JPanel contentPane;
	private JTable tFuncionalidad;
	private JComboBox selectFuncionalidad;
	private DefaultTableModel model = new DefaultTableModel();

	private FuncRolBO frBO = new FuncRolBO();
	private FuncionalidadBO fBO = new FuncionalidadBO();

	private int idRol;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Esto son datos de prueba para el desarrollo
					Rol rol = new RolAdministrador();
					rol.setId(1);
					rol.setNombre("CONTADOR");
					rol.setDescripcion("HACE CUENTAS");
					rol.setTipo(TipoRol.JEFE_SECCION);
					FuncRolVIEW frame = new FuncRolVIEW(rol);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Para crear esta ventana hay que mandar un objeto de tipo Rol
	// Es necesario porque para agregar una funcionalidad requeris del idRol
	public FuncRolVIEW(Rol rol) {
		this.idRol = rol.getId();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Vincular Funcionalidades");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(124, 11, 192, 22);
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre del Rol");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(124, 34, 192, 14);
		lblNombre.setText(rol.getNombre());
		contentPane.add(lblNombre);

		JButton btnInsertar = new JButton("Insertar Funcionalidad");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnInsertar.setBounds(35, 75, 139, 23);
		contentPane.add(btnInsertar);

		LinkedList<Funcionalidad> lista = fBO.listarFuncionalidad("");
		Object[] modelFuncionalidad = lista.toArray();
		selectFuncionalidad = new JComboBox(modelFuncionalidad);
		selectFuncionalidad.setBounds(185, 75, 213, 22);
		contentPane.add(selectFuncionalidad);

		JButton btnEliminar = new JButton("Quitar Funcionalidad");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnEliminar.setBounds(36, 276, 362, 23);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 109, 362, 156);
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
						btnEliminar.setEnabled(true);
					} else {
						btnEliminar.setEnabled(false);
					}
				}
			}
		});

		actualizarTabla();
	}

	public void insert() {
		Funcionalidad f = (Funcionalidad) selectFuncionalidad.getSelectedItem();
		String msg = frBO.insert(this.idRol, f.getId());
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
	}

	private void delete() {
		int row = tFuncionalidad.getSelectedRow();
		int idFuncionalidad = (int) tFuncionalidad.getValueAt(row, 0);
		String msg = frBO.delete(this.idRol, idFuncionalidad);
		actualizarTabla();
		JOptionPane.showMessageDialog(null, msg);
	}

	public void actualizarTabla() {
		LinkedList<Funcionalidad> lista = frBO.getList(this.idRol);
		model.setRowCount(0);
		for (Funcionalidad f : lista) {
			Object[] fila = new Object[3];
			fila[0] = f.getId();
			fila[1] = f.getNombre();
			fila[2] = f.getDescripcion();
			model.addRow(fila);
		}
	}
}
