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
        //O metodo TonsDeCinzaSDGM() converte a imagem de entrada em uma imagem representada em escala de cinza.        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int cinza = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                c = new Color(cinza, cinza, cinza);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        
        FramePrincipal.vermelhoCinza = true;
        FramePrincipal.verdeCinza = true;
        FramePrincipal.azulCinza = true;
        return FramePrincipal.imagemASerExibida;
    }
    
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
        FramePrincipal.foiAplicado = true;
        FramePrincipal.vermelhoCinza = true;
        FramePrincipal.verdeCinza = true;
        FramePrincipal.azulCinza = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "cinzageral";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage TomDeCinzaSave(){        
        //O metodo TonsDeCinza() converte a imagem de entrada em uma imagem representada em escala de cinza.        
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int cinza = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                c = new Color(cinza, cinza, cinza);
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Temperatura(int x){
        double r, g, b;
        int R, G, B;
        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            FramePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{            
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    if(x < 0){
                        r = c.getRed() + x/1.5;
                        g = c.getGreen() + x/2.0;
                        b = c.getBlue() - x/1.5;
                        R = (int) r;
                        G = (int) g;
                        B = (int) b;
                    }
                    else{
                        r = c.getRed() + x/1.5;
                        g = c.getGreen() + x/1.5;
                        b = c.getBlue();
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
                    FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                }
            }
            
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            FramePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "temp";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage TemperaturaSave(int x){
        double r, g, b;
        int R, G, B;
        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            FramePrincipal.foiAplicado = true;
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{            
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    if(x < 0){
                        r = c.getRed() + x/1.5;
                        g = c.getGreen() + x/2.0;
                        b = c.getBlue() - x/1.5;
                        R = (int) r;
                        G = (int) g;
                        B = (int) b;
                    }
                    else{
                        r = c.getRed() + x/1.5;
                        g = c.getGreen() + x/1.5;
                        b = c.getBlue();
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
                    FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                }
            }
            
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
    }
    
    public static BufferedImage VermelhoColorido(int x) {        
        //O metodo VermelhoColorido() converte a imagem de entrada em uma imagem representada apenas pela componente Red com seus valores originais.
        if ((FramePrincipal.verdeCinza == true) || (FramePrincipal.azulCinza == true)){}
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    Color corDaCopia = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                    int R = corDaCopia.getRed() + x;
                    if(R > 255){
                        R = 255;
                    }
                    if(R < 0){
                        R = 0;
                    }
                    corDaCopia = new Color(R, corDaImagemASerExibida.getGreen(), corDaImagemASerExibida.getBlue());
                    FramePrincipal.imagemASerExibida.setRGB(j, i, corDaCopia.getRGB());
                }
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = x;
        elto.codOp = "vermelhocol";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage VermelhoColoridoSave(int x) {
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color corDaCopia = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int R = corDaCopia.getRed() + x;
                if(R > 255){
                    R = 255;
                }
                if(R < 0){
                    R = 0;
                }
                corDaCopia = new Color(R, corDaImagemASerExibida.getGreen(), corDaImagemASerExibida.getBlue());
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, corDaCopia.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage VerdeColorido(int x){        
        //O metodo VerdeColorido() converte a imagem de entrada em uma imagem representada apenas pela componente Green com seus valores originais.
        if ((FramePrincipal.vermelhoCinza == true) || (FramePrincipal.azulCinza == true)){}
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    Color corDaCopia = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                    int G = corDaCopia.getGreen() + x;
                    if(G > 255){
                        G = 255;
                    }
                    if(G < 0){
                        G = 0;
                    }
                    corDaCopia = new Color(corDaImagemASerExibida.getRed(), G, corDaImagemASerExibida.getBlue());
                    FramePrincipal.imagemASerExibida.setRGB(j, i, corDaCopia.getRGB());
                }
            }
            
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            FramePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "verdecol";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        }
        
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage VerdeColoridoSave(int x){
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color corDaCopia = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int G = corDaCopia.getGreen() + x;
                if(G > 255){
                    G = 255;
                }
                if(G < 0){
                    G = 0;
                }
                corDaCopia = new Color(corDaImagemASerExibida.getRed(), G, corDaImagemASerExibida.getBlue());
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, corDaCopia.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage AzulColorido(int x){        
        //O metodo AzulColorido() converte a imagem de entrada em uma imagem representada apenas pela componente Bluee com seus valores originais.
        if ((FramePrincipal.vermelhoCinza == true) || (FramePrincipal.verdeCinza == true)){}
        else{
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    Color corDaCopia = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                    int B = corDaCopia.getBlue() + x;
                    if(B > 255){
                        B = 255;
                    }
                    if(B < 0){
                        B = 0;
                    }
                    corDaCopia = new Color(corDaImagemASerExibida.getRed(), corDaImagemASerExibida.getGreen(), B);
                    FramePrincipal.imagemASerExibida.setRGB(j, i, corDaCopia.getRGB());
                }
            }
            
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
            FramePrincipal.foiAplicado = true;
            FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
            elto.valor = x;
            elto.codOp = "azulcol";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        }
        
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage AzulColoridoSave(int x){
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color corDaCopia = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                Color corDaImagemASerExibida = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int B = corDaCopia.getBlue() + x;
                if(B > 255){
                    B = 255;
                }
                if(B < 0){
                    B = 0;
                }
                corDaCopia = new Color(corDaImagemASerExibida.getRed(), corDaImagemASerExibida.getGreen(), B);
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, corDaCopia.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage VermelhoCinza(){
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                c = new Color(c.getRed(), c.getRed(), c.getRed());
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        
        FramePrincipal.vermelhoCinza = true;
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "vermelhocinza";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage VermelhoCinzaSave(){
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                c = new Color(c.getRed(), c.getRed(), c.getRed());
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage VerdeCinza(){
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                c = new Color(c.getGreen(), c.getGreen(), c.getGreen());
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        
        FramePrincipal.verdeCinza = true;
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "verdecinza";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage VerdeCinzaSave(){
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                c = new Color(c.getGreen(), c.getGreen(), c.getGreen());
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage AzulCinza(){
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                c = new Color(c.getBlue(), c.getBlue(), c.getBlue());
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        
        FramePrincipal.azulCinza = true;
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "azulcinza";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage AzulCinzaSave(){
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                c = new Color(c.getBlue(), c.getBlue(), c.getBlue());
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Negativo(){        
        //O metodo Negativo() calcula as componentes complementares de cor da imagem original.
        //Este calculo resulta em uma imagem com cores invertidas de acordo com a imagem de entrada.        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int R = 255 - c.getRed();
                int G = 255 - c.getGreen();
                int B = 255 - c.getBlue();
                c = new Color(R, G, B);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "negativo";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage NegativoSave(){        
        //O metodo Negativo() calcula as componentes complementares de cor da imagem original.
        //Este calculo resulta em uma imagem com cores invertidas de acordo com a imagem de entrada.        
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int R = 255 - c.getRed();
                int G = 255 - c.getGreen();
                int B = 255 - c.getBlue();
                c = new Color(R, G, B);
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Expansao(){        
         //O metodo Expansao() expande o histograma da imagem de entrada.
         //Distribui os valores das componentes de cor de forma que haja uma melhora significativa na qualidade visual da imagem de saída. 
         //Esta técnica funciona bem quando a imagem de entrada possui uma faixa reduzida de níveis de cinza.
         //Caso a imagem de entrada apresente cores no valor mínimo ou no valor máximo da distribuição,
         //a expansão tende a piorar o resultado pelo fato de que ela “desloca” todo o histograma para valores próximo a esses valores.        
        EstruturaYIQ[][] estruturaTemporariaYIQ = new EstruturaYIQ[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];
        double maxY = 0;
        double minY;
        //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
        //e armazenadas na estrutura auxiliar estruturaTemporariaYIQ[][].        
        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                estruturaTemporariaYIQ[i][j] = new EstruturaYIQ();
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaYIQ[i][j].Y = Y;
                estruturaTemporariaYIQ[i][j].I = I;
                estruturaTemporariaYIQ[i][j].Q = Q;
            }
        }        

        minY = estruturaTemporariaYIQ[0][0].Y;
        //Este laco For() armazena o menor valor de estruturaTemporariaYIQ[][] em minY e o maior valor em maxY.        
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                if (estruturaTemporariaYIQ[i][j].Y > maxY) {
                    maxY = estruturaTemporariaYIQ[i][j].Y;
                } else {
                    if (estruturaTemporariaYIQ[i][j].Y <= minY) {
                        minY = estruturaTemporariaYIQ[i][j].Y;
                    }
                }
            }
        }
        //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
        //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c;
                estruturaTemporariaYIQ[i][j].Y = (int) (((estruturaTemporariaYIQ[i][j].Y - minY) / (maxY - minY)) * 255.0);
                int R = (int) (estruturaTemporariaYIQ[i][j].Y + 0.9563 * estruturaTemporariaYIQ[i][j].I + 0.6210 * estruturaTemporariaYIQ[i][j].Q);
                int G = (int) (estruturaTemporariaYIQ[i][j].Y - 0.2721 * estruturaTemporariaYIQ[i][j].I - 0.6474 * estruturaTemporariaYIQ[i][j].Q);
                int B = (int) (estruturaTemporariaYIQ[i][j].Y - 1.1070 * estruturaTemporariaYIQ[i][j].I + 1.7046 * estruturaTemporariaYIQ[i][j].Q);

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
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "expansao";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage ExpansaoSave(){        
         //O metodo Expansao() expande o histograma da imagem de entrada.
         //Distribui os valores das componentes de cor de forma que haja uma melhora significativa na qualidade visual da imagem de saída. 
         //Esta técnica funciona bem quando a imagem de entrada possui uma faixa reduzida de níveis de cinza.
         //Caso a imagem de entrada apresente cores no valor mínimo ou no valor máximo da distribuição,
         //a expansão tende a piorar o resultado pelo fato de que ela “desloca” todo o histograma para valores próximo a esses valores.        
        EstruturaYIQ[][] estruturaTemporariaYIQ = new EstruturaYIQ[FramePrincipal.imagemASerSalvaEmDisco.getHeight()][FramePrincipal.imagemASerSalvaEmDisco.getWidth()];
        double maxY = 0;
        double minY;
        //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
        //e armazenadas na estrutura auxiliar estruturaTemporariaYIQ[][].        
        for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                estruturaTemporariaYIQ[i][j] = new EstruturaYIQ();
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaYIQ[i][j].Y = Y;
                estruturaTemporariaYIQ[i][j].I = I;
                estruturaTemporariaYIQ[i][j].Q = Q;
            }
        }        

        minY = estruturaTemporariaYIQ[0][0].Y;
        //Este laco For() armazena o menor valor de estruturaTemporariaYIQ[][] em minY e o maior valor em maxY.        
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                if (estruturaTemporariaYIQ[i][j].Y > maxY) {
                    maxY = estruturaTemporariaYIQ[i][j].Y;
                } else {
                    if (estruturaTemporariaYIQ[i][j].Y <= minY) {
                        minY = estruturaTemporariaYIQ[i][j].Y;
                    }
                }
            }
        }
        //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
        //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c;
                estruturaTemporariaYIQ[i][j].Y = (int) (((estruturaTemporariaYIQ[i][j].Y - minY) / (maxY - minY)) * 255.0);
                int R = (int) (estruturaTemporariaYIQ[i][j].Y + 0.9563 * estruturaTemporariaYIQ[i][j].I + 0.6210 * estruturaTemporariaYIQ[i][j].Q);
                int G = (int) (estruturaTemporariaYIQ[i][j].Y - 0.2721 * estruturaTemporariaYIQ[i][j].I - 0.6474 * estruturaTemporariaYIQ[i][j].Q);
                int B = (int) (estruturaTemporariaYIQ[i][j].Y - 1.1070 * estruturaTemporariaYIQ[i][j].I + 1.7046 * estruturaTemporariaYIQ[i][j].Q);

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
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Equalizacao(){
         //O metodo Equalizacao() contorna a deficiência presente na técnica de expansão de histograma.
         //A equalizacao do histograma permite que os níveis de cinza da imagem sejam equalizados o máximo possível.
         //O histograma gerado fica tão uniforme quanto a fórmula permite. Entretanto, nao se recomenda sua utilizacao
         //quando os valores das cores se aproximam do valor mínimo ou do valor máximo da escala de cores presentes na imagem de entrada.
         //Isto se dá pelo fato de que a equalização mapeia um valor próximo aos limites da escala para o valor destes limites,
         //resultando em mudanças bruscas nas tonalidades das cores onde deveria haver uma mudança possivelmente mais sutil.        
        int tam = FramePrincipal.imagemASerExibida.getWidth() * FramePrincipal.imagemASerExibida.getHeight();
        EstruturaYIQ[][] estruturaTemporariaYIQ = new EstruturaYIQ[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];
        EstruturaYIQ[] estruturaTemporariaAuxiliarYIQ = new EstruturaYIQ[256];
        int somaY = 0;

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                estruturaTemporariaAuxiliarYIQ[i] = new EstruturaYIQ();
                estruturaTemporariaAuxiliarYIQ[i].incidY = 0;
                estruturaTemporariaAuxiliarYIQ[i].somaY = 0;
            }
        }
        //Este laco For() aninhado converte em YIQ os valores RGB originais das componentes de cor de cada pixel,
        //armazenando em estruturaTemporariaAuxiliarYIQ[].incidY a incidencia dos valores de Y.
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                estruturaTemporariaYIQ[i][j] = new EstruturaYIQ();
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaYIQ[i][j].Y = Y;
                estruturaTemporariaYIQ[i][j].I = I;
                estruturaTemporariaYIQ[i][j].Q = Q;
                estruturaTemporariaAuxiliarYIQ[Y].Y = Y;
                estruturaTemporariaAuxiliarYIQ[Y].incidY++;
            }
        }

        for (int i = 0; i < 256; i++) {
            somaY = somaY + estruturaTemporariaAuxiliarYIQ[i].incidY;
            estruturaTemporariaAuxiliarYIQ[i].somaY = somaY;
        }
        //Este laco For() aninhado aplica a formula da equalizacao apenas na componente Y dos pixels,
        //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {                
                Color c;
                estruturaTemporariaYIQ[i][j].Y = (int) Math.round((255.0 / tam) * estruturaTemporariaAuxiliarYIQ[estruturaTemporariaYIQ[i][j].Y].somaY);
                int R = (int) (estruturaTemporariaYIQ[i][j].Y + 0.9563 * estruturaTemporariaYIQ[i][j].I + 0.6210 * estruturaTemporariaYIQ[i][j].Q);
                int G = (int) (estruturaTemporariaYIQ[i][j].Y - 0.2721 * estruturaTemporariaYIQ[i][j].I - 0.6474 * estruturaTemporariaYIQ[i][j].Q);
                int B = (int) (estruturaTemporariaYIQ[i][j].Y - 1.1070 * estruturaTemporariaYIQ[i][j].I + 1.7046 * estruturaTemporariaYIQ[i][j].Q);

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
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "equalizacao";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage EqualizacaoSave(){
         //O metodo Equalizacao() contorna a deficiência presente na técnica de expansão de histograma.
         //A equalizacao do histograma permite que os níveis de cinza da imagem sejam equalizados o máximo possível.
         //O histograma gerado fica tão uniforme quanto a fórmula permite. Entretanto, nao se recomenda sua utilizacao
         //quando os valores das cores se aproximam do valor mínimo ou do valor máximo da escala de cores presentes na imagem de entrada.
         //Isto se dá pelo fato de que a equalização mapeia um valor próximo aos limites da escala para o valor destes limites,
         //resultando em mudanças bruscas nas tonalidades das cores onde deveria haver uma mudança possivelmente mais sutil.        
        int tam = FramePrincipal.imagemASerSalvaEmDisco.getWidth() * FramePrincipal.imagemASerSalvaEmDisco.getHeight();
        EstruturaYIQ[][] estruturaTemporariaYIQ = new EstruturaYIQ[FramePrincipal.imagemASerSalvaEmDisco.getHeight()][FramePrincipal.imagemASerSalvaEmDisco.getWidth()];
        EstruturaYIQ[] estruturaTemporariaAuxiliarYIQ = new EstruturaYIQ[256];
        int somaY = 0;

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                estruturaTemporariaAuxiliarYIQ[i] = new EstruturaYIQ();
                estruturaTemporariaAuxiliarYIQ[i].incidY = 0;
                estruturaTemporariaAuxiliarYIQ[i].somaY = 0;
            }
        }
        //Este laco For() aninhado converte em YIQ os valores RGB originais das componentes de cor de cada pixel,
        //armazenando em estruturaTemporariaAuxiliarYIQ[].incidY a incidencia dos valores de Y.
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                estruturaTemporariaYIQ[i][j] = new EstruturaYIQ();
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                estruturaTemporariaYIQ[i][j].Y = Y;
                estruturaTemporariaYIQ[i][j].I = I;
                estruturaTemporariaYIQ[i][j].Q = Q;
                estruturaTemporariaAuxiliarYIQ[Y].Y = Y;
                estruturaTemporariaAuxiliarYIQ[Y].incidY++;
            }
        }

        for (int i = 0; i < 256; i++) {
            somaY = somaY + estruturaTemporariaAuxiliarYIQ[i].incidY;
            estruturaTemporariaAuxiliarYIQ[i].somaY = somaY;
        }
        //Este laco For() aninhado aplica a formula da equalizacao apenas na componente Y dos pixels,
        //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {                
                Color c;
                estruturaTemporariaYIQ[i][j].Y = (int) Math.round((255.0 / tam) * estruturaTemporariaAuxiliarYIQ[estruturaTemporariaYIQ[i][j].Y].somaY);
                int R = (int) (estruturaTemporariaYIQ[i][j].Y + 0.9563 * estruturaTemporariaYIQ[i][j].I + 0.6210 * estruturaTemporariaYIQ[i][j].Q);
                int G = (int) (estruturaTemporariaYIQ[i][j].Y - 0.2721 * estruturaTemporariaYIQ[i][j].I - 0.6474 * estruturaTemporariaYIQ[i][j].Q);
                int B = (int) (estruturaTemporariaYIQ[i][j].Y - 1.1070 * estruturaTemporariaYIQ[i][j].I + 1.7046 * estruturaTemporariaYIQ[i][j].Q);

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
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    public static void AplicarColor(){
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
        
        for(int i = 0; i < FramePrincipal.arrayOperacoesTemp.size(); i++){
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("vermelhocol")){
                eltoTempVermelho = FramePrincipal.arrayOperacoesTemp.get(i);
            }
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("verdecol")){
                eltoTempVerde = FramePrincipal.arrayOperacoesTemp.get(i);
            }
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("azulcol")){
                eltoTempAzul = FramePrincipal.arrayOperacoesTemp.get(i);
            }
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("temp")){
                eltoTempTemper = FramePrincipal.arrayOperacoesTemp.get(i);
            }
        }
        
        for(int i = 0; i < FramePrincipal.arrayOperacoesTemp.size(); i++){
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("vermelhocol") && e.valor != eltoTempVermelho.valor));
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("verdecol") && e.valor != eltoTempVerde.valor));
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("azulcol") && e.valor != eltoTempAzul.valor));
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("temp") && e.valor != eltoTempTemper.valor));
        }
        
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
        
    public static void reverterColor(){        
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
        FramePrincipal.vermelhoCinza = false;
        FramePrincipal.verdeCinza = false;
        FramePrincipal.azulCinza = false;
        FramePrincipal.foiAplicado = false;
        
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
        FramePrincipal.vermelhoCinza = false;
        FramePrincipal.verdeCinza = false;
        FramePrincipal.azulCinza = false;
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
    }
    
    public static int IdRGB = 0;
    public static JLabel labelImagem;
    public static FramePrincipal.Operacao eltoTemp = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempVermelho = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempVerde = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempAzul = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempTemper = new FramePrincipal.Operacao();
    
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