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
    
    public static void RGBtoHSL(){
        
        for(int i = 0; i < meuJFrame.imagem2.getHeight(); i ++){
            for(int j = 0; j < meuJFrame.imagem2.getWidth(); j ++){
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                vet = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vet);
                imagem2structHSL[i][j].H = vet[0];
                imagem2structHSL[i][j].S = vet[1];
                imagem2structHSL[i][j].L = vet[2];
                c = new Color(meuJFrame.imgcpy.getRGB(j, i));
                vet = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vet);
                cpystructHSL[i][j].H = vet[0];
                cpystructHSL[i][j].S = vet[1];
                cpystructHSL[i][j].L = vet[2];
            }            
        }
    }
           
    public static void HSLtoRGB(){    
        
    for(int i = 0; i < meuJFrame.imagem2.getHeight(); i ++){
        for(int j = 0; j < meuJFrame.imagem2.getWidth(); j ++){
            int cor = Color.HSBtoRGB(imagem2structHSL[i][j].H,imagem2structHSL[i][j].S, imagem2structHSL[i][j].L);
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
            meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }            
        }
    }
    
    public static BufferedImage Saturation(float x){
        
        float sat;
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for(int i = 0; i < meuJFrame.imagem2.getHeight(); i ++){
                for(int j = 0; j < meuJFrame.imagem2.getWidth();j ++){
                    sat = cpystructHSL[i][j].S + x/100;
                    if(sat < 0){
                        imagem2structHSL[i][j].S = 0;
                    }
                    else{
                        if(sat > 1){
                            imagem2structHSL[i][j].S = 1;
                        }
                        else{
                            imagem2structHSL[i][j].S = sat;
                        }
                    }
                }
            }
            HSLtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static BufferedImage Hue(float x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imgcpy.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imgcpy.getHeight(); i++) {
                    imagem2structHSL[i][j].H = cpystructHSL[i][j].H + x/360;
                }
            }
            HSLtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
    
    public static BufferedImage Luminance(float x){
        
        float lum;
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imgcpy.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imgcpy.getHeight(); i++) {
                    lum = cpystructHSL[i][j].L + x/100;
                    if(lum < 0){
                        imagem2structHSL[i][j].L = 0;
                    }
                    else{
                        if(lum > 1){
                            imagem2structHSL[i][j].L = 1;
                        }
                        else{
                            imagem2structHSL[i][j].L = lum;
                        }
                    }
                }
            }
            HSLtoRGB();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
        
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static void ApplyHSL(){
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
        RGBtoHSL();
        YIQ.RGBtoYIQ();
    }
    
    public static void reverterHSL(){        
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
        RGBtoHSL();
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
        RGBtoHSL();
    }
    
    public static int HSLid = 3;
    public static JLabel lbl;
    public static boolean applied = false;
    public static StructHSB[][] imagem2structHSL, cpystructHSL;
    public static float vet[] = new float[3];
    
    public static class StructHSB {
        float R;
        float G;
        float B;
        float H;
        float S;
        float L;
    }
    
}
