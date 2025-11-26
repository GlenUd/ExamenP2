//Escriba los nombres de los Autores
//Autor1: Gorozabel Glen
//Autor2: Oliver Quila
/**git init
        ls
        git add out
        git commit -m "udla_1"
        git log
        git branch -M main
        git remote add origin https://github.com/GlenUd/Udla.git
        git push -u origin main

**/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

    public class Ventana extends JFrame {

        private JPanel Principal;
        private JTabbedPane tabbedPane1;
        private JTextField txtNombre;
        private JComboBox<String> cboCategoria;
        private JSpinner spiCantidad;
        private JTextField txtPrecio;
        private JList<String> listProductos;
        private JSpinner spnID;
        private JButton btnBuscar;
        private JButton btnModificar;
        private JList<String> list1;
        private JComboBox<String> comboBox1;
        private JButton btnOrdenar;
        private InventarioProductos inventario;
        private DefaultListModel<String> modeloListaProductos;
        private DefaultListModel<String> modeloListaInventario;



        public Ventana() {

            setContentPane(Principal);
            setTitle("Inventario de Productos");
            setSize(700, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventario = new InventarioProductos();
            modeloListaProductos = new DefaultListModel<>();
            modeloListaInventario = new DefaultListModel<>();
            listProductos.setModel(modeloListaProductos);
            list1.setModel(modeloListaInventario);

            cargarCategorias();
            cargarListaProductos();
            configurarEventos();

            list1.addComponentListener(new ComponentAdapter() {
            });
        }


        private void cargarCategorias() {

            String[] categorias = {"Celular", "Periferico", "Tecnologia","Electrodomesticos","Vestimenta","Fruta"};

            cboCategoria.removeAllItems();
            comboBox1.removeAllItems();

            for (int i = 0; i < categorias.length; i++) {
                cboCategoria.addItem(categorias[i]);
                comboBox1.addItem(categorias[i]);
            }
        }

        private void cargarListaProductos() {
            modeloListaProductos.clear();

            ArrayList<Producto> lista = inventario.getProductos();
            for (int i = 0; i < lista.size(); i++) {
                Producto p = lista.get(i);
                modeloListaProductos.addElement(p.toString());
            }

        }

        private void configurarEventos() {
            btnBuscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buscarProductoPorId();
                }
            });

            btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarGuardarProducto();
                }
            });

            btnOrdenar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ordenarPorCategoria();
                }
            });
        }

        private void buscarProductoPorId() {
            int id = (Integer) spnID.getValue();
            Producto p = inventario.buscarPorId(id);
            if (p == null) {
                JOptionPane.showMessageDialog(null, "No se encontró producto con ID: " + id);
                txtNombre.setText("");
                txtPrecio.setText("");
                spiCantidad.setValue(0);
            } else {
                txtNombre.setText(p.getNombre());
                cboCategoria.setSelectedItem(p.getCategoria());
                spiCantidad.setValue(p.getCantidad());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
            }
        }

        private void editarGuardarProducto() {

            int id = (Integer) spnID.getValue();
            Producto p = inventario.buscarPorId(id);
            if (p == null) {
                JOptionPane.showMessageDialog(null, "Primero busca un producto válido por ID.");
                return;
            }

            String nombre = txtNombre.getText().trim();
            String categoria = (String) cboCategoria.getSelectedItem();
            int cantidad = (Integer) spiCantidad.getValue();

            float precio;
            try {
                precio = Float.parseFloat(txtPrecio.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Precio inválido. Debe ser un número.");
                return;
            }

            Producto editado = new Producto(id, nombre, categoria, cantidad, precio);
            boolean actualizado = inventario.actualizarProducto(editado);
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
                cargarListaProductos();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.");
            }
        }

        private void ordenarPorCategoria() {

            String categoria = (String) comboBox1.getSelectedItem();
            ArrayList<Producto> listaOrdenada =
            inventario.ordenarPorPrecioDescCategoria(categoria);
            modeloListaInventario.clear();
            for (int i = 0; i < listaOrdenada.size(); i++) {
                modeloListaInventario.addElement(listaOrdenada.get(i).toString());
            }
        }



        public static void main(String[] args) {
            JFrame frame = new JFrame("Ventana");
            frame.setContentPane(new Ventana().Principal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

