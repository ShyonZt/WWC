import java.awt.*;
import javax.swing.*;

class ImagePanel extends JPanel {
    private Image image;
    private int x, y;

    public ImagePanel(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(x, y));
    }

     @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Imagem de fundo
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
}