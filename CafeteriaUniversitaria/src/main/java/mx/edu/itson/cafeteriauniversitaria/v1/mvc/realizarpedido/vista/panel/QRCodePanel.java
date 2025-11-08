/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.cafeteriauniversitaria.v1.mvc.realizarpedido.vista.panel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de utilidad tipo JPanel para generar y mostrar un codigo QR con un dato 
 * tipo String dado.
 * @author Saul Neri
 */
public class QRCodePanel extends JPanel {
    public QRCodePanel(String text, int size) throws WriterException {
        BufferedImage qrImage = generateQRCodeImage(text, size, size);
        JLabel label = new JLabel(new ImageIcon(qrImage));
        add(label);
        this.setPreferredSize(new Dimension(size, size));
    }

    /**
     * Genera el codigo QR como una image.
     * @param text Texto a encriptar.
     * @param width Ancho del codigo QR.
     * @param height Largo del codigo QR.
     * @return BufferedImage imagen del codigo QR.
     * @throws WriterException Si ocurre un problema en la generacion del codigo.
     */
    private static BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
            new String(text.getBytes(), java.nio.charset.StandardCharsets.UTF_8),
            BarcodeFormat.QR_CODE, width, height, hints);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}