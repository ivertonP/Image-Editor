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
    
    public static void RGBtoYIQ(){
        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                img2structYIQ[i][j].Y = Y;
                img2structYIQ[i][j].I = I;
                img2structYIQ[i][j].Q = Q;
                c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                cpystructYIQ[i][j].Y = Y;
                cpystructYIQ[i][j].I = I;
                cpystructYIQ[i][j].Q = Q;
            }
        }        
    }
    
    public static void YIQtoRGB(){
        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {                
                Color c;
                int R = (int) (img2structYIQ[i][j].Y + 0.9563 * img2structYIQ[i][j].I + 0.6210 * img2structYIQ[i][j].Q);
                int G = (int) (img2structYIQ[i][j].Y - 0.2721 * img2structYIQ[i][j].I - 0.6474 * img2structYIQ[i][j].Q);
                int B = (int) (img2structYIQ[i][j].Y - 1.1070 * img2structYIQ[i][j].I + 1.7046 * img2structYIQ[i][j].Q);
                
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
    
    public static BufferedImage Ycomponent(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    img2structYIQ[i][j].Y =  cpystructYIQ[i][j].Y + x;
                }
            }
            YIQtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
        }
        return meuJFrame.imagem2;
    }
    
    public static BufferedImage Icomponent(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    img2structYIQ[i][j].I =  cpystructYIQ[i][j].I + x;
                }
            }
            YIQtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static BufferedImage Qcomponent(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
        else{
            for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                    img2structYIQ[i][j].Q =  cpystructYIQ[i][j].Q + x;
                }
            }
            YIQtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static void ApplyYIQ(){
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
        RGBtoYIQ();
        HSL.RGBtoHSL();
    }
        
    public static void reverterYIQ(){        
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
        RGBtoYIQ();
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
        RGBtoYIQ();
    }
    
    public static int yiqid = 4;
    public static JLabel lbl;
    public static boolean applied = false;
    public static StructYIQ[][] img2structYIQ, cpystructYIQ;
    
    public static class StructYIQ {            //Estrutura utilizada para armazenar os valores RGB convertidos em YIQ e as incidencias dos valores de Y.
        int Y;
        int I;
        int Q;
    }
    
}
