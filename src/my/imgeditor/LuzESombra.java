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
public class LuzESombra {
    
    public static BufferedImage Brilho(int x){
        //O metodo Brilho() (transformada Logaritmica) exibe uma imagem de saída com uma faixa de valores de escala de cinza maior que a imagem de entrada.
        //Isto permite que a visibilidade da imagem de saida seja maior onde havia tons muitos escuros.
        int constante;
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            return FramePrincipal.imagemCopia;
        }
        else{
            if(x < 0){
                EstruturaYIQ[][] aux = new EstruturaYIQ[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];
                double maxY = 0;
                double minY;
                //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
                //e armazenadas na estrutura auxiliar EstruturaYIQ aux[][].        
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                        aux[i][j] = new EstruturaYIQ();
                        Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                        int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                        int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                        int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                        aux[i][j].Y = Y;
                        aux[i][j].I = I;
                        aux[i][j].Q = Q;
                    }
                }        

                minY = aux[0][0].Y;
                //Este laco For() armazena o menor valor de aux[][] em minY e o maior valor de aux[][] em maxY.        
                for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                        if (aux[i][j].Y > maxY) {
                            maxY = aux[i][j].Y;
                        } else {
                            if (aux[i][j].Y <= minY) {
                                minY = aux[i][j].Y;
                            }
                        }
                    }
                }
                //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
                //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
                for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                        Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                        aux[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
                        int R = (int) (aux[i][j].Y + 0.9563 * aux[i][j].I + 0.6210 * aux[i][j].Q);
                        int G = (int) (aux[i][j].Y - 0.2721 * aux[i][j].I - 0.6474 * aux[i][j].Q);
                        int B = (int) (aux[i][j].Y - 1.1070 * aux[i][j].I + 1.7046 * aux[i][j].Q);
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
            else{
                constante = x + 355;
                for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                        Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                        double r = c.getRed()/255.0;
                        double g = c.getGreen()/255.0;
                        double b = c.getBlue()/255.0;
                        int R = (int) (constante * Math.log(1 + r));
                        int G = (int) (constante * Math.log(1 + g));
                        int B = (int) (constante * Math.log(1 + b));
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
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                }
            }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = x;
        elto.codOp = "brilho";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage BrilhoSave(int x){
        //O metodo Brilho() (transformada Logaritmica) exibe uma imagem de saída com uma faixa de valores de escala de cinza maior que a imagem de entrada.
        //Isto permite que a visibilidade da imagem de saida seja maior onde havia tons muitos escuros.
        int constante;
        if(x < 0){
            EstruturaYIQ[][] aux = new EstruturaYIQ[FramePrincipal.imagemASerSalvaEmDisco.getHeight()][FramePrincipal.imagemASerSalvaEmDisco.getWidth()];
            double maxY = 0;
            double minY;
            //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
            //e armazenadas na estrutura auxiliar EstruturaYIQ aux[][].        
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                    aux[i][j] = new EstruturaYIQ();
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                    int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                    int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                    aux[i][j].Y = Y;
                    aux[i][j].I = I;
                    aux[i][j].Q = Q;
                }
            }        

            minY = aux[0][0].Y;
            //Este laco For() armazena o menor valor de aux[][] em minY e o maior valor de aux[][] em maxY.        
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    if (aux[i][j].Y > maxY) {
                        maxY = aux[i][j].Y;
                    } else {
                        if (aux[i][j].Y <= minY) {
                            minY = aux[i][j].Y;
                        }
                    }
                }
            }
            //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
            //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    aux[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
                    int R = (int) (aux[i][j].Y + 0.9563 * aux[i][j].I + 0.6210 * aux[i][j].Q);
                    int G = (int) (aux[i][j].Y - 0.2721 * aux[i][j].I - 0.6474 * aux[i][j].Q);
                    int B = (int) (aux[i][j].Y - 1.1070 * aux[i][j].I + 1.7046 * aux[i][j].Q);
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
        else{
            constante = x + 355;
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    double r = c.getRed()/255.0;
                    double g = c.getGreen()/255.0;
                    double b = c.getBlue()/255.0;
                    int R = (int) (constante * Math.log(1 + r));
                    int G = (int) (constante * Math.log(1 + g));
                    int B = (int) (constante * Math.log(1 + b));
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
                    FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                }
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Gamma(double x){        
        //O metodo Gamma() mapeia uma faixa de valores próximos do preto para uma faixa mais espessa na imagem de saída.
        //Ao inserir os valores para o expoente da fórmula, o usuário pode observar os resultados obtidos e buscar a imagem
        //que mais se adeque a sua necessidade de detalhamento, podendo escurecer ou clarear a imagem de saída.
        //Para valores maiores que 1.0, o resultado é uma imagem mais clara. Para valores menores que 1.0, o resultado é uma imagem mais escura.
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
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
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
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        FramePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = (int)x;
        elto.codOp = "gamma";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return FramePrincipal.imagemASerExibida;
    }
    
    public static BufferedImage GammaSave(double x){        
        //O metodo Gamma() mapeia uma faixa de valores próximos do preto para uma faixa mais espessa na imagem de saída.
        //Ao inserir os valores para o expoente da fórmula, o usuário pode observar os resultados obtidos e buscar a imagem
        //que mais se adeque a sua necessidade de detalhamento, podendo escurecer ou clarear a imagem de saída.
        //Para valores maiores que 1.0, o resultado é uma imagem mais clara. Para valores menores que 1.0, o resultado é uma imagem mais escura.
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
        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
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
                FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage Luminosidade(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            FramePrincipal.foiAplicado = true;
            return FramePrincipal.imagemCopia;
        }
        else{        
            EstruturaYIQ[][] aux = new EstruturaYIQ[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];
            double maxY = 0;
            double minY;
            //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
            //e armazenadas em uma estrutura auxiliar EstruturaYIQ aux[][].        
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    aux[i][j] = new EstruturaYIQ();
                    Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                    int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                    int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                    aux[i][j].Y = Y;
                    aux[i][j].I = I;
                    aux[i][j].Q = Q;
                }
            }        

            minY = aux[0][0].Y;
            //Este laco For() armazena o menor valor de aux[][] em minY e o maior valor em maxY.        
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    if (aux[i][j].Y > maxY) {
                        maxY = aux[i][j].Y;
                    } else {
                        if (aux[i][j].Y <= minY) {
                            minY = aux[i][j].Y;
                        }
                    }
                }
            }            
            //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
            //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                    Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                    aux[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
                    int R = (int) (aux[i][j].Y + 0.9563 * aux[i][j].I + 0.6210 * aux[i][j].Q);
                    int G = (int) (aux[i][j].Y - 0.2721 * aux[i][j].I - 0.6474 * aux[i][j].Q);
                    int B = (int) (aux[i][j].Y - 1.1070 * aux[i][j].I + 1.7046 * aux[i][j].Q);

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
            elto.valor = (int)x;
            elto.codOp = "luminosidade";
            FramePrincipal.arrayOperacoesTemp.add(elto);
            System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
            return FramePrincipal.imagemASerExibida;
        }
    }
    
    public static BufferedImage LuminosidadeSave(int x){        
        if(x == 0){
            labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemCopia));
            return FramePrincipal.imagemASerSalvaEmDisco;
        }
        else{        
            EstruturaYIQ[][] aux = new EstruturaYIQ[FramePrincipal.imagemASerSalvaEmDisco.getHeight()][FramePrincipal.imagemASerSalvaEmDisco.getWidth()];
            double maxY = 0;
            double minY;
            //Neste laco For(), as componentes de cor de cada pixel são convertidas em componentes YIQ
            //e armazenadas em uma estrutura auxiliar EstruturaYIQ aux[][].        
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                    aux[i][j] = new EstruturaYIQ();
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    int Y = (int) (0.299 * c.getRed() + (0.587 * c.getGreen()) + (0.114 * c.getBlue()));
                    int I = (int) (0.596 * c.getRed() - (0.274 * c.getGreen()) - (0.322 * c.getBlue()));
                    int Q = (int) (0.211 * c.getRed() - (0.523 * c.getGreen()) + (0.312 * c.getBlue()));
                    aux[i][j].Y = Y;
                    aux[i][j].I = I;
                    aux[i][j].Q = Q;
                }
            }        

            minY = aux[0][0].Y;
            //Este laco For() armazena o menor valor de aux[][] em minY e o maior valor em maxY.        
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    if (aux[i][j].Y > maxY) {
                        maxY = aux[i][j].Y;
                    } else {
                        if (aux[i][j].Y <= minY) {
                            minY = aux[i][j].Y;
                        }
                    }
                }
            }            
            //Este For() aninhado aplica a formula da expansão apenas na componente Y dos pixels,
            //convertendo, depois, todas as componentes YIQ de volta para seus valores originais em RGB.
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                    Color c = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j, i));
                    aux[i][j].Y = (int) (0.2126*(c.getRed()+x) + 0.7152*(c.getGreen()+x) + 0.0722*(c.getBlue()+x));
                    int R = (int) (aux[i][j].Y + 0.9563 * aux[i][j].I + 0.6210 * aux[i][j].Q);
                    int G = (int) (aux[i][j].Y - 0.2721 * aux[i][j].I - 0.6474 * aux[i][j].Q);
                    int B = (int) (aux[i][j].Y - 1.1070 * aux[i][j].I + 1.7046 * aux[i][j].Q);

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
    }
    
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    public static void AplicarLuzESombra(){
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
        
        //Aramazena nos elementos de cada operação os últimos valores das operações correspondentes inseridos em FramePrincipal.arrayOperacoesTemp
        for(int i = 0; i < FramePrincipal.arrayOperacoesTemp.size(); i++){
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("brilho")){
                eltoTempBrilho = FramePrincipal.arrayOperacoesTemp.get(i);
            }
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("luminosidade")){
                eltoTempLuminosidade = FramePrincipal.arrayOperacoesTemp.get(i);
            }
            if(FramePrincipal.arrayOperacoesTemp.get(i).codOp.equals("gamma")){
                eltoTempGama = FramePrincipal.arrayOperacoesTemp.get(i);
            }
        }
        
        //Remove de FramePrincipal.arrayOperacoesTemp todas os valores inseridos que não sejam iguais ao último valor de cada operação
        for(int i = 0; i < FramePrincipal.arrayOperacoesTemp.size(); i++){
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("brilho") && e.valor != eltoTempBrilho.valor));
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("luminosidade") && e.valor != eltoTempLuminosidade.valor));
            FramePrincipal.arrayOperacoesTemp.removeIf(e -> (e.codOp.equals("gamma") && e.valor != eltoTempGama.valor));
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
    
    public static void reverterLuzESombra(){        
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
        FramePrincipal.foiAplicado = false;
        
        HSL.RGBparaHSL();
        
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
        
        HSL.RGBparaHSL();
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
    }
    
    public static JLabel labelImagem;
    public static int IdLuzESombra = 2;
    public static FramePrincipal.Operacao eltoTemp = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempBrilho = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempLuminosidade = new FramePrincipal.Operacao();
    public static FramePrincipal.Operacao eltoTempGama = new FramePrincipal.Operacao();
    
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
