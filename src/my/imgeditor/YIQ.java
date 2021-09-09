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
public class YIQ {
    
    public static void RGBparaYIQ(){
        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporaria[i][j].Y = Y;
                estruturaTemporaria[i][j].I = I;
                estruturaTemporaria[i][j].Q = Q;
                c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporaraCopia[i][j].Y = Y;
                estruturaTemporaraCopia[i][j].I = I;
                estruturaTemporaraCopia[i][j].Q = Q;
            }
        }        
    }
    
    public static void YIQparaRGB(){
        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {                
                Color c;
                int R = (int) (estruturaTemporaria[i][j].Y + 0.9563 * estruturaTemporaria[i][j].I + 0.6210 * estruturaTemporaria[i][j].Q);
                int G = (int) (estruturaTemporaria[i][j].Y - 0.2721 * estruturaTemporaria[i][j].I - 0.6474 * estruturaTemporaria[i][j].Q);
                int B = (int) (estruturaTemporaria[i][j].Y - 1.1070 * estruturaTemporaria[i][j].I + 1.7046 * estruturaTemporaria[i][j].Q);                
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
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
    }
    
    public static BufferedImage ComponenteY(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].Y =  estruturaTemporaraCopia[i][j].Y + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
        }
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage ComponenteI(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].I =  estruturaTemporaraCopia[i][j].I + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage ComponenteQ(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].Q =  estruturaTemporaraCopia[i][j].Q + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    public static void AplicarYIQ(){
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
        RGBparaYIQ();
        HSL.RGBparaHSL();
    }
        
    public static void reverterYIQ(){        
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
        RGBparaYIQ();
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
        RGBparaYIQ();
    }
    
    public static int IdYIQ = 4;
    public static JLabel labelImagem;
    public static boolean foiAplicado = false;
    public static EstruturaYIQ[][] estruturaTemporaria, estruturaTemporaraCopia;
    
    public static class EstruturaYIQ {            //Estrutura utilizada para armazenar os valores RGB convertidos em YIQ e as incidencias dos valores de Y.
        int Y;
        int I;
        int Q;
    }
    
}
