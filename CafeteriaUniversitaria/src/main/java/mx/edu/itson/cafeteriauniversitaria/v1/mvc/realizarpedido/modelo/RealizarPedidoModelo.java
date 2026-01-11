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
 * Contiene la informacion utilizada durante el ciclo de vida de la pantalla
 * de realizacion de pedido.
 * @author Saul Neri
 */
public class RealizarPedidoModelo {

    /**
     * Observador interesado en los cambios de realizacion del pedido.
     * Vista interesada en el cambio para actualizar datos.
     */
    private CambiosPedidoObserver observador;
    
    /**
     * Detalle de pedido que esta siendo personalizado por el usuario.
     */
    private DetallePedidoDTO detalleActual;

    /**
     * Lista de detalles de pedido necesaria para almacenar los productos
     * personalizados por el usuario.
     */
    private List<DetallePedidoDTO> detallesPedido = new ArrayList<>();

    /**
     * Lista de productos disponibles en el sistema (traidos desde negocio).
     */
    private List<ProductoDTO> productosDisponibles;

    /**
     * Producto seleccionado actualmente por el usuario.
     */
    private ProductoDTO productoSeleccionado;
    
    /**
     * Tamano de producto seleccionado actualmente por el usuario.
     */
    private TamanoDTO tamanoSeleccionado;
    
    /**
     * Variante de producto seleccionada actualmente por el usuario.
     */
    private VarianteProductoDTO varianteSeleccionada;
    
    /**
     * Opciones de complemento elegidas por el usuario.
     * Representa la cantidad de complementos para cada uno elegido.
     */
    private List<OpcionComplementoDTO> complementosSeleccionados = new ArrayList<>();

    public RealizarPedidoModelo() {
        reiniciarDetalleActual();
    }
    
    public RealizarPedidoModelo(CambiosPedidoObserver observador) {
        super();
        this.observador = observador;
    }

    public RealizarPedidoModelo(List<ProductoDTO> productos) {
        reiniciarDetalleActual();
        this.productosDisponibles = productos;
    }
    
    public void setObservador(CambiosPedidoObserver observador) {
        this.observador = observador;
    }

    public void reiniciarDetalleActual() {
        this.detalleActual = new DetallePedidoDTO();
    }

    /**
     * Regresa el monto total por el pedido configurado actual.
     * @return 
     */
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
        
        if (this.productoSeleccionado == null) {
            throw new IllegalArgumentException("'this.productoSeleccionado' no debe ser 'null'");
        }
        
        if (complementosSeleccionados == null) {
            throw new IllegalArgumentException("'complementosSeleccionados' no debe ser 'null'");
        }
        
        boolean complementosValidos = complementosSeleccionados
                .stream()
                .allMatch(opt -> this.productoSeleccionado.complementosAceptados
                        .contains(opt.complemento)
                );
        
        if (!complementosValidos) {
            throw new IllegalArgumentException("Uno de los complementos no es aceptado por el producto seleccionado.");
        }
        
        float totalPorComplementos = (float) complementosSeleccionados.stream().mapToDouble(opt -> opt.obtenerMontoTotal()).sum();
        
        float monto = this.productoSeleccionado.precioBase 
                + tamanoSeleccionado.precioAdicional
                + totalPorComplementos;
        
        if (this.observador != null) {
            this.observador.actualizarMontoDetalleActual(monto);
        }
    }

    public ProductoDTO getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(ProductoDTO productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
        
        if (this.observador != null) {
            this.observador.actualizarMontoDetalleActual(this.productoSeleccionado.precioBase);
        }
    }

    public TamanoDTO getTamanoSeleccionado() {
        return tamanoSeleccionado;
    }

    public void setTamanoSeleccionado(TamanoDTO tamanoSeleccionado) {
        this.tamanoSeleccionado = tamanoSeleccionado;
         
        if (this.productoSeleccionado == null) {
            throw new IllegalArgumentException("RealizarPedidoModelo.setTamanoSeleccionado(TamanoDTO): 'this.productoSeleccionado' no debe ser 'null'");
        }
        
        if (!this.productoSeleccionado.getTamanosProducto().contains(tamanoSeleccionado)) {
            throw new IllegalArgumentException("RealizarPedidoModelo.setTamanoSeleccionado(TamanoDTO): El tamaño elegido no corresponde al producto seleccionado.");
        }
        
        if (this.observador != null) {
            this.observador.actualizarMontoDetalleActual(this.productoSeleccionado.precioBase + tamanoSeleccionado.precioAdicional);
        }
        
    }

    public VarianteProductoDTO getVarianteSeleccionada() {
        return varianteSeleccionada;
    }

    public void setVarianteSeleccionada(VarianteProductoDTO varianteSeleccionada) {
        this.varianteSeleccionada = varianteSeleccionada;
         
        if (this.productoSeleccionado == null) {
            throw new IllegalArgumentException("RealizarPedidoModelo.setVarianteSeleccionada(VarianteProductoDTO): 'this.productoSeleccionado' no debe ser 'null'");
        }
        
        /*
        if (!this.productoSeleccionado.getTamanosProducto().contains(this.tamanoSeleccionado)) {
            throw new IllegalArgumentException("RealizarPedidoModelo.setTamanoSeleccionado(TamanoDTO): El tamaño elegido no corresponde al producto seleccionado.");
        }*/
        
        if (!this.productoSeleccionado.getVariantes().contains(varianteSeleccionada)) {
            throw new IllegalArgumentException("RealizarPedidoModelo.setVarianteSeleccionada(VarianteProductoDTO): La variante elegida no corresponde al producto seleccionado.");
        }
        
        float monto = this.productoSeleccionado.precioBase + tamanoSeleccionado.precioAdicional;
        
        if (this.observador != null) {
            this.observador.actualizarMontoDetalleActual(monto);
        }
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

    /**
     * Prepara un objeto pedido para que pueda ser persistido en el sistema.
     * @return PedidoDTO Objeto pedido configurado y utilizado para mostrar
     * la informacion al usuario.
     */
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
