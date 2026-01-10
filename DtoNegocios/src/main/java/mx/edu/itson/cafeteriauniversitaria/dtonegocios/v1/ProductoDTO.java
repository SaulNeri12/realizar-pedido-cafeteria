package mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representa un producto disponible para su compra en el sistema.
 * @author itson
 */
public class ProductoDTO {

    private Long id;

    public String nombre;
    public float precioBase;
    private Set<VarianteProductoDTO> variantes;
    public Set<ComplementoDTO> complementosAceptados;
    private Set<TamanoDTO> tamanos;
    private String imageUri;

    public ProductoDTO() {
        
    }
    
    public ProductoDTO(String nombre, float precioBase, Set<TamanoDTO> tamanos, Set<VarianteProductoDTO> variantes, Set<ComplementoDTO> complementos, String imageUri) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.variantes = variantes;
        this.complementosAceptados = complementos;
        this.tamanos = tamanos;
        this.imageUri = imageUri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTamanosProducto(Set<TamanoDTO> tamanos) {
        this.tamanos = tamanos;
    }

    public Set<TamanoDTO> getTamanosProducto() {
        return this.tamanos;
    }

    public List<VarianteProductoDTO> getVariantes() {
        return this.variantes.stream().toList();
    }

    public List<ComplementoDTO> getComplementosAceptados() {
        return this.complementosAceptados.stream().toList();
    }

    public String getImagenUri() {
        return this.imageUri;
    }

    public List<Map<String, Float>> preciosPorTamano() {
        return this.tamanos.stream()
                .map(t -> {
                    LinkedHashMap<String, Float> map = new LinkedHashMap<>();
                    map.put(t.nombre, precioBase + t.precioAdicional);
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Producto: %s | Precio Base: $%.2f | Tamaños: %d | Variantes: %d",
                nombre, precioBase, (tamanos != null ? tamanos.size() : 0), (variantes != null ? variantes.size() : 0));
    }
}
