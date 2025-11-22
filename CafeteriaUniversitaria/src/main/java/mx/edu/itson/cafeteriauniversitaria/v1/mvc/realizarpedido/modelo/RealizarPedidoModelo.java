package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.VarianteProductoDTO;
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

    private ProductoDTO productoSeleccionado;
    private TamanoDTO tamanoSeleccionado;
    private VarianteProductoDTO varianteSeleccionada;
    private List<OpcionComplementoDTO> complementosSeleccionados = new ArrayList<>();

    public RealizarPedidoModelo() {
        reiniciarDetalleActual();
    }

    public RealizarPedidoModelo(List<ProductoDTO> productos) {
        reiniciarDetalleActual();
        this.productosDisponibles = productos;
    }

    public void reiniciarDetalleActual() {
        this.detalleActual = new DetallePedidoDTO();
    }

    public float getMontoTotalPedido() {
        return (float) this.detallesPedido.stream()
                .mapToDouble(d -> d.obtenerMontoTotal())
                .sum();
    }

    public List<ProductoDTO> getProductosDisponibles() {
        return productosDisponibles;
    }

    public List<OpcionComplementoDTO> getComplementosSeleccionados() {
        return complementosSeleccionados;
    }

    public void setComplementosSeleccionados(List<OpcionComplementoDTO> complementosSeleccionados) {
        this.complementosSeleccionados = complementosSeleccionados;
    }

    public ProductoDTO getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(ProductoDTO productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public TamanoDTO getTamanoSeleccionado() {
        return tamanoSeleccionado;
    }

    public void setTamanoSeleccionado(TamanoDTO tamanoSeleccionado) {
        this.tamanoSeleccionado = tamanoSeleccionado;
    }

    public VarianteProductoDTO getVarianteSeleccionada() {
        return varianteSeleccionada;
    }

    public void setVarianteSeleccionada(VarianteProductoDTO varianteSeleccionada) {
        this.varianteSeleccionada = varianteSeleccionada;
    }

    public void agregarDetallePedido() {
        DetallePedidoDTO detalle = this.getDetallePedidoActual();
        this.detallesPedido.add(detalle);
    }

    public DetallePedidoDTO getDetallePedidoActual() {
        DetallePedidoDTO detalle = new DetallePedidoDTO();

        // TODO: manejar errores...
        detalle.producto = this.productoSeleccionado;
        detalle.tamano = this.tamanoSeleccionado;
        detalle.variante = this.varianteSeleccionada;
        detalle.complementos = this.complementosSeleccionados;

        return detalle;
    }

    public void reiniciarPedido() {
        this.detalleActual = null;
        this.detallesPedido = new ArrayList<>();

        this.productosDisponibles = null;

        this.productoSeleccionado = null;
        this.tamanoSeleccionado = null;
        this.varianteSeleccionada = null;

        this.complementosSeleccionados = new ArrayList();
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
