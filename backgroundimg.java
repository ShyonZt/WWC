import java.awt.*;
import javax.swing.*;

class backgroundimg extends JPanel {
    private Image backgroundImage;


    public backgroundimg(String imagePath) {
        backgroundImage = new ImageIcon("src\\imgs\\Coverart2.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}