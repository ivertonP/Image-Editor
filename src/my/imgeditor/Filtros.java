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
public class Filtros {
    
    
    public static BufferedImage TomDeCinza(){        
        //O metodo TonsDeCinza() converte a imagem de entrada em uma imagem representada em escala de cinza.        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int cinza = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                c = new Color(cinza, cinza, cinza);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Media(int x) {
        //O metodo Media() suaviza a imagem original, causando borramento através do cálculo da média entre a vizinhança
        //do pixel central da máscara e cada pixel da imagem. A dimensão da mascara deve ser fornecida pelo usuario,
        //aumentando o efeito de borramento à medida que a dimensão aumenta.        
        int min;
        if (FramePrincipal.imagemASerExibida.getHeight() > FramePrincipal.imagemASerExibida.getWidth()) {
            min = FramePrincipal.imagemASerExibida.getWidth();
        }
        else{
            min = FramePrincipal.imagemASerExibida.getHeight();
        }
        
        if((x < 3) || (x > min) || (x%2 == 0)) {
            JOptionPane.showMessageDialog(FramePrincipal.FrameControleFiltros, "Apenas impares maiores ou iguais 3 e menores que " + min + ".");
            return FramePrincipal.imagemASerExibida;
        }
        else{          
            //Definicao das variaveis do metodo.
            //Sao calculados e setados os limites da expansão fisica da imagem e da mascara aplicada na imagem.
            double somar = 0.0;
            double somag = 0.0;
            double somab = 0.0;
            int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (x - 1);
            int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (x - 1);
            int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
            int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

            EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
            EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
            EstruturaRGB[][] mascara = new EstruturaRGB[x][x];

            //Este laco For() normaliza os valores dos pesos da mascara e inicializa estruturaTemporaria1[][] e estruturaTemporaria2[][].        
            for (int i = 0; i < novaDimensaoLinha; i++) {
                for (int j = 0; j < novaDimensaoColuna; j++) {
                    if ((i < x) && (j < x)) {
                        mascara[i][j] = new EstruturaRGB();
                        mascara[i][j].peso = 1.0 / (x * x);
                    }
                    estruturaTemporaria1[i][j] = new EstruturaRGB();
                    estruturaTemporaria2[i][j] = new EstruturaRGB();
                }
            }

            //Este laco For() aninhado copia a imagem original para estruturaTemporaria1[][] de acordo com os limites da expansao fisica.
            for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
                for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                    Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                    estruturaTemporaria1[i][j].r = c.getRed();
                    estruturaTemporaria1[i][j].g = c.getGreen();
                    estruturaTemporaria1[i][j].b = c.getBlue();
                }
            }

            /*
            Estes lacos For() aninhados processam estruturaTemporaria1[][] com a mascara normalizada,
            aplicando o calculo da media em cada pixel e armazenando em estruturaTemporaria2[][].
            */
            for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
                for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {
                    for (int a = -(x / 2), c = 0; a <= x / 2; a++, c++) {
                        for (int b = -(x / 2), d = 0; b <= x / 2; b++, d++) {
                            somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                            somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                            somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                        }
                    }
                    estruturaTemporaria2[i][j].r = somar;
                    estruturaTemporaria2[i][j].g = somag;
                    estruturaTemporaria2[i][j].b = somab;
                    somar = 0;
                    somag = 0;
                    somab = 0;
                }
            }

            //Este laco For() aninhado armazena os valores RGB em image e, apos isto, retorna a imagem final.
            for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
                for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                    int R = (int) estruturaTemporaria2[i][j].r;
                    int G = (int) estruturaTemporaria2[i][j].g;
                    int B = (int) estruturaTemporaria2[i][j].b;
                    Color c = new Color(R, G, B);
                    FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
                }
            }
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage Bartlett() {        
        //O metodo Bartlett() suaviza a imagem original, assemelhando-se aos outros filtros de suavizacao.
        //A dimensão da máscara está fixa em 5x5, e consiste em uma matriz simetrica. 
        //Correlação e convolução geram a mesma imagem resultante.        
        int dimensaoDaMascara = 5;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
            }
        }
        
        
        //Os valores dos pesos da mascara de Bartlett sao pre-definidos,
        //e a matriz permanece fixa e simetrica com dimensões 5x5.
        mascara[0][0].peso = 1.0 / 81.0; mascara[0][1].peso = 2.0 / 81.0;
        mascara[0][2].peso = 3.0 / 81.0; mascara[0][3].peso = 2.0 / 81.0;
        mascara[0][4].peso = 1.0 / 81.0;
        
        mascara[1][0].peso = 2.0 / 81.0; mascara[1][1].peso = 4.0 / 81.0;
        mascara[1][2].peso = 6.0 / 81.0; mascara[1][3].peso = 4.0 / 81.0;
        mascara[1][4].peso = 2.0 / 81.0;
        
        mascara[2][0].peso = 3.0 / 81.0; mascara[2][1].peso = 6.0 / 81.0;
        mascara[2][2].peso = 9.0 / 81.0; mascara[2][3].peso = 6.0 / 81.0;
        mascara[2][4].peso = 3.0 / 81.0;
        
        mascara[3][0].peso = 2.0 / 81.0; mascara[3][1].peso = 4.0 / 81.0;
        mascara[3][2].peso = 6.0 / 81.0; mascara[3][3].peso = 4.0 / 81.0;
        mascara[3][4].peso = 2.0 / 81.0;
        
        mascara[4][0].peso = 1.0 / 81.0; mascara[4][1].peso = 2.0 / 81.0;
        mascara[4][2].peso = 3.0 / 81.0; mascara[4][3].peso = 2.0 / 81.0;
        mascara[4][4].peso = 1.0 / 81.0;

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                estruturaTemporaria2[i][j].r = somar;
                estruturaTemporaria2[i][j].g = somag;
                estruturaTemporaria2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                int R = (int) estruturaTemporaria2[i][j].r;
                int G = (int) estruturaTemporaria2[i][j].g;
                int B = (int) estruturaTemporaria2[i][j].b;
                Color c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Gaussiano() {
        //O metodo Gaussiano() difere do metodo Bartlett() apenas nos pesos da mascara utilizada.
        //A mascara permanece com dimensões 3x3.        
        int dimensaoDaMascara = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
            }
        }
        
        /*
        Os valores dos pesos da mascara de Bartlett sao pre-definidos,
        e a matriz permanece fixa e simetrica com dimensões 5x5.
        */
        mascara[0][0].peso = 1.0 / 16.0; mascara[0][1].peso = 2.0 / 16.0; mascara[0][2].peso = 1.0 / 16.0; 
        mascara[1][0].peso = 2.0 / 16.0; mascara[1][1].peso = 4.0 / 16.0; mascara[1][2].peso = 2.0 / 16.0; 
        mascara[2][0].peso = 1.0 / 16.0; mascara[2][1].peso = 2.0 / 16.0; mascara[2][2].peso = 1.0 / 16.0; 
        

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                estruturaTemporaria2[i][j].r = somar;
                estruturaTemporaria2[i][j].g = somag;
                estruturaTemporaria2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                int R = (int) estruturaTemporaria2[i][j].r;
                int G = (int) estruturaTemporaria2[i][j].g;
                int B = (int) estruturaTemporaria2[i][j].b;
                Color c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Sepia(){
        double r, g, b;
        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
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
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage PretoEBranco(){
        FramePrincipal.imagemASerExibida = Filtros.TomDeCinza();
        int soma = 0, media;
        
        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                soma = soma + c.getRed();
            }
        }
        
        media = soma/(FramePrincipal.imagemASerExibida.getHeight() * FramePrincipal.imagemASerExibida.getWidth());
        
        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
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
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Laplaciano() {
        //O metodo Laplaciano() consiste em um filtro "passa alta", o que significa que sua funcão
        //se restringe a realcar bordas.A mascara utilizada neste metodo tem dimensões 3x3
        //e possui valores negativos em seus pesos.        
        int dimensaoDaMascara = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
            }
        }

        //Os valores dos pesos da mascara Gaussiana sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        mascara[0][0].peso = 1.0; mascara[0][1].peso = 1.0; mascara[0][2].peso = 1.0;
        mascara[1][0].peso = 1.0; mascara[1][1].peso = -8.0; mascara[1][2].peso = 1.0;
        mascara[2][0].peso = 1.0; mascara[2][1].peso = 1.0; mascara[2][2].peso = 1.0;

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    estruturaTemporaria2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        estruturaTemporaria2[i][j].r = 255.0;
                    }
                else estruturaTemporaria2[i][j].r = somar;
                

                if (somag < 0.0) {
                    estruturaTemporaria2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        estruturaTemporaria2[i][j].g = 255.0;
                    }
                else estruturaTemporaria2[i][j].g = somag;
                

                if (somab < 0.0) {
                    estruturaTemporaria2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        estruturaTemporaria2[i][j].b = 255.0;
                    }
                else estruturaTemporaria2[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                int R = (int) estruturaTemporaria2[i][j].r;
                int G = (int) estruturaTemporaria2[i][j].g;
                int B = (int) estruturaTemporaria2[i][j].b;
                Color c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Prewitt() {
        //O metodo Prewitt() sintetiza a aplicação direta de gradiente e derivadas matemáticas,
        //Este filtro tem como objetivo realçar bordas de forma mais suavizada e com mais informações de cor
        //que o filtro Laplaciano(), ou seja, utiliza o gradiente para uma maior aproximação.        
        int dimensaoDaMascara = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria3 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
                estruturaTemporaria3[i][j] = new EstruturaRGB();
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da primeira mascara de Prewitt sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        mascara[0][0].peso = -1.0; mascara[0][1].peso = -1.0; mascara[0][2].peso = -1.0;
        mascara[1][0].peso = 0.0; mascara[1][1].peso = 0.0; mascara[1][2].peso = 0.0;
        mascara[2][0].peso = 1.0; mascara[2][1].peso = 1.0; mascara[2][2].peso = 1.0;

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    estruturaTemporaria2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        estruturaTemporaria2[i][j].r = 255.0;
                    }
                else estruturaTemporaria2[i][j].r = somar;
                

                if (somag < 0.0) {
                    estruturaTemporaria2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        estruturaTemporaria2[i][j].g = 255.0;
                    }
                else estruturaTemporaria2[i][j].g = somag;
                

                if (somab < 0.0) {
                    estruturaTemporaria2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        estruturaTemporaria2[i][j].b = 255.0;
                    }
                else estruturaTemporaria2[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Os valores dos pesos da segunda mascara de Bartlett sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        mascara[0][0].peso = -1.0; mascara[0][1].peso = 0.0; mascara[0][2].peso = 1.0;
        mascara[1][0].peso = -1.0; mascara[1][1].peso = 0.0; mascara[1][2].peso = 1.0;
        mascara[2][0].peso = -1.0; mascara[2][1].peso = 0.0; mascara[2][2].peso = 1.0;

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].b;
                    }
                }

                if (somar < 0.0) {
                    estruturaTemporaria3[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        estruturaTemporaria3[i][j].r = 255.0;
                    }
                else estruturaTemporaria3[i][j].r = somar;
                

                if (somag < 0.0) {
                    estruturaTemporaria3[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        estruturaTemporaria3[i][j].g = 255.0;
                    }
                else estruturaTemporaria3[i][j].g = somag;
                

                if (somab < 0.0) {
                    estruturaTemporaria3[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        estruturaTemporaria3[i][j].b = 255.0;
                    }
                else estruturaTemporaria3[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Este laco For() aninhado soma, normaliza e armazena os valores de cada elemento de estruturaTemporaria2[][] e de estruturaTemporaria3[][] em image.
        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {

                double red = estruturaTemporaria2[i][j].r + estruturaTemporaria3[i][j].r;
                double green = estruturaTemporaria2[i][j].g + estruturaTemporaria3[i][j].g;
                double blue = estruturaTemporaria2[i][j].b + estruturaTemporaria3[i][j].b;

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
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Sobel() {
        //O metodo Sobel(), assim como o metodo Prewitt(), consiste em realcar bordas dos objetos presentes na imagem, porem de forma suavizada.       
        int dimensaoDaMascara = 3;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria3 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
                estruturaTemporaria3[i][j] = new EstruturaRGB();
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da primeira mascara de Sobel sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        mascara[0][0].peso = -1.0; mascara[0][1].peso = -2.0; mascara[0][2].peso = -1.0;
        mascara[1][0].peso = 0.0; mascara[1][1].peso = 0.0; mascara[1][2].peso = 0.0;
        mascara[2][0].peso = 1.0; mascara[2][1].peso = 2.0; mascara[2][2].peso = 1.0;

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                if (somar < 0.0) {
                    estruturaTemporaria2[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        estruturaTemporaria2[i][j].r = 255.0;
                    }
                else { estruturaTemporaria2[i][j].r = somar; }
                

                if (somag < 0.0) {
                    estruturaTemporaria2[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        estruturaTemporaria2[i][j].g = 255.0;
                    }
                else { estruturaTemporaria2[i][j].g = somag; }
                

                if (somab < 0.0) {
                    estruturaTemporaria2[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        estruturaTemporaria2[i][j].b = 255.0;
                    }
                else { estruturaTemporaria2[i][j].b = somab; }

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Os valores dos pesos da segunda mascara de Sobel sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 3x3.
        mascara[0][0].peso = -1.0; mascara[0][1].peso = 0.0; mascara[0][2].peso = 1.0;
        mascara[1][0].peso = -2.0; mascara[1][1].peso = 0.0; mascara[1][2].peso = 2.0;
        mascara[2][0].peso = -1.0; mascara[2][1].peso = 0.0; mascara[2][2].peso = 1.0;

        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i - a][j - b].b;
                    }
                }

                if (somar < 0.0) {
                    estruturaTemporaria3[i][j].r = 0.0;
                }
                else if (somar > 255.0) {
                        estruturaTemporaria3[i][j].r = 255.0;
                    }
                else estruturaTemporaria3[i][j].r = somar;
                

                if (somag < 0.0) {
                    estruturaTemporaria3[i][j].g = 0.0;
                }
                else if (somag > 255.0) {
                        estruturaTemporaria3[i][j].g = 255.0;
                    }
                else estruturaTemporaria3[i][j].g = somag;
                

                if (somab < 0.0) {
                    estruturaTemporaria3[i][j].b = 0.0;
                }
                else if (somab > 255.0) {
                        estruturaTemporaria3[i][j].b = 255.0;
                    }
                else estruturaTemporaria3[i][j].b = somab;

                somar = 0.0;
                somag = 0.0;
                somab = 0.0;
            }
        }

        //Este laco For() aninhados soma, normaliza e armazena os valores de cada elemento de estruturaTemporaria2[][] e de estruturaTemporaria3[][] em image.
        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {

                double red = estruturaTemporaria2[i][j].r + estruturaTemporaria3[i][j].r;
                double green = estruturaTemporaria2[i][j].g + estruturaTemporaria3[i][j].g;
                double blue = estruturaTemporaria2[i][j].b + estruturaTemporaria3[i][j].b;

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
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage Nitidez(int x) {
        //O metodo Nitidez() atenua a nitidez da imagem original.
        //A nitidez aumenta de acordo com o fator fornecido pelo usuario.
        int dimensaoDaMascara = 5;
        double somar = 0.0;
        double somag = 0.0;
        double somab = 0.0;
        int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (dimensaoDaMascara - 1);
        int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (dimensaoDaMascara - 1);
        int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
        int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);

        
        EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] estruturaTemporaria3 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
        EstruturaRGB[][] mascara = new EstruturaRGB[dimensaoDaMascara][dimensaoDaMascara];

        for (int i = 0; i < novaDimensaoLinha; i++) {
            for (int j = 0; j < novaDimensaoColuna; j++) {
                if ((i < dimensaoDaMascara) && (j < dimensaoDaMascara)) {
                    mascara[i][j] = new EstruturaRGB();
                }
                estruturaTemporaria1[i][j] = new EstruturaRGB();
                estruturaTemporaria2[i][j] = new EstruturaRGB();
                estruturaTemporaria3[i][j] = new EstruturaRGB();
            }
        }

        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                estruturaTemporaria1[i][j].r = c.getRed();
                estruturaTemporaria1[i][j].g = c.getGreen();
                estruturaTemporaria1[i][j].b = c.getBlue();
            }
        }

        //Os valores dos pesos da mascara utilizada para "borrar" a imagem original sao pre-definidos, e a matriz permanece fixa e simetrica com dimensões 5x5.        
        mascara[0][0].peso = 1.0 / 256.0; mascara[0][1].peso = 4.0 / 256.0;
        mascara[0][2].peso = 6.0 / 256.0; mascara[0][3].peso = 4.0 / 256.0;
        mascara[0][4].peso = 1.0 / 256.0;

        mascara[1][0].peso = 4.0 / 256.0; mascara[1][1].peso = 16.0 / 256.0;
        mascara[1][2].peso = 24.0 / 256.0; mascara[1][3].peso = 16.0 / 256.0;
        mascara[1][4].peso = 4.0 / 256.0;

        mascara[2][0].peso = 6.0 / 256.0; mascara[2][1].peso = 24.0 / 256.0; 
        mascara[2][2].peso = 36.0 / 256.0; mascara[2][3].peso = 24.0 / 256.0; 
        mascara[2][4].peso = 6.0 / 256.0;

        mascara[3][0].peso = 4.0 / 256.0; mascara[3][1].peso = 16.0 / 256.0;
        mascara[3][2].peso = 24.0 / 256.0; mascara[3][3].peso = 16.0 / 256.0;
        mascara[3][4].peso = 4.0 / 256.0;

        mascara[4][0].peso = 1.0 / 256.0; mascara[4][1].peso = 4.0 / 256.0;
        mascara[4][2].peso = 6.0 / 256.0; mascara[4][3].peso = 4.0 / 256.0;
        mascara[4][4].peso = 1.0 / 256.0;

        //Estes lacos For() aninhados aplicam a suavizacao na imagem original utilizando a mascara Gaussiana 5x5 pre-definida.
        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                for (int a = -(dimensaoDaMascara / 2), c = 0; a <= dimensaoDaMascara / 2; a++, c++) {
                    for (int b = -(dimensaoDaMascara / 2), d = 0; b <= dimensaoDaMascara / 2; b++, d++) {
                        somar = somar + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].r;
                        somag = somag + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].g;
                        somab = somab + mascara[c][d].peso * estruturaTemporaria1[i + a][j + b].b;
                    }
                }

                estruturaTemporaria2[i][j].r = somar;
                estruturaTemporaria2[i][j].g = somag;
                estruturaTemporaria2[i][j].b = somab;
                somar = 0;
                somag = 0;
                somab = 0;
            }
        }

        //Este laco For() aninhado subtrai a imagem borrada da imagem original, obtendo uma imagem composta apenas de bordas.
        for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
            for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                estruturaTemporaria2[i][j].r = estruturaTemporaria1[i][j].r - estruturaTemporaria2[i][j].r;
                estruturaTemporaria2[i][j].g = estruturaTemporaria1[i][j].g - estruturaTemporaria2[i][j].g;
                estruturaTemporaria2[i][j].b = estruturaTemporaria1[i][j].b - estruturaTemporaria2[i][j].b;
            }
        }

        //Este laco For() aninhado adiciona a imagem composta apenas de bordas à imagem original, obtendo o efeito HighBoost.
        for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
            for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                double red = estruturaTemporaria1[i][j].r + x * estruturaTemporaria2[i][j].r;
                double green = estruturaTemporaria1[i][j].g + x * estruturaTemporaria2[i][j].g;
                double blue = estruturaTemporaria1[i][j].b + x * estruturaTemporaria2[i][j].b;

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
                FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
   
    public static BufferedImage Mediana(int x) {
        //O metodo Mediana() consistem em suavizar a imagem original.
        //Difere dos outros metodos de suavizacao pelo fato de que preserva as bordas com nitidez alta e "borra" o interior da imagem.        
        int min;      
        if (FramePrincipal.imagemASerExibida.getHeight() > FramePrincipal.imagemASerExibida.getWidth()) {
            min = FramePrincipal.imagemASerExibida.getWidth();
        }
        else{
            min = FramePrincipal.imagemASerExibida.getHeight();
        }
        
        if((x < 3) || (x > min) || (x%2 == 0)) {
            JOptionPane.showMessageDialog(FramePrincipal.FrameControleFiltros, "Apenas impares maiores ou iguais 3 e menores que " + min + ".");
            return FramePrincipal.imagemASerExibida;
        }
        else{
            int novaDimensaoLinha = FramePrincipal.imagemASerExibida.getHeight() + (x - 1);
            int novaDimensaoColuna = FramePrincipal.imagemASerExibida.getWidth() + (x - 1);
            int complementoLinha = novaDimensaoLinha / 2 - (FramePrincipal.imagemASerExibida.getHeight() / 2);
            int complementoColuna = novaDimensaoColuna / 2 - (FramePrincipal.imagemASerExibida.getWidth() / 2);
            int dimensaoVetor = x * x;

            EstruturaRGB[][] estruturaTemporaria1 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
            EstruturaRGB[][] estruturaTemporaria2 = new EstruturaRGB[novaDimensaoLinha][novaDimensaoColuna];
            double[] vetorOrdenadoR = new double[dimensaoVetor];
            double[] vetorOrdenadoG = new double[dimensaoVetor];
            double[] vetorOrdenadoB = new double[dimensaoVetor];

            for (int i = 0; i < novaDimensaoLinha; i++) {
                for (int j = 0; j < novaDimensaoColuna; j++) {
                    estruturaTemporaria1[i][j] = new EstruturaRGB();
                    estruturaTemporaria2[i][j] = new EstruturaRGB();
                }
            }
            for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
                for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                    Color c = new Color(FramePrincipal.imagemCopia.getRGB(b, a));
                    estruturaTemporaria1[i][j].r = c.getRed();
                    estruturaTemporaria1[i][j].g = c.getGreen();
                    estruturaTemporaria1[i][j].b = c.getBlue();
                }
            }

            //Estes lacos For() aninhados armazenam e ordenam os valores de cada componente de cor dos pixels de estruturaTemporaria1[][] em ordem crescente.
            int cont = 0;
            for (int i = complementoLinha; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++) {
                for (int j = complementoColuna; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++) {

                    for (int a = -(x / 2); a <= x / 2; a++) {
                        for (int b = -(x / 2); b <= x / 2; b++) {
                            vetorOrdenadoR[cont] = estruturaTemporaria1[i + a][j + b].r;
                            vetorOrdenadoG[cont] = estruturaTemporaria1[i + a][j + b].g;
                            vetorOrdenadoB[cont] = estruturaTemporaria1[i + a][j + b].b;
                            cont ++;
                        }
                    }
                    Arrays.sort(vetorOrdenadoR);
                    Arrays.sort(vetorOrdenadoG);
                    Arrays.sort(vetorOrdenadoB);
                    estruturaTemporaria2[i][j].r = vetorOrdenadoR[dimensaoVetor/2];
                    estruturaTemporaria2[i][j].g = vetorOrdenadoG[dimensaoVetor/2];
                    estruturaTemporaria2[i][j].b = vetorOrdenadoB[dimensaoVetor/2];
                    cont = 0;
                }
            }
            for (int i = complementoLinha, a = 0; i < FramePrincipal.imagemASerExibida.getHeight() + complementoLinha; i++, a++) {
                for (int j = complementoColuna, b = 0; j < FramePrincipal.imagemASerExibida.getWidth() + complementoColuna; j++, b++) {
                    int R = (int) estruturaTemporaria2[i][j].r;
                    int G = (int) estruturaTemporaria2[i][j].g;
                    int B = (int) estruturaTemporaria2[i][j].b;
                    Color c = new Color(R, G, B);
                    FramePrincipal.imagemASerExibida.setRGB(b, a, c.getRGB());
                }
            }
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage Pontilhado(){        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++){
                Color c ;
                if(i%2==0 && j%2==0){
                    c = new Color(0, 0, 0);
                    FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                }
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = true;
        return FramePrincipal.imagemASerExibida;
    }
    
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
            
    public static void AplicarFiltros(){
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                FramePrincipal.imagemCopia.setRGB(j, i, c.getRGB());
                FramePrincipal.imagemOriginalRedimensionadaParaReversao.setRGB(j, i, c.getRGB());
            }
        }
        HSL.RGBparaHSL();
        YIQ.RGBparaYIQ();
    }
      
    public static void reverterFiltros(){        
        for (int j = 0; j < FramePrincipal.imagemOriginalRedimensionadaParaReversao.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemOriginalRedimensionadaParaReversao.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemOriginalRedimensionadaParaReversao.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                FramePrincipal.imagemCopia.setRGB(j, i, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        foiAplicado = false;
    }
    
    public static void cancelarCheck(){
        for (int j = 0; j < FramePrincipal.imagemOriginalRedimensionadaParaReversao.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemOriginalRedimensionadaParaReversao.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemOriginalRedimensionadaParaReversao.getRGB(j, i));
                int R = c.getRed();
                int G = c.getGreen();
                int B = c.getBlue();                               
                c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                FramePrincipal.imagemCopia.setRGB(j, i, c.getRGB());
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
    }
    
    public static int IdFiltro = 1;
    public static JLabel labelImagem;
    public static boolean foiAplicado = false;
    
    
    static class EstruturaRGB {            //Estrutura utilizada para manipular as mascaras e as copias da imagem original.
        double r;
        double g;
        double b;
        double peso;
    }
    
    public static class EstruturaYIQ {            //Estrutura utilizada para armazenar os valores RGB convertidos em YIQ e as incidencias dos valores de Y.
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
