
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo;

/**
 * Encargdo de notificar cualquier cambio en la realización del pedido.
 * Encargado de actualizar el monto total por el detalle de pedido actual y 
 * general.
 * @author itson
 */
public interface CambiosPedidoObserver {
    
    /**
     * Actualiza el monto por el producto que está siendo personalizado (DetallePedido).
     * Utilizado para notificar a las vistas interesadas en el evento.
     * @param montoNuevo Nuevo monto por el detalle de pedido.
     */
    void actualizarMontoDetalleActual(float montoNuevo);
}
