import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JTabbedPane tabbedPane1;
    private JPanel principal;
    private JTextField txtNombreProducto;
    private JTextField txtPrecioProducto;
    private JSpinner spiIdProducto;
    private JList lstProductos;
    private JSpinner spiCantidadProducto;
    private JButton btnEditarProducto;
    private JList lstProductosOrdenados;
    private JButton btnOrdenarProductos;
    private JComboBox cbcCategoriaProductoBuscar;
    private JTextField txtNombreBuscarProducto;
    private JButton btnBuscarProducto;
    private JButton btnBuscarProduto;
    private JComboBox cbcCategoriaProducto;
    // Escriba los nombres de los Autores
    // Autor1: CARLOS ANGULO
    // Autor2: RICHARD SORIA

    InventarioProductos inventarioProductos = new InventarioProductos();
    int indice;

    public void limpiarCampos() {
        txtNombreProducto.setText("");
        txtPrecioProducto.setText("");
        spiIdProducto.setValue(0);
        spiCantidadProducto.setValue(0);
        cbcCategoriaProducto.setSelectedIndex(0);
    }

    public void actualizarListaProductos() {
        DefaultListModel dlm = new DefaultListModel();
        List<Producto> productos = inventarioProductos.getProductos();
        for (Producto p : productos) {
            dlm.addElement(p.toString());
        }
        lstProductos.setModel(dlm);
    }

    public Ventana() {
        actualizarListaProductos();
        SpinnerNumberModel snmID = new SpinnerNumberModel(1, 1, 100, 1);
        SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, 100, 1);
        spiIdProducto.setModel(snmID);
        spiCantidadProducto.setModel(snm);

        lstProductos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstProductos.getSelectedIndex() != -1) {
                    indice = lstProductos.getSelectedIndex();
                    Producto productoSeleccionado = inventarioProductos.getProductos().get(indice);
                    txtNombreProducto.setText(productoSeleccionado.getNombre());
                    txtPrecioProducto.setText(String.valueOf(productoSeleccionado.getPrecio()));
                    cbcCategoriaProducto.setSelectedItem(productoSeleccionado.getCategoria());
                    spiIdProducto.setValue(productoSeleccionado.getId());
                    spiCantidadProducto.setValue(productoSeleccionado.getCantidad());
                    JOptionPane.showMessageDialog(null, "Producto seleccionado: " + productoSeleccionado.toString());
                }
            }
        });
        btnBuscarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(spiIdProducto.getValue().toString());
                Producto producto = inventarioProductos.busquedaLineal(id);
                if (producto != null) {
                    txtNombreProducto.setText(producto.getNombre());
                    txtPrecioProducto.setText(String.valueOf(producto.getPrecio()));
                    cbcCategoriaProducto.setSelectedItem(producto.getCategoria());
                    spiIdProducto.setValue(producto.getId());
                    spiCantidadProducto.setValue(producto.getCantidad());
                    JOptionPane.showMessageDialog(null, "Producto Encontrado: " + producto.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado");
                }
            }
        });

        btnEditarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar Campos Vacíos
                if (spiCantidadProducto.getValue().equals(0) || txtNombreProducto.getText().isEmpty() || txtPrecioProducto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Todos los campos deben estar llenos.");
                    return;
                }
                // Obtener datos del producto
                int id = Integer.parseInt(spiIdProducto.getValue().toString());
                String nombre = txtNombreProducto.getText();
                float precio;
                int cantidad = Integer.parseInt(spiCantidadProducto.getValue().toString());
                String categoria = cbcCategoriaProducto.getSelectedItem().toString();

                // Validar campos numéricos
                try{
                    precio = Float.parseFloat(txtPrecioProducto.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID y Precio debe ser un número");
                    return;
                }

                // Validar precio sea positivo
                if (precio <= 0) {
                    JOptionPane.showMessageDialog(null, "Precio debe ser positivo y diferente de cero");
                    return;
                }
                // Actualizar Producto
                Producto p = new Producto(id, nombre, categoria, cantidad, precio);
                boolean actualizado = inventarioProductos.actualizarProducto(p);
                if (actualizado) {
                    actualizarListaProductos();
                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado");
                }
            }
        });


        btnOrdenarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = cbcCategoriaProductoBuscar.getSelectedItem().toString();
                List<Producto> productosOrdenados = inventarioProductos.ordenarPorPrecioEnCategoria(categoria);
                DefaultListModel dlm = new DefaultListModel();
                for (Producto p : productosOrdenados) {
                    dlm.addElement(p.toString());
                }
                lstProductosOrdenados.setModel(dlm);
                JOptionPane.showMessageDialog(null, "Productos Ordenados por Precio");
            }
        });
        btnBuscarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtNombreBuscarProducto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de producto");
                    return;
                }
                String nombre = txtNombreBuscarProducto.getText();
                String resultado = inventarioProductos.buscarPorNombre(nombre);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Productos");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
