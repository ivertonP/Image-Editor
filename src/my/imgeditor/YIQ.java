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
public class YIQ {
    
    public static void RGBparaYIQ(){
        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                
                //VERSÃO ORIGINAL
                estruturaTemporaria[i][j].Y = Y;
                estruturaTemporaria[i][j].I = I;
                estruturaTemporaria[i][j].Q = Q;
                
                c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                
                //VERSÃO ORIGINAL
                estruturaTemporariaCopia[i][j].Y = Y;
                estruturaTemporariaCopia[i][j].I = I;
                estruturaTemporariaCopia[i][j].Q = Q;
            }
        }        
    }
    
    public static void RGBparaYIQSave(){
        
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {                
                //VERSÃO ORIGINAL
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaSave[i][j].Y = Y;
                estruturaTemporariaSave[i][j].I = I;
                estruturaTemporariaSave[i][j].Q = Q;
                
                //VERSÃO ORIGINAL
                c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaCopiaSave[i][j].Y = Y;
                estruturaTemporariaCopiaSave[i][j].I = I;
                estruturaTemporariaCopiaSave[i][j].Q = Q;
            }
        }        
    }
    
    public static void YIQparaRGB(){
        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {                
                //VERSÃO ORIGINAL
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
    
    public static void YIQparaRGBSave(){
        
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {                
                //VERSÃO ORIGINAL
                Color c;
                int R = (int) (estruturaTemporariaSave[i][j].Y + 0.9563 * estruturaTemporariaSave[i][j].I + 0.6210 * estruturaTemporariaSave[i][j].Q);
                int G = (int) (estruturaTemporariaSave[i][j].Y - 0.2721 * estruturaTemporariaSave[i][j].I - 0.6474 * estruturaTemporariaSave[i][j].Q);
                int B = (int) (estruturaTemporariaSave[i][j].Y - 1.1070 * estruturaTemporariaSave[i][j].I + 1.7046 * estruturaTemporariaSave[i][j].Q);              
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
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
    }
    
    public static BufferedImage ComponenteY(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].Y =  estruturaTemporariaCopia[i][j].Y + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "compy";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage ComponenteYSave(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{
            RGBparaYIQSave();
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    estruturaTemporariaSave[i][j].Y =  estruturaTemporariaCopiaSave[i][j].Y + x;
                }
            }
            YIQparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
        }
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage ComponenteI(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].I =  estruturaTemporariaCopia[i][j].I + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "compi";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage ComponenteISave(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{
            RGBparaYIQSave();
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    estruturaTemporariaSave[i][j].I =  estruturaTemporariaCopiaSave[i][j].I + x;
                }
            }
            YIQparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
    }
    
    public static BufferedImage ComponenteQ(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerExibida;
        }
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    estruturaTemporaria[i][j].Q =  estruturaTemporariaCopia[i][j].Q + x;
                }
            }
            YIQparaRGB();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "compq";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage ComponenteQSave(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{
            RGBparaYIQSave();
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    estruturaTemporariaSave[i][j].Q =  estruturaTemporariaCopiaSave[i][j].Q + x;
                }
            }
            YIQparaRGBSave();
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            framePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
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
        framePrincipal.foiAplicado = false;
        
        RGBparaYIQ();
        
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
        
        RGBparaYIQ();
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
    }
    
    public static int IdYIQ = 4;
    public static JLabel labelImagem;
    public static EstruturaYIQ[][] estruturaTemporaria, estruturaTemporariaCopia;
    public static EstruturaYIQ[][] estruturaTemporariaSave, estruturaTemporariaCopiaSave;
    public static FramePrincipal.Operacao eltoTemp = new FramePrincipal.Operacao();
    
    public static class EstruturaYIQ {            //Estrutura utilizada para armazenar os valores RGB convertidos em YIQ e as incidencias dos valores de Y.
        int Y, I, Q;
    }
    
}
