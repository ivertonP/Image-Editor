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
public class HSI {
    
    public static void RGBtoHSIimg2(){
        
        double min = 0;
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c = new Color(meuJFrame.imagem2.getRGB(j, i));
                double R = c.getRed()/255.0;
                double G = c.getGreen()/255.0;
                double B = c.getBlue()/255.0;
                img2structHSI[i][j].R = R;
                img2structHSI[i][j].G = G;
                img2structHSI[i][j].B = B;
            }
        }
        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                
                if((img2structHSI[i][j].R < img2structHSI[i][j].G) && (img2structHSI[i][j].R < img2structHSI[i][j].B)){
                    min = img2structHSI[i][j].R;
                }
                if((img2structHSI[i][j].G < img2structHSI[i][j].R) && (img2structHSI[i][j].G < img2structHSI[i][j].B)){
                    min = img2structHSI[i][j].G;
                }
                if((img2structHSI[i][j].B < img2structHSI[i][j].R) && (img2structHSI[i][j].B < img2structHSI[i][j].G)){
                    min = img2structHSI[i][j].B;
                }
            }
        }
        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                
                double rming = (img2structHSI[i][j].R - img2structHSI[i][j].G);
                double rminb = (img2structHSI[i][j].R - img2structHSI[i][j].B);
                double gminb = (img2structHSI[i][j].G - img2structHSI[i][j].B);
                double pt1 = (rming+rminb)*0.5;
                double pt2 = Math.pow(((rming*rming) + (rminb*gminb)), 0.5) + 0.0000001;
                double ang = (Math.acos(pt1/pt2));
                
                if((img2structHSI[i][j].B) > (img2structHSI[i][j].G)){
                    img2structHSI[i][j].H = (360.0 - ang)/360.0;
                }                
                else{
                    if((img2structHSI[i][j].B) <= (img2structHSI[i][j].G)){
                        img2structHSI[i][j].H = ang/360.0;
                    }
                }
                
                img2structHSI[i][j].S = 1.0 - ((3.0/(img2structHSI[i][j].R + img2structHSI[i][j].G + img2structHSI[i][j].B))*min);
                
                img2structHSI[i][j].I = (img2structHSI[i][j].R + img2structHSI[i][j].G + img2structHSI[i][j].B)/3.0;
            }
        }        
    }
    
    public static void HSItoRGBimg2(){      
        
        for (int j = 0; j < meuJFrame.imagem2.getWidth(); j++) {
            for (int i = 0; i < meuJFrame.imagem2.getHeight(); i++) {
                Color c;
                //Devolver o valor original à Matiz
                double hue = img2structHSI[i][j].H*360.0;
                double R = 0.0, G = 0.0, B = 0.0;
                
                //Determinar o intervalo da Matiz
                if(hue >= 0.0 && hue < 120.0){
                    
                    R = img2structHSI[i][j].I * (1.0 + ((img2structHSI[i][j].S * Math.cos(hue))/Math.cos(60.0 - hue)));
                    G = (3.0 * img2structHSI[i][j].I) - (img2structHSI[i][j].R + img2structHSI[i][j].B);
                    B = img2structHSI[i][j].I * (1.0 - img2structHSI[i][j].S);
                }
                else{
                    if(hue >= 120.0 && hue < 240.0){
                        
                        hue = hue - 120.0;
                        R = img2structHSI[i][j].I * (1.0 - img2structHSI[i][j].S);
                        G = img2structHSI[i][j].I * (1.0 + ((img2structHSI[i][j].S * Math.cos(hue))/Math.cos(60.0 - hue)));
                        B = (3.0 * img2structHSI[i][j].I) - (img2structHSI[i][j].R + img2structHSI[i][j].G);
                    }
                    else{
                        if(hue >= 240.0 && hue < 360.0){
                            
                            hue = hue - 240.0;
                            R = (3.0 * img2structHSI[i][j].I) - (img2structHSI[i][j].G + img2structHSI[i][j].B);
                            G = img2structHSI[i][j].I * (1.0 - img2structHSI[i][j].S);
                            B = img2structHSI[i][j].I * (1.0 + ((img2structHSI[i][j].S * Math.cos(hue))/Math.cos(60.0 - hue)));
                        }
                    }
                }
                
                //Normalizar os valores convertidos para RGB
                int r = (int)(R*255.0);
                int g = (int)(G*255.0);
                int b = (int)(B*255.0);
                if(r < 0){
                    r = 0;
                }
                if(r > 255){
                    r = 255;
                }
                if(g < 0){
                    g = 0;
                }
                if(g > 255){
                    g = 255;
                }
                if(b < 0){
                    b = 0;
                }
                if(b > 255){
                    b = 255;
                }
                c = new Color(r, g, b);
                meuJFrame.imagem2.setRGB(j, i, c.getRGB());
            }
        }
    }
    
    public static BufferedImage Saturacao(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imgcpy.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imgcpy.getHeight(); i++) {
                    img2structHSI[i][j].S = x/100.0;
                }
            }
            HSItoRGBimg2();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
        
    public static BufferedImage Matiz(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imgcpy.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imgcpy.getHeight(); i++) {
                    img2structHSI[i][j].H = x/360.0;
                }
            }
            HSItoRGBimg2();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
     
    public static BufferedImage Intensidade(int x){
        
        if(x == 0){
            lbl.setIcon(new ImageIcon(meuJFrame.imgcpy));
            applied = true;
            return meuJFrame.imgcpy;
        }
        else{
            for (int j = 0; j < meuJFrame.imgcpy.getWidth(); j++) {
                for (int i = 0; i < meuJFrame.imgcpy.getHeight(); i++) {
                    img2structHSI[i][j].I = x/100.0;
                }
            }
            HSItoRGBimg2();
            lbl.setIcon(new ImageIcon(meuJFrame.imagem2));
            applied = true;
            return meuJFrame.imagem2;
        }
    }
        
    public static void setLabel(JLabel label){
        lbl = label;
    }
    
    public static void ApplyHSI(){
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
        RGBtoHSIimg2();
    }
    
    public static void reverterHSI(){        
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
        RGBtoHSIimg2();
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
    
    public static int HSIid = 3;
    public static JLabel lbl;
    public static boolean applied = false;
    public static StructHSI[][] img2structHSI;
    public static StructHSI[][] cpystructHSI;
       
    public static class StructHSI {
        double R;
        double G;
        double B;
        double H;
        double S;
        double I;
    }
    
}

