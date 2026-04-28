import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal que se encarga de gestionar los juegos
 */
public class GestorJuegos {
    // Definido como final porque se inicializa en el constructor y no cambia su referencia
    private final Map<String, Integer> stockJuegos;
    private final int maxStock = 200;

    public GestorJuegos() {
        this.stockJuegos = new HashMap<>(); // [cite: 83]
    }

    public static void main(String[] args) {
        GestorJuegos tienda = new GestorJuegos();

        // Registro inicial [cite: 168]
        tienda.registrarLoteJuegos(new String[]{"ABC123", "ABC124"}, new int[]{10, 5});

        // Venta de prueba [cite: 217]
        tienda.venderJuego("ABC123", 2);

        tienda.mostrarStockJuegos();
    }

    public int registrarLoteJuegos(String[] codigos, int[] unidadesPorCodigo) {
        if (codigos.length != unidadesPorCodigo.length || codigos.length == 0) {
            return -1; // [cite: 171]
        }

        int cantidadTotalLote = 0;
        for (int cantidad : unidadesPorCodigo) {
            if (cantidad < 0) return -2; // [cite: 178]
            cantidadTotalLote += cantidad;
        }

        if (obtenerStockActual() + cantidadTotalLote > maxStock) {
            return -3; // [cite: 185]
        }

        for (int i = 0; i < codigos.length; i++) {
            stockJuegos.put(codigos[i], stockJuegos.getOrDefault(codigos[i], 0) + unidadesPorCodigo[i]);
        }
        return cantidadTotalLote;
    }

    public int venderJuego(String codigo, int cantidad) {
        try {
            validarCodigo(codigo); // [cite: 219]
            validarCantidad(cantidad); // [cite: 220]

            if (!stockJuegos.containsKey(codigo)) {
                System.out.println("No existe el juego con código " + codigo); // [cite: 230]
                return -1;
            }

            if (stockJuegos.get(codigo) < cantidad) {
                System.out.println("No hay suficiente stock para el código " + codigo); // [cite: 225]
                return -2;
            }

            stockJuegos.put(codigo, stockJuegos.get(codigo) - cantidad); // [cite: 223]
            return cantidad;

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // [cite: 234]
            return 0;
        }
    }

    private int obtenerStockActual() {
        int total = 0;
        for (int cant : stockJuegos.values()) {
            total += cant; // [cite: 201]
        }
        return total;
    }

    public void mostrarStockJuegos() {
        System.out.println("Stock de juegos:"); // [cite: 163]
        stockJuegos.forEach((cod, cant) -> System.out.println(cod + ": " + cant));
    }

    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0"); // [cite: 239]
        }
    }

    private void validarCodigo(String codigo) {
        if (codigo == null || codigo.length() != 6) {
            throw new IllegalArgumentException("El código debe tener 6 caracteres"); // [cite: 246]
        }
        if (!codigo.substring(0, 3).matches("[A-Z]+")) {
            throw new IllegalArgumentException("Los primeros 3 caracteres deben ser letras mayúsculas"); // [cite: 249]
        }
        if (!codigo.substring(3).matches("[0-9]+")) {
            throw new IllegalArgumentException("Los últimos 3 caracteres deben ser dígitos"); // [cite: 253]
        }
    }
}