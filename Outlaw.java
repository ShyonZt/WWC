import javax.swing.*;

class Outlaw extends Hero {

    ImageIcon portrait = new ImageIcon("src\\imgs\\outlaw.PNG");

    public Outlaw(String nome, int hp, int atk, int def, int numWhiskey, String rota){
        super(nome, hp, atk, def, numWhiskey, "src\\imgs\\outlaw.PNG");

        setbaseHp(12);
        setbaseAtk(12);
        setbaseDef(2);
      }

    // skill
    @Override
    public int useSkill() {
        JOptionPane.showMessageDialog(null, "       Voce usou sua Habilidade Especial!\nUm fora da lei mortal, mestre em driblar a lei");
        return 1;
    }

    public ImageIcon getPortrait() {
        return portrait;
    }

}