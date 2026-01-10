package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Contiene toda la informacion de un pedido (detalles de pedido, usuario, monto
 * total, etc).
 *
 * @author itson
 */
public class PedidoDTO {

    public String codigoPedido;
    private float montoTotal;
    public String usuario;
    public String estado;

    public LocalDateTime fechaHoraCreacion;
    public LocalDateTime fechaHoraEntregado;
    public LocalDateTime fechaHoraCancelacion;

    private List<DetallePedidoDTO> detallesPedido;

    /**
     * Constructor por defecto.
     * @param detallesPedido Lista de detalles de pedido 
     * (productos personalizados).
     */
    public PedidoDTO(List<DetallePedidoDTO> detallesPedido) {
        this.codigoPedido = UUID.randomUUID().toString();
        this.fechaHoraCreacion = LocalDateTime.now();
        this.detallesPedido = detallesPedido;
    }

    /**
     * Realiza el cálculo del monto total del pedido sumando cada detalle.
     * @return El costo total acumulado del pedido.
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

    @Override
    public String toString() {
        return String.format("Pedido [%s] | Estado: %s | Total: $%.2f | Fecha: %s",
                codigoPedido, estado, obtenerMontoTotal(), fechaHoraCreacion);
    }
}
