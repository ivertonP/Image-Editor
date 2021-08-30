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
 * @author Iverton
 */
public class LightShadow {
    
    public static BufferedImage Brilho(int x){
        /**
         *O metodo Logaritmica() exibe uma imagem de saída com uma faixa de valores de escala de cinza maior que a imagem de entrada.
         *Isto permite que a visibilidade da imagem de saida seja maior onde havia tons muitos escuros.
         */
        int cst;
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
        }
        else{
            if(x < 0){
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
                        Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                        s[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
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
            }
            else{
                cst = x + 355;
                for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                    for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                        Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                        double r = c.getRed()/255.0;
                        double g = c.getGreen()/255.0;
                        double b = c.getBlue()/255.0;
                        int R = (int) (cst * Math.log(1 + r));
                        int G = (int) (cst * Math.log(1 + g));
                        int B = (int) (cst * Math.log(1 + b));
                        if(R > 255){
                            R = 255;
                        }
                        if(R < 0){
                            R = 0;
                        }
                        if(G > 255){
                            G = 255;
                        }
                        if(G < 0){
                            G = 0;
                        }
                        if(B > 255){
                            B = 255;
                        }
                        if(B < 0){
                            B = 0;
                        }
                        c = new Color(R, G, B);
                        meuJFrame.imagem2.setRGB(j, i, c.getRGB());
                    }
                }
            }
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        }
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Gamma(double x){
        
        /**
         *O metodo Gamma() mapeia uma faixa de valores próximos do preto para uma faixa mais espessa na imagem de saída.
         *Ao inserir os valores para a constante e para o expoente da fórmula, o usuário pode observar os resultados obtidos e buscar a imagem que mais se adeque a sua necessidade de detalhamento, podendo escurecer ou clarear a imagem de saída.
         *Para valores menores que 1.0, o resultado é uma imagem mais clara.
         *Para valores acima de 1.0, o resultado é uma imagem mais escura.
        */
        double exp = 0.0;
        if(x > 0.0){
            exp = (100.0 - x)/100.0;
        }
        if(x == 0.0){
            exp = 1.0;
        }
        if(x < 0.0){
            exp = (-x/10.0) + 1.0;
        }
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                double r = c.getRed()/255.0;
                double g = c.getGreen()/255.0;
                double b = c.getBlue()/255.0;
                int R = (int) (255.0 * Math.pow(r, exp));
                int G = (int) (255.0 * Math.pow(g, exp));
                int B = (int) (255.0 * Math.pow(b, exp));
                if(R > 255){
                    R = 255;
                }
                if(R < 0){
                    R = 0;
                }
                if(G > 255){
                    G = 255;
                }
                if(G < 0){
                    G = 0;
                }
                if(B > 255){
                    B = 255;
                }
                if(B < 0){
                    B = 0;
                }
                c = new Color(R, G, B);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }        
        lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
        applied = true;
        return meuJFrame.imagem2;
    }
        
    public static BufferedImage Luminosidade(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{        
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
                    Color c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                    s[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
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
    }
        
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static void ApplyLuminance(){
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
    
    public static void reverterLuminance(){        
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
    
    public static JLabel lbl;
    public static boolean applied = false;
    public static int lumid = 2;
    
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
