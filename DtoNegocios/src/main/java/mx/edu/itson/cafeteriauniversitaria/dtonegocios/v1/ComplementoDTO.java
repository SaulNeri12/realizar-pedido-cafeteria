
package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

/**
 * Representa un complemento disponible para una bebida o alimento.
 * @author itson
 */
public class ComplementoDTO {

    private Long id;
    public String nombre;
    public float precio;

    /**
     * Constructor por defecto.
     */
    public ComplementoDTO() {

    }
    
    /**
     * Devuelve el ID del complemento en el sistema.
     * @return ID del complemento.
     */
    public Long getId() {
        return this.id;
    }

    public ComplementoDTO(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%s (+$%.2f)", nombre, precio);
    }
}
