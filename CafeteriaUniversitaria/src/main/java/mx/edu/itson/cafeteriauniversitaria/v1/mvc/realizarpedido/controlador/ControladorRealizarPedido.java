/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.observadores.RealizarPedidoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.ManejadorPedido;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

/**
 *
 * @author Saul Neri
 */
public class ControladorRealizarPedido implements RealizarPedidoObserver {
    private FrameRealizarPedido vista;
    private ManejadorPedido modelo;
    
    public ControladorRealizarPedido(FrameRealizarPedido vista, ManejadorPedido modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
