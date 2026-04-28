import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestorJuegosTest {
    private GestorJuegos gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorJuegos();
    }

    @Test
    void testCaso1_LongitudesDiferentes_RetornaMenosUno() {
        String[] codigos = {"Juego1", "Juego2"};
        int[] unidades = {10};
        assertEquals(-1, gestor.registrarLoteJuegos(codigos, unidades));
    }

    @Test
    void testCaso2_ArraysVacios_RetornaMenosUno() {
        String[] codigos = {};
        int[] unidades = {}; // Arrays vacíos [cite: 451]
        assertEquals(-1, gestor.registrarLoteJuegos(codigos, unidades));
    }

    @Test
    void testCaso3_CantidadNegativa_RetornaMenosDos() {
        String[] codigos = {"Juego1"};
        int[] unidades = {-5}; // Valor negativo [cite: 458]
        assertEquals(-2, gestor.registrarLoteJuegos(codigos, unidades));
    }

    @Test
    void testCaso4_ExcederStockMaximo_RetornaMenosTres() {
        String[] codigos = {"Juego1"};
        int[] unidades = {500};
        assertEquals(-3, gestor.registrarLoteJuegos(codigos, unidades));
    }

    @Test
    void testCaso5_ActualizarJuegoExistente_RetornaUnidades() {
        gestor.registrarLoteJuegos(new String[]{"Mario"}, new int[]{10});
        int resultado = gestor.registrarLoteJuegos(new String[]{"Mario"}, new int[]{5});
        assertEquals(5, resultado);
    }

    @Test
    void testCaso6_InsertarJuegoNuevo_RetornaUnidades() {
        String[] codigos = {"Zelda"};
        int[] unidades = {5}; // Registro nuevo [cite: 473]
        assertEquals(5, gestor.registrarLoteJuegos(codigos, unidades));
    }

    @Test
    void testCaso8_RegistroExitosoEstandar_RetornaTotal() {
        String[] codigos = {"Juego1"};
        int[] unidades = {10};
        assertEquals(10, gestor.registrarLoteJuegos(codigos, unidades));
    }
}