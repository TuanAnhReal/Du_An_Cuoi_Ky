/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienich;

import java.awt.Image;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author PC
 */
public class ResizeIcons {

    public static ImageIcon scaleIcon(String resourcePath, int width, int height) {
        java.net.URL imgURL = ResizeIcons.class.getResource(resourcePath);
        if (imgURL == null) {
            System.err.println("Không tìm thấy icon: " + resourcePath);
            return null;
        }
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void setIconByFontSize(JLabel label, String resourcePath) {
        int fontSize = label.getFont().getSize();
        label.setIcon(scaleIcon(resourcePath, fontSize, fontSize));
    }

    // Scale lại icon đã gắn sẵn trong UI
    public static void resizeCurrentIcon(JLabel label) {
        Icon current = label.getIcon();
        if (current == null) {
            return;
        }

        int fontSize = label.getFont().getSize();

        if (current instanceof ImageIcon) {
            ImageIcon imgIcon = (ImageIcon) current;
            Image scaled = imgIcon.getImage().getScaledInstance(fontSize, fontSize, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
        }
    }

    // Set icon cho JMenuItem theo font size
    public static void setIconByFontSize(JMenuItem item, String resourcePath) {
        int fontSize = item.getFont().getSize();
        item.setIcon(scaleIcon(resourcePath, fontSize, fontSize));
    }

    public static void resizeCurrentIcon(AbstractButton button) {
        Icon current = button.getIcon();
        if (current == null) {
            return;
        }
        int fontSize = button.getFont().getSize();
        if (current instanceof ImageIcon) {
            ImageIcon imgIcon = (ImageIcon) current;
            Image scaled = imgIcon.getImage().getScaledInstance(fontSize, fontSize, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaled));
        }
    }
}
