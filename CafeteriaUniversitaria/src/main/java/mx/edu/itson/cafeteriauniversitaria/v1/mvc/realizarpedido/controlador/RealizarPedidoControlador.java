/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.controlador;

import java.util.List;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.*;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.modelo.PedidoModel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.FrameRealizarPedido;

/**
 *
 * @author nerix
 */
public class RealizarPedidoControlador {
    
    private PedidoModel modelo;
    private FrameRealizarPedido vista;
    
    private DetallePedidoDTO detalleAEliminar; // El Controlador puede guardar estado temporal de UI

    public RealizarPedidoControlador(PedidoModel modelo, FrameRealizarPedido vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        this.detalleAEliminar = null;
    }

    // --- MANEJO DEL FLUJO DE PANELES ---

    // El ProductosPanel llama a esto
    public void onProductoSeleccionado(ProductoDTO producto) {
        modelo.setProductoActual(producto);

        if (!producto.getTamanosProducto().isEmpty()) {
            vista.cargarDatosTamanos(producto.getTamanosProducto());
        }

        vista.mostrarPanel("tamanos");
    }

    // El TamanosPanel llama a esto
    public void onTamanoSeleccionado(TamanoDTO tamano) {
        modelo.setTamanoActual(tamano);
        
        // 3. Decide el flujo
        List<VarianteProductoDTO> variantes = modelo.getDetalleActual().producto.getVariantes();
        
        if (variantes.isEmpty()) {
            // No hay variantes, saltar a complementos
            List<ComplementoDTO> complementos = modelo.getDetalleActual().producto.getComplementosAceptados();
            vista.cargarDatosComplementos(complementos);
            vista.mostrarPanel("complementos");
        } else {
            // Hay variantes, mostrarlas
            vista.cargarDatosVariantes(variantes);
            vista.mostrarPanel("variantes");
        }
    }
    
    public void onNavegarAtras(String panelDestino) {
        // El controlador sabe que "atrás" de "tamanos" es "productos"
        if (panelDestino.equals("productos")) {
            // Opcional: limpiar la selección actual del modelo
            modelo.reiniciarDetalleActual(); 
            
            // Simplemente muestra el panel anterior
            vista.mostrarPanel("productos");
        }
        // ... (más lógica si vienes de otros paneles)
    }
    
    // El VariantesPanel llama a esto
    public void onVarianteSeleccionada(VarianteProductoDTO variante) {
        modelo.getDetalleActual().variante = variante;
        vista.mostrarPanel("complementos");
    }
    
    public void onCancelarAdicion() {
        modelo.reiniciarDetalleActual();
        vista.mostrarPanel("productos");
    }

    public void onDetalleSeleccionado(DetallePedidoDTO detalle) {
        this.detalleAEliminar = detalle;
    }

    // ... (cuando el usuario presiona "Siguiente" en Complementos)
    public void onComplementosSeleccionados(List<OpcionComplementoDTO> complementos) {
        // 1. Actualiza el modelo
        modelo.setComplementosActuales(complementos); // (Creas este método en tu PedidoModel)

        // 2. Carga el siguiente panel (Confirmación)
        // El panel de confirmación también necesita un método cargarDatos
        vista.cargarDatosConfirmacion(modelo.getDetalleActual());
        vista.mostrarPanel("confirmacion");
    }

    // ... (cuando el usuario presiona "Atrás" en Complementos)
    public void onNavegarAtrasDesdeComplementos() {
        // 1. Limpia los datos del modelo
        modelo.limpiarComplementosActuales(); // (Creas este método en PedidoModel)

        // 2. Decide a qué panel ir
        List<VarianteProductoDTO> variantes = modelo.getDetalleActual().producto.getVariantes();
        if (variantes.isEmpty()) {
            vista.mostrarPanel("tamanos");
        } else {
            vista.mostrarPanel("variantes");
        }
    }

    public void onConfirmarAdicionProducto() {
        // 1. Guarda el detalle actual en la lista de detalles del pedido.
        modelo.agregarDetalleActualAlPedido();

        // 2. Reinicia el detalle actual del modelo (para el siguiente producto).
        modelo.reiniciarDetalleActual();

        // 3. Vuelve al panel de productos inicial.
        vista.mostrarPanel("productos");
    }

    /**
     * Maneja la navegación de vuelta desde el panel de Confirmación.
     * Vuelve a la selección de Complementos y limpia el estado de las opciones.
     */
    public void onNavegarAtrasDesdeConfirmacion() {
        // 1. Limpiar las opciones del detalle actual
        DetallePedidoDTO detalle = modelo.getDetalleActual();
        if (detalle != null) {
            // Se asume que solo queremos volver al paso anterior (Complementos)
            // y mantener el producto, tamaño y variante seleccionados, pero el
            // panel de Complementos necesitará recargar su vista.

            // Si el flujo exige que se borren los complementos, se haría así:
            // detalle.complementos.clear();

            // Por simplicidad en la navegación, solo volvemos al panel.
        }

        // 2. Navegar al panel de Complementos
        vista.mostrarPanel("complementos");
    }

    // --- MANEJO DE EVENTOS DEL FRAME PRINCIPAL ---


    // El botón de eliminar llama a esto
    public void onEliminarDetalle() {
        if (this.detalleAEliminar != null) {
            modelo.eliminarDetalle(this.detalleAEliminar);
            this.detalleAEliminar = null;
        }
    }

    // El botón de completar pedido llama a esto
    public void onCompletarPedido() {
        // ... (lógica de mostrar diálogo de confirmación)
        boolean respuesta = true; // Simulación
        
        if (respuesta) {
            PedidoDTO pedidoRealizado = new PedidoDTO(modelo.getDetallesPedido());
            // ... (mostrar diálogo de QR)
            
            // Limpiar el modelo para un nuevo pedido
            modelo.getDetallesPedido().clear();
            modelo.reiniciarDetalleActual();
            // El modelo notificará a la vista y esta se limpiará sola
        }
    }
    
    public float getMontoTotalPedido() {
        return modelo.getMontoTotalPedido();
    }
}