import javax.swing.ImageIcon;

public class Rogue extends Inimigo {

    ImageIcon portrait = new ImageIcon("src\\imgs\\rougue.png");

    Rogue(String nome, int hp, int atk, int def) {
        super(nome, hp, atk, def);
    }

    public ImageIcon getPortrait() {
        return portrait;
    }
}