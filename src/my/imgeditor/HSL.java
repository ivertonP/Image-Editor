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
import static my.imgeditor.FramePrincipal.framePrincipal;

/**
 *
 * @author Iverton
 */
public class HSL {
    
    public static void RGBparaHSL(){        
        for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){                
                //VERSÃO ORIGINAL
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
    
    public static void RGBparaHSLSave(){        
        for(int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i ++){
            for(int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j ++){                
                //VERSÃO ORIGINAL
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                vetor = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vetor);
                estruturaTemporariaSave[i][j].H = vetor[0];
                estruturaTemporariaSave[i][j].S = vetor[1];
                estruturaTemporariaSave[i][j].L = vetor[2];
                
                //VERSÃO ORIGINAL
                c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                vetor = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), vetor);
                estruturaTemporariaCopiaSave[i][j].H = vetor[0];
                estruturaTemporariaCopiaSave[i][j].S = vetor[1];
                estruturaTemporariaCopiaSave[i][j].L = vetor[2];
            }            
        }
    }
           
    public static void HSLparaRGB(){        
    for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
        for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j ++){            
            //VERSÃO ORIGINAL
            int cor = Color.HSBtoRGB(estruturaTemporaria[i][j].H, estruturaTemporaria[i][j].S, estruturaTemporaria[i][j].L);
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
            framePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }            
        }
    }
    
    public static void HSLparaRGBSave(){        
    for(int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i ++){
        for(int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j ++){
            //VERSÃO ORIGINAL
            int cor = Color.HSBtoRGB(estruturaTemporariaSave[i][j].H, estruturaTemporariaSave[i][j].S, estruturaTemporariaSave[i][j].L);
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
            framePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }            
        }
    }
    
    public static BufferedImage Saturacao(int x){        
        float sat;
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for(int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i ++){
                for(int j = 0; j < FramePrincipal.imagemASerExibida.getWidth();j ++){                    
                    //VERSÃO ORIGINAL
                    sat = estruturaTemporariaCopia[i][j].S + y/100;
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
            framePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "sat";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage SaturacaoSave(int x){        
        float sat;
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{
            RGBparaHSLSave();
            for(int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i ++){
                for(int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth();j ++){                    
                    //VERSÃO ORIGINAL
                    sat = estruturaTemporariaCopiaSave[i][j].S + y/100;
                    if(sat < 0){
                        estruturaTemporaria[i][j].S = 0;
                    }
                    else{
                        if(sat > 1){
                            estruturaTemporariaSave[i][j].S = 1;
                        }
                        else{
                            estruturaTemporariaSave[i][j].S = sat;
                        }
                    }
                }
            }
            HSLparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
    }
    
    public static BufferedImage Matiz(int x){
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemCopia.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemCopia.getHeight(); i++) {
                    estruturaTemporaria[i][j].H = estruturaTemporariaCopia[i][j].H + y/360;
                }
            }
            HSLparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "hue";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage MatizSave(int x){
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{
            RGBparaHSLSave();
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    estruturaTemporariaSave[i][j].H = estruturaTemporariaCopiaSave[i][j].H + y/360;
                }
            }
            HSLparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
    }
    
    public static BufferedImage Luminancia(int x){        
        float lum;
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemCopia.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemCopia.getHeight(); i++) {
                    lum = estruturaTemporariaCopia[i][j].L + y/100;
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
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "lum";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage LuminanciaSave(int x){        
        float lum;
        float y = x;
        if(y == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            RGBparaHSLSave();
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    lum = estruturaTemporariaCopiaSave[i][j].L + y/100;
                    if(lum < 0){
                        estruturaTemporariaSave[i][j].L = 0;
                    }
                    else{
                        if(lum > 1){
                            estruturaTemporariaSave[i][j].L = 1;
                        }
                        else{
                            estruturaTemporariaSave[i][j].L = lum;
                        }
                    }
                }
            }
            HSLparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            return FramePrincipal.imagemASerSalvaEmDisco;
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
        
        for(int i = 0; i < FramePrincipal.arrayOperacoesTemp.size(); i++){
            eltoTemp = FramePrincipal.arrayOperacoesTemp.get(i);
            FramePrincipal.arrayOperacoesDefinit.add(eltoTemp);
            FramePrincipal.arrayOperacoesCopia.add(eltoTemp);
            System.out.println("\n------------------- Atualização dos arrays -------------------");
            for(int j = 0; j < FramePrincipal.arrayOperacoesDefinit.size(); j++){
               System.out.println("Array Definit[" + j + "]: " + FramePrincipal.arrayOperacoesDefinit.get(j).codOp + ", " + FramePrincipal.arrayOperacoesDefinit.get(j).valor);
            }
            for(int k = 0; k < FramePrincipal.arrayOperacoesCopia.size(); k++){
               System.out.println("Array Cópia[" + k + "]: " + FramePrincipal.arrayOperacoesCopia.get(k).codOp + ", " + FramePrincipal.arrayOperacoesCopia.get(k).valor);
            }
            System.out.println("\n");
        }
        FramePrincipal.arrayOperacoesTemp.clear();
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
        framePrincipal.foiAplicado = false;
        
        RGBparaHSL();
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
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
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
    }
    
    public static int IdHSL = 3;
    public static JLabel labelImagem;
    public static EstruturaHSL[][] estruturaTemporaria, estruturaTemporariaCopia;
    public static EstruturaHSL[][] estruturaTemporariaSave, estruturaTemporariaCopiaSave;
    public static float[] vetor = new float[3];
    public static FramePrincipal.Operacao eltoTemp = new FramePrincipal.Operacao();
    
    public static class EstruturaHSL {
        float R, G, B, H, S, L;
    }
    
}
