/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.ProductoDTO;
import mx.edu.itson.cafeteriauniversitaria.dtonegocios.v1.TamanoDTO;
import mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.util.ImageResizer;



/**
 * Panel que muestra las distintas elecciones de tamanos para el producto a personalizar.
 * @author Saul Neri
 */
public class TamanosPanel extends javax.swing.JPanel {

    private ButtonGroup tamanoProductoGroup;
    private ProductoDTO productoSeleccionado;
    private ImageIcon tamanoProductoImagen;
    private TamanoDTO tamanoSeleccionado;

    /**
     * Creates new form TamanosPanel
     */
    public TamanosPanel(ProductoDTO productoSeleccionado) {

        initComponents();

        this.productoSeleccionado = productoSeleccionado;
        this.tamanoProductoImagen = new ImageIcon("imagenes/taza-icon.png");

        this.setupBotonesSeleccionTamano(productoSeleccionado.getTamanosProducto());
    }

    /**
     * Obtiene el objeto TamanoDTO a traves del grupo de radio buttons usado para
     * mostrar dichas opciones de tamanos.
     * @return Tamano seleccionado.
     */
    private TamanoDTO obtenerSeleccionTamano() {
        if (this.tamanoProductoGroup != null) {
            for (AbstractButton button : java.util.Collections.list(this.tamanoProductoGroup.getElements())) {
                if (button.isSelected()) {
                    String tamanoNombre = button.getText();
                    return this.productoSeleccionado.getTamanosProducto()
                            .stream()
                            .filter(t -> t.nombre.equals(tamanoNombre))
                            .findFirst()
                            .orElse(null);
                }
            }
        }

        return null;
    }

    /**
     * Carga los JRadioButton que se usan para mostrar los paneles de eleccion de
     * tamano.
     * @param tamanos Set de tamanos disponibles.
     */
    private void setupBotonesSeleccionTamano(Set<TamanoDTO> tamanos) {
        this.tamanoProductoGroup = new ButtonGroup();

        this.panelBotonesTamanos.removeAll();

        int ancho = 32, largo = 32;

        for (TamanoDTO tamano : tamanos) {
            BotonTamanoProducto btnTamano = new BotonTamanoProducto(tamano.nombre);

            try {
                ImageIcon imagenReescalada = ImageResizer.resize(this.tamanoProductoImagen, new Dimension(ancho, largo));
                btnTamano.setImagenTamano(imagenReescalada);
                btnTamano.setFont(new Font("SansSerif", Font.PLAIN, 18));
                btnTamano.setBackground(java.awt.Color.WHITE);
                btnTamano.setOpaque(false);
                btnTamano.revalidate();
                btnTamano.repaint();

                MouseAdapter clickAction = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        BotonTamanoProducto target = (BotonTamanoProducto) e.getComponent();
                        target.getJRadioButton().setSelected(true);
                        cargarSeleccionTamano();
                    }
                };

                btnTamano.getJRadioButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cargarSeleccionTamano();
                    }
                });

                btnTamano.addMouseListener(clickAction);

            } catch (Exception ex) {
                ex.printStackTrace();
                // no hacer nada para no detener la creacion de los botones...
            }

            this.tamanoProductoGroup.add(btnTamano.getJRadioButton());
            this.panelBotonesTamanos.add(btnTamano);

            ancho *= 1.25;
            largo *= 1.25;
        }

        this.panelBotonesTamanos.revalidate();
        this.panelBotonesTamanos.repaint();
    }

    /**
     * Carga la seleccion del boton a la clase de utilidad PedidoHandler para el 
     * detalle de pedido actual.
     */
    private void cargarSeleccionTamano() {
        tamanoSeleccionado = obtenerSeleccionTamano();
        //pedido.getDetalleActual().tamano = tamanoSeleccionado;
        habilitarBotonSiguiente();
        //this.parent.actualizarMontoTotalDetallePedido();
        
        System.out.println("Tamano Seleccionado: " + tamanoSeleccionado.nombre);
    }

    /**
     * Habilita el boton para seguir a la pantalla de personalizacion siguiente.
     */
    public void habilitarBotonSiguiente() {
        this.siguientePanelBtn.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelBotonesTamanos = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1 = new javax.swing.JPanel();
        siguientePanelBtn = new javax.swing.JButton();
        atrasBtn = new javax.swing.JButton();

        setName("panelSeleccionTamano"); // NOI18N
        setPreferredSize(new java.awt.Dimension(798, 592));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(96, 96, 96));
        jLabel1.setText("Tamaños");

        siguientePanelBtn.setText("Siguiente");
        siguientePanelBtn.setEnabled(false);
        siguientePanelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguientePanelBtnActionPerformed(evt);
            }
        });

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(atrasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(siguientePanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(atrasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(siguientePanelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBotonesTamanos, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotonesTamanos, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        //this.parent.mostrarProductosPanel();
    }//GEN-LAST:event_atrasBtnActionPerformed

    private void siguientePanelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguientePanelBtnActionPerformed
        //this.parent.mostrarVariantesPanel();
    }//GEN-LAST:event_siguientePanelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atrasBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBotonesTamanos;
    private javax.swing.JButton siguientePanelBtn;
    // End of variables declaration//GEN-END:variables
}
