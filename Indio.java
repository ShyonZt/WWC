import javax.swing.ImageIcon;

public class Indio extends Inimigo {

    ImageIcon portrait = new ImageIcon("src\\imgs\\indio.png");

    Indio(String nome, int hp, int atk, int def) {
        super(nome, hp, atk, def);
    }

    public ImageIcon getPortrait() {
        return portrait;
    }
}
