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
import javax.swing.JOptionPane;
import static my.imgeditor.FramePrincipal.framePrincipal;

/**
 *
 * @author Iverton Perboyre L. Maia
 */
public class Morfologicas {
   
    public static BufferedImage Rotacao(int x){        
        
        boolean[][] matrizMapeamentoImagemASerExibida = new boolean[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];
        int centroLinha = FramePrincipal.imagemASerExibida.getHeight()/2;
        int centroColuna = FramePrincipal.imagemASerExibida.getWidth()/2;

        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                matrizMapeamentoImagemASerExibida[i][j] = false;
                Color c = new Color(0, 51, 102);
                FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }
    
        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int novaLinha = (int) Math.round((i - centroLinha) * Math.cos(Math.toRadians(x)) - (j - centroColuna) * Math.sin(Math.toRadians(x)) + centroLinha);
                int novaColuna = (int) Math.round((i - centroLinha) * Math.sin(Math.toRadians(x)) + (j - centroColuna) * Math.cos(Math.toRadians(x)) + centroColuna);
                if (novaLinha >= 0 && novaLinha < FramePrincipal.imagemASerExibida.getHeight() && novaColuna >= 0 && novaColuna < FramePrincipal.imagemASerExibida.getWidth()) {
                    FramePrincipal.imagemASerExibida.setRGB(novaColuna, novaLinha, c.getRGB());
                    matrizMapeamentoImagemASerExibida[novaLinha][novaColuna] = true;
                }
            }
        }

        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                Color c;
                if(matrizMapeamentoImagemASerExibida[i][j] == false){                    
                    if((j == 0 && i <= FramePrincipal.imagemASerExibida.getHeight() - 1)){
                        Color corADireita = new Color(FramePrincipal.imagemASerExibida.getRGB(j + 1, i));
                        c = new Color(corADireita.getRed(), corADireita.getGreen(), corADireita.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                    else if(j == FramePrincipal.imagemASerExibida.getWidth() - 1 && i <= FramePrincipal.imagemASerExibida.getHeight() - 1){
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerExibida.getRGB(j - 1, i));
                        c = new Color(corAEsquerda.getRed(), corAEsquerda.getGreen(), corAEsquerda.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                    else{
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerExibida.getRGB(j - 1, i));
                        Color corADireita = new Color(FramePrincipal.imagemASerExibida.getRGB(j + 1, i));
                        int R = (corAEsquerda.getRed() + corADireita.getRed()) / 2;
                        int G = (corAEsquerda.getGreen() + corADireita.getGreen()) / 2;
                        int B = (corAEsquerda.getBlue() + corADireita.getBlue()) / 2;
                        c = new Color(R, G, B);
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        framePrincipal.foiAplicado = true;
        return imagem;
    }
    
    public static BufferedImage Rotacao90(){
        soma = soma + 90;
        int centroLinha = FramePrincipal.imagemASerExibida.getHeight()/2;
        int centroColuna = FramePrincipal.imagemASerExibida.getWidth()/2;
        boolean[][] matrizMapeamentoImagemASerExibida = new boolean[FramePrincipal.imagemASerExibida.getHeight()][FramePrincipal.imagemASerExibida.getWidth()];

        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                    matrizMapeamentoImagemASerExibida[i][j] = false;
                    Color c = new Color(0, 51, 102);
                    FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
            }
        }

        for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCopia.getRGB(j, i));
                int novaLinha = (int) Math.round(centroLinha + (i - centroLinha) * Math.cos(Math.toRadians(soma)) - (j - centroColuna) * Math.sin(Math.toRadians(soma)));
                int novaColuna = (int) Math.round(centroColuna + (i - centroLinha) * Math.sin(Math.toRadians(soma)) + (j - centroColuna) * Math.cos(Math.toRadians(soma)));
                if (novaLinha >= 0 && novaLinha < FramePrincipal.imagemASerExibida.getHeight() && novaColuna >= 0 && novaColuna < FramePrincipal.imagemASerExibida.getWidth()) {
                    FramePrincipal.imagemASerExibida.setRGB(novaColuna, novaLinha, c.getRGB());
                    matrizMapeamentoImagemASerExibida[novaLinha][novaColuna] = true;
                }
            }
        }
        
        for (int i = 0; i < FramePrincipal.imagemASerExibida.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerExibida.getWidth(); j++) {
                Color c;
                if(matrizMapeamentoImagemASerExibida[i][j] == false){                    
                    if((j == 0 && i <= FramePrincipal.imagemASerExibida.getHeight() - 1)){
                        Color corADireita = new Color(FramePrincipal.imagemASerExibida.getRGB(j + 1, i));
                        c = new Color(corADireita.getRed(), corADireita.getGreen(), corADireita.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                    else if(j == FramePrincipal.imagemASerExibida.getWidth() - 1 && i <= FramePrincipal.imagemASerExibida.getHeight() - 1){
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerExibida.getRGB(j - 1, i));
                        c = new Color(corAEsquerda.getRed(), corAEsquerda.getGreen(), corAEsquerda.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                    else{
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerExibida.getRGB(j - 1, i));
                        Color corADireita = new Color(FramePrincipal.imagemASerExibida.getRGB(j + 1, i));
                        int R = (corAEsquerda.getRed() + corADireita.getRed()) / 2;
                        int G = (corAEsquerda.getGreen() + corADireita.getGreen()) / 2;
                        int B = (corAEsquerda.getBlue() + corADireita.getBlue()) / 2;
                        c = new Color(R, G, B);
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerExibida.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        framePrincipal.foiAplicado = true;
        FramePrincipal.Operacao elto = new FramePrincipal.Operacao();
        elto.valor = 0;
        elto.codOp = "rotacao";
        FramePrincipal.arrayOperacoesTemp.add(elto);
        System.out.println(FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).codOp + ", " + FramePrincipal.arrayOperacoesTemp.get(FramePrincipal.arrayOperacoesTemp.size()-1).valor);
        return imagem;
    }
    
    public static BufferedImage Rotacao90Save(){
        soma = soma + 90;
        int centroLinha = FramePrincipal.imagemASerSalvaEmDisco.getHeight()/2;
        int centroColuna = FramePrincipal.imagemASerSalvaEmDisco.getWidth()/2;
        boolean[][] matrizMapeamentoImagemASerExibida = new boolean[FramePrincipal.imagemASerSalvaEmDisco.getHeight()][FramePrincipal.imagemASerSalvaEmDisco.getWidth()];

        for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                    matrizMapeamentoImagemASerExibida[i][j] = false;
                    Color c = new Color(0, 51, 102);
                    FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
            }
        }

        for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
            for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
                Color c = new Color(FramePrincipal.imagemCarregadaDoDisco.getRGB(j, i));
                int novaLinha = (int) Math.round(centroLinha + (i - centroLinha) * Math.cos(Math.toRadians(soma)) - (j - centroColuna) * Math.sin(Math.toRadians(soma)));
                int novaColuna = (int) Math.round(centroColuna + (i - centroLinha) * Math.sin(Math.toRadians(soma)) + (j - centroColuna) * Math.cos(Math.toRadians(soma)));
                if (novaLinha >= 0 && novaLinha < FramePrincipal.imagemASerSalvaEmDisco.getHeight() && novaColuna >= 0 && novaColuna < FramePrincipal.imagemASerSalvaEmDisco.getWidth()) {
                    FramePrincipal.imagemASerSalvaEmDisco.setRGB(novaColuna, novaLinha, c.getRGB());
                    matrizMapeamentoImagemASerExibida[novaLinha][novaColuna] = true;
                }
            }
        }
        
        for (int i = 0; i < FramePrincipal.imagemASerSalvaEmDisco.getHeight(); i++) {
            for (int j = 0; j < FramePrincipal.imagemASerSalvaEmDisco.getWidth(); j++) {
                Color c;
                if(matrizMapeamentoImagemASerExibida[i][j] == false){                    
                    if((j == 0 && i <= FramePrincipal.imagemASerSalvaEmDisco.getHeight() - 1)){
                        Color corADireita = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j + 1, i));
                        c = new Color(corADireita.getRed(), corADireita.getGreen(), corADireita.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                    }
                    else if(j == FramePrincipal.imagemASerSalvaEmDisco.getWidth() - 1 && i <= FramePrincipal.imagemASerSalvaEmDisco.getHeight() - 1){
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j - 1, i));
                        c = new Color(corAEsquerda.getRed(), corAEsquerda.getGreen(), corAEsquerda.getBlue());
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                    }
                    else{
                        Color corAEsquerda = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j - 1, i));
                        Color corADireita = new Color(FramePrincipal.imagemASerSalvaEmDisco.getRGB(j + 1, i));
                        int R = (corAEsquerda.getRed() + corADireita.getRed()) / 2;
                        int G = (corAEsquerda.getGreen() + corADireita.getGreen()) / 2;
                        int B = (corAEsquerda.getBlue() + corADireita.getBlue()) / 2;
                        c = new Color(R, G, B);
                        matrizMapeamentoImagemASerExibida[i][j] = true;
                        FramePrincipal.imagemASerSalvaEmDisco.setRGB(j, i, c.getRGB());
                    }
                }
            }
        }
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        return FramePrincipal.imagemASerSalvaEmDisco;
    }
    
    public static BufferedImage EspelharVertical(){
        
        imagem = new BufferedImage(FramePrincipal.imagemASerExibida.getWidth(), FramePrincipal.imagemASerExibida.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i < imagem.getHeight(); i++){
            for(int j = 0, larguraDaImagem = imagem.getWidth() - 1; j < imagem.getWidth(); j++, larguraDaImagem--){
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                imagem.setRGB(larguraDaImagem, i,c.getRGB());
            }
        }        
        for(int i = 0; i < imagem.getHeight(); i++){
            for(int j = 0; j < imagem.getWidth(); j++){
                Color c = new Color(imagem.getRGB(j, i));
                FramePrincipal.imagemASerExibida.setRGB(j, i,c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        framePrincipal.foiAplicado = true;
        return imagem;
    }
    
    public static BufferedImage EspelharHorizontal(){
        
        imagem = new BufferedImage(FramePrincipal.imagemASerExibida.getWidth(), FramePrincipal.imagemASerExibida.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0, alturaDaImagem = imagem.getHeight()-1; i < imagem.getHeight(); i++, alturaDaImagem--){
            for(int j = 0; j < imagem.getWidth(); j++){
                Color c = new Color(FramePrincipal.imagemASerExibida.getRGB(j, i));
                imagem.setRGB(j, alturaDaImagem,c.getRGB());
            }
        }        
        for(int i = 0; i < imagem.getHeight(); i++){
            for(int j = 0; j < imagem.getWidth(); j++){
                Color c = new Color(imagem.getRGB(j, i));
                FramePrincipal.imagemASerExibida.setRGB(j, i,c.getRGB());
            }
        }
        
        labelImagem.setIcon(new ImageIcon(FramePrincipal.imagemASerExibida));
        framePrincipal.foiAplicado = true;
        return imagem;
    }
    
    public static BufferedImage PinchVertical(BufferedImage entrada){        
        /**
         *O metodo PinchVertical() "belisca" a imagem em suas linhas.
         * Para isto, é utilizada a técnica de contração de linhas na imagem original,
         * e é por isso que este efeito recebe o nome “vertical”.
         */
               
        double indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        while (indpinch < 1 || indpinch > entrada.getHeight()) {
            indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        }
        
        imagem = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        int centrolin = entrada.getHeight() / 2;
        int centrocol = entrada.getWidth() / 2;
        double fator = 0.0;
        boolean[][] mat = new boolean[entrada.getHeight()][entrada.getWidth()];

        for (int i = 0; i < entrada.getHeight(); i++) {
            for (int j = 0; j < entrada.getWidth(); j++) {
                mat[i][j] = false;
                Color c = new Color(0, 102, 102);
                imagem.setRGB(j, i, c.getRGB());
            }
        }

        for (int i = 0; i < entrada.getHeight(); i++) {
            if (i <= centrolin) {
                fator = (indpinch - 1) / centrolin * i + 1;
            } else if (i > centrolin) {
                fator = -1 * (indpinch - 1) / (entrada.getHeight() - 1 - centrolin) * (i - centrolin) + indpinch;
            }

            for (int j = 0; j < entrada.getWidth(); j++) {

                Color c = new Color(entrada.getRGB(j, i));
                int novacol = (int) Math.round((j - centrocol) / fator + centrocol);

                if (novacol >= 0 && novacol < entrada.getWidth()) {
                    imagem.setRGB(novacol, i, c.getRGB());
                }
            }
        }
            
        return imagem;
    }
    
    public static BufferedImage PinchVerticalDeBordas(BufferedImage entrada){        
        /**
         *O PinchVerticalDeBordas() se assemelha ao Pinch Vertical, diferindo apenas no cálculo do fator de contração de cada linha.
         * O resultado obtido pela aplicação deste filtro é uma imagem com Pinch inverso ao exemplo anterior.
         * A linha central da imagem é mantida intacta e todas as outras sofrem o achatamento causado pela contração das linhas.  
         */
        
        double indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        while (indpinch < 0 || indpinch > entrada.getHeight()) {
            indpinch = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um numero inteiro entre 0 e " + entrada.getHeight() + " para contrair a imagem "));
        }

        imagem = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        int centrolin = entrada.getHeight() / 2;
        int centrocol = entrada.getWidth() / 2;
        double fator = 0.0;        
        boolean[][] mat = new boolean[entrada.getHeight()][entrada.getWidth()];

        for (int i = 0; i < entrada.getHeight(); i++) {
            for (int j = 0; j < entrada.getWidth(); j++) {
                mat[i][j] = false;
                Color c = new Color(0, 102, 102);
                imagem.setRGB(j, i, c.getRGB());
            }
        }

        for (int i = 0; i < entrada.getHeight(); i++) {
            if (i <= centrolin) {
                fator = -(indpinch - 1) / centrolin * i + indpinch;
            } else if (i > centrolin) {
                fator = (indpinch - 1) / (entrada.getHeight() - 1 - centrolin) * (i - centrolin) + 1;
            }

            for (int j = 0; j < entrada.getWidth(); j++) {

                Color c = new Color(entrada.getRGB(j, i));
                int novacol = (int) Math.round((j - centrocol) / fator + centrocol);

                if (novacol >= 0 && novacol < entrada.getWidth()) {
                    imagem.setRGB(novacol, i, c.getRGB());
                }
            }
        }
        
        return imagem;
    }
    
    public static BufferedImage ZoomIn(BufferedImage entrada){        
        /**
         *O metodo ZoomIn() implementada o conceito de interpolação bilinear.
         * A imagem resultante consiste em uma apliacao da imagem original de acordo com o fator fornecido pelo usuario.
         * O fator deve ser maior que 1.0 .
         */
        
        imagem = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        double fator = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um fator maior que 1 para o Zoom In: "));
        while (fator <= 1) {
            fator = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite um fator maior que 1 para o Zoom In: "));
        }

        double passo = 1.0 / fator;
        double i;
        double j;
        int a;
        int b;

        for (i = 0.0, a = 0; a < entrada.getHeight(); a++) {
            for (j = 0.0, b = 0; b < entrada.getWidth(); b++) {

                Color pixel1 = new Color(entrada.getRGB((int) j, (int) i));
                Color pixel2 = new Color(entrada.getRGB((int) j, (int) (i + 1)));
                Color pixel3 = new Color(entrada.getRGB((int) (j + 1), (int) i));
                Color pixel4 = new Color(entrada.getRGB((int) (j + 1), (int) (i + 1)));

                double calc1;
                double calc2;
                double calc3;

                calc1 = pixel1.getRed() + (i - ((int) i)) * (pixel2.getRed() - pixel1.getRed());
                calc2 = pixel3.getRed() + (i - ((int) i)) * (pixel4.getRed() - pixel3.getRed());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int R = (int) Math.round(calc3);

                calc1 = pixel1.getGreen() + (i - ((int) i)) * (pixel2.getGreen() - pixel1.getGreen());
                calc2 = pixel3.getGreen() + (i - ((int) i)) * (pixel4.getGreen() - pixel3.getGreen());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int G = (int) Math.round(calc3);

                calc1 = pixel1.getBlue() + (i - ((int) i)) * (pixel2.getBlue() - pixel1.getBlue());
                calc2 = pixel3.getBlue() + (i - ((int) i)) * (pixel4.getBlue() - pixel2.getBlue());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int B = (int) Math.round(calc3);

                Color c = new Color(R, G, B);
                imagem.setRGB(b, a, c.getRGB());

                j = j + passo;
            }
            i = i + passo;
        }
        
        return imagem;
    }
    
    public static BufferedImage ZoomOut(BufferedImage entrada){        
        /**
         *A operação de Zoom Out é implementada seguindo basicamente o mesmo raciocínio que a operação de Zoom In,
         * diferindo apenas nos valores permitidos como entrada.
         * No Zoom In, o usuário pode entrar com quaisquer valores maiores que 1.0.
         * No Zoom Out, o fator de redução da imagem deve estar obrigatoriamente entre 0 e 1.0.  
         */
        imagem = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
        double passo = 1.7;
        EstruturaRGB[][] mat = new EstruturaRGB[700][700];
        double i;
        double j;
        int a;
        int b;

        for (a = 0; a < 700; a++) {
            for (b = 0; b < 700; b++) {
                mat[a][b] = new EstruturaRGB();
            }
        }

        for (i = 0.0, a = 0; a < 700; a++) {
            for (j = 0.0, b = 0; b < 700; b++) {

                Color pixel1 = new Color(entrada.getRGB((int) j, (int) i));
                Color pixel2 = new Color(entrada.getRGB((int) j, (int) (i + 1)));
                Color pixel3 = new Color(entrada.getRGB((int) (j + 1), (int) i));
                Color pixel4 = new Color(entrada.getRGB((int) (j + 1), (int) (i + 1)));

                double calc1;
                double calc2;
                double calc3;

                calc1 = pixel1.getRed() + (i - ((int) i)) * (pixel2.getRed() - pixel1.getRed());
                calc2 = pixel3.getRed() + (i - ((int) i)) * (pixel4.getRed() - pixel3.getRed());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int R = (int) Math.round(calc3);

                calc1 = pixel1.getGreen() + (i - ((int) i)) * (pixel2.getGreen() - pixel1.getGreen());
                calc2 = pixel3.getGreen() + (i - ((int) i)) * (pixel4.getGreen() - pixel3.getGreen());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int G = (int) Math.round(calc3);

                calc1 = pixel1.getBlue() + (i - ((int) i)) * (pixel2.getBlue() - pixel1.getBlue());
                calc2 = pixel3.getBlue() + (i - ((int) i)) * (pixel4.getBlue() - pixel2.getBlue());
                calc3 = calc1 + (j - ((int) j)) * (calc2 - calc1);
                if (calc3 > 255.0) {
                    calc3 = 255.0;
                }
                if (calc3 < 0.0) {
                    calc3 = 0.0;
                }
                int B = (int) Math.round(calc3);

                mat[a][b].r = R;
                mat[a][b].g = G;
                mat[a][b].b = B;

                j = j + passo;
            }
            i = i + passo;
        }

        for (a = 0; a < 700; a++) {
            for (b = 0; b < 700; b++) {
                if (a < 700 && b < 700) {
                    Color c;
                    c = new Color((int) mat[a][b].r, (int) mat[a][b].g, (int) mat[a][b].b);
                    imagem.setRGB(b, a, c.getRGB());
                } else {
                    Color c = new Color(0, 102, 102);
                    imagem.setRGB(b, a, c.getRGB());
                }
            }
        }
        
        return imagem;
    }
    
    public static void AplicarMorfologicas(){
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
        
        YIQ.RGBparaYIQ();
        HSL.RGBparaHSL();
        soma = 0;
        
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
    
    public static void reverterMorfologicas(){        
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
        soma = 0;
        
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
        soma = 0;
        
        FramePrincipal.arrayOperacoesTemp.clear();
        if(FramePrincipal.arrayOperacoesTemp.isEmpty()){
            System.out.println("Array vazio.");
        }
    }
    
    public static void setLabel(JLabel label){
        labelImagem = label;
    }
    
    public static BufferedImage imagem;
    public static JLabel labelImagem;
    public static int IdMorfologicas = 5;
    public static int soma = 0;
    public static FramePrincipal.Operacao eltoTemp = new FramePrincipal.Operacao();
    
    static class EstruturaRGB {
        double r;
        double g;
        double b;
        double peso;
    }
}
