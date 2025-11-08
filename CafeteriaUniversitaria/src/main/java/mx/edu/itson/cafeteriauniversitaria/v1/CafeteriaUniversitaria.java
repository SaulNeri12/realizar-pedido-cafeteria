
package mx.edu.itson.cafeteriauniversitaria.v1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.ComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.TamanoDTO;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador.ControladorRealizarPedido;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.ManejadorPedido;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

/**
 *
 * @author Saul Neri
 */
public class CafeteriaUniversitaria {

    public static void main(String[] args) {
        ManejadorPedido modelo = new ManejadorPedido();
        
        Set<TamanoDTO> tamanos = new LinkedHashSet<>();
        tamanos.add(new TamanoDTO("Chico", 0.0f));
        tamanos.add(new TamanoDTO("Mediano", 10.0f));
        tamanos.add(new TamanoDTO("Grande", 17.0f));

        Set<String> variantes = new LinkedHashSet<>();
        variantes.add("Normal");
        variantes.add("Deslactosado");
        variantes.add("Leche de Soya");
        variantes.add("Leche de Almendras");
        variantes.add("Leche de Coco");

        Set<ComplementoDTO> complementos = new LinkedHashSet<>();
        complementos.add(new ComplementoDTO("Azucar Morena", 7.0f));
        complementos.add(new ComplementoDTO("Stevia", 7.0f));
        complementos.add(new ComplementoDTO("Caramelo", 7.0f));
        complementos.add(new ComplementoDTO("Chispas de Chocolate", 10.0f));
        complementos.add(new ComplementoDTO("Crema Batida Extra", 10.0f));

        List<ProductoDTO> productos = new ArrayList<>();

        productos.add(new ProductoDTO("Iced Latte", 55.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        productos.add(new ProductoDTO("Iced Caramel Macchiato", 62.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        productos.add(new ProductoDTO("Iced Vainilla Latte", 59.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        productos.add(new ProductoDTO("Iced Mocha", 60.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        productos.add(new ProductoDTO("Iced Chai Latte", 58.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        productos.add(new ProductoDTO("Cold Brew Latte", 57.0f, tamanos, variantes, complementos, "imagenes/icedlatte.png"));
        
        FrameRealizarPedido vista = new FrameRealizarPedido(productos);
        
        vista.setVisible(true);
        
        ControladorRealizarPedido controlador = new ControladorRealizarPedido(vista, modelo);
        
    }
}
