
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Contiene toda la informacion de un pedido (detalles de pedido, usuario, monto total, etc).
 * @author Saul Neri
 */
public class PedidoDTO {

    public String codigoPedido;
    private float montoTotal;
    //public String usuario;
    public String estado;

    public LocalDateTime fechaHoraCreacion;
    public LocalDateTime fechaHoraEntregado;
    public LocalDateTime fechaHoraCancelacion;

    private List<DetallePedidoDTO> detallesPedido;

    public PedidoDTO(List<DetallePedidoDTO> detallesPedido) {
        this.codigoPedido = UUID.randomUUID().toString();
        this.fechaHoraCreacion = LocalDateTime.now();
        this.detallesPedido = detallesPedido;
    }

    /**
     * Calcula el monto total del pedido a partir de sus detalle de pedidos.
     * @return montoTotal
     */
    public float obtenerMontoTotal() {
        if (this.detallesPedido == null) {
            return this.montoTotal = 0.0f;
        }

        this.montoTotal = (float) this.detallesPedido.stream()
            .mapToDouble(d -> d.obtenerMontoTotal())
            .sum();

        return this.montoTotal;
    }
}
