
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.*;

/**
 *
 * @author Saul Neri
 */
public class OpcionComplementoDTO {
    public ComplementoDTO complemento;
    public int cantidad = 0;
    public float montoTotal = 0.0f;
    
    public OpcionComplementoDTO(ComplementoDTO compl, int cantidad) {
        this.complemento = compl;
        this.cantidad = cantidad;
    }
    
    public float obtenerMontoTotal() {
        if (this.complemento == null) {
            return 0.0f;
        }
        
        return this.montoTotal = this.complemento.precio * this.cantidad;
    }
    
    @Override
    public String toString() {
        return String.format("%s\t(%d)\t=$%.2f", this.complemento.nombre, this.cantidad, this.obtenerMontoTotal());
    }
}
