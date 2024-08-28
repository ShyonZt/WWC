import javax.swing.*;

class Sheriff extends Hero {

    ImageIcon portrait=new ImageIcon("src\\imgs\\sheriff.png");

    Sheriff(String nome, int hp, int atk, int def, int numWhiskey, String rota) {
        super(nome, hp, atk, def, numWhiskey, "src\\imgs\\sheriff.png");

        setbaseHp(21);
        setbaseAtk(7);
        setbaseDef(6);
    }

    // skill
    @Override
    public int useSkill() {
        JOptionPane.showMessageDialog(null,
                "                       Voce usou sua Habilidade Especial!\nA lei encarnada, poucos podem escapar de sua perseguicao sem fim");
        return 1;
    }

    public ImageIcon getPortrait() {
        return portrait;
    }
}