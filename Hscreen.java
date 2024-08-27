import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Hscreen extends JFrame implements ActionListener {

    // botões
    private JButton Bgunslinger;
    private JButton Boutlaw;
    private JButton Bsheriff;

    private boolean escolhaModoJogo;

    private JLabel lblgunslinger,lbloutlaw,lblsheriff;

    // icones
    ImageIcon gunslingerIcon;
    ImageIcon outlawIcon;
    ImageIcon sheriffIcon;

    public Hscreen(boolean escolhaModoJogo) {
        super("Seleção de Herói");
        this.escolhaModoJogo = escolhaModoJogo;

        // icone da janale
        ImageIcon icon = new ImageIcon("src\\imgs\\WWC2icon.png");
        setIconImage(icon.getImage());

        Container tela = getContentPane();
        setSize(800, 480);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // tamanho do botão
        Dimension buttonSize = new Dimension(148, 202);

        // escalar as imagens
        gunslingerIcon = scaleImageIcon(new ImageIcon("src\\imgs\\gunslingerbaner.png"), buttonSize.width,
                buttonSize.height);
        outlawIcon = scaleImageIcon(new ImageIcon("src\\imgs\\outlawbaner.png"), buttonSize.width, buttonSize.height);
        sheriffIcon = scaleImageIcon(new ImageIcon("src\\imgs\\sheriffbaner.png"), buttonSize.width, buttonSize.height);

        // botões ja re-escalados
        Bgunslinger = new JButton(gunslingerIcon);
        Boutlaw = new JButton(outlawIcon);
        Bsheriff = new JButton(sheriffIcon);

        // travar tamanho do botão
        Bgunslinger.setPreferredSize(buttonSize);
        Bgunslinger.setMinimumSize(buttonSize);
        Bgunslinger.setMaximumSize(buttonSize);

        Boutlaw.setPreferredSize(buttonSize);
        Boutlaw.setMinimumSize(buttonSize);
        Boutlaw.setMaximumSize(buttonSize);

        Bsheriff.setPreferredSize(buttonSize);
        Bsheriff.setMinimumSize(buttonSize);
        Bsheriff.setMaximumSize(buttonSize);

        // action listeners
        Bgunslinger.addActionListener(this);
        Boutlaw.addActionListener(this);
        Bsheriff.addActionListener(this);

        // imagem de fundo
        Hselect backimg = new Hselect("src\\imgs\\HSelectinfo2.png");

        //info de personagens
        lblgunslinger= new JLabel("Gunslinger");
        lblgunslinger.setForeground(Color.BLACK);
        lblgunslinger.setFont(new Font("Arial", Font.BOLD, 15));
        lbloutlaw= new JLabel("Outlaw");
        lbloutlaw.setForeground(Color.BLACK);
        lbloutlaw.setFont(new Font("Arial", Font.BOLD, 15));
        lblsheriff= new JLabel("Sheriff");
        lblsheriff.setForeground(Color.BLACK);
        lblsheriff.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel infopanel= new JPanel (new FlowLayout(FlowLayout.CENTER, 10,10));
        infopanel.setOpaque(false);
        infopanel.add(lblgunslinger);
        infopanel.add(Box.createHorizontalStrut(70));
        infopanel.add(lbloutlaw);
        infopanel.add(Box.createHorizontalStrut(80));
        infopanel.add(lblsheriff);
        infopanel.setBounds(190,10,400,400);
        infopanel.setForeground(Color.BLACK);


        // painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false); // fazer botão transparente
        buttonPanel.setBorder(new EmptyBorder(40, 0, 0, 0)); // espaço para baixo
        buttonPanel.add(Bgunslinger);
        buttonPanel.add(Boutlaw);
        buttonPanel.add(Bsheriff);
        buttonPanel.setBounds(100, 20, 600, 400);

        // labels
        JLabel Lgunslinger = new JLabel(
                "<html>Gunslinger: Um agil mestre em revolvers <br> <strong>Skill:<strong> Trick Shot (Próximo ataque ira dar 50%+ dano)</html>");
        Lgunslinger.setForeground(Color.BLACK);
        Lgunslinger.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel Loutlaw = new JLabel(
                "<html>Outlaw: Um foragido mortifero, especialista em escapar da lei <br> <strong>Skill:<strong> Super Foco (Esquiva do próximo ataque)</html>");
        Loutlaw.setForeground(Color.BLACK);
        Loutlaw.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel Lsheriff = new JLabel(
                "<html>Sheriff: A lei em pessoa, não são muitos que conseguem escapar sua perseguição sem fim <br> <strong> Skill:<strong> Prova de Justiça (Recebe metade do dano no próximo ataque)</html>");
        Lsheriff.setForeground(Color.BLACK);
        Lsheriff.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);
        labelPanel.setBorder(new EmptyBorder(60, 180, 0, 0));// Espaço para baixo e direita
        labelPanel.add(Lgunslinger);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre as labels
        labelPanel.add(Loutlaw);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre as labels
        labelPanel.add(Lsheriff);
        labelPanel.setBounds(0,250,800,400);


        backimg.setLayout(null);
        backimg.add(buttonPanel, BorderLayout.NORTH);
        backimg.add(labelPanel, BorderLayout.CENTER);
        backimg.add(infopanel);

        // adicionando info
        tela.add(backimg, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == Bgunslinger) {

            Gunslinger gunslinger = new Gunslinger("Gunslinger", 12, 7, 3, 0, "src\\imgs\\gunslinger.png");
            ChScreen chScreen = new ChScreen(gunslinger, escolhaModoJogo);
            chScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();

        }
        if (action.getSource() == Boutlaw) {

            Outlaw outlaw = new Outlaw("Outlaw", 7, 12, 2, 0, "src\\imgs\\outlaw.PNG");
            ChScreen chScreen = new ChScreen(outlaw, escolhaModoJogo);
            chScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();

        }
        if (action.getSource() == Bsheriff) {

            Sheriff sheriff = new Sheriff("Sheriff", 16, 6, 8, 0,"src\\imgs\\sheriff.png");
            ChScreen chScreen = new ChScreen(sheriff, escolhaModoJogo);
            chScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();

        }
    }

    // resizer de imagem
    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

}