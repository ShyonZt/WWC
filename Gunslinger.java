import javax.swing.*;

class Gunslinger extends Hero {

    ImageIcon portrait = new ImageIcon("src\\imgs\\gunslinger.png");

    public Gunslinger(String nome, int hp, int atk, int def, int numWhiskey, String rota){
        super(nome, hp,atk,def,numWhiskey,"src\\imgs\\gunslinger.png");

        setbaseHp(17);
        setbaseAtk(7);
        setbaseDef(3);
    }

    // Aumenta em 50% o proximo ataque
    @Override
    public int useSkill() {
        JOptionPane.showMessageDialog(null, "       Voce usou sua Habilidade Especial!\n            Um agil mestre dos revolveres\n\nSeu proximo ataque causa 50% a mais de dano");
        return (this.getAtk() + this.getAtk()*(1/2));
    }
    
    public ImageIcon getPortrait() {
        return portrait;
    }

}