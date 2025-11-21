/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.interfaces;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.observadores.VolverAtrasObserver;

/**
 * Se utiliza para indicar que una clase de tipo JPanel debe contener el metodo de asignacion
 * de un observador del tipo "VolverAtrasObserver". Se usa como contrato para asegurar que todos los paneles
 * navegables contengan dichos metodos para su asignacion y correcto funcionamiento del flujo de paneles.
 * @author Saul Neri
 */
public interface ComponenteNavegable {
    void setVolverAtrasObserver(VolverAtrasObserver observador);
}
