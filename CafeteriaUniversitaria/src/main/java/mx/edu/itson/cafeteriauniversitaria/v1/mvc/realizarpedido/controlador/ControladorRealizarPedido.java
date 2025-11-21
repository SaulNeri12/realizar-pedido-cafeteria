
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.PersonalizacionProductoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.RealizarPedidoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.RealizarPedidoModelo;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.OpcionComplementoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.VarianteProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;

import java.util.List;


/**
 *
 * @author Saul Neri
 */
public class ControladorRealizarPedido implements RealizarPedidoObserver, PersonalizacionProductoObserver {
    private FrameRealizarPedido vista;
    private RealizarPedidoModelo modelo;
    
    public ControladorRealizarPedido(FrameRealizarPedido vista, RealizarPedidoModelo modelo) {
        this.vista = vista;
        vista.setPersonalizacionProductoObservador(this);
        this.modelo = modelo;
    }
    
    public void iniciar() {
        if (this.vista != null) {
            this.vista.setVisible(true);
        }
    }

    @Override
    public void onCompletarPedido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onProductoSeleccionado(ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onTamanoSeleccionado(TamanoDTO tamano) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onVarianteSeleccionada(VarianteProductoDTO variante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onComplementosSeleccionados(List<OpcionComplementoDTO> complementos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onConfirmacionAdicionProducto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
