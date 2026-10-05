package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Logistica;
import modelo.Envio;
import modelo.Terrestre;
import modelo.Aereo;
import modelo.Maritimo;

public class LogisticaVista extends JFrame {

    // Encabezados predeterminados para la tabla de envíos
    public String[] encabezados = new String[] { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" };

    // Componentes de la interfaz gráfica
    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
    private JComboBox cmbTipoEnvio;
    private DefaultTableModel dtm;
    private Logistica logistica;

    public LogisticaVista() {
        logistica = new Logistica();

        setSize(600, 400);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Barra de herramientas superior con botones de acción rápida
        JToolBar tbLogistica = new JToolBar();

        // Botón para desplegar el formulario de agregar envío
        JButton btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarEnvio();
            }
        });
        tbLogistica.add(btnAgregarEnvio);

        // Botón para eliminar el envío seleccionado de la tabla
        JButton btnQuitarEnvio = new JButton();
        btnQuitarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarEnvio.png")));
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitarEnvio();
            }
        });
        tbLogistica.add(btnQuitarEnvio);

        // Contenedor principal de envíos organizado de forma vertical 
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        // Panel de edición/formulario
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 250));
        pnlEditarEnvio.setLayout(null);

        // Etiqueta y campo de texto para el número o código de envío
        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);        
        pnlEditarEnvio.add(txtNumero);

        // Etiqueta y campo de texto para el nombre del cliente
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        // Etiqueta y campo de texto para el peso del envío
        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        // Etiqueta y selector desplegable para el tipo de envío
        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox();
        cmbTipoEnvio.setBounds(320, 10, 100, 25);
        String[] opciones = new String[] { "Terrestre", "Aéreo", "Marítimo" };
        DefaultComboBoxModel mdlTipoEnvio = new DefaultComboBoxModel(opciones);
        cmbTipoEnvio.setModel(mdlTipoEnvio);
        pnlEditarEnvio.add(cmbTipoEnvio);

        // Etiqueta y campo de texto para la distancia en kilómetros
        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        // Botón para guardar el nuevo envío registrado en el formulario
        JButton btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guardarEnvio();
            }
        });
        pnlEditarEnvio.add(btnGuardarEnvio);

        // Botón para cancelar la operación y ocultar el formulario
        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarEnvio();
            }
        });
        pnlEditarEnvio.add(btnCancelarEnvio);
        pnlEditarEnvio.setVisible(false); 

        // Panel de la tabla que muestra la lista de envíos
        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        // Configuración del modelo de la tabla y bloqueo de celdas editables directamente
        dtm = new DefaultTableModel(null, encabezados) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
         }
        };
        tblEnvios.setModel(dtm);

        // Agregar los componentes principales al contenedor de envíos
        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        JScrollPane spEnvios = new JScrollPane(pnlEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(tbLogistica, BorderLayout.NORTH);
        getContentPane().add(pnlEnvios, BorderLayout.CENTER);
    }

    
    public void agregarEnvio() {
        txtNumero.setText(logistica.generarCodigo());
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        pnlEditarEnvio.setVisible(true);
    }

   
    public void quitarEnvio() {
        int filaSeleccionada = tblEnvios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = dtm.getValueAt(filaSeleccionada, 1).toString(); // Columna 1 es Código
            logistica.quitarEnvio(codigo);
            dtm.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(this, "Envío eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un envío de la tabla para quitar.");
        }
    }

   
    public void guardarEnvio() {
        try {
            String codigo = txtNumero.getText();
            String cliente = txtCliente.getText();
            double peso = Double.parseDouble(txtPeso.getText());
            double distancia = Double.parseDouble(txtDistancia.getText());
            String tipoSeleccionado = (String) cmbTipoEnvio.getSelectedItem();

            Envio nuevoEnvio = null;

            // Creación de objetos según la selección del usuario
            if (tipoSeleccionado.equals("Terrestre")) {
                nuevoEnvio = new Terrestre(codigo, cliente, peso, distancia);
            } else if (tipoSeleccionado.equals("Aéreo")) {
                nuevoEnvio = new Aereo(codigo, cliente, peso, distancia);
            } else if (tipoSeleccionado.equals("Marítimo")) {
                nuevoEnvio = new Maritimo(codigo, cliente, peso, distancia);
            }

            if (nuevoEnvio != null) {
                logistica.agregarEnvio(nuevoEnvio);
                // Formatea el costo a cadena plana para evitar notación científica y conservar decimales
                String costoFormateado = java.math.BigDecimal.valueOf(nuevoEnvio.calcularTarifa()).toPlainString();
                Object[] fila = new Object[] {
                    tipoSeleccionado,
                    nuevoEnvio.getCodigo(),
                    nuevoEnvio.getCliente(),
                    nuevoEnvio.getPeso(),
                    nuevoEnvio.getDistancia(),                    
                    costoFormateado
                    };
                
                dtm.addRow(fila);

                pnlEditarEnvio.setVisible(false);
                JOptionPane.showMessageDialog(this, "Envío guardado con éxito.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos en Peso y Distancia.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void cancelarEnvio() {
        pnlEditarEnvio.setVisible(false);
    }
}