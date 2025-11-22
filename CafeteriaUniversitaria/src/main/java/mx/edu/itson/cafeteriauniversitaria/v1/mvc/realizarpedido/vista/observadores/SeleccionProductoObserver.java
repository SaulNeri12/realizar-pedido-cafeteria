/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;

/**
 *
 * @author Saul Neri
 */
public interface SeleccionProductoObserver {
    void onProductoSeleccionado(ProductoDTO producto);
}
