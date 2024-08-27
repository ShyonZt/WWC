import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ChScreen extends JFrame implements ActionListener {

    // botões
    private JButton hpp;
    private JButton hpm;

    private JButton atkp;
    private JButton atkm;

    private JButton defp;
    private JButton defm;

    private JButton Bconfirm;

    private JLabel heroname;
    private JLabel infohp;
    private JLabel infoatk;
    private JLabel infodef;
    private JLabel pointsLabel;

    private ImageIcon picture;

    private Hero character;

    private boolean escolhaModoJogo;

    public int points = 10;

    public ChScreen(Hero character, boolean escolhaModoJogo) {
        super("Customização de Personagem");
        this.character = character;
        this.escolhaModoJogo = escolhaModoJogo;

        // icone de janela
        ImageIcon icon = new ImageIcon("src\\imgs\\WWC2icon.png");
        setIconImage(icon.getImage());

        Container tela = getContentPane();
        setSize(800, 480);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        changebackground backimg = new changebackground("src\\imgs\\Upgradescreen2.png");
        backimg.setLayout(null);

        Dimension buttonSize = new Dimension(80, 30);

        // inicialização
        hpp = new JButton("HP+");
        hpp.setSize(buttonSize);

        hpm = new JButton("HP-");
        hpm.setSize(buttonSize);

        atkp = new JButton("ATK+");
        atkp.setSize(buttonSize);

        atkm = new JButton("ATK-");
        atkm.setSize(buttonSize);

        defp = new JButton("DEF+");
        defp.setSize(buttonSize);

        defm = new JButton("DEF-");
        defm.setSize(buttonSize);

        Bconfirm = new JButton("Confirm");
        Bconfirm.setSize(100, 30);

        // listeners
        hpp.addActionListener(this);
        hpm.addActionListener(this);
        atkp.addActionListener(this);
        atkm.addActionListener(this);
        defp.addActionListener(this);
        defm.addActionListener(this);
        Bconfirm.addActionListener(this);

        // labels
        String name = character.getClass().getSimpleName();
        heroname = new JLabel("" + name);
        heroname.setForeground(Color.BLACK);

        infohp = new JLabel("HP: " + character.getHp(), SwingConstants.CENTER);
        infohp.setForeground(Color.BLACK);

        infoatk = new JLabel("ATK: " + character.getAtk(), SwingConstants.CENTER);
        infoatk.setForeground(Color.BLACK);

        infodef = new JLabel("DEF: " + character.getDef(), SwingConstants.CENTER);
        infodef.setForeground(Color.BLACK);

        pointsLabel = new JLabel("Pontos: " + points, SwingConstants.CENTER);
        pointsLabel.setForeground(Color.BLACK);

        // imagem de personagem
        picture = scaleImageIcon(character);
        JLabel Lportrait = new JLabel(picture);
        // posição e tamanho da imagem
        Lportrait.setBounds(446, 90, 200, 200);

        // painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(60, 80, 250, 300);

        // Adding buttons and labels to the button panel
        buttonPanel.add(hpm);
        buttonPanel.add(infohp);
        buttonPanel.add(hpp);

        buttonPanel.add(atkm);
        buttonPanel.add(infoatk);
        buttonPanel.add(atkp);

        buttonPanel.add(defm);
        buttonPanel.add(infodef);
        buttonPanel.add(defp);

        // posição do confirm
        Bconfirm.setBounds(140, 380, 100, 30);
        // posição dos labels
        pointsLabel.setBounds(60, 300, 250, 30);
        heroname.setBounds(146, 55, 250, 30);

        // adicionando elementos na tela
        backimg.add(buttonPanel);
        backimg.add(Bconfirm);
        backimg.add(Lportrait);
        backimg.add(pointsLabel);
        backimg.add(heroname);

        // imagem de fundo
        tela.add(backimg, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == hpp && points > 0) {
            character.setHp(character.getHp() + 1);
            points--;
        } else if (action.getSource() == hpm) {
            if (character.getHp() > character.getbaseHp()) {
                character.setHp(character.getHp() - 1);
                points++;
            }
        } else if (action.getSource() == atkp && points > 0) {
            character.setAtk(character.getAtk() + 1);
            points--;
        } else if (action.getSource() == atkm) {
            if (character.getAtk() > character.getbaseAtk()) {
                character.setAtk(character.getAtk() - 1);
                points++;
            }
        } else if (action.getSource() == defp && points > 0) {
            character.setDef(character.getDef() + 1);
            points--;
        } else if (action.getSource() == defm) {
            if (character.getDef() > character.getbaseDef()) {
                character.setDef(character.getDef() - 1);
                points++;
            }
        } else if (action.getSource() == Bconfirm) {
            // Opens the battlefield screen
            new Field(character, escolhaModoJogo);
            dispose();
        }
        updater();
    }


    private void updater() {
        infohp.setText("HP: " + character.getHp());
        infoatk.setText("ATK: " + character.getAtk());
        infodef.setText("DEF: " + character.getDef());
        pointsLabel.setText("Pontos: " + points);
    }

    private ImageIcon scaleImageIcon(Hero chara) {
        ImageIcon imgbuf = new ImageIcon(chara.getPortrait().getImage());
        Image img = imgbuf.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
