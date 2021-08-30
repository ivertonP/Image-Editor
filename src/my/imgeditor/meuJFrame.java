package my.imgeditor;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class meuJFrame extends javax.swing.JFrame {
    
    public meuJFrame() {        
        initComponents();
        setLocationRelativeTo(null);
        setIconeMainFrame();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Image Editor");
        fileChooser.setFileFilter(new meuFiltro());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image Editor");
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setAutoscrolls(true);
        jPanel1.setMaximumSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pré-visualizaçäo", 2, 0, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 255, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
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

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirImagem(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem12.setText("Save");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarImagem(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu13.setText("Transform");
        jMenu13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem4.setText("RGB");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirCanaisRGB(evt);
            }
        });
        jMenu13.add(jMenuItem4);

        jMenuItem2.setText("Light - Shadow");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirLightShadow(evt);
            }
        });
        jMenu13.add(jMenuItem2);

        jMenuItem5.setText("HSL");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirHSL(evt);
            }
        });
        jMenu13.add(jMenuItem5);

        jMenuItem7.setText("YIQ");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirYIQ(evt);
            }
        });
        jMenu13.add(jMenuItem7);

        jMenuItem8.setText("Morphological");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirMorphological(evt);
            }
        });
        jMenu13.add(jMenuItem8);

        jMenuBar1.add(jMenu13);

        jMenu8.setText("Filter");
        jMenu8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem3.setText("Show Filters");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExibirFiltros(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuBar1.add(jMenu8);

        jMenu7.setText("Histogram");
        jMenu7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem13.setText("Original");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaOrigem(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuItem15.setText("Preview");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaDestino(evt);
            }
        });
        jMenu7.add(jMenuItem15);

        jMenuBar1.add(jMenu7);

        jMenu3.setText("Segmentation");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem6.setText("S-DGM");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDGM(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

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

    private void AbrirImagem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirImagem
        if(controlFrameRgb.isShowing() == false && controlFrameFilter.isShowing() == false && controlFrameLightShadow.isShowing() == false
           && controlFrameHsl.isShowing() == false && controlFrameYiq.isShowing() == false
           && controlFrameMorph.isShowing() == false && chk.isShowing() == false){
            AbrirExplorer();
        }
        else{
            if((controlFrameRgb.isShowing() == true) && RGB.applied == false){
                controlFrameRgb.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameRgb.isShowing() == true) && RGB.applied == true){
                Check.cod = RGB.colorid;
                controlFrameRgb.setVisible(false);                
                chk.setVisible(true);
                RGB.applied = false;
            }
            if((controlFrameFilter.isShowing() == true) && Filter.applied == false){
                controlFrameFilter.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameFilter.isShowing() == true) && Filter.applied == true){
                Check.cod = Filter.filterid;
                controlFrameFilter.setVisible(false);
                chk.setVisible(true);
                Filter.applied = false;
            }
            if((controlFrameLightShadow.isShowing() == true) && LightShadow.applied == false){
                controlFrameLightShadow.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameLightShadow.isShowing() == true) && LightShadow.applied == true){
                Check.cod = LightShadow.lumid;
                controlFrameLightShadow.setVisible(false);
                chk.setVisible(true);
                LightShadow.applied = false;
            }
            if((controlFrameHsl.isShowing() == true) && HSL.applied == false){
                controlFrameHsl.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameHsl.isShowing() == true) && HSL.applied == true){
                Check.cod = HSL.HSLid;
                controlFrameHsl.setVisible(false);
                chk.setVisible(true);
                HSL.applied = false;
            }
            if((controlFrameYiq.isShowing() == true) && YIQ.applied == false){
                controlFrameYiq.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameYiq.isShowing() == true) && YIQ.applied == true){
                Check.cod = YIQ.yiqid;
                controlFrameYiq.setVisible(false);
                chk.setVisible(true);
                YIQ.applied = false;
            }
            if((controlFrameMorph.isShowing() == true) && MorphTransform.applied == false){
                controlFrameMorph.setVisible(false);
                AbrirExplorer();
            }
            if((controlFrameMorph.isShowing() == true) && MorphTransform.applied == true){
                Check.cod = MorphTransform.morphid;
                controlFrameMorph.setVisible(false);
                chk.setVisible(true);
                MorphTransform.applied = false;
            }
        }
    }//GEN-LAST:event_AbrirImagem

    private static BufferedImage Redimensionar(){
        
        progressFrame.setVisible(true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     //Método nativo para capturar as dimensões da tela do dispositivo 
        int alturaTela = (int) screenSize.getHeight();
        int larguraTela = (int) screenSize.getWidth();      
        alturaTela = (int)(0.80 * alturaTela);                                  //a constante 0.80 representa a porcentagem de pixels necessários para preservar o layout da GUI
        larguraTela = (int)(0.97 * larguraTela);                                //a constante 0.97 representa a porcentagem de pixels necessários para preservar o layout da GUI
        
        int alturaImg = imagem1.getHeight();
        int larguraImg = imagem1.getWidth();
        
        if((larguraImg <= larguraTela) && (alturaImg <= alturaTela)){           //Imagem com altura e largura menores ou iguais às dimensões da tela
            progressFrame.Carregar(5);
            img = imagem1.getScaledInstance(larguraImg, alturaImg, 100);            
            BuildImage();
        }
        else{            
            progressFrame.Carregar(30);            
            if(alturaTela > larguraTela){                                       //Telas verticalmente retangulares                
                int difAltura = 0;
                int difLargura = 0;
                int novaAlturaImg = alturaImg;
                int novaLarguraImg = larguraImg;                
                //Este laço while() é executado enquanto uma das dimensões calculadas para a nova imagem
                //for maior que o limite da tela do dispositivo.                
                while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){                    
                    //A maior diferença calculada entre as dimensões da imagem e as dimensões equivalentes da tela do dispositivo
                    //indicam a dimensão que servirá como base para o redimensionamento da imagem                    
                    if(novaAlturaImg >= alturaTela){
                        difAltura = novaAlturaImg - alturaTela;
                    }
                    else{
                        if(alturaTela > novaAlturaImg){
                            difAltura = alturaTela - novaAlturaImg;
                        }
                    }
                    if(novaLarguraImg >= larguraTela){
                        difLargura = novaLarguraImg - larguraTela;
                    }
                    else{
                        if(larguraTela > novaLarguraImg){
                            difLargura = larguraTela - novaLarguraImg;
                        }
                    }
                    if(difLargura > difAltura){                                 //caso a diferença entre as larguras seja maior que a diferença entre as alturas
                        novaLarguraImg = larguraTela;
                        int percentRedim = (larguraTela * 100)/larguraImg;
                        novaAlturaImg = (alturaImg * percentRedim)/100;
                    }
                    if(difAltura > difLargura){                                 //caso a diferença entre as alturas seja maior que a diferença entre as larguras
                        novaAlturaImg = alturaTela;
                        int percentRedim = (alturaTela * 100)/alturaImg;
                        novaLarguraImg = (larguraImg * percentRedim)/100;
                    }
                }                
                img = imagem1.getScaledInstance(novaLarguraImg, novaAlturaImg, 100);            
                BuildImage();
            }
            
            if(larguraTela > alturaTela){                                       //Telas horizontalmente retangulares                
                int difAltura = 0;
                int difLargura = 0;
                int novaAlturaImg = alturaImg;
                int novaLarguraImg = larguraImg;
                
                while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){                    
                    if(novaAlturaImg >= alturaTela){
                        difAltura = novaAlturaImg - alturaTela;
                    }
                    if(alturaTela >= novaAlturaImg){
                        difAltura = alturaTela - novaAlturaImg;
                    }
                    if(novaLarguraImg >= larguraTela){
                        difLargura = novaLarguraImg - larguraTela;
                    }
                    if(larguraTela >= novaLarguraImg){
                        difLargura = larguraTela - novaLarguraImg;
                    }
                    if(difLargura > difAltura){
                        novaLarguraImg = larguraTela;
                        int percentRedim = (larguraTela * 100)/larguraImg;
                        novaAlturaImg = (alturaImg * percentRedim)/100;
                    }
                    if(difAltura > difLargura){
                        novaAlturaImg = alturaTela;
                        int percentRedim = (alturaTela * 100)/alturaImg;
                        novaLarguraImg = (larguraImg * percentRedim)/100;
                    }
                }                
                img = imagem1.getScaledInstance(novaLarguraImg, novaAlturaImg, 100);            
                BuildImage();
            }

            if(alturaTela == larguraTela){                                      //Telas quadrados
                int difAltura = 0;
                int difLargura = 0;
                int novaAlturaImg = alturaImg;
                int novaLarguraImg = larguraImg;
                
                while((novaAlturaImg > alturaTela) || (novaLarguraImg > larguraTela)){                    
                    if(novaAlturaImg >= alturaTela){
                        difAltura = novaAlturaImg - alturaTela;
                    }
                    if(alturaTela >= novaAlturaImg){
                        difAltura = alturaTela - novaAlturaImg;
                    }
                    if(novaLarguraImg >= larguraTela){
                        difLargura = novaLarguraImg - larguraTela;
                    }
                    if(larguraTela >= novaLarguraImg){
                        difLargura = larguraTela - novaLarguraImg;
                    }
                    if(difLargura > difAltura){
                        novaLarguraImg = larguraTela;
                        int percentRedim = (larguraTela * 100)/larguraImg;
                        novaAlturaImg = (alturaImg * percentRedim)/100;
                    }
                    if(difAltura > difLargura){
                        novaAlturaImg = alturaTela;
                        int percentRedim = (alturaTela * 100)/alturaImg;
                        novaLarguraImg = (larguraImg * percentRedim)/100;
                    }
                }                
                img = imagem1.getScaledInstance(novaLarguraImg, novaAlturaImg, 100);            
                BuildImage();
            }
        }        
        return imagem2;           
    } 
     
    private static void BuildImage(){   
        
        imagem2 = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imgcpy = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imgbkp = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        imgrevert = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr1 = imagem2.createGraphics();
        Graphics2D bGr2 = imgcpy.createGraphics();
        Graphics2D bGr3 = imgbkp.createGraphics();
        Graphics2D bGr4 = imgrevert.createGraphics();
        bGr1.drawImage(img, 0, 0, null);
        bGr2.drawImage(img, 0, 0, null);
        bGr3.drawImage(img, 0, 0, null);
        bGr4.drawImage(img, 0, 0, null);
        bGr1.dispose();
        bGr2.dispose();
        bGr3.dispose();
        bGr4.dispose();        
       
        
        HSL.imagem2structHSL = new HSL.StructHSB[imagem2.getHeight()][imagem2.getWidth()];
        HSL.cpystructHSL = new HSL.StructHSB[imagem2.getHeight()][imagem2.getWidth()];
        HSI.img2structHSI = new HSI.StructHSI[imagem2.getHeight()][imagem2.getWidth()];
        YIQ.img2structYIQ = new YIQ.StructYIQ[imagem2.getHeight()][imagem2.getWidth()];
        YIQ.cpystructYIQ = new YIQ.StructYIQ[imagem2.getHeight()][imagem2.getWidth()];
        for (int j = 0; j < imagem2.getWidth(); j++) {
            for (int i = 0; i < imagem2.getHeight(); i++) {
                HSL.imagem2structHSL[i][j] = new HSL.StructHSB();
                HSL.cpystructHSL[i][j] = new HSL.StructHSB();
                HSI.img2structHSI[i][j] = new HSI.StructHSI();
                YIQ.img2structYIQ[i][j] = new YIQ.StructYIQ();
                YIQ.cpystructYIQ[i][j] = new YIQ.StructYIQ();
            }
        }        
        HSL.RGBtoHSL();
        YIQ.RGBtoYIQ();
        HSI.RGBtoHSIimg2();
    }
    
    private void HistogramaOrigem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaOrigem
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
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

            for (int i = 0; i < imagem1.getHeight(); i++) {
                for (int j = 0; j < imagem1.getWidth(); j++) {
                    Color c = new Color(imagem1.getRGB(j, i));
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
            ControlFrameHist histograma = new ControlFrameHist();
            histograma.setVisible(true);
        }
    }//GEN-LAST:event_HistogramaOrigem
    
    private void HistogramaDestino(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaDestino
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
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

            for (int j = 0; j < imagem2.getWidth(); j++) {
                for (int i = 0; i < imagem2.getHeight(); i++) {
                    Color c = new Color(imagem2.getRGB(j, i));
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
            ControlFrameHist histograma = new ControlFrameHist();
            histograma.setVisible(true);            
        }
    }//GEN-LAST:event_HistogramaDestino
   
    public static JPanel getPanelOriginal() {
        return new ChartPanel(grafico);
    }
    
    private void SalvarImagem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarImagem
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                caminho = file.getPath();
                try {
                    ImageIO.write(imgsave, "PNG", new File(caminho));
                } catch (IOException ex) {
                    Logger.getLogger(meuJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("A imagem nao foi salva. Procedimento cancelado pelo usuario.");
            }
        }
    }//GEN-LAST:event_SalvarImagem
   
    private void SDGM(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDGM
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            Segmentation.setLabel(jLabel1);
            imagem2 = Segmentation.SDGM(imagem1);
            jLabel1.setIcon(new ImageIcon(imagem2));
        }
    }//GEN-LAST:event_SDGM

    private void ExibirCanaisRGB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirCanaisRGB
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            RGB.setLabel(jLabel1);
            controlFrameRgb = new ControlFrameRGB();
            controlFrameRgb.setVisible(true);
        }               
    }//GEN-LAST:event_ExibirCanaisRGB

    private void ExibirFiltros(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirFiltros
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            Filter.setLabel(jLabel1);
            controlFrameFilter = new ControlFrameFilter();
            controlFrameFilter.setVisible(true);
        }
    }//GEN-LAST:event_ExibirFiltros

    private void ExibirLightShadow(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirLightShadow
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            LightShadow.setLabel(jLabel1);
            controlFrameLightShadow = new ControlFrameLightShadow();
            controlFrameLightShadow.setVisible(true);
        }
    }//GEN-LAST:event_ExibirLightShadow

    private void ExibirHSL(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirHSL
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            HSL.setLabel(jLabel1);
            controlFrameHsl = new ControlFrameHSL();
            controlFrameHsl.setVisible(true);
        }
    }//GEN-LAST:event_ExibirHSL

    private void ExibirYIQ(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirYIQ
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            YIQ.setLabel(jLabel1);
            controlFrameYiq = new ControlFrameYIQ();
            controlFrameYiq.setVisible(true);
        }
    }//GEN-LAST:event_ExibirYIQ

    private void ExibirMorphological(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExibirMorphological
        if(imagem1 == null || chk.isShowing() || controlFrameRgb.isShowing() == true || controlFrameFilter.isShowing() == true || controlFrameLightShadow.isShowing() == true || controlFrameHsl.isShowing() == true || controlFrameYiq.isShowing() == true || controlFrameMorph.isShowing() == true){
        }
        else{
            MorphTransform.setLabel(jLabel1);
            controlFrameMorph = new ControlFrameMorph();
            controlFrameMorph.setVisible(true);
        }
    }//GEN-LAST:event_ExibirMorphological
        
    public static void AbrirExplorer(){
        returnVal = fileChooser.showOpenDialog(jPanel1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            caminho = file.getPath();
            try {
                imagem1 = ImageIO.read(new File(caminho));
                imgsave = new BufferedImage(meuJFrame.imagem1.getWidth(), meuJFrame.imagem1.getHeight(), BufferedImage.TYPE_INT_RGB);                
                for (int j = 0; j < meuJFrame.imagem1.getWidth(); j++) {
                    for (int i = 0; i < meuJFrame.imagem1.getHeight(); i++) {
                        Color c = new Color(meuJFrame.imagem1.getRGB(j, i));
                        int R = c.getRed();
                        int G = c.getGreen();
                        int B = c.getBlue();   
                        c = new Color(R, G, B);
                        imgsave.setRGB(j, i, c.getRGB());
                    }
                }                
                jLabel1.setIcon(new ImageIcon(Redimensionar()));
            } catch (IOException ex) {
                Logger.getLogger(meuJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Procedimento cancelado pelo usuario.");
        }
    }
    
    public static void initFrames(){
        controlFrameFilter = new ControlFrameFilter();
        controlFrameRgb = new ControlFrameRGB();
        controlFrameLightShadow = new ControlFrameLightShadow();
        controlFrameHsl = new ControlFrameHSL();
        controlFrameYiq = new ControlFrameYIQ();
        controlFrameMorph = new ControlFrameMorph();
        chk = new Check();
        progressFrame = new ProgressFrame();
    }
    
    public void setIconeMainFrame(){
        try{
            Image icone = Toolkit.getDefaultToolkit().getImage("D:/BACKUP SSD/BACKUP INTERMITENTE/LIVROS DA UFERSA/PROJETOS JAVA/Image Editor/dist/ImgEditorImage.png");
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
            java.util.logging.Logger.getLogger(meuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new meuJFrame().setVisible(true);
            initFrames();
        });
        
    }
    
    public static void Exibir(){
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask(){
            @Override
            public void run(){
                jLabel1.setIcon(new ImageIcon(imagem2));
                timer.cancel();
            }
        };
        timer.schedule(tarefa, 1000);
    }
    
    public static int returnVal;
    public static ImageIcon icon;
    public static ProgressFrame progressFrame;
    public static Check chk;
    public static ControlFrameFilter controlFrameFilter;
    public static ControlFrameRGB controlFrameRgb;
    public static ControlFrameLightShadow controlFrameLightShadow;
    public static ControlFrameHSL controlFrameHsl;
    public static ControlFrameYIQ controlFrameYiq;
    public static ControlFrameMorph controlFrameMorph;
    public static boolean vermelhocinza = false, verdecinza = false, azulcinza = false, vernegativo = false;
    public static Image img;
    public static BufferedImage imagem1, imagem2, imgsave, imgbkp, imgcpy, imgrevert;
    public static String caminho;
    public static JFreeChart grafico;
    
    public static javax.swing.JFileChooser fileChooser;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JMenu jMenu1, jMenu3, jMenu7, jMenu8, jMenu13;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JMenuItem jMenuItem1, jMenuItem2, jMenuItem3, jMenuItem4, jMenuItem5, jMenuItem6, jMenuItem7, jMenuItem8, jMenuItem12, jMenuItem13, jMenuItem15;
    public static javax.swing.JPanel jPanel1, jPanel2;

    /*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
