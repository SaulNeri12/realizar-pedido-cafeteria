/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul Neri
 */
public class DetallePedidoDTO {

    public ProductoDTO producto;
    public VarianteProductoDTO variante;
    public TamanoDTO tamano;
    public List<OpcionComplementoDTO> complementos;
    private float montoTotal = 0.0f;

    public DetallePedidoDTO() {
        this.complementos = new ArrayList<>();
    }

    public float obtenerMontoTotal() {
        if (producto == null) {
            return 0.0f;
        }

        // Reiniciamos para evitar sumas acumuladas si se llama varias veces
        this.montoTotal = 0.0f;

        // --- DEBUG INICIO ---
        System.out.println("---------------------------------");
        System.out.println("DEBUG: Iniciando cálculo...");
        System.out.println(String.format("1. Precio Base: %.2f", this.producto.precioBase));
        this.montoTotal = this.producto.precioBase;

        if (this.tamano != null) {
            System.out.println(String.format("2. Sumando Tamaño (%s): %.2f", this.tamano.nombre, this.tamano.precioAdicional));
            this.montoTotal += this.tamano.precioAdicional;
        } else {
            System.out.println("2. Tamaño es NULO");
        }

        if (this.complementos != null) {
            System.out.println(String.format("3. Sumando %d complementos...", this.complementos.size()));
            for (OpcionComplementoDTO opcion : this.complementos) {
                float montoOpcion = opcion.obtenerMontoTotal();
                System.out.println(String.format("   - Sumando: %s ($%.2f)", opcion.toString(), montoOpcion));
                this.montoTotal += montoOpcion;
            }
        } else {
            System.out.println("3. Complementos es NULO");
        }

        System.out.println(String.format("TOTAL CALCULADO: %.2f", this.montoTotal));
        System.out.println("---------------------------------");
        // --- DEBUG FIN ---

        return this.montoTotal;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", this.producto.nombre, this.tamano, this.obtenerMontoTotal());
    }
}
