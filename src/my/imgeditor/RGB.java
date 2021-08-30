/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.imgeditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class RGB {
        
    public static BufferedImage TomDeCinzaSDGM(){
        
        //!O metodo TonsDeCinza() converte a imagem de entrada em uma imagem representada em escala de cinza.        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int cinza = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                c = new Color(cinza, cinza, cinza);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        meuJFrame.vermelhocinza = true;
        meuJFrame.verdecinza = true;
        meuJFrame.azulcinza = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage TomDeCinza(){
        
        //!O metodo TonsDeCinza() converte a imagem de entrada em uma imagem representada em escala de cinza.        
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
        meuJFrame.vermelhocinza = true;
        meuJFrame.verdecinza = true;
        meuJFrame.azulcinza = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Temperatura(int x){
        
        double r, g, b;
        int R, G, B;
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{            
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                    if(x < 0){
                        r = c.getRed() + x;
                        g = c.getGreen() - x/5.0;
                        b = c.getBlue() - x/5.0;
                        R = (int) r;
                        G = (int) g;
                        B = (int) b;
                    }
                    else{
                        r = c.getRed();
                        g = c.getGreen();
                        b = c.getBlue() - x;
                        R = (int) r;
                        G = (int) g;
                        B = (int) b;
                    }
                    
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
    }
    
    public static BufferedImage VermelhoCol(int x) {        
        //!O metodo VermelhoCol() converte a imagem de entrada em uma imagem representada apenas pela componente Red com seus valores originais.
        if ((meuJFrame.verdecinza == true) || (meuJFrame.azulcinza == true)){}
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                    Color cp = new Color(meuJFrame.imagem2.getRGB(j, i));
                    int R = c.getRed() + x;
                    if(R > 255){
                        R = 255;
                    }
                    if(R < 0){
                        R = 0;
                    }
                    c = new Color(R, cp.getGreen(), cp.getBlue());
                    meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                }
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage VerdeCol(int x){
        
        //!O metodo VerdeCol() converte a imagem de entrada em uma imagem representada apenas pela componente Green com seus valores originais.
        if ((meuJFrame.vermelhocinza == true) || (meuJFrame.azulcinza == true)){}
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                    Color cp = new Color(meuJFrame.imagem2.getRGB(j, i));
                    int G = c.getGreen() + x;
                    if(G > 255){
                        G = 255;
                    }
                    if(G < 0){
                        G = 0;
                    }
                    c = new Color(cp.getRed(), G, cp.getBlue());
                    meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                }
            }
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
        }
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage AzulCol(int x){
        
        //!O metodo AzulCol() converte a imagem de entrada em uma imagem representada apenas pela componente Bluee com seus valores originais.
        if ((meuJFrame.vermelhocinza == true) || (meuJFrame.verdecinza == true)){}
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                    Color cp = new Color(meuJFrame.imagem2.getRGB(j, i));
                    int B = c.getBlue() + x;
                    if(B > 255){
                        B = 255;
                    }
                    if(B < 0){
                        B = 0;
                    }
                    c = new Color(cp.getRed(), cp.getGreen(), B);
                    meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                }
            }
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
        }
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage VermelhoCinza(){
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                c = new Color(c.getRed(), c.getRed(), c.getRed());
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        meuJFrame.vermelhocinza = true;
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage VerdeCinza(){
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                c = new Color(c.getGreen(), c.getGreen(), c.getGreen());
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        meuJFrame.verdecinza = true;
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage AzulCinza(){
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                c = new Color(c.getBlue(), c.getBlue(), c.getBlue());
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        meuJFrame.azulcinza = true;
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Negativo(){
        
        /**
         *O metodo Negativo() calcula as componentes complementares de cor da imagem original.
         *Este calculo resulta em uma imagem com cores invertidas de acordo com a imagem de entrada.
        */
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int R = 255 - c.getRed();
                int G = 255 - c.getGreen();
                int B = 255 - c.getBlue();
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Expansao(){
        
        /**
         *O metodo Expansao() expande o histograma da imagem de entrada.
         *Distribui os valores das componentes de cor de forma que haja uma melhora significativa na qualidade visual da imagem de saída. 
         *Esta técnica funciona bem quando a imagem de entrada possui uma faixa reduzida de níveis de cinza.
         *Caso a imagem de entrada apresente cores no valor mínimo ou no valor máximo da distribuição,
         *a expansão tende a piorar o resultado pelo fato de que ela “desloca” todo o histograma para valores próximo a esses valores.
         */
        
        StructYIQ[][] s = new StructYIQ[meuJFrame.imagem2.getHeight()][meuJFrame.imagem2.getWidth()];
        double maxY = 0;
        double minY;

        /*
        Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
        e armazenadas em uma estrutura auxiliar StructYIQ s[][].
        */        
        for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                s[i][j] = new StructYIQ();
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                s[i][j].Y = Y;
                s[i][j].I = I;
                s[i][j].Q = Q;
            }
        }        

        minY = s[0][0].Y;

        //Este laco For() armazena o menor valor de s[][] em minY e o maior valor em maxY.        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                if (s[i][j].Y > maxY) {
                    maxY = s[i][j].Y;
                } else {
                    if (s[i][j].Y <= minY) {
                        minY = s[i][j].Y;
                    }
                }
            }
        }

        /*
        Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
        convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        */
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c;
                s[i][j].Y = (int) (((s[i][j].Y - minY) / (maxY - minY)) * 255.0);
                int R = (int) (s[i][j].Y + 0.9563 * s[i][j].I + 0.6210 * s[i][j].Q);
                int G = (int) (s[i][j].Y - 0.2721 * s[i][j].I - 0.6474 * s[i][j].Q);
                int B = (int) (s[i][j].Y - 1.1070 * s[i][j].I + 1.7046 * s[i][j].Q);

                if (R < 0) {
                    R = 0;
                }
                if (R > 255) {
                    R = 255;
                }
                if (G < 0) {
                    G = 0;
                }
                if (G > 255) {
                    G = 255;
                }
                if (B < 0) {
                    B = 0;
                }
                if (B > 255) {
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
        
    public static BufferedImage Equalizacao(){
        /**
         *O metodo Equalizacao() contorna a deficiência presente na técnica de expansão de histograma.
         *A equalizacao do histograma permite que os níveis de cinza da imagem sejam equalizados o máximo possível.
         *O histograma gerado fica tão uniforme quanto a fórmula permite.
         *Entretanto, nao se recomenda sua utilizacao quando os valores das cores se aproximam do valor mínimo ou do valor máximo da escala de cores presentes na imagem de entrada.
         *Isto se dá pelo fato de que a equalização mapeia um valor próximo aos limites da escala para o valor destes limites,
         *resultando em mudanças bruscas nas tonalidades das cores onde deveria haver uma mudança possivelmente mais sutil. 
         */
        
        int tam = meuJFrame.imagem2.getWidth() * meuJFrame.imagem2.getHeight();
        StructYIQ[][] s = new StructYIQ[meuJFrame.imagem2.getHeight()][meuJFrame.imagem2.getWidth()];
        StructYIQ[] aux = new StructYIQ[256];
        int somaY = 0;

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                aux[i] = new StructYIQ();
                aux[i].incidY = 0;
                aux[i].somaY = 0;
            }
        }
        
        /*
        Este laco For() aninhado converte em YIQ os valores RGB originais das componentes de cor de cada pixel,
        armazenando em aux[].incidY a incidencia dos valores de Y. 
        */

        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                s[i][j] = new StructYIQ();
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                s[i][j].Y = Y;
                s[i][j].I = I;
                s[i][j].Q = Q;
                aux[Y].Y = Y;
                aux[Y].incidY++;
            }
        }

        for (int i = 0; i < 256; i++) {
            somaY = somaY + aux[i].incidY;
            aux[i].somaY = somaY;
        }

        /*
        Este laco For() aninhado aplica a formula da equalizacao apenas na componente Y dos pixels,
        convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        */
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {                
                Color c;
                s[i][j].Y = (int) Math.round((255.0 / tam) * aux[s[i][j].Y].somaY);
                int R = (int) (s[i][j].Y + 0.9563 * s[i][j].I + 0.6210 * s[i][j].Q);
                int G = (int) (s[i][j].Y - 0.2721 * s[i][j].I - 0.6474 * s[i][j].Q);
                int B = (int) (s[i][j].Y - 1.1070 * s[i][j].I + 1.7046 * s[i][j].Q);

                if (R < 0) {
                    R = 0;
                }
                if (R > 255) {
                    R = 255;
                }
                if (G < 0) {
                    G = 0;
                }
                if (G > 255) {
                    G = 255;
                }
                if (B < 0) {
                    B = 0;
                }
                if (B > 255) {
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
        
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static void ApplyColor(){
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
        
    public static void reverterColor(){        
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
        meuJFrame.vermelhocinza = false;
        meuJFrame.verdecinza = false;
        meuJFrame.azulcinza = false;
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
        meuJFrame.vermelhocinza = false;
        meuJFrame.verdecinza = false;
        meuJFrame.azulcinza = false;
    }
    
    public static int colorid = 0;
    public static JLabel lbl;
    public static boolean applied = false;
    
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