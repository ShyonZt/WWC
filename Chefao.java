import javax.swing.ImageIcon;

public class Chefao extends Inimigo {

    ImageIcon portrait = new ImageIcon("src\\imgs\\billy.png");

    Chefao(String nome, int hp, int atk, int def) {
        super(nome, hp, atk, def);
    }

    public ImageIcon getPortrait() {
        return portrait;
    }
}