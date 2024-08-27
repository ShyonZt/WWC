import java.awt.*;
import javax.swing.*;

class changebackground extends JPanel {
    private Image backgroundImage;


    public changebackground(String imagePath) {
        backgroundImage = new ImageIcon("src\\imgs\\Upgradescreen2.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Imagem de fundo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}