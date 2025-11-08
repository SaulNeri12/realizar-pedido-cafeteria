/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mx.edu.itson.cafeteriauniversitaria.dtonegocios.*;

import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.observadores.RealizarPedidoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.observadores.vista.PersonalizarProductoObserver;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.util.PedidoHandler;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.dialogo.ConfirmacionDialogo;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.dialogo.PedidoCompletadoDialogo;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.ConfirmacionAdicionProductoPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.ProductosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.SeleccionComplementosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.TamanosPanel;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel.VariantesPanel;

/**
 *
 * @author Saul Neri
 */
public class FrameRealizarPedido extends JFrame implements PersonalizarProductoObserver, RealizarPedidoObserver {

    private List<ProductoDTO> productos;
    private DetallePedidoDTO detalleAEliminar;

    private List<DetallePedidoDTO> pedidos = new ArrayList<>();

    private TamanoDTO tamanoSeleccionado;
    private ImageIcon imagenProducto;

    private ProductosPanel productosPanel;
    private TamanosPanel tamanosPanel;
    private VariantesPanel variantesPanel;
    private SeleccionComplementosPanel complementosPanel;
    private ConfirmacionAdicionProductoPanel confirmacionAdicionPanel;

    private PedidoHandler pedido = PedidoHandler.getInstance();

    /**
     * Creates new form FramePersonalizarProducto
     */
    public FrameRealizarPedido(List<ProductoDTO> productos) {
        initComponents();

        //this.setTitle("Realizar Pedido");
        
        this.productos = productos;

        this.panelFlujo.setLayout(new java.awt.BorderLayout());

        this.listaProductosScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.listaProductosScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.productosPanel = new ProductosPanel(this, productos);

        //this.mostrarProductosPanel();
        //this.actualizarListaPedido();
    }
    
    

    /*
    public void mostrarProductosPanel() {

        this.pedido.reiniciarDetalleActual();

        this.panelFlujo.removeAll();
        this.panelFlujo.add(this.productosPanel);
        this.panelFlujo.revalidate();
        this.panelFlujo.repaint();

        this.actualizarMontoTotalDetallePedido();
    }

    public void mostrarTamanosProductoPanel(ProductoDTO producto) {
        pedido.getDetalleActual().producto = producto;

        this.tamanosPanel = new TamanosPanel(this, producto);
        this.panelFlujo.removeAll();
        this.panelFlujo.add(this.tamanosPanel);
        this.panelFlujo.revalidate();
        this.panelFlujo.repaint();

        this.actualizarMontoTotalDetallePedido();
    }

    public void mostrarVariantesPanel() {
        this.variantesPanel = new VariantesPanel(this, pedido.getDetalleActual().producto.getVariantes());

        this.panelFlujo.removeAll();
        this.panelFlujo.add(this.variantesPanel);
        this.panelFlujo.revalidate();
        this.panelFlujo.repaint();

        this.actualizarMontoTotalDetallePedido();
    }

    public void mostrarComplementosPanel() {
        this.complementosPanel = new SeleccionComplementosPanel(this);

        this.panelFlujo.removeAll();
        this.panelFlujo.add(this.complementosPanel);
        this.panelFlujo.revalidate();
        this.panelFlujo.repaint();

        this.actualizarMontoTotalDetallePedido();
    }

    public void mostrarConfirmacionAdicionProductoPanel() {
        this.confirmacionAdicionPanel = new ConfirmacionAdicionProductoPanel(this);

        //this.pedido.getDetalleActual().complementos.clear();

        this.panelFlujo.removeAll();
        this.panelFlujo.add(this.confirmacionAdicionPanel);
        this.panelFlujo.revalidate();
        this.panelFlujo.repaint();

        this.actualizarMontoTotalDetallePedido();
    }

    public void actualizarListaPedido() {
        DefaultListModel<DetallePedidoDTO> modeloLista = new DefaultListModel<>();

        // No necesitas esta línea:
        // this.listaProductosScrollPanel.removeAll();
        this.completarPedidoBtn.setEnabled(this.pedido.getDetallesPedido().size() > 0);

        modeloLista.addAll(pedido.getDetallesPedido());

        JList<DetallePedidoDTO> jList = new JList<>(modeloLista);

        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DetallePedidoDTO objetoSeleccionado = jList.getSelectedValue();

                if (objetoSeleccionado != null) {
                    detalleAEliminar = objetoSeleccionado;
                    eliminarDetalleBtn.setEnabled(true);
                } else {
                    eliminarDetalleBtn.setEnabled(false);
                }
            }
        });

        this.listaProductosScrollPanel.setViewportView(jList);

        Float montoTotal = 0.0f;

        for (DetallePedidoDTO detalle : this.pedido.getDetallesPedido()) {
            montoTotal += detalle.obtenerMontoTotal();
        }

        this.completarPedidoBtn.setText(String.format("Completar Pedido ($%.2f)", montoTotal));
    }

    public void actualizarMontoTotalDetallePedido() {
        if (pedido.getDetalleActual() != null) {
            DetallePedidoDTO detalle = this.pedido.getDetalleActual();

            // 2. Obtienes el MONTO CORRECTO (esto te dará 62, como viste en la consola)
            float monto = detalle.obtenerMontoTotal();

            // 3. Asignas el nuevo texto a tu label
            // (Asegúrate de que 'labelMontoTotal' es el nombre correcto de tu componente)
            this.montoTotalDetalleLabel.setText(String.format("$%.2f", monto));

            // 4. ¡ESTA ES LA PARTE CLAVE!
            // Fuerza al label a redibujarse con el nuevo texto.
            this.montoTotalDetalleLabel.revalidate();
            this.montoTotalDetalleLabel.repaint();
        }

    }

    public void reiniciarSeleccionProducto() {
        this.pedido.getDetalleActual().producto = null;
        this.eliminarDetalleBtn.setEnabled(false);
    }
    */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        listaProductosScrollPanel = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        montoTotalDetalleLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelFlujo = new javax.swing.JPanel();
        eliminarDetalleBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        completarPedidoBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listaProductosScrollPanel)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listaProductosScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        montoTotalDetalleLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        montoTotalDetalleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        montoTotalDetalleLabel.setText("$0.0");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Total a Pagar:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(montoTotalDetalleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(montoTotalDetalleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelFlujoLayout = new javax.swing.GroupLayout(panelFlujo);
        panelFlujo.setLayout(panelFlujoLayout);
        panelFlujoLayout.setHorizontalGroup(
            panelFlujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFlujoLayout.setVerticalGroup(
            panelFlujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        eliminarDetalleBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        eliminarDetalleBtn.setText("Eliminar Producto");
        eliminarDetalleBtn.setEnabled(false);
        eliminarDetalleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDetalleBtnActionPerformed(evt);
            }
        });

        completarPedidoBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        completarPedidoBtn.setText("Completar Pedido");
        completarPedidoBtn.setEnabled(false);
        completarPedidoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completarPedidoBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Lista de Pedido");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(completarPedidoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(completarPedidoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eliminarDetalleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFlujo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarDetalleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelFlujo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarDetalleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDetalleBtnActionPerformed
        this.pedido.getDetallesPedido().remove(this.detalleAEliminar);
        this.detalleAEliminar = null;
        this.eliminarDetalleBtn.setEnabled(false);
        //this.actualizarListaPedido();
    }//GEN-LAST:event_eliminarDetalleBtnActionPerformed

    private void completarPedidoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completarPedidoBtnActionPerformed
        ConfirmacionDialogo dialogo = new ConfirmacionDialogo(this, true, "¿Deseas completar tu pedido?");

        boolean respuesta = dialogo.mostrarDialogo();

        if (respuesta == true) {
            System.out.println("Usuario confirmó la acción.");
            
            PedidoDTO pedidoRealizado = this.pedido.obtenerPedido();
            
            PedidoCompletadoDialogo dialogoCodigoQR = new PedidoCompletadoDialogo(this, pedidoRealizado.codigoPedido);
            dialogoCodigoQR.setVisible(true);
            
            this.pedido.reiniciarDetalleActual();
            this.pedido.getDetallesPedido().clear();
            //this.actualizarListaPedido();
            //this.mostrarProductosPanel();
        }
    }//GEN-LAST:event_completarPedidoBtnActionPerformed

    @Override
    public void seleccionarProducto(ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void seleccionarTamano(TamanoDTO tamano) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void seleccionarVariante(VarianteProductoDTO variante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void confirmarSeleccionComplementos(List<OpcionComplementoDTO> complementos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void confirmarRealizacionPedido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton completarPedidoBtn;
    private javax.swing.JButton eliminarDetalleBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane listaProductosScrollPanel;
    private javax.swing.JLabel montoTotalDetalleLabel;
    private javax.swing.JPanel panelFlujo;
    // End of variables declaration//GEN-END:variables

}
