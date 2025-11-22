
package mx.edu.itson.cafeteriauniversitaria.v1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.VarianteProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador.ControladorRealizarPedido;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.RealizarPedidoModelo;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

/**
 *
 * @author Saul Neri
 */
public class CafeteriaUniversitaria {

    public static void main(String[] args) {
        
        Set<TamanoDTO> tamanos = new LinkedHashSet<>();
        tamanos.add(new TamanoDTO("Chico", 0.0f));
        tamanos.add(new TamanoDTO("Mediano", 10.0f));
        tamanos.add(new TamanoDTO("Grande", 17.0f));

        Set<VarianteProductoDTO> variantes = new LinkedHashSet<>();
        variantes.add(new VarianteProductoDTO("Normal"));
        variantes.add(new VarianteProductoDTO("Deslactosado"));
        variantes.add(new VarianteProductoDTO("Leche de Soya"));
        variantes.add(new VarianteProductoDTO("Leche de Almendras"));
        variantes.add(new VarianteProductoDTO("Leche de Coco"));

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

        RealizarPedidoModelo modelo = new RealizarPedidoModelo(productos);
        
        FrameRealizarPedido frame = new FrameRealizarPedido();
        
        ControladorRealizarPedido controlador = new ControladorRealizarPedido(frame, modelo);
        
        controlador.iniciar();
        
    }
}
