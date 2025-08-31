/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienich;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class CreateHyperLink {
        public static void createHyperlink(JLabel label, String text) {
        // Gạch dưới bằng HTML
        label.setText("<html><u>" + text + "</u></html>");

        // Đổi con trỏ thành hình bàn tay khi hover
        label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Xử lý hover + click
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setForeground(Color.BLUE); // đổi màu khi hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setForeground(Color.BLACK); // về màu gốc
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Chúc may mắn!");
            }
        });
    }
}
