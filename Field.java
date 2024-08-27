import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

class Field extends JFrame implements ActionListener, KeyListener {

    private JPanel battlefield;

    private Hero heroi;

    private ImageIcon picture, Wpicture;;

    private Image battleground_img, wooden_img;

    private JLabel infohp;
    private JLabel infoatk;
    private JLabel infodef;
    private JLabel inventinfo;

    private JButton N_game;
    private JButton Reiniciar;
    private JButton botaoVisibilidade;

    private Font custom;
    private boolean visibilidade;

    //====================battleinfo========================

    JButton[] botoes = new JButton[50];
    Random aleatorio = new Random();
    JButton botaoDica = new JButton();
    int limiteUsosDica = 3;

    // Posicoes
    int posicaoHeroi = aleatorio.nextInt(9);  // Posicao do heroi aleatoria da linha inicial
    int posicaoAnteriorHeroi = posicaoHeroi;  // Posicao anterior e para caso heroi fuja do combate
    int posicaoChefao = aleatorio.nextInt(40, 49);  // Posicao do chefao aleatoria da linha final

    // Criacao de vetores para posicoes e inimigos
    ArrayList<Inimigo> vetorInimigos = new ArrayList<Inimigo>();
    ArrayList<Integer> posicoesInimigos = new ArrayList<Integer>();
    ArrayList<Integer> posicoesArmadilhasLeves = new ArrayList<Integer>();
    ArrayList<Integer> posicoesArmadilhasPesadas = new ArrayList<Integer>();
    ArrayList<Integer> posicoesWhiskey = new ArrayList<Integer>();    

    // Criacao de vetores para armazenar as posicoes iniciais (botao resetar)
    private int posicaoInicialHeroi = posicaoHeroi;
    private int posicaoInicialChefao = posicaoChefao;
    ArrayList<Inimigo> vetorInimigosIniciais = new ArrayList<Inimigo>();
    private ArrayList<Integer> posicoesInimigosIniciais = new ArrayList<>();
    private ArrayList<Integer> posicoesArmadilhasLevesIniciais = new ArrayList<>();
    private ArrayList<Integer> posicoesArmadilhasPesadasIniciais = new ArrayList<>();
    private ArrayList<Integer> posicoesWhiskeyIniciais = new ArrayList<>();

    // Imagens
    ImageIcon heroiIcon;
    ImageIcon indioIcon = new ImageIcon("src\\imgs\\indio.png");
    ImageIcon rogueIcon = new ImageIcon("src\\imgs\\rougue.png");
    ImageIcon billyIcon = new ImageIcon("src\\imgs\\billy.png");
    ImageIcon armadilhaPesadaIcon = new ImageIcon("src\\imgs\\beartrap.png");
    ImageIcon armadilhaLeveIcon = new ImageIcon("src\\imgs\\cacto.png");
    ImageIcon whiskeyIcon = new ImageIcon("src\\imgs\\whiskey.png");

    //=============================================================================================
    public Field(Hero heroi, boolean visibilidade) {
        super("Campo de Batalha");
        this.heroi = heroi;
        this.visibilidade = visibilidade;

        heroiIcon = heroi.getPortrait();

        // icone de janela
        ImageIcon icon = new ImageIcon("src\\imgs\\WWC2icon.png");
        setIconImage(icon.getImage());

        // resolução e icone
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // ============================Painel de fundo=============================

        // carregar imagem de personagem
        picture = scaleImageIconHero(heroi, 200, 200);
        JLabel Lportrait = new JLabel(picture);
        Lportrait.setBounds(1030, 30, 200, 200);

        // label de info de personagem
        infohp = new JLabel("HP: " + heroi.getHp(), SwingConstants.CENTER);
        infohp.setForeground(Color.BLACK);

        infoatk = new JLabel("ATK: " + heroi.getAtk(), SwingConstants.CENTER);
        infoatk.setForeground(Color.BLACK);

        infodef = new JLabel("DEF: " + heroi.getDef(), SwingConstants.CENTER);
        infodef.setForeground(Color.BLACK);

        JPanel infodisplay = new JPanel(new GridLayout(3, 1, 0, 2));
        infodisplay.setOpaque(false);
        infodisplay.setBounds(1030, 210, 200, 200);

        infodisplay.add(infohp);
        infodisplay.add(infoatk);
        infodisplay.add(infodef);

        // criando label de inventario
        JLabel Invent = new JLabel("Inventario");
        Invent.setBounds(1090, 450, 100, 20);
        Invent.setForeground(Color.BLACK);
        inventinfo = new JLabel("X: " + heroi.getNumWhiskey());
        inventinfo.setBounds(1150, 490, 100, 100);
        inventinfo.setForeground(Color.BLACK);

        // Label de itens do inventario
        Wpicture = new ImageIcon("src\\imgs\\whiskey.png");
        Wpicture = scaleImageIcon(Wpicture, 100, 100);
        JLabel Wportrait = new JLabel(Wpicture);
        Wportrait.setBounds(1030, 480, 100, 100);

        // carregando fundos
        battleground_img = new ImageIcon("src\\imgs\\Fieldsml.png").getImage();
        wooden_img = new ImageIcon("src\\imgs\\depth2.png").getImage();

        // painel de madeira
        JPanel Woodenpanel = new ImagePanel(wooden_img, 1280, 720);
        Woodenpanel.setBounds(0, 0, 1280, 720);
        Woodenpanel.setLayout(null);
        this.setLocationRelativeTo(null);

        // painel de combate
        battlefield = new ImagePanel(battleground_img, 1000, 650);
        battlefield.setBounds(0, 0, 1000, 650);
        battlefield.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        battlefield.setLayout(new GridLayout(5, 10));
        battlefield.addKeyListener(this);
        battlefield.setFocusable(true);       

        // Instancia 50 botoes adicionando ao frame e o ActionListener
        for (int i = 0; i < 50; i++) {
            botoes[i] = new JButton();
            botoes[i].addActionListener(this);
            battlefield.add(botoes[i]);

            // Deixa os botoes invisiveis (para colocar uma imagem de fundo)
            botoes[i].setOpaque(false);
            botoes[i].setContentAreaFilled(false);
           // botoes[i].setBorderPainted(false);
        }

        // Painel de botões inferiores
        N_game = new JButton("Novo Jogo");
        Reiniciar = new JButton("Reiniciar");
        botaoDica = new JButton("Dica");
        botaoVisibilidade = new JButton("Alternar Visibilidade");

        N_game.setOpaque(false);
        Reiniciar.setOpaque(false);
        botaoDica.setOpaque(false);
        botaoVisibilidade.setOpaque(false);

        botaoDica.addActionListener(this);
        N_game.addActionListener(this);
        Reiniciar.addActionListener(this);
        botaoVisibilidade.addActionListener(this);

        JPanel painel_fundo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painel_fundo.add(N_game);
        painel_fundo.add(Reiniciar);
        painel_fundo.add(botaoDica);
        painel_fundo.add(botaoVisibilidade);
        painel_fundo.setBounds(20, 660, 600, 30);
        painel_fundo.setOpaque(false);

        // colocando um painel no outro
        Woodenpanel.add(battlefield);
        Woodenpanel.add(Lportrait);
        Woodenpanel.add(infodisplay);
        Woodenpanel.add(Invent);
        Woodenpanel.add(inventinfo);
        Woodenpanel.add(Wportrait);
        Woodenpanel.add(painel_fundo);

        // adicionando os paineis na tela
        add(Woodenpanel);
        setVisible(true);

        // Escala as imagens
        int escalaX = 105;
        int escalaY = 105;
        armadilhaPesadaIcon = scaleImageIcon(armadilhaPesadaIcon, escalaX, escalaY);
        armadilhaLeveIcon = scaleImageIcon(armadilhaLeveIcon, escalaX, escalaY);
        whiskeyIcon = scaleImageIcon(whiskeyIcon, escalaX, escalaY);
        indioIcon = scaleImageIcon(indioIcon, escalaX, escalaY);
        rogueIcon = scaleImageIcon(rogueIcon, escalaX, escalaY);
        billyIcon = scaleImageIcon(billyIcon, escalaX, escalaY);
        heroiIcon = scaleImageIcon(heroiIcon, escalaX, escalaY);

        // Posiciona heroi e chefao
        botoes[posicaoHeroi].setIcon(heroiIcon); // Define a imagem do heroi no botao
        botoes[posicaoChefao].setIcon(billyIcon); // Define a imagem do chefao no botao

        // Cria e distribui inimigos e armadilhas pelo campo
        distribuirArmadilhasPesadas(3, posicoesArmadilhasPesadasIniciais);
        distribuirArmadilhasLeves(3, posicoesArmadilhasLevesIniciais);
        distribuirIndios(3, posicoesInimigosIniciais);
        distribuirRogues(3, posicoesInimigosIniciais);
        distribuirWhiskey(4, posicoesWhiskeyIniciais);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Dica
        if (e.getSource() == botaoDica) {
            if (limiteUsosDica == 0){
                JOptionPane.showMessageDialog(this, "Limite de dicas alcancado!");

            }else {                
                if (verificarArmadilhasAoRedor()) {
                    limiteUsosDica--;
                    JOptionPane.showMessageDialog(this, "Tem no minimo uma armadilha ao seu redor\n                    Tome cuidado");
                    battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao
                } else {
                    limiteUsosDica--;
                    JOptionPane.showMessageDialog(this, "Nenhuma armadilha encontrada ao seu redor!");
                    battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao
                }
            }
        }

        if (e.getSource() == Reiniciar) {
            reiniciarJogo();
            battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao
        }

        // Muda visibilidade
        if (e.getSource() == botaoVisibilidade) {
            if (!visibilidade) {
                mudarVisibilidade(true);
                visibilidade = true;
                battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao
            } else {
                mudarVisibilidade(false);
                visibilidade = false;
                battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao
            }    
        }

        if (e.getSource() == N_game) {
            JOptionPane.showMessageDialog(this, "Iniciando novo jogo");
            new Mwindows();
            dispose();
        }

        // Nao utilizado no momento (provavelmente deletar para o projeto final) (so usei para ajudar com as logicas do programa)
        for (int i = 0; i < 50; i++) {
            if (e.getSource() == botoes[i]) {
                System.out.println("Botao " + i + " pressionado");
                battlefield.requestFocusInWindow();
            }
        }
    }

    private boolean verificarArmadilhasAoRedor() {
        int[] direcoes = {-1, 1, -10, 10, -9, 9, -11, 11};  // Direcoes a verificar
        int[] posicoesSemParedeEsquerda = {1, -10, 10, -9, 11};  // Direcoes a verificar
        int[] posicoesSemParedeDireita = {-1, -10, 10, 9, -11};  // Direcoes a verificar
        int posicaoVerificarAtual;

        // Heroi esta na primeira coluna
        if (posicaoHeroi % 10 == 0) {
            for (int i = 0; i < posicoesSemParedeEsquerda.length; i++) {

                posicaoVerificarAtual = posicaoHeroi + posicoesSemParedeEsquerda[i];

                // Nao verificar alem do 'teto' ou do 'chao' do campo de batalha
                if (posicaoVerificarAtual >= 0 && posicaoVerificarAtual < 50) {
                    if (posicoesArmadilhasLeves.contains(posicaoVerificarAtual) || posicoesArmadilhasPesadas.contains(posicaoVerificarAtual)) {
                        return true;  
                    }
                }

            }
        }

        // Heroi esta na ultima coluna
        else if (posicaoHeroi % 10 == 9) {      
            for (int i = 0; i < posicoesSemParedeDireita.length; i++) {

                posicaoVerificarAtual = posicaoHeroi + posicoesSemParedeDireita[i];

                // Nao verificar alem do 'teto' ou do 'chao' do campo de batalha
                if (posicaoVerificarAtual >= 0 && posicaoVerificarAtual < 50) {
                    if (posicoesArmadilhasLeves.contains(posicaoVerificarAtual) || posicoesArmadilhasPesadas.contains(posicaoVerificarAtual)) {
                        return true;  
                    }
                }

            }
        }

        // Heroi esta em qualquer outra coluna
        else {
            for (int i = 0; i < direcoes.length; i++) {

                posicaoVerificarAtual = posicaoHeroi + direcoes[i];

                // Nao verificar alem do 'teto' ou do 'chao' do campo de batalha
                if (posicaoVerificarAtual >= 0 && posicaoVerificarAtual < 50) {
                    if (posicoesArmadilhasLeves.contains(posicaoVerificarAtual) || posicoesArmadilhasPesadas.contains(posicaoVerificarAtual)) {
                        return true;  
                    }
                }
            }
        }    
        return false; 
    }

    private boolean posicaoNaoEstaLivre(int posicao){
        // Garante que os inimigos nao fiquem na mesma posicao que o heroi ou entre si, nem com as armdilhas
        if (posicao == posicaoHeroi || posicao == posicaoChefao || posicoesInimigos.contains(posicao) || posicoesArmadilhasLeves.contains(posicao) || posicoesArmadilhasPesadas.contains(posicao) || posicoesWhiskey.contains(posicao)) {
            return true;
        }return false;
    }

    private void distribuirWhiskey(int numWhiskey, ArrayList<Integer> listaInicial) {
        for (int i = 0; i < numWhiskey; i++) {
            int posicao;
            do {
                posicao = aleatorio.nextInt(50); // Gera uma posicao aleatoria entre 0 e 49
            } while (posicaoNaoEstaLivre(posicao)); 

            posicoesWhiskey.add(posicao);  // Adiciona a posicao ao vetor de posicoes
            listaInicial.add(posicao);    // Salva a posicao para usar no reiniciar jogo

            // So atualiza o icone se modo new game
            if (visibilidade) {
                botoes[posicao].setIcon(whiskeyIcon);  // Define a imagem do whiskey no botao da posicao atual
            }   
        }
    }

    private void distribuirArmadilhasPesadas(int numArmadilhas, ArrayList<Integer> listaInicial) {
        for (int i = 0; i < numArmadilhas; i++) {
            int posicao;
            do {
                posicao = aleatorio.nextInt(50); // Gera uma posicao aleatoria entre 0 e 49
            } while (posicaoNaoEstaLivre(posicao)); 

            posicoesArmadilhasPesadas.add(posicao);  // Adiciona a posicao ao vetor de posicoes
            listaInicial.add(posicao);    // Salva a posicao para usar no reiniciar jogo

            // So atualiza o icone se modo new game
            if (visibilidade) {
                botoes[posicao].setIcon(armadilhaPesadaIcon);  // Define a imagem da armadilha no botao da posicao atual
            } 
        }
    }

    private void distribuirArmadilhasLeves(int numArmadilhas, ArrayList<Integer> listaInicial) {
        for (int i = 0; i < numArmadilhas; i++) {
            int posicao;
            do {
                posicao = aleatorio.nextInt(50); // Gera uma posicao aleatoria entre 0 e 49
            } while (posicaoNaoEstaLivre(posicao)); 

            posicoesArmadilhasLeves.add(posicao);  // Adiciona a posicao ao vetor de posicoes
            listaInicial.add(posicao);    // Salva a posicao para usar no reiniciar jogo

            // So atualiza o icone se modo new game
            if (visibilidade) {
                botoes[posicao].setIcon(armadilhaLeveIcon); // Define a imagem da armadilha no botao da posicao atual
            }

        }
    }

    private void distribuirIndios(int numInimigos, ArrayList<Integer> listaInicial) {
        for (int i = 0; i < numInimigos; i++) {
            int posicao;
            do {
                posicao = aleatorio.nextInt(50); // Gera uma posicao aleatoria entre 0 e 49
            } while (posicaoNaoEstaLivre(posicao)); 

            Inimigo inimigo = new Indio("Indio " + (i + 1), 20, 5, 7); // Instancia os Indios
            vetorInimigos.add(inimigo);     // Adiciona o inimigo ao vetor de inimigos
            posicoesInimigos.add(posicao);  // Adiciona a posicao ao vetor de posicoes
            listaInicial.add(posicao);    // Salva a posicao para usar no reiniciar jogo
            vetorInimigosIniciais.add(inimigo);   // Salva o inimigo para usar no reiniciar jogo

            // So atualiza o icone se modo new game
            if (visibilidade) {
                botoes[posicao].setIcon(indioIcon); // Define a imagem do inimigo no botao da posicao atual
            }

        }
    }

    private void distribuirRogues(int numInimigos, ArrayList<Integer> listaInicial) {
        for (int i = 0; i < numInimigos; i++) {
            int posicao;
            do {
                posicao = aleatorio.nextInt(50); // Gera uma posicao aleatoria entre 0 e 49
            } while (posicaoNaoEstaLivre(posicao)); 

            Inimigo inimigo = new Rogue("Rogue " + (i + 1), 15, 12, 5); // Instancia os rogues
            vetorInimigos.add(inimigo);     // Adiciona o inimigo ao vetor de inimigos
            posicoesInimigos.add(posicao);  // Adiciona a posicao ao vetor de posicoes
            listaInicial.add(posicao);    // Salva a posicao para usar no reiniciar jogo
            vetorInimigosIniciais.add(inimigo);   // Salva o inimigo para usar no reiniciar jogo 

            if (visibilidade) {
                botoes[posicao].setIcon(rogueIcon); // Define a imagem do inimigo no botão da posicao atual
            }

        }
    }

    @Override // Funcao para movimentar o heroi e o comeco das interacoes
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        botoes[posicaoHeroi].setIcon(null); // Remove a imagem do heroi do botao anterior

        // Os ifs garantem que o heroi nao vai sair para fora do campoBatalha
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (posicaoHeroi >= 10) {
                    posicaoAnteriorHeroi = posicaoHeroi;
                    posicaoHeroi -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (posicaoHeroi < 40) {
                    posicaoAnteriorHeroi = posicaoHeroi;
                    posicaoHeroi += 10;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (posicaoHeroi % 10 != 0) {
                    posicaoAnteriorHeroi = posicaoHeroi;
                    posicaoHeroi -= 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (posicaoHeroi % 10 != 9) {
                    posicaoAnteriorHeroi = posicaoHeroi;
                    posicaoHeroi += 1;
                } 
                break;
        }

        // Interagi de acordo com o que o heroi entrou em contato
        if (posicoesInimigos.contains(posicaoHeroi)) {           
            combateInimigoBasico();

        }else if (posicaoChefao == posicaoHeroi) {
            combateChefao();
        }
        else if (posicoesArmadilhasPesadas.contains(posicaoHeroi)) {
            danoArmadilhaPesada();
        } 
        else if (posicoesArmadilhasLeves.contains((posicaoHeroi))) {
            danoArmadilhaLeve();
        }
        else if (posicoesWhiskey.contains((posicaoHeroi))) {
            adicionarWhiskey();
        }
        else {
            botoes[posicaoHeroi].setIcon(heroiIcon); // Caso contrario so atualiza a posicao do heroi com a imagem
        }

        battlefield.requestFocusInWindow(); // Atualiza o foco para o teclado, assim pode usar setas novamente apos clicar em um botao

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nao utilizado
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nao utilizado
    }

    // Combate com inimigo basico
    private void combateInimigoBasico() {

        int indiceInimigoAtual = posicoesInimigos.indexOf(posicaoHeroi);  // Pega o indice do inimigo atual na lista
        Inimigo inimigoAtual = vetorInimigos.get(indiceInimigoAtual); // Pega o mesmo inimigo que o heroi encontrou

        JOptionPane.showMessageDialog(this,"        " + inimigoAtual.getNome() + " te emboscou!");
        JanelaCombate janelaCombate = new JanelaCombate(heroi, inimigoAtual); // Abre a nova janela de combate

        // So executa apos a janela de combate for fechada
        janelaCombate.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                // Caso heroi tenha morrido durante a batalha, fecha a janela
                if (heroi.getHp() <= 0) {
                    dispose();
                }
                // Atualizas as posicoes e icones se o heroi venceu a batalha
                else if (janelaCombate.heroiFugiu() == false) {
                    posicoesInimigos.remove(indiceInimigoAtual);
                    vetorInimigos.remove(indiceInimigoAtual);
                    botoes[posicaoHeroi].setIcon(heroiIcon);
                    updater();
                }
                // Caso ele tenha fugido, nao atualiza e o heroi volta para posicao anterior
                else if (janelaCombate.heroiFugiu() == true){
                    botoes[posicaoAnteriorHeroi].setIcon(heroiIcon);
                    posicaoHeroi = posicaoAnteriorHeroi;
                    updater();
                }
            }
        });
    }

    // Combate com chefao
    private void combateChefao() {
        JOptionPane.showMessageDialog(this, "<VOCE ENCONTROU O CHEFAO> \n<PREPARE-SE PARA BATALHA>");
        Inimigo chefao = new Chefao("Billy The Kid", 50, 20, 10); // Instancia o chefao
        JanelaCombate janelaCombate = new JanelaCombate(heroi, chefao); // Abre a nova janela de combate

        // So executa apos a janela de combate for fechada
        janelaCombate.addWindowListener(new WindowAdapter() {
            @Override // Inicia o jogo de novo
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });        
    }

    // -0 ate -5 de hp ao contato
    private void danoArmadilhaPesada() {
        int danoPorArmadilha = aleatorio.nextInt(5);

        if (danoPorArmadilha == 0) {
            JOptionPane.showMessageDialog(this, "Voce pisou em uma armadilha de urso, porem\ncom muito cuidado conseguiu desarma-la.\n\n                     Tome cuidado!"); 
        } else {
            heroi.setHp(heroi.getHp() - danoPorArmadilha);
            JOptionPane.showMessageDialog(this, "Voce pisou em uma armadilha de \nurso, perdendo " + danoPorArmadilha + " pontos de vida\n\n        Olha por onde anda!");
        }

        posicoesArmadilhasPesadas.remove(Integer.valueOf(posicaoHeroi)); // Remove a armdilha do vetor
        botoes[posicaoHeroi].setIcon(heroiIcon); // Atualiza o icone do heroi
        updater();
    }

    // -1 de hp ao contato    
    private void danoArmadilhaLeve() {
        heroi.setHp(heroi.getHp() - 1);
        JOptionPane.showMessageDialog(this, "Voce bebeu tanto Whisky que acabou batendo\nem um cacto, perdendo 1 ponto de vida.\n\n         Ta na hora de parar de beber!");

        posicoesArmadilhasLeves.remove(Integer.valueOf(posicaoHeroi)); // Remove a armdilha do vetor
        botoes[posicaoHeroi].setIcon(heroiIcon); // Atualiza o icone do heroi
        updater();
    }

    private void adicionarWhiskey() {
        JOptionPane.showMessageDialog(this, "Voce encontrou um Whiskey\n     Beba com moderacao");
        heroi.setNumWhiskey(heroi.getNumWhiskey() + 1);

        posicoesWhiskey.remove(Integer.valueOf(posicaoHeroi)); // Remove o Whiskey do vetor
        botoes[posicaoHeroi].setIcon(heroiIcon); // Atualiza o icone do heroi
        updater();
    }

    //img escalador generico
    private ImageIcon scaleImageIcon(ImageIcon icon, int x, int y) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    //img escalador heroi
    private ImageIcon scaleImageIconHero(Hero chara, int x, int y) {
        ImageIcon imgbuf = new ImageIcon(chara.getPortrait().getImage());
        Image img = imgbuf.getImage();
        Image scaledImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void updater() {
        infohp.setText("HP: " + heroi.getHp());
        infoatk.setText("ATK: " + heroi.getAtk());
        infodef.setText("DEF: " + heroi.getDef());
        inventinfo.setText("X: " + heroi.getNumWhiskey());
    }

    private void mudarVisibilidade(boolean visibilidade) {
        for (int i = 0; i < 50; i++) {
            if (visibilidade) {
                // Define os icones de acordo com o tipo de objeto presente na posicao
                if (posicoesArmadilhasPesadas.contains(i)) {
                    botoes[i].setIcon(armadilhaPesadaIcon);
                } 
                else if (posicoesArmadilhasLeves.contains(i)) {
                    botoes[i].setIcon(armadilhaLeveIcon);
                } 
                else if (posicoesWhiskey.contains(i)) {
                    botoes[i].setIcon(whiskeyIcon);
                } 
                else if (posicoesInimigos.contains(i)) {
                    int indiceInimigoAtual = posicoesInimigos.indexOf(i);  // Pega o indice do inimigo atual na lista
                    Inimigo inimigoAtual = vetorInimigos.get(indiceInimigoAtual); // Pega o mesmo inimigo
                    if (inimigoAtual instanceof Indio){   // Atualiza o icone de acordo com o inimigo correto
                        botoes[i].setIcon(indioIcon); 
                    } else {
                        botoes[i].setIcon(rogueIcon); 
                    }
                } 
                else if (i == posicaoChefao) {
                    botoes[i].setIcon(billyIcon);
                } 
                else if (i == posicaoHeroi) {
                    botoes[i].setIcon(heroiIcon);
                } 
                else {
                    botoes[i].setIcon(null); // Nenhum icone
                }

              // Ou nao deixa nada visivel alem do heroi e chefao
            } else {
                if (i == posicaoHeroi) {
                    botoes[i].setIcon(heroiIcon);
                }
                else if (i == posicaoChefao) {
                    botoes[i].setIcon(billyIcon);
                } else {
                    botoes[i].setIcon(null); // Esconde o icone
                }
            }
        }
    }

    private void reiniciarJogo() {

        // Limpa todos os icones dos botoes
        for (int i = 0; i < 50; i++) {
            botoes[i].setIcon(null);
        }

        // Limpa todas as listas de posicoes
        posicoesWhiskey.clear();
        posicoesArmadilhasLeves.clear();
        posicoesArmadilhasPesadas.clear();
        posicoesInimigos.clear();
        vetorInimigos.clear();

        // Passa os dados de volta para o vetor de sempre
        posicoesWhiskey.addAll(posicoesWhiskeyIniciais);
        posicoesArmadilhasLeves.addAll(posicoesArmadilhasLevesIniciais);
        posicoesArmadilhasPesadas.addAll(posicoesArmadilhasPesadasIniciais);
        posicoesInimigos.addAll(posicoesInimigosIniciais);
        vetorInimigos.addAll(vetorInimigosIniciais);

        // Restaura as posicoes iniciais do heroi e chefao
        posicaoHeroi = posicaoInicialHeroi;
        posicaoChefao = posicaoInicialChefao;
        botoes[posicaoHeroi].setIcon(heroiIcon);
        botoes[posicaoChefao].setIcon(billyIcon);


        // Reseta tudo 
        // Reseta whiskeys
        for (int i = 0; i < posicoesWhiskey.size(); i++) {
            botoes[posicoesWhiskey.get(i)].setIcon(whiskeyIcon);
        }

        // Reseta armadilhas leves
        for (int i = 0; i < posicoesArmadilhasLeves.size(); i++) {
            botoes[posicoesArmadilhasLeves.get(i)].setIcon(armadilhaLeveIcon);
        }

        // Reseta armadilhas pesadas 
        for (int i = 0; i < posicoesArmadilhasPesadas.size(); i++) {
            botoes[posicoesArmadilhasPesadas.get(i)].setIcon(armadilhaPesadaIcon);
        }

        // Reseta inimigos 
        for (int i = 0; i < posicoesInimigos.size(); i++) {

            int posicao = posicoesInimigos.get(i);

            if (i < 3) {
                botoes[posicao].setIcon(indioIcon); // Primeiro 3 sempre sao Indios

            } else if (i >= 3 && i <= 5) {
                botoes[posicao].setIcon(rogueIcon); // Restantes sempre sao Rogues
            }
        }

        // Mantem visivel ou nao dependendo do modo atual do jogo
        if (!visibilidade) {
            mudarVisibilidade(false);
        }


        // Reverte os status do heroi
        heroi.setHp(heroi.getbaseHp()); 
        heroi.setAtk(heroi.getbaseAtk());  
        heroi.setDef(heroi.getbaseDef()); 
        heroi.setNumWhiskey(0);
        updater();
    }    
}