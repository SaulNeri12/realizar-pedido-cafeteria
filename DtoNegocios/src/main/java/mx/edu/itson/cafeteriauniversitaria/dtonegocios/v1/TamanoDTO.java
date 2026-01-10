
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

/**
 *
 * @author itson
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
