import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Mwindows extends JFrame implements ActionListener {

  private JButton Bstart;
  private JButton Bdebug;
  private JButton Bexit;

  public Mwindows() {
    super("Wild West Crusade");

    // Icone
    ImageIcon icon = new ImageIcon("src\\imgs\\WWC2icon.png");
    setIconImage(icon.getImage());

    Container tela = getContentPane();
    setSize(800, 480);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    // botões
    Bstart = new JButton("Novo Jogo");
    Bdebug = new JButton("Debug");
    Bexit = new JButton("Sair");

    // leitores para botões
    Bstart.addActionListener(this);
    Bdebug.addActionListener(this);
    Bexit.addActionListener(this);

    // Imagem de fundo
    backgroundimg backimg = new backgroundimg("src\\imgs\\Coverart2.png");

    // painel de botões
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setOpaque(false); // background dos botões transparente
    buttonPanel.add(Bstart);
    buttonPanel.add(Bdebug);
    buttonPanel.add(Bexit);

    // adicionando os elementos na tela
    backimg.setLayout(new BorderLayout());
    backimg.add(buttonPanel, BorderLayout.SOUTH);

    // adicionando a imagem
    tela.add(backimg, BorderLayout.CENTER);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent action) {
    if (action.getSource() == Bstart) {

      Hscreen Hscreen = new Hscreen(false);
      Hscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dispose();

    }

    if (action.getSource() == Bdebug) {

      Hscreen Hscreen = new Hscreen(true);
      Hscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dispose();

    }
    if (action.getSource() == Bexit) {

      JOptionPane.showMessageDialog(this, "Saindo do Jogo", "Info", JOptionPane.INFORMATION_MESSAGE, null);

      System.exit(0);
    }

  }
}