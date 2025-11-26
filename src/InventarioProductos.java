import java.util.ArrayList;

public class InventarioProductos {
    // Unicamente se debe usar ArrayList
    // Escriba los nombres de los Autores
    // Autor1: CARLOS ANGULO
    // Autor2: RICHARD SORIA

    // ArrayList para almacenar productos
    private ArrayList<Producto> productos;

    // Constructor - inicializa con 6 productos desordenados
    public InventarioProductos() {
        productos = new ArrayList<>();

        // Agregar 6 productos iniciales con IDs únicos (desordenados)
        productos.add(new Producto(5, "Router TP-Link AC1750", "ROUTER", 15, 89.99f));
        productos.add(new Producto(2, "Switch Cisco 24 puertos", "SWITCH", 8, 245.50f));
        productos.add(new Producto(8, "Expansor USB 3.0", "EXPANSOR", 25, 19.99f));
        productos.add(new Producto(1, "Router Netgear Nighthawk", "ROUTER", 10, 199.99f));
        productos.add(new Producto(4, "Switch D-Link 8 puertos", "SWITCH", 20, 45.75f));
        productos.add(new Producto(7, "Expansor HDMI 4K", "EXPANSOR", 12, 35.50f));
    }

    // Verificar si un ID ya existe
    private boolean existeId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Agregar producto con validación de ID único
    public boolean agregarProducto(Producto producto) {
        if (existeId(producto.getId())) {
            return false; // ID ya existe
        }
        productos.add(producto);
        return true;
    }

    // Búsqueda lineal por ID
    public Producto busquedaLineal(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Búsqueda binaria por ID (requiere que la lista esté ordenada por ID)
    public Producto busquedaBinaria(int id) {
        // Primero ordenamos por ID usando bubble sort
        ArrayList<Producto> productosOrdenados = new ArrayList<>(productos);
        for (int i = 0; i < productosOrdenados.size() - 1; i++) {
            for (int j = 0; j < productosOrdenados.size() - i - 1; j++) {
                if (productosOrdenados.get(j).getId() > productosOrdenados.get(j + 1).getId()) {
                    Producto temp = productosOrdenados.get(j);
                    productosOrdenados.set(j, productosOrdenados.get(j + 1));
                    productosOrdenados.set(j + 1, temp);
                }
            }
        }

        // Búsqueda binaria
        int inicio = 0;
        int fin = productosOrdenados.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (productosOrdenados.get(medio).getId() == id) {
                return productosOrdenados.get(medio);
            }

            if (productosOrdenados.get(medio).getId() < id) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return null;
    }

    // Búsqueda por categoría - retorna lista de productos
    public ArrayList<Producto> buscarPorCategoria(String categoria) {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Búsqueda por nombre - retorna el producto o mensaje si no se encuentra
    public String buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return p.toString();
            }
        }
        return "Producto no encontrado con el nombre: " + nombre;
    }

    // Ordenar productos por precio (mayor a menor) dentro de una categoría
    // Usando algoritmo Bubble Sort (sin Collections.sort)
    public ArrayList<Producto> ordenarPorPrecioEnCategoria(String categoria) {
        // Primero obtener productos de la categoría
        ArrayList<Producto> productosCat = buscarPorCategoria(categoria);

        // Bubble Sort - ordenar de mayor a menor precio
        for (int i = 0; i < productosCat.size() - 1; i++) {
            for (int j = 0; j < productosCat.size() - i - 1; j++) {
                if (productosCat.get(j).getPrecio() < productosCat.get(j + 1).getPrecio()) {
                    // Intercambiar
                    Producto temp = productosCat.get(j);
                    productosCat.set(j, productosCat.get(j + 1));
                    productosCat.set(j + 1, temp);
                }
            }
        }

        return productosCat;
    }

    // Búsqueda lineal del producto con menor precio
    public Producto buscarMenorPrecio() {
        if (productos.isEmpty()) {
            return null;
        }

        Producto menorPrecio = productos.get(0);

        for (Producto p : productos) {
            if (p.getPrecio() < menorPrecio.getPrecio()) {
                menorPrecio = p;
            }
        }

        return menorPrecio;
    }

    // Getter para obtener todos los productos
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Eliminar producto por ID
    public boolean eliminarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    // Actualizar producto
    public boolean actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                return true;
            }
        }
        return false;
    }
}
