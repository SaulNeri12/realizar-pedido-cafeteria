
package mx.edu.itson.cafeteriauniversitaria.dtonegocios;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author Saul Neri
 */
public class ProductoDTO {
    public String nombre;
    public float precioBase;
    private Set<String> variantes;
    public Set<ComplementoDTO> complementosAceptados;
    private Set<TamanoDTO> tamanos;
    private String imageUri;
    
    public ProductoDTO(String nombre, float precioBase, Set<TamanoDTO> tamanos, Set<String> variantes, Set<ComplementoDTO> complementos, String imageUri) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.variantes = variantes;
        this.complementosAceptados = complementos;
        this.tamanos = tamanos;
        this.imageUri = imageUri;
    }
    
    public void setTamanosProducto(Set<TamanoDTO> tamanos) {
        this.tamanos = tamanos;
    }
    
    public Set<TamanoDTO> getTamanosProducto() {
        return this.tamanos;
    }
    
    public List<String> getVariantes() {
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
}
