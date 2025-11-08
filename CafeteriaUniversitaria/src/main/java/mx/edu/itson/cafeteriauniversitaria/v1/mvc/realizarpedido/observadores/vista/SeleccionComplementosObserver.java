/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.observadores.vista;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.OpcionComplementoDTO;
import java.util.List;

/**
 *
 * @author saul Neri
 */
public interface SeleccionComplementosObserver {
    void confirmarSeleccionComplementos(List<OpcionComplementoDTO> complementos);
}
