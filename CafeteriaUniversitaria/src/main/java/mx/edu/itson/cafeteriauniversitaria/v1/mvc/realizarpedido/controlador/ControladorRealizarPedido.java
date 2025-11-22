
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador;

import java.util.HashMap;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.PersonalizacionProductoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.RealizarPedidoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.RealizarPedidoModelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.VarianteProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.swing.JPanel;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.ProductosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.SeleccionComplementosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.TamanosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.VariantesPanel;


/**
 *
 * @author Saul Neri
 */
public class ControladorRealizarPedido implements RealizarPedidoObserver, PersonalizacionProductoObserver {
    private FrameRealizarPedido vista;
    private RealizarPedidoModelo modelo;
    
    private Map<String, JPanel> panelesFlujoPersonalizacion = new HashMap<>();
    
    private ProductoDTO productoSeleccionado;
    private TamanoDTO tamanoSeleccionado;
    private VarianteProductoDTO varianteSeleccionada;
    private List<OpcionComplementoDTO> complementosSeleccioandos;
    
    public ControladorRealizarPedido(FrameRealizarPedido vista, RealizarPedidoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    private void cargarPanelProductos() {
        
        if (this.modelo != null) {
            return;
        }
        
        if (this.modelo.productosDisponibles().isEmpty()) {
            // mostrar un error...
            return;
        }
        
        ProductosPanel productosPanel = new ProductosPanel(this.modelo.productosDisponibles());
        
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarTamanosProducto() {
        
        if (this.productoSeleccionado != null) {
            return;
        }
        
        Set<TamanoDTO> tamanos = this.productoSeleccionado.getTamanosProducto();
        
        if (tamanos != null) {
            this.cargarVariantesProducto();
            return;
        }
        
        if (tamanos.isEmpty()) {
            this.cargarVariantesProducto();
            return;
        }
        
        TamanosPanel tamanosPanel = new TamanosPanel(this.productoSeleccionado);
        
        this.panelesFlujoPersonalizacion.put("tamanos", tamanosPanel);
        this.vista.setPanel(tamanosPanel);
    }
    
    @Override
    public void onProductoSeleccionado(ProductoDTO producto) {
        this.cargarTamanosProducto();
    }
    
    private void cargarVariantesProducto() {
        
        if (this.productoSeleccionado != null) {
            return;
        }
        
        List<VarianteProductoDTO> variantes = this.productoSeleccionado.getVariantes();
        
        if (variantes != null) {
            // mostrar error u omitir frame
            return;
        }
        
        if (variantes.isEmpty()) {
            // mostrar error u omitir frame
            return;
        }
        
        VariantesPanel variantesPanel = new VariantesPanel(this.productoSeleccionado.getVariantes());
        
        this.panelesFlujoPersonalizacion.put("variantes", variantesPanel);
        this.vista.setPanel(variantesPanel);
    }

    @Override
    public void onTamanoSeleccionado(TamanoDTO tamano) {
        this.cargarVariantesProducto();
    }

    private void cargarComplementosProducto() {
        if (this.productoSeleccionado != null) {
            
            return;
        }
        
        List<ComplementoDTO> complementos = this.productoSeleccionado.getComplementosAceptados();
        
        if (complementos.isEmpty()) {
            
            return;
        }
        
        SeleccionComplementosPanel compPanel = new SeleccionComplementosPanel(complementos);
        this.panelesFlujoPersonalizacion.put("variantes", compPanel);
        this.vista.setPanel(compPanel);
        
    }
    
    @Override
    public void onVarianteSeleccionada(VarianteProductoDTO variante) {
        this.cargarComplementosProducto();
    }

    @Override
    public void onComplementosSeleccionados(List<OpcionComplementoDTO> complementos) {
        
    }

    @Override
    public void onConfirmacionAdicionProducto() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
