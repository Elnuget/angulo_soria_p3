public class Producto {
    // Escriba los nombres de los Autores
    // Autor1: CARLOS ANGULO
    // Autor2: RICHARD SORIA

    // Atributos
    private int id; // Único, manejado con Spinner
    private String nombre; // TextField
    private String categoria; // Combobox: ROUTER, SWITCH, EXPANSOR
    private int cantidad; // Spinner
    private float precio; // TextField

    // Constructor
    public Producto(int id, String nombre, String categoria, int cantidad, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    // toString para visualización legible
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Categoría: " + categoria +
                " | Cantidad: " + cantidad + " | Precio: $" + String.format("%.2f", precio);
    }
}
