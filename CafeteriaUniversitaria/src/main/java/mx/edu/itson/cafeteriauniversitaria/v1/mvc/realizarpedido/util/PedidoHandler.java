/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.util;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.DetallePedidoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.PedidoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que permite construir un pedido y sus detalles sin
 * tener que inyectar paso por paso el detalle de pedido a los frames y paneles.
 * @author Saul Neri
 */
public class PedidoHandler {
    
    private static PedidoHandler INSTANCE;
    
    private List<DetallePedidoDTO> detallesPedido = new ArrayList<>();
    private DetallePedidoDTO detalleActual = null;
    
    private PedidoHandler() {
        this.detalleActual = new DetallePedidoDTO();
    }
    
    /**
     * Obtiene la instancia unica del manejador de pedido.
     * @return 
     */
    public static PedidoHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PedidoHandler();
        }
        
        return INSTANCE;
    }
    
    /**
     * Obtiene los detalles de pedido guardados a lo largo del flujo.
     * @return Lista de detalles de pedido.
     */
    public List<DetallePedidoDTO> getDetallesPedido() {
        return this.detallesPedido;
    }
    
    public DetallePedidoDTO getDetalleActual() {
        return this.detalleActual;
    }
    
    public void setDetalleActual(DetallePedidoDTO detalle) {
        this.detalleActual = detalle;
    }
    
    public void agregarDetallePedido(DetallePedidoDTO detalle) {
        this.detallesPedido.add(detalle);
        //this.reiniciarDetalleActual();
    }
    
    public void reiniciarDetalleActual() {
        this.detalleActual = new DetallePedidoDTO();
    }
    
    public DetallePedidoDTO eliminarDetallePedido(int index) {
        
        if (index < 0) {
            return null;
        }
        
        return this.detallesPedido.remove(index);
    }
    
    /**
     * Obtiene una nueva instancia de un pedido con un codigo de pedido unico
     * y con los detalle de pedidos anteriormente guardados.
     * @return 
     */
    public PedidoDTO obtenerPedido() {
        PedidoDTO p = new PedidoDTO(this.detallesPedido);
        return p;
    }
}
