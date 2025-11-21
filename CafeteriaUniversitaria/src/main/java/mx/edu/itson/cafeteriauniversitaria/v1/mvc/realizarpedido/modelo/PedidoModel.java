/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo;

/**
 *
 * @author nerix
 */
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.DetallePedidoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.TamanoDTO;

public class PedidoModel { // O puedes añadir esto a tu PedidoHandler
    
    private DetallePedidoDTO detalleActual;
    private List<DetallePedidoDTO> detallesPedido = new ArrayList<>();
    private PropertyChangeSupport support;

    public PedidoModel() {
        this.support = new PropertyChangeSupport(this);
        reiniciarDetalleActual();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
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
            // No debería pasar si el flujo es correcto, pero es una buena validación
            return;
        }

        // 1. Actualiza el modelo
        this.detalleActual.complementos = complementos;

        // 2. ¡Notifica a la Vista!
        // Esto le avisa al Frame que el detalle cambió,
        // para que la Vista pueda recalcular y mostrar el nuevo monto total.
        support.firePropertyChange("detalleActualizado", null, this.detalleActual);
    }

    /**
     * Limpia la lista de complementos del detalle actual.
     * Se usa cuando el usuario presiona "Atrás" en el panel de complementos.
     */
    public void limpiarComplementosActuales() {
        if (this.detalleActual == null) {
            return;
        }

        // 1. Limpia los datos del modelo
        // El constructor de DetallePedidoDTO inicializa la lista,
        // así que solo necesitamos limpiarla.
        if (this.detalleActual.complementos != null) {
            this.detalleActual.complementos.clear();
        }

        // 2. ¡Notifica a la Vista!
        // El precio del detalle ha cambiado (se han quitado los complementos).
        support.firePropertyChange("detalleActualizado", null, this.detalleActual);
    }

    public void setProductoActual(ProductoDTO producto) {
        this.detalleActual.producto = producto;
        // ¡Notifica a la Vista que el detalle cambió!
        support.firePropertyChange("detalleActualizado", null, this.detalleActual);
    }

    public void setTamanoActual(TamanoDTO tamano) {
        this.detalleActual.tamano = tamano;
        support.firePropertyChange("detalleActualizado", null, this.detalleActual);
    }
    
    public void agregarDetalleActualAlPedido() {
        this.detallesPedido.add(this.detalleActual);
        // ¡Notifica a la Vista que la LISTA de pedidos cambió!
        support.firePropertyChange("listaPedidosActualizada", null, this.detallesPedido);
        reiniciarDetalleActual();
        support.firePropertyChange("detalleActualizado", null, this.detalleActual);
    }

    public void eliminarDetalle(DetallePedidoDTO detalle) {
        this.detallesPedido.remove(detalle);
        support.firePropertyChange("listaPedidosActualizada", null, this.detallesPedido);
    }
    
    public float getMontoTotalPedido() {
        return (float) this.detallesPedido.stream()
            .mapToDouble(d -> d.obtenerMontoTotal())
            .sum();
    }
}
