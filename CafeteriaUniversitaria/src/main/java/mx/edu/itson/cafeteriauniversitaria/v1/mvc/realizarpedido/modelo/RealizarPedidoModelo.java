
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.DetallePedidoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.PedidoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;

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
    
    public List<ProductoDTO> productosDisponibles() {
        return this.productosDisponibles;
    }
    
    public void reiniciarDetalleActual() {
        this.detalleActual = new DetallePedidoDTO();
    }

    public DetallePedidoDTO getDetalleActual() {
        return this.detalleActual;
    }

    public List<DetallePedidoDTO> getDetallesPedido() {
        return this.detallesPedido;
    }

    /**
     * Asigna la lista de complementos seleccionados al detalle
     * que se está personalizando actualmente.
     * * @param complementos La lista de opciones seleccionadas en el panel.
     */
    public void setComplementosActuales(List<OpcionComplementoDTO> complementos) {
        if (this.detalleActual == null) {
            return;
        }
        
        this.detalleActual.complementos = complementos;
    }

    /**
     * Limpia la lista de complementos del detalle actual.
     * Se usa cuando el usuario presiona "Atrás" en el panel de complementos.
     */
    public void limpiarComplementosActuales() {
        if (this.detalleActual == null) {
            return;
        }
        
        if (this.detalleActual.complementos != null) {
            this.detalleActual.complementos.clear();
        }
        
    }

    public void setProductoActual(ProductoDTO producto) {
        this.detalleActual.producto = producto;
    }

    public void setTamanoActual(TamanoDTO tamano) {
        this.detalleActual.tamano = tamano;
    }
    
    public void agregarDetalleActualAlPedido() {
        this.detallesPedido.add(this.detalleActual);
        reiniciarDetalleActual();
    }

    public void eliminarDetalle(DetallePedidoDTO detalle) {
        this.detallesPedido.remove(detalle);
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
