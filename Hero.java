import javax.swing.*;

abstract class Hero {
    private String nome;
    private int hp;
    private int atk;
    private int def;
    private int numWhiskey;
    private ImageIcon portrait;

    private int basehp;
    private int baseatk;
    private int basedef;

    public Hero(String nome, int hp, int atk, int def, int numWhiskey, String caminho) {
        this.nome = nome;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.numWhiskey = numWhiskey;
        this.portrait = new ImageIcon(caminho);

        this.basehp = hp;
        this.baseatk = atk;
        this.basedef = def;
    }

    // getter setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getNumWhiskey() {
        return numWhiskey;
    }

    public void setNumWhiskey(int numWhiskey) {
        this.numWhiskey = numWhiskey;
    }

    public int getbaseHp() {
        return basehp;
    }

    public int getbaseAtk() {
        return baseatk;
    }

    public int getbaseDef() {
        return basedef;
    }

    public void setbaseHp(int basehp) {
        this.basehp = basehp;
    }

    public void setbaseAtk(int baseatk) {
        this.baseatk = baseatk;
    }

    public void setbaseDef(int basedef) {
        this.basedef = basedef;
    }

    // skill
    public abstract int useSkill();

    public ImageIcon getPortrait() {
        return portrait;
    }
}
