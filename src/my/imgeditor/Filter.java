/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.imgeditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class Filter {
    
    public static BufferedImage TomDeCinza(){        
        //O metodo TonsDeCinza() converte a imagem de entrada em uma imagem representada em escala de cinza.        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int cinza = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                c = new Color(cinza, cinza, cinza);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Media(int x) {
        //O metodo Media() suaviza a imagem original, causando borramento através do cálculo da média entre a vizinhança
        //do pixel central da máscara e cada pixel da imagem. A dimensão da mascara deve ser fornecida pelo usuario,
        //aumentando o efeito de borramento à medida que a dimensão aumenta.        
        int min;
        if (meuJFrame.imagem2.getHeight() > meuJFrame.imagem2.getWidth()) {
                min = meuJFrame.imagem2.getWidth();
        }
        else{
                min = meuJFrame.imagem2.getHeight();
        }
        
        if((x < 3) || (x > min) || (x%2 == 0)) {
                JOptionPane.showMessageDialog(meuJFrame.controlFrameFilter, "Apenas impares maiores ou iguais 3 e menores que " + min + ".");
                return meuJFrame.imagem2;
        }
        else{
           /*
            Definicao das variaveis do metodo.
            Sao calculados e setados os limites da expansão fisica da imagem e da mascara aplicada na imagem.
            */
            double somar = 0.0;
            double somag = 0.0;
            double somab = 0.0;
            int novadimlin = meuJFrame.imagem2.getHeight() + (x - 1);
            int novadimcol = meuJFrame.imagem2.getWidth() + (x - 1);
            int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
            int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

            StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
            StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
            StructRGB[][] masc = new StructRGB[x][x];

            //Este laco For() normaliza os valores dos pesos da mascara e inicializa imgtemp1[][] e imgtemp2[][].        
            for (int i = 0; i < novadimlin; i++) {
                for (int j = 0; j < novadimcol; j++) {
                    if ((i < x) && (j < x)) {
                        masc[i][j] = new StructRGB();
                        masc[i][j].peso = 1.0 / (x * x);
                    }
                    imgtemp1[i][j] = new StructRGB();
                    imgtemp2[i][j] = new StructRGB();
                }
            }

            //Este laco For() aninhado copia a imagem original para imgtemp1[][] de acordo com os limites da expansao fisica.
            for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
                for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                    imgtemp1[i][j].r = c.getRed();
                    imgtemp1[i][j].g = c.getGreen();
                    imgtemp1[i][j].b = c.getBlue();
                }
            }

            /*
            Estes lacos For() aninhados processam a imagem com a mascara normalizada,
            aplicando o calculo da media em cada pixel e armazenando em imgtemp2[][].
            */
            for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
                for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                    for (int a = -(x / 2), c = 0; a <= x / 2; a++, c++) {
                        for (int b = -(x / 2), d = 0; b <= x / 2; b++, d++) {
                            somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                            somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                            somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                        }
                    }

                    imgtemp2[i][j].r = somar;
                    imgtemp2[i][j].g = somag;
                    imgtemp2[i][j].b = somab;
                    somar = 0;
                    somag = 0;
                    somab = 0;
                }
            }

            //Este laco For() aninhado armazena os valores RGB em img e, apos isto, retorna-se a imagem final.
            for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
                for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                    int R = (int) imgtemp2[i][j].r;
                    int G = (int) imgtemp2[i][j].g;
                    int B = (int) imgtemp2[i][j].b;
                    Color c = new Color(R, G, B);
                    meuJFrame.imagem2.setRGB(b, a, c.getRGB());
                }
            }
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static BufferedImage Bartlett() {        
        //O metodo Bartlett() suaviza a imagem original, assemelhando-se aos outros filtros de suavizacao.
        //A dimensão da máscara está fixa em 5x5, e consiste em uma matriz simetrica. 
        //Correlação e convolução geram a mesma imagem resultante.        
        int dimasc = 5;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
            }
        }
        
        
        //Os valores dos pesos da mascara de Bartlett sao pre-definidos,
        //e a matriz permanece fixa e simetrica com dimensões 5x5.
        masc[0][0].peso = 1.0 / 81.0; masc[0][1].peso = 2.0 / 81.0;
        masc[0][2].peso = 3.0 / 81.0; masc[0][3].peso = 2.0 / 81.0;
        masc[0][4].peso = 1.0 / 81.0;
        
        masc[1][0].peso = 2.0 / 81.0; masc[1][1].peso = 4.0 / 81.0;
        masc[1][2].peso = 6.0 / 81.0; masc[1][3].peso = 4.0 / 81.0;
        masc[1][4].peso = 2.0 / 81.0;
        
        masc[2][0].peso = 3.0 / 81.0; masc[2][1].peso = 6.0 / 81.0;
        masc[2][2].peso = 9.0 / 81.0; masc[2][3].peso = 6.0 / 81.0;
        masc[2][4].peso = 3.0 / 81.0;
        
        masc[3][0].peso = 2.0 / 81.0; masc[3][1].peso = 4.0 / 81.0;
        masc[3][2].peso = 6.0 / 81.0; masc[3][3].peso = 4.0 / 81.0;
        masc[3][4].peso = 2.0 / 81.0;
        
        masc[4][0].peso = 1.0 / 81.0; masc[4][1].peso = 2.0 / 81.0;
        masc[4][2].peso = 3.0 / 81.0; masc[4][3].peso = 2.0 / 81.0;
        masc[4][4].peso = 1.0 / 81.0;

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                imgtemp2[i][j].r = somar;
                imgtemp2[i][j].g = somag;
                imgtemp2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                int R = (int) imgtemp2[i][j].r;
                int G = (int) imgtemp2[i][j].g;
                int B = (int) imgtemp2[i][j].b;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }        
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Gaussiano() {
        //O metodo Gaussiano() difere do metodo Bartlett() apenas nos pesos da mascara utilizada.
        //A mascara permanece com dimensões 5x5.        
        int dimasc = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
            }
        }
        
        /*
        Os valores dos pesos da mascara de Bartlett sao pre-definidos,
        e a matriz permanece fixa e simetrica com dimensões 5x5.
        */
        masc[0][0].peso = 1.0 / 16.0; masc[0][1].peso = 2.0 / 16.0; masc[0][2].peso = 1.0 / 16.0; 
        masc[1][0].peso = 2.0 / 16.0; masc[1][1].peso = 4.0 / 16.0; masc[1][2].peso = 2.0 / 16.0; 
        masc[2][0].peso = 1.0 / 16.0; masc[2][1].peso = 2.0 / 16.0; masc[2][2].peso = 1.0 / 16.0; 
        

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                imgtemp2[i][j].r = somar;
                imgtemp2[i][j].g = somag;
                imgtemp2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                int R = (int) imgtemp2[i][j].r;
                int G = (int) imgtemp2[i][j].g;
                int B = (int) imgtemp2[i][j].b;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Sepia(){
        
        double r, g, b;
        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                r = ((c.getRed() * 0.393) + (c.getGreen() * 0.769) + (c.getBlue() * 0.189));
                g = ((c.getRed() * 0.349) + (c.getGreen() * 0.686) + (c.getBlue() * 0.168));
                b = ((c.getRed() * 0.272) + (c.getGreen() * 0.534) + (c.getBlue() * 0.131));
                int R = (int) r;
                int G = (int) g;
                int B = (int) b;
                
                if(R < 0){
                    R = 0;
                }
                if(R > 255){
                    R = 255;
                }
                if(G < 0){
                    G = 0;
                }
                if(G > 255){
                    G = 255;
                }
                if(B < 0){
                    B = 0;
                }                
                if(B > 255){
                    B = 255;
                }                
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage PeB(){
        
        meuJFrame.imagem2 = Filter.TomDeCinza();
        int soma = 0, media;
        
        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                soma = soma + c.getRed();
            }
        }
        
        media = soma/(meuJFrame.imagem2.getHeight() * meuJFrame.imagem2.getWidth());
        
        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                if(c.getRed() < media){
                    int R = 0;
                    int G = 0;
                    int B = 0;
                    c = new Color(R, G, B); 
                }
                else{
                    if(c.getRed() > media){
                        int R = 255;
                        int G = 255;
                        int B = 255;
                        c = new Color(R, G, B); 
                    }
                    else{
                        int R = c.getRed();
                        int G = c.getRed();
                        int B = c.getRed();
                        c = new Color(R, G, B);                        
                    }
                }
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Laplaciano() {
        //O metodo Laplaciano() consiste em um filtro "passa alta", o que significa que sua funcão
        //se restringe a realcar bordas.A mascara utilizada neste metodo tem dimensões 3x3
        //e possui valores negativos em seus pesos.        
        int dimasc = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
            }
        }

        //Os valores dos pesos da mascara Gaussiana sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        masc[0][0].peso = 1.0; masc[0][1].peso = 1.0; masc[0][2].peso = 1.0;
        masc[1][0].peso = 1.0; masc[1][1].peso = -8.0; masc[1][2].peso = 1.0;
        masc[2][0].peso = 1.0; masc[2][1].peso = 1.0; masc[2][2].peso = 1.0;

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    imgtemp2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        imgtemp2[i][j].r = 255.0;
                    }
                else imgtemp2[i][j].r = somar;
                

                if (somag < 0.0) {
                    imgtemp2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        imgtemp2[i][j].g = 255.0;
                    }
                else imgtemp2[i][j].g = somag;
                

                if (somab < 0.0) {
                    imgtemp2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        imgtemp2[i][j].b = 255.0;
                    }
                else imgtemp2[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                int R = (int) imgtemp2[i][j].r;
                int G = (int) imgtemp2[i][j].g;
                int B = (int) imgtemp2[i][j].b;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Prewitt() {
        //O metodo Prewitt() sintetiza a aplicação direta de gradiente e derivadas matemáticas,
        //Este filtro tem como objetivo realçar bordas de forma mais suavizada e com mais informações de cor
        //que o filtro Laplaciano(), ou seja, utiliza o gradiente para uma maior aproximação.        
        int dimasc = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp3 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
                imgtemp3[i][j] = new StructRGB();
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da primeira mascara de Prewitt sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        masc[0][0].peso = -1.0; masc[0][1].peso = -1.0; masc[0][2].peso = -1.0;
        masc[1][0].peso = 0.0; masc[1][1].peso = 0.0; masc[1][2].peso = 0.0;
        masc[2][0].peso = 1.0; masc[2][1].peso = 1.0; masc[2][2].peso = 1.0;

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    imgtemp2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        imgtemp2[i][j].r = 255.0;
                    }
                else imgtemp2[i][j].r = somar;
                

                if (somag < 0.0) {
                    imgtemp2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        imgtemp2[i][j].g = 255.0;
                    }
                else imgtemp2[i][j].g = somag;
                

                if (somab < 0.0) {
                    imgtemp2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        imgtemp2[i][j].b = 255.0;
                    }
                else imgtemp2[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Os valores dos pesos da segunda mascara de Bartlett sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        masc[0][0].peso = -1.0; masc[0][1].peso = 0.0; masc[0][2].peso = 1.0;
        masc[1][0].peso = -1.0; masc[1][1].peso = 0.0; masc[1][2].peso = 1.0;
        masc[2][0].peso = -1.0; masc[2][1].peso = 0.0; masc[2][2].peso = 1.0;

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i - a][j - b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i - a][j - b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i - a][j - b].b;
                    }
                }

                if (somar < 0.0) {
                    imgtemp3[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        imgtemp3[i][j].r = 255.0;
                    }
                else imgtemp3[i][j].r = somar;
                

                if (somag < 0.0) {
                    imgtemp3[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        imgtemp3[i][j].g = 255.0;
                    }
                else imgtemp3[i][j].g = somag;
                

                if (somab < 0.0) {
                    imgtemp3[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        imgtemp3[i][j].b = 255.0;
                    }
                else imgtemp3[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Este laco For() aninhado soma, normaliza e armazena os valores de cada elemento de imgtemp2[][] e de imgtemp3[][] em img.
        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {

                double red = imgtemp2[i][j].r + imgtemp3[i][j].r;
                double green = imgtemp2[i][j].g + imgtemp3[i][j].g;
                double blue = imgtemp2[i][j].b + imgtemp3[i][j].b;

                if (red < 0.0) {
                    red = 0.0;
                } else {
                    if (red > 255.0) {
                        red = 255.0;
                    }
                }

                if (green < 0.0) {
                    green = 0.0;
                } else {
                    if (green > 255.0) {
                        green = 255.0;
                    }
                }

                if (blue < 0.0) {
                    blue = 0.0;
                } else {
                    if (blue > 255.0) {
                        blue = 255.0;
                    }
                }

                int R = (int) red;
                int G = (int) green;
                int B = (int) blue;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Sobel() {
        //O metodo Sobel(), assim como o metodo Prewitt(), consiste em realcar bordas dos objetos presentes na imagem, porem de forma suavizada.       
        int dimasc = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp3 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
                imgtemp3[i][j] = new StructRGB();
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da primeira mascara de Sobel sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        masc[0][0].peso = -1.0; masc[0][1].peso = -2.0; masc[0][2].peso = -1.0;
        masc[1][0].peso = 0.0; masc[1][1].peso = 0.0; masc[1][2].peso = 0.0;
        masc[2][0].peso = 1.0; masc[2][1].peso = 2.0; masc[2][2].peso = 1.0;

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    imgtemp2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        imgtemp2[i][j].r = 255.0;
                    }
                else { imgtemp2[i][j].r = somar; }
                

                if (somag < 0.0) {
                    imgtemp2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        imgtemp2[i][j].g = 255.0;
                    }
                else { imgtemp2[i][j].g = somag; }
                

                if (somab < 0.0) {
                    imgtemp2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        imgtemp2[i][j].b = 255.0;
                    }
                else { imgtemp2[i][j].b = somab; }

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Os valores dos pesos da segunda mascara de Sobel sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        masc[0][0].peso = -1.0; masc[0][1].peso = 0.0; masc[0][2].peso = 1.0;
        masc[1][0].peso = -2.0; masc[1][1].peso = 0.0; masc[1][2].peso = 2.0;
        masc[2][0].peso = -1.0; masc[2][1].peso = 0.0; masc[2][2].peso = 1.0;

        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i - a][j - b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i - a][j - b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i - a][j - b].b;
                    }
                }

                if (somar < 0.0) {
                    imgtemp3[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        imgtemp3[i][j].r = 255.0;
                    }
                else imgtemp3[i][j].r = somar;
                

                if (somag < 0.0) {
                    imgtemp3[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        imgtemp3[i][j].g = 255.0;
                    }
                else imgtemp3[i][j].g = somag;
                

                if (somab < 0.0) {
                    imgtemp3[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        imgtemp3[i][j].b = 255.0;
                    }
                else imgtemp3[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Este laco For() aninhados soma, normaliza e armazena os valores de cada elemento de imgtemp2[][] e de imgtemp3[][] em img.
        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {

                double red = imgtemp2[i][j].r + imgtemp3[i][j].r;
                double green = imgtemp2[i][j].g + imgtemp3[i][j].g;
                double blue = imgtemp2[i][j].b + imgtemp3[i][j].b;

                if (red < 0.0) {
                    red = 0.0;
                } else {
                    if (red > 255.0) {
                        red = 255.0;
                    }
                }

                if (green < 0.0) {
                    green = 0.0;
                } else {
                    if (green > 255.0) {
                        green = 255.0;
                    }
                }

                if (blue < 0.0) {
                    blue = 0.0;
                } else {
                    if (blue > 255.0) {
                        blue = 255.0;
                    }
                }

                int R = (int) red;
                int G = (int) green;
                int B = (int) blue;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Nitidez(int x) {
        //O metodo HighBoost() atua na nitidez da imagem original.
        //A nitidez aumenta de acordo com o fator fornecido pelo usuario.
        int dimasc = 5;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novadimlin = meuJFrame.imagem2.getHeight() + (dimasc - 1);
        int novadimcol = meuJFrame.imagem2.getWidth() + (dimasc - 1);
        int compllin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
        int complcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);

        
        StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] imgtemp3 = new StructRGB[novadimlin][novadimcol];
        StructRGB[][] masc = new StructRGB[dimasc][dimasc];

        for (int i = 0; i < novadimlin; i++) {
            for (int j = 0; j < novadimcol; j++) {
                if ((i < dimasc) && (j < dimasc)) {
                    masc[i][j] = new StructRGB();
                }
                imgtemp1[i][j] = new StructRGB();
                imgtemp2[i][j] = new StructRGB();
                imgtemp3[i][j] = new StructRGB();
            }
        }

        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                imgtemp1[i][j].r = c.getRed();
                imgtemp1[i][j].g = c.getGreen();
                imgtemp1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da mascara utilizada para "borrar" a imagem original sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 5x5.        
        masc[0][0].peso = 1.0 / 256.0; masc[0][1].peso = 4.0 / 256.0;
        masc[0][2].peso = 6.0 / 256.0; masc[0][3].peso = 4.0 / 256.0;
        masc[0][4].peso = 1.0 / 256.0;

        masc[1][0].peso = 4.0 / 256.0; masc[1][1].peso = 16.0 / 256.0;
        masc[1][2].peso = 24.0 / 256.0; masc[1][3].peso = 16.0 / 256.0;
        masc[1][4].peso = 4.0 / 256.0;

        masc[2][0].peso = 6.0 / 256.0; masc[2][1].peso = 24.0 / 256.0; 
        masc[2][2].peso = 36.0 / 256.0; masc[2][3].peso = 24.0 / 256.0; 
        masc[2][4].peso = 6.0 / 256.0;

        masc[3][0].peso = 4.0 / 256.0; masc[3][1].peso = 16.0 / 256.0;
        masc[3][2].peso = 24.0 / 256.0; masc[3][3].peso = 16.0 / 256.0;
        masc[3][4].peso = 4.0 / 256.0;

        masc[4][0].peso = 1.0 / 256.0; masc[4][1].peso = 4.0 / 256.0;
        masc[4][2].peso = 6.0 / 256.0; masc[4][3].peso = 4.0 / 256.0;
        masc[4][4].peso = 1.0 / 256.0;

        //Estes lacos For() aninhados aplicam a suavizacao na imagem original utilizando a mascara Gaussiana 5x5 pre-definida.
        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                for (int a = -(dimasc / 2), c = 0; a <= dimasc / 2; a++, c++) {
                    for (int b = -(dimasc / 2), d = 0; b <= dimasc / 2; b++, d++) {
                        somar = somar + masc[c][d].peso * imgtemp1[i + a][j + b].r;
                        somag = somag + masc[c][d].peso * imgtemp1[i + a][j + b].g;
                        somab = somab + masc[c][d].peso * imgtemp1[i + a][j + b].b;
                    }
                }

                imgtemp2[i][j].r = somar;
                imgtemp2[i][j].g = somag;
                imgtemp2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        //Este laco For() aninhado subtrai a imagem borrada da imagem original, obtendo uma imagem composta apenas de bordas.
        for (int i = compllin; i < meuJFrame.imagem2.getHeight() + compllin; i++) {
            for (int j = complcol; j < meuJFrame.imagem2.getWidth() + complcol; j++) {

                imgtemp2[i][j].r = imgtemp1[i][j].r - imgtemp2[i][j].r;
                imgtemp2[i][j].g = imgtemp1[i][j].g - imgtemp2[i][j].g;
                imgtemp2[i][j].b = imgtemp1[i][j].b - imgtemp2[i][j].b;
            }
        }

        //Este laco For() aninhado adiciona a imagem composta apenas de bordas à imagem original, obtendo o efeito HighBoost.
        for (int i = compllin, a = 0; i < meuJFrame.imagem2.getHeight() + compllin; i++, a++) {
            for (int j = complcol, b = 0; j < meuJFrame.imagem2.getWidth() + complcol; j++, b++) {
                double red = imgtemp1[i][j].r + x * imgtemp2[i][j].r;
                double green = imgtemp1[i][j].g + x * imgtemp2[i][j].g;
                double blue = imgtemp1[i][j].b + x * imgtemp2[i][j].b;

                if (red < 0.0) {
                    red = 0.0;
                } else {
                    if (red > 255.0) {
                        red = 255.0;
                    }
                }

                if (green < 0.0) {
                    green = 0.0;
                } else {
                    if (green > 255.0) {
                        green = 255.0;
                    }
                }

                if (blue < 0.0) {
                    blue = 0.0;
                } else {
                    if (blue > 255.0) {
                        blue = 255.0;
                    }
                }

                int R = (int) red;
                int G = (int) green;
                int B = (int) blue;
                Color c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(b, a, c.getRGB());
            }
        }
        
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
   
    public static BufferedImage Mediana(int x) {
        //O metodo Mediana() consistem em suavizar a imagem original.
        //Difere dos outros metodos de suavizacao pelo fato de que preserva as bordas com nitidez alta e "borra" o interior da imagem.        
        int min;      
        if (meuJFrame.imagem2.getHeight() > meuJFrame.imagem2.getWidth()) {
                min = meuJFrame.imagem2.getWidth();
        }
        else{
                min = meuJFrame.imagem2.getHeight();
        }
        
        if((x < 3) || (x > min) || (x%2 == 0)) {
                JOptionPane.showMessageDialog(meuJFrame.controlFrameFilter, "Apenas impares maiores ou iguais 3 e menores que " + min + ".");
                return meuJFrame.imagem2;
        }
        else{
            int novadimlin = meuJFrame.imagem2.getHeight() + (x - 1);
            int novadimcol = meuJFrame.imagem2.getWidth() + (x - 1);
            int complemlin = novadimlin / 2 - (meuJFrame.imagem2.getHeight() / 2);
            int complemcol = novadimcol / 2 - (meuJFrame.imagem2.getWidth() / 2);
            int dimvet = x * x;

            StructRGB[][] imgtemp1 = new StructRGB[novadimlin][novadimcol];
            StructRGB[][] imgtemp2 = new StructRGB[novadimlin][novadimcol];
            double[] vetorOrdR = new double[dimvet];
            double[] vetorOrdG = new double[dimvet];
            double[] vetorOrdB = new double[dimvet];

            for (int i = 0; i < novadimlin; i++) {
                for (int j = 0; j < novadimcol; j++) {
                    imgtemp1[i][j] = new StructRGB();
                    imgtemp2[i][j] = new StructRGB();
                }
            }

            for (int i = complemlin, a = 0; i < meuJFrame.imagem2.getHeight() + complemlin; i++, a++) {
                for (int j = complemcol, b = 0; j < meuJFrame.imagem2.getWidth() + complemcol; j++, b++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(b, a));
                    imgtemp1[i][j].r = c.getRed();
                    imgtemp1[i][j].g = c.getGreen();
                    imgtemp1[i][j].b = c.getBlue();
                }
            }

            //Estes lacos For() aninhados armazenam e ordenam os valores de cada componente de cor dos pixels de imgtemp1[][] em ordem crescente.
            int cont = 0;
            for (int i = complemlin; i < meuJFrame.imagem2.getHeight() + complemlin; i++) {
                for (int j = complemcol; j < meuJFrame.imagem2.getWidth() + complemcol; j++) {

                    for (int a = -(x / 2); a <= x / 2; a++) {
                        for (int b = -(x / 2); b <= x / 2; b++) {

                            vetorOrdR[cont] = imgtemp1[i + a][j + b].r;
                            vetorOrdG[cont] = imgtemp1[i + a][j + b].g;
                            vetorOrdB[cont] = imgtemp1[i + a][j + b].b;
                            cont ++;
                        }
                    }

                    Arrays.sort(vetorOrdR);
                    Arrays.sort(vetorOrdG);
                    Arrays.sort(vetorOrdB);

                    imgtemp2[i][j].r = vetorOrdR[dimvet/2];
                    imgtemp2[i][j].g = vetorOrdG[dimvet/2];
                    imgtemp2[i][j].b = vetorOrdB[dimvet/2];
                    cont = 0;
                }
            }

            for (int i = complemlin, a = 0; i < meuJFrame.imagem2.getHeight() + complemlin; i++, a++) {
                for (int j = complemcol, b = 0; j < meuJFrame.imagem2.getWidth() + complemcol; j++, b++) {
                    int R = (int) imgtemp2[i][j].r;
                    int G = (int) imgtemp2[i][j].g;
                    int B = (int) imgtemp2[i][j].b;
                    Color c = new Color(R, G, B);
                    meuJFrame.imagem2.setRGB(b, a, c.getRGB());
                }
            }
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static BufferedImage Dithering(){
        
        for(int i = 0; i < meuJFrame.imagem2.getHeight(); i++){
            for(int j = 0; j < meuJFrame.imagem2.getWidth(); j++){
                Color c = new Color(0, 51, 102);
                if(i%2==0 && j%2==0){
                    meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                }
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static void setLabel(JLabel label){
        lbl = label;
    }
            
    public static void ApplyFilter(){
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
                meuJFrame.imgrevert.setRGB(j, i, c.getRGB());
            }
        }
        HSL.RGBtoHSL();
        YIQ.RGBtoYIQ();
    }
      
    public static void reverterFilter(){        
        for (int j = 0; j < meuJFrame.imgrevert.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imgrevert.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgrevert.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = false;
    }
    
    public static void cancelarCheck(){
        for (int j = 0; j < meuJFrame.imgrevert.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imgrevert.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgrevert.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                meuJFrame.imgcpy.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
    }
    
    public static int filterid = 1;
    public static JLabel lbl;
    public static boolean applied = false;
    
    
    static class StructRGB {            //Estrutura utilizada para manipular as mascaras e as copias da imagem original.

        double r;
        double g;
        double b;
        double peso;
    }
    
    public static class StructYIQ {            //Estrutura utilizada para armazenar os valores RGB convertidos em YIQ e as incidencias dos valores de Y.
        int Y;
        int I;
        int Q;
        int incidY;
        int incidI;
        int incidQ;
        int somaY;
        int somaI;
        int somaQ;
    }
}
