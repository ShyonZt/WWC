
import javax.swing.*;

class victory extends JDialog{

  ImageIcon img;
  JButton back, sair;

  public victory(JFrame parent, String title, String path){
    super(parent, title, true);

    img= new ImageIcon(path);
    
    setSize(800, 480);
    setLocationRelativeTo(parent);;
    setLayout(null);

    JLabel ImageLabel= new JLabel(img);
    ImageLabel.setBounds(0, 0, 800, 480);
    add(ImageLabel);

    
  }
}