
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.DetallePedidoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.PedidoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul Neri
 */
public class RealizarPedidoModelo {
    
    private DetallePedidoDTO detalleActual;
    
    private List<DetallePedidoDTO> detallesPedido = new ArrayList<>();
   
    private List<ProductoDTO> productosDisponibles;

    public RealizarPedidoModelo() {
        reiniciarDetalleActual();
    }
    
    public RealizarPedidoModelo(List<ProductoDTO> productos) {
        reiniciarDetalleActual();
    }
    
    public void reiniciarDetalleActual() {
        this.detalleActual = new DetallePedidoDTO();
    }
    
    
    
    
    public float getMontoTotalPedido() {
        return (float) this.detallesPedido.stream()
            .mapToDouble(d -> d.obtenerMontoTotal())
            .sum();
    }
    
    public PedidoDTO completarPedido() {
        
        PedidoDTO pedido = null;
        
        if (this.detallesPedido != null) {
            if (!this.detallesPedido.isEmpty()) {
                pedido = new PedidoDTO(this.detallesPedido);
            }
        }
        
        return pedido;
    }
}
