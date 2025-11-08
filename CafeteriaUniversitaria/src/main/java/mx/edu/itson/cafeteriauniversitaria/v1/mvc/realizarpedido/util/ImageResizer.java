/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.util;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Saul Neri
 */
public class ImageResizer {
    public static ImageIcon resize(ImageIcon image, Dimension dimension) throws Exception {
        if (image == null) {
            throw new Exception("La imagen dada es nulo.");
        }
        
        if (dimension == null) {
            throw new Exception("El objeto Dimension dado es nulo.");
        }
        
        Image imagenEscalada = image.getImage().getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
}
