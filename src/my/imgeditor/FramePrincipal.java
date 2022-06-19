package my.imgeditor;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class FramePrincipal extends javax.swing.JFrame {
    
    public FramePrincipal() {        
        initComponents();
        setLocationRelativeTo(null);
        setIconeFramePrincipal();
        Toolkit.getDefaultToolkit().setDynamicLayout(false);                    //Isto garante que o evento frameResized() somente será executado após o usuário soltar o clique de mouse
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seletorDeArquivo = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        seletorDeArquivo.setDialogTitle("Image Editor");
        seletorDeArquivo.setFileFilter(new meuFiltro());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image Editor");
        setAlwaysOnTop(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                frameResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setAutoscrolls(true);
        jPanel1.setMaximumSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pré-visualizaçäo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 255, 255))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setMaximumSize(new java.awt.Dimension(0, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Arquivo");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirArquivo(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salvar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarArquivo(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transformadas");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem3.setText("RGB");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirCanaisRGB(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Luz e Sombras");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirLuzESombras(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("HSL");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirHSL(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("YIQ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirYIQ(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Morfológicas");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirMorfologico(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Filtros");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem8.setText("Exibir Filtros");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirFiltros(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Histogramas");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem9.setText("Original");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaOrigem(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Preview");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaDestino(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Segmentação");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem11.setText("S-DGM");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDGM(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AbrirArquivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArquivo
        int opcao = seletorDeArquivo.showOpenDialog(jPanel1);
        if (opcao == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletorDeArquivo.getSelectedFile();
            caminho = arquivo.getPath();
            try {
                framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);                                //Desabilita framePrincipal para habilitar o progressFrame
                progressFrame.setVisible(true);                                                                                       //Exibe o progressFrame
                hsliniciado = false;
                yiqiniciado = false;
                imagemCarregadaDoDisco = ImageIO.read(new File(caminho));
                imagemASerSalvaEmDisco = new BufferedImage(imagemCarregadaDoDisco.getWidth(), imagemCarregadaDoDisco.getHeight(), BufferedImage.TYPE_INT_BGR);
                imagemOriginalRedimensionadaSemAlteracao = new BufferedImage(imagemCarregadaDoDisco.getWidth(), imagemCarregadaDoDisco.getHeight(), BufferedImage.TYPE_INT_BGR);
                for (int j = 0; j < imagemCarregadaDoDisco.getWidth(); j++) {
                    for (int i = 0; i < imagemCarregadaDoDisco.getHeight(); i++) {
                        Color c = new Color(imagemCarregadaDoDisco.getRGB(j, i));
                        int r = c.getRed();
                        int g = c.getGreen();
                        int b = c.getBlue();
                        c = new Color(r, g, b);
                        imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                        imagemOriginalRedimensionadaSemAlteracao.setRGB(j, i, c.getRGB());
                    }
                }
                arrayOperacoesDefinit.clear();
                arrayOperacoesTemp.clear();
                arrayOperacoesCopia.clear();
                arrayOperacoesSatVermelho.clear();
                arrayOperacoesSatVerde.clear();
                arrayOperacoesSatAzul.clear();
                arrayOperacoesSatLaranja.clear();
                arrayOperacoesSatAmarelo.clear();
                arrayOperacoesSatCiano.clear();
                arrayOperacoesSatMagenta.clear();
                jLabel1.setIcon(new ImageIcon(RedimensionarImagem()));
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AbrirArquivo

    private static BufferedImage RedimensionarImagem(){       
        Dimension dimPanel = framePrincipal.getSize();                                               //Método nativo para capturar as dimensões atuais do frame da aplicação
        double alturaTela = dimPanel.getHeight() * 0.85;                                             //a constante 0.85 representa a porcentagem de pixels necessários para preservar a altura do layout da GUI
        double larguraTela = dimPanel.getWidth() * 0.90;                                             //a constante 0.90 representa a porcentagem de pixels necessários para preservar a largura do layout da GUI
        
        if(alturaTela > larguraTela){
            alturaTela = alturaTela * 0.85;
            larguraTela = larguraTela * 0.8;
        }
        else{
            if(larguraTela > alturaTela){
                alturaTela = alturaTela * 0.9;
                larguraTela = larguraTela * 0.97;
            }
        }
        
        int alturaImg = imagemCarregadaDoDisco.getHeight();
        int larguraImg = imagemCarregadaDoDisco.getWidth();
        double novaAlturaImg = alturaImg;
        double novaLarguraImg = larguraImg;
        double difAltura;
        double difLargura;

        if(alturaTela > larguraTela){                                                           //Telas verticalmente retangulares
            difAltura = 0;
            difLargura = 0;
            
            //Este laço while() é executado enquanto uma das dimensões calculadas para a nova imagem for maior que o limite da tela do dispositivo.                
            while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){                    
                //A maior diferença calculada entre as dimensões da imagem e as dimensões equivalentes da tela do dispositivo indicam a dimensão que servirá como base para o redimensionamento da imagem                    
                if(novaAlturaImg >= alturaTela){
                    difAltura = novaAlturaImg - alturaTela;
                    novaAlturaImg = alturaTela;
                }
                if(alturaTela > novaAlturaImg){
                    difAltura = alturaTela - novaAlturaImg;
                }
                if(novaLarguraImg >= larguraTela){
                    difLargura = novaLarguraImg - larguraTela;
                    novaLarguraImg = larguraTela;
                }
                if(larguraTela > novaLarguraImg){
                    difLargura = larguraTela - novaLarguraImg;
                }
                if(difLargura > difAltura){                                                     //caso a diferença entre as larguras seja maior que a diferença entre as alturas
                    double percentRedim = (larguraTela * 100)/larguraImg;
                    novaAlturaImg = (alturaImg * percentRedim)/100;
                }
                if(difAltura > difLargura){                                                     //caso a diferença entre as alturas seja maior que a diferença entre as larguras
                    double percentRedim = (alturaTela * 100)/alturaImg;
                    novaLarguraImg = (larguraImg * percentRedim)/100;
                }
            }
            
            //Copia imagemCarregadaDoDisco para imagemASerSalvaEmDisco, pois as operações serão aplicadas na imagem original não redimensionada
            for (int i = 0; i < FramePrincipal.imagemCarregadaDoDisco.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemCarregadaDoDisco.getWidth(); j++) {
                    Color c = new Color(FramePrincipal.imagemCarregadaDoDisco.getRGB(j, i));
                    FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                }
            }
            
            arrayOperacoesDefinit.clear();
            System.out.println("\n----------------- arrayOperacoesDefinit vazio: copiando arrayOperacoesCopia para arrayOperacoesDefinit -----------------");
            for(int i = 0; i < arrayOperacoesCopia.size(); i++){
                Operacao elto = arrayOperacoesCopia.get(i);
                arrayOperacoesDefinit.add(elto);
            }
            System.out.println("----------------- Aplicando aplicarOperacoes() -----------------");
            aplicarOperacoes();
            System.out.println("\n");
            
            image = imagemASerSalvaEmDisco.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);
            imageRedimensionadoSemAlteracoes = imagemOriginalRedimensionadaSemAlteracao.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);
            
            ConstruirImagem(); 
        }
        else{
            if(larguraTela > alturaTela){                                                       //Telas horizontalmente retangulares
                difAltura = 0;
                difLargura = 0;
                
                while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){
                    if(novaAlturaImg >= alturaTela){
                        difAltura = novaAlturaImg - alturaTela;
                        novaAlturaImg = alturaTela;
                    }else{
                        if(alturaTela > novaAlturaImg){
                            difAltura = alturaTela - novaAlturaImg;
                            novaAlturaImg = alturaTela;
                        }
                    }
                    if(novaLarguraImg >= larguraTela){
                        difLargura = novaLarguraImg - larguraTela;
                        novaLarguraImg = larguraTela;
                    }
                    else{
                        if(larguraTela > novaLarguraImg){
                            difLargura = larguraTela - novaLarguraImg;
                            novaLarguraImg = larguraTela;
                        }
                    }
                    if(difLargura > difAltura){
                        double percentRedim = (larguraTela * 100)/larguraImg;
                        novaAlturaImg = (alturaImg * percentRedim)/100;
                    }
                    if(difAltura > difLargura){
                        double percentRedim = (alturaTela * 100)/alturaImg;
                        novaLarguraImg = (larguraImg * percentRedim)/100;
                    }
                }
                
                for (int i = 0; i < FramePrincipal.imagemCarregadaDoDisco.getHeight(); i++) {
                    for (int j = 0; j < FramePrincipal.imagemCarregadaDoDisco.getWidth(); j++) {
                        Color c = new Color(FramePrincipal.imagemCarregadaDoDisco.getRGB(j, i));
                        imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                    }
                }
                
                arrayOperacoesDefinit.clear();
                System.out.println("\n----------------- arrayOperacoesDefinit vazio: copiando arrayOperacoesCopia para arrayOperacoesDefinit -----------------");
                for(int i = 0; i < arrayOperacoesCopia.size(); i++){
                    Operacao elto = arrayOperacoesCopia.get(i);
                    arrayOperacoesDefinit.add(elto);
                }
                System.out.println("----------------- Aplicando aplicarOperacoes() -----------------");
                aplicarOperacoes();
                System.out.println("\n");
                
                image = imagemASerSalvaEmDisco.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);
                imageRedimensionadoSemAlteracoes = imagemOriginalRedimensionadaSemAlteracao.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);
                
                ConstruirImagem();
            }
            else{                                                                               //Telas quadradas
                difAltura = 0;
                difLargura = 0;
                
                while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){                    
                    if(novaAlturaImg >= alturaTela){
                        difAltura = novaAlturaImg - alturaTela;
                        novaAlturaImg = alturaTela;
                    }
                    else{
                        if(alturaTela > novaAlturaImg){
                            difAltura = alturaTela - novaAlturaImg;
                            novaAlturaImg = alturaTela;
                        }
                    }
                    if(novaLarguraImg >= larguraTela){
                        difLargura = novaLarguraImg - larguraTela;
                        novaLarguraImg = larguraTela;
                    }
                    else{
                        if(larguraTela > novaLarguraImg){
                            difLargura = larguraTela - novaLarguraImg;
                            novaLarguraImg = larguraTela;
                        }
                    }
                    if(difLargura > difAltura){
                        double percentRedim = (larguraTela * 100)/larguraImg;
                        novaAlturaImg = (alturaImg * percentRedim)/100;
                    }
                    if(difAltura > difLargura){
                        double percentRedim = (alturaTela * 100)/alturaImg;
                        novaLarguraImg = (larguraImg * percentRedim)/100;
                    }
                }
                
                for (int i = 0; i < FramePrincipal.imagemCarregadaDoDisco.getHeight(); i++) {
                    for (int j = 0; j < FramePrincipal.imagemCarregadaDoDisco.getWidth(); j++) {
                        Color c = new Color(FramePrincipal.imagemCarregadaDoDisco.getRGB(j, i));
                        imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                        imagemOriginalRedimensionadaSemAlteracao.setRGB(j, i, c.getRGB());
                    }
                }
                
                arrayOperacoesDefinit.clear();
                System.out.println("\n----------------- arrayOperacoesDefinit vazio: copiando arrayOperacoesCopia para arrayOperacoesDefinit -----------------");
                for(int i = 0; i < arrayOperacoesCopia.size(); i++){
                    Operacao elto = arrayOperacoesCopia.get(i);
                    arrayOperacoesDefinit.add(elto);
                }
                System.out.println("----------------- Aplicando aplicarOperacoes() -----------------");
                aplicarOperacoes();
                System.out.println("\n");

                image = imagemASerSalvaEmDisco.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);
                imageRedimensionadoSemAlteracoes = imagemOriginalRedimensionadaSemAlteracao.getScaledInstance((int)novaLarguraImg, (int)novaAlturaImg, 100);

                ConstruirImagem(); 
            }
        }
        return imagemASerExibida; 
    } 
    
    private static void ConstruirImagem(){   
        imagemASerExibida = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imagemCopia = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imagemOriginalRedimensionadaParaReversao = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        
        Graphics2D bGr1 = imagemASerExibida.createGraphics();
        Graphics2D bGr2 = imagemCopia.createGraphics();
        Graphics2D bGr3 = imagemOriginalRedimensionadaParaReversao.createGraphics();
        
        bGr1.drawImage(image, 0, 0, null);
        bGr2.drawImage(image, 0, 0, null);
        bGr3.drawImage(image, 0, 0, null);
        bGr1.dispose();
        bGr2.dispose();
        bGr3.dispose();
        
        //VERSÃO ORIGINAL
        HSL.estruturaTemporaria = new HSL.EstruturaHSL[imagemASerExibida.getHeight()][imagemASerExibida.getWidth()];
        HSL.estruturaTemporariaCopia = new HSL.EstruturaHSL[imagemASerExibida.getHeight()][imagemASerExibida.getWidth()];
        YIQ.estruturaTemporaria = new YIQ.EstruturaYIQ[imagemASerExibida.getHeight()][imagemASerExibida.getWidth()];
        YIQ.estruturaTemporariaCopia = new YIQ.EstruturaYIQ[imagemASerExibida.getHeight()][imagemASerExibida.getWidth()];
        
        for (int j = 0; j < imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < imagemASerExibida.getHeight(); i++) {
                HSL.estruturaTemporaria[i][j] = new HSL.EstruturaHSL();
                HSL.estruturaTemporariaCopia[i][j] = new HSL.EstruturaHSL();
                YIQ.estruturaTemporaria[i][j] = new YIQ.EstruturaYIQ();
                YIQ.estruturaTemporariaCopia[i][j] = new YIQ.EstruturaYIQ();
            }
        }
        
        HSL.RGBparaHSL();
        YIQ.RGBparaYIQ();
    }
    
    private void HistogramaOrigem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaOrigem
        if(imagemCarregadaDoDisco == null || FrameControleCheck.isShowing() || FrameControleRGB.isShowing() ||
           FrameControleFiltros.isShowing() || FrameControleLuzESombra.isShowing() || FrameControleHSL.isShowing() ||
           FrameControleYIQ.isShowing() || FrameControleMorfologico.isShowing() || progressFrame.isShowing()){
        }
        else{
            int vetred[] = new int[256];
            int vetgreen[] = new int[256];
            int vetblue[] = new int[256];

            for (int i = 0; i < 256; i++) {
                vetred[i] = 0;
                vetgreen[i] = 0;
                vetblue[i] = 0;
            }
            for (int i = 0; i < imagemCarregadaDoDisco.getHeight(); i++) {
                for (int j = 0; j < imagemCarregadaDoDisco.getWidth(); j++) {
                    Color c = new Color(imagemCarregadaDoDisco.getRGB(j, i));
                    if(c.getRed() != 0 && c.getGreen() != 0 && c.getBlue() != 0){
                        int red = c.getRed();
                        int green = c.getGreen();
                        int blue = c.getBlue();
                        vetred[red]++;
                        vetgreen[green]++;
                        vetblue[blue]++;
                    }
                }
            }
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            for (int i = 0; i < 256; i++) {
                ds.addValue(vetred[i], " Vermelho ", String.valueOf(i));
                ds.addValue(vetblue[i], " Azul ", String.valueOf(i));
                ds.addValue(vetgreen[i], " Verde ", String.valueOf(i));
            }
            grafico = ChartFactory.createBarChart("Histograma Original", " Cores [0-255] ", " Incidencia ", ds, PlotOrientation.VERTICAL, true, true, false); 
            FrameControleHistogramas histograma = new FrameControleHistogramas();
            histograma.setVisible(true);
        }
    }//GEN-LAST:event_HistogramaOrigem
    
    private void HistogramaDestino(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaDestino
        if(imagemCarregadaDoDisco == null || FrameControleCheck.isShowing() || FrameControleRGB.isShowing() ||
           FrameControleFiltros.isShowing() || FrameControleLuzESombra.isShowing() || FrameControleHSL.isShowing() ||
           FrameControleYIQ.isShowing() || FrameControleMorfologico.isShowing() || progressFrame.isShowing()){
        }
        else{
            int vetred[] = new int[256];
            int vetgreen[] = new int[256];
            int vetblue[] = new int[256];

            for (int i = 0; i < 256; i++) {
                vetred[i] = 0;
                vetgreen[i] = 0;
                vetblue[i] = 0;
            }
            for (int j = 0; j < imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < imagemASerExibida.getHeight(); i++) {
                    Color c = new Color(imagemASerExibida.getRGB(j, i));
                    if(c.getRed() != 0 && c.getGreen() != 0 && c.getBlue() != 0){
                            int red = c.getRed();
                            int green = c.getGreen();
                            int blue = c.getBlue();
                            vetred[red]++;
                            vetgreen[green]++;
                            vetblue[blue]++;
                    }
                }
            }
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            for (int i = 0; i < 256; i++) {
                ds.addValue(vetred[i], " Vermelho ", String.valueOf(i));
                ds.addValue(vetblue[i], " Azul ", String.valueOf(i));
                ds.addValue(vetgreen[i], " Verde ", String.valueOf(i));
            }
            grafico = ChartFactory.createBarChart("Histograma Pre-visualização", " Cores [0-255] ", " Incidencia ", ds, PlotOrientation.VERTICAL, true, true, false);
            FrameControleHistogramas histograma = new FrameControleHistogramas();
            histograma.setVisible(true);            
        }
    }//GEN-LAST:event_HistogramaDestino
   
    public static JPanel getPanelOriginal() {
        return new ChartPanel(grafico);
    }
    
    private void SalvarArquivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarArquivo
        if(imagemCarregadaDoDisco != null){
            int opcao = seletorDeArquivo.showSaveDialog(this);
            if (opcao == JFileChooser.APPROVE_OPTION) {
                File arquivo = seletorDeArquivo.getSelectedFile();
                caminho = arquivo.getPath();
                try {
                    framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);                                  //Desabilita framePrincipal para habilitar o progressFrame
                    saveFrame.setVisible(true);                                                                                         //Exibe o progressFrame
                    System.out.println("\n------------------- ARRAYS ANTES DE SALVAR -------------------");
                    for(int i = 0; i < FramePrincipal.arrayOperacoesDefinit.size(); i++){
                       System.out.println("Array Definit[" + i + "]: " + FramePrincipal.arrayOperacoesDefinit.get(i).codOp + ", " + FramePrincipal.arrayOperacoesDefinit.get(i).valor);
                    }
                    for(int j = 0; j < FramePrincipal.arrayOperacoesCopia.size(); j++){
                       System.out.println("Array Cópia[" + j + "]: " + FramePrincipal.arrayOperacoesCopia.get(j).codOp + ", " + FramePrincipal.arrayOperacoesCopia.get(j).valor);
                    }
                    System.out.println("\n");
                    
                    System.out.println("\n------------------- Início da aplicação das operações -------------------");
                    for (int i = 0; i < FramePrincipal.imagemCarregadaDoDisco.getHeight(); i++) {
                        for (int j = 0; j < FramePrincipal.imagemCarregadaDoDisco.getWidth(); j++) {
                            Color c = new Color(FramePrincipal.imagemCarregadaDoDisco.getRGB(j, i));
                            imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                        }
                    } 
                    aplicarOperacoes();
                    System.out.println("\n");
                    
                    ImageIO.write(imagemASerSalvaEmDisco, "PNG", new File(caminho));
                    FramePrincipal.arrayOperacoesDefinit.clear();
                    
                    System.out.println("\n------------------- ARRAYS DEPOIS DE SALVAR -------------------");
                    for(int i = 0; i < FramePrincipal.arrayOperacoesDefinit.size(); i++){
                       System.out.println("Array Definit[" + i + "]: " + FramePrincipal.arrayOperacoesDefinit.get(i).codOp + ", " + FramePrincipal.arrayOperacoesDefinit.get(i).valor);
                    }
                    for(int j = 0; j < FramePrincipal.arrayOperacoesCopia.size(); j++){
                       System.out.println("Array Cópia[" + j + "]: " + FramePrincipal.arrayOperacoesCopia.get(j).codOp + ", " + FramePrincipal.arrayOperacoesCopia.get(j).valor);
                    }
                    System.out.println("\n");
                }
                catch (IOException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_SalvarArquivo
   
    private void SDGM(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDGM
        if(imagemCarregadaDoDisco != null){
            Segmentacao.setLabel(jLabel1);
            imagemASerExibida = Segmentacao.SDGM(imagemCarregadaDoDisco);
            jLabel1.setIcon(new ImageIcon(imagemASerExibida));
        }
    }//GEN-LAST:event_SDGM

    private void ExibirCanaisRGB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirCanaisRGB
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            RGB.setLabel(jLabel1);
            FrameControleRGB = new FrameControleRGB();
            FrameControleRGB.setVisible(true);
        }             
    }//GEN-LAST:event_ExibirCanaisRGB

    private void ExibirFiltros(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirFiltros
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            Filtros.setLabel(jLabel1);
            FrameControleFiltros = new FrameControleFiltros();
            FrameControleFiltros.setVisible(true);
        }
    }//GEN-LAST:event_ExibirFiltros

    private void ExibirLuzESombras(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirLuzESombras
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            LuzESombra.setLabel(jLabel1);
            FrameControleLuzESombra = new FrameControleLuzESombra();
            FrameControleLuzESombra.setVisible(true);
        }
    }//GEN-LAST:event_ExibirLuzESombras

    private void ExibirHSL(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirHSL
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            if(!hsliniciado){
                loadHSLFrame.setVisible(true);
                HSL.estruturaTemporariaSave = new HSL.EstruturaHSL[imagemASerSalvaEmDisco.getHeight()][imagemASerSalvaEmDisco.getWidth()];
                HSL.estruturaTemporariaCopiaSave = new HSL.EstruturaHSL[imagemASerSalvaEmDisco.getHeight()][imagemASerSalvaEmDisco.getWidth()];
                for (int j = 0; j < imagemASerSalvaEmDisco.getWidth(); j++) {
                    for (int i = 0; i < imagemASerSalvaEmDisco.getHeight(); i++) {
                        HSL.estruturaTemporariaSave[i][j] = new HSL.EstruturaHSL();
                        HSL.estruturaTemporariaCopiaSave[i][j] = new HSL.EstruturaHSL();
                    }
                }
                HSL.RGBparaHSLSave();
                HSL.setLabel(jLabel1);
                FrameControleHSL = new FrameControleHSL();
                loadHSLFrame.setVisible(false);
                FrameControleHSL.setVisible(true);
                hsliniciado = true;
            }
            else{
               HSL.setLabel(jLabel1);
               FrameControleHSL = new FrameControleHSL();
               FrameControleHSL.setVisible(true);
            }
        }
    }//GEN-LAST:event_ExibirHSL

    private void ExibirYIQ(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirYIQ
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            if(!yiqiniciado){
                loadYIQFrame.setVisible(true);
                YIQ.estruturaTemporariaSave = new YIQ.EstruturaYIQ[imagemASerSalvaEmDisco.getHeight()][imagemASerSalvaEmDisco.getWidth()];
                YIQ.estruturaTemporariaCopiaSave = new YIQ.EstruturaYIQ[imagemASerSalvaEmDisco.getHeight()][imagemASerSalvaEmDisco.getWidth()];
                for (int j = 0; j < imagemASerSalvaEmDisco.getWidth(); j++) {
                    for (int i = 0; i < imagemASerSalvaEmDisco.getHeight(); i++) {
                        YIQ.estruturaTemporariaSave[i][j] = new YIQ.EstruturaYIQ();
                        YIQ.estruturaTemporariaCopiaSave[i][j] = new YIQ.EstruturaYIQ();
                    }
                }
                YIQ.RGBparaYIQSave();
                YIQ.setLabel(jLabel1);
                FrameControleYIQ = new FrameControleYIQ();
                loadYIQFrame.setVisible(false);
                FrameControleYIQ.setVisible(true);
                yiqiniciado = true;
            }
            else{
               YIQ.setLabel(jLabel1);
               FrameControleYIQ = new FrameControleYIQ();
               FrameControleYIQ.setVisible(true);
            }
        }
    }//GEN-LAST:event_ExibirYIQ

    private void ExibirMorfologico(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirMorfologico
        if(imagemCarregadaDoDisco != null){
            framePrincipal = framePrincipal.HabilitarEDesabilitarMeuJFrame(framePrincipal, false);
            Morfologicas.setLabel(jLabel1);
            FrameControleMorfologico = new FrameControleMorfologico();
            FrameControleMorfologico.setVisible(true);
        }
    }//GEN-LAST:event_ExibirMorfologico

    private void frameResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frameResized
        if(imagemCarregadaDoDisco != null){
            RedimensionarImagem();
            jLabel1.setIcon(new ImageIcon (imagemASerExibida));
        }
    }//GEN-LAST:event_frameResized

    public static void aplicarOperacoes() {
        //percorre o array de operações definitivas
        for(int i = 0; i < arrayOperacoesDefinit.size(); i++) {
            Operacao elto = arrayOperacoesDefinit.get(i);
            System.out.println("Aplicando operação: " + elto.codOp + ", " + elto.valor);
            //aplica na imagem original todas as operações identificadas no array
            if(elto.codOp.equals("media")) {
                imagemASerSalvaEmDisco = Filtros.MediaSave(elto.valor);
            }
            if(elto.codOp.equals("bartlett")) {
                imagemASerSalvaEmDisco = Filtros.BartlettSave();
            }
            if(elto.codOp.equals("gaussiano")) {
                imagemASerSalvaEmDisco = Filtros.GaussianoSave();
            }
            if(elto.codOp.equals("sepia")) {
                imagemASerSalvaEmDisco = Filtros.SepiaSave();
            }
            if(elto.codOp.equals("peb")) {
                imagemASerSalvaEmDisco = Filtros.PretoEBrancoSave();
            }
            if(elto.codOp.equals("laplaciano")) {
                imagemASerSalvaEmDisco = Filtros.LaplacianoSave();
            }
            if(elto.codOp.equals("prewitt")) {
                imagemASerSalvaEmDisco = Filtros.PrewittSave();
            }
            if(elto.codOp.equals("sobel")) {
                imagemASerSalvaEmDisco = Filtros.SobelSave();
            }
            if(elto.codOp.equals("nitidez")) {
                imagemASerSalvaEmDisco = Filtros.NitidezSave(elto.valor);
            }
            if(elto.codOp.equals("mediana")) {
                imagemASerSalvaEmDisco = Filtros.MedianaSave(elto.valor);
            }
            if(elto.codOp.equals("pontilhado")) {
                imagemASerSalvaEmDisco = Filtros.PontilhadoSave();
            }
            if(elto.codOp.equals("sat")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoSave(elto.valor);
            }
            if(elto.codOp.equals("hue")) {
                imagemASerSalvaEmDisco = HSL.MatizSave(elto.valor);
            }
            if(elto.codOp.equals("lum")) {
                imagemASerSalvaEmDisco = HSL.LuminanciaSave(elto.valor);
            }
            if(elto.codOp.equals("brilho")) {
                imagemASerSalvaEmDisco = LuzESombra.BrilhoSave(elto.valor);
            }
            if(elto.codOp.equals("luminosidade")) {
                imagemASerSalvaEmDisco = LuzESombra.LuminosidadeSave(elto.valor);
            }
            if(elto.codOp.equals("gamma")) {
                imagemASerSalvaEmDisco = LuzESombra.GammaSave(elto.valor);
            }
            if(elto.codOp.equals("compy")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteYSave(elto.valor);
            }
            if(elto.codOp.equals("compi")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteISave(elto.valor);
            }
            if(elto.codOp.equals("compq")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteQSave(elto.valor);
            }
            if(elto.codOp.equals("cinzageral")) {
                imagemASerSalvaEmDisco = RGB.TomDeCinzaSave();
            }
            if(elto.codOp.equals("temp")) {
                imagemASerSalvaEmDisco = RGB.TemperaturaSave(elto.valor);
            }
            if(elto.codOp.equals("vermelhocol")) {
                imagemASerSalvaEmDisco = RGB.VermelhoColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("verdecol")) {
                imagemASerSalvaEmDisco = RGB.VerdeColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("azulcol")) {
                imagemASerSalvaEmDisco = RGB.AzulColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("vermelhocinza")) {
                imagemASerSalvaEmDisco = RGB.VermelhoCinzaSave();
            }
            if(elto.codOp.equals("verdecinza")) {
                imagemASerSalvaEmDisco = RGB.VerdeCinzaSave();
            }
            if(elto.codOp.equals("azulcinza")) {
                imagemASerSalvaEmDisco = RGB.AzulCinzaSave();
            }
            if(elto.codOp.equals("negativo")) {
                imagemASerSalvaEmDisco = RGB.NegativoSave();
            }
            if(elto.codOp.equals("expansao")) {
                imagemASerSalvaEmDisco = RGB.ExpansaoSave();
            }
            if(elto.codOp.equals("equalizacao")) {
                imagemASerSalvaEmDisco = RGB.EqualizacaoSave();
            }
            if(elto.codOp.equals("rotacao")) {
                imagemASerSalvaEmDisco = Morfologicas.Rotacao90Save();
            }
            if(elto.codOp.equals("satVermelho")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoVermelhoSave(elto.valor);
            }
            if(elto.codOp.equals("satVerde")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoVerdeSave(elto.valor);
            }
            if(elto.codOp.equals("satAzul")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoAzulSave(elto.valor);
            }
            if(elto.codOp.equals("satLaranja")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoLaranjaSave(elto.valor);
            }
            if(elto.codOp.equals("satAmarelo")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoAmareloSave(elto.valor);
            }
            if(elto.codOp.equals("satCiano")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoCianoSave(elto.valor);
            }
            if(elto.codOp.equals("satMagenta")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoMagentaSave(elto.valor);
            }
        }
    }
    
    public static void aplicarOperacoesCopia() {
        //percorre o array de operações definitivas
        for(int i = 0; i < arrayOperacoesCopia.size(); i++) {
            Operacao elto = arrayOperacoesCopia.get(i);
            System.out.println("Aplicando operação: " + elto.codOp + ", " + elto.valor);
            //aplica na imagem original todas as operações identificadas no array
            if(elto.codOp.equals("media")) {
                imagemASerSalvaEmDisco = Filtros.MediaSave(elto.valor);
            }
            if(elto.codOp.equals("bartlett")) {
                imagemASerSalvaEmDisco = Filtros.BartlettSave();
            }
            if(elto.codOp.equals("gaussiano")) {
                imagemASerSalvaEmDisco = Filtros.GaussianoSave();
            }
            if(elto.codOp.equals("sepia")) {
                imagemASerSalvaEmDisco = Filtros.SepiaSave();
            }
            if(elto.codOp.equals("peb")) {
                imagemASerSalvaEmDisco = Filtros.PretoEBrancoSave();
            }
            if(elto.codOp.equals("laplaciano")) {
                imagemASerSalvaEmDisco = Filtros.LaplacianoSave();
            }
            if(elto.codOp.equals("prewitt")) {
                imagemASerSalvaEmDisco = Filtros.PrewittSave();
            }
            if(elto.codOp.equals("sobel")) {
                imagemASerSalvaEmDisco = Filtros.SobelSave();
            }
            if(elto.codOp.equals("nitidez")) {
                imagemASerSalvaEmDisco = Filtros.NitidezSave(elto.valor);
            }
            if(elto.codOp.equals("mediana")) {
                imagemASerSalvaEmDisco = Filtros.MedianaSave(elto.valor);
            }
            if(elto.codOp.equals("pontilhado")) {
                imagemASerSalvaEmDisco = Filtros.PontilhadoSave();
            }
            if(elto.codOp.equals("sat")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoSave(elto.valor);
            }
            if(elto.codOp.equals("hue")) {
                imagemASerSalvaEmDisco = HSL.MatizSave(elto.valor);
            }
            if(elto.codOp.equals("lum")) {
                imagemASerSalvaEmDisco = HSL.LuminanciaSave(elto.valor);
            }
            if(elto.codOp.equals("brilho")) {
                imagemASerSalvaEmDisco = LuzESombra.BrilhoSave(elto.valor);
            }
            if(elto.codOp.equals("luminosidade")) {
                imagemASerSalvaEmDisco = LuzESombra.LuminosidadeSave(elto.valor);
            }
            if(elto.codOp.equals("gamma")) {
                imagemASerSalvaEmDisco = LuzESombra.GammaSave(elto.valor);
            }
            if(elto.codOp.equals("compy")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteYSave(elto.valor);
            }
            if(elto.codOp.equals("compi")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteISave(elto.valor);
            }
            if(elto.codOp.equals("compq")) {
                imagemASerSalvaEmDisco = YIQ.ComponenteQSave(elto.valor);
            }
            if(elto.codOp.equals("cinzageral")) {
                imagemASerSalvaEmDisco = RGB.TomDeCinzaSave();
            }
            if(elto.codOp.equals("temp")) {
                imagemASerSalvaEmDisco = RGB.TemperaturaSave(elto.valor);
            }
            if(elto.codOp.equals("vermelhocol")) {
                imagemASerSalvaEmDisco = RGB.VermelhoColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("verdecol")) {
                imagemASerSalvaEmDisco = RGB.VerdeColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("azulcol")) {
                imagemASerSalvaEmDisco = RGB.AzulColoridoSave(elto.valor);
            }
            if(elto.codOp.equals("vermelhocinza")) {
                imagemASerSalvaEmDisco = RGB.VermelhoCinzaSave();
            }
            if(elto.codOp.equals("verdecinza")) {
                imagemASerSalvaEmDisco = RGB.VerdeCinzaSave();
            }
            if(elto.codOp.equals("azulcinza")) {
                imagemASerSalvaEmDisco = RGB.AzulCinzaSave();
            }
            if(elto.codOp.equals("negativo")) {
                imagemASerSalvaEmDisco = RGB.NegativoSave();
            }
            if(elto.codOp.equals("expansao")) {
                imagemASerSalvaEmDisco = RGB.ExpansaoSave();
            }
            if(elto.codOp.equals("equalizacao")) {
                imagemASerSalvaEmDisco = RGB.EqualizacaoSave();
            }
            if(elto.codOp.equals("rotacao")) {
                imagemASerSalvaEmDisco = Morfologicas.Rotacao90Save();
            }
            if(elto.codOp.equals("satVermelho")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoVermelhoSave(elto.valor);
            }
            if(elto.codOp.equals("satVerde")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoVerdeSave(elto.valor);
            }
            if(elto.codOp.equals("satAzul")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoAzulSave(elto.valor);
            }
            if(elto.codOp.equals("satLaranja")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoLaranjaSave(elto.valor);
            }
            if(elto.codOp.equals("satAmarelo")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoAmareloSave(elto.valor);
            }
            if(elto.codOp.equals("satCiano")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoCianoSave(elto.valor);
            }
            if(elto.codOp.equals("satMagenta")) {
                imagemASerSalvaEmDisco = HSL.SaturacaoMagentaSave(elto.valor);
            }
        }
    }
    
    public static void inicializarFrames(){
        FrameControleFiltros = new FrameControleFiltros();
        FrameControleRGB = new FrameControleRGB();
        FrameControleLuzESombra = new FrameControleLuzESombra();
        FrameControleHSL = new FrameControleHSL();
        FrameControleYIQ = new FrameControleYIQ();
        FrameControleMorfologico = new FrameControleMorfologico();
        FrameControleCheck = new FrameControleCheck();
        progressFrame = new ProgressFrame();
        saveFrame = new SaveFrame();
        loadHSLFrame = new LoadHSLFrame();
        loadYIQFrame = new LoadYIQFrame();
    }
    
    public void setIconeFramePrincipal(){
        try{
            Image icone = Toolkit.getDefaultToolkit().getImage("D:/BACKUP SSD/BACKUP INTERMITENTE/LIVROS DA UFERSA/PROJETOS JAVA/Image Editor/src/resources/icone.png");
            setIconImage(icone);
        }
        catch(Exception ex){        
        }
        
    }
    
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            framePrincipal = new FramePrincipal();
            framePrincipal.setVisible(true);
            inicializarFrames();
        });
        
    }
    
    public static void ExibirOriginal(){
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask(){
            @Override
            public void run(){
                jLabel1.setIcon(new ImageIcon(imagemASerExibida));
                timer.cancel();
            }
        };
        timer.schedule(tarefa, 1000);
    }
    
    public FramePrincipal HabilitarEDesabilitarMeuJFrame(FramePrincipal fp, boolean opt){
        fp.setEnabled(opt);
        return fp;
    }
    
    public static FramePrincipal framePrincipal;
    public static ImageIcon icone;
    public static Image image, imageRedimensionadoSemAlteracoes;
    public static ProgressFrame progressFrame;
    public static SaveFrame saveFrame;
    public static LoadHSLFrame loadHSLFrame;
    public static LoadYIQFrame loadYIQFrame;
    public static FrameControleCheck FrameControleCheck;
    public static FrameControleFiltros FrameControleFiltros;
    public static FrameControleRGB FrameControleRGB;
    public static FrameControleLuzESombra FrameControleLuzESombra;
    public static FrameControleHSL FrameControleHSL;
    public static FrameControleYIQ FrameControleYIQ;
    public static FrameControleMorfologico FrameControleMorfologico;
    public static String caminho;
    public static JFreeChart grafico;
    public static javax.swing.JFileChooser seletorDeArquivo;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JMenu jMenu1, jMenu2, jMenu3, jMenu4, jMenu5;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1, jPanel2;
    public static BufferedImage
                  imagemCarregadaDoDisco, imagemASerExibida,
                  imagemASerSalvaEmDisco,imagemOriginalRedimensionadaSemAlteracao,
                  imagemCopia, imagemOriginalRedimensionadaParaReversao;
    public static javax.swing.JMenuItem
                  jMenuItem1, jMenuItem2, jMenuItem3,
                  jMenuItem4, jMenuItem5, jMenuItem6,
                  jMenuItem7, jMenuItem8, jMenuItem9,
                  jMenuItem10, jMenuItem11;
    public static boolean
                  vermelhoCinza = false,
                  verdeCinza = false,
                  azulCinza = false,
                  verificarNegativo = false,
                  foiAplicado = false;
    public static boolean hsliniciado = false;
    public static boolean yiqiniciado = false;
    public static class Operacao {
        int valor;
        String codOp;
    }
    public static ArrayList<Operacao> arrayOperacoesTemp = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesDefinit = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesCopia = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatVermelho = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatVerde = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatAzul = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatLaranja = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatAmarelo = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatCiano = new ArrayList();
    public static ArrayList<Operacao> arrayOperacoesSatMagenta = new ArrayList();
    
    /*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFileChooser seletorDeArquivo;
    // End of variables declaration//GEN-END:variables
    */
}

class meuFiltro extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".jpg") || file.getAbsolutePath().endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Image documents (*.jpg), (*.png)";
    }
}
