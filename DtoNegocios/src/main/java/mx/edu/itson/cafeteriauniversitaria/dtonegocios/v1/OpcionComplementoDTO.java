package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

/**
 * Representa la elección de un complemento elegido para un detalle
 * de pedido.
 * @author itson
 */
public class OpcionComplementoDTO {

    public ComplementoDTO complemento;
    public int cantidad = 0;
    public float montoTotal = 0.0f;

    /**
     * Constructor por defecto.
     * @param compl Objeto del complemento elegido.
     * @param cantidad Cantidad de complementos elegido.
     */
    public OpcionComplementoDTO(ComplementoDTO compl, int cantidad) {
        this.complemento = compl;
        this.cantidad = cantidad;
    }

    /**
     * Calcula el subtotal del complemento basado en la cantidad.
     * @return El costo acumulado de este extra.
     */
    public float obtenerMontoTotal() {
        if (this.complemento == null) {
            return 0.0f;
        }

        return this.montoTotal = this.complemento.precio * this.cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s x%d - Subtotal: $%.2f",
                this.complemento.nombre, this.cantidad, this.obtenerMontoTotal());
    }
}
