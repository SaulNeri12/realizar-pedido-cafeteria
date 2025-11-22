
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador;


import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.PersonalizacionProductoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.ConfirmacionAdicionProductoPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.RealizarPedidoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.SeleccionComplementosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.ProductosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.VariantesPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.TamanosPanel;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.RealizarPedidoModelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.VarianteProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;

import javax.swing.JPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Clase controladora para el manejo de la realizacion de un pedido.
 * @author Saul Neri
 */
public class ControladorRealizarPedido implements RealizarPedidoObserver, PersonalizacionProductoObserver {
    private FrameRealizarPedido vista;
    private RealizarPedidoModelo modelo;
    
    private Map<String, JPanel> panelesFlujoPersonalizacion = new HashMap<>();
    
    public ControladorRealizarPedido(FrameRealizarPedido vista, RealizarPedidoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.cargarPanelProductos();
    }
    
    private void cargarPanelProductos() {
        
        if (this.modelo == null) {
            return;
        }
        
        List<ProductoDTO> productos = this.modelo.getProductosDisponibles();
        
        if (productos == null) {
            // mostrar un error...
            return;
        }
        
        if (productos.isEmpty()) {
            // mostrar error...
            return;
        }
        
        ProductosPanel productosPanel = new ProductosPanel(productos);
        productosPanel.setObservador(this);
        
        this.panelesFlujoPersonalizacion.put("productos", productosPanel);
        this.vista.setPanel(productosPanel);
    }
    
    public void iniciar() {
        if (this.vista != null) {
            this.cargarPanelProductos();
            this.vista.setVisible(true);
        }
    }

    @Override
    public void onCompletarPedido() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void cargarTamanosProducto() {
        
        ProductoDTO productoSeleccionado = this.modelo.getProductoSeleccionado();
                
        if (productoSeleccionado == null) {
            System.out.println("Producto seleccionado: " + productoSeleccionado);
            return;
        }
        
        Set<TamanoDTO> tamanos = productoSeleccionado.getTamanosProducto();
        
        if (tamanos == null) {
            this.cargarVariantesProducto();
            return;
        }
        
        if (tamanos.isEmpty()) {
            this.cargarVariantesProducto();
            return;
        }
        
        TamanosPanel tamanosPanel = new TamanosPanel(productoSeleccionado);
        tamanosPanel.setVolverAtrasObserver(this);
        tamanosPanel.setObservador(this);
        
        this.panelesFlujoPersonalizacion.put("tamanos", tamanosPanel);
        this.vista.setPanel(tamanosPanel);
    }
    
    @Override
    public void onProductoSeleccionado(ProductoDTO producto) {
        this.modelo.setProductoSeleccionado(producto);
        System.out.println(producto);
        this.cargarTamanosProducto();
    }
    
    private void cargarVariantesProducto() {
        
        ProductoDTO productoSeleccionado = this.modelo.getProductoSeleccionado();
        
        if (productoSeleccionado == null) {
            return;
        }
        
        List<VarianteProductoDTO> variantes = productoSeleccionado.getVariantes();
        
        if (variantes == null) {
            this.cargarComplementosProducto();
            return;
        }
        
        if (variantes.isEmpty()) {
            this.cargarComplementosProducto();
            return;
        }
        
        VariantesPanel variantesPanel = new VariantesPanel(productoSeleccionado.getVariantes());
        variantesPanel.setVolverAtrasObserver(this);
        variantesPanel.setObservador(this);
        
        this.panelesFlujoPersonalizacion.put("variantes", variantesPanel);
        this.vista.setPanel(variantesPanel);
    }

    @Override
    public void onTamanoSeleccionado(TamanoDTO tamano) {
        this.modelo.setTamanoSeleccionado(tamano);
        this.cargarVariantesProducto();
    }

    private void cargarComplementosProducto() {
        
        ProductoDTO productoSeleccionado = this.modelo.getProductoSeleccionado();
        
        if (productoSeleccionado == null) {
            return;
        }
        
        List<ComplementoDTO> complementos = productoSeleccionado.getComplementosAceptados();
        
        if (complementos.isEmpty()) {
            this.cargarConfirmacionAdicionProducto();
            return;
        }
        
        SeleccionComplementosPanel compPanel = new SeleccionComplementosPanel(complementos);
        compPanel.setVolverAtrasObserver(this);
        compPanel.setObservador(this);
        
        this.panelesFlujoPersonalizacion.put("complementos", compPanel);
        this.vista.setPanel(compPanel);
    }
    
    @Override
    public void onVarianteSeleccionada(VarianteProductoDTO variante) {
        this.modelo.setVarianteSeleccionada(variante);
        this.cargarComplementosProducto();
    }

    private void cargarConfirmacionAdicionProducto() {
        
        if (this.modelo == null) {
            return;
        }
        
        ConfirmacionAdicionProductoPanel panel = new ConfirmacionAdicionProductoPanel(this.modelo.getDetallePedidoActual());
        panel.setVolverAtrasObserver(this);
        panel.setObservador(this);
        
        this.panelesFlujoPersonalizacion.put("confirmacion", panel);
        this.vista.setPanel(panel);
    }
    
    @Override
    public void onComplementosSeleccionados(List<OpcionComplementoDTO> complementos) {
        this.modelo.setComplementosSeleccionados(complementos);
        this.cargarConfirmacionAdicionProducto();
    }

    @Override
    public void onConfirmacionAdicionProducto() {
        
    }

    @Override
    public void volverA(String nombreComponente) {
        JPanel panel = this.panelesFlujoPersonalizacion.get(nombreComponente);
        
        if (panel != null) {
            System.out.println("### cambiando panel...");
            this.vista.setPanel(panel);
        }
    }
}
