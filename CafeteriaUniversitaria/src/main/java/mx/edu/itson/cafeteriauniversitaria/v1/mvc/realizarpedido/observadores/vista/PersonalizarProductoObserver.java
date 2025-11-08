
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.observadores.vista;

/**
 * Encargado del cambio de paneles en la vista padre (FrameRealizarPedido).
 * @author Saul Neri
 */
public interface PersonalizarProductoObserver extends 
        SeleccionProductoObserver, 
        SeleccionTamanoObserver, 
        SeleccionVarianteObserver, 
        SeleccionComplementosObserver, 
        ConfirmacionAdicionProductoObserver {}