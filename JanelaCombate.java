import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


public class JanelaCombate extends JFrame {
    private Hero heroi;          
    private Inimigo inimigoAtual; 
    private JLabel lblHeroHp, lblHeroAtk, lblHeroDef; // Labels para exibir stats do heroi
    private JLabel lblInimigoHp, lblInimigoAtk, lblInimigoDef; // Labels para exibir stats do inimigo
    private boolean heroiFugiu = false;

    // Variaveis globais
    Random aleatorio = new Random();
    boolean habilidadeEspecialUsada = false;
    boolean habilidadeEspecialAtiva = false;
    int statusModificado;
    int atkTotal = 0;
    int defTotal = 0;
    int diferenca = 0;

    //=========================UI=========================
    private Image background_img;

    //=========================UI=========================


    public JanelaCombate(Hero heroi, Inimigo inimigoAtual) {
        this.heroi = heroi;
        this.inimigoAtual = inimigoAtual;

        // Caracteristicas da janela de combate
        setTitle("Combate com " +  inimigoAtual.getNome());   // Titulo 
        setSize(800, 480);                       // Dimensoes
        setLayout(null);
        ImageIcon icon = new ImageIcon("src\\imgs\\WWC2icon.png");
        setIconImage(icon.getImage());


        // Inicializa os labels dos stats do heroi e do inimigo
        lblHeroHp = new JLabel("HP do Heroi: " + heroi.getHp());
        lblHeroAtk = new JLabel("Atk do Heroi: " + heroi.getAtk());
        lblHeroDef = new JLabel("Def do Heroi: " + heroi.getDef());
        lblInimigoHp = new JLabel("HP do Inimigo: " + inimigoAtual.getHp());
        lblInimigoAtk = new JLabel("Atk do Inimigo: " + inimigoAtual.getAtk());
        lblInimigoDef = new JLabel("Def do Inimigo: " + inimigoAtual.getDef());

        // Botao Atacar + ActionListener
        JButton botaoAtacar = new JButton("Atacar");
        botaoAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combate(); 
            }
        });

        // Botao Beber Whisky + ActionListener
        JButton botaoWhisky  = new JButton("Beber");
        botaoWhisky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (heroi.getNumWhiskey() == 0 ){
                    JOptionPane.showMessageDialog(null, "Acabou o estoque camarada...");
                }else {
                    heroi.setHp(heroi.getHp() + 20); // Recupera 20 de HP, por exemplo
                    heroi.setNumWhiskey(heroi.getNumWhiskey() - 1);
                    atualizarStatusDafundo(); // Atualiza a interface com o novo valor de HP
                    JOptionPane.showMessageDialog(null, "Voce bebeu whisky e recuperou 20 de vida!");
                }    
            }
        });

        // Botao usarHabilidadeEspecial + ActionListener
        JButton botaoHabilidadeEspecial  = new JButton("Habilidade");
        botaoHabilidadeEspecial.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (habilidadeEspecialUsada == true){
                    JOptionPane.showMessageDialog(null, "Voce ja usou sua habilidade especial contra esse inimigo");
                } else {
                    statusModificado = heroi.useSkill();
                    habilidadeEspecialUsada = true;
                    habilidadeEspecialAtiva = true;
                }
            }
        });

        // Botao botaoFugir + ActionListener
        JButton botaoFugir  = new JButton("Fugir");
        botaoFugir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inimigoAtual instanceof Chefao){
                    JOptionPane.showMessageDialog(null, "Ninguem consegue escapar da batalha final!!!");  
                    return;  
                } else if (heroi.getHp() - 3 <= 0) {
                    JOptionPane.showMessageDialog(null, "Voce esta cansado demais para fugir");  
                    return;  
                }

                JOptionPane.showMessageDialog(null, "Voce saiu correndo e acabou tropecando, sofrendo 3 de dano no processo... seu covarde!");
                heroi.setHp(heroi.getHp() - 3);
                heroiFugiu = true;

                dispose(); // Fecha a janela de combate
            }
        });

//==================================================================
        
     background_img = new ImageIcon("src\\imgs\\duel_screen.png").getImage();
        ImagePanel fundo = new ImagePanel(background_img, 800, 480);
        fundo.setBounds(0, 0, 800, 480);
        fundo.setLayout(null); // Set layout to null for absolute positioning
        add(fundo);

    JPanel combatgrid= new JPanel(new GridLayout(2,2));
        combatgrid.setOpaque(false);
        combatgrid.add(botaoAtacar);
        combatgrid.add(botaoFugir);
        combatgrid.add(botaoWhisky);
        combatgrid.add(botaoHabilidadeEspecial);
        combatgrid.setBounds(300, 338, 200, 130);

    JLabel heroname= new JLabel(heroi.getNome());
        heroname.setOpaque(false);
        heroname.setBounds(100, 20, 100, 20);
        heroname.setForeground(Color.BLACK);

    JLabel enemyname= new JLabel(inimigoAtual.getNome());
        enemyname.setOpaque(false);
        enemyname.setBounds(635, 20, 100, 20);
        enemyname.setForeground(Color.BLACK);

    ImageIcon heroicon=scaleImageIcon(heroi.getPortrait(),200,200);
    ImageIcon enemyicon=scaleImageIcon(inimigoAtual.getPortrait(),200,200);

        JPanel pictures= new JPanel(new GridLayout(1,2));
        pictures.setOpaque(false);
        pictures.add(new JLabel(heroicon));
        pictures.add(Box.createHorizontalStrut(250));
        pictures.add(new JLabel(enemyicon));
        pictures.setBounds(0,70,800,200);

    JPanel hero_stats= new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
        hero_stats.setOpaque(false);
        hero_stats.add(lblHeroHp);
        hero_stats.add(lblHeroAtk);
        hero_stats.add(lblHeroDef);
        hero_stats.setBounds(80,340,130,200);
        hero_stats.setForeground(Color.BLACK);

    JPanel enemy_stats= new JPanel(new FlowLayout(FlowLayout.CENTER, 10,20));
        enemy_stats.setOpaque(false);
        enemy_stats.add(lblInimigoHp);
        enemy_stats.add(lblInimigoAtk);
        enemy_stats.add(lblInimigoDef);
        enemy_stats.setBounds(600,340,130,200);
        enemy_stats.setForeground(Color.BLACK);

//==================================================================

        fundo.add(combatgrid);
        fundo.add(heroname);
        fundo.add(enemyname);
        fundo.add(pictures);
        fundo.add(hero_stats);
        fundo.add(enemy_stats);   


        add(fundo);




        // Mais caracteristicas da janela de combate
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // So fecha essa janela e nao toda o jogo
        setLocationRelativeTo(null); // Começa no centro
        setFocusable(true);  // Tira a 'sombra' do botao (tambem deve fazer mais alguma coisa que nao sei)
        setVisible(true);            // Visivel
    }

    // Logica de combate
    private void combate() {

        // Calculos para o combate (heroi atacando) (com habilidade especial) 
        if (habilidadeEspecialAtiva) {
            if (heroi instanceof Gunslinger) {
                atkTotal = heroi.getAtk() + aleatorio.nextInt(10) + statusModificado;
                defTotal = inimigoAtual.getDef() + aleatorio.nextInt(10);
                diferenca = atkTotal - defTotal;

                // Tem 50% a mais de dano
                realizarCombateHeroiAtacando(atkTotal, defTotal, diferenca);
                habilidadeEspecialAtiva = false;
            }

            else if (heroi instanceof Outlaw) {
                // Pula o turno do inimigo
                diferenca = calcularDiferencaHeroi();
                realizarCombateHeroiAtacando(atkTotal, defTotal, diferenca);

                JOptionPane.showMessageDialog(this, "Voce desviou do ataque do inimigo");
                habilidadeEspecialAtiva = false;

                // Caso inimigo tenha morrido
                if (inimigoAtual.getHp() <= 0) {
                    // Caso a luta tenha sido contra o chefao mostra mensagem de end game (VOLTAR PARA MENU INICIAL)
                    if (inimigoAtual instanceof Chefao) {
                        JOptionPane.showMessageDialog(this, "Parabens!!!");
                        dispose(); // Fecha a janela de combate
                        return;
                    } else {
                        adicionarAtributoAleatorio();
                    }
                }

                atualizarStatusDafundo();
                return;
            }

            else if (heroi instanceof Sheriff) {
                // Ataque normal
                diferenca = calcularDiferencaHeroi();                
                realizarCombateHeroiAtacando(atkTotal, defTotal, diferenca);
            }

        } else {
            // Combate (heroi atacando) (sem habilidade especial)
            diferenca = calcularDiferencaHeroi();
            realizarCombateHeroiAtacando(atkTotal, defTotal, diferenca);
        }




        // Caso inimigo tenha morrido
        if (inimigoAtual.getHp() <= 0) {
            // Caso a luta tenha sido contra o chefao mostra mensagem de end game (VOLTAR PARA MENU INICIAL)
            if (inimigoAtual instanceof Chefao) {
                    if(heroi instanceof Gunslinger){
                        JOptionPane.showMessageDialog(this, "O inimigo final cai ao seus pes");
                        JOptionPane.showMessageDialog(this, "Parabens pela sua vitoria!!!");
                        
                        victory winner= new victory(this, "Vitória do Gunsliner","src\\imgs\\Gunslinger_win.png" );
                        winner.setVisible(true);
                        new Mwindows();
                        dispose(); // Fecha a janela de combate
                        return;
                    }
                else if(heroi instanceof Outlaw){
                    JOptionPane.showMessageDialog(this, "O inimigo final cai ao seus pes");
                    JOptionPane.showMessageDialog(this, "Parabens pela sua vitoria!!!");
                    victory out_winner= new victory(this, "Vitória do Outlaw","src\\imgs\\outlaw_win.png");
                    out_winner.setVisible(true);
                    new Mwindows();
                    dispose(); // Fecha a janela de combate
                    return;
                    
                }else if(heroi instanceof Sheriff){
                    JOptionPane.showMessageDialog(this, "O inimigo final cai ao seus pes");
                    JOptionPane.showMessageDialog(this, "Parabens pela sua vitoria!!!");
                        victory sher_win= new victory(this, "Vitória do Sheriff","src\\imgs\\sheriff_win.png");
                    sher_win.setVisible(true);
                    new Mwindows();
                    dispose(); // Fecha a janela de combate
                    return;
                }

            } else {
                adicionarAtributoAleatorio();
            }

        } else {
            if (habilidadeEspecialAtiva) {
                if (heroi instanceof Sheriff) {
                    // Recebe metade do dano do inimigo
                    diferenca = calcularDiferencaHeroi();
                    realizarCombateInimigoAtacando(atkTotal, defTotal, diferenca / 2);
                    habilidadeEspecialAtiva = false;
                }

            } else {
                // Combate (inimigo atacando)
                diferenca = calcularDiferencaInimigo();
                realizarCombateInimigoAtacando(atkTotal, defTotal, diferenca);
            }
        }




        // Caso o heroi tenha morrido (VOLTAR PARA MENU INICIAL)
        if (heroi.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "Seu HP chegou em 0, O ceifador conseguiu sua alma\n");
            new Mwindows();
            dispose(); // Fecha a janela de combate
        }

        // Refresh na fundo de status
        atualizarStatusDafundo();
    }

    private void realizarCombateHeroiAtacando(int atkTotal, int defTotal, int diferenca) {
        if (defTotal >= atkTotal) {
            JOptionPane.showMessageDialog(this, "O inimigo defendou todo o seu ataque!!!");
        }
        else {
            JOptionPane.showMessageDialog(this, "Voce infligiu " + diferenca + " de dano.");
            inimigoAtual.setHp((inimigoAtual.getHp() - diferenca));  // Atualiza a vida do inimigo
        }
    }

    private void realizarCombateInimigoAtacando(int atkTotal, int defTotal, int diferenca) {
        // Mesma logica de combate de antes mas agora com o heroi recebendo o ataque
        if (defTotal >= atkTotal) {
            JOptionPane.showMessageDialog(this, "Voce defendou completamente o ataque do inimigo!!!");
        }
        else {
            JOptionPane.showMessageDialog(this, "Voce sofreu " + diferenca + " de dano.");
            heroi.setHp((heroi.getHp() - diferenca));  // Atualiza a vida do inimigo
        }
    }

    private int calcularDiferencaHeroi() {
        atkTotal = heroi.getAtk() + aleatorio.nextInt(10);
        defTotal = inimigoAtual.getDef() + aleatorio.nextInt(10);
        return diferenca = atkTotal - defTotal;
    }

    private int calcularDiferencaInimigo() {
        atkTotal = inimigoAtual.getAtk() + aleatorio.nextInt(10);
        defTotal = heroi.getDef() + aleatorio.nextInt(10);
        return diferenca = atkTotal - defTotal;
}

    private void adicionarAtributoAleatorio() {
        int recompensaAleatoria = aleatorio.nextInt(3);           

        //  Concede ao heroi um atributo aleatorio
        switch (recompensaAleatoria) {
            case 0:
                heroi.setHp(heroi.getHp() + 15);
                JOptionPane.showMessageDialog(this, "Voce venceu a batalha e recebeu +5 pontos de Vida");
                break;
            case 1:
                heroi.setAtk(heroi.getAtk() + 1);
                JOptionPane.showMessageDialog(this, "Voce venceu a batalha e recebeu +1 ponto de Ataque");
                break;
            case 2:
                heroi.setDef(heroi.getDef() + 1);
                JOptionPane.showMessageDialog(this, "Voce venceu a batalha e recebeu +1 ponto de Defesa");
                break;
            default:
                System.out.println("Erro dentro do switch!");
                throw new AssertionError();
        }

        dispose(); // Fecha a janela de combate
    }

    public boolean heroiFugiu() {
        return heroiFugiu;
    }

    // Atualiza labels de HP na janela
    private void atualizarStatusDafundo() {
        lblHeroHp.setText("HP do Heroi: " + heroi.getHp());             
        lblInimigoHp.setText("HP do Inimigo: " + inimigoAtual.getHp()); 
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int x, int y) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

}