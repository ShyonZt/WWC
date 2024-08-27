import java.awt.*;
import javax.swing.*;

class Hselect extends JPanel {
    private Image backgroundImage;


    public Hselect(String imagePath) {
        backgroundImage = new ImageIcon("src\\imgs\\HSelectinfo2.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Imagem de fundo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}