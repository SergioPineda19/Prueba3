import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author segio
 */
public class Principal extends JFrame{
    ArrayList<Empleado> empleados = new ArrayList<>();

    JTextField txtCodigo, txtNombre, txtSalario;
    JTextField txtBonificacion, txtDescuento;
    JTextField txtHoras, txtTarifa;

    JComboBox<String> cmbTipo;

    JButton btnRegistrar, btnCalcular, btnBuscar;
    JButton btnClonar, btnRegistrarClon, btnComparar;
    JButton btnMaximo, btnPromedio, btnLimpiar;

    JTable tabla;
    DefaultTableModel modelo;
    

    public Principal() {

        setTitle("Sistema de Gestion de Empleados");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(4, 4, 10, 10));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtSalario = new JTextField();
        txtBonificacion = new JTextField();
        txtDescuento = new JTextField();
        txtHoras = new JTextField();
        txtTarifa = new JTextField();

        cmbTipo = new JComboBox<>();
        cmbTipo.addItem("Administrativo");
        cmbTipo.addItem("Por Horas");

        panelDatos.add(new JLabel("Codigo:"));
        panelDatos.add(txtCodigo);

        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Tipo:"));
        panelDatos.add(cmbTipo);

        panelDatos.add(new JLabel("Salario base:"));
        panelDatos.add(txtSalario);

        panelDatos.add(new JLabel("Bonificacion:"));
        panelDatos.add(txtBonificacion);

        panelDatos.add(new JLabel("Descuento:"));
        panelDatos.add(txtDescuento);

        panelDatos.add(new JLabel("Horas trabajadas:"));
        panelDatos.add(txtHoras);

        panelDatos.add(new JLabel("Tarifa por hora:"));
        panelDatos.add(txtTarifa);

        add(panelDatos, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnRegistrar = new JButton("Registrar");
        btnCalcular = new JButton("Calcular salario");
        btnBuscar = new JButton("Buscar");
        btnClonar = new JButton("Clonar");
        btnRegistrarClon = new JButton("Registrar clon");
        btnComparar = new JButton("Comparar");
        btnMaximo = new JButton("Salario maximo");
        btnPromedio = new JButton("Promedio");
        btnLimpiar = new JButton("Limpiar");
        
        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnCalcular.addActionListener(e -> calcularSalario());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnClonar);
        panelBotones.add(btnRegistrarClon);
        panelBotones.add(btnComparar);
        panelBotones.add(btnMaximo);
        panelBotones.add(btnPromedio);
        panelBotones.add(btnLimpiar);

        String columnas[] = {"Codigo", "Nombre", "Tipo", "Salario", "Categoria"};

        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        JPanel panelCentro = new JPanel(new BorderLayout());

        panelCentro.add(panelBotones, BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Principal();
    }
    
    private void registrarEmpleado() {
    try {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los datos");
            return;
        }

        for (Empleado e : empleados) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                JOptionPane.showMessageDialog(this, "El codigo ya existe");
                return;
            }
        }

        double salario = Double.parseDouble(txtSalario.getText().trim());

        Empleado emp;

        if (cmbTipo.getSelectedIndex() == 0) {
            double bonificacion = Double.parseDouble(txtBonificacion.getText().trim());
            double descuento = Double.parseDouble(txtDescuento.getText().trim());

            emp = new EmpleadoAdministrativo(
                    codigo, nombre, salario, bonificacion, descuento);

        } else {
            int horas = Integer.parseInt(txtHoras.getText().trim());
            double tarifa = Double.parseDouble(txtTarifa.getText().trim());

            emp = new EmpleadoPorHoras(
                    codigo, nombre, salario, horas, tarifa);
        }

        empleados.add(emp);

        modelo.addRow(new Object[]{
            emp.getCodigo(),
            emp.getNombre(),
            cmbTipo.getSelectedItem(),
            emp.calcularSalario(),
            emp.obtenerCategoria()
        });

        JOptionPane.showMessageDialog(this, "Empleado registrado");

        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese valores numericos validos");

        } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }
    
    private void calcularSalario(){
        int fila = tabla.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado");
            return;
        }
        
        int pos = tabla.convertRowIndexToModel(fila);
        Empleado emp = empleados.get(pos);
        double salario = emp.calcularSalario();
        JOptionPane.showMessageDialog(this,"Empleado: "+emp.getNombre()+ "Salario: "+salario);
       
    }
    
}

