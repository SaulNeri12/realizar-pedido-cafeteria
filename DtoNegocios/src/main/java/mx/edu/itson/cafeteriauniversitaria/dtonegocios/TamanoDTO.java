/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.dtonegocios;

/**
 *
 * @author Saul Neri
 */
public class TamanoDTO {
    public String nombre;
    public float precioAdicional;
    
    public TamanoDTO(String nombre, float precio) {
        this.nombre = nombre;
        this.precioAdicional = precio;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }
}
