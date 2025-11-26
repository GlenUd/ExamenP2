//Autor1: Gorozabel Glen
//Autor2: Quila Oliver


import java.util.ArrayList;

public class InventarioProductos {
    private ArrayList<Producto> productos;
    public InventarioProductos() {
        productos = new ArrayList<>();
        cargarProductosIniciales();
    }
    private void cargarProductosIniciales() {
        agregarProducto(new Producto(1,  "Xioami",   "Celular",   8, 450.50f));
        agregarProducto(new Producto(2,  "Mouse",  "Periferico",   5,  35.00f));
        agregarProducto(new Producto(3,  "Laptop",   "Tecnologia", 10, 800.99f));
        agregarProducto(new Producto(4,  "Refrigerador",    "Electrodomestico",   3,  600.00f));
        agregarProducto(new Producto(5,  "Camiseta",    "Vestimenta",   7,  21.75f));
        agregarProducto(new Producto(6,  "Manzana", "Frutas", 12, 1.99f));
    }
    public boolean agregarProducto(Producto nuevo) {
        if (buscarPorId(nuevo.getId()) != null) {
            return false;
        }
        productos.add(nuevo);
        return true;
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public Producto buscarPorId(int idBuscado) {
        for (Producto p : productos) {
            if (p.getId() == idBuscado) {
                return p;
            }
        }
        return null;
    }
    public boolean actualizarProducto(Producto productoEditado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoEditado.getId()) {
                productos.set(i, productoEditado);
                return true;
            }
        }
        return false;
    }
    public Producto buscarPorNombre(String nombreBuscado) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p;
            }
        }
        return null;
    }
    public ArrayList<Producto> buscarPorCategoria(String categoriaBuscada) {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoriaBuscada)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    public ArrayList<Producto> ordenarPorPrecioDescCategoria(String categoria) {
        ArrayList<Producto> lista = buscarPorCategoria(categoria);

        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1; j++) {
                if (lista.get(j).getPrecio() < lista.get(j + 1).getPrecio()) {
                    Producto aux = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, aux);
                }
            }
        }
        return lista;
    }
    public Producto buscarProductoMenorPrecio() {
        if (productos.isEmpty()) {
            return null;
        }

        Producto menor = productos.get(0);
        for (Producto p : productos) {
            if (p.getPrecio() < menor.getPrecio()) {
                menor = p;
            }
        }
        return menor;
    }
}