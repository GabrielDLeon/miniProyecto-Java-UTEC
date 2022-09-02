package edu.utec.uy.view;

import java.awt.EventQueue;

import javax.management.modelmbean.ModelMBean;
import javax.management.relation.RoleStatus;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.utec.uy.bo.FuncRolBO;
import edu.utec.uy.bo.PersonaBO;
import edu.utec.uy.bo.RolBO;
import edu.utec.uy.dao.RolDAO;
import edu.utec.uy.model.Funcionalidad;
import edu.utec.uy.model.Persona;
import edu.utec.uy.model.Rol;

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
import java.sql.Date;

import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

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
    private JDateChooser dateChooser;

    private RolBO rBO = new RolBO();
    private FuncRolBO frBO = new FuncRolBO();

    private LinkedList<Rol> roles = rBO.listarRol("");
    private Object[] obj = roles.toArray();

    private JComboBox comboBox = new JComboBox(obj);

    private PersonaBO pBO = new PersonaBO();

    private JTextField inputEmail;
    private JPasswordField inputClave;

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
	setBounds(100, 100, 800, 500);
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
	inputBusquedaNombre.setBounds(368, 377, 135, 19);
	contentPane.add(inputBusquedaNombre);
	inputBusquedaNombre.setColumns(10);

	inputBusquedaApellido = new JTextField();
	inputBusquedaApellido.setColumns(10);
	inputBusquedaApellido.setBounds(514, 377, 135, 19);
	contentPane.add(inputBusquedaApellido);

	JButton btnInsertar = new JButton("Insertar");
	btnInsertar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		insertarPersona();
	    }
	});
	btnInsertar.setBounds(41, 375, 91, 23);
	contentPane.add(btnInsertar);

	JButton btnModificar = new JButton("Modficar");
	btnModificar.setEnabled(false);
	btnModificar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		modificarPersona();
	    }
	});
	btnModificar.setBounds(142, 375, 89, 23);
	contentPane.add(btnModificar);

	JButton btnEliminar = new JButton("Eliminar");
	btnEliminar.setEnabled(false);
	btnEliminar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		eliminarPersona();
	    }
	});
	btnEliminar.setBounds(241, 375, 89, 23);
	contentPane.add(btnEliminar);

	JButton btnListar = new JButton("Listar");
	btnListar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		actualizarTabla();
	    }
	});
	btnListar.setBounds(659, 375, 89, 23);
	contentPane.add(btnListar);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(340, 28, 408, 338);
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

	inputEmail = new JTextField();
	inputEmail.setColumns(10);
	inputEmail.setBounds(142, 68, 188, 20);
	contentPane.add(inputEmail);

	JLabel lblMail = new JLabel("Email");
	lblMail.setBounds(41, 71, 91, 14);
	contentPane.add(lblMail);

	JLabel lblClave = new JLabel("Clave");
	lblClave.setBounds(41, 99, 91, 14);
	contentPane.add(lblClave);

	inputClave = new JPasswordField();
	inputClave.setBounds(142, 99, 188, 20);
	contentPane.add(inputClave);

	dateChooser = new JDateChooser();
	dateChooser.setDateFormatString("dd/MM/yyyy");

	dateChooser.setBounds(142, 281, 188, 20);
	contentPane.add(dateChooser);

	JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
	lblFechaNacimiento.setBounds(41, 287, 91, 14);
	contentPane.add(lblFechaNacimiento);

	JLabel lblRol = new JLabel("Rol");
	lblRol.setBounds(41, 323, 91, 14);
	contentPane.add(lblRol);

	comboBox.setBounds(142, 312, 188, 22);
	contentPane.add(comboBox);

	JButton btnVerFuncionalidades = new JButton("Ver funcionalidades");
	btnVerFuncionalidades.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = tPersona.getSelectedRow();
		int idRol = (int) tPersona.getValueAt(row, 8);
		LinkedList<Funcionalidad> lista = frBO.getList(idRol);

		String mensaje = "Funcionalidades disponibles:\n";
		if (lista.size() <= 0) {
		    mensaje += "Esta persona no puede usar ninguna funcionalidades :P";
		} else {
		    for (Funcionalidad funcionalidad : lista) {
			mensaje += funcionalidad.getNombre();
		    }
		}
	    }
	});
	btnVerFuncionalidades.setBounds(41, 348, 289, 23);
	contentPane.add(btnVerFuncionalidades);

	model.addColumn("ID");
	model.addColumn("Documento");
	model.addColumn("Nombre");
	model.addColumn("Nombre");
	model.addColumn("Apellido");
	model.addColumn("Apellido");
	model.addColumn("Email");
	model.addColumn("Nacimiento");
	model.addColumn("Rol");

	tPersona.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent lse) {
		if (!lse.getValueIsAdjusting()) {
		    int row = tPersona.getSelectedRow();
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

    public Persona getPersonaFromForm() {
	Persona p = new Persona();
	String documento = inputDocumento.getText();
	String nombre1 = inputNombre1.getText();
	String nombre2 = inputNombre2.getText();
	String apellido1 = inputApellido1.getText();
	String apellido2 = inputApellido2.getText();
	String email = inputEmail.getText();
	String clave = inputClave.getText();
	Rol rol = (Rol) comboBox.getSelectedItem();

	Date fechaNac = null;
	try {
	    fechaNac = new Date(dateChooser.getDate().getTime());
	} catch (Exception e) {
	    e.getMessage();
	}

	p.setDocumento(documento);
	p.setNombre1(nombre1);
	p.setNombre2(nombre2);
	p.setApellido1(apellido1);
	p.setApellido2(apellido2);
	p.setFechaNac(fechaNac);
	p.setMail(email);
	p.setClave(clave);
	p.setRol(rol);

	return p;
    }

    public void insertarPersona() {
	Persona p = getPersonaFromForm();
	String msg = pBO.agregarPersona(p);
	actualizarTabla();
	limpiarInput();
	JOptionPane.showMessageDialog(null, msg);
    }

    public void modificarPersona() {
	Persona p = getPersonaFromForm();
	int id = extraerIDSeleccion();
	p.setId(id);
	String msg = pBO.modificarPersona(p);
	actualizarTabla();
	JOptionPane.showMessageDialog(null, msg);
    }

    public void eliminarPersona() {
	int id = extraerIDSeleccion();
	String msg = pBO.eliminarPersona(id);
	actualizarTabla();
	limpiarInput();
	JOptionPane.showMessageDialog(null, msg);
    }

    public void actualizarTabla() {
	LinkedList<Persona> lista = pBO.listarPersona();
	model.setRowCount(0);
	for (Persona persona : lista) {
	    Object[] fila = new Object[9];
	    fila[0] = persona.getId();
	    fila[1] = persona.getDocumento();
	    fila[2] = persona.getNombre1();
	    fila[3] = persona.getNombre2();
	    fila[4] = persona.getApellido1();
	    fila[5] = persona.getApellido2();
	    fila[6] = persona.getMail();
	    fila[7] = persona.getFechaNac();
	    fila[8] = persona.getRol();
	    model.addRow(fila);
	}
    }

    public void limpiarInput() {

    }

    public void llenarCampos(int row) {
	inputDocumento.setText(tPersona.getValueAt(row, 1) + "");
	inputNombre1.setText(tPersona.getValueAt(row, 2) + "");
	inputNombre2.setText(tPersona.getValueAt(row, 3) + "");
	inputApellido1.setText(tPersona.getValueAt(row, 4) + "");
	inputApellido2.setText(tPersona.getValueAt(row, 5) + "");
	inputEmail.setText(tPersona.getValueAt(row, 6) + "");
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
