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
public class HSL {
    
    public static void RGBparaHSL(){
        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                vetor = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vetor);
                estruturaTemporaria[i][j].H = vetor[0];
                estruturaTemporaria[i][j].S = vetor[1];
                estruturaTemporaria[i][j].L = vetor[2];
                c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                vetor = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vetor);
                estruturaTemporariaCopia[i][j].H = vetor[0];
                estruturaTemporariaCopia[i][j].S = vetor[1];
                estruturaTemporariaCopia[i][j].L = vetor[2];
            }            
        }
    }
           
    public static void HSLparaRGB(){    
        
    for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
        for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){
            int cor = Color.HSBtoRGB(estruturaTemporaria[i][j].H,estruturaTemporaria[i][j].S, estruturaTemporaria[i][j].L);
            Color c;
            int R = (0xff & (cor >> 16));
            int G = (0xff & (cor >> 8));
            int B = (0xff & cor);
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
    }
    
    public static BufferedImage Saturacao(float x){
        
        float sat;        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
                for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth();j ++){
                    sat = estruturaTemporariaCopia[i][j].S + x/100;
                    if(sat < 0){
                        estruturaTemporaria[i][j].S = 0;
                    }
                    else{
                        if(sat > 1){
                            estruturaTemporaria[i][j].S = 1;
                        }
                        else{
                            estruturaTemporaria[i][j].S = sat;
                        }
                    }
                }
            }
            HSLparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage Matiz(float x){
        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemCopia.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemCopia.getHeight(); i++) {
                    estruturaTemporaria[i][j].H = estruturaTemporariaCopia[i][j].H + x/360;
                }
            }
            HSLparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage Luminancia(float x){
        
        float lum;
        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemCopia.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemCopia.getHeight(); i++) {
                    lum = estruturaTemporariaCopia[i][j].L + x/100;
                    if(lum < 0){
                        estruturaTemporaria[i][j].L = 0;
                    }
                    else{
                        if(lum > 1){
                            estruturaTemporaria[i][j].L = 1;
                        }
                        else{
                            estruturaTemporaria[i][j].L = lum;
                        }
                    }
                }
            }
            HSLparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
        
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    public static void AplicarHSL(){
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
        RGBparaHSL();
        YIQ.RGBparaYIQ();
    }
    
    public static void reverterHSL(){        
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
        RGBparaHSL();
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
        RGBparaHSL();
    }
    
    public static int IdHSL = 3;
    public static JLabel labelImagem;
    public static boolean foiAplicado = false;
    public static EstruturaHSL[][] estruturaTemporaria, estruturaTemporariaCopia;
    public static float[] vetor = new float[3];
    
    public static class EstruturaHSL {
        float R;
        float G;
        float B;
        float H;
        float S;
        float L;
    }
    
}
