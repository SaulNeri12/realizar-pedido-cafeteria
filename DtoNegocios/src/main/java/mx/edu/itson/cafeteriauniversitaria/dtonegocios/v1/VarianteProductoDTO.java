/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

/**
 *
 * @author itson
 */
public class VarianteProductoDTO {

    public String nombre;

    public VarianteProductoDTO(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
